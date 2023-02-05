package project.p1.repository;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.springframework.stereotype.Repository;
import org.xmldb.api.modules.XMLResource;
import project.p1.model.p1.ZahtevZaPatent;
import project.p1.util.AuthenticationUtilities;
import project.p1.util.MarshallingUtils;
import project.p1.util.SparqlUtil;

import javax.xml.bind.JAXBException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class MetadataRepository {

    private static final String XSL_FILE = "src/main/resources/data/xslt/metadata.xsl";
    private static final String RDF_FILE = "src/main/resources/data/rdf/";
    private static final String SPARQL_NAMED_GRAPH_URI = "metadata";
    private final TransformerFactory transformerFactory;

    public MetadataRepository(){
        this.transformerFactory = TransformerFactory.newInstance();
    }

    public void extractMetadata(ZahtevZaPatent zahtevZaPatent) throws IOException, JAXBException, TransformerException {
        AuthenticationUtilities.RDFConnectionProperties conn = AuthenticationUtilities.loadRdfProperties();
        ByteArrayOutputStream outputStream = (ByteArrayOutputStream) new MarshallingUtils().marshall(zahtevZaPatent);
        InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        extractMetadata(inputStream, new FileOutputStream(RDF_FILE + zahtevZaPatent.getId() + ".rdf"));

        Model model = ModelFactory.createDefaultModel();
        model.read(RDF_FILE + zahtevZaPatent.getId() + ".rdf");

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

    public static List<RDFNode> searchMetadata(String sparqlQuery) throws IOException {
        AuthenticationUtilities.RDFConnectionProperties conn = AuthenticationUtilities.loadRdfProperties();

        QueryExecution query = QueryExecutionFactory.sparqlService(conn.queryEndpoint, sparqlQuery);
        ResultSet results = query.execSelect();

        String varName = "Id";
        List<RDFNode> nodes = new ArrayList<>();

        while (results.hasNext()) {
            QuerySolution querySolution = results.next();
            nodes.add(querySolution.get(varName));
        }

        query.close();
        return nodes;
    }


    public static String getMetadataAdvancedQuery(List<String> preds, List<String> values, List<String> operators) throws IOException {
        AuthenticationUtilities.RDFConnectionProperties conn = AuthenticationUtilities.loadRdfProperties();

        StringBuilder query = new StringBuilder();
        StringBuilder filter = new StringBuilder();
        query.append(String.format("SELECT * FROM <%s> WHERE { ", conn.dataEndpoint + SPARQL_NAMED_GRAPH_URI));
        filter.append("\nFILTER(");

        String equals = "=";
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

        return query.toString();
    }

    public static void main(String[] args) throws JAXBException, IOException, TransformerException{
        ZahtevZaPatent zahtevZaPatent = new MarshallingUtils().unmarshall("src/main/resources/data/patent1.xml");
        MetadataRepository repo = new MetadataRepository();
        repo.extractMetadata(zahtevZaPatent);
    }

}
