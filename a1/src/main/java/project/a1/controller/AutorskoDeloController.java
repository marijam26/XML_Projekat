package project.a1.controller;

import com.itextpdf.text.DocumentException;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.a1.dto.a1.ZahtevZaAutorskaDelaDTO;
import project.a1.dto.main_schema.TPrilogDTO;
import project.a1.model.a1.Prilozi;
import project.a1.model.a1.ZahtevZaAutorskaDela;
import project.a1.model.main_schema.TPrilog;
import project.a1.service.AutorskoDeloService;
import project.a1.util.MarshallingUtils;

import javax.xml.bind.JAXBException;
import java.io.IOException;
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<ZahtevZaAutorskaDela> getOne(@PathVariable String id){
        try {
            ZahtevZaAutorskaDela z = autorskoDeloService.getOne(id);
            return new ResponseEntity<>(z, HttpStatus.OK);
        } catch (Exception e) {
        // vrati bad request
        System.out.print(e.getMessage());
    }
        return null;
    }

    @GetMapping(value = "/proba", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<ZahtevZaAutorskaDela>> proba() throws JAXBException {
        MarshallingUtils marshallingUtils = new MarshallingUtils();
        try {
            ZahtevZaAutorskaDela z = marshallingUtils.unmarshall("data/autorsko_delo.xml");
            Prilozi p = new Prilozi();
            TPrilog opis = new TPrilog();
            opis.setPutanja("data/nekaputanja");
            opis.setValue(true);
            p.setOpisDela(opis);
            z.setPrilozi(p);
            autorskoDeloService.save(z);
        } catch (Exception e) {
        // vrati bad request
            System.out.print(e.getMessage());
    }
        return null;
    }


    @PostMapping(value = "/", consumes = "application/xml")
    public void add(@RequestBody ZahtevZaAutorskaDelaDTO dto){
        try {
            ZahtevZaAutorskaDela zahtevZaAutorskaDela = autorskoDeloService.map(dto);
            autorskoDeloService.save(zahtevZaAutorskaDela);
        } catch (Exception e) {
            // vrati bad request
            System.out.print(e.getMessage());
        }
    }

    @GetMapping("/getPdf/{id}")
    public ResponseEntity<String> getPdf(@PathVariable String id) throws DocumentException, IOException {
        autorskoDeloService.getDocumentPdf(id);
        return new ResponseEntity<>("Uspesno", HttpStatus.OK);
    }


}
