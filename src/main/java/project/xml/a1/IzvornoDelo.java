package project.xml.a1;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "naslov",
    "autor",
    "anonimanAutor"
})
@XmlRootElement(name = "Izvorno_delo")
@Getter
@Setter
public class IzvornoDelo {

    @XmlElement(name = "Naslov", required = true)
    protected String naslov;
    @XmlElement(name = "Autor")
    protected TAutor autor;
    @XmlElement(name = "Anoniman_autor", defaultValue = "true")
    protected Boolean anonimanAutor;

}
