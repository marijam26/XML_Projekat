import {RanijaPrijavaDTO} from "./ranija-prijava";
import {OsnovnaPrijavaDTO} from "./osnovna-prijava-d-t-o";
import {TLiceDTO} from "../../shared-models/t-lice-d-t-o";
import {PronalazakDTO} from "./pronalazak";
import {PodaciODostavljanjuDTO} from "./podaci-o-dostavljanju";

export class ZahtevZaPatentDTO {
  constructor(
    public osnovnaPrijava: OsnovnaPrijavaDTO = new OsnovnaPrijavaDTO(),
    public podnosilacPrijave: TLiceDTO = new TLiceDTO(),
    public punomocnik: TLiceDTO = new TLiceDTO(),
    public pronalazak: PronalazakDTO = new PronalazakDTO(),
    public ranijaPrijava: RanijaPrijavaDTO[] = new Array<RanijaPrijavaDTO>(),
    public pronalazac: TLiceDTO = new TLiceDTO(),
    public podaciODostavljanju: PodaciODostavljanjuDTO = new PodaciODostavljanjuDTO(),
    public brojPrijave: number = 1,
    public datumPrijema: string = '',
    public datumPodnosenja: string = '',
    public vrstaPunomocnika: string = '',
    public zajednickiPredstavnik: boolean = false
  ) {}
}
