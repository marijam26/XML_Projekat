import {TLiceDTO} from "../../shared-models/t-lice-d-t-o";
import {AutorskoDeloDTO} from "./autorsko-delo-d-t-o";
import {TAutor} from "./t-autor";
import {Prilozi} from "./prilozi";

export class ZahtevZaAutorskaDelaDTO {

  constructor(
    public broj:number = 11,
    public datumPodnosenja: string = '',
    public podnosilacPrijave:TLiceDTO = new TLiceDTO(),
    public punomocnik:TLiceDTO = new TLiceDTO(),
    public autorskoDelo: AutorskoDeloDTO = new AutorskoDeloDTO(),
    public autor: TAutor = new TAutor(),
    public prilozi: Prilozi = new Prilozi(),
    public podnosilacJeAutor: boolean = false
  ) {}

}
