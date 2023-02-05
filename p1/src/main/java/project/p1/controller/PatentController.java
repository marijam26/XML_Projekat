package project.p1.controller;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;
import project.p1.dto.*;
import project.p1.model.p1.ZahtevZaPatent;
import project.p1.model.resenje.Resenje;
import project.p1.service.PatentService;
import project.p1.util.MarshallingUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "api/patent")
public class PatentController {

    @Autowired
    private PatentService patentService;

    @GetMapping(value = "/getAll", produces = MediaType.APPLICATION_XML_VALUE,consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ListaZahtevaZaPatentDTO> getAll(HttpServletRequest request){
        try {
            List<ZahtevZaPatent> zahtevZaPatentList = patentService.getAllPatents();
            ListaZahtevaZaPatentDTO listaZahtevaZaPatentDTO = new ListaZahtevaZaPatentDTO(zahtevZaPatentList);
            return new ResponseEntity<>(listaZahtevaZaPatentDTO,HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/getAllZahetve", produces = MediaType.APPLICATION_XML_VALUE,consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ListaZahtevaZaPatentDTO> getAllZahteve(HttpServletRequest request){
        try {
            List<ZahtevZaPatent> zahtevZaPatentList = patentService.getAllZahtevePatents();
            ListaZahtevaZaPatentDTO listaZahtevaZaPatentDTO = new ListaZahtevaZaPatentDTO(zahtevZaPatentList);
            return new ResponseEntity<>(listaZahtevaZaPatentDTO,HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/getAllOdobrene", produces = MediaType.APPLICATION_XML_VALUE,consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ListaZahtevaZaPatentDTO> getAllOdobrene(HttpServletRequest request){
        try {
            List<ZahtevZaPatent> zahtevZaPatentList = patentService.getAllOdobrenePatents();
            ListaZahtevaZaPatentDTO listaZahtevaZaPatentDTO = new ListaZahtevaZaPatentDTO(zahtevZaPatentList);
            return new ResponseEntity<>(listaZahtevaZaPatentDTO,HttpStatus.OK);
        }catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ZahtevZaPatent> getOne(@PathVariable String id){
        ZahtevZaPatent zahtevZaPatent = patentService.getPatent(id);
        return new ResponseEntity<>(zahtevZaPatent, HttpStatus.OK);
    }

    @GetMapping(value = "/save1")
    public ResponseEntity<List<ZahtevZaPatent>> save() throws JAXBException, XMLDBException, DocumentException, IOException {
        MarshallingUtils marshallingUtils = new MarshallingUtils();
        ZahtevZaPatent z = marshallingUtils.unmarshall("src/main/resources/data/patent1.xml");
        System.out.println(z);
        patentService.save(z);
        patentService.getDocumentPdf(z.getId());
        return null;
    }


    @PostMapping(value = "/save", consumes = "application/xml")
    public void add(@RequestBody ZahtevZaPatentDTO zahtevZaPatentDTO){
        try {
            ZahtevZaPatent zahtev = patentService.map(zahtevZaPatentDTO);
            patentService.save(zahtev);
            patentService.saveMetadataForZahtev(zahtev);
            patentService.getDocumentPdf(zahtev.getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @PostMapping(value = "/saveResenje", consumes = "application/xml")
    public void addResenje(@RequestBody ResenjeDTO resenjeDTO){
        try {
            Resenje resenje = patentService.map(resenjeDTO);
            patentService.save(resenje);
        } catch (Exception e) {
            // vrati bad request
            System.out.println(e.getMessage());
        }
    }

    @GetMapping(value = "/search/{data}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<ZahtevZaPatent>> search(@PathVariable String data) throws Exception {
        List<ZahtevZaPatent> zahtevi = patentService.search(data);
        return new ResponseEntity<>(zahtevi, HttpStatus.OK);
    }

    @GetMapping(value = "/searchMetadata/{pred}/{value}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<ZahtevZaPatent>> searchMetadata(@PathVariable String pred, @PathVariable String value) throws Exception {
        List<ZahtevZaPatent> zahtevi = patentService.searchMetadata(pred, value);
        return new ResponseEntity<>(zahtevi, HttpStatus.OK);
    }


    @PostMapping(value = "/searchMetadata/advanced", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<ZahtevZaPatent>> searchMetadataAdvanced(@RequestBody MetadataSearchDTO data) throws Exception {
        List<ZahtevZaPatent> zahtevi = patentService.searchMetadataAdvanced(data);
        return new ResponseEntity<>(zahtevi, HttpStatus.OK);
    }


    @GetMapping("/getPdf/{id}")
    public ResponseEntity<String> getPdf(@PathVariable String id) throws DocumentException, IOException {
        patentService.getDocumentPdf(id);
        return new ResponseEntity<>("Uspesno", HttpStatus.OK);
    }

    @GetMapping("/metadata/{id}")
    public ResponseEntity<String> saveMetadata(@PathVariable String id) throws JAXBException, IOException, TransformerException {
        //patentService.saveMetadataForZahtev(id);
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

    @RequestMapping("/downloadRDF/{fileName}")
    public void downloadRDFResource(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileName") String fileName) throws IOException {
        String path = "src/main/resources/data/rdf/" + fileName;
        File file = new File(path);
        if (file.exists()) {
            String mimeType = "application/xml";
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
            response.setContentLength((int) file.length());
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        }
    }

    @PostMapping(value = "/report",consumes = "application/xml")
    public ResponseEntity<String> getIzvestaj(@RequestBody IzvestajDTO izvestajDTO) throws DocumentException, FileNotFoundException, XMLDBException {
        patentService.kreirajIzvestaj(izvestajDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
