import { Component } from '@angular/core';
import {IzvestajDTO} from "../../shared-models/izvestajDTO";
import {P1Service} from "../../p1/services/p1.service";
import {ToastrService} from "ngx-toastr";

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css']
})
export class ReportComponent {
  today: string = new Date().toLocaleDateString('en-ca');
  startDate = '';
  endDate = '';
  vrsta:string = "Patent";

  constructor(private patentService:P1Service,private toast:ToastrService) {
  }


  getreport(){
    const start = new Date(this.startDate);
    const end = new Date(this.endDate);
    let izvestajDTO = new IzvestajDTO(start.toLocaleDateString('en-ca'),end.toLocaleDateString('en-ca'));
    if(this.vrsta === 'Patent'){
      this.patentService.generateReport(izvestajDTO).subscribe(
        (data)=> {
          this.toast.success('Uspesno generisan izvestaj!');
          window.open(
            'http://localhost:9001/api/patent/downloadPDF/izvestaj.pdf'
          );
        },
        (err)=>console.log(err)
      )
    }
  }

  changeVrsta(vrsta:string){
    this.vrsta = vrsta;
  }

}
