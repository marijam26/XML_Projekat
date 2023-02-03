package project.korisnik.dto;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class KorisnikDTO {

    public String ime;
    public String prezime;
    public String email;
    public String lozinka;
    public String uloga;
}
