package project.xml.a1;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "naslov",
    "nacinKoriscenja",
    "vrsta",
    "formatZapisa",
    "izvornoDelo"
})
@XmlRootElement(name = "Autorsko_delo")
@Getter
@Setter
public class AutorskoDelo {

    @XmlElement(name = "Naslov", required = true)
    protected String naslov;
    @XmlElement(name = "Nacin_koriscenja", required = true)
    protected String nacinKoriscenja;
    @XmlElement(name = "Vrsta", required = true)
    protected TVrsta vrsta;
    @XmlElement(name = "Format_zapisa", required = true)
    protected TFormatZapisa formatZapisa;
    @XmlElement(name = "Izvorno_delo")
    protected IzvornoDelo izvornoDelo;
    @XmlAttribute(name = "Stvoreno_u_radnom_odnosu")
    protected Boolean stvorenoURadnomOdnosu;


}
