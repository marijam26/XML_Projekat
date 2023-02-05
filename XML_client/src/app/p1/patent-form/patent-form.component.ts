import {AfterViewInit, Component, OnInit} from '@angular/core';
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { ZahtevZaPatentDTO } from '../model/zahtev-za-patent';
import { RanijaPrijavaDTO } from '../model/ranija-prijava';
import { P1Service } from '../services/p1.service';
import { TLiceDTO } from '../../shared-models/t-lice-d-t-o';
import {XonomyService} from "../services/xonomy.service";

declare const Xonomy: any;
@Component({
  selector: 'app-patent-form',
  templateUrl: './patent-form.component.html',
  styleUrls: ['./patent-form.component.css'],
})
export class PatentFormComponent implements OnInit,AfterViewInit{
  podnosilacLice: string = 'Fizicko lice';
  punomocnikLice: string = 'Fizicko lice';
  anonimanPronalazac: boolean = false;
  dopunskaPrijava: boolean = false;
  datumPrvobitne: NgbDateStruct | undefined;
  datumRanije: NgbDateStruct | undefined;
  zatevZaPatent: ZahtevZaPatentDTO = new ZahtevZaPatentDTO();
  punomocnikZaPrijemPismena: boolean = true;
  zajednickiPredstavnik: boolean = false;
  papirnaForma: boolean = true;
  brojRanije: number = -1;
  oznakaRanije: string = '';
  podnosilacJePronalazac: boolean = false;

  constructor(private patentService: P1Service,private xonomyService:XonomyService) {}

  ngOnInit() {}

  ngAfterViewInit(): void {
    this.fizickoLiceXonomy();
  }

  changePodnosilacLice(event: any, lice: string) {
    this.podnosilacLice = lice;
    if(this.podnosilacLice === 'Pravno lice'){
      this.pravnoLiceXonomy();
    }else{
      this.fizickoLiceXonomy();
    }
  }

  fizickoLiceXonomy(){
    let element = document.getElementById("editor");
    let specification = this.xonomyService.FpodnosilacXonomy;
    let xmlString = '<Podnosilac_prijave></Podnosilac_prijave>';
    Xonomy.render(xmlString, element, specification);
  }

  pravnoLiceXonomy(){
    let element = document.getElementById("editor");
    let specification = this.xonomyService.PpodnosilacXonomy;
    let xmlString = '<Podnosilac_prijave></Podnosilac_prijave>';
    Xonomy.render(xmlString, element, specification);
  }

  changeAnoniman() {
    !this.anonimanPronalazac
      ? (this.anonimanPronalazac = true)
      : (this.anonimanPronalazac = false);
    if (this.anonimanPronalazac) {
      this.zatevZaPatent.pronalazac = new TLiceDTO();
    }
  }

  changePunomocnikLice(event: any, lice: string) {
    this.punomocnikLice = lice;
  }

  changeDopunskaPrijava() {
    !this.dopunskaPrijava
      ? (this.dopunskaPrijava = true)
      : (this.dopunskaPrijava = false);
  }

  changeZajednicki() {
    this.zajednickiPredstavnik
      ? (this.zajednickiPredstavnik = false)
      : (this.zajednickiPredstavnik = true);
  }

  posaljiZahtev() {
    this.punomocnikZaPrijemPismena
      ? (this.zatevZaPatent.vrstaPunomocnika = 'za_prijem_pismena')
      : 'za_zastupanje';
    this.zatevZaPatent.zajednickiPredstavnik = this.zajednickiPredstavnik;
    this.zatevZaPatent.osnovnaPrijava.datum =
      this.datumPrvobitne?.year +
      '-' +
      this.datumPrvobitne?.month +
      '-' +
      this.datumPrvobitne?.day;
    this.papirnaForma
      ? (this.zatevZaPatent.podaciODostavljanju.nacinDostavljanja =
          'papirna_forma')
      : (this.zatevZaPatent.podaciODostavljanju.nacinDostavljanja =
          'elektronska_forma');
    this.mapPodnosioca();

    this.patentService.savePatent(this.zatevZaPatent).subscribe();
  }

  mapPodnosioca(){
    let text = Xonomy.harvest();
    console.log(text)
    let t = text.replaceAll("xml:space='preserve'","");
    console.log(t)
    var convert = require('xml-js');
    var result1 = convert.xml2json(t, {compact: true,spaces:4,trim:true});
    var res = JSON.parse(result1);
    console.log(res)
    if(res.Podnosilac_prijave.Ime !== undefined){
      this.zatevZaPatent.podnosilacPrijave.ime = res.Podnosilac_prijave.Ime._text;
    }
    if(res.Podnosilac_prijave.Prezime !== undefined){
      this.zatevZaPatent.podnosilacPrijave.prezime = res.Podnosilac_prijave.Prezime._text;
    }
    if(res.Podnosilac_prijave.Poslovno_ime !== undefined){
      this.zatevZaPatent.podnosilacPrijave.poslovnoIme = res.Podnosilac_prijave.Poslovno_ime._text;
    }
    if(res.Podnosilac_prijave.Adresa !== undefined){
      if(res.Podnosilac_prijave.Adresa.Grad !== undefined){
        this.zatevZaPatent.podnosilacPrijave.adresa.grad = res.Podnosilac_prijave.Adresa.Grad._text;
      }
      if(res.Podnosilac_prijave.Adresa.Ulica !== undefined){
        this.zatevZaPatent.podnosilacPrijave.adresa.ulica = res.Podnosilac_prijave.Adresa.Ulica._text;
      }
      if(res.Podnosilac_prijave.Adresa.Broj !== undefined){
        this.zatevZaPatent.podnosilacPrijave.adresa.broj = res.Podnosilac_prijave.Adresa.Broj._text;
      }
      if(res.Podnosilac_prijave.Adresa.Postanski_broj !== undefined){
        this.zatevZaPatent.podnosilacPrijave.adresa.postanskiBroj = res.Podnosilac_prijave.Adresa.Postanski_broj._text;
      }
      if(res.Podnosilac_prijave.Adresa.Drzava !== undefined){
        this.zatevZaPatent.podnosilacPrijave.adresa.drzava = res.Podnosilac_prijave.Adresa.Drzava._text;
      }
    }
    if(res.Podnosilac_prijave.Kontakt !== undefined){
      if(res.Podnosilac_prijave.Kontakt.E_posta !== undefined){
        this.zatevZaPatent.podnosilacPrijave.kontakt.eposta = res.Podnosilac_prijave.Kontakt.E_posta._text;
      }
      if(res.Podnosilac_prijave.Kontakt.Faks !== undefined){
        this.zatevZaPatent.podnosilacPrijave.kontakt.faks = res.Podnosilac_prijave.Kontakt.Faks._text;
      }
      if(res.Podnosilac_prijave.Kontakt.Telefon !== undefined){
        this.zatevZaPatent.podnosilacPrijave.kontakt.telefon = res.Podnosilac_prijave.Kontakt.Telefon._text;
      }
    }

    console.log(this.zatevZaPatent.podnosilacPrijave)

  }

  changePunomocnik() {
    !this.punomocnikZaPrijemPismena
      ? (this.punomocnikZaPrijemPismena = true)
      : (this.punomocnikZaPrijemPismena = false);
  }

  changePapirnaForma() {
    !this.papirnaForma
      ? (this.papirnaForma = true)
      : (this.papirnaForma = false);
  }
  changePodnosilacPronalazac() {
    !this.podnosilacJePronalazac
      ? (this.podnosilacJePronalazac = true)
      : (this.podnosilacJePronalazac = false);
    if (this.podnosilacJePronalazac) {
      this.zatevZaPatent.pronalazac = this.zatevZaPatent.podnosilacPrijave;
    } else {
      this.zatevZaPatent.pronalazac = new TLiceDTO();
    }
  }

  dodajRanijuPrijavu() {
    console.log(this.datumRanije);
    if (
      this.brojRanije > 0 &&
      this.oznakaRanije != '' &&
      this.datumRanije != undefined
    ) {
      let datum =
        this.datumRanije?.year +
        '-' +
        this.datumRanije?.month +
        '-' +
        this.datumRanije?.day;
      this.zatevZaPatent.ranijaPrijava.push(
        new RanijaPrijavaDTO(this.brojRanije, datum, this.oznakaRanije)
      );
      alert('Dodato');
    } else {
      alert('Nije dodato');
    }
  }
}
