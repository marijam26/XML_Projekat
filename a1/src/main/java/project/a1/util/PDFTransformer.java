package project.a1.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import lombok.var;
import org.w3c.dom.Node;
import project.a1.model.a1.ZahtevZaAutorskaDela;
import project.a1.model.resenje.Resenje;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;


public class PDFTransformer {

    private static DocumentBuilderFactory documentFactory;

    private static TransformerFactory transformerFactory;

    public static final String XSL_FILE = "src/main/resources/data/xslt/autorskoPravo.xsl";
    public static final String XSL_RESENJE_FILE = "src/main/resources/data/xslt/resenje.xsl";
    public static final String OUTPUT_DIR = "src/main/resources/data/gen/";
    public static final String OUTPUT_DIR_RESENJE = "src/main/resources/data/resenje/";

    static {

        documentFactory = DocumentBuilderFactory.newInstance();
        documentFactory.setNamespaceAware(true);
        documentFactory.setIgnoringComments(true);
        documentFactory.setIgnoringElementContentWhitespace(true);

        transformerFactory = TransformerFactory.newInstance();

    }


    public void generatePDF(String filePath, String htmlFileName) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(OUTPUT_DIR+filePath));
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(OUTPUT_DIR+htmlFileName));
        document.close();
    }

    public void generateResenjePDF(String filePath, String htmlFileName) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(OUTPUT_DIR_RESENJE+filePath));
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(OUTPUT_DIR_RESENJE+htmlFileName));
        document.close();
    }


    public void generateHTML(String htmlFileName, Node xmlDoc) throws FileNotFoundException {
        try {
            StreamSource transformSource = new StreamSource(new File(XSL_FILE));
            Transformer transformer = transformerFactory.newTransformer(transformSource);
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

            DOMSource source = new DOMSource(xmlDoc);
            StreamResult result = new StreamResult(new FileOutputStream(OUTPUT_DIR+htmlFileName));
            transformer.transform(source, result);

        } catch (TransformerFactoryConfigurationError | TransformerException e) {
            e.printStackTrace();
        }

    }


    public void generateResenjeHTML(String htmlFileName, Node xmlDoc) throws FileNotFoundException {
        try {
            StreamSource transformSource = new StreamSource(new File(XSL_RESENJE_FILE));
            Transformer transformer = transformerFactory.newTransformer(transformSource);
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

            DOMSource source = new DOMSource(xmlDoc);
            StreamResult result = new StreamResult(new FileOutputStream(OUTPUT_DIR_RESENJE+htmlFileName));
            transformer.transform(source, result);

        } catch (TransformerFactoryConfigurationError | TransformerException e) {
            e.printStackTrace();
        }

    }

    public void generatePdfResenje(Resenje resenje,Node node) throws IOException, DocumentException {
        PDFTransformer pdfTransformer = new PDFTransformer();
        String fileNamePDF = resenje.getReferenca() + ".pdf";
        String fileNameHTML = resenje.getReferenca() + ".html";

        pdfTransformer.generateResenjeHTML(fileNameHTML,node);
        pdfTransformer.generateResenjePDF(fileNamePDF,fileNameHTML);
    }

}

