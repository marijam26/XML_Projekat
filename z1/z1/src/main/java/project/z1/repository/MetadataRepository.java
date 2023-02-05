package project.z1.repository;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.z1.model.z1.ZahtevZaZig;
import project.z1.util.AuthenticationUtilities;
import project.z1.util.DatabaseUtilities;
import project.z1.util.MarshallingUtils;
import project.z1.util.SparqlUtil;

import javax.xml.bind.JAXBException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Repository
public class MetadataRepository {

    private static final String XSL_FILE = "src/main/resources/data/xsl/metadata.xsl";
    private static final String RDF_FILE = "src/main/resources/data/rdf/z1.rdf";
    private static final String SPARQL_NAMED_GRAPH_URI = "/metadata";
    private final TransformerFactory transformerFactory;


    public MetadataRepository(){
        this.transformerFactory = TransformerFactory.newInstance();
    }

    public void extractMetadata(ZahtevZaZig zahtevZaZig) throws IOException, JAXBException, TransformerException {
        AuthenticationUtilities.RDFConnectionProperties conn = AuthenticationUtilities.loadRdfProperties();
        ByteArrayOutputStream outputStream = (ByteArrayOutputStream) new MarshallingUtils().marshall(zahtevZaZig);
        InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        extractMetadata(inputStream, new FileOutputStream(RDF_FILE));

        Model model = ModelFactory.createDefaultModel();
        model.read(RDF_FILE);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        model.write(out, SparqlUtil.NTRIPLES);
        model.write(System.out, SparqlUtil.RDF_XML);
        String sparqlUpdate = SparqlUtil.insertData(conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI, out.toString());
        UpdateRequest update = UpdateFactory.create(sparqlUpdate);
        UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, conn.updateEndpoint);
        processor.execute();

        String sparqlQuery = SparqlUtil.selectData(conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI, "?s ?p ?o");
        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);

        ResultSet results = query.execSelect();
        ResultSetFormatter.out(System.out, results);
        query.close();

    }

    public void extractMetadata(InputStream in, OutputStream out) throws TransformerException {
        StreamSource transformSource = new StreamSource(new File(XSL_FILE));
        Transformer grddlTransformer = transformerFactory.newTransformer(transformSource);
        grddlTransformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
        grddlTransformer.setOutputProperty(OutputKeys.INDENT, "yes");

        StreamSource source = new StreamSource(in);
        StreamResult result = new StreamResult(out);

        grddlTransformer.transform(source, result);
    }

    public String getMetadataSimpleQuery(String pred, String value) throws IOException {
        AuthenticationUtilities.RDFConnectionProperties conn = AuthenticationUtilities.loadRdfProperties();
        return String.format(SparqlUtil.SIMPLE_METADATA,  conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI, pred, value);
    }

    public List<String> searchMetadata(String sparqlQuery) throws IOException {
        AuthenticationUtilities.RDFConnectionProperties conn = AuthenticationUtilities.loadRdfProperties();

        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
        ResultSet results = query.execSelect();

        String varName = "Id";
        List<String> nodes = new ArrayList<>();
        while (results.hasNext()) {
            QuerySolution querySolution = results.next();
            nodes.add(querySolution.get(varName).toString());
        }

        query.close();
        return nodes;
    }

    public String getMetadataAdvancedQuery(List<String> preds, List<String> values, List<String> operators) throws IOException {
        AuthenticationUtilities.RDFConnectionProperties conn = AuthenticationUtilities.loadRdfProperties();

        StringBuilder query = new StringBuilder();
        StringBuilder filter = new StringBuilder();
        query.append(String.format("SELECT * FROM <%s> WHERE { ", conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI));
        filter.append("\nFILTER(");

        String equals = "=";

        if(operators.isEmpty()){
            query.append(String.format("\n\t ?z <http://ftn.uns.ac.rs/z/pred/%s> ?%s .", preds.get(0), preds.get(0)));
            filter.append(String.format("?%s = \"%s\")\n}", preds.get(0), equals, values.get(0)));
        }

        else{
            if(operators.get(0).equals("!")){
                equals = "!=";
            }
            String nextEquals;
            for(int i = 0; i < preds.size(); i++){

                if(i != preds.size() - 1 && operators.get(i+1).contains("!")){
                    nextEquals = "!=";
                    operators.set(i+1, operators.get(i+1).substring(0, 2));
                }
                else{
                    nextEquals = "=";
                }

                query.append(String.format("\n\t ?z <http://ftn.uns.ac.rs/z/pred/%s> ?%s .", preds.get(i), preds.get(i)));
                if(i != preds.size() - 1) {
                    filter.append(String.format("?%s %s \"%s\" %s ", preds.get(i), equals, values.get(i), operators.get(i+1)));
                }
                else{
                    filter.append(String.format("?%s %s \"%s\")\n}", preds.get(i), equals, values.get(i)));
                    query.append(filter);
                }

                equals = nextEquals;
            }
        }



        return query.toString();
    }

    public void nesto() throws IOException {
//        ZahtevZaZig z = new MarshallingUtils().unmarshall("src/main/resources/data/xsd/zig.xml");
//        MetadataRepository repo = new MetadataRepository();
//        repo.extractMetadata(z);
        List<String> preds = Arrays.asList("Id", "Datum_podnosenja");
        List<String> values = Arrays.asList("Z-1-2023", "2022-12-06");
        List<String> operators = Arrays.asList(" ", "&&");

        String query = getMetadataAdvancedQuery(preds, values, operators);
        System.out.println(query);
        List<String> nodes = searchMetadata(query);
        for(String node: nodes){
            System.out.println(node);
            ZahtevZaZig zahtevZaZig = DatabaseUtilities.getZig(node, "db/zigovi");
            System.out.println(zahtevZaZig.getId());
            System.out.println(zahtevZaZig.getZig().getBoja().get(0));
        }
    }

}
