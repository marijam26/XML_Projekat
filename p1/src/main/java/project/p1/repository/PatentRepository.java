package project.p1.repository;

import org.springframework.stereotype.Repository;
import org.w3c.dom.Node;
import org.xmldb.api.base.XMLDBException;
import project.p1.model.p1.ZahtevZaPatent;
import project.p1.model.resenje.Resenje;
import project.p1.util.DatabaseUtilities;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Repository
public class PatentRepository {

    private final String collectionId = "db/patenti";
    private final String collectionIdResenja = "db/resenja";


    public PatentRepository() throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        DatabaseUtilities.init();
    }

    public void save(OutputStream os,String id) throws XMLDBException {
        String documentId = "p" + id;
        DatabaseUtilities.storeResource(collectionId, documentId, os);
    }

    public void saveResenje(OutputStream os,String id,String brojPrijave) throws XMLDBException {
        String documentId = "resenje-p" + id;
        DatabaseUtilities.storeResource(collectionIdResenja, documentId, os);
        //DatabaseUtilities.updatePatent("p"+id,collectionId,brojPrijave);
    }

    public List<ZahtevZaPatent> getAll(){ return  DatabaseUtilities.getAllPatent(collectionId); }

    public List<Resenje> getAllResenja(){ return  DatabaseUtilities.getAllResenja(collectionIdResenja); }

    public ZahtevZaPatent getPatentById(String id){
        return DatabaseUtilities.getPatent(id, collectionId);
    }

    public Node getPatentNode(String id) {
        return DatabaseUtilities.getPatentResource(id,collectionId);
    }
}
