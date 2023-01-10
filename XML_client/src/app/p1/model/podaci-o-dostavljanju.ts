import {Adresa} from "./adresa";

export class PodaciODostavljanju {
  constructor(
    public adresa:Adresa = new Adresa(),
    public nacinDostavljanja:string = ''
  ) {}
}
