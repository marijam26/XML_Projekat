package project.a1.util;

import org.w3c.dom.Node;
import project.a1.model.a1.ZahtevZaAutorskaDela;
import project.a1.model.resenje.Resenje;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;

public class MarshallingUtils {

    private static JAXBContext context;

    static {
        try {
            context = JAXBContext.newInstance("project.a1.model.a1");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }



    public OutputStream marshall(ZahtevZaAutorskaDela autorskoDelo) throws JAXBException {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        OutputStream os = new ByteArrayOutputStream();
        marshaller.marshal(autorskoDelo, os);
        return os;
    }

    public OutputStream marshall(Resenje resenje) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("project.a1.model.resenje");
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        OutputStream os = new ByteArrayOutputStream();
        marshaller.marshal(resenje, os);
        return os;
    }

    public ZahtevZaAutorskaDela unmarshall(String filepath) throws JAXBException {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (ZahtevZaAutorskaDela) unmarshaller.unmarshal(new File(filepath));
    }
    public static ZahtevZaAutorskaDela unmarshallFromDOM(Node data) throws JAXBException {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (ZahtevZaAutorskaDela) unmarshaller.unmarshal(data);
    }

    public Resenje unmarshallFromNodeResenje(Node contentAsDOM) throws JAXBException {
        JAXBContext context1 = JAXBContext.newInstance("project.a1.model.resenje");
        Unmarshaller unmarshaller = context1.createUnmarshaller();
        return (Resenje) unmarshaller.unmarshal(contentAsDOM);
    }
}
