package project.a1.service;

import com.itextpdf.text.DocumentException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.xmldb.api.base.XMLDBException;
import project.a1.dto.a1.*;
import project.a1.dto.main_schema.AdresaDTO;
import project.a1.dto.main_schema.KontaktDTO;
import project.a1.dto.main_schema.TLiceDTO;
import project.a1.dto.main_schema.TPrilogDTO;
import project.a1.model.a1.*;
import project.a1.model.main_schema.*;
import project.a1.repository.AutorskoDeloRepository;
import project.a1.util.MarshallingUtils;
import project.a1.util.PDFTransformer;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;

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


    public ZahtevZaAutorskaDela getOne(String id) throws JAXBException, XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        return autorskoDeloRepository.getOne(id);
    }

    public ZahtevZaAutorskaDela map(ZahtevZaAutorskaDelaDTO dto) {
        ZahtevZaAutorskaDela delo = new ZahtevZaAutorskaDela();
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
        v.setValue(TVrsta.valueOf(autorskoDelo.vrsta.value));
        v.setOstalaVrstaDela(autorskoDelo.vrsta.ostalaVrstaDela);
        a.setVrsta(v);
        AutorskoDelo.FormatZapisa z = new AutorskoDelo.FormatZapisa();
        z.setValue(TFormatZapisa.valueOf(autorskoDelo.formatZapisa.value));
        z.setOstaliFormat(autorskoDelo.formatZapisa.ostaliFormat);
        a.setFormatZapisa(z);
        a.setIzvornoDelo(mapIzvorno(autorskoDelo.izvornoDelo));
        return a;
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
        String fileNamePDF = "p" + id + ".pdf";
        String fileNameHTML = "p" + id + ".html";

        Node patentNode = autorskoDeloRepository.getAutorskoPravoNode(id);

        pdfTransformer.generateHTML(fileNameHTML,patentNode);
        pdfTransformer.generatePDF(fileNamePDF,fileNameHTML);

    }
}
