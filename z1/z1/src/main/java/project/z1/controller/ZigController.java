package project.z1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;
import project.z1.model.z1.ZahtevZaZig;
import project.z1.service.ZigService;

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
    public ResponseEntity<List<ZahtevZaZig>> getOne(@PathVariable String id){

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
}
