package project.z1.repository;

import org.springframework.stereotype.Repository;
import project.z1.util.DatabaseUtilities;

import java.io.IOException;
import java.io.OutputStream;

@Repository
public class ZigRepository {

    public ZigRepository() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        DatabaseUtilities.init();
    }

    public void save(OutputStream os) {
        DatabaseUtilities.storeResource("db/zigovi", "1", os);
    }
}
