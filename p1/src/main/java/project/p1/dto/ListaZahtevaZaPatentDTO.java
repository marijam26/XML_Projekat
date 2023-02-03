package project.p1.dto;

import project.p1.model.p1.ZahtevZaPatent;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "zahtevi")
@XmlSeeAlso({ZahtevZaPatent.class})
public class ListaZahtevaZaPatentDTO {

    @XmlElement(name="zahtev")
    private final List<ZahtevZaPatent> zahtevZaPatentList;

    public ListaZahtevaZaPatentDTO() {
        zahtevZaPatentList = new ArrayList<>();
    }

    public ListaZahtevaZaPatentDTO(List<ZahtevZaPatent> zahtevZaPatentList) {
        this.zahtevZaPatentList = zahtevZaPatentList;
    }

}
