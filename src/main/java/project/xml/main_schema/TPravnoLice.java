package project.xml.main_schema;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TPravno_lice", propOrder = {
    "poslovnoIme"
})
@Getter
@Setter
public class TPravnoLice
    extends TLice
{

    @XmlElement(name = "Poslovno_ime", required = true)
    protected Object poslovnoIme;

}
