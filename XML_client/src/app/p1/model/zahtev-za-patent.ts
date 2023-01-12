import {RanijaPrijava} from "./ranija-prijava";
import {OsnovnaPrijava} from "./osnovna-prijava";
import {TLiceDTO} from "../../shared-models/t-lice-d-t-o";
import {Pronalazak} from "./pronalazak";
import {PodaciODostavljanju} from "./podaci-o-dostavljanju";

export class ZahtevZaPatent{
  constructor(
    public osnovnaPrijava: OsnovnaPrijava = new OsnovnaPrijava(),
    public podnosilacPrijave: TLiceDTO = new TLiceDTO(),
    public punomocnik: TLiceDTO = new TLiceDTO(),
    public pronalazak: Pronalazak = new Pronalazak(),
    public ranijaPrijava: RanijaPrijava[] = new Array<RanijaPrijava>(),
    public pronalazac: TLiceDTO = new TLiceDTO(),
    public podaciODostavljanju: PodaciODostavljanju = new PodaciODostavljanju(),
    public brojPrijave: number = 1,
    public datumPrijema: string = '',
    public datumPodnosenja: string = '',
    public vrstaPunomocnika: string = '',
    public zajednickiPredstavnik: boolean = false
  ) {}
}
