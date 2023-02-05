package project.z1.controller;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;
import project.z1.dto.*;
import project.z1.model.resenje.Resenje;
import project.z1.model.z1.ZahtevZaZig;
import project.z1.repository.MetadataRepository;
import project.z1.service.ZigService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.util.List;

@RestController
@RequestMapping(value = "api/zig")
public class ZigController {

    @Autowired
    private ZigService zigService;

    @Autowired
    private MetadataRepository zigRepository;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ListaZahteva> getAll(){
        List<ZahtevZaZig> zahtevi = zigService.getAll();
        ListaZahteva lista = new ListaZahteva(zahtevi);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping(value = "/zahtevi", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ListaZahteva> getAllZahteve() throws XMLDBException {
        List<ZahtevZaZig> zahtevi = zigService.getAllZahteve();
        ListaZahteva lista = new ListaZahteva(zahtevi);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping(value = "/odobreni", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ListaZahteva> getAllOdobrene() throws XMLDBException {
        List<ZahtevZaZig> zahtevi = zigService.getAllOdobrene();
        ListaZahteva lista = new ListaZahteva(zahtevi);
        return new ResponseEntity<>(lista, HttpStatus.OK);
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
            zigService.getDocumentPdf(zahtevZaZig.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping(value = "/saveResenje", consumes = "application/xml")
    public void saveResenje(@RequestBody ResenjeDTO resenjeDTO){
        try {
            Resenje resenje = zigService.map(resenjeDTO);
            zigService.save(resenje);
        } catch (Exception e) {
            // vrati bad request
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("/getPdf/{id}")
    public ResponseEntity<String> getPdf(@PathVariable String id) throws DocumentException, IOException {
        zigService.getDocumentPdf(id);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    @GetMapping(value = "/search/{data}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ListaZahteva> search(@PathVariable String data) throws Exception {
        List<ZahtevZaZig> zahtevi = zigService.search(data);
        ListaZahteva lista = new ListaZahteva(zahtevi);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping(value = "/search/{pred}/{value}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ListaZahteva> searchMetadata(@PathVariable String pred, @PathVariable String value) throws Exception {
        List<ZahtevZaZig> zahtevi = zigService.searchMetadata(pred, value);
        ListaZahteva lista = new ListaZahteva(zahtevi);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }


    @GetMapping(value = "/searchMetadata/{value}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ListaZahteva> searchMetadataAdvanced(@PathVariable("value") String value) throws Exception {
        System.out.println(value);
        MetadataSearchDTO dto = zigService.getDtoFromString(value);

        List<ZahtevZaZig> zahtevi = zigService.searchMetadataAdvanced(dto);
        ListaZahteva lista = new ListaZahteva(zahtevi);
        return new ResponseEntity<>(lista, HttpStatus.OK);
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

    @RequestMapping("/download/{fileName}")
    public void get(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileName") String fileName) throws IOException {
        String path = "src/main/resources/data/files/" + fileName.split(":")[0] + "/"+ fileName.split(":")[1];
        File file = new File(path);
        if (file.exists()) {
            String mimeType = "application/octet-stream";
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
            response.setContentLength((int) file.length());
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        }
    }

    @GetMapping(value = "/save1")
    public ResponseEntity<List<ZahtevZaZig>> save() throws JAXBException, XMLDBException, IOException, TransformerException {
        zigRepository.nesto();
        return null;
    }


    @PostMapping(value = "/report",consumes = "application/xml")
    public ResponseEntity<String> getIzvestaj(@RequestBody IzvestajDTO izvestajDTO) throws DocumentException, FileNotFoundException, XMLDBException {
        zigService.kreirajIzvestaj(izvestajDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
