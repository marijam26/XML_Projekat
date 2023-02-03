export class Korisnik {
  constructor(
    public ime:string = '',
    public prezime:string = '',
    public email: string = '',
    public lozinka: string = '',
    public uloga: string = 'Gradjanin'
  ) {}
}

export class LoginInfo{
  constructor(
    public email:string = '',
    public password:string = ''
  ) {}
}
