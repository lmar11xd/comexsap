import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { ConfiguracionService } from 'src/app/pages/configuracion/configuracion.sevice';
import { Cliente } from 'src/app/pages/configuracion/to/cliente';
import { REPORTEMARITIMO_CARGASUELTA } from 'src/app/shared/breadcrumb/breadcrumb';
import { Breadcrumb2Service } from 'src/app/shared/breadcrumb2.service';
import { Constantes } from 'src/app/utils/constantes';
import { DAY_FORMAT } from 'src/app/utils/dayformat';
import { Utils } from 'src/app/utils/utils';
import { ReportesService } from '../../reportes.service';
import { ReporteMaritimo } from '../../to/reportemaritimo';
import * as XLSX from 'xlsx';

@Component({
  selector: 'app-cargasuelta',
  templateUrl: './cargasuelta.component.html',
  styleUrls: ['./cargasuelta.component.scss']
})
export class CargasueltaComponent implements OnInit {
  filtro = {
    codigos: [],
    codigosPedidoFirme: [],
    clientes: [],
    fechaInicio: Utils.formatDate(Utils.getPreviousMonth()),
    fechaFin: Utils.formatDate(new Date()),
    responsables: [],
    bookings: []
  };

  filtroClientes: Cliente[] = [];

  selectedItemsLabel: string = '{0} items seleccionados';
  displaySelectedLabel: boolean = true;
  maxSelectedLabels: number = 1;

  listado: ReporteMaritimo[];

  constructor(
    private breadcrumb2Service: Breadcrumb2Service,
    public messageService: MessageService,
    private settingsService: SettingsService,
    private service: ReportesService
  ) { }

  ngOnInit(): void {
    this.inicializarBreadcrumb();
    this.inicializar();
    this.consultar();
  }

  inicializarBreadcrumb(){
    this.breadcrumb2Service.addBreadcrumbs(REPORTEMARITIMO_CARGASUELTA);
  }

  inicializar() {
    const filtroStg = sessionStorage.getItem(Constantes.P_STG_REPORTE_CARGASUELTA_FILTRO);
    if(filtroStg != null) {
      this.filtro = JSON.parse(filtroStg);
      if(this.filtro.clientes != null) {
        this.filtro.clientes.forEach(codigo => {
          this.filtroClientes.push(new Cliente(codigo, '', ''));
        });
      }
    }
  }

  seleccionarCodigos(items) {
    this.filtro.codigos = items;
  }

  seleccionarCodigosPedidoFirme(items) {
    this.filtro.codigosPedidoFirme = items;
  }

  seleccionarClientes(items: Cliente[]) {
    let codigos: string[] = [];
    items.forEach(cliente => {
      codigos.push(cliente.codigo);
    });
    this.filtro.clientes = [...codigos];
    this.filtroClientes = [...items];
  }

  seleccionarResponsable(items) {
    this.filtro.responsables = items;
  }

  seleccionarBooking(items) {
    this.filtro.bookings = items;
  }

  consultar() {
    sessionStorage.setItem(Constantes.P_STG_REPORTE_CARGASUELTA_FILTRO, JSON.stringify(this.filtro));

    let request : any = {};
    request.formulario = this.filtro;
    request.usuario = this.settingsService.getUsername();
    this.settingsService.mostrarSpinner();
    Promise.all([
      this.service.reporteMaritimoCargaSuelta(request).toPromise()
    ]).then(
      (data :any[]) => {
        this.settingsService.ocultarSpinner();
        this.listado = data[0];
      },
      (err) => {
        this.settingsService.ocultarSpinner();
        this.messageService.add({
          severity: "error",
			    summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
			    detail: JSON.stringify(err.message)
        });
      }
    );
  }

  obtenerFechaMaximaDam41(strFechaBlReal: string) {
    return Utils.addDaysToDate(strFechaBlReal, 30)
  }

  obtenerTiempoRegularizacionDam41(strFechaDam41: string, strFechaBlReal: string) {
    const fechaDam41 = Utils.addDaysToDate(strFechaDam41, 0);
    const fechaMaximaDam41 = Utils.addDaysToDate(strFechaBlReal, 30);
    const tiempoDam41 = fechaDam41 ? fechaDam41.getTime() : 0;
    const tiempoMaximoDam41 = fechaMaximaDam41 ? fechaMaximaDam41.getTime() : 0;
    return tiempoDam41 <= tiempoMaximoDam41 ? "SI" : "NO";
  }

  obtenerDiasOperacion(strEtaOrigen: string, strFechaBlReal: string) {
    return Utils.getDaysFromDates(strEtaOrigen, strFechaBlReal);
  }

  obtenerJornadas(strEtaOrigen: string, strFechaBlReal: string) {
    const diasOp = Utils.getDaysFromDates(strEtaOrigen, strFechaBlReal);
    return diasOp ? diasOp * 3 : '';
  }

  descargar(): void {
    if(this.listado.length <= 0) {
      return;
    }
    const name = 'ReporteMaritimoCargaSuelta_' + Utils.getCurrentTimeId() + '.xlsx';
    let exportData = [];
    this.listado.map(
      row => {
        const fechaFactura = Utils.dateToStringFormat(Utils.addDaysToDate(row.fechaFactura, 0), DAY_FORMAT.DDMMYYYY_SLASH);
        const fechaEnvioDocumento = Utils.dateToStringFormat(Utils.addDaysToDate(row.fechaEnvioDocumento, 0), DAY_FORMAT.DDMMYYYY_SLASH);
        const fechaDam41 = Utils.addDaysToDate(row.fechaDam41, 0);
        const fechaMaximaDam41 = this.obtenerFechaMaximaDam41(row.fechaBlReal);
        const tiempoRegularizacionDam41 = this.obtenerTiempoRegularizacionDam41(row.fechaDam41, row.fechaBlReal);

        exportData.push({
          anio: row.anio,
          responsable: row.usuarioCreacion.toUpperCase(),
          mes: row.mes,
          cliente: row.nombreCliente,
          descripcionMercancia: row.tipoPedido,
          incoterm: row.codigoIncotermComercial,
          puertoEmbarque: row.puertoOrigen,
          puertoDescarga: row.puertoDestino + ', ' + row.paisDestino,
          nave: row.nave,
          etaOrigen: Utils.dateToShortFormat(row.etaOrigen),
          fleteTM: row.fleteTm,
          tonRealEmbarque: row.pesoReal,
          fechaBlReal: Utils.dateToShortFormat(row.fechaBlReal),
          diasOp: this.obtenerDiasOperacion(row.etaOrigen, row.fechaBlReal),
          jornadas: this.obtenerJornadas(row.etaOrigen, row.fechaBlReal),
          armador: row.armador,
          bl: row.bl,
          facturaSap: row.facturaSap,
          fechaFacturaSap: fechaFactura,
          fechaEnvioDocumento: fechaEnvioDocumento,
          importeFacturado: row.importeTotal ? (row.codigoMoneda + ' ' + Utils.formatearImporte(row.importeTotal)) : '',
          importeFob: row.importe ? (row.codigoMoneda + ' ' + Utils.formatearImporte(row.importe)) : '',
          importeFlete: row.flete ? (row.codigoMoneda + ' ' + Utils.formatearImporte(row.flete)) : '',
          seguro: row.seguro ? (row.codigoMoneda + ' ' + Utils.formatearImporte(row.seguro)) : '',
          ordenInterna: row.ordenInterna,
          fechaDam41: Utils.dateToStringFormat(fechaDam41, DAY_FORMAT.DDMMYYYY_SLASH),
          fechaMaximaDam41: Utils.dateToStringFormat(fechaMaximaDam41, DAY_FORMAT.DDMMYYYY_SLASH),
          tiempoRegularizacionDam41: tiempoRegularizacionDam41,
          dam: row.dam
        });
    });

    const columns: any = {
      anio: 'AÑO',
      responsable: 'RESP',
      mes: 'MES EMBARQUE',
      cliente: 'CLIENTE',
      descripcionMercancia: 'DESCRIPCION DE LA MERCADERIA',
      incoterm: 'INC COMERCIAL',
      puertoEmbarque: 'PUERTO EMBARQUE',
      puertoDescarga: 'PUERTO DESCARGA',
      nave: 'NAVE',
      etaOrigen: 'ETA ORIGEN',
      fleteTM: 'FLETE POR TM ($)',
      tonRealEmbarque: 'TON REAL EMBARQUE',
      fechaBlReal: 'FECHA BL REAL',
      diasOp: 'DIAS OP',
      jornadas: 'JORNADAS',
      armador: 'ARMADOR',
      bl: 'BL',
      facturaSap: 'FACTURA SAP',
      fechaFacturaSap: 'FECHA FACTURA SAP',
      fechaEnvioDocumento: 'FECHA ENVÍO DOCUMENTO',
      importeFacturado: 'IMPORTE FACTURADO (USD)',
      importeFlete: 'IMPORTE FLETE',
      importeFob: 'IMPORTE FOB (USD)',
      seguro: 'SEGURO',
      ordenInterna: 'ORDEN INTERNA',
      fechaDam41: 'FECHA DAM 41',
      fechaMaximaDam41: 'FECHA MÁXIMA DAM 41',
      tiempoRegularizacionDam41: 'TIEMPO REGULARIZAC. DAM 41',
      dam: 'DAM'
    };

    exportData.unshift(columns);

    const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(exportData, { skipHeader: true });
    const book: XLSX.WorkBook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(book, worksheet, 'Reporte');

    XLSX.writeFile(book, name);
  }

}
