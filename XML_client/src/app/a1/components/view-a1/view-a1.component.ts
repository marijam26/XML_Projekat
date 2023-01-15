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


  public upotreba: string = 'read'


  constructor() {}


}
