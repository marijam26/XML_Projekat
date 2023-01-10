package project.a1.repository;

import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;
import project.a1.util.DatabaseUtilities;
import java.io.IOException;
import java.io.OutputStream;

@Repository
public class AutorskoDeloRepository {

    public AutorskoDeloRepository() throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        DatabaseUtilities.init();
    }

    public void save(OutputStream os) throws XMLDBException {
        DatabaseUtilities.storeResource("db/autorskaDela", "2", os);
    }
}
