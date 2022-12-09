package project.xml.main_schema;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ulica",
    "broj",
    "grad",
    "postanskiBroj",
    "drzava"
})
@XmlRootElement(name = "Adresa")
@Getter
@Setter
public class Adresa {

    @XmlElement(name = "Ulica", required = true)
    protected String ulica;
    @XmlElement(name = "Broj", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger broj;
    @XmlElement(name = "Grad", required = true)
    protected String grad;
    @XmlElement(name = "Postanski_broj", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger postanskiBroj;
    @XmlElement(name = "Drzava")
    protected String drzava;



}
