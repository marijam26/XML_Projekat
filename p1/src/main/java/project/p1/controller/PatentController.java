package project.p1.controller;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;
import project.p1.dto.ZahtevZaPatentDTO;
import project.p1.model.p1.ZahtevZaPatent;
import project.p1.service.PatentService;
import project.p1.util.MarshallingUtils;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "api/patent")
public class PatentController {

    @Autowired
    private PatentService patentService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<ZahtevZaPatent>> getAll(){
        return null;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ZahtevZaPatent> getOne(@PathVariable String id){
        ZahtevZaPatent zahtevZaPatent = patentService.getPatent(id);
        return new ResponseEntity<>(zahtevZaPatent, HttpStatus.OK);
    }

    @GetMapping(value = "/save1")
    public ResponseEntity<List<ZahtevZaPatent>> save() throws JAXBException, XMLDBException {
        MarshallingUtils marshallingUtils = new MarshallingUtils();
        ZahtevZaPatent z = marshallingUtils.unmarshall("src/main/resources/data/patent1.xml");
        System.out.println(z);
        patentService.save(z);
        return null;
    }


    @PostMapping(value = "/save", consumes = "application/xml")
    public void add(@RequestBody ZahtevZaPatentDTO zahtevZaPatentDTO){
        try {
            ZahtevZaPatent zahtev = patentService.map(zahtevZaPatentDTO);
            patentService.save(zahtev);
        } catch (Exception e) {
            // vrati bad request
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("/getPdf/{id}")
    public ResponseEntity<String> getPdf(@PathVariable String id) throws DocumentException, IOException {
        patentService.getDocumentPdf(id);
        return new ResponseEntity<>("Uspesno", HttpStatus.OK);
    }

    @GetMapping("/metadata/{id}")
    public ResponseEntity<String> saveMetadata(@PathVariable String id) throws JAXBException, IOException, TransformerException {
        patentService.saveMetadataForZahetv(id);
        return new ResponseEntity<>("OK",HttpStatus.OK);
    }

}
