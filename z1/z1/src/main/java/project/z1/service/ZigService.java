package project.z1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.z1.model.z1.ZahtevZaZig;
import project.z1.repository.ZigRepository;
import project.z1.util.MarshallingUtils;

import javax.xml.bind.JAXBException;
import java.io.OutputStream;

@Service
public class ZigService {

    @Autowired
    private ZigRepository zigRepository;

    public void save(ZahtevZaZig zahtevZaZig) throws JAXBException {
        MarshallingUtils marshallingUtils = new MarshallingUtils();
        OutputStream os = marshallingUtils.marshall(zahtevZaZig);
        zigRepository.save(os);
    }


}
