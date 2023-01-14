import {Component} from '@angular/core';
import {NgbDateStruct} from "@ng-bootstrap/ng-bootstrap";
import {ZahtevZaPatentDTO} from "../model/zahtev-za-patent";
import {RanijaPrijavaDTO} from "../model/ranija-prijava";
import {P1Service} from "../services/p1.service";
import {TLiceDTO} from "../../shared-models/t-lice-d-t-o";

@Component({
  selector: 'app-patent-form',
  templateUrl: './patent-form.component.html',
  styleUrls: ['./patent-form.component.css']
})
export class PatentFormComponent {
  podnosilacLice: string = 'Fizicko lice';
  punomocnikLice: string = 'Fizicko lice'
  anonimanPronalazac:boolean = false;
  dopunskaPrijava:boolean = false;
  datumPrvobitne: NgbDateStruct | undefined;
  datumRanije: NgbDateStruct | undefined;
  zatevZaPatent: ZahtevZaPatentDTO = new ZahtevZaPatentDTO();
  punomocnikZaPrijemPismena: boolean = true;
  zajednickiPredstavnik: boolean = false;
  papirnaForma: boolean = true;
  brojRanije: number = -1;
  oznakaRanije: string = '';
  podnosilacJePronalazac: boolean = false;

  constructor(private patentService:P1Service) {}

  ngOnInit(){}


  changePodnosilacLice(event:any, lice:string){
    this.podnosilacLice = lice;
  }

  changeAnoniman(){
    !this.anonimanPronalazac?this.anonimanPronalazac=true:this.anonimanPronalazac=false;
    if (this.anonimanPronalazac){
      this.zatevZaPatent.pronalazac = new TLiceDTO();
    }
  }

  changePunomocnikLice(event:any,lice:string){
    this.punomocnikLice = lice;
  }

  changeDopunskaPrijava(){
    !this.dopunskaPrijava?this.dopunskaPrijava=true:this.dopunskaPrijava=false;
  }

  changeZajednicki(){
    this.zajednickiPredstavnik?this.zajednickiPredstavnik=false:this.zajednickiPredstavnik=true;
  }

  posaljiZahtev(){
    this.punomocnikZaPrijemPismena?this.zatevZaPatent.vrstaPunomocnika='za_prijem_pismena':'za_zastupanje';
    this.zatevZaPatent.zajednickiPredstavnik = this.zajednickiPredstavnik;
    this.zatevZaPatent.osnovnaPrijava.datum = this.datumPrvobitne?.year + '-' + this.datumPrvobitne?.month + '-' + this.datumPrvobitne?.day;
    this.papirnaForma?this.zatevZaPatent.podaciODostavljanju.nacinDostavljanja = 'papirna_forma':'elektronska_forma';
    this.patentService.savePatent(this.zatevZaPatent).subscribe();
  }

  changePunomocnik(){
    !this.punomocnikZaPrijemPismena?this.punomocnikZaPrijemPismena=true:this.punomocnikZaPrijemPismena=false;
  }

  changePapirnaForma(){
    !this.papirnaForma?this.papirnaForma=true:this.papirnaForma=false;
  }
  changePodnosilacPronalazac(){
    !this.podnosilacJePronalazac?this.podnosilacJePronalazac=true:this.podnosilacJePronalazac=false;
    if(this.podnosilacJePronalazac){
      this.zatevZaPatent.pronalazac = this.zatevZaPatent.podnosilacPrijave;
    }else {
      this.zatevZaPatent.pronalazac = new TLiceDTO();
    }
  }

  dodajRanijuPrijavu(){
    console.log(this.datumRanije)
    if (this.brojRanije > 0 && this.oznakaRanije != '' && this.datumRanije != undefined){
      let datum = this.datumRanije?.year + '-' + this.datumRanije?.month + '-' + this.datumRanije?.day;
      this.zatevZaPatent.ranijaPrijava.push(new RanijaPrijavaDTO(this.brojRanije,datum,this.oznakaRanije))
      alert("Dodato");
    }else{
      alert("Nije dodato")
    }
  }

}
