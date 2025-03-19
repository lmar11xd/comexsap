export class ExcelMaterial {
  public codigo: string;
  public cantidad: number;
  public precio: number;

  constructor(codigo: string, cantidad: number, precio: number) {
    this.codigo = codigo;
    this.cantidad = cantidad;
    this.precio = precio;
  }
}
