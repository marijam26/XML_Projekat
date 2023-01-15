import { Component, Input } from '@angular/core';
import { A1Service } from '../../services/a1.service';
import { ZahtevZaAutorskaDelaDTO } from '../../model/zahtev-za-autorska-dela-d-t-o';
import { TVrsta } from '../../model/t-vrsta';
import { TFormatZapisa } from '../../model/t-format-zapisa';
import { AdresaDTO } from '../../../shared-models/adresaDTO';

@Component({
  selector: 'app-autorsko-pravo-form',
  templateUrl: './autorsko-pravo-form.component.html',
  styleUrls: ['./autorsko-pravo-form.component.css'],
})
export class AutorskoPravoFormComponent {
  //@Input()
  zahtev: ZahtevZaAutorskaDelaDTO = new ZahtevZaAutorskaDelaDTO();

  @Input()
  upotreba: string = 'read';

  anonimanAutor: boolean = false;
  anonimanAutorIzvorno: boolean = false;
  zivAutor: boolean = false;
  zivAutorIzvorno: boolean = false;
  podnosilacJeAutor: boolean = false;
  podnosiPunomocnik: boolean = true;
  podnosilacLice: string = 'fizicko';
  punomocnikLice: string = 'fizicko';
  stvorenouRadnomOdnosu: boolean = false;
  prerada: boolean = false;
  drugaVrsta: boolean = false;
  drugaForma: boolean = false;
  opcija: string = 'fizicko';
  vrsta: string = 'KNJIZEVNO_DELO';
  format: string = 'OPTICKI_DISK';

  constructor(private a1: A1Service) {
    let con = this;
    if (this.upotreba === 'read') {
      this.a1.getAutorskoPravo().subscribe((data) => {
        con.zahtev = data;
        console.log(data);
        con.setValues();
      });
    } else {
      con.setValues();
    }
  }

  private setValues() {
    this.prerada = this.zahtev?.autorskoDelo.prerada;
    this.stvorenouRadnomOdnosu = this.zahtev.autorskoDelo.stvorenoURadnomOdnosu;
    if (this.zahtev.autor.ime === '' && this.zahtev.autor.prezime === '')
      this.anonimanAutor = true;
    if (
      this.zahtev.autorskoDelo.izvornoDelo === null ||
      (this.zahtev.autorskoDelo.izvornoDelo.autor.ime === '' &&
        this.zahtev.autorskoDelo.izvornoDelo.autor.prezime === '')
    )
      this.anonimanAutorIzvorno = true;
    this.podnosilacJeAutor = this.zahtev.podnosilacJeAutor;
    if (this.zahtev.punomocnik != undefined) this.podnosiPunomocnik = true;
    if (
      this.zahtev.autor.godinaSmrti === 0 ||
      this.zahtev.autor.godinaSmrti == undefined
    )
      this.zivAutor = true;
    if (
      this.zahtev.autorskoDelo.izvornoDelo != null &&
      (this.zahtev.autorskoDelo?.izvornoDelo.autor?.godinaSmrti === 0 ||
        this.zahtev.autorskoDelo?.izvornoDelo.autor?.godinaSmrti == undefined)
    )
      this.zivAutorIzvorno = true;
    if (this.upotreba === 'read') {
      this.setVrstaFormat();
    }
    if (this.zahtev?.podnosilacPrijave.poslovnoIme) {
      this.opcija = 'pravno';
      this.podnosilacLice = 'pravno';
    }
  }

  private setVrstaFormat() {
    if (this.zahtev.autorskoDelo.vrsta.value.toLowerCase() != 'ostalo') {
      const but = document.getElementById(
        this.zahtev.autorskoDelo.vrsta.value.toLowerCase().split('_')[0].trim()
      ) as HTMLInputElement;
      but.checked = true;
    } else {
      const but = document.getElementById('drugo') as HTMLInputElement;
      but.checked = true;
    }
    if (this.zahtev.autorskoDelo.formatZapisa.value.toLowerCase() != 'ostalo') {
      const but = document.getElementById(
        this.zahtev.autorskoDelo.formatZapisa.value
          .toLowerCase()
          .split('_')[0]
          .trim()
      ) as HTMLInputElement;
      but.checked = true;
    } else {
      const but = document.getElementById('ostalo') as HTMLInputElement;
      but.checked = true;
    }
    if (!this.zivAutorIzvorno) {
      this.zahtev.autorskoDelo.izvornoDelo.autor.adresa = new AdresaDTO();
    }
  }

  changeAnoniman() {
    !this.anonimanAutor
      ? (this.anonimanAutor = true)
      : (this.anonimanAutor = false);
    if (this.anonimanAutor) {
      this.zahtev.autor.ime = '';
      this.zahtev.autor.prezime = '';
    }
  }

  changePodnosilacLice(event: any, lice: string) {
    this.podnosilacLice = lice;
  }

  changePunomocnikLice(event: any, lice: string) {
    this.punomocnikLice = lice;
  }

  changePodnosilacJeAutor() {
    !this.podnosilacJeAutor
      ? (this.podnosilacJeAutor = true)
      : (this.podnosilacJeAutor = false);
  }

  changePodnosiPunomocnik() {
    !this.podnosiPunomocnik
      ? (this.podnosiPunomocnik = true)
      : (this.podnosiPunomocnik = false);
  }

  changePrerada() {
    !this.prerada ? (this.prerada = true) : (this.prerada = false);
    this.zahtev.autorskoDelo.prerada = this.prerada;
  }

  changeStvorenouRadnomOdnosu() {
    !this.stvorenouRadnomOdnosu
      ? (this.stvorenouRadnomOdnosu = true)
      : (this.stvorenouRadnomOdnosu = false);
    this.zahtev.autorskoDelo.stvorenoURadnomOdnosu = this.stvorenouRadnomOdnosu;
  }

  changeDrugaVrstaVisible(event: Event) {
    const index = Object.values(TVrsta).indexOf(
      (event.target as HTMLInputElement).id as unknown as TVrsta
    );
    const key = Object.keys(TVrsta)[index];
    this.drugaVrsta = true;
    this.zahtev.autorskoDelo.vrsta.value = key;
  }

  changeDrugaVrstaNonVisible(event: Event) {
    const index = Object.values(TVrsta).indexOf(
      (event.target as HTMLInputElement).id as unknown as TVrsta
    );
    this.zahtev.autorskoDelo.vrsta.value = Object.keys(TVrsta)[index];
    this.zahtev.autorskoDelo.vrsta.ostalaVrstaDela = '';
    this.drugaVrsta = false;
  }

  changeDrugaFormaVisible(event: Event) {
    const index = Object.values(TFormatZapisa).indexOf(
      (event.target as HTMLInputElement).id as unknown as TFormatZapisa
    );
    this.zahtev.autorskoDelo.formatZapisa.value =
      Object.keys(TFormatZapisa)[index];
    this.drugaForma = true;
  }

  changeDrugaFormaNonVisible(event: Event) {
    const index = Object.values(TFormatZapisa).indexOf(
      (event.target as HTMLInputElement).id as unknown as TFormatZapisa
    );
    this.zahtev.autorskoDelo.formatZapisa.value =
      Object.keys(TFormatZapisa)[index];
    this.zahtev.autorskoDelo.formatZapisa.ostaliFormat = '';
    this.drugaForma = false;
  }

  changeZiv() {
    !this.zivAutor ? (this.zivAutor = true) : (this.zivAutor = false);
    if (!this.zivAutor) {
      this.zahtev.autor.godinaSmrti = 0;
      this.zahtev.autor.adresa = new AdresaDTO();
    }
  }

  changeAnonimanIzvornogDela() {
    !this.anonimanAutorIzvorno
      ? (this.anonimanAutorIzvorno = true)
      : (this.anonimanAutorIzvorno = false);
    if (this.anonimanAutorIzvorno) {
      this.zahtev.autorskoDelo.izvornoDelo.autor.ime = '';
      this.zahtev.autorskoDelo.izvornoDelo.autor.prezime = '';
    }
  }

  changeZivIzvornogDela() {
    !this.zivAutorIzvorno
      ? (this.zivAutorIzvorno = true)
      : (this.zivAutorIzvorno = false);
    if (!this.zivAutorIzvorno) {
      this.zahtev.autorskoDelo.izvornoDelo.autor.godinaSmrti = 0;
      this.zahtev.autorskoDelo.izvornoDelo.autor.adresa = new AdresaDTO();
    }
  }

  onFileSelectedOpis(event: any) {
    let selectedFile = event.target.files[0];
    this.zahtev.prilozi.opisDela = { putanja: selectedFile.name, value: true };
  }

  removeOpis() {
    if (document.getElementById('opis') != null) {
      (document.getElementById('opis') as HTMLInputElement).value = '';
    }
    this.zahtev.prilozi.opisDela = { putanja: '', value: false };
  }

  onFileSelectedPrimer(event: any) {
    let selectedFile = event.target.files[0];
    this.zahtev.prilozi.primerDela = {
      putanja: selectedFile.name,
      value: true,
    };
    console.log(this.zahtev);
  }

  removePrimer() {
    if (document.getElementById('primer') != null) {
      (document.getElementById('primer') as HTMLInputElement).value = '';
    }
    this.zahtev.prilozi.primerDela = { putanja: '', value: false };
  }

  sendRequest() {
    console.log(this.zahtev);
    if (this.zahtev != undefined)
      this.a1.saveAutorskoPravo(this.zahtev).subscribe();
  }
}
