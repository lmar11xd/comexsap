export class UnidadMedida {
  public codigo: string;
  public codigoMaterial: string;
  public umRen: number;
  public umRez: number;
  public pesoNominal: number;
  public unidadMedidaBase: string;

  static toObject(obj: Object) {
    if(!obj) return null;
    return new UnidadMedida(
      obj['unidadMedida'],
      obj['codigoMaterial'],
      obj['umRen'],
      obj['umRez'],
      obj['pesoNominal'],
      obj['unidadMedidaBase']
    )
  }

  static toArray(arrObj: any[]) {
    return arrObj.map(obj => UnidadMedida.toObject(obj));
  }

  constructor(
    codigo: string,
    codigoMaterial:string,
    umRen: number,
    umRez: number,
    pesoNominal: number,
    unidadMedidaBase: string) {
      this.codigo = codigo;
      this.codigoMaterial = codigoMaterial;
      this.umRen = umRen;
      this.umRez = umRez;
      this.pesoNominal = pesoNominal;
      this.unidadMedidaBase = unidadMedidaBase;
    }
}
