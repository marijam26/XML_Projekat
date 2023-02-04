export class ResenjeDTO{
  constructor(
    public imeSluzbenika:string = '',
    public prezimeSluzbenika:string = '',
    public referenca:string = '',
    public odobren:boolean = true,
    public obrazlozenje:string = ''
  ) {}
}
