package project.z1.repository;

import org.exist.xmldb.EXistResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Node;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;
import project.z1.model.resenje.Resenje;
import project.z1.model.z1.ZahtevZaZig;
import project.z1.util.AuthenticationUtilities;
import project.z1.util.DatabaseUtilities;
import project.z1.util.MarshallingUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
@Repository
public class ZigRepository {

    private final String collectionId = "db/zigovi";
    private final String collectionIdResenja = "db/zResenja";

    private Collection col;

    public ZigRepository() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
        DatabaseUtilities.init();
    }

    public void save(OutputStream os, String id) throws XMLDBException {
        DatabaseUtilities.storeResource("db/zigovi", id, os);
    }

    public Node getZigNode(String id) {
        return DatabaseUtilities.getResource(id,collectionId);
    }

    public ZahtevZaZig getById(String id){
        return DatabaseUtilities.getZig(id, collectionId);
    }

    public List<ZahtevZaZig> search(String data) throws Exception {

        List<XMLResource> resources = getResources(data);
        List<ZahtevZaZig> zahtevi = new ArrayList<>();

        for(XMLResource r: resources){

            zahtevi.add(new MarshallingUtils().unmarshallFromNode(r.getContentAsDOM()));
        }

        return zahtevi;
    }

    public List<XMLResource> getResources(String data) throws Exception{
        AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();
        String xpathExp = String.format("/*[contains(., '%s')]", data);
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

    public List<ZahtevZaZig> getAll() {
        return DatabaseUtilities.getAll(collectionId);
    }

    public List<Resenje> getAllResenja() throws XMLDBException {
        return DatabaseUtilities.getAllResenja(collectionIdResenja);
    }

    public void saveResenje(OutputStream os, String id) throws XMLDBException {
        String documentId = "resenje-" + id;
        DatabaseUtilities.storeResource(collectionIdResenja, documentId, os);
    }
}
