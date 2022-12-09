package project.xml.a1;

import lombok.Getter;
import lombok.Setter;
import project.xml.main_schema.TLice;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "podnosilacPrijave",
    "punomocnik",
    "autorskoDelo",
    "prilozi",
    "autor"
})
@XmlRootElement(name = "Zahtev_za_autorska_dela")
@Getter
@Setter
public class ZahtevZaAutorskaDela {

    @XmlElement(name = "Podnosilac_prijave", namespace = "http://ftn.uns.ac.rs/sema", required = true)
    protected TLice podnosilacPrijave;
    @XmlElement(name = "Punomocnik", namespace = "http://ftn.uns.ac.rs/sema", required = true)
    protected TLice punomocnik;
    @XmlElement(name = "Autorsko_delo", required = true)
    protected AutorskoDelo autorskoDelo;
    @XmlElement(name = "Prilozi", required = true)
    protected Prilozi prilozi;
    @XmlElement(name = "Autor")
    protected TAutor autor;
    @XmlAttribute(name = "Datum_podnosenja")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumPodnosenja;
    @XmlAttribute(name = "Broj")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger broj;

}
