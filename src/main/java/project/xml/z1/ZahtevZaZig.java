package project.xml.z1;

import lombok.Getter;
import lombok.Setter;
import project.xml.main_schema.TLice;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "podnosilacPrijave",
    "punomocnik",
    "zig",
    "takse",
    "prilozi"
})
@XmlRootElement(name = "Zahtev_za_zig")
@Getter
@Setter
public class ZahtevZaZig {

    @XmlElement(name = "Podnosilac_prijave", namespace = "http://ftn.uns.ac.rs/sema", required = true)
    protected TLice podnosilacPrijave;
    @XmlElement(name = "Punomocnik", namespace = "http://ftn.uns.ac.rs/sema", required = true)
    protected TLice punomocnik;
    @XmlElement(name = "Zig", required = true)
    protected Zig zig;
    @XmlElement(name = "Takse", required = true)
    protected Takse takse;
    @XmlElement(name = "Prilozi", required = true)
    protected Prilozi prilozi;

}
