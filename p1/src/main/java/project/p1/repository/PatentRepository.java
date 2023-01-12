package project.p1.repository;

import org.springframework.stereotype.Repository;
import org.w3c.dom.Node;
import org.xmldb.api.base.XMLDBException;
import project.p1.model.p1.ZahtevZaPatent;
import project.p1.util.DatabaseUtilities;

import java.io.IOException;
import java.io.OutputStream;

@Repository
public class PatentRepository {

    private final String collectionId = "db/patenti";

    public PatentRepository() throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        DatabaseUtilities.init();
    }

    public void save(OutputStream os) throws XMLDBException {
        String documentId = "p" + (DatabaseUtilities.getCollectionSize(collectionId) + 1);
        DatabaseUtilities.storeResource(collectionId, documentId, os);
    }

    public ZahtevZaPatent getPatentById(String id){
        return DatabaseUtilities.getPatent(id, collectionId);
    }

    public Node getPatentNode(String id) {
        return DatabaseUtilities.getPatentResource(id,collectionId);
    }
}
