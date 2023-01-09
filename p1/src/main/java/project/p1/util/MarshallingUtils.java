package project.p1.util;

import project.p1.model.p1.ZahtevZaPatent;
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

    public ZahtevZaPatent unmarshall(String filepath) throws JAXBException {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        File file = new File(filepath);
        return (ZahtevZaPatent) unmarshaller.unmarshal(file);
    }
}
