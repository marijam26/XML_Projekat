import {AfterViewInit, Component, OnInit} from '@angular/core';
import {XonomyService} from "../services/xonomy.service";

declare const Xonomy: any;
@Component({
  selector: 'app-patent-xonomy',
  templateUrl: './patent-xonomy.component.html',
  styleUrls: ['./patent-xonomy.component.css']
})
export class PatentXonomyComponent implements OnInit,AfterViewInit{

  constructor(private xonomyService:XonomyService) {
  }



  send(){
    let text = Xonomy.harvest();
    console.log(text)
  }

  ngOnInit(): void {

  }

  ngAfterViewInit(): void {
    let element = document.getElementById("editor");
    let specification = this.xonomyService.patentXonomy;
    let xmlString = '<Podnosilac_prijave></Podnosilac_prijave>';
    Xonomy.render(xmlString, element, specification);
  }

}