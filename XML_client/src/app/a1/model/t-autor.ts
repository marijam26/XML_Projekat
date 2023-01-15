import {AdresaDTO} from "../../shared-models/adresaDTO";

export class TAutor{

  constructor(
    public ime: string = '',
    public prezime: string ='',
    public adresa: AdresaDTO = new AdresaDTO(),
    public drzavljanstvo: string = '',
    public pseudonim: string = '',
    public godinaSmrti: number = 0
  ){}


}
