export class Almacen {
  public id: number;
  public idCentro: number;
  public codigo: string;
  public descripcion: string;
  public descripcionLarga: string;

  static toObject( obj: Object) {
    return new Almacen(
      obj['id'],
      obj['idCentro'],
      obj['codigoSap'],
      obj['descripcion']
    )
  }

  static toArray( arrObj: Object[]) {
    return arrObj.map(obj => Almacen.toObject(obj));
  }

  constructor(
    id: number,
    idCentro: number,
    codigo: string,
    descripcion: string) {
      this.id = id;
      this.idCentro = idCentro;
      this.codigo = codigo;
      this.descripcion = descripcion.toUpperCase();
      this.descripcionLarga = codigo + ' - ' + descripcion.toUpperCase();
    }
}
