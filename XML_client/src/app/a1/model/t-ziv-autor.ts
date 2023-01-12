import {TAutor} from "./t-autor";
import {AdresaDTO} from "../../shared-models/adresaDTO";

export interface TZivAutor extends TAutor{

  adresa: AdresaDTO;
  drzavljanstvo: string;
  pseudonim: string;

}
