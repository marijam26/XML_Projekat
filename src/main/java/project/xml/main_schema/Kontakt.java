package project.xml.main_schema;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "telefon",
    "faks",
    "ePosta"
})
@XmlRootElement(name = "Kontakt")
@Getter
@Setter
public class Kontakt {

    @XmlElement(name = "Telefon", required = true)
    protected String telefon;
    @XmlElement(name = "Faks")
    protected String faks;
    @XmlElement(name = "E_posta", required = true)
    protected String ePosta;


}
