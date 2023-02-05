package project.p1.repository;

import org.exist.xmldb.EXistResource;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Node;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;
import project.p1.model.p1.ZahtevZaPatent;
import project.p1.model.resenje.Resenje;
import project.p1.util.AuthenticationUtilities;
import project.p1.util.DatabaseUtilities;
import project.p1.util.MarshallingUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PatentRepository {

    private final String collectionId = "db/patenti";
    private final String collectionIdResenja = "db/P-resenja";
    private Collection col;


    public PatentRepository() throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        DatabaseUtilities.init();
    }

    public void save(OutputStream os,String id) throws XMLDBException {
        DatabaseUtilities.storeResource(collectionId, id, os);
    }

    public void saveResenje(OutputStream os,String id) throws XMLDBException {
        String documentId = "resenje-" + id;
        DatabaseUtilities.storeResource(collectionIdResenja, documentId, os);
    }

    public List<ZahtevZaPatent> getAll(){ return  DatabaseUtilities.getAllPatent(collectionId); }

    public List<Resenje> getAllResenja() throws XMLDBException {
        //DatabaseUtilities.getOrCreateCollection(collectionIdResenja);
        return  DatabaseUtilities.getAllResenja(collectionIdResenja); }

    public ZahtevZaPatent getPatentById(String id){
        return DatabaseUtilities.getPatent(id, collectionId);
    }

    public List<ZahtevZaPatent> search(String data) throws Exception {

        List<XMLResource> resources = getResources(data);
        List<ZahtevZaPatent> zahtevi = new ArrayList<>();

        for(XMLResource r: resources){

            zahtevi.add(new MarshallingUtils().unmarshallFromNode(r.getContentAsDOM()));
        }

        return zahtevi;
    }

    public List<XMLResource> getResources(String data) throws Exception{
        AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();
        String xpathExp = String.format("/*[contains(., '%s')]", data.toLowerCase());
        List<XMLResource> resources = new ArrayList<>();

        try {
            col = DatabaseManager.getCollection(conn.uri + collectionId);
            XPathQueryService xpathService = (XPathQueryService) col.getService("XPathQueryService", "1.0");
            xpathService.setProperty("indent", "yes");

            ResourceSet result = xpathService.query(xpathExp);

            ResourceIterator i = result.getIterator();
            Resource res = null;

            while(i.hasMoreResources()) {

                try {
                    res = i.nextResource();
                    resources.add((XMLResource) res);
                }
                finally {

                    try {
                        if(res != null) {
                            ((EXistResource) res).freeResources();
                        }
                    } catch (XMLDBException xe) {
                        xe.printStackTrace();
                    }
                }
            }

        } finally {

            if(col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }

        return resources;
    }

    public Node getPatentNode(String id) {
        return DatabaseUtilities.getPatentResource(id,collectionId);
    }
}
