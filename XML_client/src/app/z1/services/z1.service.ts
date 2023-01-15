import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ZahtevZaZigDTO } from '../model/zahtev-za-zig';
import * as JsonToXML from 'js2xmlparser';

@Injectable({
  providedIn: 'root',
})
export class Z1Service {
  constructor(private _http: HttpClient) {}
  url = 'http://localhost:8080/api/zig/';

  save(zahtev: ZahtevZaZigDTO) {
    const xml = JsonToXML.parse('zahtevZaZigDTO', zahtev);
    const saveUrl = this.url + 'save';
    return this._http.post<any>(saveUrl, xml, {
      headers: new HttpHeaders({
        'Content-Type': 'application/xml',
        'Access-Control-Allow-Origin': '*',
        responseType: 'text',
      }),
    });
  }
}
