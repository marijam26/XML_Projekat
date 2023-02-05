import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ZahtevZaPatentDTO } from '../model/zahtev-za-patent';
import * as JsonToXML from 'js2xmlparser';
import { RanijaPrijavaDTO } from '../model/ranija-prijava';
import { ResenjeDTO } from '../../shared-models/resenjeDTO';

@Injectable({
  providedIn: 'root',
})
export class P1Service {
  constructor(private _http: HttpClient) {}
  p1Url = 'http://localhost:9001/api/patent';

  getPatent() {
    const newUrl = this.p1Url + 2; //document id
    return this._http.get<any>(newUrl, {
      headers: new HttpHeaders({
        'Content-Type': 'text/xml',
        'Access-Control-Allow-Origin': '*',
        responseType: 'xml',
      }),
    });
  }

  getAllPatent() {
    return this._http.get(this.p1Url + '/getAll', {
      headers: new HttpHeaders().set('Content-Type', 'application/xml'),
      responseType: 'text',
    });
  }

  getAllZahetvPatent() {
    return this._http.get(this.p1Url + '/getAllZahetve', {
      headers: new HttpHeaders().set('Content-Type', 'application/xml'),
      responseType: 'text',
    });
  }

  getAllOdobrenePatent() {
    return this._http.get(this.p1Url + '/getAllOdobrene', {
      headers: new HttpHeaders().set('Content-Type', 'application/xml'),
      responseType: 'text',
    });
  }

  savePatent(zahtev: ZahtevZaPatentDTO) {
    const xmlZahtev = JsonToXML.parse('zahtevZaPatentDTO', zahtev);
    const newUrl = this.p1Url + '/save';
    return this._http.post<any>(newUrl, xmlZahtev, {
      headers: new HttpHeaders({
        'Content-Type': 'application/xml',
        'Access-Control-Allow-Origin': '*',
        responseType: 'text',
      }),
    });
  }

  saveResenje(resenje: ResenjeDTO) {
    const resenjeXML = JsonToXML.parse('resenjeDTO', resenje);
    const newUrl = this.p1Url + '/saveResenje';
    return this._http.post<any>(newUrl, resenjeXML, {
      headers: new HttpHeaders({
        'Content-Type': 'application/xml',
        'Access-Control-Allow-Origin': '*',
        responseType: 'text',
      }),
    });
  }

  mapXmlToPatent(data: any) {
    let zahtev = new ZahtevZaPatentDTO();
    zahtev.id = data['_attributes']['Id'];
    zahtev.brojPrijave = data['_attributes']['Broj_prijave'];
    zahtev.datumPodnosenja = data['_attributes']['Datum_podnosenja'];
    zahtev.datumPrijema = data['_attributes']['Datum_prijema'];
    zahtev.vrstaPunomocnika = data['_attributes']['Vrsta_punomocnika'];
    zahtev.zajednickiPredstavnik =
      data['_attributes']['Zajednicki_predstavnik'];

    if (data['ns3:Osnovna_prijava'] != undefined) {
      zahtev.osnovnaPrijava.broj =
        data['ns3:Osnovna_prijava']['ns3:Broj']._text;
      zahtev.osnovnaPrijava.datum =
        data['ns3:Osnovna_prijava']['ns3:Datum']._text;
    }

    if (
      data['ns2:Podnosilac_prijave']['_attributes']['xsi:type'] ===
      'ns2:TFizicko_lice'
    ) {
      zahtev.podnosilacPrijave.ime =
        data['ns2:Podnosilac_prijave']['ns2:Ime']._text;
      zahtev.podnosilacPrijave.prezime =
        data['ns2:Podnosilac_prijave']['ns2:Prezime']._text;
    } else {
      zahtev.podnosilacPrijave.poslovnoIme =
        data['ns2:Podnosilac_prijave']['ns2:Poslovno_ime']._text;
    }
    zahtev.podnosilacPrijave.kontakt.eposta =
      data['ns2:Podnosilac_prijave']['ns2:Kontakt']['ns2:E_posta']._text;
    zahtev.podnosilacPrijave.kontakt.faks =
      data['ns2:Podnosilac_prijave']['ns2:Kontakt']['ns2:Faks']._text;
    zahtev.podnosilacPrijave.kontakt.telefon =
      data['ns2:Podnosilac_prijave']['ns2:Kontakt']['ns2:Telefon']._text;

    zahtev.podnosilacPrijave.adresa.ulica =
      data['ns2:Podnosilac_prijave']['ns2:Adresa']['ns2:Ulica']._text;
    zahtev.podnosilacPrijave.adresa.broj =
      data['ns2:Podnosilac_prijave']['ns2:Adresa']['ns2:Broj']._text;
    zahtev.podnosilacPrijave.adresa.postanskiBroj =
      data['ns2:Podnosilac_prijave']['ns2:Adresa']['ns2:Postanski_broj']._text;
    zahtev.podnosilacPrijave.adresa.grad =
      data['ns2:Podnosilac_prijave']['ns2:Adresa']['ns2:Grad']._text;

    if (
      data['ns2:Punomocnik']['_attributes']['xsi:type'] === 'ns2:TFizicko_lice'
    ) {
      zahtev.punomocnik.ime = data['ns2:Punomocnik']['ns2:Ime']._text;
      zahtev.punomocnik.prezime = data['ns2:Punomocnik']['ns2:Prezime']._text;
    } else {
      zahtev.punomocnik.poslovnoIme =
        data['ns2:Punomocnik']['ns2:Poslovno_ime']._text;
    }
    zahtev.punomocnik.drzavljanstvo =
      data['ns2:Punomocnik']['ns2:Drzavljanstvo']._text;
    zahtev.punomocnik.kontakt.eposta =
      data['ns2:Punomocnik']['ns2:Kontakt']['ns2:E_posta']._text;
    zahtev.punomocnik.kontakt.faks =
      data['ns2:Punomocnik']['ns2:Kontakt']['ns2:Faks']._text;
    zahtev.punomocnik.kontakt.telefon =
      data['ns2:Punomocnik']['ns2:Kontakt']['ns2:Telefon']._text;

    zahtev.punomocnik.adresa.ulica =
      data['ns2:Punomocnik']['ns2:Adresa']['ns2:Ulica']._text;
    zahtev.punomocnik.adresa.broj =
      data['ns2:Punomocnik']['ns2:Adresa']['ns2:Broj']._text;
    zahtev.punomocnik.adresa.postanskiBroj =
      data['ns2:Punomocnik']['ns2:Adresa']['ns2:Postanski_broj']._text;
    zahtev.punomocnik.adresa.grad =
      data['ns2:Punomocnik']['ns2:Adresa']['ns2:Grad']._text;

    if (data['ns3:Pronalazac'] != undefined) {
      zahtev.pronalazac.ime = data['ns3:Pronalazac']['ns2:Ime']._text;
      zahtev.pronalazac.prezime = data['ns3:Pronalazac']['ns2:Prezime']._text;

      zahtev.pronalazac.drzavljanstvo =
        data['ns3:Pronalazac']['ns2:Drzavljanstvo']._text;
      zahtev.pronalazac.kontakt.eposta =
        data['ns3:Pronalazac']['ns2:Kontakt']['ns2:E_posta']._text;
      zahtev.pronalazac.kontakt.faks =
        data['ns3:Pronalazac']['ns2:Kontakt']['ns2:Faks']._text;
      zahtev.pronalazac.kontakt.telefon =
        data['ns3:Pronalazac']['ns2:Kontakt']['ns2:Telefon']._text;

      zahtev.pronalazac.adresa.ulica =
        data['ns3:Pronalazac']['ns2:Adresa']['ns2:Ulica']._text;
      zahtev.pronalazac.adresa.broj =
        data['ns3:Pronalazac']['ns2:Adresa']['ns2:Broj']._text;
      zahtev.pronalazac.adresa.drzava =
        data['ns3:Pronalazac']['ns2:Adresa']['ns2:Drzava']._text;
      zahtev.pronalazac.adresa.postanskiBroj =
        data['ns3:Pronalazac']['ns2:Adresa']['ns2:Postanski_broj']._text;
      zahtev.pronalazac.adresa.grad =
        data['ns3:Pronalazac']['ns2:Adresa']['ns2:Grad']._text;
    }

    zahtev.podaciODostavljanju.nacinDostavljanja =
      data['ns3:Podaci_o_dostavljanju']['ns3:Nacin_dostavljanja']._text;
    zahtev.podaciODostavljanju.adresa.ulica =
      data['ns3:Podaci_o_dostavljanju']['ns2:Adresa']['ns2:Ulica']._text;
    zahtev.podaciODostavljanju.adresa.broj =
      data['ns3:Podaci_o_dostavljanju']['ns2:Adresa']['ns2:Broj']._text;
    zahtev.podaciODostavljanju.adresa.postanskiBroj =
      data['ns3:Podaci_o_dostavljanju']['ns2:Adresa'][
        'ns2:Postanski_broj'
      ]._text;
    zahtev.podaciODostavljanju.adresa.grad =
      data['ns3:Podaci_o_dostavljanju']['ns2:Adresa']['ns2:Grad']._text;

    zahtev.pronalazak.engeskiNaziv =
      data['ns3:Pronalazak']['ns3:Engleski_naziv']._text;
    zahtev.pronalazak.srpskiNaziv =
      data['ns3:Pronalazak']['ns3:Srpski_naziv']._text;

    if (data['ns3:Ranija_prijava'] != undefined) {
      if (Array.isArray(data['ns3:Ranija_prijava'])) {
        for (let ranija of data['ns3:Ranija_prijava']) {
          zahtev.ranijaPrijava.push(
            new RanijaPrijavaDTO(
              ranija['ns3:Broj']._text,
              ranija['ns3:Datum']._text,
              ranija['ns3:Oznaka_drzave']._text
            )
          );
        }
      } else {
        zahtev.ranijaPrijava.push(
          new RanijaPrijavaDTO(
            data['ns3:Ranija_prijava']['ns3:Broj']._text,
            data['ns3:Ranija_prijava']['ns3:Datum']._text,
            data['ns3:Ranija_prijava']['ns3:Oznaka_drzave']._text
          )
        );
      }
    }

    console.log(zahtev);
    return zahtev;
  }
}
