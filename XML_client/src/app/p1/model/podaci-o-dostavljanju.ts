import {AdresaDTO} from "../../shared-models/adresaDTO";

export class PodaciODostavljanju {
  constructor(
    public adresa:AdresaDTO = new AdresaDTO(),
    public nacinDostavljanja:string = ''
  ) {}
}
