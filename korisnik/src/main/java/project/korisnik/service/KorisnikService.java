package project.korisnik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;
import project.korisnik.dto.KorisnikDTO;
import project.korisnik.dto.LoginInfoDTO;
import project.korisnik.model.Korisnik;
import project.korisnik.repository.KorisnikRepository;
import project.korisnik.util.MarshallingUtils;

import javax.xml.bind.JAXBException;
import java.io.OutputStream;

@Service
public class KorisnikService {

    @Autowired
    private KorisnikRepository korisnikRepository;


    public Korisnik map(KorisnikDTO korisnikDTO){
        Korisnik korisnik = new Korisnik();
        korisnik.setIme(korisnikDTO.ime);
        korisnik.setPrezime(korisnikDTO.prezime);
        korisnik.setEposta(korisnikDTO.email);
        korisnik.setLozinka(korisnikDTO.lozinka);
        korisnik.setUloga(korisnikDTO.uloga);
        return korisnik;
    }

    public boolean save(Korisnik korisnik) throws JAXBException, XMLDBException {
        MarshallingUtils marshallingUtils = new MarshallingUtils();
        OutputStream os = marshallingUtils.marshall(korisnik);
        return korisnikRepository.save(os,korisnik.getEposta());
    }

    public boolean loginUser(LoginInfoDTO loginInfoDTO){
        Korisnik korisnik = getUser(loginInfoDTO.email);
        if(korisnik == null){
            return false;
        }else{
            return korisnik.getLozinka().equals(loginInfoDTO.password);
        }
    }

    public Korisnik getUser(String email){
        return korisnikRepository.getUserByEmail(email);
    }
}
