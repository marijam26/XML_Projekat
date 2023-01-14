package project.z1.service;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.xmldb.api.base.XMLDBException;
import project.z1.model.z1.ZahtevZaZig;
import project.z1.repository.ZigRepository;
import project.z1.util.MarshallingUtils;
import project.z1.util.PDFTransformer;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class ZigService {

    @Autowired
    private ZigRepository zigRepository;

    public void save(ZahtevZaZig zahtevZaZig) throws JAXBException, XMLDBException {
        MarshallingUtils marshallingUtils = new MarshallingUtils();
        OutputStream os = marshallingUtils.marshall(zahtevZaZig);
        zigRepository.save(os);
    }


    public void getDocumentPdf(String id) throws DocumentException, IOException {
        PDFTransformer pdfTransformer = new PDFTransformer();
        String fileNamePDF = id + ".pdf";
        String fileNameHTML = id + ".html";
        Node zig = zigRepository.getZigNode(id);

        pdfTransformer.generateHTML(fileNameHTML, zig);
        pdfTransformer.generatePDF(fileNamePDF,fileNameHTML);

    }

    public ZahtevZaZig getZig(String id){
        return zigRepository.getById(id);
    }

}
