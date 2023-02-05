import { Injectable } from '@angular/core';
declare const Xonomy: any

@Injectable({
  providedIn: 'root'
})
export class XonomyService {

  constructor() { }

  public patentXonomy = {
    elements: {
      Podnosilac_prijave: {
        expanded: true,
        collapsed: true,
        menu:[
          {
            caption: 'Dodaj <Adresa>',
            action: Xonomy.newElementChild,
            actionParameter: '<Adresa></Adresa>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("Adresa")
            }
          },
          {
            caption: 'Dodaj <Kontakt>',
            action: Xonomy.newElementChild,
            actionParameter: '<Kontakt></Kontakt>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("Kontakt")
            }
          },
          {
            caption: 'Dodaj <Ime>',
            action: Xonomy.newElementChild,
            actionParameter: '<Ime></Ime>',
            hideIf: function (jsElement:any) {
              return jsElement.hasAttribute("Ime")
            }
          },
          {
            caption: 'Dodaj <Prezime>',
            action: Xonomy.newElementChild,
            actionParameter: '<Prezime></Prezime>',
            hideIf: function (jsElement:any) {
              return jsElement.hasAttribute("Prezime")
            }
          },
          {
            caption: 'Dodaj <Poslovno_ime>',
            action: Xonomy.newElementChild,
            actionParameter: '<Poslovno_ime></Poslovno_ime>',
            hideIf: function (jsElement:any) {
              return jsElement.hasAttribute("Poslovno_ime")
            }
          },
        ],
      },
      Adresa:{
        mustBeBefore:["Kontakt","Ime", "Prezime", "Poslovno_ime"],
        menu:[
          {
            caption: 'Dodaj grad',
            action: Xonomy.newElementChild,
            actionParameter: '<grad></grad>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("grad");
            }
          },
          {
            caption: 'Dodaj ulica',
            action: Xonomy.newElementChild,
            actionParameter: '<ulica></ulica>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("ulica");
            }
          },
          {
            caption: 'Dodaj broj',
            action: Xonomy.newElementChild,
            actionParameter: '<broj></broj>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("broj");
            }
          },
          {
            caption: 'Dodaj postanski_broj',
            action: Xonomy.newElementChild,
            actionParameter: '<postanski_broj></postanski_broj>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("postanski_broj");
            }
          },
          {
            caption: 'Dodaj drzava',
            action: Xonomy.newElementChild,
            actionParameter: '<drzava></drzava>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("drzava");
            }
          },
        ]
      },
      kontakt:{
        mustBeBefore:["Ime", "Prezime","Poslovno_ime"],
        menu:[
          {
            caption: 'Dodaj email',
            action: Xonomy.newElementChild,
            actionParameter: '<email></email>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("email");
            }
          },
          {
            caption: 'Dodaj telefon',
            action: Xonomy.newElementChild,
            actionParameter: '<telefon></telefon>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("telefon");
            }
          },
          {
            caption: 'Dodaj fax',
            action: Xonomy.newElementChild,
            actionParameter: '<fax></fax>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("fax");
            }
          },
        ]
      },
      Ime:{
        mustBeBefore:["Prezime", "Poslovno_ime"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("Ime");
        }
      },
      Prezime:{
        mustBeBefore:["Poslovno_ime"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("Prezime");
        }
      },
      Poslovno_ime:{
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("Poslovno_ime");
        }
      },
      grad:{
        mustBeBefore:["ulica","broj", "postanski_broj", "drzava"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("grad");
        }
      },
      ulica:{
        mustBeBefore:["broj", "postanski_broj", "drzava"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("ulica");
        }
      },
      broj:{
        mustBeBefore:["postanski_broj", "drzava"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("broj");
        }
      },
      postanski_broj:{
        mustBeBefore:["drzava"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("postanski_broj");
        }
      },
      drzava:{
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("drzava");
        }
      },
    }
  }

  public pronalazac = {
    elements: {
      Zahtev_za_patent: {
        expanded: true,
        collapsed: true,
        menu: [
          {
            caption: 'Dodaj <Podnosilac_prijave>',
            action: Xonomy.newElementChild,
            actionParameter: '<Podnosilac_prijave></Podnosilac_prijave>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("podnosilac")
            }
          },
          {
            caption: 'Dodaj <Pronalazac>',
            action: Xonomy.newElementChild,
            actionParameter: '<Pronalazac></Pronalazac>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("Pronalazac")
            }
          },

          {
            caption: 'Dodaj <Ranija_prijava>',
            action: Xonomy.newElementChild,
            actionParameter: '<Ranija_prijava></Ranija_prijava>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("Ranija_prijava")
            }
          },

        ],

      },

      Podnosilac_prijave:{
        mustBeBefore:['Pronalazac', 'Ranija_prijava' ],
        menu:[
          {
            caption: 'Dodaj <Adresa>',
            action: Xonomy.newElementChild,
            actionParameter: '<Adresa></Adresa>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("Adresa")
            }
          },
          {
            caption: 'Dodaj <Kontakt>',
            action: Xonomy.newElementChild,
            actionParameter: '<Kontakt></Kontakt>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("Kontakt")
            }
          },
          {
            caption: 'Dodaj <Ime>',
            action: Xonomy.newElementChild,
            actionParameter: '<Ime></Ime>',
            hideIf: function (jsElement:any) {
              return jsElement.hasAttribute("Ime")
            }
          },
          {
            caption: 'Dodaj <Prezime>',
            action: Xonomy.newElementChild,
            actionParameter: '<Prezime></Prezime>',
            hideIf: function (jsElement:any) {
              return jsElement.hasAttribute("Prezime")
            }
          },
          {
            caption: 'Dodaj <Poslovno_ime>',
            action: Xonomy.newElementChild,
            actionParameter: '<Poslovno_ime></Poslovno_ime>',
            hideIf: function (jsElement:any) {
              return jsElement.hasAttribute("Poslovno_ime")
            }
          },
        ],
      },
      Adresa:{
        mustBeBefore:["Kontakt","Ime", "Prezime", "Poslovno_ime"],
        menu:[
          {
            caption: 'Dodaj grad',
            action: Xonomy.newElementChild,
            actionParameter: '<grad></grad>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("grad");
            }
          },
          {
            caption: 'Dodaj ulica',
            action: Xonomy.newElementChild,
            actionParameter: '<ulica></ulica>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("ulica");
            }
          },
          {
            caption: 'Dodaj broj',
            action: Xonomy.newElementChild,
            actionParameter: '<broj></broj>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("broj");
            }
          },
          {
            caption: 'Dodaj postanski_broj',
            action: Xonomy.newElementChild,
            actionParameter: '<postanski_broj></postanski_broj>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("postanski_broj");
            }
          },
          {
            caption: 'Dodaj drzava',
            action: Xonomy.newElementChild,
            actionParameter: '<drzava></drzava>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("drzava");
            }
          },
        ]
      },
      kontakt:{
        mustBeBefore:["Ime", "Prezime","Poslovno_ime"],
        menu:[
          {
            caption: 'Dodaj email',
            action: Xonomy.newElementChild,
            actionParameter: '<email></email>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("email");
            }
          },
          {
            caption: 'Dodaj telefon',
            action: Xonomy.newElementChild,
            actionParameter: '<telefon></telefon>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("telefon");
            }
          },
          {
            caption: 'Dodaj fax',
            action: Xonomy.newElementChild,
            actionParameter: '<fax></fax>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("fax");
            }
          },
        ]
      },
      ulica:{
        mustBeBefore:["broj", "postanski_broj", "drzava"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("ulica");
        }
      },

    }
  }

}
