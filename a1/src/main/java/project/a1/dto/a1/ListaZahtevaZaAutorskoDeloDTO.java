package project.a1.dto.a1;

import project.a1.model.a1.ZahtevZaAutorskaDela;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "zahtevi")
@XmlSeeAlso({ZahtevZaAutorskaDela.class})
public class ListaZahtevaZaAutorskoDeloDTO {

    @XmlElement(name="zahtev")
    private final List<ZahtevZaAutorskaDela> zahtevZaAutorskoDeloList;

    public ListaZahtevaZaAutorskoDeloDTO() {
        zahtevZaAutorskoDeloList = new ArrayList<>();
    }

    public ListaZahtevaZaAutorskoDeloDTO(List<ZahtevZaAutorskaDela> zahtevZaAutorskoDeloList) {
        this.zahtevZaAutorskoDeloList = zahtevZaAutorskoDeloList;
    }

}
