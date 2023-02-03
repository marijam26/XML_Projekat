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
import project.p1.dto.ListaZahtevaZaPatentDTO;
import project.p1.dto.ZahtevZaPatentDTO;
import project.p1.model.p1.ZahtevZaPatent;
import project.p1.service.PatentService;
import project.p1.util.MarshallingUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.io.*;
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

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ZahtevZaPatent> getOne(@PathVariable String id){
        ZahtevZaPatent zahtevZaPatent = patentService.getPatent(id);
        return new ResponseEntity<>(zahtevZaPatent, HttpStatus.OK);
    }

    @GetMapping(value = "/save1")
    public ResponseEntity<List<ZahtevZaPatent>> save() throws JAXBException, XMLDBException {
        MarshallingUtils marshallingUtils = new MarshallingUtils();
        ZahtevZaPatent z = marshallingUtils.unmarshall("src/main/resources/data/patent1.xml");
        System.out.println(z);
        patentService.save(z);
        return null;
    }


    @PostMapping(value = "/save", consumes = "application/xml")
    public void add(@RequestBody ZahtevZaPatentDTO zahtevZaPatentDTO){
        try {
            ZahtevZaPatent zahtev = patentService.map(zahtevZaPatentDTO);
            patentService.save(zahtev);
        } catch (Exception e) {
            // vrati bad request
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("/getPdf/{id}")
    public ResponseEntity<String> getPdf(@PathVariable String id) throws DocumentException, IOException {
        patentService.getDocumentPdf(id);
        return new ResponseEntity<>("Uspesno", HttpStatus.OK);
    }

    @GetMapping("/metadata/{id}")
    public ResponseEntity<String> saveMetadata(@PathVariable String id) throws JAXBException, IOException, TransformerException {
        patentService.saveMetadataForZahetv(id);
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
