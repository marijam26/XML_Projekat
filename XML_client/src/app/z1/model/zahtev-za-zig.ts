import { TLiceDTO } from '../../shared-models/t-lice-d-t-o';
import { ZigDTO } from './zig-dto';
import { TakseDTO } from './takse-dto';
import { ZigPrilozi } from './prilozi';

export class ZahtevZaZigDTO {
  constructor(
    public podnosilac: TLiceDTO = new TLiceDTO(),
    public punomocnik: TLiceDTO = new TLiceDTO(),
    public zig: ZigDTO = new ZigDTO(),
    public takse: TakseDTO = new TakseDTO(),
    public prilozi: ZigPrilozi = new ZigPrilozi()
  ) {}
}
