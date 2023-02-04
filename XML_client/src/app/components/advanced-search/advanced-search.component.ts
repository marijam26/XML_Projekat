import { Component } from '@angular/core';
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-advanced-search',
  templateUrl: './advanced-search.component.html',
  styleUrls: ['./advanced-search.component.css']
})
export class AdvancedSearchComponent {
  operatori:string[] = [];
  metapodaci:string[] = [''];
  vredosti:string[] = [''];

  constructor(private toast:ToastrService) {
  }

  dodajMetapodatak(){
    if(this.metapodaci.at(-1) !== '' && this.vredosti.at(-1) !== ''){
      this.operatori.push('||');
      this.metapodaci.push('');
      this.vredosti.push('');
    }else{
      this.toast.error('Unesite sve!')
    }

    console.log(this.metapodaci)
  }

  pretrazi(){
    //salje se
    console.log(this.operatori)
    console.log(this.metapodaci)
    console.log(this.vredosti)
  }

}
