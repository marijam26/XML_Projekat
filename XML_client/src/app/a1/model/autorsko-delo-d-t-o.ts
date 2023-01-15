import {VrstaDTO} from "./vrstaDTO";
import {FormatDTO} from "./formatDTO";
import {IzvornoDelo} from "./izvorno-delo";
import {TVrsta} from "./t-vrsta";
import {TFormatZapisa} from "./t-format-zapisa";


export class AutorskoDeloDTO {

  constructor(
    public naslov:string = '',
    public nacinKoriscenja: string = '',
    public vrsta: VrstaDTO = {value:'knjizevno_delo',ostalaVrstaDela:''},
    public formatZapisa: FormatDTO = {value:'opticki_disk',ostaliFormat:''},
    public izvornoDelo: IzvornoDelo = new IzvornoDelo(),
    public stvorenoURadnomOdnosu: boolean = false,
    public prerada: boolean = false )
  {}

}
