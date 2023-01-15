export class ZigDTO {
  constructor(
    public izgledZnaka: string = '',
    public boje: string[] = [],
    public transliteracija: string = '',
    public prevod: string = '',
    public opis: string = '',
    public klase: number[] = [],
    public tipZiga: string = 'INDIVIDUALNI',
    public vrstaZnaka: string = 'VERBALNI',
    public ostalo: string = ''
  ) {}
}
