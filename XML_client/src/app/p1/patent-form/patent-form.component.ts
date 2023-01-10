import { Component } from '@angular/core';
import {NgbDateStruct} from "@ng-bootstrap/ng-bootstrap";

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
  datumRanije1: NgbDateStruct | undefined;
  datumRanije2: NgbDateStruct | undefined;


  changePodnosilacLice(event:any, lice:string){
    this.podnosilacLice = lice;
  }

  changeAnoniman(){
    !this.anonimanPronalazac?this.anonimanPronalazac=true:this.anonimanPronalazac=false;
  }

  changePunomocnikLice(event:any,lice:string){
    this.punomocnikLice = lice;
  }

  changeDopunskaPrijava(){
    !this.dopunskaPrijava?this.dopunskaPrijava=true:this.dopunskaPrijava=false;
  }
}
