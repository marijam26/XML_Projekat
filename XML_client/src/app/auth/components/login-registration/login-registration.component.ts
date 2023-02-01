import { Component } from '@angular/core';
import {Kosrisnik, LoginInfo} from "../../../shared-models/korisnik";

@Component({
  selector: 'app-login-registration',
  templateUrl: './login-registration.component.html',
  styleUrls: ['./login-registration.component.css']
})
export class LoginRegistrationComponent {
  showLogin:boolean = true;
  korisnik:Kosrisnik = new Kosrisnik();
  loginInfo:LoginInfo = new LoginInfo();

  registerClick(){
    this.showLogin = false;
  }
}
