package project.xml.main_schema;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TFizicko_lice", propOrder = {
    "ime",
    "prezime",
    "drzavljanstvo"
})
@Getter
@Setter
public class TFizickoLice
    extends TLice
{

    @XmlElement(name = "Ime", required = true)
    protected String ime;
    @XmlElement(name = "Prezime", required = true)
    protected String prezime;
    @XmlElement(name = "Drzavljanstvo", required = true)
    protected String drzavljanstvo;

}
