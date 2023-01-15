package project.z1.service;

import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;
import org.xmldb.api.base.XMLDBException;
import project.z1.dto.*;
import project.z1.model.main_schema.*;
import project.z1.model.z1.*;
import project.z1.repository.ZigRepository;
import project.z1.util.MarshallingUtils;
import project.z1.util.PDFTransformer;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

@Service
public class ZigService {

    @Autowired
    private ZigRepository zigRepository;

    public void save(ZahtevZaZig zahtevZaZig) throws JAXBException, XMLDBException {
        MarshallingUtils marshallingUtils = new MarshallingUtils();
        OutputStream os = marshallingUtils.marshall(zahtevZaZig);
        zigRepository.save(os);
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

    public ZahtevZaZig map(ZahtevZaZigDTO zahtevZaZigDTO) {
        ZahtevZaZig zahtevZaZig = new ZahtevZaZig();
        zahtevZaZig.setBrojPrijave(BigInteger.valueOf(1));  // ...
        zahtevZaZig.setDatumPodnosenja(getCurrentDate());
        zahtevZaZig.setPodnosilacPrijave(getTLice(zahtevZaZigDTO.podnosilac));
        zahtevZaZig.setPunomocnik(getTLice(zahtevZaZigDTO.punomocnik));
        zahtevZaZig.setZig(getZigFromDTO(zahtevZaZigDTO.zig));
        zahtevZaZig.setTakse(getTakse(zahtevZaZigDTO.takse));
        zahtevZaZig.setPrilozi(getPrilozi(zahtevZaZigDTO.prilozi));
        return zahtevZaZig;
    }

    private Prilozi getPrilozi(ZigPrilozi priloziDTO) {
        Prilozi prilozi = new Prilozi();

        TPrilog primerak = new TPrilog();
        primerak.setValue(priloziDTO.primerak.value);
        primerak.setPutanja(priloziDTO.primerak.putanja);
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
        Takse.Klase klaseTakse = new Takse.Klase();
        klaseTakse.setBrojKlasa(takseDTO.klaseTakse.brojKlasa);
        klaseTakse.setValue(BigInteger.valueOf(takseDTO.klaseTakse.value));
        takse.setKlase(klaseTakse);
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
        if (podnosilac.poslovnoIme != null && !podnosilac.poslovnoIme.equals("") && !podnosilac.poslovnoIme.equals("undefined")){
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
        k.setEPosta(kontakt.e_posta);
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

    public XMLGregorianCalendar getCurrentDate(){
        Date date = new Date();
        XMLGregorianCalendar xmlDate = null;
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);

        try {
            xmlDate = DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar(gc);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return xmlDate;
    }
}
