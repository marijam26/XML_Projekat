import {Component, Input} from '@angular/core';
import {A1Service} from "../../services/a1.service";
import {ZahtevZaAutorskaDelaDTO} from "../../model/zahtev-za-autorska-dela-d-t-o";
import {TVrsta} from "../../model/t-vrsta";
import {TFormatZapisa} from "../../model/t-format-zapisa";
import {TAutor} from "../../model/t-autor";
import {AdresaDTO} from "../../../shared-models/adresaDTO";
import {TLiceDTO} from "../../../shared-models/t-lice-d-t-o";
import {KontaktDTO} from "../../../shared-models/kontaktDTO";
import {AutorskoDeloDTO} from "../../model/autorsko-delo-d-t-o";
import {TPrilog} from "../../../shared-models/t-prilog";


@Component({
  selector: 'app-autorsko-pravo-form',
  templateUrl: './autorsko-pravo-form.component.html',
  styleUrls: ['./autorsko-pravo-form.component.css']
})
export class AutorskoPravoFormComponent {

  @Input()
  zahtev: ZahtevZaAutorskaDelaDTO | undefined;

  @Input()
  upotreba: string = 'read';

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
  drugaVrsta:boolean = false;
  drugaForma:boolean = false;
  opcija: string = "fizicko";
  vrsta: string = "KNJIZEVNO_DELO";
  format: string ="OPTICKI_DISK";
  opis: TPrilog = {value:false,putanja:''};
  primer: TPrilog = {value:false,putanja:''};

  constructor(private a1:A1Service) {
    console.log(this.zahtev)
    this.setValues()
  }

  private setValues() {
    this.prerada = !(!this.zahtev?.autorskoDelo.prerada)
    this.stvorenouRadnomOdnosu = !(!this.zahtev?.autorskoDelo.stvorenoURadnomOdnosu)
    if (this.zahtev?.autor.ime == '')
      this.anonimanAutor = true
    this.podnosilacJeAutor = (!this.zahtev?.podnosilacJeAutor)
    if (this.zahtev?.punomocnik != undefined)
      this.podnosiPunomocnik = true
    if (!this.zahtev?.autor?.godinaSmrti)
      this.zivAutor = true
    if (!this.zahtev?.autorskoDelo?.izvornoDelo?.autor?.godinaSmrti)
      this.zivAutorIzvorno = true
    if (this.zahtev?.autorskoDelo.vrsta.value !== TVrsta.OSTALO && this.zahtev != undefined) {
      const but = document.getElementById(this.zahtev?.autorskoDelo.vrsta.value.toLowerCase().split('_')[0]) as HTMLInputElement
      but.checked = true
    }
    if(!(!this.zahtev?.podnosilacPrijave.poslovnoIme)){
      this.opcija = "pravno"
    }
  }


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
    if (this.zahtev != undefined)
      this.zahtev.autorskoDelo.prerada = this.prerada;
  }

  changeStvorenouRadnomOdnosu() {
    !this.stvorenouRadnomOdnosu?this.stvorenouRadnomOdnosu=true:this.stvorenouRadnomOdnosu=false;
    if (this.zahtev != undefined)
      this.zahtev.autorskoDelo.stvorenoURadnomOdnosu = this.stvorenouRadnomOdnosu;
  }

  changeDrugaVrstaVisible(event: Event) {
    const index = Object.values(TVrsta).indexOf((event.target as HTMLInputElement).id as unknown as TVrsta);
    const key = Object.keys(TVrsta)[index];
    this.drugaVrsta = true;
    if (this.zahtev != undefined)
      this.zahtev.autorskoDelo.vrsta.value = key
    this.vrsta = key
  }

  changeDrugaVrstaNonVisible(event: Event) {
    const index = Object.values(TVrsta).indexOf((event.target as HTMLInputElement).id as unknown as TVrsta);
    const key = Object.keys(TVrsta)[index];
    if (this.zahtev != undefined)
      this.zahtev.autorskoDelo.vrsta.value = key
    this.vrsta = key
    this.drugaVrsta = false;
  }

  changeDrugaFormaVisible(event: Event) {
    const index = Object.values(TFormatZapisa).indexOf((event.target as HTMLInputElement).id as unknown as TFormatZapisa);
    const key = Object.keys(TFormatZapisa)[index];
    if (this.zahtev != undefined)
      this.zahtev.autorskoDelo.formatZapisa.value = key
    this.format = key
    this.drugaForma = true;
  }

  changeDrugaFormaNonVisible(event: Event) {
    const index = Object.values(TFormatZapisa).indexOf((event.target as HTMLInputElement).id as unknown as TFormatZapisa);
    const key = Object.keys(TFormatZapisa)[index];
    if (this.zahtev != undefined)
      this.zahtev.autorskoDelo.formatZapisa.value = key
    this.format = key
    this.drugaForma = false;
  }

  changeZiv() {
    !this.zivAutor?this.zivAutor = true:this.zivAutor=false;
  }

  changeAnonimanIzvornogDela() {
    !this.anonimanAutorIzvorno?this.anonimanAutorIzvorno=true:this.anonimanAutorIzvorno=false;
  }

  changeZivIzvornogDela() {
    !this.zivAutorIzvorno?this.zivAutorIzvorno=true:this.zivAutorIzvorno=false;
  }

  onFileSelectedOpis(event:any) {
    let selectedFile = event.target.files[0];
    if (this.zahtev != undefined)
      this.zahtev.prilozi.opisDela = {'putanja':selectedFile.name,'value':true}
    this.opis = {'putanja':selectedFile.name,'value':true}
  }

  removeOpis(){
    if( document.getElementById('opis') != null) {
      (document.getElementById('opis') as HTMLInputElement).value = "";
    }
    if (this.zahtev != undefined)
      this.zahtev.prilozi.opisDela = {'putanja':'','value':false}
    this.opis = {value:false,putanja:''};
  }

  onFileSelectedPrimer(event:any) {
    let selectedFile = event.target.files[0];
    if (this.zahtev != undefined)
      this.zahtev.prilozi.primerDela= {'putanja':selectedFile.name,'value':true}
    this.primer = {'putanja':selectedFile.name,'value':true}
    console.log(this.zahtev)
  }

  removePrimer() {
    if( document.getElementById('primer') != null) {
      (document.getElementById('primer') as HTMLInputElement).value = "";
    }
    if (this.zahtev != undefined)
      this.zahtev.prilozi.primerDela = {'putanja':'','value':false}
    this.primer = {value:false,putanja:''};
  }

  sendRequest() {
    this.formRequest()
    if (this.zahtev != undefined)
      this.a1.saveAutorskoPravo(this.zahtev).subscribe();
  }

  formRequest() {
    let adresaPodnosioca: AdresaDTO =
      {
        broj: (<HTMLInputElement>document.getElementById('broj_podnosioca'))?.value as unknown as number,
        drzava: (<HTMLInputElement>document.getElementById('drzava_podnosioca'))?.value,
        grad: (<HTMLInputElement>document.getElementById('grad_podnosioca'))?.value,
        postanskiBroj: (<HTMLInputElement>document.getElementById('pos_br_podnosioca'))?.value as unknown as number,
        ulica: (<HTMLInputElement>document.getElementById('ulica_podnosioca'))?.value
      }

    let kon: KontaktDTO = {
      e_posta: (<HTMLInputElement>document.getElementById('eposta_podnosioca'))?.value,
      telefon: (<HTMLInputElement>document.getElementById('telefon_podnosioca'))?.value,
      faks: ''
    }


    let podnosilac: TLiceDTO =
      {
        ime: (<HTMLInputElement>document.getElementById('ime_podnosioca'))?.value,
        prezime: (<HTMLInputElement>document.getElementById('prezime_podnosioca'))?.value,
        drzavljanstvo: (<HTMLInputElement>document.getElementById('drzavljanstvo_podnosioca'))?.value,
        poslovnoIme: (<HTMLInputElement>document.getElementById('poslovno_ime_podnosioca'))?.value,
        adresa: adresaPodnosioca,
        kontakt: kon,
      };

    let adresaPunomocnika: AdresaDTO =
      {
        broj: (<HTMLInputElement>document.getElementById('broj_punomocnika'))?.value as unknown as number,
        drzava: (<HTMLInputElement>document.getElementById('drzava_punomocnika'))?.value,
        grad: (<HTMLInputElement>document.getElementById('grad_punomocnika'))?.value,
        postanskiBroj: (<HTMLInputElement>document.getElementById('pos_br_punomocnika'))?.value as unknown as number,
        ulica: (<HTMLInputElement>document.getElementById('ulica_punomocnika'))?.value
      }

    let punomocnik:TLiceDTO =
      {
        ime:(<HTMLInputElement>document.getElementById('ime_punomocnika'))?.value,
        prezime:(<HTMLInputElement>document.getElementById('prezime_punomocnika'))?.value,
        drzavljanstvo:'',
        poslovnoIme:'',
        adresa:adresaPunomocnika,
        kontakt:{faks:'',telefon:'',e_posta:''},
      } ;

    let adresaAutora:AdresaDTO = {
      broj: (<HTMLInputElement>document.getElementById('broj_autora'))?.value as unknown as number,
      drzava: (<HTMLInputElement>document.getElementById('drzava_autora'))?.value,
      grad: (<HTMLInputElement>document.getElementById('grad_autora'))?.value,
      postanskiBroj: (<HTMLInputElement>document.getElementById('pos_br_autora'))?.value as unknown as number,
      ulica: (<HTMLInputElement>document.getElementById('ulica_autora'))?.value

    }
    let autor: TAutor;
    if (this.zahtev?.podnosilacJeAutor){
      autor = {
        ime: podnosilac.ime,
        prezime: podnosilac.prezime,
        drzavljanstvo: podnosilac.drzavljanstvo,
        adresa: podnosilac.adresa,
        godinaSmrti: -1,
        pseudonim:''}
      }
      else{
        let ime = (<HTMLInputElement>document.getElementById('ime_autora'))?.value;
        if (!this.zivAutor){
          ime = (<HTMLInputElement>document.getElementById('ime_autora_rip'))?.value;
        }
        let prezime = (<HTMLInputElement>document.getElementById('prezime_autora'))?.value;
        if (!this.zivAutor){
          prezime = (<HTMLInputElement>document.getElementById('prezime_autora_rip'))?.value;
        }
      autor = {
        ime: ime ,
        prezime: prezime,
        drzavljanstvo: (<HTMLInputElement>document.getElementById('drzavljanstvo_autora'))?.value,
        adresa: adresaAutora,
        godinaSmrti: (<HTMLInputElement>document.getElementById('godina_smrti'))?.value as unknown as number,
        pseudonim: ''
      }

      }

    let ime_autora_izvorno = (<HTMLInputElement>document.getElementById('ime_autora_izvorno'))?.value;
    if (!this.zivAutorIzvorno){
      ime_autora_izvorno = (<HTMLInputElement>document.getElementById('ime_autora_rip_izvorno'))?.value;
    }
    let prezime_autora_izvorno = (<HTMLInputElement>document.getElementById('prezime_autora_izvorno'))?.value;
    if (!this.zivAutorIzvorno){
      prezime_autora_izvorno = (<HTMLInputElement>document.getElementById('prezime_autora_rip_izvorno'))?.value;
    }

    let adresaAutoraIzvorno:AdresaDTO = {
      broj: (<HTMLInputElement>document.getElementById('broj_autora_izvorno'))?.value as unknown as number,
      drzava: (<HTMLInputElement>document.getElementById('drzava_autora_izvorno'))?.value,
      grad: (<HTMLInputElement>document.getElementById('grad_autora_izvorno'))?.value,
      postanskiBroj: (<HTMLInputElement>document.getElementById('pos_br_autora_izvorno'))?.value as unknown as number,
      ulica: (<HTMLInputElement>document.getElementById('ulica_autora_izvorno'))?.value

    }
    let izvorniAutor:TAutor = {
      ime: ime_autora_izvorno ,
      prezime: prezime_autora_izvorno,
      drzavljanstvo: (<HTMLInputElement>document.getElementById('drzavljanstvo_autora_izvorno'))?.value,
      adresa: adresaAutoraIzvorno,
      godinaSmrti: (<HTMLInputElement>document.getElementById('godina_smrti_izvorno'))?.value as unknown as number,
      pseudonim: ''
      }

    let izvorno= {
      stvorenoURadnomOdnosu:false,
      prerada:false,
      naslov: (<HTMLInputElement>document.getElementById('naslov_prerade'))?.value,
      nacinKoriscenja: '',
      vrsta: {value:'',
        ostalaVrstaDela:''
      },
      formatZapisa:{value:'',
        ostaliFormat:''
      },
      izvornoDelo:undefined,
      autor: izvorniAutor,
      anonimanAutor:this.anonimanAutorIzvorno

    }
    let autorskoDelo:AutorskoDeloDTO= {
        stvorenoURadnomOdnosu:this.stvorenouRadnomOdnosu,
      prerada:this.prerada,
      naslov: (<HTMLInputElement>document.getElementById('naslov_dela'))?.value,
      nacinKoriscenja: (<HTMLInputElement>document.getElementById('nacin_koristenja'))?.value,
      vrsta: {value:this.vrsta,
              ostalaVrstaDela:(<HTMLInputElement>document.getElementById('vrsta_dela'))?.value
              },
      formatZapisa:{value:this.format,
                  ostaliFormat:(<HTMLInputElement>document.getElementById('forma_dela'))?.value
                  },
      izvornoDelo:izvorno,
    }
    this.zahtev = {
      broj: -1,
      podnosilacPrijave:podnosilac,
      podnosilacJeAutor: this.podnosilacJeAutor,
      datumPodnosenja: '',
      punomocnik:punomocnik,
      autor: autor,
      autorskoDelo:autorskoDelo,
      prilozi:{
        opisDela:this.opis,
        primerDela:this.primer
      }
    }

  }
}
