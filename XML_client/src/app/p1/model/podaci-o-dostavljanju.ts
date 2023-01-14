import {AdresaDTO} from "../../shared-models/adresaDTO";

export class PodaciODostavljanjuDTO {
  constructor(
    public adresa:AdresaDTO = new AdresaDTO(),
    public nacinDostavljanja:string = ''
  ) {}
}
