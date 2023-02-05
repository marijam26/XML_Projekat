import { TLiceDTO } from '../../shared-models/t-lice-d-t-o';
import { ZigDTO } from './zig-dto';
import { TakseDTO } from './takse-dto';
import { ZigPrilozi } from './prilozi';

export class ZahtevZaZigDTO {
  constructor(
    public podnosilacPrijave: TLiceDTO = new TLiceDTO(),
    public punomocnik: TLiceDTO = new TLiceDTO(),
    public zig: ZigDTO = new ZigDTO(),
    public takse: TakseDTO = new TakseDTO(),
    public prilozi: ZigPrilozi = new ZigPrilozi(),
    public brojPrijave: number = -1,
    public id: string = '',
    public datumPodnosenja: string = ''
  ) {}
}
