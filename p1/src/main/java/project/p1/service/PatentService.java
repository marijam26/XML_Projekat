package project.p1.service;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfDocument;
import org.apache.jena.rdf.model.RDFNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.xmldb.api.base.XMLDBException;
import project.p1.dto.*;
import project.p1.model.main_schema.*;
import project.p1.model.p1.OsnovnaPrijava;
import project.p1.model.p1.TNacinDostavljanja;
import project.p1.model.p1.TVrstaPunomocnika;
import project.p1.model.p1.ZahtevZaPatent;
import project.p1.model.resenje.Resenje;
import project.p1.repository.MetadataRepository;
import project.p1.repository.PatentRepository;
import project.p1.util.DatabaseUtilities;
import project.p1.util.MarshallingUtils;
import project.p1.util.PDFTransformer;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PatentService {

    @Autowired
    private PatentRepository patentRepository;



    public void save(ZahtevZaPatent zahtevZaPatent) throws JAXBException, XMLDBException {
        MarshallingUtils marshallingUtils = new MarshallingUtils();
        OutputStream os = marshallingUtils.marshall(zahtevZaPatent);
        patentRepository.save(os,zahtevZaPatent.getId());
    }

    public void save(Resenje resenje) throws JAXBException, XMLDBException {
        MarshallingUtils marshallingUtils = new MarshallingUtils();
        OutputStream os = marshallingUtils.marshall(resenje);
        patentRepository.saveResenje(os,resenje.getReferenca());
    }

    public ZahtevZaPatent getPatent(String id){
        return patentRepository.getPatentById(id);
    }

    public List<ZahtevZaPatent> getAllPatents(){
        return patentRepository.getAll();
    }

    public List<ZahtevZaPatent> getAllZahtevePatents() throws XMLDBException {
        List<ZahtevZaPatent> sviZahtevi = patentRepository.getAll();
        List<ZahtevZaPatent> zahtevi = new ArrayList<>();
        List<Resenje> svaResenja = patentRepository.getAllResenja();
        List<String> reference = svaResenja.stream().map(Resenje::getReferenca).collect(Collectors.toList());
        for (ZahtevZaPatent zahtev:sviZahtevi){
            if(!reference.contains(zahtev.getId())){
                zahtevi.add(zahtev);
            }
        }

        return zahtevi;
    }

    public List<ZahtevZaPatent> getAllOdobrenePatents() throws XMLDBException {
        List<ZahtevZaPatent> sviZahtevi = patentRepository.getAll();
        List<ZahtevZaPatent> zahtevi = new ArrayList<>();
        List<Resenje> svaResenja = patentRepository.getAllResenja();
        for (Resenje resenje:svaResenja){
            if(resenje.getOdobren()){
                for(ZahtevZaPatent z:sviZahtevi){
                    if(z.getId().equals(resenje.getReferenca())){
                        zahtevi.add(z);
                    }
                }
            }

        }
        return zahtevi;
    }

    public void saveMetadataForZahtev(ZahtevZaPatent zahtevZaPatent) throws JAXBException, IOException, TransformerException {
        MetadataRepository metadataRepository = new MetadataRepository();
        metadataRepository.extractMetadata(zahtevZaPatent);
    }


    public List<ZahtevZaPatent> search(String data) throws Exception {
        return patentRepository.search(data);
    }

    public List<ZahtevZaPatent> searchMetadata(String pred, String value) throws IOException {
        MetadataRepository metadataRepository = new MetadataRepository();
        String query = metadataRepository.getMetadataSimpleQuery(pred, value);
        return getByQuery(query);
    }

    public List<ZahtevZaPatent> searchMetadataAdvanced(MetadataSearchDTO data) throws IOException {
        MetadataRepository metadataRepository = new MetadataRepository();
        String query = metadataRepository.getMetadataAdvancedQuery(data.getPreds(), data.getValues(), data.getOperators());
        return getByQuery(query);
    }

    public List<ZahtevZaPatent> getByQuery(String query) throws IOException {
        MetadataRepository metadataRepository = new MetadataRepository();
        List<RDFNode> files =  metadataRepository.searchMetadata(query);
        List<ZahtevZaPatent> zahtevi = new ArrayList<>();
        for(RDFNode f: files){
            zahtevi.add(patentRepository.getPatentById(f.toString()));
        }
        return zahtevi;
    }


    public void getDocumentPdf(String id) throws DocumentException, IOException {
        PDFTransformer pdfTransformer = new PDFTransformer();
        String fileNamePDF = id + ".pdf";
        String fileNameHTML = id + ".html";

        Node patentNode = patentRepository.getPatentNode(id);

        pdfTransformer.generateHTML(fileNameHTML,patentNode);
        pdfTransformer.generatePDF(fileNamePDF,fileNameHTML);

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

    public ZahtevZaPatent map(ZahtevZaPatentDTO zahtevZaPatent) {
        ZahtevZaPatent zahtev = new ZahtevZaPatent();
        zahtev.setBrojPrijave(BigInteger.valueOf(DatabaseUtilities.getCollectionSize("db/patenti") + 1));
        zahtev.setId("P-"+ zahtev.getBrojPrijave()+"-"+ getDateXML(new Date()).getYear());
        zahtev.setDatumPrijema(getDateXML(new Date()));
        zahtev.setDatumPodnosenja(getDateXML(new Date()));
        zahtev.setVrstaPunomocnika(TVrstaPunomocnika.fromValue(zahtevZaPatent.vrstaPunomocnika));
        zahtev.setZajednickiPredstavnik(zahtevZaPatent.zajednickiPredstavnik);
        OsnovnaPrijava osnovnaPrijava = new OsnovnaPrijava();
        if(zahtevZaPatent.osnovnaPrijava.broj.equals(BigInteger.valueOf(-1))){
            osnovnaPrijava = null;
        }else{
            osnovnaPrijava.setBroj(zahtevZaPatent.osnovnaPrijava.broj);
            osnovnaPrijava.setDatum(formatDateToXML(zahtevZaPatent.osnovnaPrijava.datum));
        }
        zahtev.setOsnovnaPrijava(osnovnaPrijava);
        zahtev.setPodnosilacPrijave(mapTLice(zahtevZaPatent.podnosilacPrijave));
        zahtev.setPunomocnik(mapTLice(zahtevZaPatent.punomocnik));
        ZahtevZaPatent.Pronalazak pronalazak = new ZahtevZaPatent.Pronalazak();
        pronalazak.setEngleskiNaziv(zahtevZaPatent.pronalazak.engeskiNaziv);
        pronalazak.setSrpskiNaziv(zahtevZaPatent.pronalazak.srpskiNaziv);
        zahtev.setPronalazak(pronalazak);
        if (!zahtevZaPatent.pronalazac.ime.equals("")){
            zahtev.setPronalazac((TFizickoLice) mapTLice(zahtevZaPatent.pronalazac));
        }
        ZahtevZaPatent.PodaciODostavljanju podaciODostavljanju = new ZahtevZaPatent.PodaciODostavljanju();
        podaciODostavljanju.setAdresa(mapAdresa(zahtevZaPatent.podaciODostavljanju.adresa));
        podaciODostavljanju.setNacinDostavljanja(TNacinDostavljanja.fromValue(zahtevZaPatent.podaciODostavljanju.nacinDostavljanja));
        zahtev.setPodaciODostavljanju(podaciODostavljanju);
        if (zahtevZaPatent.ranijaPrijava != null){
            zahtev.setRanijaPrijava(mapRanijePrijave(zahtevZaPatent.ranijaPrijava));
        }
        return zahtev;
    }

    private List<ZahtevZaPatent.RanijaPrijava> mapRanijePrijave(RanijaPrijavaDTO[] ranijaPrijave) {
        List<ZahtevZaPatent.RanijaPrijava> lista = new ArrayList<>();
        for (RanijaPrijavaDTO r: ranijaPrijave) {
            ZahtevZaPatent.RanijaPrijava ranijaPrijava = new ZahtevZaPatent.RanijaPrijava();
            ranijaPrijava.setBroj(r.broj);
            ranijaPrijava.setDatum(formatDateToXML(r.datum));
            ranijaPrijava.setOznakaDrzave(r.oznakaDrzave);
            lista.add(ranijaPrijava);
        }
        return lista;
    }

    public XMLGregorianCalendar formatDateToXML(String datum){
        String[] datum_delovi = datum.split("-");
        GregorianCalendar gc = new GregorianCalendar(Integer.parseInt(datum_delovi[0]), Integer.parseInt(datum_delovi[1]) - 1,Integer.parseInt(datum_delovi[2]));
        return getDateXML(gc.getTime());
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

    public void kreirajIzvestaj(IzvestajDTO izvestajDTO) throws FileNotFoundException, DocumentException, XMLDBException {
        XMLGregorianCalendar pocetniDatum = formatDateToXML(izvestajDTO.pocetniDatum);
        XMLGregorianCalendar krajnjiDatum = formatDateToXML(izvestajDTO.krajnjiDatum);
        System.out.println(pocetniDatum);

        int zahtevi = 0;
        int prihvaceni = 0;
        int odbijeni = 0;

        List<Resenje> resenja = patentRepository.getAllResenja();
        List<ZahtevZaPatent> zahteviZaPatent = patentRepository.getAll();
        for (Resenje r: resenja) {
            if (pocetniDatum.compare(r.getDatumRazresenjaZahteva()) <= 0 && krajnjiDatum.compare(r.getDatumRazresenjaZahteva()) >= 0) {
                if(r.getOdobren()){
                    prihvaceni++;
                }else{
                    odbijeni++;
                }
            }
        }

        for (ZahtevZaPatent zahtevZaPatent:zahteviZaPatent){
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
