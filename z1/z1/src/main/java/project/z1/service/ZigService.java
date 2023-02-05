package project.z1.service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.xmldb.api.base.XMLDBException;
import project.z1.dto.*;
import project.z1.model.main_schema.*;
import project.z1.model.resenje.Resenje;
import project.z1.model.z1.*;
import project.z1.repository.MetadataRepository;
import project.z1.repository.ZigRepository;
import project.z1.util.DatabaseUtilities;
import project.z1.util.MarshallingUtils;
import project.z1.util.PDFTransformer;

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
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ZigService {

    @Autowired
    private ZigRepository zigRepository;

    @Autowired
    private MetadataRepository metadataRepository;

    public void save(ZahtevZaZig zahtevZaZig) throws JAXBException, XMLDBException, IOException, TransformerException {
        MarshallingUtils marshallingUtils = new MarshallingUtils();
        OutputStream os = marshallingUtils.marshall(zahtevZaZig);
        zigRepository.save(os, zahtevZaZig.getId());
        metadataRepository = new MetadataRepository();
        metadataRepository.extractMetadata(zahtevZaZig);
    }

    public void save(Resenje resenje) throws JAXBException, XMLDBException {
        MarshallingUtils marshallingUtils = new MarshallingUtils();
        OutputStream os = marshallingUtils.marshall(resenje);
        zigRepository.saveResenje(os,resenje.getReferenca());
    }


    public void getDocumentPdf(String id) throws DocumentException, IOException {
        PDFTransformer pdfTransformer = new PDFTransformer();
        String fileNamePDF = id + ".pdf";
        String fileNameHTML = id + ".html";
        Node zig = zigRepository.getZigNode(id);

        pdfTransformer.generateHTML(fileNameHTML, zig);
        pdfTransformer.generatePDF(fileNamePDF,fileNameHTML);

    }

    public ZahtevZaZig getZig(String id){
        return zigRepository.getById(id);
    }

    public ZahtevZaZig map(ZahtevZaZigDTO zahtevZaZig) {
        ZahtevZaZig zahtev = new ZahtevZaZig();
        zahtev.setBrojPrijave(BigInteger.valueOf(DatabaseUtilities.getCollectionSize("db/zigovi") + 1));
        zahtev.setId("Z-"+ zahtev.getBrojPrijave()+"-"+getDateXML(new Date()).getYear());
        zahtev.setDatumPodnosenja(getDateXML(new Date()));
        zahtev.setPodnosilacPrijave(getTLice(zahtevZaZig.podnosilacPrijave));
        zahtev.setPunomocnik(getTLice(zahtevZaZig.punomocnik));
        zahtev.setZig(getZigFromDTO(zahtevZaZig.zig));
        zahtev.setTakse(getTakse(zahtevZaZig.takse));
        zahtev.setPrilozi(getPrilozi(zahtevZaZig.prilozi));
        return zahtev;
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

    public XMLGregorianCalendar formatDateToXML(String datum){
        String[] datum_delovi = datum.split("-");
        GregorianCalendar gc = new GregorianCalendar(Integer.parseInt(datum_delovi[0]), Integer.parseInt(datum_delovi[1]),Integer.parseInt(datum_delovi[2]));
        return getDateXML(gc.getTime());
    }


    private Prilozi getPrilozi(ZigPrilozi priloziDTO) {
        Prilozi prilozi = new Prilozi();

        TPrilog primerak = new TPrilog();
        primerak.setValue(priloziDTO.primerakZnaka.value);
        primerak.setPutanja(priloziDTO.primerakZnaka.putanja);
        prilozi.setPrimerakZnaka(primerak);

        TPrilog spisakRobe = new TPrilog();
        primerak.setValue(priloziDTO.spisakRobe.value);
        primerak.setPutanja(priloziDTO.spisakRobe.putanja);
        prilozi.setSpisakRobe(spisakRobe);

        TPrilog punomoc = new TPrilog();
        primerak.setValue(priloziDTO.punomoc.value);
        primerak.setPutanja(priloziDTO.punomoc.putanja);
        prilozi.setPunomoc(punomoc);

        TPrilog ranijaPunomoc = new TPrilog();
        primerak.setValue(priloziDTO.ranijaPunomoc.value);
        primerak.setPutanja(priloziDTO.ranijaPunomoc.putanja);
        prilozi.setRanijaPunomoc(ranijaPunomoc);

        TPrilog naknadnaPunomoc = new TPrilog();
        primerak.setValue(priloziDTO.naknadnaPunomoc.value);
        primerak.setPutanja(priloziDTO.naknadnaPunomoc.putanja);
        prilozi.setNaknadnaPunomoc(naknadnaPunomoc);

        TPrilog opstiAkt = new TPrilog();
        primerak.setValue(priloziDTO.opstiAkt.value);
        primerak.setPutanja(priloziDTO.opstiAkt.putanja);
        prilozi.setOpstiAkt(opstiAkt);

        TPrilog dokazOPravuPrvenstva = new TPrilog();
        primerak.setValue(priloziDTO.dokazOPravuPrvenstva.value);
        primerak.setPutanja(priloziDTO.dokazOPravuPrvenstva.putanja);
        prilozi.setDokazOPravuPrvenstva(dokazOPravuPrvenstva);

        TPrilog dokazOUplati = new TPrilog();
        primerak.setValue(priloziDTO.dokazOUplati.value);
        primerak.setPutanja(priloziDTO.dokazOUplati.putanja);
        prilozi.setDokazOUplati(dokazOUplati);

        return prilozi;
    }

    private Takse getTakse(TakseDTO takseDTO) {
        Takse takse = new Takse();
        takse.setOsnovnaTaksa(BigInteger.valueOf(takseDTO.osnovnaTaksa));
        takse.setGrafickoResenje(BigInteger.valueOf(takseDTO.grafickoResenje));
        takse.setUkupno(BigInteger.valueOf(takseDTO.ukupno));
        takse.setKlase(BigInteger.valueOf(takseDTO.klase));
        takse.setBrojKlasa(BigInteger.valueOf(takseDTO.brojKlasa));
        return takse;
    }

    private Zig getZigFromDTO(ZigDTO zigDto) {
        Zig zig = new Zig();
        zig.setIzgledZnaka(zigDto.izgledZnaka);
        zig.setOpis(zigDto.opis);
        zig.setOstalo(zigDto.ostalo);
        zig.setTipZiga(TTipZiga.fromValue(zigDto.tipZiga.toLowerCase()));
        zig.setVrstaZnaka(TVrstaZnaka.fromValue(zigDto.vrstaZnaka.toLowerCase()));
        zig.setBoje(zigDto.boje);
        zig.setTransliteracija(zigDto.transliteracija);
        zig.setPrevod(zigDto.prevod);
        zig.setKlase(zigDto.klase);
        return zig;

    }

    private TLice getTLice(TLiceDTO podnosilac) {
        Adresa a = mapAdresa(podnosilac.adresa);
        Kontakt k = mapKontakt(podnosilac.kontakt);
        if (!podnosilac.poslovnoIme.equals("")){
            TPravnoLice t = new TPravnoLice();
            t.setPoslovnoIme(podnosilac.poslovnoIme);
            t.setAdresa(a);
            t.setKontakt(k);
            return t;
        }else{
            TFizickoLice t = new TFizickoLice();
            t.setIme(podnosilac.ime);
            t.setPrezime(podnosilac.prezime);
            t.setDrzavljanstvo(podnosilac.drzavljanstvo);
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

    public List<ZahtevZaZig> search(String data) throws Exception {
        return zigRepository.search(data);
    }

    public List<ZahtevZaZig> searchMetadata(String pred, String value) throws IOException {
        String query = metadataRepository.getMetadataSimpleQuery(pred, value);
        return getByQuery(query);
    }

    public List<ZahtevZaZig> searchMetadataAdvanced(MetadataSearchDTO data) throws IOException {
        metadataRepository = new MetadataRepository();
        String query = metadataRepository.getMetadataAdvancedQuery(data.getPreds(), data.getValues(), data.getOperators());
        System.out.println(query);
        return getByQuery(query);
    }

    public List<ZahtevZaZig> getByQuery(String query) throws IOException {
        List<String> files =  metadataRepository.searchMetadata(query);
        List<ZahtevZaZig> zahtevi = new ArrayList<>();
        for(String f: files){
            ZahtevZaZig zahtevZaZig = zigRepository.getById(f);
            zahtevi.add(zahtevZaZig);
        }
        return zahtevi;
    }

    public List<ZahtevZaZig> getAll() {
        return zigRepository.getAll();
    }

    public List<ZahtevZaZig> getAllZahteve() throws XMLDBException {
        List<ZahtevZaZig> sviZahtevi = zigRepository.getAll();
        List<ZahtevZaZig> zahtevi = new ArrayList<>();
        List<Resenje> svaResenja = zigRepository.getAllResenja();
        List<String> reference = svaResenja.stream().map(Resenje::getReferenca).collect(Collectors.toList());
        for (ZahtevZaZig zahtev: sviZahtevi){
            if(!reference.contains(zahtev.getId())){
                zahtevi.add(zahtev);
            }
        }

        return zahtevi;
    }

    public List<ZahtevZaZig> getAllOdobrene() throws XMLDBException {
        List<ZahtevZaZig> sviZahtevi = zigRepository.getAll();
        List<ZahtevZaZig> zahtevi = new ArrayList<>();
        List<Resenje> svaResenja = zigRepository.getAllResenja();
        for (Resenje resenje: svaResenja){
            if(resenje.getOdobren()){
                for(ZahtevZaZig z: sviZahtevi){
                    if(z.getId().equals(resenje.getReferenca())){
                        zahtevi.add(z);
                    }
                }
            }

        }
        return zahtevi;
    }

    public MetadataSearchDTO getDtoFromString(String data) {
        String[] tokens = data.split("---");
        System.out.println(tokens);
        if(!tokens[0].contains(",")){
            return new MetadataSearchDTO(Arrays.asList(tokens[1]), Arrays.asList(tokens[0]), Arrays.asList(tokens[2]));
        }
        String[] values = tokens[0].split(",");
        String[] preds = tokens[1].split(",");
        String[] operators = tokens[2].split(",");
        return new MetadataSearchDTO(Arrays.asList(preds), Arrays.asList(values), Arrays.asList(operators));
    }

    public void kreirajIzvestaj(IzvestajDTO izvestajDTO) throws FileNotFoundException, DocumentException, XMLDBException {
        XMLGregorianCalendar pocetniDatum = formatDateToXML(izvestajDTO.pocetniDatum);
        XMLGregorianCalendar krajnjiDatum = formatDateToXML(izvestajDTO.krajnjiDatum);
        System.out.println(pocetniDatum);

        int zahtevi = 0;
        int prihvaceni = 0;
        int odbijeni = 0;

        List<Resenje> resenja = zigRepository.getAllResenja();
        List<ZahtevZaZig> zahteviZaZig = zigRepository.getAll();
        for (Resenje r: resenja) {
            if (pocetniDatum.compare(r.getDatumRazresenjaZahteva()) <= 0 && krajnjiDatum.compare(r.getDatumRazresenjaZahteva()) >= 0) {
                if(r.getOdobren()){
                    prihvaceni++;
                }else{
                    odbijeni++;
                }
            }
        }

        for (ZahtevZaZig zahtevZaZig:zahteviZaZig){
            if(pocetniDatum.compare(zahtevZaZig.getDatumPodnosenja()) <= 0 && krajnjiDatum.compare(zahtevZaZig.getDatumPodnosenja()) >= 0){
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
