package project.p1.util;

import org.w3c.dom.Node;
import project.p1.model.p1.ZahtevZaPatent;
import project.p1.model.resenje.Resenje;

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
        this.context = JAXBContext.newInstance("project.p1.model.p1");
    }

    public OutputStream marshall(ZahtevZaPatent zahtevZaPatent) throws JAXBException {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        OutputStream os = new ByteArrayOutputStream();
        marshaller.marshal(zahtevZaPatent, os);
        return os;
    }

    public OutputStream marshall(Resenje resenje) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("project.p1.model.resenje");
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        OutputStream os = new ByteArrayOutputStream();
        marshaller.marshal(resenje, os);
        return os;
    }

    public ZahtevZaPatent unmarshall(String filepath) throws JAXBException {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        File file = new File(filepath);
        return (ZahtevZaPatent) unmarshaller.unmarshal(file);
    }

    public Resenje unmarshallResenje(String filepath) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("project.p1.model.resenje");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        File file = new File(filepath);
        return (Resenje) unmarshaller.unmarshal(file);
    }

    public ZahtevZaPatent unmarshallFromNode(Node patenetNode) throws JAXBException {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (ZahtevZaPatent) unmarshaller.unmarshal(patenetNode);
    }

    public Resenje unmarshallFromNodeResenje(Node patenetNode) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("project.p1.model.resenje");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Resenje) unmarshaller.unmarshal(patenetNode);
    }
}
