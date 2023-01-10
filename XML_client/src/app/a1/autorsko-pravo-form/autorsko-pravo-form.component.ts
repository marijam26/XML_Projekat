import { Component } from '@angular/core';

@Component({
  selector: 'app-autorsko-pravo-form',
  templateUrl: './autorsko-pravo-form.component.html',
  styleUrls: ['./autorsko-pravo-form.component.css']
})
export class AutorskoPravoFormComponent {

  anonimanAutor:boolean = false;
  anonimanAutorIzvorno:boolean = false;
  zivAutor:boolean = false;
  zivAutorIzvorno:boolean = false;
  podnosilacJeAutor:boolean = false;
  podnosiPunomocnik:boolean = true;
  podnosilacLice: string = 'Fizicko lice';
  punomocnikLice: string = 'Fizicko lice'
  stvorenouRadnomOdnosu:boolean = false;
  prerada:boolean = false;
  drugaVrsta:boolean = true;
  drugaForma:boolean = true;

  changeAnoniman(){
    !this.anonimanAutor?this.anonimanAutor=true:this.anonimanAutor=false;
  }

  changePodnosilacLice(event:any, lice:string){
    this.podnosilacLice = lice;
  }

  changePunomocnikLice(event:any,lice:string){
    this.punomocnikLice = lice;
  }

  changePodnosilacJeAutor() {
    !this.podnosilacJeAutor?this.podnosilacJeAutor=true:this.podnosilacJeAutor=false;

  }

  changePodnosiPunomocnik() {
    !this.podnosiPunomocnik?this.podnosiPunomocnik=true:this.podnosiPunomocnik=false;
  }

  changePrerada() {
    !this.prerada?this.prerada=true:this.prerada=false;
  }

  changeStvorenouRadnomOdnosu() {
    !this.stvorenouRadnomOdnosu?this.stvorenouRadnomOdnosu=true:this.stvorenouRadnomOdnosu=false;
  }

  changeDrugaVrstaVisible() {
    this.drugaVrsta = true;
  }

  changeDrugaVrstaNonVisible() {
    this.drugaVrsta = false;
  }

  changeDrugaFormaVisible() {
    this.drugaForma = true;
  }

  changeDrugaFormaNonVisible() {
    this.drugaForma = false;
  }

  changeZiv() {
    !this.zivAutor?this.zivAutor=true:this.zivAutor=false;
  }

  changeAnonimanIzvornogDela() {
    !this.anonimanAutorIzvorno?this.anonimanAutorIzvorno=true:this.anonimanAutorIzvorno=false;
  }

  changeZivIzvornogDela() {
    !this.zivAutorIzvorno?this.zivAutorIzvorno=true:this.zivAutorIzvorno=false;
  }

  onFileSelected() {

  }
}
