package project.a1.controller;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;
import project.a1.dto.a1.ListaZahtevaZaAutorskoDeloDTO;
import project.a1.dto.a1.ZahtevZaAutorskaDelaDTO;
import project.a1.dto.main_schema.ResenjeDTO;
import project.a1.model.a1.Prilozi;
import project.a1.model.a1.ZahtevZaAutorskaDela;
import project.a1.model.main_schema.TPrilog;
import project.a1.model.resenje.Resenje;
import project.a1.service.AutorskoDeloService;
import project.a1.util.MarshallingUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.io.*;
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
            System.out.print(e.getMessage());
        }
    }

    @GetMapping("/getPdf/{id}")
    public ResponseEntity<String> getPdf(@PathVariable String id) throws DocumentException, IOException {
        autorskoDeloService.getDocumentPdf(id);
        return new ResponseEntity<>("Uspesno", HttpStatus.OK);
    }

    @GetMapping(value = "/search/{data}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<ZahtevZaAutorskaDela>> search(@PathVariable String data) throws Exception {
        List<ZahtevZaAutorskaDela> zahtevi = autorskoDeloService.search(data);
        return new ResponseEntity<>(zahtevi, HttpStatus.OK);
    }


    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_XML_VALUE,consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ListaZahtevaZaAutorskoDeloDTO> getAll(HttpServletRequest request){
        try {
            List<ZahtevZaAutorskaDela> zahtevZaPatentList = autorskoDeloService.getAll();
            ListaZahtevaZaAutorskoDeloDTO listaZahtevaZaDeloDTO = new ListaZahtevaZaAutorskoDeloDTO(zahtevZaPatentList);
            return new ResponseEntity<>(listaZahtevaZaDeloDTO,HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @GetMapping(value = "/getAllZahteve", produces = MediaType.APPLICATION_XML_VALUE,consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ListaZahtevaZaAutorskoDeloDTO> getAllZahteve(HttpServletRequest request){
        try {
            List<ZahtevZaAutorskaDela> zahtevList = autorskoDeloService.getAllRequests();
            ListaZahtevaZaAutorskoDeloDTO listaZahtevaZaPatentDTO = new ListaZahtevaZaAutorskoDeloDTO(zahtevList);
            return new ResponseEntity<>(listaZahtevaZaPatentDTO, HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/getAllOdobrene", produces = MediaType.APPLICATION_XML_VALUE,consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ListaZahtevaZaAutorskoDeloDTO> getAllOdobrene(HttpServletRequest request){
        try {
            List<ZahtevZaAutorskaDela> zahtevList = autorskoDeloService.getAllApprovedRequests();
            ListaZahtevaZaAutorskoDeloDTO listaZahtevaZaPatentDTO = new ListaZahtevaZaAutorskoDeloDTO(zahtevList);
            return new ResponseEntity<>(listaZahtevaZaPatentDTO,HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @PostMapping(value = "/saveResenje", consumes = "application/xml")
    public void addResenje(@RequestBody ResenjeDTO resenjeDTO){
        try {
            Resenje resenje = autorskoDeloService.map(resenjeDTO);
            autorskoDeloService.save(resenje);
        } catch (Exception e) {
            // vrati bad request
            System.out.println(e.getMessage());
        }
    }




    @GetMapping("/metadata/{id}")
    public ResponseEntity<String> saveMetadata(@PathVariable String id) throws JAXBException, XMLDBException, IOException, ClassNotFoundException, TransformerException, InstantiationException, IllegalAccessException {
        autorskoDeloService.saveMetadataForZahtev(id);
        return new ResponseEntity<>("OK",HttpStatus.OK);
    }

    @RequestMapping("/downloadPDF/{fileName}")
    public void downloadPDFResource(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileName") String fileName) throws IOException {
        String path = "src/main/resources/data/gen/" + fileName;
        File file = new File(path);
        if (file.exists()) {
            String mimeType = "application/pdf";
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
            response.setContentLength((int) file.length());
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        }
    }

    @RequestMapping("/downloadHTML/{fileName}")
    public void downloadHTMLResource(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileName") String fileName) throws IOException {
        String path = "src/main/resources/data/gen/" + fileName;
        File file = new File(path);
        if (file.exists()) {
            String mimeType = "application/html";
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
            response.setContentLength((int) file.length());
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        }
    }



}
