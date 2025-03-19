export class Puerto {
  public id:number;
  public codigo: string;
  public descripcion: string;
  public descripcionLarga: string;
  public codigoPais: string;
  public descripcionPais: string;
  public checked: boolean;

  static toObject( obj: Object) {
    return new Puerto(
      obj['puernId'],
      obj['puervCodigosap'],
      obj['puervDescripcion'],
      obj['codigoPais'],
      obj['descripcionPais']
    )
  }

  static toArray( arrObj: Object[]) {
    return arrObj.map(obj => Puerto.toObject(obj));
  }

  constructor(
    id:number,
    codigo: string,
    descripcion: string,
    codigoPais: string,
    descripcionPais: string) {
      this.id = id;
      this.codigo = codigo;
      this.descripcion = descripcion.toUpperCase();
      this.descripcionLarga = codigo + ' - ' + descripcion.toUpperCase();
      this.codigoPais = codigoPais;
      this.descripcionPais = descripcionPais;
      this.checked = false;
    }
}
