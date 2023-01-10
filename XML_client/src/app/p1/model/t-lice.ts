import {Adresa} from "./adresa";
import {Kontakt} from "./kontakt";

export class TLice{
  constructor(
    public ime:string = '',
    public prezime:string = '',
    public drzavljanstvo:string = '',
    public poslovnoIme:string = '',
    public adresa:Adresa = new Adresa(),
    public kontakt:Kontakt = new Kontakt()
  ) {}
}
