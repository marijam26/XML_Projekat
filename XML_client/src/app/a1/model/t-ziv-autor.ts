import {TAutor} from "./t-autor";
import {AdresaDTO} from "../../shared-models/adresaDTO";

export class TZivAutor extends TAutor{

  constructor(
    public override adresa: AdresaDTO = new AdresaDTO(),
    public override drzavljanstvo: string = '',
    public override pseudonim: string = ''
  ) {
    super();
  }


}
