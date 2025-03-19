export class Destinatario {
  public codigo: string;
  public codigoCliente: string;
  public descripcion:string;
  public descripcionLarga: string;

  static toObject(obj: any) {
    return new Destinatario(
      obj['codigoDestinatario'],
      obj['codigoClienteSap'],
      obj['descripcion']
    )
  }

  static toArray( arrObj: any[]) {
    return arrObj.map(obj => Destinatario.toObject(obj));
  }

  constructor(
    codigo: string,
    codigoCliente: string,
    descripcion: string) {
      this.codigo = codigo;
      this.codigoCliente = codigoCliente;
      this.descripcion = descripcion.toUpperCase();
      this.descripcionLarga = codigo + ' - ' + descripcion.toUpperCase();
    }
}
