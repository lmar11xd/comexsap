import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { Cliente } from 'src/app/pages/configuracion/to/cliente';
import { ControlGastosService } from '../../controlgastos.service';
import { ReporteMaritimo } from 'src/app/pages/reportes/to/reportemaritimo';
import { LISTAR_CONTROLGASTOS_CONTENEDORES } from 'src/app/shared/breadcrumb/breadcrumb';
import { Breadcrumb2Service } from 'src/app/shared/breadcrumb2.service';
import { Constantes } from 'src/app/utils/constantes';
import { DAY_FORMAT } from 'src/app/utils/dayformat';
import { Utils } from 'src/app/utils/utils';
import * as XLSX from 'xlsx';

@Component({
  selector: 'app-listar',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.scss']
})
export class ListarControlGastosContenedoresComponent implements OnInit {

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
    private router: Router,
    private breadcrumb2Service: Breadcrumb2Service,
    public messageService: MessageService,
    private settingsService: SettingsService,
    private service: ControlGastosService
  ) { }

  ngOnInit(): void {
    this.inicializarBreadcrumb();
    this.inicializar();
    this.consultar();
  }

  inicializarBreadcrumb(){
    this.breadcrumb2Service.addBreadcrumbs(LISTAR_CONTROLGASTOS_CONTENEDORES);
  }

  inicializar() {
    const filtroStg = sessionStorage.getItem(Constantes.P_STG_CONTROLGASTOS_CONTENEDORES_FILTRO);
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
    sessionStorage.setItem(Constantes.P_STG_CONTROLGASTOS_CONTENEDORES_FILTRO, JSON.stringify(this.filtro));

    let request : any = {};
    request.formulario = this.filtro;
    request.usuario = this.settingsService.getUsername();
    this.settingsService.mostrarSpinner();
    Promise.all([
      this.service.reporteMaritimoContenedores(request).toPromise()
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

  descargar(): void {
    if(this.listado.length <= 0) {
      return;
    }
    const name = 'ReporteMaritimoContenedores_' + Utils.getCurrentTimeId() + '.xlsx';
    let exportData = [];
    this.listado.map(
      row => {
        const fechaFactura = Utils.dateToStringFormat(Utils.addDaysToDate(row.fechaFactura , 0), DAY_FORMAT.DDMMYYYY_SLASH);
        const fechaEnvioDocumento = Utils.getNextBusinnesDay(row.fechaFactura);
        const tiempoEnvioDocumento = Utils.workingDays(fechaFactura, fechaEnvioDocumento);
        const fechaDam41 = Utils.addDaysToDate(row.fechaDam41, 0);
        const fechaMaximaDam41 = Utils.addDaysToDate(row.fechaBlReal, 30);
        const tiempoDam41 = fechaDam41 ? fechaDam41.getTime() : 0;
        const tiempoMaximoDam41 = fechaMaximaDam41 ? fechaMaximaDam41.getTime() : 0;
        const tiempoRegularizacionDam41 = tiempoDam41 <= tiempoMaximoDam41 ? "SI" : "NO";

        const fechaIngresoCtn = Utils.addDaysToDate(row.fechaCarguio, 0);
        const fechaMaximaIngreso = Utils.addDaysToDate(row.fechaMaximaIngreso, 0);
        const tiempoIngreCtn = fechaIngresoCtn ? fechaIngresoCtn.getTime() : 0;
        const tiempoMaximoIngreso = fechaMaximaIngreso ? fechaMaximaIngreso.getTime() : 0;
        const despachoSegunProgramacion = tiempoIngreCtn <= tiempoMaximoIngreso ? "SI" : "NO";

        exportData.push({
          anio: row.anio,
          mes: row.mes,
          pedidoFirme: row.pedidoFirme,
          cliente: row.nombreCliente,
          puertoDestino: row.puertoDestino + ', ' + row.paisDestino,
          //numeroContenedor: Utils.round(row.pesoReal / 28, 1),
          numeroContenedor: row.numeroContenedor,
          pesoReal: Utils.round(row.pesoReal, 2),
          responsable: row.usuarioCreacion.toUpperCase(),
          tipoPedido: row.tipoPedido,
          codigo: row.codigo,
          fechaDisponibilidad: Utils.dateToShortFormat(row.fechaDisponibilidad),
          incoterm: row.codigoIncotermComercial,
          agente: row.nombreAgenteCarga ? row.nombreAgenteCarga : row.nombreAgenteNaviera,
          booking: row.booking,
          bl: row.bl,
          etaCallao: Utils.dateToShortFormat(row.etaOrigen),
          etaDestino: Utils.dateToShortFormat(row.etaDestino),
          estadoDespacho: Utils.isNullOrEmpty(row.facturaElectronica) ? 'Por Facturar' : 'Facturado',
          facturaElectronica: row.facturaElectronica,
          facturaSap: row.facturaSap,
          fechaFactura: fechaFactura,
          fechaEnvioDocumento: fechaEnvioDocumento,
          importe: row.importeTotal ? (row.codigoMoneda + ' ' + Utils.formatearImporte(row.importeTotal)) : '',
          importeFob: row.importe ? (row.codigoMoneda + ' ' + Utils.formatearImporte(row.importe)) : '',
          flete: row.flete ? (row.codigoMoneda + ' ' + Utils.formatearImporte(row.flete)) : '',
          seguro: row.seguro ? (row.codigoMoneda + ' ' + Utils.formatearImporte(row.seguro)) : '',
          ordenInterna: row.ordenInterna,
          guiaDhl: row.tipoEnvio == Constantes.P_ENVIO_DIGITAL ? 'ENVÍO DIGITAL' : row.guiaDhl,
          fechaBl: Utils.dateToShortFormat(row.fechaBlReal),
          fechaIngresoCtn: Utils.dateToStringFormat(fechaIngresoCtn, DAY_FORMAT.DDMMYYYY_SLASH),
          fechaMaximaIngreso: Utils.dateToStringFormat(fechaMaximaIngreso, DAY_FORMAT.DDMMYYYY_SLASH),
          fechaDam41: Utils.dateToStringFormat(fechaDam41, DAY_FORMAT.DDMMYYYY_SLASH),
          fechaMaximaDam41: Utils.dateToStringFormat(fechaMaximaDam41, DAY_FORMAT.DDMMYYYY_SLASH),
          tiempoRegularizacionDam41: tiempoRegularizacionDam41,
          fechaRecepcionDam: Utils.dateToShortFormat(row.fechaDam),
          dam: row.dam,
          tiempoEnvioDocumento: tiempoEnvioDocumento,
          despachoProgramacion: despachoSegunProgramacion
        });
    });

    const columns: any = {
      anio: 'AÑO',
      mes: 'MES',
      pedidoFirme: 'PER',
      cliente: 'CLIENTE',
      puertoDestino: 'POD',
      numeroContenedor: 'N° CONT',
      pesoReal: 'PESO REAL',
      responsable: 'RESP',
      tipoPedido: 'TIPO PEDIDO',
      codigo: 'PEDIDO',
      fechaDisponibilidad: 'DISPONIB. PEDIDO A FIRME',
      incoterm: 'INCOTERM VENTA',
      agente: 'AG. CARGA / NAVIERA',
      booking: 'BOOKING',
      bl: 'BL',
      etaCallao: 'ETA CALLAO',
      etaDestino: 'ETA DESTINO',
      estadoDespacho: 'ESTADO DESPACHO',
      facturaElectronica: 'FACTURA ELECT',
      facturaSap: 'FACTURA SAP',
      fechaFactura: 'FECHA FACTURA',
      fechaEnvioDocumento: 'FECHA ENVIO DOCS CLIENTE',
      importe: 'IMPORTE FACTURADO (USD)',
      importeFob: 'IMPORTE FOB (USD)',
      flete: 'FLETE',
      seguro: 'SEGURO',
      ordenInterna: 'ORDEN INTERNA',
      guiaDhl: 'GUIA DHL',
      fechaBl: 'FECHA BL',
      fechaIngresoCtn: 'FECHA INGRESO CTN',
      fechaMaximaIngreso: 'FECHA MÁXIMA INGRESO',
      fechaDam41: 'FECHA DAM 41',
      fechaMaximaDam41: 'FECHA MÁXIMA DAM 41',
      tiempoRegularizacionDam41: 'TIEMPO REGULARIZAC. DAM 41',
      fechaRecepcionDam: 'FECHA RECEPCION DAM',
      dam: 'DAM',
      tiempoEnvioDocumento: 'TIEMPO ENVIO DOCS CLIENTE',
      despachoProgramacion: 'DESPACHO SEGÚN PROGRAMACIÓN'
    };
    exportData.unshift(columns);

    const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(exportData, { skipHeader: true });
    const book: XLSX.WorkBook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(book, worksheet, 'Reporte');

    XLSX.writeFile(book, name);
  }

  seleccionarDocumentos(event) {
    this.listado.forEach((item) => {
      if(item.idControlGasto == 0) {
        item.checked = event.target.checked;
      }
    });
  }

  seleccionarDocumento(event, item: ReporteMaritimo) {
    if(item.idControlGasto > 0) {
      item.checked = false;
    } else {
      item.checked = event.target.checked;
    }
  }

  irARegistrar() {
    const documentos = this.listado.filter(item => item.checked);
    if(documentos != null && documentos.length > 0) {
      const ids = documentos.map(item => item.idExportacion).join(';');
      this.router.navigate(['/exportaciones/controlgastos/contenedores/registrar', ids]);
    } else {
      this.messageService.add({
        severity: "warn",
        summary: "Alerta",
        detail: "Seleccione al menos un documento de exportación."
      });
    }
  }

}
