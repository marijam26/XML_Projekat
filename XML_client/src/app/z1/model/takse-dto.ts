import { KlaseTakse } from './klase-takse';

export class TakseDTO {
  constructor(
    public osnovnaTaksa: number = 0,
    public grafickoResenje: number = 0,
    public ukupno: number = 0,
    public klase: number = 0,
    public brojKlasa: number = 0
  ) {}
}
