import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/pages/configuracion/to/cliente';
import { Constantes } from 'src/app/utils/constantes';
import { Utils } from 'src/app/utils/utils';
import { PackingList } from '../../to/packing-list';
import { Parametro } from 'src/app/pages/configuracion/to/parametro';
import { LISTAR_PACKINGLIST_CONTENEDORES } from 'src/app/shared/breadcrumb/breadcrumb';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Breadcrumb2Service } from 'src/app/shared/breadcrumb2.service';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { ConfiguracionService } from 'src/app/pages/configuracion/configuracion.sevice';
import { PackingListService } from '../../packinglist.service';
import * as XLSX from 'xlsx';

@Component({
  selector: 'app-listar',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.scss']
})
export class ListarPackingListContenedorComponent implements OnInit {

  filtro = {
    idDespacho: Constantes.P_ID_CONTENEDOR,
    codigoPacking: [],
    codigoExportacion: [],
    clientes: [],
    fechaExpMaritimoInicio: Utils.formatDate(Utils.getPreviousMonth()),
    fechaExpMaritimoFin: Utils.formatDate(new Date()),
    pagos: []
  };

  filtroClientes: Cliente[] = [];

  listado: PackingList[];

  pagos: Parametro[] = [];
  estados: Parametro[] = [];

  selectedItemsLabel: string = '{0} items seleccionados';
  displaySelectedLabel: boolean = true;
  maxSelectedLabels: number = 1;
  minSearchCharacters: number = 3;

  loadingTablaResultado: boolean = false;

  constructor(
    private router: Router,
    private breadcrumb2Service: Breadcrumb2Service,
    private messageService: MessageService,
    private settingsService: SettingsService,
    private configuracionService: ConfiguracionService,
    private service: PackingListService
  ) { }

  ngOnInit(): void {
    this.inicializarBreadcrumb();
    this.inicializar();
    this.consultar();
  }

  inicializarBreadcrumb(){
    this.breadcrumb2Service.addBreadcrumbs(LISTAR_PACKINGLIST_CONTENEDORES);
  }

  inicializar() {
    const filtroStg = sessionStorage.getItem(Constantes.P_STG_PACKINGLIST_CONTENEDORES_FILTRO);
    if(filtroStg != null) {
      this.filtro = JSON.parse(filtroStg);
      if(this.filtro.clientes != null) {
        this.filtro.clientes.forEach(codigo => {
          this.filtroClientes.push(new Cliente(codigo, '', ''));
        });
      }
    }

    this.settingsService.mostrarSpinner();
    Promise.all([
      this.configuracionService.listarParametroxDominio(Constantes.P_D011).toPromise(),//Condicion Pago Cotizacion
    ]).then(
      (data: any[]) => {
        this.settingsService.ocultarSpinner();
        this.pagos = Parametro.toArray(data[0]);
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

  irAEditar(packinglist: PackingList) {
    this.service.setPackingList(packinglist);
    this.router.navigate(['/exportaciones/packinglist/contenedores/editar', packinglist.id]);
  }

  seleccionarCodigos(items) {
    this.filtro.codigoPacking = items;
  }

  seleccionarCodigosPedidoFirme(items) {
    this.filtro.codigoExportacion = items;
  }

  seleccionarClientes(items: Cliente[]) {
    let codigos: string[] = [];
    items.forEach(cliente => {
      codigos.push(cliente.codigo);
    });
    this.filtro.clientes = [...codigos];
    this.filtroClientes = [...items];
  }

  consultar() {
    sessionStorage.setItem(Constantes.P_STG_PACKINGLIST_CONTENEDORES_FILTRO, JSON.stringify(this.filtro));

    let request : any = {};
    request.formulario = this.filtro;
    request.usuario = this.settingsService.getUsername();
    this.settingsService.mostrarSpinner();
    Promise.all([
      this.service.listar(request).toPromise()
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
    const name = 'DocumentoExportacion_' + Utils.getCurrentTimeId() + '.xlsx';
    let exportData = [];
    this.listado.map(
      row => {
        exportData.push({
          codigo: row.codigoPacking,
          codigoExportacion: row.codigo,
          nombreTipoTransporte: row.nombreTransporte,
          codigoCliente: row.codigoSapCliente,
          nombreCliente: row.nombreCliente,
          codigoDestinatario: row.codigoSapDestinatario,
          nombreDestinatario: row.nombreDestinatario,
          codigoIncotermSap: row.codigoIncoterm,
          nombreIncotermSap: row.nombreIncoterm,
          codigoIncotermComercial: row.codigoIncotermComercial,
          nombreIncotermComercial: row.nombreIncotermComercial,
          codigoPuntoOrigen: row.codigoSapPuertoOrigen,
          nombrePuntoOrigen: row.puertoDestino,
          codigoPuntoDestino: row.codigoPuertoDestino,
          nombreTipoDestino: row.puertoOrigen,
          nombreTipoDespacho: row.nombreDespacho,
          codigoCondicionPago: row.codigoCondicionPago,
          nombreCondicionPago: row.nombreCondicionPago,
          shipper: row.shipper,
          direccion: row.direccionShipper,
          consignatario: row.consignatario,
          notificante: row.notificante,
          glosa: row.glosa,
          codigoAgenteNaviera: row.codigoAgenteNaviera,
          nombreAgenteNaviera: row.nombreAgenteNaviera,
          codigoAgenteCarga: row.codigoAgenteCarga,
          nombreAgenteCarga: row.nombreAgenteCarga,
          nave: row.nave,
          etaORigen: row.etaOrigen,
          etaDestino: row.etaDestino,
          fechaBlProgramado: row.fechaBlProgramado,
          fechaBlReal: row.fechaBlReal,
          fechaCarguio: row.fechaCarguio,
          fechaEnvioDocumento: row.fechaEnvioDocum,
          booking: row.booking,
          bl: row.bl,
          guiaDHL: row.guiaDhl,
          emitidoEn: row.emitidoEn,
          dam: row.dam,
          regimen: row.nombreRegimen,
          fechaEntrega: row.fechaEntrega,
          fechaDamRegularizacion: row.fechaDamRegularizacion,
          fechaDam41: row.fechaDam41,
          nombreEstadoDocumento: row.estadodocumentoExport,
        });
    });

    const columns: any = {
      codigo: 'Código',
      codigoExportacion: 'Código Exportación',
      nombreTipoTransporte: 'Tipo Transporte',
      codigoCliente: 'Código Cliente',
      nombreCliente: 'Cliente',
      codigoDestinatario: 'Código Destinatario',
      nombreDestinatario: 'Destinatario',
      consignatario: 'Consignatario',
      notificante: 'Notificante',
      shipper: 'Shipper',
      codigoCondicionPago: 'Código Condición Pago',
      nombreCondicionPago: 'Condición Pago',
      codigoPuntoOrigen: 'Código Punto Origen',
      nombrePuntoOrigen: 'Punto Origen',
      codigoPuntoDestino: 'Código Punto Destino',
      nombreTipoDestino: 'Punto Destino',
      codigoIncotermSap: 'Código Incoterm SAP',
      nombreIncotermSap: 'Incoterm SAP',
      codigoIncotermComercial: 'Código Incoterm Comercial',
      nombreIncotermComercial: 'Incoterm Comercial',
      glosa: 'Glosa',
      codigoAgenteCarga: 'Código Agente Carga',
      nombreAgenteCarga: 'Agente Carga',
      codigoAgenteNaviera: 'Código Agente Naviera',
      nombreAgenteNaviera: 'Agente Naviera',
      booking: 'Booking',
      bl: 'BL',
      guiaDHL: 'Guía DHL',
      nave: 'Nave',
      etaORigen: 'ETA Origen',
      fechaBlProgramado: 'Fecha BL Programado',
      fechaCarguio: 'Fecha Carguío',
      fechaBlReal: 'Fecha BL Real',
      etaDestino: 'ETA Destino',
      fechaEnvioDocumento: 'Fecha Envío Documento',
      dam: 'DAM',
      regimen: 'Régimen',
      fechaEntrega: 'Fecha Entrega DAM',
      fechaDamRegularizacion: 'Fecha DAM Regularización',
      fechaDam41: 'Fecha DAM41',
      nombreEstadoDocumento: 'Estado',
    };
    exportData.unshift(columns);

    const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(exportData, { skipHeader: true });
    const book: XLSX.WorkBook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(book, worksheet, 'Documentos Maritimos');

    XLSX.writeFile(book, name);
  }

}
