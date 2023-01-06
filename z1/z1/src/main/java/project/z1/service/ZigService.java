package project.z1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;
import project.z1.model.z1.ZahtevZaZig;
import project.z1.repository.ZigRepository;
import project.z1.util.MarshallingUtils;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class ZigService {

    @Autowired
    private ZigRepository zigRepository;

    private MarshallingUtils marshallingUtils;

    public ZigService() throws JAXBException {
        this.marshallingUtils = new MarshallingUtils();
    }

    public void save(ZahtevZaZig zahtevZaZig) throws JAXBException, XMLDBException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        OutputStream os = this.marshallingUtils.marshall(zahtevZaZig);
        zigRepository.save(os);
    }


}
