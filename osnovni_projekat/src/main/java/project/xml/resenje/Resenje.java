package project.xml.resenje;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "datumRazresenjaZahteva",
    "obrazlozenje",
    "sifraDela",
    "imeAutora",
    "prezimeAutora",
    "referenca"
})
@XmlRootElement(name = "Resenje")
public class Resenje {

    @XmlElement(name = "Datum_razresenja_zahteva", required = true)
    protected XMLGregorianCalendar datumRazresenjaZahteva;
    @XmlElement(name = "Obrazlozenje", required = true)
    protected String obrazlozenje;
    @XmlElement(name = "Sifra_dela", required = true)
    protected String sifraDela;
    @XmlElement(name = "Ime_autora", required = true)
    protected String imeAutora;
    @XmlElement(name = "Prezime_autora", required = true)
    protected String prezimeAutora;
    @XmlElement(name = "Referenca", required = true)
    protected String referenca;
}