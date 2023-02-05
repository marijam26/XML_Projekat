import { Component, OnInit } from '@angular/core';
import { Z1Service } from '../../z1/services/z1.service';
import * as xml2js from 'xml2js';

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
