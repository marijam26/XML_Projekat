package project.a1.repository;

import org.exist.xmldb.EXistResource;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Node;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;
import project.a1.model.a1.ZahtevZaAutorskaDela;
import project.a1.model.resenje.Resenje;
import project.a1.util.AuthenticationUtilities;
import project.a1.util.DatabaseUtilities;
import project.a1.util.MarshallingUtils;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AutorskoDeloRepository {

    private final String collectionId = "db/autorskaDela";
    private final String collectionIdResenja = "db/AResenja";

    private Collection col;

    public AutorskoDeloRepository() throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        DatabaseUtilities.init();
    }

    public void save(OutputStream os) throws XMLDBException {
        DatabaseUtilities.storeResource(String.valueOf(DatabaseUtilities.getCollectionSize(collectionId)+1), os);
    }

    public ZahtevZaAutorskaDela getOne(String id) throws JAXBException, XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        return DatabaseUtilities.getZahtevById(id);
    }

    public Node getAutorskoPravoNode(String id) {
        return DatabaseUtilities.getPravoResource(id);
    }

    public List<ZahtevZaAutorskaDela> search(String data) throws Exception {

        List<XMLResource> resources = getResources(data);
        List<ZahtevZaAutorskaDela> zahtevi = new ArrayList<>();

        for(XMLResource r: resources){

            zahtevi.add(MarshallingUtils.unmarshallFromDOM(r.getContentAsDOM()));
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


    public List<ZahtevZaAutorskaDela> getAll() {
        return  DatabaseUtilities.getAll(collectionId);
    }

    public List<Resenje> getAllResenja() {
        return  DatabaseUtilities.getAllResenja(collectionIdResenja);
    }

    public void saveResenje(OutputStream os,String id ,String sifraZahteva) throws XMLDBException {
        String documentId = "resenje-a" + id;
        DatabaseUtilities.storeResource(collectionIdResenja, documentId, os);
    }
}
