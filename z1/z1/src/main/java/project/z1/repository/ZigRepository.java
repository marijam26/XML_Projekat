package project.z1.repository;

import org.springframework.stereotype.Repository;
import org.w3c.dom.Node;
import org.xmldb.api.base.XMLDBException;
import project.z1.model.z1.ZahtevZaZig;
import project.z1.util.DatabaseUtilities;

import java.io.IOException;
import java.io.OutputStream;

@Repository
public class ZigRepository {

    private final String collectionId = "db/zigovi";

    public ZigRepository() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
        DatabaseUtilities.init();
    }

    public void save(OutputStream os) throws XMLDBException {
        DatabaseUtilities.storeResource("db/zigovi", "1", os);
    }

    public Node getZigNode(String id) {
        return DatabaseUtilities.getResource(id,collectionId);
    }

    public ZahtevZaZig getById(String id){
        return DatabaseUtilities.getZig(id, collectionId);
    }
}
