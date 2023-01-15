import {TZivAutor} from "./t-ziv-autor";
import {TPreminuliAutor} from "./t-preminuli-autor";
import {TAutor} from "./t-autor";

export class IzvornoDelo{

  constructor(
    public naslov: string = '',
    public autor: TAutor = new TAutor(),
    public anonimanAutor: boolean = false
  ) {}


}
