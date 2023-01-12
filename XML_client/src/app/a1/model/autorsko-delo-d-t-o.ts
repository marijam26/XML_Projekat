import {VrstaDTO} from "./vrstaDTO";
import {FormatDTO} from "./formatDTO";
import {IzvornoDelo} from "./izvorno-delo";
import {TVrsta} from "./t-vrsta";
import {TFormatZapisa} from "./t-format-zapisa";


export class AutorskoDeloDTO {

  public naslov: string = '';
  public nacinKoriscenja: string = '';
  public vrsta: VrstaDTO = {value:TVrsta.KNJIZEVNO_DELO,ostalaVrstaDela:''};
  public formatZapisa: FormatDTO = {value:TFormatZapisa.OPTICKI_DISK,ostaliFormat:''};
  public izvornoDelo: IzvornoDelo | undefined;
  public stvorenoURadnomOdnosu: boolean = false;
  public prerada: boolean = false;


}
