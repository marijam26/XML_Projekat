package project.p1.util;

import org.exist.xmldb.EXistResource;
import org.w3c.dom.Node;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XUpdateQueryService;
import project.p1.model.p1.ZahtevZaPatent;
import project.p1.model.resenje.Resenje;

import javax.xml.transform.OutputKeys;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.exist.xupdate.XUpdateProcessor.UPDATE;

public class DatabaseUtilities {

    private static AuthenticationUtilities.ConnectionProperties conn;


    public static void init() throws IOException, ClassNotFoundException, XMLDBException, InstantiationException, IllegalAccessException {
        conn = AuthenticationUtilities.loadProperties();
        Class<?> cl = Class.forName(conn.driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
    }

    public static void storeResource(String collectionId, String documentId, OutputStream outputStream) throws XMLDBException {
        Collection col = null;
        XMLResource res = null;

        try {

            System.out.println("[INFO] Retrieving the collection: " + collectionId);
            col = getOrCreateCollection(collectionId);

            System.out.println("[INFO] Inserting the document: " + documentId);
            res = (XMLResource) col.createResource(documentId, XMLResource.RESOURCE_TYPE);

            res.setContent(outputStream);
            System.out.println("[INFO] Storing the document: " + res.getId());

            col.storeResource(res);
            System.out.println("[INFO] Done.");

        } finally {

            if(res != null) {
                try {
                    ((EXistResource)res).freeResources();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }

            if(col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }

    }

    private static Collection getOrCreateCollection(String collectionUri) throws XMLDBException {
        return getOrCreateCollection(collectionUri, 0);
    }

    private static Collection getOrCreateCollection(String collectionUri, int pathSegmentOffset) throws XMLDBException {
        Collection col = DatabaseManager.getCollection(conn.uri + collectionUri, conn.user, conn.password);

        if(col == null) {

            if(collectionUri.startsWith("/")) {
                collectionUri = collectionUri.substring(1);
            }

            String[] pathSegments = collectionUri.split("/");

            if(pathSegments.length > 0) {
                StringBuilder path = new StringBuilder();

                for(int i = 0; i <= pathSegmentOffset; i++) {
                    path.append("/").append(pathSegments[i]);
                }

                Collection startCol = DatabaseManager.getCollection(conn.uri + path, conn.user, conn.password);

                if (startCol == null) {

                    String parentPath = path.substring(0, path.lastIndexOf("/"));
                    Collection parentCol = DatabaseManager.getCollection(conn.uri + parentPath, conn.user, conn.password);

                    CollectionManagementService mgt = (CollectionManagementService) parentCol.getService("CollectionManagementService", "1.0");

                    System.out.println("[INFO] Creating the collection: " + pathSegments[pathSegmentOffset]);
                    col = mgt.createCollection(pathSegments[pathSegmentOffset]);

                    col.close();
                    parentCol.close();

                } else {
                    startCol.close();
                }
            }
            return getOrCreateCollection(collectionUri, ++pathSegmentOffset);
        } else {
            return col;
        }
    }

    public static Node getPatentResource(String documentId,String collectionId) {
        Collection col = null;
        XMLResource res = null;
        try {
            col = DatabaseManager.getCollection(conn.uri + collectionId, conn.user, conn.password);
            col.setProperty(OutputKeys.INDENT, "yes");

            res = (XMLResource)col.getResource(documentId);
            return res.getContentAsDOM();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(res != null) {
                try {
                    ((EXistResource)res).freeResources();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }

            if(col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }
    }

    public static ZahtevZaPatent getPatent(String documentId, String collectionId) {
        Collection col = null;
        XMLResource res = null;
        try {
            col = DatabaseManager.getCollection(conn.uri + collectionId, conn.user, conn.password);
            col.setProperty(OutputKeys.INDENT, "yes");

            res = (XMLResource)col.getResource(documentId);

            if(res == null) {
                System.out.println("[WARNING] Document '" + documentId + "' can not be found!");
                return null;
            } else {

                MarshallingUtils marshallingUtils = new MarshallingUtils();
                return marshallingUtils.unmarshallFromNode(res.getContentAsDOM());
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(res != null) {
                try {
                    ((EXistResource)res).freeResources();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }

            if(col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }
    }

    public static int getCollectionSize(String collectionId){
        Collection col = null;
        try {
            col = DatabaseManager.getCollection(conn.uri + collectionId, conn.user, conn.password);
            col.setProperty(OutputKeys.INDENT, "yes");
            return col.getResourceCount();
        } catch (Exception e) {
            return 0;
        } finally {
            if(col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }
    }

    public static void updatePatent(String documentId, String collectionId,String brojPrijave){
        Collection col = null;
        XMLResource res = null;
        try {
            String contextXPath4 = "/Zahtev_za_patent/@Broj_prijave";

            col = DatabaseManager.getCollection(conn.uri + collectionId, conn.user, conn.password);
            col.setProperty(OutputKeys.INDENT, "yes");

            System.out.println("[INFO] Fetching XUpdate service for the collection.");
            XUpdateQueryService xupdateService = (XUpdateQueryService) col.getService("XUpdateQueryService", "1.0");
            xupdateService.setProperty("indent", "yes");

            System.out.println("[INFO] Updating " + contextXPath4 + " node.");
            long mods = xupdateService.updateResource(documentId, String.format(UPDATE, contextXPath4, "1"));
            System.out.println("[INFO] " + mods + " modifications processed.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }
    }

    public static List<ZahtevZaPatent> getAllPatent(String collectionId) {
        Collection col = null;
        XMLResource res = null;
        try {
            MarshallingUtils marshallingUtils = new MarshallingUtils();
            List<ZahtevZaPatent> zahtevZaPatentList = new ArrayList<>();
            col = DatabaseManager.getCollection(conn.uri + collectionId, conn.user, conn.password);
            col.setProperty(OutputKeys.INDENT, "yes");
            for(String s: col.listResources()){
                res = (XMLResource)col.getResource(s);
                zahtevZaPatentList.add(marshallingUtils.unmarshallFromNode(res.getContentAsDOM()));
            }

            return zahtevZaPatentList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(res != null) {
                try {
                    ((EXistResource)res).freeResources();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }

            if(col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }
    }

    public static List<Resenje> getAllResenja(String collectionId) {
        Collection col = null;
        XMLResource res = null;
        try {
            MarshallingUtils marshallingUtils = new MarshallingUtils();
            List<Resenje> resenja = new ArrayList<>();
            col = DatabaseManager.getCollection(conn.uri + collectionId, conn.user, conn.password);
            col.setProperty(OutputKeys.INDENT, "yes");
            for(String s: col.listResources()){
                res = (XMLResource)col.getResource(s);
                resenja.add(marshallingUtils.unmarshallFromNodeResenje(res.getContentAsDOM()));
            }

            return resenja;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(res != null) {
                try {
                    ((EXistResource)res).freeResources();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }

            if(col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
        }
    }


}
