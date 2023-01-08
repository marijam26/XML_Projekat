package project.p1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.p1.model.p1.ZahtevZaPatent;
import project.p1.service.PatentService;

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


    @PostMapping(value = "/", consumes = MediaType.APPLICATION_XML_VALUE)
    public void add(@RequestBody ZahtevZaPatent zahtevZaPatent){
        try {
            patentService.save(zahtevZaPatent);
        } catch (Exception e) {
            // vrati bad request
        }
    }

}
