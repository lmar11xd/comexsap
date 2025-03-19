export class Dominio {
  public id: number;
  public codigo: string;
  public descripcion: string;
  public descripcionLarga: string;

  static toObject(obj: any) {
    return new Dominio(
      obj['idDominio'],
      obj['codigoDominio'],
      obj['descripcion'],
    )
  }

  static toArray(arrObj: any[]) {
    return arrObj.map(obj => Dominio.toObject(obj));
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
