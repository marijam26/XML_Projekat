package project.xml.main_schema;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TLice", propOrder = {
    "adresa",
    "kontakt"
})
@XmlSeeAlso({
    TPravnoLice.class,
    TFizickoLice.class
})
@Getter
@Setter
public abstract class TLice {

    @XmlElement(name = "Adresa", required = true)
    protected Adresa adresa;
    @XmlElement(name = "Kontakt", required = true)
    protected Kontakt kontakt;

}
