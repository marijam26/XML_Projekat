package project.p1.service;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.xmldb.api.base.XMLDBException;
import project.p1.model.p1.ZahtevZaPatent;
import project.p1.repository.PatentRepository;
import project.p1.util.MarshallingUtils;
import project.p1.util.PDFTransformer;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class PatentService {

    @Autowired
    private PatentRepository patentRepository;


    public void save(ZahtevZaPatent zahtevZaPatent) throws JAXBException, XMLDBException {
        MarshallingUtils marshallingUtils = new MarshallingUtils();
        OutputStream os = marshallingUtils.marshall(zahtevZaPatent);
        patentRepository.save(os);
    }

    public ZahtevZaPatent getPatent(String id){
        return patentRepository.getPatentById(id);
    }

    public void getDocumentPdf(String id) throws DocumentException, IOException {
        PDFTransformer pdfTransformer = new PDFTransformer();
        String fileNamePDF = "p" + id + ".pdf";
        String fileNameHTML = "p" + id + ".html";

        Node patentNode = patentRepository.getPatentNode(id);

        pdfTransformer.generateHTML(fileNameHTML,patentNode);
        pdfTransformer.generatePDF(fileNamePDF,fileNameHTML);

    }
}
