package project.z1.repository;

import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;
import project.z1.util.DatabaseUtilities;

import java.io.IOException;
import java.io.OutputStream;

@Repository
public class ZigRepository {

    public ZigRepository() throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        DatabaseUtilities.init();
    }

    public void save(OutputStream os) throws XMLDBException {
        DatabaseUtilities.storeResource("db/zigovi", "1", os);
    }
}
