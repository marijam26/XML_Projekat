package project.xml.main_schema;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


@XmlRegistry
public class ObjectFactory {

    private static final QName _PodnosilacPrijave_QNAME = new QName("http://ftn.uns.ac.rs/sema", "Podnosilac_prijave");
    private static final QName _Punomocnik_QNAME = new QName("http://ftn.uns.ac.rs/sema", "Punomocnik");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.sema
     * 
     */
    public ObjectFactory() {
        // TODO document why this constructor is empty
    }

    /**
     * Create an instance of {@link Kontakt }
     * 
     */
    public Kontakt createKontakt() {
        return new Kontakt();
    }

    /**
     * Create an instance of {@link Adresa }
     * 
     */
    public Adresa createAdresa() {
        return new Adresa();
    }

    /**
     * Create an instance of {@link TPravnoLice }
     * 
     */
    public TPravnoLice createTPravnoLice() {
        return new TPravnoLice();
    }

    /**
     * Create an instance of {@link TFizickoLice }
     * 
     */
    public TFizickoLice createTFizickoLice() {
        return new TFizickoLice();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TLice }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/sema", name = "Podnosilac_prijave")
    public JAXBElement<TLice> createPodnosilacPrijave(TLice value) {
        return new JAXBElement<>(_PodnosilacPrijave_QNAME, TLice.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TLice }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ftn.uns.ac.rs/sema", name = "Punomocnik")
    public JAXBElement<TLice> createPunomocnik(TLice value) {
        return new JAXBElement<>(_Punomocnik_QNAME, TLice.class, null, value);
    }

}
