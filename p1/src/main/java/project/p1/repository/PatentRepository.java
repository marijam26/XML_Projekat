package project.p1.repository;

import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;
import project.p1.util.DatabaseUtilities;

import java.io.IOException;
import java.io.OutputStream;

@Repository
public class PatentRepository {

    public PatentRepository() throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        DatabaseUtilities.init();
    }

    public void save(OutputStream os) throws XMLDBException {
        DatabaseUtilities.storeResource("db/patenti", "1", os);
    }
}
