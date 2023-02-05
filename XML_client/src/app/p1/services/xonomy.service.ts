import { Injectable } from '@angular/core';
declare const Xonomy: any

@Injectable({
  providedIn: 'root'
})
export class XonomyService {

  constructor() { }

  public FpodnosilacXonomy = {
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
              return jsElement.hasChildElement("Ime")
            }
          },
          {
            caption: 'Dodaj <Prezime>',
            action: Xonomy.newElementChild,
            actionParameter: '<Prezime></Prezime>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("Prezime")
            }
          },
          {
            caption: 'Dodaj <Drzavljanstvo>',
            action: Xonomy.newElementChild,
            actionParameter: '<Drzavljanstvo></Drzavljanstvo>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("Drzavljanstvo")
            }
          },
        ],
      },
      Adresa:{
        mustBeBefore:["Kontakt","Ime", "Prezime","Drzavljanstvo"],
        menu:[
          {
            caption: 'Dodaj grad',
            action: Xonomy.newElementChild,
            actionParameter: '<Grad></Grad>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("Grad");
            }
          },
          {
            caption: 'Dodaj ulica',
            action: Xonomy.newElementChild,
            actionParameter: '<Ulica></Ulica>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("Ulica");
            }
          },
          {
            caption: 'Dodaj broj',
            action: Xonomy.newElementChild,
            actionParameter: '<Broj></Broj>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("Broj");
            }
          },
          {
            caption: 'Dodaj postanski_broj',
            action: Xonomy.newElementChild,
            actionParameter: '<Postanski_broj></Postanski_broj>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("Postanski_broj");
            }
          },
          {
            caption: 'Dodaj drzava',
            action: Xonomy.newElementChild,
            actionParameter: '<Drzava></Drzava>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("Drzava");
            }
          },
        ]
      },
      Kontakt:{
        mustBeBefore:["Ime", "Prezime","Drzavljanstvo"],
        menu:[
          {
            caption: 'Dodaj email',
            action: Xonomy.newElementChild,
            actionParameter: '<E_posta></E_posta>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("E_posta");
            }
          },
          {
            caption: 'Dodaj telefon',
            action: Xonomy.newElementChild,
            actionParameter: '<Telefon></Telefon>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("Telefon");
            }
          },
          {
            caption: 'Dodaj faks',
            action: Xonomy.newElementChild,
            actionParameter: '<Faks></Faks>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("Faks");
            }
          },
        ]
      },
      Ime:{
        mustBeBefore:["Prezime","Drzavljanstvo"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("Ime");
        }
      },
      Prezime:{
        mustBeBefore:["Drzavljanstvo"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("Prezime");
        }
      },
      Drzavljanstvo:{
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("Drzavljanstvo");
        }
      },
      Grad:{
        mustBeBefore:["Ulica","Broj", "Postanski_broj", "Drzava"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("Grad");
        }
      },
      Ulica:{
        mustBeBefore:["Broj", "Postanski_broj", "Drzava"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("ulica");
        }
      },
      Broj:{
        mustBeBefore:["Postanski_broj", "Drzava"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("Broj");
        }
      },
      Postanski_broj:{
        mustBeBefore:["Drzava"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("Postanski_broj");
        }
      },
      Drzava:{
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("Drzava");
        }
      },
      E_posta:{
        mustBeBefore:["Telefon", "Faks"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("E_posta");
        }
      },
      Telefon:{
        mustBeBefore:["Faks"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("Telefon");
        }
      },
      Faks:{
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("Faks");
        }
      },
    }
  }

  public PpodnosilacXonomy = {
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
        mustBeBefore:["Kontakt","Poslovno_ime"],
        menu:[
          {
            caption: 'Dodaj grad',
            action: Xonomy.newElementChild,
            actionParameter: '<Grad></Grad>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("Grad");
            }
          },
          {
            caption: 'Dodaj ulica',
            action: Xonomy.newElementChild,
            actionParameter: '<Ulica></Ulica>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("Ulica");
            }
          },
          {
            caption: 'Dodaj broj',
            action: Xonomy.newElementChild,
            actionParameter: '<Broj></Broj>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("Broj");
            }
          },
          {
            caption: 'Dodaj postanski_broj',
            action: Xonomy.newElementChild,
            actionParameter: '<Postanski_broj></Postanski_broj>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("Postanski_broj");
            }
          },
          {
            caption: 'Dodaj drzava',
            action: Xonomy.newElementChild,
            actionParameter: '<Drzava></Drzava>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("Drzava");
            }
          },
        ]
      },
      Kontakt:{
        mustBeBefore:["Poslovno_ime"],
        menu:[
          {
            caption: 'Dodaj email',
            action: Xonomy.newElementChild,
            actionParameter: '<E_posta></E_posta>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("E_posta");
            }
          },
          {
            caption: 'Dodaj telefon',
            action: Xonomy.newElementChild,
            actionParameter: '<Telefon></Telefon>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("Telefon");
            }
          },
          {
            caption: 'Dodaj faks',
            action: Xonomy.newElementChild,
            actionParameter: '<Faks></Faks>',
            hideIf: function (jsElement:any) {
              return jsElement.hasChildElement("Faks");
            }
          },
        ]
      },
      Poslovno_ime:{
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("Poslovno_ime");
        }
      },
      Grad:{
        mustBeBefore:["Ulica","Broj", "Postanski_broj", "Drzava"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("Grad");
        }
      },
      Ulica:{
        mustBeBefore:["Broj", "Postanski_broj", "Drzava"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("ulica");
        }
      },
      Broj:{
        mustBeBefore:["Postanski_broj", "Drzava"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("Broj");
        }
      },
      Postanski_broj:{
        mustBeBefore:["Drzava"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("Postanski_broj");
        }
      },
      Drzava:{
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("Drzava");
        }
      },
      E_posta:{
        mustBeBefore:["Telefon", "Faks"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("E_posta");
        }
      },
      Telefon:{
        mustBeBefore:["Faks"],
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("Telefon");
        }
      },
      Faks:{
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString,
        hideIf: function (jsElement:any) {
          return jsElement.hasAttribute("Faks");
        }
      },
    }
  }

}
