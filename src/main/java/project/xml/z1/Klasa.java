package project.xml.z1;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "broj",
    "iznos"
})
@XmlRootElement(name = "Klasa")
@Getter
@Setter
public class Klasa {

    @XmlElement(name = "Broj")
    protected int broj;
    @XmlElement(name = "Iznos", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger iznos;

}
