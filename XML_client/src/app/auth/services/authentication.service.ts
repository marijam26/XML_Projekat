import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import * as JsonToXML from "js2xmlparser";
import {Kosrisnik} from "../../shared-models/korisnik";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private _http: HttpClient) { }

  url = 'http://localhost:9001/api/user';


  savePatent(korisnik: Kosrisnik) {
    const korisnikXML = JsonToXML.parse('KorisnikDTO', korisnik);
    const newUrl = this.url + '/register';
    return this._http.post<any>(newUrl, korisnikXML, {
      headers: new HttpHeaders({
        'Content-Type': 'application/xml',
        'Access-Control-Allow-Origin': '*',
        responseType: 'text',
      }),
    });
  }
}
