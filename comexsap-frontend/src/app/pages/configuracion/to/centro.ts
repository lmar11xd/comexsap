export class Centro {
  public id: number;
  public codigo: string;
  public descripcion: string;
  public descripcionLarga: string;
  public checked: boolean;

  static toObject( obj: Object) {
    return new Centro(
      obj['id'],
      obj['codigoSap'],
      obj['descripcion']
    )
  }

  static toArray( arrObj: Object[]) {
    return arrObj.map(obj => Centro.toObject(obj));
  }

  constructor(
    id: number,
    codigo: string,
    descripcion: string) {
      this.id = id;
      this.codigo = codigo;
      this.descripcion = descripcion.toUpperCase();
      this.descripcionLarga = codigo + ' - ' + descripcion.toUpperCase();
    }
}
