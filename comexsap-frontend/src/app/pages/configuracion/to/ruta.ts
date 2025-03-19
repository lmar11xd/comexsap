export class Ruta {
  public id: number;
  public codigo: string;
  public descripcion: string;
  public descripcionLarga: string;
  public checked: boolean;

  static toObject(obj: any) {
    return new Ruta(
      obj['idParametro'],
      obj['valorParametro'],
      obj['descripcion'],
    )
  }

  static toArray(arrObj: any[]) {
    return arrObj.map(obj => Ruta.toObject(obj));
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
    this.checked = false;
  }
}
