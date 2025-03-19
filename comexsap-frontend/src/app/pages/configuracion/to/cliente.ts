export class Cliente {
  public codigo: string;
  public descripcion: string;
  public descripcionLarga: string;
  public ruc: string;
  public checked: boolean;

  static toObject( obj: Object) {
    return new Cliente(
      obj['codigoClienteSap'],
      obj['descripcion'],
      obj['ruc'],
    )
  }

  static toArray( arrObj: Object[]) {
    return arrObj.map(obj => Cliente.toObject(obj));
  }

  constructor(
    codigo: string,
    descripcion: string,
    ruc: string) {
      this.codigo = codigo;
      this.descripcion = descripcion.toUpperCase();
      this.descripcionLarga = codigo + ' - ' + descripcion.toUpperCase();
      this.ruc = ruc;
      this.checked = false;
    }
}
