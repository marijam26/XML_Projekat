package project.xml.p1;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "datum",
    "broj"
})
@XmlRootElement(name = "Osnovna_prijava")
@Getter
@Setter
public class OsnovnaPrijava {

    @XmlElement(name = "Datum", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datum;
    @XmlElement(name = "Broj", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger broj;

}
