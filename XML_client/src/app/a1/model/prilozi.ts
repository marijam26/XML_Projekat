import {TPrilog} from "../../shared-models/t-prilog";

export class Prilozi{
  constructor(
    public opisDela: TPrilog = {value:false,putanja:''},
    public primerDela: TPrilog = {value:false,putanja:''},
  ) {}
}


