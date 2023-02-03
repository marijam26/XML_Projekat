import {Component, OnInit} from '@angular/core';
import {P1Service} from "../../p1/services/p1.service";
import {xml2js} from "xml-js";
import {ZahtevZaPatentDTO} from "../../p1/model/zahtev-za-patent";

@Component({
  selector: 'app-view-applications',
  templateUrl: './view-applications.component.html',
  styleUrls: ['./view-applications.component.css']
})
export class ViewApplicationsComponent implements OnInit{
  showDropDown:string = '';
  showModal = false;
  obradjeniZahtevi:string[] = ['1','2','3','4'];
  obradjeni:boolean = false;
  neobradjeniZahtavi:ZahtevZaPatentDTO[] = []

  constructor(private patentService:P1Service) {
  }

  ngOnInit(): void {
    this.patentService.getAllPatent().subscribe(
      (data)=> {
        var convert = require('xml-js');
        var result1 = convert.xml2json(data, {compact: true,spaces:4,trim:true});
        var res = JSON.parse(result1);
        console.log(res.zahtevi.zahtev)
        for(let zahev of res.zahtevi.zahtev){
          let z = this.patentService.mapXmlToPatent(zahev);
          this.neobradjeniZahtavi.push(z);
        }
        console.log(this.neobradjeniZahtavi)
      }
    )
  }

  clickShow(zahtev:string){
    if(this.showDropDown === zahtev){
      this.showDropDown = '';
    }else{
      this.showDropDown = zahtev;
    }
  }

  changeObradjeni(){
    this.obradjeni?this.obradjeni=false:this.obradjeni=true;
  }

  getPDF(id:string){
    window.open("http://localhost:9001/api/patent/downloadPDF/p"+id+".pdf");
  }

  getHTML(id:string){
    window.open("http://localhost:9001/api/patent/downloadHTML/p"+id+".html");
  }

  getRDF(id:string){
    window.open("http://localhost:9001/api/patent/downloadPDF/p"+id+".pdf");
  }

  getJSON(id:string){
    window.open("http://localhost:9001/api/patent/downloadPDF/p"+id+".pdf");
  }

}
