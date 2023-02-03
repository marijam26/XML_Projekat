package project.korisnik.util;

import org.w3c.dom.Node;
import project.korisnik.model.Korisnik;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;

public class MarshallingUtils {

    private JAXBContext context;

    public MarshallingUtils() throws JAXBException {
        this.context = JAXBContext.newInstance("project.korisnik.model");
    }

    public OutputStream marshall(Korisnik korisnik) throws JAXBException {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        OutputStream os = new ByteArrayOutputStream();
        marshaller.marshal(korisnik, os);
        return os;
    }

    public Korisnik unmarshall(String filepath) throws JAXBException {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        File file = new File(filepath);
        return (Korisnik) unmarshaller.unmarshal(file);
    }

    public Korisnik unmarshallFromNode(Node patenetNode) throws JAXBException {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Korisnik) unmarshaller.unmarshal(patenetNode);
    }
}
