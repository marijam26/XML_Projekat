import { TPrilog } from '../../shared-models/t-prilog';
import { Prilog } from './prilog';

export class ZigPrilozi {
  constructor(
    public primerakZnaka: Prilog = new Prilog(),
    public spisakRobe: Prilog = new Prilog(),
    public punomoc: Prilog = new Prilog(),
    public ranijaPunomoc: Prilog = new Prilog(),
    public naknadnaPunomoc: Prilog = new Prilog(),
    public opstiAkt: Prilog = new Prilog(),
    public dokazOPravuPrvenstva: Prilog = new Prilog(),
    public dokazOUplati: Prilog = new Prilog()
  ) {}
}
