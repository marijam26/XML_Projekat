import { Component, OnInit } from '@angular/core';
import { Z1Service } from '../../z1/services/z1.service';

@Component({
  selector: 'app-search-applications',
  templateUrl: './search-applications.component.html',
  styleUrls: ['./search-applications.component.css'],
})
export class SearchApplicationsComponent {
  zahtevi: any[] = [];
  searchField: string = '';
  showDropDown: string = '';

  constructor(private zigService: Z1Service) {}

  advancedSearch() {
    window.location.href = '/advancedSearch';
  }

  search() {
    this.searchZig();
  }

  searchZig() {
    this.zigService.search(this.searchField).subscribe({
      next: async (value) => {
        let convert = require('xml-js');
        let result1 = convert.xml2json(value, {
          compact: true,
          spaces: 4,
          trim: true,
        });
        let res = JSON.parse(result1);
        if (
          Array.isArray(res.zahtevi.zahtev) &&
          res.zahtevi.zahtev != undefined
        ) {
          for (let zahev of res.zahtevi.zahtev) {
            let z = this.zigService.mapXmlToZahtev(zahev);
            this.zahtevi.push(z);
          }
        } else if (res.zahtevi.zahtev != undefined) {
          let z = this.zigService.mapXmlToZahtev(res.zahtevi.zahtev);
          this.zahtevi.push(z);
        }
      },
    });
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
