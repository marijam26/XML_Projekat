import {AdresaDTO} from "./adresaDTO";
import {KontaktDTO} from "./kontaktDTO";

export class TLiceDTO {
  constructor(
    public ime:string = '',
    public prezime:string = '',
    public drzavljanstvo:string = '',
    public poslovnoIme:string = '',
    public adresa:AdresaDTO = new AdresaDTO(),
    public kontakt:KontaktDTO = new KontaktDTO()
  ) {}
}
