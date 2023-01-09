package project.p1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;
import project.p1.model.p1.ZahtevZaPatent;
import project.p1.repository.PatentRepository;
import project.p1.util.MarshallingUtils;
import javax.xml.bind.JAXBException;
import java.io.OutputStream;

@Service
public class PatentService {

    @Autowired
    private PatentRepository patentRepository;


    public void save(ZahtevZaPatent zahtevZaPatent) throws JAXBException, XMLDBException {
        MarshallingUtils marshallingUtils = new MarshallingUtils();
        OutputStream os = marshallingUtils.marshall(zahtevZaPatent);
        patentRepository.save(os);
    }
}
