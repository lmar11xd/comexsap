export class Parametro {
  public id: number;
  public codigo: string;
  public descripcion: string;
  public descripcionLarga: string;

  public idDominio: number;
  public codigoDominio: string;
  public nombreDominio: string;
  public idParametro: number;
  public valorParametro: string;
  public valorParametro2: string;

  static toObject(obj: any) {
    return new Parametro(
      obj['idParametro'],
      obj['valorParametro'],
      obj['descripcion'],
    )
  }

  static toArray(arrObj: any[]) {
    return arrObj.map(obj => Parametro.toObject(obj));
  }

  constructor (
    id: number,
    codigo: string,
    descripcion: string
  ) {
    this.id = id;
    this.codigo = codigo;
    this.descripcion = descripcion == null ? '' : descripcion.toUpperCase();
    this.descripcionLarga = descripcion == null ? codigo : codigo + ' - ' + descripcion.toUpperCase();
  }
}
