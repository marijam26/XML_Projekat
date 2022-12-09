package project.xml.loading;

import project.xml.a1.ZahtevZaAutorskaDela;
import project.xml.p1.ZahtevZaPatent;
import project.xml.z1.ZahtevZaZig;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Loading {

    public void loadA() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("project.xml.a1");
        Unmarshaller unmarshaller = context.createUnmarshaller();

        ZahtevZaAutorskaDela zahtevZaAutorskaDela = (ZahtevZaAutorskaDela) unmarshaller.unmarshal(new File("./data/autorsko_delo.xml"));
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(zahtevZaAutorskaDela, System.out);
    }

    public void loadP() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("project.xml.p1");
        Unmarshaller unmarshaller = context.createUnmarshaller();

        ZahtevZaPatent zahtevZaPatent = (ZahtevZaPatent) unmarshaller.unmarshal(new File("./data/patent.xml"));
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(zahtevZaPatent, System.out);
    }

    public void loadZ() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance("project.xml.z1");
        Unmarshaller unmarshaller = context.createUnmarshaller();

        ZahtevZaZig zahtevZaZig = (ZahtevZaZig) unmarshaller.unmarshal(new File("./data/zig.xml"));

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(zahtevZaZig, System.out);
    }

}
