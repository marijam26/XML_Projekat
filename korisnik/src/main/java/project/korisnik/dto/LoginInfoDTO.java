package project.korisnik.dto;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoginInfoDTO {

    public String email;
    public String password;
}
