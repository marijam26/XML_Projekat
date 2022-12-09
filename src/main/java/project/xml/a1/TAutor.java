package project.xml.a1;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TAutor", propOrder = {
    "ime",
    "prezime"
})
@XmlSeeAlso({
    TPreminuliAutor.class,
    TZivAutor.class
})
@Getter
@Setter
public abstract class TAutor {

    @XmlElement(name = "Ime", required = true)
    protected String ime;
    @XmlElement(name = "Prezime", required = true)
    protected String prezime;

}
