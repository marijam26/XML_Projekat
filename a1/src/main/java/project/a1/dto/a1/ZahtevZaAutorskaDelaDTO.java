package project.a1.dto.a1;

import lombok.Getter;
import lombok.Setter;
import project.a1.dto.main_schema.TLiceDTO;
import project.a1.model.a1.AutorskoDelo;
import project.a1.model.a1.Prilozi;
import project.a1.model.a1.TAutor;
import project.a1.model.main_schema.TLice;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;

@XmlRootElement
public class ZahtevZaAutorskaDelaDTO {
    public TLiceDTO podnosilacPrijave;
    public TLiceDTO punomocnik;
    public AutorskoDeloDTO autorskoDelo;
    public PriloziDTO prilozi;
    public TAutorDTO autor;
    public Date datumPodnosenja = new Date();
    public Integer broj = 3;
    public Boolean podnosilacJeAutor;

}
