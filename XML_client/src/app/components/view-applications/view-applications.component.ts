import {Component, OnInit} from '@angular/core';
import {P1Service} from "../../p1/services/p1.service";
import {xml2js} from "xml-js";
import {ZahtevZaPatentDTO} from "../../p1/model/zahtev-za-patent";
import {ResenjeDTO} from "../../shared-models/resenjeDTO";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-view-applications',
  templateUrl: './view-applications.component.html',
  styleUrls: ['./view-applications.component.css']
})
export class ViewApplicationsComponent implements OnInit{
  showDropDown:string = '';
  showModal = false;
  obradjeniZahtevi:ZahtevZaPatentDTO[] = [];
  obradjeni:boolean = false;
  neobradjeniZahtavi:ZahtevZaPatentDTO[] = [];
  zahevZaOdbijanje:ZahtevZaPatentDTO = new ZahtevZaPatentDTO();
  obrazlozenje:string = '';

  constructor(private patentService:P1Service,private toast:ToastrService) {
  }

  ngOnInit(): void {
    // this.patentService.getAllPatent().subscribe(
    //   (data)=> {
    //     var convert = require('xml-js');
    //     var result1 = convert.xml2json(data, {compact: true,spaces:4,trim:true});
    //     var res = JSON.parse(result1);
    //     console.log(res.zahtevi.zahtev)
    //     for(let zahev of res.zahtevi.zahtev){
    //       let z = this.patentService.mapXmlToPatent(zahev);
    //       if(z.brojPrijave == undefined){
    //         this.neobradjeniZahtavi.push(z);
    //
    //       }else {
    //         this.neobradjeniZahtavi.push(z);
    //
    //         this.obradjeniZahtevi.push(z);
    //       }
    //
    //     }
    //     console.log(this.neobradjeniZahtavi)
    //   }
    // )

    this.patentService.getAllZahetvPatent().subscribe(
      (data)=> {
        var convert = require('xml-js');
        var result1 = convert.xml2json(data, {compact: true,spaces:4,trim:true});
        var res = JSON.parse(result1);
        console.log(res.zahtevi.zahtev)
        if(Array.isArray(res.zahtevi.zahtev)){
          for(let zahev of res.zahtevi.zahtev){
            let z = this.patentService.mapXmlToPatent(zahev);
            this.neobradjeniZahtavi.push(z);
          }
        }else{
          let z = this.patentService.mapXmlToPatent(res.zahtevi.zahtev);
          this.neobradjeniZahtavi.push(z);
        }
      }
    )

    this.patentService.getAllOdobrenePatent().subscribe(
      (data)=> {
        var convert = require('xml-js');
        var result1 = convert.xml2json(data, {compact: true,spaces:4,trim:true});
        var res = JSON.parse(result1);
        if(Array.isArray(res.zahtevi.zahtev)){
          for(let zahev of res.zahtevi.zahtev){
            let z = this.patentService.mapXmlToPatent(zahev);
            this.obradjeniZahtevi.push(z);
          }
        }else{
          let z = this.patentService.mapXmlToPatent(res.zahtevi.zahtev);
          this.obradjeniZahtevi.push(z);
        }
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
    window.open("http://localhost:9001/api/patent/downloadPDF/"+id+".pdf");
  }

  getHTML(id:string){
    window.open("http://localhost:9001/api/patent/downloadHTML/"+id+".html");
  }

  getRDF(id:string){
    window.open("http://localhost:9001/api/patent/downloadPDF/"+id+".rdf");
  }

  getJSON(id:string){
    window.open("http://localhost:9001/api/patent/downloadPDF/"+id+".rdf");
  }

  clickOdbij(zahtev:ZahtevZaPatentDTO){
    this.showModal=true
    this.zahevZaOdbijanje = zahtev;
  }

  prihvatiZahtev(zahtev:ZahtevZaPatentDTO){
    let resenje = new ResenjeDTO('Pera','Peric',zahtev.id.toString(),true,'');
    this.patentService.saveResenje(resenje).subscribe(
      ()=> {
        this.toast.success('Uspesno prihvacen zahtev!')
        let index = this.neobradjeniZahtavi.indexOf(zahtev);
        this.neobradjeniZahtavi.splice(index,1);
        this.obradjeniZahtevi.push(zahtev);
      },
      (err)=>this.toast.error('Greska u slanju!')

    )
  }

  odbijZahtev(){
    let resenje = new ResenjeDTO('Pera','Peric',this.zahevZaOdbijanje.id.toString(),false,this.obrazlozenje);
    this.patentService.saveResenje(resenje).subscribe(
      ()=>{
        this.toast.success('Uspesno odbijen zahtev!')
        this.showModal= false;
        let index = this.neobradjeniZahtavi.indexOf(this.zahevZaOdbijanje);
        this.neobradjeniZahtavi.splice(index,1);
      },
      (err)=>this.toast.error('Greska u slanju!')
    )
  }

  naprednaPretraga(){
    window.location.href = '/advancedSearch';
  }

}
