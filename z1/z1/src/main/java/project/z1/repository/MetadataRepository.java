package project.z1.repository;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.springframework.stereotype.Repository;
import project.z1.model.z1.ZahtevZaZig;
import project.z1.util.AuthenticationUtilities;
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


@Repository
public class MetadataRepository {

    private static final String XSL_FILE = "src/main/resources/data/xsl/metadata.xsl";
    private static final String RDF_FILE = "src/main/resources/data/rdf/z1.rdf";
    private static final String SPARQL_NAMED_GRAPH_URI = "metadata";
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

    public static void main(String[] args) throws JAXBException, IOException, TransformerException{
        ZahtevZaZig z = new MarshallingUtils().unmarshall("src/main/resources/data/xsd/zig.xml");
        MetadataRepository repo = new MetadataRepository();
        repo.extractMetadata(z);
    }

}
