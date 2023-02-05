import { Component } from '@angular/core';
import {Korisnik, LoginInfo} from "../../../shared-models/korisnik";
import {AuthenticationService} from "../../services/authentication.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-login-registration',
  templateUrl: './login-registration.component.html',
  styleUrls: ['./login-registration.component.css']
})
export class LoginRegistrationComponent {
  showLogin:boolean = true;
  korisnik:Korisnik = new Korisnik();
  loginInfo:LoginInfo = new LoginInfo();

  constructor(private authenticationService:AuthenticationService,private toastr: ToastrService) {
  }


  loginUser(){
    if(this.loginInfo.email === '' || this.loginInfo.password === ''){
      this.toastr.error('Please fill in all fields!')
    }else{
      this.authenticationService.loginUser(this.loginInfo).subscribe(
        (data)=> {
          console.log(data)
          sessionStorage.setItem('logged',JSON.stringify(data));
          this.toastr.success('Successfully logged in!');
          if (data.uloga === 'Gradjanin'){
            setTimeout(window.location.href='/home',3000);
          }else{
            setTimeout(window.location.href='/view',3000);
          }
        },
        (err)=>this.toastr.error('Wrong credentials!')
      )
    }
  }

  registerClick(){
    this.showLogin = false;
  }

  registerUser(){
    if(this.korisnik.ime === '' || this.korisnik.prezime === '' || this.korisnik.email === '' || this.korisnik.lozinka === ''){
      this.toastr.error('Please fill in all fields!')
    }else{
      this.authenticationService.registerUser(this.korisnik).subscribe(
        (data)=> {
          this.toastr.success('Successfully registered!');
          this.showLogin = true;
        },
        (err)=>this.toastr.error(err.error)
      )
    }
  }
}
