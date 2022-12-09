package project.xml.z1;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "izgledZnaka",
    "boja",
    "transliteracija",
    "prevod",
    "opis",
    "klasaRobe"
})
@XmlRootElement(name = "Zig")
@Getter
@Setter
public class Zig {

    @XmlElement(name = "Izgled_znaka", required = true)
    protected Object izgledZnaka;
    @XmlElement(name = "Boja", required = true)
    protected List<String> boja;
    @XmlElement(name = "Transliteracija", required = true)
    protected String transliteracija;
    @XmlElement(name = "Prevod", required = true)
    protected String prevod;
    @XmlElement(name = "Opis", required = true)
    protected String opis;
    @XmlElement(name = "Klasa_robe", type = Integer.class)
    protected List<Integer> klasaRobe;
    @XmlAttribute(name = "Tip_ziga")
    protected TTipZiga tipZiga;
    @XmlAttribute(name = "Vrsta_znaka")
    protected TVrstaZnaka vrstaZnaka;

}
