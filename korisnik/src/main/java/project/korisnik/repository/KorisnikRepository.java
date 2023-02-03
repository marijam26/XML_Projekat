package project.korisnik.repository;

import org.springframework.stereotype.Repository;
import org.xmldb.api.base.XMLDBException;
import project.korisnik.model.Korisnik;
import project.korisnik.util.DatabaseUtilities;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.function.Predicate;

@Repository
public class KorisnikRepository {

    private final String collectionId = "db/korisnici";

    public KorisnikRepository() throws XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        DatabaseUtilities.init();
    }

    public boolean save(OutputStream os,String email) throws XMLDBException {
        String[] emails = DatabaseUtilities.getCollectionNames(collectionId);
        if(Arrays.asList(emails).contains(email)){
            return false;
        }
        DatabaseUtilities.storeResource(collectionId, email, os);
        return true;
    }

    public Korisnik getUserByEmail(String email){
        return DatabaseUtilities.getKorisnik(email,collectionId);
    }
}
