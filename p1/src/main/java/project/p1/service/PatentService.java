package project.p1.service;

import com.itextpdf.text.DocumentException;
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
import project.p1.repository.MetadataRepository;
import project.p1.repository.PatentRepository;
import project.p1.util.MarshallingUtils;
import project.p1.util.PDFTransformer;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Service
public class PatentService {

    @Autowired
    private PatentRepository patentRepository;


    public void save(ZahtevZaPatent zahtevZaPatent) throws JAXBException, XMLDBException {
        MarshallingUtils marshallingUtils = new MarshallingUtils();
        OutputStream os = marshallingUtils.marshall(zahtevZaPatent);
        patentRepository.save(os);
    }

    public ZahtevZaPatent getPatent(String id){
        return patentRepository.getPatentById(id);
    }

    public List<ZahtevZaPatent> getAllPatents(){
        return patentRepository.getAll();
    }

    public void saveMetadataForZahetv(String id) throws JAXBException, IOException, TransformerException {
        ZahtevZaPatent zahtevZaPatent = getPatent(id);
        MetadataRepository repo = new MetadataRepository();
        repo.extractMetadata(zahtevZaPatent);
    }

    public void getDocumentPdf(String id) throws DocumentException, IOException {
        PDFTransformer pdfTransformer = new PDFTransformer();
        String fileNamePDF = id + ".pdf";
        String fileNameHTML = id + ".html";

        Node patentNode = patentRepository.getPatentNode(id);

        pdfTransformer.generateHTML(fileNameHTML,patentNode);
        pdfTransformer.generatePDF(fileNamePDF,fileNameHTML);

    }

    public ZahtevZaPatent map(ZahtevZaPatentDTO zahtevZaPatent) {
        ZahtevZaPatent zahtev = new ZahtevZaPatent();
        zahtev.setBrojPrijave(zahtevZaPatent.brojPrijave);
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
        GregorianCalendar gc = new GregorianCalendar(Integer.parseInt(datum_delovi[0]), Integer.parseInt(datum_delovi[1]),Integer.parseInt(datum_delovi[2]));
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
}
