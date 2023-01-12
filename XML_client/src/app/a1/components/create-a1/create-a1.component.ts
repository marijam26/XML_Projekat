import { Component } from '@angular/core';
import {ZahtevZaAutorskaDelaDTO} from "../../model/zahtev-za-autorska-dela-d-t-o";

@Component({
  selector: 'app-create-a1',
  templateUrl: './create-a1.component.html',
  styleUrls: ['./create-a1.component.css']
})
export class CreateA1Component {

  zahtev: ZahtevZaAutorskaDelaDTO | undefined;
  upotreba: string = 'create';

}
