package project.z1.controller;

import com.itextpdf.text.DocumentException;
import org.apache.jena.base.Sys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;
import project.z1.dto.ZahtevZaZigDTO;
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

    @PostMapping(value = "/save", consumes = "application/xml")
    public void add(@RequestBody ZahtevZaZigDTO zahtevZaZigDTO){
        try {
            ZahtevZaZig zahtevZaZig = zigService.map(zahtevZaZigDTO);
            zigService.save(zahtevZaZig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/getPdf/{id}")
    public ResponseEntity<String> getPdf(@PathVariable String id) throws DocumentException, IOException {
        zigService.getDocumentPdf(id);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping(value = "/search/{data}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<ZahtevZaZig>> search(@PathVariable String data) throws Exception {
        List<ZahtevZaZig> zahtevi = zigService.search(data);
        return new ResponseEntity<>(zahtevi, HttpStatus.OK);
    }

    @GetMapping(value = "/searchMetadata/{pred}/{value}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<ZahtevZaZig>> searchMetadata(@PathVariable String pred, @PathVariable String value) throws Exception {
        List<ZahtevZaZig> zahtevi = zigService.searchMetadata(pred, value);
        return new ResponseEntity<>(zahtevi, HttpStatus.OK);
    }


    @GetMapping(value = "/searchMetadata/advanced/{data}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<ZahtevZaZig>> searchMetadataAdvanced(@PathVariable String data) throws Exception {
        List<ZahtevZaZig> zahtevi = zigService.searchMetadataAdvanced(data);
        return new ResponseEntity<>(zahtevi, HttpStatus.OK);
    }

}
