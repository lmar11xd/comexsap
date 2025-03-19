export class ConversionFamilia {
  public codigo: string;
  public unidadMedida: string;
  public unidadMedidaConversion: string;

  constructor (
    codigo: string,
    unidadMedida: string,
    unidadMedidaConversion: string
  ) {
    this.codigo = codigo;
    this.unidadMedida = unidadMedida;
    this.unidadMedidaConversion = unidadMedidaConversion;
  }
}
