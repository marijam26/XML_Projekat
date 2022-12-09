package project.xml.z1;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "primerakZnaka",
    "spisakRobe",
    "punomoc",
    "ranijaPunomoc",
    "naknadnaPunomoc",
    "opstiAkt",
    "dokazOPravuPrvenstva",
    "dokazOUplati"
})
@XmlRootElement(name = "Prilozi")
@Getter
@Setter
public class Prilozi {

    @XmlElement(name = "Primerak_znaka", required = true)
    protected String primerakZnaka;
    @XmlElement(name = "Spisak_robe", required = true)
    protected String spisakRobe;
    @XmlElement(name = "Punomoc", required = true)
    protected String punomoc;
    @XmlElement(name = "Ranija_punomoc", required = true)
    protected String ranijaPunomoc;
    @XmlElement(name = "Naknadna_punomoc", required = true)
    protected String naknadnaPunomoc;
    @XmlElement(name = "Opsti_akt", required = true)
    protected String opstiAkt;
    @XmlElement(name = "Dokaz_o_pravu_prvenstva", required = true)
    protected String dokazOPravuPrvenstva;
    @XmlElement(name = "Dokaz_o_uplati", required = true)
    protected String dokazOUplati;

}
