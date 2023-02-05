import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ZahtevZaZigDTO } from '../model/zahtev-za-zig';
import * as JsonToXML from 'js2xmlparser';
import { ResenjeDTO } from '../../shared-models/resenjeDTO';
import { TLiceDTO } from '../../shared-models/t-lice-d-t-o';

@Injectable({
  providedIn: 'root',
})
export class Z1Service {
  constructor(private _http: HttpClient) {}
  url = 'http://localhost:9003/api/zig';

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

  get() {
    const newUrl = this.url + 2; //document id
    return this._http.get<any>(newUrl, {
      headers: new HttpHeaders({
        'Content-Type': 'text/xml',
        'Access-Control-Allow-Origin': '*',
        responseType: 'xml',
      }),
    });
  }

  getAll() {
    return this._http.get(this.url + '/', {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/xml')
        .set('Accept', 'application/xml'),
      responseType: 'text',
    });
  }

  getZahtevi() {
    return this._http.get(this.url + '/zahtevi', {
      headers: new HttpHeaders().set('Content-Type', 'application/xml'),
      responseType: 'text',
    });
  }

  getOdobreni() {
    return this._http.get(this.url + '/odobreni', {
      headers: new HttpHeaders().set('Content-Type', 'application/xml'),
      responseType: 'text',
    });
  }

  saveResenje(resenje: ResenjeDTO) {
    const resenjeXML = JsonToXML.parse('resenjeDTO', resenje);
    const newUrl = this.url + '/saveResenje';
    return this._http.post<any>(newUrl, resenjeXML, {
      headers: new HttpHeaders({
        'Content-Type': 'application/xml',
        'Access-Control-Allow-Origin': '*',
        responseType: 'text',
      }),
    });
  }

  mapXmlToZahtev(value: any) {
    let zahtev: ZahtevZaZigDTO = new ZahtevZaZigDTO();
    zahtev.id = value.id[0];
    zahtev.brojPrijave = value.brojPrijave[0];
    zahtev.datumPodnosenja = value.datumPodnosenja[0];
    zahtev.zig.opis = value.zig[0].opis[0];
    if (value.zig[0].ostalo !== undefined) {
      zahtev.zig.ostalo = value.zig[0].ostalo[0];
    }
    zahtev.zig.prevod = value.zig[0].prevod[0];
    zahtev.zig.transliteracija = value.zig[0].transliteracija[0];

    for (let b of value.zig[0].boja[0].boja) {
      console.log(b);
      zahtev.zig.boje.push(b);
    }

    for (let k of value.zig[0].klasaRobe[0].klasaRobe) {
      console.log(k);
      zahtev.zig.klase.push(k);
    }

    zahtev.takse.osnovnaTaksa = value.takse[0].osnovnaTaksa[0];
    zahtev.takse.ukupno = value.takse[0].ukupno[0];
    zahtev.takse.grafickoResenje = value.takse[0].grafickoResenje[0];
    zahtev.takse.klase = value.takse[0].klase[0];
    zahtev.takse.brojKlasa = value.takse[0].brojKlasa[0];

    if (value.podnosilacPrijave[0].ime !== undefined) {
      zahtev.podnosilacPrijave.ime = value.podnosilacPrijave[0].ime[0];
      zahtev.podnosilacPrijave.prezime = value.podnosilacPrijave[0].prezime[0];
    } else {
      zahtev.podnosilacPrijave.poslovnoIme =
        value.podnosilacPrijave[0].poslovnoIme[0];
    }

    zahtev.podnosilacPrijave.kontakt.eposta =
      value.podnosilacPrijave[0].kontakt[0].eposta[0];
    zahtev.podnosilacPrijave.kontakt.faks =
      value.podnosilacPrijave[0].kontakt[0].faks[0];
    zahtev.podnosilacPrijave.kontakt.telefon =
      value.podnosilacPrijave[0].kontakt[0].telefon[0];

    zahtev.podnosilacPrijave.adresa.ulica =
      value.podnosilacPrijave[0].adresa[0].ulica[0];
    zahtev.podnosilacPrijave.adresa.broj =
      value.podnosilacPrijave[0].adresa[0].broj[0];
    zahtev.podnosilacPrijave.adresa.postanskiBroj =
      value.podnosilacPrijave[0].adresa[0].postanskiBroj[0];
    zahtev.podnosilacPrijave.adresa.grad =
      value.podnosilacPrijave[0].adresa[0].grad[0];
    zahtev.podnosilacPrijave.drzavljanstvo =
      value.podnosilacPrijave[0].drzavljanstvo[0];

    if (value.punomocnik[0].ime !== undefined) {
      zahtev.punomocnik.ime = value.punomocnik[0].ime[0];
      zahtev.punomocnik.prezime = value.punomocnik[0].prezime[0];
    } else {
      zahtev.punomocnik.poslovnoIme = value.punomocnik[0].poslovnoIme[0];
    }

    zahtev.punomocnik.kontakt.eposta = value.punomocnik[0].kontakt[0].eposta[0];
    zahtev.punomocnik.kontakt.faks = value.punomocnik[0].kontakt[0].faks[0];
    zahtev.punomocnik.kontakt.telefon =
      value.punomocnik[0].kontakt[0].telefon[0];

    zahtev.punomocnik.adresa.ulica = value.punomocnik[0].adresa[0].ulica[0];
    zahtev.punomocnik.adresa.broj = value.punomocnik[0].adresa[0].broj[0];
    zahtev.punomocnik.adresa.postanskiBroj =
      value.punomocnik[0].adresa[0].postanskiBroj[0];
    zahtev.punomocnik.adresa.grad = value.punomocnik[0].adresa[0].grad[0];

    zahtev.prilozi.dokazOPravuPrvenstva.putanja =
      value.prilozi[0].dokazOPravuPrvenstva[0].putanja[0];
    zahtev.prilozi.dokazOPravuPrvenstva.value =
      value.prilozi[0].dokazOPravuPrvenstva[0].value[0] !== 'false';

    zahtev.prilozi.dokazOUplati.putanja =
      value.prilozi[0].dokazOUplati[0].putanja[0];
    zahtev.prilozi.dokazOUplati.value =
      value.prilozi[0].dokazOUplati[0].value[0] !== 'false';

    zahtev.prilozi.opstiAkt.putanja = value.prilozi[0].opstiAkt[0].putanja[0];
    zahtev.prilozi.opstiAkt.value =
      value.prilozi[0].opstiAkt[0].value[0] !== 'false';

    zahtev.prilozi.punomoc.putanja = value.prilozi[0].punomoc[0].putanja[0];
    zahtev.prilozi.punomoc.value =
      value.prilozi[0].punomoc[0].value[0] !== 'false';

    zahtev.prilozi.primerakZnaka.putanja =
      value.prilozi[0].primerakZnaka[0].putanja[0];
    zahtev.prilozi.primerakZnaka.value =
      value.prilozi[0].primerakZnaka[0].value[0] !== 'false';

    zahtev.prilozi.spisakRobe.putanja =
      value.prilozi[0].spisakRobe[0].putanja[0];
    zahtev.prilozi.spisakRobe.value =
      value.prilozi[0].spisakRobe[0].value[0] !== 'false';

    zahtev.prilozi.naknadnaPunomoc.putanja =
      value.prilozi[0].naknadnaPunomoc[0].putanja[0];
    zahtev.prilozi.naknadnaPunomoc.value =
      value.prilozi[0].naknadnaPunomoc[0].value[0] !== 'false';

    zahtev.prilozi.ranijaPunomoc.putanja =
      value.prilozi[0].ranijaPunomoc[0].putanja[0];
    zahtev.prilozi.ranijaPunomoc.value =
      value.prilozi[0].spisakRobe[0].value[0] !== 'false';

    console.log(zahtev);
    return zahtev;
  }
}
