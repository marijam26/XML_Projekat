package project.xml.a1;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "opisDela",
    "primerDela"
})
@XmlRootElement(name = "Prilozi")
@Getter
@Setter
public class Prilozi {

    @XmlElement(name = "Opis_dela", required = true)
    protected String opisDela;
    @XmlElement(name = "Primer_dela", required = true)
    protected String primerDela;

}
