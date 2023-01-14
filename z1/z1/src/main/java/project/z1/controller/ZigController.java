package project.z1.controller;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;
import project.z1.model.z1.ZahtevZaZig;
import project.z1.service.ZigService;
import project.z1.util.MarshallingUtils;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "api/zig")
public class ZigController {

    @Autowired
    private ZigService zigService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<ZahtevZaZig>> getAll(){

        return null;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ZahtevZaZig> getOne(@PathVariable String id){
        ZahtevZaZig zahtevZaZig = zigService.getZig(id);
        return new ResponseEntity<>(zahtevZaZig, HttpStatus.OK);
    }

    @GetMapping(value = "/save1")
    public ResponseEntity<List<ZahtevZaZig>> save() throws JAXBException, XMLDBException {
        MarshallingUtils marshallingUtils = new MarshallingUtils();
        ZahtevZaZig z = marshallingUtils.unmarshall("src/main/resources/data/xsd/zig.xml");
        System.out.println(z);
        zigService.save(z);
        return null;
    }


    @PostMapping(value = "/", consumes = MediaType.APPLICATION_XML_VALUE)
    public void add(@RequestBody ZahtevZaZig zahtevZaZig){
        try {
            zigService.save(zahtevZaZig);
        } catch (Exception e) {
            // vrati bad request
        }
    }

    @GetMapping("/getPdf/{id}")
    public ResponseEntity<String> getPdf(@PathVariable String id) throws DocumentException, IOException {
        zigService.getDocumentPdf(id);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
