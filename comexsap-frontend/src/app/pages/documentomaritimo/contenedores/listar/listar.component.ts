import { Component, OnInit } from '@angular/core';
import { Cliente } from 'src/app/pages/configuracion/to/cliente';
import { Parametro } from 'src/app/pages/configuracion/to/parametro';
import { Utils } from 'src/app/utils/utils';
import { DocumentoMaritimo } from '../../to/documentomaritimo';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MessageService } from 'primeng/api';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { Breadcrumb2Service } from 'src/app/shared/breadcrumb2.service';
import { ConfiguracionService } from 'src/app/pages/configuracion/configuracion.sevice';
import { DocumentoMaritimoService } from '../../documentomaritimo.service';
import * as XLSX from 'xlsx';
import { LISTAR_DOCUMENTOMARITIMO_CONTENEDORES } from 'src/app/shared/breadcrumb/breadcrumb';
import { Constantes } from 'src/app/utils/constantes';

@Component({
  selector: 'app-listar',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.scss']
})
export class ListarContenedorComponent implements OnInit {
  filtro = {
    codigos: [],
    codigosPedidoFirme: [],
    clientes: [],
    fechaExpMaritimoInicio: Utils.formatDate(Utils.getPreviousMonth()),
    fechaExpMaritimoFin: Utils.formatDate(new Date()),
    pagos: [],
    idDespacho: Constantes.P_ID_CONTENEDOR
  };

  filtroClientes: Cliente[] = [];

  listado: DocumentoMaritimo[];
  selectedDocumento: DocumentoMaritimo;

  pagos: Parametro[] = [];
  estados: Parametro[] = [];

  selectedItemsLabel: string = '{0} items seleccionados';
  displaySelectedLabel: boolean = true;
  maxSelectedLabels: number = 1;
  minSearchCharacters: number = 3;

  constructor(
    private router: Router,
    private breadcrumb2Service: Breadcrumb2Service,
    public messageService: MessageService,
    private modalService: NgbModal,
    private settingsService: SettingsService,
    private configuracionService: ConfiguracionService,
    private service: DocumentoMaritimoService
  ) { }

  ngOnInit(): void {
    this.inicializarBreadcrumb();
    this.inicializar();
    this.consultar();
  }

  inicializarBreadcrumb(){
    this.breadcrumb2Service.addBreadcrumbs(LISTAR_DOCUMENTOMARITIMO_CONTENEDORES);
  }

  inicializar() {
    const filtroStg = sessionStorage.getItem(Constantes.P_STG_MARITIMO_CONTENEDORES_FILTRO);
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

  irACrear() {
    this.router.navigate(['/exportaciones/documentomaritimo/contenedores/crear']);
  }

  irAEditar(documento: DocumentoMaritimo) {
    this.service.setDocumento(documento);
    this.router.navigate(['/exportaciones/documentomaritimo/contenedores/editar', documento.id]);
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

  consultar() {
    sessionStorage.setItem(Constantes.P_STG_MARITIMO_CONTENEDORES_FILTRO, JSON.stringify(this.filtro));

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
    const name = 'DocumentoMaritimo_' + Utils.getCurrentTimeId() + '.xlsx';
    let exportData = [];
    this.listado.map(
      row => {
        exportData.push({
          codigo: row.codigo,
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
          fechaListaPrecio: row.fechaListaPrecio,
          codigoListaPrecio: row.codigoListaPrecio,
          nombreListaPrecio: row.nombreListaPrecio,
          codigoRuta: row.codigoRuta,
          nombreRuta: row.nombreRuta,
          nombreMoneda: row.nombreMoneda,
          nombreAgenteAduana: row.agenteAduana,
          shipper: row.shipper,
          direccion: row.direccionShipper,
          consignatario: row.consignatario,
          notificante: row.notificante,
          glosa: row.glosa,
          agenteNaviera: row.agenteNaviera,
          agenteCarga: row.agenteCarga,
          nave: row.nave,
          etaORigen: row.etaOrigen,
          etaDestino: row.etaDestino,
          fechaBlProgramado: row.fechaBlProgramado,
          fechaBlReal: row.fechaBlReal,
          fechaCarguio: row.fechaCarguio,
          fechaEnvioDocumento: row.fechaEnvioDocum,
          flete: row.flete,
          seguro: row.seguro,
          numeroContenedor: row.numeroContenedor,
          booking: row.booking,
          bl: row.bl,
          guiaDHL: row.guiaDhl,
          emitidoEn: row.emitidoEn,
          dam: row.dam,
          regimen: row.nombreRegimen,
          fechaEntrega: row.fechaEntrega,
          fechaManifiestoAduana: row.fechaManifAduana,
          fechaDam: row.fechaDam,
          fechaDamRegularizacion: row.fechaDamRegularizacion,
          fechaDam41: row.fechaDam41,
          nombreEstadoDocumento: row.estadodocumentoExport,
        });
    });

    const columns: any = {
      codigo: 'Código',
      nombreTipoTransporte: 'Tipo Transporte',
      codigoCliente: 'Código Cliente',
      nombreCliente: 'Cliente',
      codigoDestinatario: 'Código Destinatario',
      nombreDestinatario: 'Destinatario',
      codigoIncotermSap: 'Código Incoterm SAP',
      nombreIncotermSap: 'Incoterm SAP',
      codigoIncotermComercial: 'Código Incoterm Comercial',
      nombreIncotermComercial: 'Incoterm Comercial',
      codigoPuntoOrigen: 'Código Punto Origen',
      nombrePuntoOrigen: 'Punto Origen',
      codigoPuntoDestino: 'Código Punto Destino',
      nombreTipoDestino: 'Punto Destino',
      nombreTipoDespacho: 'Tipo Despacho',
      codigoCondicionPago: 'Código Condición Pago',
      nombreCondicionPago: 'Condición Pago',
      fechaListaPrecio: 'Fecha Lista Precio',
      codigoListaPrecio: 'Código Lista Precio',
      nombreListaPrecio: 'Lista Precio',
      codigoRuta: 'Código Ruta',
      nombreRuta: 'Ruta',
      nombreMoneda: 'Moneda',
      nombreAgenteAduana: 'Agente Aduana',
      shipper: 'Shipper',
      direccion: 'Dirección',
      consignatario: 'Consignatario',
      notificante: 'Notificante',
      glosa: 'Glosa',
      agenteNaviera: 'Agente Naviera',
      agenteCarga: 'Agente Carga',
      nave: 'Nave',
      etaORigen: 'ETA Origen',
      etaDestino: 'ETA Destino',
      fechaBlProgramado: 'Fecha BL Programado',
      fechaBlReal: 'Fecha BL Real',
      fechaCarguio: 'Fecha Carguío',
      fechaEnvioDocumento: 'Fecha Envío Documento',
      flete: 'Flete',
      seguro: 'Seguro',
      numeroContenedor: 'N° Contenedor',
      booking: 'Booking',
      bl: 'BL',
      guiaDHL: 'Guía DHL',
      emitidoEn: 'Emitido En',
      dam: 'DAM',
      regimen: 'Régimen',
      fechaEntrega: 'Fecha Entrega DAM a DRAWBACK',
      fechaManifiestoAduana: 'Fecha Manifiesto Aduana',
      fechaDam: 'Fecha DAM',
      fechaDamRegularizacion: 'Fecha DAM Regularización',
      fechaDam41: 'Fecha DAM41',
      nombreEstadoDocumento: 'Estado'
    };
    exportData.unshift(columns);

    const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(exportData, { skipHeader: true });
    const book: XLSX.WorkBook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(book, worksheet, 'Documentos Maritimos');

    XLSX.writeFile(book, name);
  }

  mostrarModal(content: string, item: DocumentoMaritimo) {
    this.selectedDocumento = item;
    this.modalService.open(content, { centered: true });
  }

  eliminar() {
    if(this.selectedDocumento == null) return;

    this.settingsService.mostrarSpinner();
    Promise.all([
      this.service.eliminar(this.selectedDocumento.id, this.settingsService.getUsername()).toPromise()
    ]).then(
      (data :any[]) => {
        this.settingsService.ocultarSpinner();
        this.messageService.add({
          severity: "success",
          summary: 'Información',
          detail: JSON.stringify('El documento ' + this.selectedDocumento.codigo + ' ha sido eliminado.')
        });
        this.selectedDocumento == null;
        this.consultar();
      },
      (err) => {
        this.modalService.dismissAll();
        this.settingsService.ocultarSpinner();
        this.messageService.add({
          severity: "error",
			    summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
			    detail: JSON.stringify(err.message)
        });
      }
    );
  }
}
