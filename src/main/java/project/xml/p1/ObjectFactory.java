package project.xml.p1;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.p
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ZahtevZaPatent }
     * 
     */
    public ZahtevZaPatent createZahtevZaPatent() {
        return new ZahtevZaPatent();
    }

    /**
     * Create an instance of {@link OsnovnaPrijava }
     * 
     */
    public OsnovnaPrijava createOsnovnaPrijava() {
        return new OsnovnaPrijava();
    }

    /**
     * Create an instance of {@link ZahtevZaPatent.Pronalazak }
     * 
     */
    public ZahtevZaPatent.Pronalazak createZahtevZaPatentPronalazak() {
        return new ZahtevZaPatent.Pronalazak();
    }

    /**
     * Create an instance of {@link ZahtevZaPatent.PodaciODostavljanju }
     * 
     */
    public ZahtevZaPatent.PodaciODostavljanju createZahtevZaPatentPodaciODostavljanju() {
        return new ZahtevZaPatent.PodaciODostavljanju();
    }

    /**
     * Create an instance of {@link ZahtevZaPatent.RanijaPrijava }
     * 
     */
    public ZahtevZaPatent.RanijaPrijava createZahtevZaPatentRanijaPrijava() {
        return new ZahtevZaPatent.RanijaPrijava();
    }

}
