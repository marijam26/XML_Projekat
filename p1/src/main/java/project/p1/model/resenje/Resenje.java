package project.p1.model.resenje;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "datumRazresenjaZahteva",
    "obrazlozenje",
    "sifraZahteva",
    "imeSluzbenika",
    "prezimeSluzbenika",
    "referenca",
    "odobren"
})
@XmlRootElement(name = "Resenje")
@Getter
@Setter
public class Resenje {

    @XmlElement(name = "Datum_razresenja_zahteva", required = true)
    protected XMLGregorianCalendar datumRazresenjaZahteva;
    @XmlElement(name = "Obrazlozenje", required = false)
    protected String obrazlozenje;
    @XmlElement(name = "Sifra_zahteva", required = true)
    protected String sifraZahteva;
    @XmlElement(name = "Ime_sluzbenika", required = true)
    protected String imeSluzbenika;
    @XmlElement(name = "Prezime_sluzbenika", required = true)
    protected String prezimeSluzbenika;
    @XmlElement(name = "Referenca", required = true)
    protected String referenca;
    @XmlElement(name = "Odobren", required = true)
    protected Boolean odobren;

}