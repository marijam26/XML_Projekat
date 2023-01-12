import {TZivAutor} from "./t-ziv-autor";
import {TPreminuliAutor} from "./t-preminuli-autor";
import {TAutor} from "./t-autor";

export interface IzvornoDelo{

  naslov: string;
  autor: TAutor;
  anonimanAutor: boolean;

}
