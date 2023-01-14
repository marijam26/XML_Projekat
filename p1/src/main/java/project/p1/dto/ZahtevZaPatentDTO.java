package project.p1.dto;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigInteger;


@XmlRootElement
public class ZahtevZaPatentDTO {

    public OsnovnaPrijavaDTO osnovnaPrijava;
    public TLiceDTO podnosilacPrijave;
    public TLiceDTO punomocnik;
    public PronalazakDTO pronalazak;
    public RanijaPrijavaDTO[] ranijaPrijava;
    public TLiceDTO pronalazac;
    public PodaciODostavljanjuDTO podaciODostavljanju;
    public BigInteger brojPrijave;
    public String datumPrijema;
    public String datumPodnosenja;
    public String vrstaPunomocnika;
    public Boolean zajednickiPredstavnik;

}
