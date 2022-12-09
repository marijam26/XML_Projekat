package project.xml.z1;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "osnovnaTaksa",
    "klasa",
    "grafickoResenje",
    "ukupno"
})
@XmlRootElement(name = "Takse")
@Getter
@Setter
public class Takse {

    @XmlElement(name = "Osnovna_taksa", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger osnovnaTaksa;
    @XmlElement(name = "Klasa", required = true)
    protected List<Klasa> klasa;
    @XmlElement(name = "Graficko_resenje")
    protected int grafickoResenje;
    @XmlElement(name = "Ukupno")
    protected int ukupno;

}
