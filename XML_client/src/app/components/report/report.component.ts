import { Component } from '@angular/core';
import { IzvestajDTO } from '../../shared-models/izvestajDTO';
import { P1Service } from '../../p1/services/p1.service';
import { ToastrService } from 'ngx-toastr';
import { Z1Service } from '../../z1/services/z1.service';
import {A1Service} from "../../a1/services/a1.service";

@Component({
  selector: 'app-report',
  templateUrl: './report.component.html',
  styleUrls: ['./report.component.css'],
})
export class ReportComponent {
  today: string = new Date().toLocaleDateString('en-ca');
  startDate = '';
  endDate = '';
  vrsta: string = 'Patent';

  constructor(
    private patentService: P1Service,
    private toast: ToastrService,
    private zigService: Z1Service,
    private autorsko: A1Service
  ) {}

  getreport() {
    const start = new Date(this.startDate);
    const end = new Date(this.endDate);
    let izvestajDTO = new IzvestajDTO(
      start.toLocaleDateString('en-ca'),
      end.toLocaleDateString('en-ca')
    );
    if (this.vrsta === 'Patent') {
      this.patentService.generateReport(izvestajDTO).subscribe(
        (data) => {
          this.toast.success('Uspesno generisan izvestaj!');
          window.open(
            'http://localhost:9001/api/patent/downloadPDF/izvestaj.pdf'
          );
        },
        (err) => console.log(err)
      );
    } else if (this.vrsta === 'Zig') {
      this.zigService.generateReport(izvestajDTO).subscribe(
        (data) => {
          this.toast.success('Uspesno generisan izvestaj!');
          window.open('http://localhost:9003/api/zig/downloadPDF/izvestaj.pdf');
        },
        (err) => console.log(err)
      );
    } else  {
      this.autorsko.generateReport(izvestajDTO).subscribe(
        (data) => {
          this.toast.success('Uspesno generisan izvestaj!');
          window.open(
            'http://localhost:9002/api/autorskoPravo/downloadPDF/izvestaj.pdf'
          );
        },
        (err) => console.log(err)
      );
    }
  }

  changeVrsta(vrsta: string) {
    this.vrsta = vrsta;
  }
}
