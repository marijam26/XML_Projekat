package project.p1.model.p1;

import lombok.Getter;
import lombok.Setter;
import project.p1.model.main_schema.Adresa;
import project.p1.model.main_schema.TFizickoLice;
import project.p1.model.main_schema.TLice;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "osnovnaPrijava",
    "podnosilacPrijave",
    "pronalazak",
    "punomocnik",
    "podaciODostavljanju",
    "ranijaPrijava",
    "pronalazac"
})
@XmlRootElement(name = "Zahtev_za_patent")
@Getter
@Setter
public class ZahtevZaPatent {

    @XmlElement(name = "Osnovna_prijava")
    protected OsnovnaPrijava osnovnaPrijava;
    @XmlElement(name = "Podnosilac_prijave", namespace = "http://ftn.uns.ac.rs/sema", required = true)
    protected TLice podnosilacPrijave;
    @XmlElement(name = "Pronalazak", required = true)
    protected Pronalazak pronalazak;
    @XmlElement(name = "Punomocnik", namespace = "http://ftn.uns.ac.rs/sema", required = true)
    protected TLice punomocnik;
    @XmlElement(name = "Podaci_o_dostavljanju", required = true)
    protected PodaciODostavljanju podaciODostavljanju;
    @XmlElement(name = "Ranija_prijava")
    protected List<RanijaPrijava> ranijaPrijava;
    @XmlElement(name = "Pronalazac")
    protected TFizickoLice pronalazac;
    @XmlAttribute(name = "Id")
    @XmlSchemaType(name = "positiveInteger")
    protected String id;
    @XmlAttribute(name = "Broj_prijave")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger brojPrijave;
    @XmlAttribute(name = "Datum_prijema")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumPrijema;
    @XmlAttribute(name = "Datum_podnosenja")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumPodnosenja;
    @XmlAttribute(name = "Vrsta_punomocnika")
    protected TVrstaPunomocnika vrstaPunomocnika;
    @XmlAttribute(name = "Zajednicki_predstavnik")
    protected Boolean zajednickiPredstavnik;


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "adresa",
        "nacinDostavljanja"
    })
    @Getter
    @Setter
    public static class PodaciODostavljanju {

        @XmlElement(name = "Adresa", namespace = "http://ftn.uns.ac.rs/sema", required = true)
        protected Adresa adresa;
        @XmlElement(name = "Nacin_dostavljanja", required = true)
        protected TNacinDostavljanja nacinDostavljanja;

    }



    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "srpskiNaziv",
        "engleskiNaziv"
    })
    @Getter
    @Setter
    public static class Pronalazak {

        @XmlElement(name = "Srpski_naziv", required = true)
        protected String srpskiNaziv;
        @XmlElement(name = "Engleski_naziv", required = true)
        protected String engleskiNaziv;

    }


    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "broj",
        "datum",
        "oznakaDrzave"
    })
    @Getter
    @Setter
    public static class RanijaPrijava {

        @XmlElement(name = "Broj", required = true)
        @XmlSchemaType(name = "positiveInteger")
        protected BigInteger broj;
        @XmlElement(name = "Datum", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar datum;
        @XmlElement(name = "Oznaka_drzave", required = true)
        protected String oznakaDrzave;

    }

}
