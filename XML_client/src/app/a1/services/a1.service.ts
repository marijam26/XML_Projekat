import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ZahtevZaAutorskaDelaDTO} from "../model/zahtev-za-autorska-dela-d-t-o";
import * as JsonToXML from "js2xmlparser";
import {ResenjeDTO} from "../../shared-models/resenjeDTO";
import {ImageDTO} from "../../shared-models/data.model";
import {IzvestajDTO} from "../../shared-models/izvestajDTO";

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


  getAllZahtevi() {
    return this._http.get(this.a1Url + 'getAllZahteve', {
      headers: new HttpHeaders().set('Content-Type', 'application/xml'),
      responseType: 'text'
    });
  }

  getAllApproved() {
    return this._http.get(this.a1Url + 'getAllOdobrene', {
      headers: new HttpHeaders().set('Content-Type', 'application/xml'),
      responseType: 'text'
    });
  }

  mapXmlToDelo(data: any) {
    console.log("mapiraa")
    let zahtev = new ZahtevZaAutorskaDelaDTO();
    zahtev.id = data['_attributes']['Id'];
    zahtev.broj = data['_attributes']['Broj'];
    zahtev.datumPodnosenja = data['_attributes']['Datum_podnosenja'];
    zahtev.podnosilacJeAutor = data['_attributes']['Podnosilac_je_autor'];

    if(data['ns3:Podnosilac_prijave']['_attributes']['xsi:type'] === 'ns3:TFizicko_lice'){
      zahtev.podnosilacPrijave.ime = data['ns3:Podnosilac_prijave']['ns3:Ime']._text;
      zahtev.podnosilacPrijave.prezime = data['ns3:Podnosilac_prijave']['ns3:Prezime']._text;
    }else{
      zahtev.podnosilacPrijave.poslovnoIme = data['ns3:Podnosilac_prijave']['ns3:Poslovno_ime']._text;
    }
    zahtev.podnosilacPrijave.kontakt.eposta = data['ns3:Podnosilac_prijave']['ns3:Kontakt']['ns3:E_posta']._text;
    zahtev.podnosilacPrijave.kontakt.faks = data['ns3:Podnosilac_prijave']['ns3:Kontakt']['ns3:Faks']._text;
    zahtev.podnosilacPrijave.kontakt.telefon = data['ns3:Podnosilac_prijave']['ns3:Kontakt']['ns3:Telefon']._text;

    zahtev.podnosilacPrijave.adresa.ulica = data['ns3:Podnosilac_prijave']['ns3:Adresa']['ns3:Ulica']._text;
    zahtev.podnosilacPrijave.adresa.broj = data['ns3:Podnosilac_prijave']['ns3:Adresa']['ns3:Broj']._text;
    zahtev.podnosilacPrijave.adresa.postanskiBroj = data['ns3:Podnosilac_prijave']['ns3:Adresa']['ns3:Postanski_broj']._text;
    zahtev.podnosilacPrijave.adresa.grad = data['ns3:Podnosilac_prijave']['ns3:Adresa']['ns3:Grad']._text;

    if(data['ns3:Punomocnik']['_attributes']['xsi:type'] === 'ns3:TFizicko_lice'){
      zahtev.punomocnik.ime = data['ns3:Punomocnik']['ns3:Ime']._text;
      zahtev.punomocnik.prezime = data['ns3:Punomocnik']['ns3:Prezime']._text;
    }else{
      zahtev.punomocnik.poslovnoIme = data['ns3:Punomocnik']['ns3:Poslovno_ime']._text;
    }
    zahtev.punomocnik.drzavljanstvo = data['ns3:Punomocnik']['ns3:Drzavljanstvo']._text;
    zahtev.punomocnik.kontakt.eposta = data['ns3:Punomocnik']['ns3:Kontakt']['ns3:E_posta']._text;
    zahtev.punomocnik.kontakt.faks = data['ns3:Punomocnik']['ns3:Kontakt']['ns3:Faks']._text;
    zahtev.punomocnik.kontakt.telefon = data['ns3:Punomocnik']['ns3:Kontakt']['ns3:Telefon']._text;

    zahtev.punomocnik.adresa.ulica = data['ns3:Punomocnik']['ns3:Adresa']['ns3:Ulica']._text;
    zahtev.punomocnik.adresa.broj = data['ns3:Punomocnik']['ns3:Adresa']['ns3:Broj']._text;
    zahtev.punomocnik.adresa.postanskiBroj = data['ns3:Punomocnik']['ns3:Adresa']['ns3:Postanski_broj']._text;
    zahtev.punomocnik.adresa.grad = data['ns3:Punomocnik']['ns3:Adresa']['ns3:Grad']._text;

    if(data['ns2:Autor'] != undefined){
      if(data['ns2:Autor']['_attributes']['xsi:type'] === 'ns2:TZiv_autor'){
        zahtev.autor.drzavljanstvo = data['ns2:Autor']['ns2:Drzavljanstvo']._text;
        zahtev.autor.pseudonim = data['ns2:Autor']['ns2:Pseudonim']._text;
        zahtev.autor.adresa.ulica = data['ns2:Autor']['ns3:Adresa']['ns3:Ulica']._text;
        zahtev.autor.adresa.broj = data['ns2:Autor']['ns3:Adresa']['ns3:Broj']._text;
        zahtev.autor.adresa.postanskiBroj = data['ns2:Autor']['ns3:Adresa']['ns3:Postanski_broj']._text;
        zahtev.autor.adresa.grad = data['ns2:Autor']['ns3:Adresa']['ns3:Grad']._text;

      }else{
        zahtev.autor.godinaSmrti = data['ns2:Autor']['ns2:Godina_smrti']._text;
      }
      zahtev.autor.ime = data['ns2:Autor']['ns2:Ime']._text;
      zahtev.autor.prezime = data['ns2:Autor']['ns2:Prezime']._text;

      zahtev.autorskoDelo.naslov = data['ns2:Autorsko_delo']['ns2:Naslov']._text;
      zahtev.autorskoDelo.nacinKoriscenja = data['ns2:Autorsko_delo']['ns2:Nacin_koriscenja']._text;
      zahtev.autorskoDelo.vrsta = data['ns2:Autorsko_delo']['ns2:Naslov']._text;
      zahtev.autorskoDelo.naslov = data['ns2:Autorsko_delo']['ns2:Naslov']._text;
      zahtev.autorskoDelo.naslov = data['ns2:Autorsko_delo']['ns2:Naslov']._text;
      if(data['_attributes']['Autorsko_delo'] != undefined) {
        zahtev.autorskoDelo.stvorenoURadnomOdnosu = data['_attributes']['Autorsko_delo']['ns2:Stvoreno_u_radnom_odnosu']._text;
      }
      if(data['_attributes']['Autorsko_delo'] != undefined)
        zahtev.autorskoDelo.prerada = data['_attributes']['Autorsko_delo']['ns2:Prerada']._text;
      if(data['_attributes']['Autorsko_delo'] != undefined)
        zahtev.autorskoDelo.vrsta = data['_attributes']['Autorsko_delo']['ns2:Vrsta']._text;
      if(data['_attributes']['Autorsko_delo'] != undefined)
        zahtev.autorskoDelo.formatZapisa = data['_attributes']['Autorsko_delo']['ns2:Format_zapisa']._text;

    if(data['ns2:Prilozi'] != undefined){
      if(data['ns2:Prilozi']['ns2:Opis_dela'] != undefined){
        zahtev.prilozi.opisDela.putanja = data['ns2:Prilozi']['ns2:Opis_dela']['_attributes']['Putanja']
        zahtev.prilozi.opisDela.value = zahtev.prilozi.opisDela.putanja !== "False";
      }
      if(data['ns2:Prilozi']['ns2:Primer_dela'] != undefined){
        zahtev.prilozi.primerDela.putanja = data['ns2:Prilozi']['ns2:Primer_dela']['_attributes']['Putanja']
        zahtev.prilozi.primerDela.value = zahtev.prilozi.primerDela.putanja !== "False";
      }

    }
  }

    console.log(zahtev)
    return zahtev;
  }

  saveResenje(resenje: ResenjeDTO) {
    const resenjeXML = JsonToXML.parse('resenjeDTO', resenje);
    const newUrl = this.a1Url + '/saveResenje';
    return this._http.post<any>(newUrl, resenjeXML, {
      headers: new HttpHeaders({
        'Content-Type': 'application/xml',
        'Access-Control-Allow-Origin': '*',
        responseType: 'text',
      }),
    });
  }
  HTTPOptionsForBlob: Object = {
    headers: new HttpHeaders({
      'Content-Type': 'multipart/form-data;boundary=abv',
    }),
  };
  HTTPOptionsForBlob1: Object = {
    responseType: 'blob',
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  saveData(image:ImageDTO) {
    let tr = new FormData()
    const newUrl = this.a1Url + 'addImage';
    tr.append('prilog', image.data);
    return this._http.post<any>(newUrl, image,this.HTTPOptionsForBlob1);
  }

  savePdf(id:any) {
    const newUrl = this.a1Url + 'getPdf/'+id;
    return this._http.get<any>(newUrl);
  }

  searchMetadata(value: String) {
    return this._http.get(this.a1Url + 'searchMetadata/' + value, {
      headers: new HttpHeaders().set('Content-Type', 'application/xml'),
      responseType: 'text',
    });
  }


  generateReport(izvestaj: IzvestajDTO) {
    const xmlZahtev = JsonToXML.parse('izvestajDTO', izvestaj);
    const newUrl = this.a1Url + '/report';
    return this._http.post<any>(newUrl, xmlZahtev, {
      headers: new HttpHeaders({
        'Content-Type': 'application/xml',
        'Access-Control-Allow-Origin': '*',
        responseType: 'text',
      }),
    });

  }
}
