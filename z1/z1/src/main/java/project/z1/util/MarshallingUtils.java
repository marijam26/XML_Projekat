package project.z1.util;

import org.w3c.dom.Node;
import project.z1.model.z1.ZahtevZaZig;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;

public class MarshallingUtils {

    private final JAXBContext context;

    public MarshallingUtils() throws JAXBException {
        this.context = JAXBContext.newInstance("project.z1.model.z1");
    }

    public OutputStream marshall(ZahtevZaZig zahtevZaZig) throws JAXBException {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        OutputStream os = new ByteArrayOutputStream();
        marshaller.marshal(zahtevZaZig, os);
        return os;
    }

    public ZahtevZaZig unmarshall(String filepath) throws JAXBException {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (ZahtevZaZig) unmarshaller.unmarshal(new File(filepath));
    }

    public ZahtevZaZig unmarshallFromNode(Node node) throws JAXBException {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (ZahtevZaZig) unmarshaller.unmarshal(node);
    }
}
