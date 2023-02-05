package project.z1.controller;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import project.z1.dto.MetadataSearchDTO;
import project.z1.dto.ResenjeDTO;
import project.z1.dto.ZahtevZaZigDTO;
import project.z1.model.resenje.Resenje;
import project.z1.model.z1.ZahtevZaZig;
import project.z1.service.ZigService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
@RequestMapping(value = "api/zig")
public class ZigController {

    @Autowired
    private ZigService zigService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<ZahtevZaZig>> getAll(){
        List<ZahtevZaZig> zahtevi = zigService.getAll();
        return new ResponseEntity<>(zahtevi, HttpStatus.OK);
    }

    @GetMapping(value = "/zahtevi", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<ZahtevZaZig>> getAllZahteve(){
        List<ZahtevZaZig> zahtevi = zigService.getAllZahteve();
        return new ResponseEntity<>(zahtevi, HttpStatus.OK);
    }

    @GetMapping(value = "/odobreni", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<ZahtevZaZig>> getAllOdobrene(){
        List<ZahtevZaZig> zahtevi = zigService.getAllOdobrene();
        return new ResponseEntity<>(zahtevi, HttpStatus.OK);
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
    public ResponseEntity<List<ZahtevZaZig>> search(@PathVariable String data) throws Exception {
        List<ZahtevZaZig> zahtevi = zigService.search(data);
        return new ResponseEntity<>(zahtevi, HttpStatus.OK);
    }

    @GetMapping(value = "/searchMetadata/{pred}/{value}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<ZahtevZaZig>> searchMetadata(@PathVariable String pred, @PathVariable String value) throws Exception {
        List<ZahtevZaZig> zahtevi = zigService.searchMetadata(pred, value);
        return new ResponseEntity<>(zahtevi, HttpStatus.OK);
    }


    @PostMapping(value = "/searchMetadata/advanced", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<ZahtevZaZig>> searchMetadataAdvanced(@RequestBody MetadataSearchDTO data) throws Exception {
        List<ZahtevZaZig> zahtevi = zigService.searchMetadataAdvanced(data);
        return new ResponseEntity<>(zahtevi, HttpStatus.OK);
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
