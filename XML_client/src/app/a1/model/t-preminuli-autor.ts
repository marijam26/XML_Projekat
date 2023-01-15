import {TAutor} from "./t-autor";

export class TPreminuliAutor extends TAutor{

  constructor(
    public override godinaSmrti: number = -1
  ) {
    super();
  }

}
