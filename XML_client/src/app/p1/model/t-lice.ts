import {Adresa} from "./adresa";
import {Kontakt} from "./kontakt";

export abstract class TLice{
  constructor(
    private ime:string,
    private prezime:string,
    private drzavljanstvo:string,
    private poslovnoIme:string,
    private adresa:Adresa,
    private kontakt:Kontakt
  ) {}
}
