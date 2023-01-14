import { Component } from '@angular/core';
import {A1Service} from "../../services/a1.service";
import {TVrsta} from "../../model/t-vrsta";
import {ZahtevZaAutorskaDelaDTO} from "../../model/zahtev-za-autorska-dela-d-t-o";
import {ZahtevZaPatentDTO} from "../../../p1/model/zahtev-za-patent";

@Component({
  selector: 'app-view-a1',
  templateUrl: './view-a1.component.html',
  styleUrls: ['./view-a1.component.css']
})
export class ViewA1Component {

  zahtev: ZahtevZaAutorskaDelaDTO | undefined;
  filtersLoaded: Promise<boolean> = Promise.resolve(false);


  constructor(private a1:A1Service) {
    let con = this;
    this.a1.getAutorskoPravo().subscribe((data)=>{
      con.zahtev = data;
      console.log(this.zahtev)
      this.filtersLoaded = Promise.resolve(true); // Setting the Promise as resolved after I have the needed data
    });
  }


}
