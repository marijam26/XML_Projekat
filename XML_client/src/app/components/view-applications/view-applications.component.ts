import { Component, OnInit } from '@angular/core';
import { P1Service } from '../../p1/services/p1.service';
import { ZahtevZaPatentDTO } from '../../p1/model/zahtev-za-patent';
import { ResenjeDTO } from '../../shared-models/resenjeDTO';
import { ToastrService } from 'ngx-toastr';
import { A1Service } from '../../a1/services/a1.service';
import { ZahtevZaAutorskaDelaDTO } from '../../a1/model/zahtev-za-autorska-dela-d-t-o';
import { Korisnik } from '../../shared-models/korisnik';
import { Z1Service } from '../../z1/services/z1.service';
import { ZahtevZaZigDTO } from '../../z1/model/zahtev-za-zig';

@Component({
  selector: 'app-view-applications',
  templateUrl: './view-applications.component.html',
  styleUrls: ['./view-applications.component.css'],
})
export class ViewApplicationsComponent implements OnInit {
  showDropDown: string = '';
  showModal = false;
  obradjeniZahtevi: any = [];
  obradjeni: boolean = false;
  neobradjeniZahtavi: any = [];
  zahevZaOdbijanje:
    | ZahtevZaPatentDTO
    | ZahtevZaAutorskaDelaDTO
    | ZahtevZaZigDTO = new ZahtevZaPatentDTO();
  zahevZaReference: any;
  obrazlozenje: string = '';
  showModalReference: boolean = false;
  ulogovani: Korisnik | null = new Korisnik();
  searchField: string = '';

  constructor(
    private patentService: P1Service,
    private toast: ToastrService,
    private autorskoService: A1Service,
    private zigService: Z1Service
  ) {}

  ngOnInit(): void {
    let a = sessionStorage.getItem('logged');
    if (typeof a != 'undefined' && a) {
      this.ulogovani = JSON.parse(a);
      console.log(this.ulogovani);
    }

    // this.getPatents();
    this.getZigovi();
    //this.getAutorskaDela();
  }

  getZigovi() {
    this.zigService.getZahtevi().subscribe({
      next: async (data) => {
        let convert = require('xml-js');
        let result1 = convert.xml2json(data, {
          compact: true,
          spaces: 4,
          trim: true,
        });
        let res = JSON.parse(result1);
        console.log(res.zahtevi.zahtev);
        if (
          Array.isArray(res.zahtevi.zahtev) &&
          res.zahtevi.zahtev != undefined
        ) {
          for (let zahev of res.zahtevi.zahtev) {
            let z = this.zigService.mapXmlToZahtev(zahev);
            this.neobradjeniZahtavi.push(z);
          }
        } else if (res.zahtevi.zahtev != undefined) {
          let z = this.zigService.mapXmlToZahtev(res.zahtevi.zahtev);
          this.neobradjeniZahtavi.push(z);
        }
      },
    });

    this.zigService.getOdobreni().subscribe({
      next: async (value) => {
        let convert = require('xml-js');
        let result1 = convert.xml2json(value, {
          compact: true,
          spaces: 4,
          trim: true,
        });
        let res = JSON.parse(result1);
        console.log(res.zahtevi.zahtev);
        if (
          Array.isArray(res.zahtevi.zahtev) &&
          res.zahtevi.zahtev != undefined
        ) {
          for (let zahev of res.zahtevi.zahtev) {
            let z = this.zigService.mapXmlToZahtev(zahev);
            this.obradjeniZahtevi.push(z);
          }
        } else if (res.zahtevi.zahtev != undefined) {
          let z = this.zigService.mapXmlToZahtev(res.zahtevi.zahtev);
          this.obradjeniZahtevi.push(z);
        }
      },
    });
  }

  getAutorskaDela() {
    this.autorskoService.getAllZahtevi().subscribe((data) => {
      var convert = require('xml-js');
      var result1 = convert.xml2json(data, {
        compact: true,
        spaces: 4,
        trim: true,
      });
      var res = JSON.parse(result1);
      console.log(res.zahtevi.zahtev);
      if (Array.isArray(res.zahtevi.zahtev)) {
        for (let zahev of res.zahtevi.zahtev) {
          let z = this.autorskoService.mapXmlToDelo(zahev);
          this.neobradjeniZahtavi.push(z);
        }
      } else {
        let z = this.autorskoService.mapXmlToDelo(res.zahtevi.zahtev);
        this.neobradjeniZahtavi.push(z);
      }
    });

    this.autorskoService.getAllApproved().subscribe((data) => {
      var convert = require('xml-js');
      var result1 = convert.xml2json(data, {
        compact: true,
        spaces: 4,
        trim: true,
      });
      var res = JSON.parse(result1);
      if (Array.isArray(res.zahtevi.zahtev)) {
        for (let zahev of res.zahtevi.zahtev) {
          let z = this.autorskoService.mapXmlToDelo(zahev);
          this.obradjeniZahtevi.push(z);
        }
      } else {
        let z = this.autorskoService.mapXmlToDelo(res.zahtevi.zahtev);
        this.obradjeniZahtevi.push(z);
      }
    });
  }

  getPatents() {
    this.patentService.getAllZahetvPatent().subscribe((data) => {
      var convert = require('xml-js');
      var result1 = convert.xml2json(data, {
        compact: true,
        spaces: 4,
        trim: true,
      });
      var res = JSON.parse(result1);
      console.log(res.zahtevi.zahtev);
      if (Array.isArray(res.zahtevi.zahtev)) {
        for (let zahev of res.zahtevi.zahtev) {
          let z = this.patentService.mapXmlToPatent(zahev);
          this.neobradjeniZahtavi.push(z);
        }
      } else {
        let z = this.patentService.mapXmlToPatent(res.zahtevi.zahtev);
        this.neobradjeniZahtavi.push(z);
      }
    });

    this.patentService.getAllOdobrenePatent().subscribe((data) => {
      var convert = require('xml-js');
      var result1 = convert.xml2json(data, {
        compact: true,
        spaces: 4,
        trim: true,
      });
      var res = JSON.parse(result1);
      if (Array.isArray(res.zahtevi.zahtev)) {
        for (let zahev of res.zahtevi.zahtev) {
          let z = this.patentService.mapXmlToPatent(zahev);
          this.obradjeniZahtevi.push(z);
        }
      } else {
        let z = this.patentService.mapXmlToPatent(res.zahtevi.zahtev);
        this.obradjeniZahtevi.push(z);
      }
    });
  }

  clickShow(zahtev: string) {
    if (this.showDropDown === zahtev) {
      this.showDropDown = '';
    } else {
      this.showDropDown = zahtev;
    }
  }

  changeObradjeni() {
    this.obradjeni ? (this.obradjeni = false) : (this.obradjeni = true);
  }

  getPDF(id: string) {
    if (id.includes('A')) {
      window.open(
        'http://localhost:9002/api/autorskoPravo/downloadPDF/' + id + '.pdf'
      );
    } else if (id.includes('P')) {
      window.open(
        'http://localhost:9001/api/patent/downloadPDF/' + id + '.pdf'
      );
    } else if (id.includes('Z')) {
      window.open('http://localhost:9003/api/zig/downloadPDF/' + id + '.pdf');
    }
  }

  getHTML(id: string) {
    if (id.includes('A')) {
      window.open(
        'http://localhost:9002/api/autorskoPravo/downloadHTML/' + id + '.html'
      );
    } else if (id.includes('P')) {
      window.open(
        'http://localhost:9001/api/patent/downloadHTML/' + id + '.html'
      );
    } else if (id.includes('Z')) {
      window.open('http://localhost:9003/api/zig/downloadHTML/' + id + '.html');
    }
  }

  getRDF(id: string) {
    if (id.includes('A')) {
      window.open(
        'http://localhost:9002/api/autorskoPravo/downloadPDF/' + id + '.rdf'
      );
    } else if (id.includes('P')) {
      window.open(
        'http://localhost:9001/api/patent/downloadRDF/' + id + '.rdf'
      );
    } else if (id.includes('Z')) {
      window.open('http://localhost:9003/api/zig/downloadPDF/' + id + '.rdf');
    }
  }

  getJSON(id: string) {
    if (id.includes('A')) {
      window.open(
        'http://localhost:9002/api/autorskoPravo/downloadPDF/' + id + '.rdf'
      );
    } else if (id.includes('P')) {
      window.open(
        'http://localhost:9001/api/patent/downloadPDF/' + id + '.rdf'
      );
    } else if (id.includes('Z')) {
      window.open('http://localhost:9003/api/zig/downloadPDF/' + id + '.rdf');
    }
  }

  clickOdbij(zahtev: ZahtevZaPatentDTO | ZahtevZaAutorskaDelaDTO) {
    this.showModal = true;
    this.zahevZaOdbijanje = zahtev;
  }

  prihvatiZahtev(
    zahtev: ZahtevZaPatentDTO | ZahtevZaAutorskaDelaDTO | ZahtevZaZigDTO
  ) {
    let resenje = new ResenjeDTO(
      this.ulogovani?.ime,
      this.ulogovani?.prezime,
      zahtev.id.toString(),
      true,
      ''
    );
    if (zahtev instanceof ZahtevZaAutorskaDelaDTO) {
      this.autorskoService.saveResenje(resenje).subscribe(
        () => {
          this.toast.success('Uspesno prihvacen zahtev!');
          let index = this.neobradjeniZahtavi.indexOf(zahtev);
          this.neobradjeniZahtavi.splice(index, 1);
          this.obradjeniZahtevi.push(zahtev);
        },
        (err) => this.toast.error('Greska u slanju!')
      );
    } else if (zahtev instanceof ZahtevZaPatentDTO) {
      this.patentService.saveResenje(resenje).subscribe(
        () => {
          this.toast.success('Uspesno prihvacen zahtev!');
          let index = this.neobradjeniZahtavi.indexOf(zahtev);
          this.neobradjeniZahtavi.splice(index, 1);
          this.obradjeniZahtevi.push(zahtev);
        },
        (err) => this.toast.error('Greska u slanju!')
      );
    } else {
      this.zigService.saveResenje(resenje).subscribe(
        () => {
          this.toast.success('Uspesno prihvacen zahtev!');
          let index = this.neobradjeniZahtavi.indexOf(zahtev);
          this.neobradjeniZahtavi.splice(index, 1);
          this.obradjeniZahtevi.push(zahtev);
        },
        (err) => this.toast.error('Greska u slanju!')
      );
    }
  }

  odbijZahtev() {
    let resenje = new ResenjeDTO(
      this.ulogovani?.ime,
      this.ulogovani?.prezime,
      this.zahevZaOdbijanje.id.toString(),
      false,
      this.obrazlozenje
    );
    if (this.zahevZaOdbijanje instanceof ZahtevZaAutorskaDelaDTO) {
      this.autorskoService.saveResenje(resenje).subscribe(
        () => {
          this.toast.success('Uspesno odbijen zahtev!');
          this.showModal = false;
          let index = this.neobradjeniZahtavi.indexOf(this.zahevZaOdbijanje);
          this.neobradjeniZahtavi.splice(index, 1);
        },
        (err) => this.toast.error('Greska u slanju!')
      );
    } else if (this.zahevZaOdbijanje instanceof ZahtevZaPatentDTO) {
      this.patentService.saveResenje(resenje).subscribe(
        () => {
          this.toast.success('Uspesno odbijen zahtev!');
          this.showModal = false;
          let index = this.neobradjeniZahtavi.indexOf(this.zahevZaOdbijanje);
          this.neobradjeniZahtavi.splice(index, 1);
        },
        (err) => this.toast.error('Greska u slanju!')
      );
    } else {
      this.zigService.saveResenje(resenje).subscribe(
        () => {
          this.toast.success('Uspesno odbijen zahtev!');
          this.showModal = false;
          let index = this.neobradjeniZahtavi.indexOf(this.zahevZaOdbijanje);
          this.neobradjeniZahtavi.splice(index, 1);
        },
        (err) => this.toast.error('Greska u slanju!')
      );
    }
  }

  naprednaPretraga() {
    window.location.href = '/advancedSearch';
  }

  viewReferences(zahtev: any) {
    this.zahevZaReference = zahtev;
    this.showModalReference = true;
  }

  getPrimerFile(id: any, putanja: any) {
    let filePath = id + ':' + putanja;
    if (id.includes('A')) {
      window.open(
        'http://localhost:9002/api/autorskoPravo/downloadPrimer/' + filePath
      );
    }
  }

  getOpisFile(id: any, putanja: any) {
    let filePath = id + ':' + putanja;
    if (id.includes('A')) {
      window.open(
        'http://localhost:9002/api/autorskoPravo/downloadOpis/' + filePath
      );
    }
  }

  getPrimerakFajla(id: string, putanja: string) {
    let filePath = id + ':' + putanja;
    if (id.includes('Z')) {
      window.open('http://localhost:9003/api/zig/download/' + filePath);
    }
  }
}
