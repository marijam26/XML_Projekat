package project.a1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.xmldb.api.base.XMLDBException;
import project.a1.dto.a1.ListaZahtevaZaAutorskoDeloDTO;
import project.a1.dto.a1.ZahtevZaAutorskaDelaDTO;
import project.a1.dto.main_schema.ImageDTO;
import project.a1.dto.main_schema.MetadataSearchDTO;
import project.a1.dto.main_schema.ResenjeDTO;
import project.a1.model.a1.Prilozi;
import project.a1.model.a1.ZahtevZaAutorskaDela;
import project.a1.model.main_schema.TPrilog;
import project.a1.model.resenje.Resenje;
import project.a1.repository.MetadataRepository;
import project.a1.service.AutorskoDeloService;
import project.a1.util.MarshallingUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
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
    public ZahtevZaAutorskaDela add(@RequestBody ZahtevZaAutorskaDelaDTO dto){
        try {
            ZahtevZaAutorskaDela zahtevZaAutorskaDela = autorskoDeloService.map(dto);
            autorskoDeloService.save(zahtevZaAutorskaDela);
            MetadataRepository repo = new MetadataRepository();
            repo.extractMetadata(zahtevZaAutorskaDela);
            autorskoDeloService.getDocumentPdf(zahtevZaAutorskaDela.getId());//zahtevZaAutorskaDela.getId().split("-")[1],zahtevZaAutorskaDela.getId());
            return zahtevZaAutorskaDela;
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        return null;
    }

//    @GetMapping("/getPdf/{id}")
//    public ResponseEntity<String> getPdf(@PathVariable String id) throws DocumentException, IOException {
//        autorskoDeloService.getDocumentPdf(id);
//        return new ResponseEntity<>("Uspesno", HttpStatus.OK);
//    }

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

    @GetMapping(value = "/test")
    public void testt(){
        try {
            autorskoDeloService.testSaveResenje();
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

    @RequestMapping("/downloadRDF/{fileName}")
    public void downloadRDFResource(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileName") String fileName) throws IOException {
        String path = "src/main/resources/data/rdf/" + fileName;
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

    @PostMapping(value = "/searchMetadata/advanced", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<ZahtevZaAutorskaDela>> searchMetadataAdvanced(@RequestBody MetadataSearchDTO data) throws Exception {
        List<ZahtevZaAutorskaDela> zahtevi = autorskoDeloService.searchMetadataAdvanced(data);
        return new ResponseEntity<>(zahtevi, HttpStatus.OK);
    }


    @GetMapping(value = "/searchMetadata/{pred}/{value}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<ZahtevZaAutorskaDela>> searchMetadata(@PathVariable String pred, @PathVariable String value) throws Exception {
        List<ZahtevZaAutorskaDela> zahtevi = autorskoDeloService.searchMetadata(pred, value);
        return new ResponseEntity<>(zahtevi, HttpStatus.OK);
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

    @PostMapping(value="/addImage")
    public ResponseEntity<InputStreamResource> addImage(@RequestBody ImageDTO imageDTO) throws IOException {
        byte[] data;
        try {
            data = Base64.getDecoder().decode(imageDTO.getData().split(",")[1]);
        } catch(Exception e) {
            return null;
        }
        Path path = Paths.get("src/main/resources/data/files/"+imageDTO.getId());
        Files.createDirectories(path);

        String imageName = imageDTO.getPath();
        String picturePath = "src\\main\\resources\\data\\files\\"+imageDTO.getId()+"\\"+imageName;
        try (OutputStream stream = new FileOutputStream(new File(picturePath).getCanonicalFile())) {
            stream.write(data);
            FileSystemResource imgFile = new FileSystemResource("src/main/resources/data/files/"+imageDTO.getId()+"/" + imageName);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(new InputStreamResource(imgFile.getInputStream()));
        }



    }

    @RequestMapping("/downloadPrimer/{fileName}")
    public void download(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileName") String fileName) throws IOException {
        String path = "src/main/resources/data/files/" + fileName.split(":")[0]+"/"+fileName.split(":")[1];
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

    @RequestMapping("/downloadResenje/{fileName}")
    public void downloadResenje(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileName") String fileName) throws IOException {
        String path = "src/main/resources/data/resenje/" + fileName.split(":")[0]+"/"+fileName.split(":")[1];
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

    @RequestMapping("/downloadOpis/{fileName}")
    public void downloadOpis(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileName") String fileName) throws IOException {
        String path = "src/main/resources/data/files/" + fileName.split(":")[0]+"/"+fileName.split(":")[1];
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


}
