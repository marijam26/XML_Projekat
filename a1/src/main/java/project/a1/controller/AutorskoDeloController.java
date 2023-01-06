package project.a1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.a1.model.a1.ZahtevZaAutorskaDela;
import project.a1.service.AutorskoDeloService;

import java.util.List;

@RestController
@RequestMapping(value = "api/autorskoPravo")
public class AutorskoDeloController {

    @Autowired
    private AutorskoDeloService autorskoDeloService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<ZahtevZaAutorskaDela>> getAll(){

        return null;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<ZahtevZaAutorskaDela>> getOne(@PathVariable String id){

        return null;
    }

    @GetMapping(value = "/proba", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<ZahtevZaAutorskaDela>> proba(){
        ZahtevZaAutorskaDela zahtevZaAutorskaDela = new ZahtevZaAutorskaDela();
        try {
            autorskoDeloService.save(zahtevZaAutorskaDela);
        } catch (Exception e) {
        // vrati bad request
    }
        return null;
    }


    @PostMapping(value = "/", consumes = MediaType.APPLICATION_XML_VALUE)
    public void add(@RequestBody ZahtevZaAutorskaDela zahtevZaAutorskaDela){
        try {
            autorskoDeloService.save(zahtevZaAutorskaDela);
        } catch (Exception e) {
            // vrati bad request
        }
    }


}
