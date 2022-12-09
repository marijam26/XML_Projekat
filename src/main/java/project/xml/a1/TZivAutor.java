package project.xml.a1;

import lombok.Getter;
import lombok.Setter;
import project.xml.main_schema.Adresa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TZiv_autor", propOrder = {
    "adresa",
    "drzavljanstvo",
    "pseudonim"
})
@Getter
@Setter
public class TZivAutor
    extends TAutor
{

    @XmlElement(name = "Adresa", namespace = "http://ftn.uns.ac.rs/sema", required = true)
    protected Adresa adresa;
    @XmlElement(name = "Drzavljanstvo", required = true)
    protected String drzavljanstvo;
    @XmlElement(name = "Pseudonim", required = true)
    protected String pseudonim;

}
