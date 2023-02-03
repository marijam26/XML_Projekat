package project.korisnik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.korisnik.dto.KorisnikDTO;
import project.korisnik.dto.LoginInfoDTO;
import project.korisnik.model.Korisnik;
import project.korisnik.service.KorisnikService;

@RestController
@RequestMapping(value = "api/user")
public class AuthenticationController {

    @Autowired
    public KorisnikService korisnikService;

    @PostMapping(value = "/register", consumes = "application/xml")
    public ResponseEntity<String> register(@RequestBody KorisnikDTO korisnikDTO){
        try {
            Korisnik korisnik = korisnikService.map(korisnikDTO);
            boolean isStored = korisnikService.save(korisnik);
            if(isStored){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>("Username already exist", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Server error", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/login", consumes = "application/xml")
    public ResponseEntity<String> login(@RequestBody LoginInfoDTO loginInfoDTO){
        try {
            boolean isLoggedIn = korisnikService.loginUser(loginInfoDTO);
            if(isLoggedIn){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>("Username or password are not valid!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Server error", HttpStatus.BAD_REQUEST);
        }
    }

}
