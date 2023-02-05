package project.a1.service;

import com.itextpdf.text.DocumentException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorskoDeloService {

    @Autowired
    private AutorskoDeloRepository autorskoDeloRepository;

    @Autowired
    private ModelMapper modelMapper;

    public void save(ZahtevZaAutorskaDela zahtevZaAutorskaDela) throws XMLDBException, JAXBException {
        MarshallingUtils marshallingUtils = new MarshallingUtils();
        OutputStream os = marshallingUtils.marshall(zahtevZaAutorskaDela);
        autorskoDeloRepository.save(os);
    }

    public void save(Resenje resenje) throws JAXBException, XMLDBException {
        MarshallingUtils marshallingUtils = new MarshallingUtils();
        OutputStream os = marshallingUtils.marshall(resenje);
        autorskoDeloRepository.saveResenje(os,resenje.getReferenca(),resenje.getSifraZahteva());
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
        String fileNamePDF = "a" + id + ".pdf";
        String fileNameHTML = "a" + id + ".html";

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
}
