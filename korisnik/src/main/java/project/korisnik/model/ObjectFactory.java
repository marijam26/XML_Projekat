package project.korisnik.model;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Korisnik }
     */
    public Korisnik createKorisnik() {
        return new Korisnik();
    }
}