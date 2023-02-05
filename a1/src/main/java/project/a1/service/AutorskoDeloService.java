package project.a1.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.jena.rdf.model.RDFNode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.xmldb.api.base.XMLDBException;
import project.a1.dto.a1.*;
import project.a1.dto.main_schema.*;
import project.a1.model.a1.*;
import project.a1.model.main_schema.*;
import project.a1.model.resenje.Resenje;
import project.a1.repository.AutorskoDeloRepository;
import project.a1.repository.MetadataRepository;
import project.a1.util.DatabaseUtilities;
import project.a1.util.MarshallingUtils;
import project.a1.util.PDFTransformer;

import javax.mail.MessagingException;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.TransformerException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AutorskoDeloService {

    @Autowired
    private AutorskoDeloRepository autorskoDeloRepository;

    @Autowired
    private ModelMapper modelMapper;

    private MetadataRepository metadataRepository;

    @Autowired
    private Environment env;

    @Bean
    public JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("rentalappteam6@gmail.com");
        mailSender.setPassword("qxoizxexuemjfgow");
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        mailSender.setJavaMailProperties(props);
        return mailSender;
    }

    JavaMailSenderImpl javaMailSender = mailSender();


    public void save(ZahtevZaAutorskaDela zahtevZaAutorskaDela) throws XMLDBException, JAXBException {
        MarshallingUtils marshallingUtils = new MarshallingUtils();
        OutputStream os = marshallingUtils.marshall(zahtevZaAutorskaDela);
        autorskoDeloRepository.save(os,zahtevZaAutorskaDela.getId());
    }

    public void save(Resenje resenje) throws JAXBException, XMLDBException, DocumentException, IOException, MessagingException {
        MarshallingUtils marshallingUtils = new MarshallingUtils();
        OutputStream os = marshallingUtils.marshall(resenje);
        autorskoDeloRepository.saveResenje(os,resenje.getReferenca(),resenje.getSifraZahteva());
        sendToPodnosilac(resenje);
    }

    public void testSaveResenje() throws DocumentException, IOException {
        Resenje resenje = new Resenje();
        resenje.setObrazlozenje("obr");
        resenje.setOdobren(true);
        resenje.setImeSluzbenika("Pera");
        resenje.setPrezimeSluzbenika("Pera");
        PDFTransformer pdfTransformer = new PDFTransformer();
        Node node = autorskoDeloRepository.getResenjeNode("A-10-2023");
        pdfTransformer.generatePdfResenje(resenje,node);
    }

    public void generatePdfTask(Resenje resenje) throws DocumentException, IOException {
        PDFTransformer pdfTransformer = new PDFTransformer();
        Node node = autorskoDeloRepository.getResenjeNode(resenje.getReferenca());
        pdfTransformer.generatePdfResenje(resenje,node);
    }


    private void sendToPodnosilac(Resenje resenje) throws DocumentException, IOException, MessagingException {
        generatePdfTask(resenje);
        sendMail(resenje.getReferenca() );
    }

    public ZahtevZaAutorskaDela getById(String id) throws JAXBException, XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        return DatabaseUtilities.getZahtevById(id);
    }


    public ZahtevZaAutorskaDela getOne(String id) throws JAXBException, XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        return autorskoDeloRepository.getOne(id);
    }

    public Resenje map(ResenjeDTO resenjeDTO){
        Resenje resenje = new Resenje();
        resenje.setImeSluzbenika(resenjeDTO.imeSluzbenika);
        resenje.setPrezimeSluzbenika(resenjeDTO.prezimeSluzbenika);
        resenje.setOdobren(resenjeDTO.odobren);
        resenje.setReferenca(resenjeDTO.referenca);
        resenje.setObrazlozenje(resenjeDTO.obrazlozenje);
        resenje.setSifraZahteva(resenjeDTO.referenca);
        resenje.setDatumRazresenjaZahteva(getDateXML(new Date()));
        return resenje;
    }


    private XMLGregorianCalendar getDateXML(Date datum) {
        XMLGregorianCalendar xmlDate = null;
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(datum);

        try {
            xmlDate = DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar(gc);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return xmlDate;
    }

    public ZahtevZaAutorskaDela map(ZahtevZaAutorskaDelaDTO dto) {
        ZahtevZaAutorskaDela delo = new ZahtevZaAutorskaDela();
        delo.setId("A-"+ (DatabaseUtilities.getCollectionSize("db/autorskaDela")+1)+"-"+ LocalDateTime.now().getYear());
        delo.setpodnosilacJeAutor(dto.podnosilacJeAutor);
        delo.setPodnosilacPrijave(mapTLice(dto.podnosilacPrijave));
        delo.setAutorskoDelo(mapDelo(dto.autorskoDelo));
        delo.setAutor(mapAutor(dto.autor));
        delo.setPrilozi(mapPrilozi(dto.prilozi));
        delo.setPunomocnik(mapTLice(dto.punomocnik));
        delo.setDatumPodnosenja(getCurrentDateXML());
        return delo;
    }

    private Prilozi mapPrilozi(PriloziDTO prilozi) {
        Prilozi p = new Prilozi();
        p.setOpisDela(mapPrilog(prilozi.opisDela));
        p.setPrimerDela(mapPrilog(prilozi.primerDela));
        return p;
    }

    private TPrilog mapPrilog(TPrilogDTO dto){
        TPrilog d = new TPrilog();
        d.setValue(dto.value);
        d.setPutanja(dto.putanja);
        return d;
    }

    private AutorskoDelo mapDelo(AutorskoDeloDTO autorskoDelo) {
        AutorskoDelo a = new AutorskoDelo();
        a.setNaslov(autorskoDelo.naslov);
        a.setNacinKoriscenja(autorskoDelo.nacinKoriscenja);
        a.setPrerada(autorskoDelo.prerada);
        a.setStvorenoURadnomOdnosu(autorskoDelo.stvorenoURadnomOdnosu);
        AutorskoDelo.Vrsta v = new AutorskoDelo.Vrsta();
        v.setValue(mapVrstaDela(autorskoDelo.vrsta.value));
        v.setOstalaVrstaDela(autorskoDelo.vrsta.ostalaVrstaDela);
        a.setVrsta(v);
        AutorskoDelo.FormatZapisa z = new AutorskoDelo.FormatZapisa();
        z.setValue(TFormatZapisa.valueOf(autorskoDelo.formatZapisa.value.toUpperCase()));
        z.setOstaliFormat(autorskoDelo.formatZapisa.ostaliFormat);
        a.setFormatZapisa(z);
        a.setIzvornoDelo(mapIzvorno(autorskoDelo.izvornoDelo));
        return a;
    }

    private TVrsta mapVrstaDela(String value) {
        if (value.equalsIgnoreCase("knjizevno_delo")){
            return TVrsta.KNJIZEVNO_DELO;
        }
        else if(value.equalsIgnoreCase("muzicko_delo")){
            return TVrsta.MUZICKO_DELO;
        }
        else if(value.equalsIgnoreCase("likovno_delo")){
            return TVrsta.LIKOVNO_DELO;
        }else if (value.equalsIgnoreCase("racunarski_program")){
            return TVrsta.RACUNARSKI_PROGRAM;
        }else{
            return TVrsta.OSTALO;
        }
    }

    private IzvornoDelo mapIzvorno(IzvornoDeloDTO izvornoDelo) {
        IzvornoDelo d = new IzvornoDelo();
        d.setNaslov(izvornoDelo.naslov);
        d.setAnonimanAutor(izvornoDelo.anonimanAutor);
        if (!izvornoDelo.anonimanAutor){
            d.setAutor(mapAutor(izvornoDelo.autor));
        }
        return d;
    }

    private TAutor mapAutor(TAutorDTO autor) {
        Adresa a = mapAdresa(autor.adresa);
        if (autor.godinaSmrti == null || autor.godinaSmrti == 0  ){
            TZivAutor t = new TZivAutor();
            t.setIme(autor.ime);
            t.setPrezime(autor.prezime);
            t.setDrzavljanstvo(autor.drzavljanstvo);
            t.setPseudonim(autor.pseudonim);
            t.setAdresa(a);
            return t;
        }else {
            TPreminuliAutor t = new TPreminuliAutor();
            t.setIme(autor.ime);
            t.setPrezime(autor.prezime);
            t.setGodinaSmrti(BigInteger.valueOf(autor.godinaSmrti));
            return t;
        }
    }

    private TLice mapTLice(TLiceDTO podnosilacPrijave) {
        Adresa a = mapAdresa(podnosilacPrijave.adresa);
        Kontakt k = mapKontakt(podnosilacPrijave.kontakt);
        if (podnosilacPrijave.poslovnoIme != null && !podnosilacPrijave.poslovnoIme.equals("") && !podnosilacPrijave.poslovnoIme.equals("undefined")){
            TPravnoLice t = new TPravnoLice();
            t.setPoslovnoIme(podnosilacPrijave.poslovnoIme);
            t.setAdresa(a);
            t.setKontakt(k);
            return t;
        }else{
            TFizickoLice t = new TFizickoLice();
            t.setIme(podnosilacPrijave.ime);
            t.setPrezime(podnosilacPrijave.prezime);
            t.setDrzavljanstvo(podnosilacPrijave.drzavljanstvo);
            t.setAdresa(a);
            t.setKontakt(k);
            return t;
        }
    }

    private Kontakt mapKontakt(KontaktDTO kontakt) {
        Kontakt k = new Kontakt();
        k.setEPosta(kontakt.eposta);
        k.setFaks(kontakt.faks);
        k.setTelefon(kontakt.telefon);
        return k;
    }

    private Adresa mapAdresa(AdresaDTO dto){
        Adresa a = new Adresa();
        a.setBroj(dto.broj);
        a.setDrzava(dto.drzava);
        a.setGrad(dto.grad);
        a.setUlica(dto.ulica);
        a.setPostanskiBroj(dto.postanskiBroj);
        return a;
    }

    private XMLGregorianCalendar getCurrentDateXML() {
        Date current_date = new Date();
        XMLGregorianCalendar xmlDate = null;
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(current_date);

        try {
            xmlDate = DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar(gc);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return xmlDate;
    }

    public void getDocumentPdf(String id) throws DocumentException, IOException {
        PDFTransformer pdfTransformer = new PDFTransformer();
        String fileNamePDF = id + ".pdf";
        String fileNameHTML = id + ".html";

        Node patentNode = autorskoDeloRepository.getAutorskoPravoNode(id);

        pdfTransformer.generateHTML(fileNameHTML,patentNode);
        pdfTransformer.generatePDF(fileNamePDF,fileNameHTML);

    }

    public List<ZahtevZaAutorskaDela> search(String data) throws Exception {
        return autorskoDeloRepository.search(data);
    }

    public List<ZahtevZaAutorskaDela> getAll() {
        return autorskoDeloRepository.getAll();
    }

    public List<ZahtevZaAutorskaDela> getAllRequests() throws XMLDBException {
        List<ZahtevZaAutorskaDela> sviZahtevi = autorskoDeloRepository.getAll();
        List<ZahtevZaAutorskaDela> zahtevi = new ArrayList<>();
        List<Resenje> svaResenja = autorskoDeloRepository.getAllResenja();
        List<String> reference = svaResenja.stream().map(Resenje::getReferenca).collect(Collectors.toList());
        for (ZahtevZaAutorskaDela zahtev:sviZahtevi){
            if(!reference.contains(zahtev.getId())){
                zahtevi.add(zahtev);
            }
        }

        return zahtevi;
    }

    public List<ZahtevZaAutorskaDela> getAllApprovedRequests() throws XMLDBException {
        List<ZahtevZaAutorskaDela> sviZahtevi = autorskoDeloRepository.getAll();
        List<ZahtevZaAutorskaDela> zahtevi = new ArrayList<>();
        List<Resenje> svaResenja = autorskoDeloRepository.getAllResenja();
        for (Resenje resenje:svaResenja){
            if(resenje.getOdobren()){
                for(ZahtevZaAutorskaDela z:sviZahtevi){
                    if(z.getId().equals(resenje.getReferenca())){
                        zahtevi.add(z);
                    }
                }
            }

        }
        return zahtevi;
    }

    public void saveMetadataForZahtev(String id) throws JAXBException, XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, TransformerException {
        ZahtevZaAutorskaDela zahtevZaPatent = getOne(id);
        MetadataRepository repo = new MetadataRepository();
        repo.extractMetadata(zahtevZaPatent);
    }

    public List<ZahtevZaAutorskaDela> searchMetadata(String pred, String value) throws IOException, JAXBException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String query = metadataRepository.getMetadataSimpleQuery(pred, value);
        return getByQuery(query);
    }

    public List<ZahtevZaAutorskaDela> searchMetadataAdvanced(MetadataSearchDTO data) throws IOException, JAXBException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String query = metadataRepository.getMetadataAdvancedQuery(data.getPreds(), data.getValues(), data.getOperators());
        return getByQuery(query);
    }

    public List<ZahtevZaAutorskaDela> getByQuery(String query) throws IOException, JAXBException, XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        List<RDFNode> files =  metadataRepository.searchMetadata(query);
        List<ZahtevZaAutorskaDela> zahtevi = new ArrayList<>();
        for(RDFNode f: files){
            zahtevi.add(autorskoDeloRepository.getOne(f.toString()));
        }
        return zahtevi;
    }

    public void sendMail(String docId) throws MessagingException {
            System.out.print("salje");
            MimeMessageHelper mail = new MimeMessageHelper(javaMailSender.createMimeMessage(), true, "UTF-8");
            mail.setTo("ikasikovic@yahoo.com");
            mail.setFrom(Objects.requireNonNull(env.getProperty("spring.mail.username")));
            mail.setSubject("Uber account password forgotten");

            String link = "http://localhost:4200/downloadResenje/" + docId;
            mail.setText("<html>\n" +
                    "    <body>\n" +
                    "        <div style=\"margin: 50px;\">\n" +
                    "            <div style=\"background-color: rgb(99, 216, 99);height: 55px;\">\n" +
                    "                    <h1 style=\"margin-left:15px; color: white;\">Change your password</h1>\n" +
                    "            </div>\n" +
                    "            <div style=\"margin-top: 10px;\">\n" +
                    "                <div style=\"margin: 25px;\">\n" +
                    "                Postovani ,\n" +
                    "                <br/>\n" +
                    "                Kliknite da preuzmete vase resenje: \n" +
                    "                <br/>\n" +
                    "                "+ link + " \n" +
                    "                <br/>\n" +
                    "                Pozdrav,\n" +
                    "                <br/>\n" +
                    "                <span >Zavod</span>\n" +
                    "            </div>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "\n" +
                    "    </body>\n" +
                    "</html>",true);
            javaMailSender.send(mail.getMimeMessage());

    }


    public XMLGregorianCalendar formatDateToXML(String datum){
        String[] datum_delovi = datum.split("-");
        GregorianCalendar gc = new GregorianCalendar(Integer.parseInt(datum_delovi[0]), Integer.parseInt(datum_delovi[1]) - 1,Integer.parseInt(datum_delovi[2]));
        return getDateXML(gc.getTime());
    }

    public void kreirajIzvestaj(IzvestajDTO izvestajDTO) throws FileNotFoundException, DocumentException, XMLDBException {
        XMLGregorianCalendar pocetniDatum = formatDateToXML(izvestajDTO.pocetniDatum);
        XMLGregorianCalendar krajnjiDatum = formatDateToXML(izvestajDTO.krajnjiDatum);
        System.out.println(pocetniDatum);

        int zahtevi = 0;
        int prihvaceni = 0;
        int odbijeni = 0;

        List<Resenje> resenja = autorskoDeloRepository.getAllResenja();
        List<ZahtevZaAutorskaDela> zahteviZaPatent = autorskoDeloRepository.getAll();
        for (Resenje r: resenja) {
            if (pocetniDatum.compare(r.getDatumRazresenjaZahteva()) <= 0 && krajnjiDatum.compare(r.getDatumRazresenjaZahteva()) >= 0) {
                if(r.getOdobren()){
                    prihvaceni++;
                }else{
                    odbijeni++;
                }
            }
        }

        for (ZahtevZaAutorskaDela zahtevZaPatent:zahteviZaPatent){
            if(pocetniDatum.compare(zahtevZaPatent.getDatumPodnosenja()) <= 0 && krajnjiDatum.compare(zahtevZaPatent.getDatumPodnosenja()) >= 0){
                zahtevi++;
            }
        }


        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("src/main/resources/data/gen/izvestaj.pdf"));
        document.open();
        Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 20, BaseColor.BLACK);
        Chunk chunk = new Chunk("IZVESTAJ U PERIODU OD " + convertDateToStr(pocetniDatum) + " DO " + convertDateToStr(krajnjiDatum), font);
        document.add(chunk);
        document.add(new Paragraph("\n\n"));

        PdfPTable table = new PdfPTable(3);
        addTableHeader(table);
        table.addCell(String.valueOf(zahtevi));
        table.addCell(String.valueOf(prihvaceni));
        table.addCell(String.valueOf(odbijeni));
        document.add(table);
        document.close();

    }


    private void addTableHeader(PdfPTable table) {
        Stream.of("Podneti zahtevi", "Prihvaceni zahtevi", "Odbijeni zahtevi")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.YELLOW);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private String convertDateToStr(XMLGregorianCalendar calendar) {
        Date date = calendar.toGregorianCalendar().getTime();
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy.");
        return df.format(date);
    }

}
