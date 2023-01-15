import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ZahtevZaAutorskaDelaDTO} from "../model/zahtev-za-autorska-dela-d-t-o";
import * as JsonToXML from "js2xmlparser";

@Injectable({
  providedIn: 'root'
})
export class A1Service {

  constructor(private _http: HttpClient) { }
  a1Url = 'http://localhost:9002/api/autorskoPravo/'


  getAutorskoPravo() {
    const newUrl = this.a1Url + 2 ; //document id
    return this._http.get<any>(newUrl,{headers:new HttpHeaders({
        'Content-Type': 'text/xml',
        'Access-Control-Allow-Origin': '*',
        responseType: 'xml'
      })});
  }

  saveAutorskoPravo(zahtev: ZahtevZaAutorskaDelaDTO) {
    const xmlZahtev = JsonToXML.parse("zahtevZaAutorskaDelaDTO", zahtev);
    const newUrl = this.a1Url ; //document id
    return this._http.post<any>(newUrl,xmlZahtev,{headers:new HttpHeaders({
        'Content-Type': 'application/xml',
        'Access-Control-Allow-Origin': '*',
        responseType: 'text'
      })})}

}
