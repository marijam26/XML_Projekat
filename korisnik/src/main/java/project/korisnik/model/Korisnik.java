
package project.korisnik.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "ime",
        "prezime",
        "eposta",
        "lozinka",
        "uloga"
})
@XmlRootElement(name = "Korisnik")
public class Korisnik {

    @XmlElement(name = "Ime", required = true)
    protected String ime;
    @XmlElement(name = "Prezime", required = true)
    protected String prezime;
    @XmlElement(name = "Eposta", required = true)
    protected String eposta;
    @XmlElement(name = "Lozinka", required = true)
    protected String lozinka;
    @XmlElement(name = "Uloga", required = true)
    protected String uloga;



    public String getIme() {
        return ime;
    }

    public void setIme(String value) {
        this.ime = value;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String value) {
        this.prezime = value;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String value) {
        this.eposta = value;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String value) {
        this.lozinka = value;
    }

    public String getUloga() {
        return uloga;
    }

    public void setUloga(String value) {
        this.uloga = value;
    }



}
