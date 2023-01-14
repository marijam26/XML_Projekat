export class AdresaDTO {
  constructor(
    public ulica:string = '',
    public broj:number = -1,
    public drzava:string = '',
    public postanskiBroj:number = -1,
    public grad:string = ''
  ) {}
}
