package project.a1.util;

import project.a1.model.a1.AutorskoDelo;
import project.a1.model.a1.ZahtevZaAutorskaDela;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;

public class MarshallingUtils {


    public OutputStream marshall(ZahtevZaAutorskaDela autorskoDelo) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("project.a1.model.a1");
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        OutputStream os = new ByteArrayOutputStream();
        marshaller.marshal(autorskoDelo, os);
        return os;
    }

    public AutorskoDelo unmarshall(String filepath) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("project.a1.model.a1");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (AutorskoDelo) unmarshaller.unmarshal(new File(filepath));
    }
}
