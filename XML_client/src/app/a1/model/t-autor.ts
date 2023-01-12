import {AdresaDTO} from "../../shared-models/adresaDTO";

export interface TAutor{

  ime: string;
  prezime: string;
  adresa: AdresaDTO;
  drzavljanstvo: string;
  pseudonim: string;
  godinaSmrti: number;

}
