package project.a1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;
import project.a1.model.a1.ZahtevZaAutorskaDela;
import project.a1.repository.AutorskoDeloRepository;
import project.a1.util.MarshallingUtils;
import javax.xml.bind.JAXBException;
import java.io.OutputStream;

@Service
public class AutorskoDeloService {

    @Autowired
    private AutorskoDeloRepository autorskoDeloRepository;

    public void save(ZahtevZaAutorskaDela zahtevZaAutorskaDela) throws XMLDBException, JAXBException {
        MarshallingUtils marshallingUtils = new MarshallingUtils();
        OutputStream os = marshallingUtils.marshall(zahtevZaAutorskaDela);
        autorskoDeloRepository.save(os);
    }


}
