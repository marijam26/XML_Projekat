import { Component } from '@angular/core';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent {


  zahtevZaAutorsko(){
    window.location.href = '/a1';
  }

  zahtevZaPatent(){
    window.location.href = '/p1';
  }

  zahtevZaZig(){
    window.location.href = '/z1';
  }

  redirectToSearch(){
    window.location.href = '/search';
  }

}
