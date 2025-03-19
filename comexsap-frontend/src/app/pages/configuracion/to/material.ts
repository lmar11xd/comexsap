export class Material {
  public codigo: string;
  public descripcion:string;
  public descripcionLarga:string;
  public unidadBase: string;
  public unidadVenta: string;
  public pesoNeto: number;
  public pesoBruto: number;
  public checked: boolean;

  static toObject( obj: Object) {
    return new Material(
      obj['codigoMaterial'],
      obj['descripcionMaterial'],
      obj['unidadBase'],
      obj['unidadVenta'],
      obj['pesoNeto'],
      obj['pesoBruto']
    )
  }

  static toArray( arrObj: Object[]) {
    return arrObj.map(obj => Material.toObject(obj));
  }

  constructor(
    codigo: string,
    descripcion:string,
    unidadBase: string,
    unidadVenta: string,
    pesoNeto: number,
    pesoBruto: number) {
      this.codigo = codigo;
      this.descripcion = descripcion;
      this.descripcionLarga = codigo.replace(/^(0+)/g, '') + ' - ' + descripcion.toUpperCase();
      this.unidadBase = unidadBase;
      this.unidadVenta = unidadVenta;
      this.pesoNeto = pesoNeto;
      this.pesoBruto = pesoBruto;
      this.checked = false;
    }
}
