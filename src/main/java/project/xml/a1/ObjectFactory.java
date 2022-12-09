package project.xml.a1;

import javax.xml.bind.annotation.XmlRegistry;


@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: rs.ac.uns.ftn.autorska_dela
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ZahtevZaAutorskaDela }
     * 
     */
    public ZahtevZaAutorskaDela createZahtevZaAutorskaDela() {
        return new ZahtevZaAutorskaDela();
    }

    /**
     * Create an instance of {@link AutorskoDelo }
     * 
     */
    public AutorskoDelo createAutorskoDelo() {
        return new AutorskoDelo();
    }

    /**
     * Create an instance of {@link IzvornoDelo }
     * 
     */
    public IzvornoDelo createIzvornoDelo() {
        return new IzvornoDelo();
    }

    /**
     * Create an instance of {@link Prilozi }
     * 
     */
    public Prilozi createPrilozi() {
        return new Prilozi();
    }

    /**
     * Create an instance of {@link TPreminuliAutor }
     * 
     */
    public TPreminuliAutor createTPreminuliAutor() {
        return new TPreminuliAutor();
    }

    /**
     * Create an instance of {@link TZivAutor }
     * 
     */
    public TZivAutor createTZivAutor() {
        return new TZivAutor();
    }

}
