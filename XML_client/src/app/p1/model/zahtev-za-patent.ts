import {RanijaPrijava} from "./ranija-prijava";
import {OsnovnaPrijava} from "./osnovna-prijava";
import {TLice} from "./t-lice";
import {Pronalazak} from "./pronalazak";
import {PodaciODostavljanju} from "./podaci-o-dostavljanju";

export class ZahtevZaPatent{
  constructor(
    private osnovnaPrijava:OsnovnaPrijava,
    private podnosilacPrijave:TLice,
    private punomocnik:TLice,
    private pronalazak:Pronalazak,
    private ranijaPrijava:RanijaPrijava[],
    private pronalazac:TLice,
    private podaciODostavljanju:PodaciODostavljanju,
    private brojPrijave:number,
    private datumPrijema:string,
    private datumPodnosenja:string,
    private vrstaPunomocnika:string,
    private zajednickiPredstavnik:boolean
  ) {}
}
