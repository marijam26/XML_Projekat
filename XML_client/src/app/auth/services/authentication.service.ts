import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import * as JsonToXML from "js2xmlparser";
import {Korisnik, LoginInfo} from "../../shared-models/korisnik";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private _http: HttpClient) { }

  url = 'http://localhost:9004/api/user';


  registerUser(korisnik: Korisnik) {
    const korisnikXML = JsonToXML.parse('korisnikDTO', korisnik);
    const newUrl = this.url + '/register';
    return this._http.post<any>(newUrl, korisnikXML, {
      headers: new HttpHeaders({
        'Content-Type': 'application/xml',
        'Access-Control-Allow-Origin': '*',
        responseType: 'text',
      }),
    });
  }

  loginUser(loginInfo:LoginInfo){
    const infoXML = JsonToXML.parse('loginInfoDTO', loginInfo);
    const newUrl = this.url + '/login';
    return this._http.post<any>(newUrl, infoXML, {
      headers: new HttpHeaders({
        'Content-Type': 'application/xml',
        'Access-Control-Allow-Origin': '*',
        responseType: 'text',
      }),
    });
  }

}
