import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ZahtevZaPatentDTO} from "../model/zahtev-za-patent";
import * as JsonToXML from "js2xmlparser";

@Injectable({
  providedIn: 'root'
})
export class P1Service {

  constructor(private _http: HttpClient) { }
  p1Url = 'http://localhost:9001/api/patent/'


  getAutorskoPravo() {
    const newUrl = this.p1Url + 2 ; //document id
    return this._http.get<any>(newUrl,{headers:new HttpHeaders({
        'Content-Type': 'text/xml',
        'Access-Control-Allow-Origin': '*',
        responseType: 'xml'
      })});
  }

  savePatent(zahtev: ZahtevZaPatentDTO) {
    const xmlZahtev = JsonToXML.parse("zahtevZaPatentDTO", zahtev);
    console.log(xmlZahtev);
    const newUrl = this.p1Url + '/save'; //document id
    return this._http.post<any>(newUrl,xmlZahtev,{headers:new HttpHeaders({
        'Content-Type': 'application/xml',
        'Access-Control-Allow-Origin': '*',
        responseType: 'text'
      })})}
}
