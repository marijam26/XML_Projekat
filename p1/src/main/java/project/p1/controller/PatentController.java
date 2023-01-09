package project.p1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;
import project.p1.model.p1.ZahtevZaPatent;
import project.p1.service.PatentService;
import project.p1.util.MarshallingUtils;

import javax.xml.bind.JAXBException;
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
    public ResponseEntity<List<ZahtevZaPatent>> getOne(@PathVariable String id){

        return null;
    }

    @GetMapping(value = "/save1")
    public ResponseEntity<List<ZahtevZaPatent>> getOne() throws JAXBException, XMLDBException {
        MarshallingUtils marshallingUtils = new MarshallingUtils();
        ZahtevZaPatent z = marshallingUtils.unmarshall("data/patent.xml");
        System.out.println(z);
        patentService.save(z);
        return null;
    }



    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_XML_VALUE)
    public void add(@RequestBody ZahtevZaPatent zahtevZaPatent){
        try {
            //ovo je za proveru
            MarshallingUtils marshallingUtils = new MarshallingUtils();
            ZahtevZaPatent z = marshallingUtils.unmarshall("data/patent.xml");
            System.out.println(z);
            patentService.save(z);
        } catch (Exception e) {
            // vrati bad request
            System.out.println(e.getMessage());
        }
    }

}
