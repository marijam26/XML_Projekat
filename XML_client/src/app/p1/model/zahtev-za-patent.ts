import {RanijaPrijava} from "./ranija-prijava";
import {OsnovnaPrijava} from "./osnovna-prijava";
import {TLice} from "./t-lice";
import {Pronalazak} from "./pronalazak";
import {PodaciODostavljanju} from "./podaci-o-dostavljanju";

export class ZahtevZaPatent{
  constructor(
    public osnovnaPrijava: OsnovnaPrijava = new OsnovnaPrijava(),
    public podnosilacPrijave: TLice = new TLice(),
    public punomocnik: TLice = new TLice(),
    public pronalazak: Pronalazak = new Pronalazak(),
    public ranijaPrijava: RanijaPrijava[] = new Array<RanijaPrijava>(),
    public pronalazac: TLice = new TLice(),
    public podaciODostavljanju: PodaciODostavljanju = new PodaciODostavljanju(),
    public brojPrijave: number = 1,
    public datumPrijema: string = '',
    public datumPodnosenja: string = '',
    public vrstaPunomocnika: string = '',
    public zajednickiPredstavnik: boolean = false
  ) {}
}
