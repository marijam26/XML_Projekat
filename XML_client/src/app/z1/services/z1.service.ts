import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ZahtevZaZigDTO } from '../model/zahtev-za-zig';
import * as JsonToXML from 'js2xmlparser';
import { ResenjeDTO } from '../../shared-models/resenjeDTO';
import { IzvestajDTO } from '../../shared-models/izvestajDTO';

@Injectable({
  providedIn: 'root',
})
export class Z1Service {
  constructor(private _http: HttpClient) {}
  url = 'http://localhost:9003/api/zig';

  save(zahtev: ZahtevZaZigDTO) {
    const xml = JsonToXML.parse('zahtevZaZigDTO', zahtev);
    const saveUrl = this.url + '/save';
    return this._http.post<any>(saveUrl, xml, {
      headers: new HttpHeaders({
        'Content-Type': 'application/xml',
        'Access-Control-Allow-Origin': '*',
        responseType: 'text',
      }),
    });
  }

  get() {
    const newUrl = this.url + '/' + 2; //document id
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

  search(value: string) {
    return this._http.get(this.url + '/search/' + value, {
      headers: new HttpHeaders().set('Content-Type', 'application/xml'),
      responseType: 'text',
    });
  }

  searchMetadata(value: String) {
    return this._http.get(this.url + '/searchMetadata/' + value, {
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

  generateReport(izvestaj: IzvestajDTO) {
    const xmlZahtev = JsonToXML.parse('izvestajDTO', izvestaj);
    const newUrl = this.url + '/report';
    return this._http.post<any>(newUrl, xmlZahtev, {
      headers: new HttpHeaders({
        'Content-Type': 'application/xml',
        'Access-Control-Allow-Origin': '*',
        responseType: 'text',
      }),
    });
  }

  mapXmlToZahtev(data: any) {
    let zahtev: ZahtevZaZigDTO = new ZahtevZaZigDTO();
    zahtev.id = data['_attributes']['Id'];
    zahtev.brojPrijave = data['_attributes']['Broj_prijave'];
    zahtev.datumPodnosenja = data['_attributes']['Datum_podnosenja'];
    zahtev.zig.opis = data['ns2:Zig']['ns2:Opis'];
    if (data['ns2:Zig']['ns2:Ostalo'] !== undefined) {
      zahtev.zig.ostalo = data['ns2:Zig']['ns3:Ostalo'];
    }
    zahtev.zig.prevod = data['ns2:Zig']['ns3:Prevod'];
    zahtev.zig.transliteracija = data['ns2:Zig']['ns3:Transliteracija'];

    if (Array.isArray(data['ns2:Zig']['ns2:Boja'])) {
      for (let b of data['ns2:Zig']['ns2:Boja']) {
        console.log(b);
        zahtev.zig.boje.push(b);
      }
    } else {
      zahtev.zig.boje.push(data['ns2:Zig']['ns2:Boja']._text);
    }

    if (Array.isArray(data['ns2:Zig']['ns3:Klasa_robe'])) {
      for (let k of data['ns2:Zig']['ns3:Klasa_robe']) {
        console.log(k);
        zahtev.zig.klase.push(k);
      }
    } else {
      zahtev.zig.klase.push(data['ns2:Zig']['ns2:Klasa_robe']._text);
    }

    zahtev.takse.osnovnaTaksa = data['ns2:Takse']['ns2:Osnovna_taksa'];
    zahtev.takse.ukupno = data['ns2:Takse']['ns2:Ukupno'];
    zahtev.takse.grafickoResenje = data['ns2:Takse']['ns2:Graficko_resenje'];
    zahtev.takse.klase = data['ns2:Takse']['ns2:Klase'];
    zahtev.takse.brojKlasa = data['ns2:Takse']['ns2:Broj_klasa'];

    if (
      data['ns3:Podnosilac_prijave']['_attributes']['xsi:type'] ===
      'ns3:TFizicko_lice'
    ) {
      zahtev.podnosilacPrijave.ime =
        data['ns3:Podnosilac_prijave']['ns3:Ime']._text;
      zahtev.podnosilacPrijave.prezime =
        data['ns3:Podnosilac_prijave']['ns3:Prezime']._text;
    } else {
      zahtev.podnosilacPrijave.poslovnoIme =
        data['ns3:Podnosilac_prijave']['ns3:Poslovno_ime']._text;
    }
    zahtev.podnosilacPrijave.kontakt.eposta =
      data['ns3:Podnosilac_prijave']['ns3:Kontakt']['ns3:E_posta']._text;
    zahtev.podnosilacPrijave.kontakt.faks =
      data['ns3:Podnosilac_prijave']['ns3:Kontakt']['ns3:Faks']._text;
    zahtev.podnosilacPrijave.kontakt.telefon =
      data['ns3:Podnosilac_prijave']['ns3:Kontakt']['ns3:Telefon']._text;

    zahtev.podnosilacPrijave.adresa.ulica =
      data['ns3:Podnosilac_prijave']['ns3:Adresa']['ns3:Ulica']._text;
    zahtev.podnosilacPrijave.adresa.broj =
      data['ns3:Podnosilac_prijave']['ns3:Adresa']['ns3:Broj']._text;
    zahtev.podnosilacPrijave.adresa.postanskiBroj =
      data['ns3:Podnosilac_prijave']['ns3:Adresa']['ns3:Postanski_broj']._text;
    zahtev.podnosilacPrijave.adresa.grad =
      data['ns3:Podnosilac_prijave']['ns3:Adresa']['ns3:Grad']._text;

    if (
      data['ns3:Punomocnik']['_attributes']['xsi:type'] === 'ns3:TFizicko_lice'
    ) {
      zahtev.punomocnik.ime = data['ns3:Punomocnik']['ns3:Ime']._text;
      zahtev.punomocnik.prezime = data['ns3:Punomocnik']['ns3:Prezime']._text;
    } else {
      zahtev.punomocnik.poslovnoIme =
        data['ns3:Punomocnik']['ns3:Poslovno_ime']._text;
    }
    zahtev.punomocnik.drzavljanstvo =
      data['ns3:Punomocnik']['ns3:Drzavljanstvo']._text;
    zahtev.punomocnik.kontakt.eposta =
      data['ns3:Punomocnik']['ns3:Kontakt']['ns3:E_posta']._text;
    zahtev.punomocnik.kontakt.faks =
      data['ns3:Punomocnik']['ns3:Kontakt']['ns3:Faks']._text;
    zahtev.punomocnik.kontakt.telefon =
      data['ns3:Punomocnik']['ns3:Kontakt']['ns3:Telefon']._text;

    zahtev.punomocnik.adresa.ulica =
      data['ns3:Punomocnik']['ns3:Adresa']['ns3:Ulica']._text;
    zahtev.punomocnik.adresa.broj =
      data['ns3:Punomocnik']['ns3:Adresa']['ns3:Broj']._text;
    zahtev.punomocnik.adresa.postanskiBroj =
      data['ns3:Punomocnik']['ns3:Adresa']['ns3:Postanski_broj']._text;
    zahtev.punomocnik.adresa.grad =
      data['ns3:Punomocnik']['ns3:Adresa']['ns3:Grad']._text;

    if (data['ns2:Prilozi'] != undefined) {
      if (data['ns2:Prilozi']['ns2:Opis_dela'] != undefined) {
        zahtev.prilozi.dokazOPravuPrvenstva.putanja =
          data['ns2:Prilozi']['ns2:Dokaz_o_pravu_prvenstva']['_attributes'][
            'Putanja'
          ];
        zahtev.prilozi.dokazOPravuPrvenstva.value =
          zahtev.prilozi.dokazOPravuPrvenstva.putanja !== 'False';
      }
      if (data['ns2:Prilozi']['ns2:Primer_dela'] != undefined) {
        zahtev.prilozi.dokazOUplati.putanja =
          data['ns2:Prilozi']['ns2:Primer_dela']['_attributes']['Putanja'];
        zahtev.prilozi.dokazOUplati.value =
          zahtev.prilozi.dokazOUplati.putanja !== 'False';
      }
      if (data['ns2:Prilozi']['ns2:Primer_dela'] != undefined) {
        zahtev.prilozi.opstiAkt.putanja =
          data['ns2:Prilozi']['ns2:Primer_dela']['_attributes']['Putanja'];
        zahtev.prilozi.opstiAkt.value =
          zahtev.prilozi.opstiAkt.putanja !== 'False';
      }
      if (data['ns2:Prilozi']['ns2:Primer_dela'] != undefined) {
        zahtev.prilozi.punomoc.putanja =
          data['ns2:Prilozi']['ns2:Primer_dela']['_attributes']['Putanja'];
        zahtev.prilozi.punomoc.value =
          zahtev.prilozi.punomoc.putanja !== 'False';
      }
      if (data['ns2:Prilozi']['ns2:Primer_dela'] != undefined) {
        zahtev.prilozi.primerakZnaka.putanja =
          data['ns2:Prilozi']['ns2:Primer_dela']['_attributes']['Putanja'];
        zahtev.prilozi.primerakZnaka.value =
          zahtev.prilozi.primerakZnaka.putanja !== 'False';
      }
      if (data['ns2:Prilozi']['ns2:Primer_dela'] != undefined) {
        zahtev.prilozi.naknadnaPunomoc.putanja =
          data['ns2:Prilozi']['ns2:Primer_dela']['_attributes']['Putanja'];
        zahtev.prilozi.naknadnaPunomoc.value =
          zahtev.prilozi.naknadnaPunomoc.putanja !== 'False';
      }
      if (data['ns2:Prilozi']['ns2:Primer_dela'] != undefined) {
        zahtev.prilozi.ranijaPunomoc.putanja =
          data['ns2:Prilozi']['ns2:Primer_dela']['_attributes']['Putanja'];
        zahtev.prilozi.ranijaPunomoc.value =
          zahtev.prilozi.ranijaPunomoc.putanja !== 'False';
      }

      if (data['ns2:Prilozi']['ns2:Primer_dela'] != undefined) {
        zahtev.prilozi.spisakRobe.putanja =
          data['ns2:Prilozi']['ns2:Primer_dela']['_attributes']['Putanja'];
        zahtev.prilozi.spisakRobe.value =
          zahtev.prilozi.spisakRobe.putanja !== 'False';
      }
    }

    console.log(zahtev);
    return zahtev;
  }
}
