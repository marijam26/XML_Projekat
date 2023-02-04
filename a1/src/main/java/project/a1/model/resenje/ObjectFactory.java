package project.a1.model.resenje;


import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Resenje }
     */
    public Resenje createKorisnik() {
        return new Resenje();
    }
}