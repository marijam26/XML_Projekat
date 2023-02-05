import { Component } from '@angular/core';
import { ZahtevZaZigDTO } from '../../model/zahtev-za-zig';
import { Z1Service } from '../../services/z1.service';

@Component({
  selector: 'app-zig-form',
  templateUrl: './zig-form.component.html',
  styleUrls: ['./zig-form.component.css'],
})
export class ZigFormComponent {
  zahtevZaZig: ZahtevZaZigDTO = new ZahtevZaZigDTO();
  podnosilacLice: string = 'Fizicko lice';
  punomocnikLice: string = 'Fizicko lice';
  klasa: number = 1;
  boja: string = '';

  constructor(private zigService: Z1Service) {}

  save() {
    console.log('pozvano');
    if (this.zahtevZaZig.zig.vrstaZnaka != 'OSTALO') {
      this.zahtevZaZig.zig.ostalo = '';
    }
    this.zahtevZaZig.takse.ukupno =
      this.zahtevZaZig.takse.osnovnaTaksa +
      this.zahtevZaZig.takse.grafickoResenje +
      this.zahtevZaZig.takse.klase;
    this.zigService.save(this.zahtevZaZig).subscribe();
  }

  dodajKlasu() {
    if (!this.zahtevZaZig.zig.klase.includes(this.klasa)) {
      this.zahtevZaZig.zig.klase.push(this.klasa);
      this.zahtevZaZig.takse.brojKlasa += 1;
      alert('Dodato');
    } else {
      alert('Nije dodato');
    }
    this.klasa = 1;
  }

  removePrimerak() {
    this.zahtevZaZig.prilozi.primerakZnaka.value = false;
    this.zahtevZaZig.prilozi.primerakZnaka.putanja = '';
  }

  addPrimerak($event: any) {
    let file = $event.target.files[0];
    this.zahtevZaZig.prilozi.primerakZnaka.value = true;
    this.zahtevZaZig.prilozi.primerakZnaka.putanja = file.name;
  }

  removeSpisak() {
    this.zahtevZaZig.prilozi.spisakRobe.value = false;
    this.zahtevZaZig.prilozi.spisakRobe.putanja = '';
  }

  addSpisak($event: any) {
    let file = $event.target.files[0];
    this.zahtevZaZig.prilozi.spisakRobe.value = true;
    this.zahtevZaZig.prilozi.spisakRobe.putanja = file.name;
  }

  removePunomoc() {
    this.zahtevZaZig.prilozi.punomoc.value = false;
    this.zahtevZaZig.prilozi.punomoc.putanja = '';
  }

  addPunomoc($event: any) {
    let file = $event.target.files[0];
    this.zahtevZaZig.prilozi.punomoc.value = true;
    this.zahtevZaZig.prilozi.punomoc.putanja = file.name;
  }

  removeOpstiAkt() {
    this.zahtevZaZig.prilozi.opstiAkt.value = false;
    this.zahtevZaZig.prilozi.opstiAkt.putanja = '';
  }

  addOpstiAkt($event: any) {
    let file = $event.target.files[0];
    this.zahtevZaZig.prilozi.opstiAkt.value = true;
    this.zahtevZaZig.prilozi.opstiAkt.putanja = file.name;
  }

  removeDokazPrvenstvo() {
    this.zahtevZaZig.prilozi.dokazOPravuPrvenstva.value = false;
    this.zahtevZaZig.prilozi.dokazOPravuPrvenstva.putanja = '';
  }

  addDokazPrvenstvo($event: any) {
    let file = $event.target.files[0];
    this.zahtevZaZig.prilozi.dokazOPravuPrvenstva.value = true;
    this.zahtevZaZig.prilozi.dokazOPravuPrvenstva.putanja = file.name;
  }

  removeDokazTakse() {
    this.zahtevZaZig.prilozi.dokazOUplati.value = false;
    this.zahtevZaZig.prilozi.dokazOUplati.putanja = '';
  }

  addDokazTakse($event: any) {
    let file = $event.target.files[0];
    this.zahtevZaZig.prilozi.dokazOUplati.value = true;
    this.zahtevZaZig.prilozi.dokazOUplati.putanja = file.name;
  }

  dodajBoju() {
    this.zahtevZaZig.zig.boje.push(this.boja);
    this.boja = '';
    alert('Dodato');
  }
}
