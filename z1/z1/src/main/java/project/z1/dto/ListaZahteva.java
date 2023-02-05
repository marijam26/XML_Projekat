package project.z1.dto;

import project.z1.model.z1.ZahtevZaZig;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "zahtevi")
@XmlSeeAlso({ZahtevZaZig.class})
public class ListaZahteva {

    @XmlElement(name="zahtev")
    private final List<ZahtevZaZig> lista;

    public ListaZahteva() {
        lista = new ArrayList<>();
    }

    public ListaZahteva(List<ZahtevZaZig> listaa) {
        this.lista = listaa;
    }

}
