import { Component } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { MetadataSearchDto } from '../../shared-models/metadataSearchDto';
import { Z1Service } from '../../z1/services/z1.service';
import * as xml2js from 'xml2js';

@Component({
  selector: 'app-advanced-search',
  templateUrl: './advanced-search.component.html',
  styleUrls: ['./advanced-search.component.css'],
})
export class AdvancedSearchComponent {
  operatori: string[] = [' '];
  metapodaci: string[] = [''];
  vredosti: string[] = [''];

  zahtevi: any[] = [];

  showDropDown: string = '';

  constructor(private toast: ToastrService, private zigService: Z1Service) {}

  dodajMetapodatak() {
    if (this.metapodaci.at(-1) !== '' && this.vredosti.at(-1) !== '') {
      this.operatori.push('||');
      this.metapodaci.push('');
      this.vredosti.push('');
    } else {
      this.toast.error('Unesite sve!');
    }
  }

  pretrazi() {
    this.zahtevi = [];

    //ukloni se poslednji ako je prazan
    if (this.metapodaci.at(-1) === '' || this.vredosti.at(-1) === '') {
      this.metapodaci.splice(this.metapodaci.length - 1, 1);
      this.vredosti.splice(this.vredosti.length - 1, 1);
      this.operatori.splice(this.operatori.length - 1, 1);
    }

    console.log(this.operatori);
    console.log(this.metapodaci);
    console.log(this.vredosti);

    let data: string = '';

    for (let v of this.vredosti) {
      data += v + ',';
    }
    data = data.slice(0, -1);
    data += '---';

    for (let v of this.metapodaci) {
      data += v + ',';
    }
    data = data.slice(0, -1);
    data += '---';

    for (let v of this.operatori) {
      if (v === ' ') {
        data += '*,';
      } else {
        data += v + ',';
      }
    }
    data = data.slice(0, -1);

    this.getZigZahtevi(data);
  }

  getZigZahtevi(data: String) {
    this.zigService.searchMetadata(data).subscribe({
      next: async (value) => {
        this.zahtevi = [];
        let result: any = await this.parseXml(value);
        if (result.List === '') {
          return;
        }
        for (let z of result.List.item) {
          let zahtev = this.zigService.mapXmlToZahtev(
            JSON.parse(JSON.stringify(z))
          );
          this.zahtevi.push(zahtev);
        }
      },
    });
  }

  async parseXml(xmlString: string) {
    return await new Promise((resolve, reject) =>
      xml2js.parseString(xmlString, (err, jsonData) => {
        if (err) {
          reject(err);
        }
        resolve(jsonData);
      })
    );
  }

  clickShow(zahtev: string) {
    if (this.showDropDown === zahtev) {
      this.showDropDown = '';
    } else {
      this.showDropDown = zahtev;
    }
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
        'http://localhost:9001/api/patent/downloadPDF/' + id + '.rdf'
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
}
