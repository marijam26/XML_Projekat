package project.a1.repository;

import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;
import project.a1.model.a1.ZahtevZaAutorskaDela;
import project.a1.util.DatabaseUtilities;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.OutputStream;

@Repository
public class AutorskoDeloRepository {

    public AutorskoDeloRepository() throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        DatabaseUtilities.init();
    }

    public void save(OutputStream os) throws XMLDBException {
        DatabaseUtilities.storeResource("2", os);
    }

    public ZahtevZaAutorskaDela getOne(String id) throws JAXBException, XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        return DatabaseUtilities.getZahtevById(id);
    }
}
