import {TLiceDTO} from "../../shared-models/t-lice-d-t-o";
import {AutorskoDeloDTO} from "./autorsko-delo-d-t-o";
import {TAutor} from "./t-autor";
import {Prilozi} from "./prilozi";

export interface ZahtevZaAutorskaDelaDTO {

  broj:number;
  datumPodnosenja: string;
  podnosilacPrijave:TLiceDTO;
  punomocnik:TLiceDTO;
  autorskoDelo: AutorskoDeloDTO;
  autor: TAutor;
  prilozi: Prilozi;
  podnosilacJeAutor: boolean;

}
