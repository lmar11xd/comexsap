export class ExportacionFactura {
  id: number;
  idExportacion: number;
	idPedido: number;
  codigoPedido: string;
	factura: string;
	etiquetaFlete: string;
	etiquetaImporteFlete: string;
	etiquetaTotal: string;
  etiquetaImporteTotal: string;
	etiquetaUnidadMedida: string;
	montoFlete: number;
	montoImporteTotal: number;
	montoTotal: number;
	estado: number;
}
