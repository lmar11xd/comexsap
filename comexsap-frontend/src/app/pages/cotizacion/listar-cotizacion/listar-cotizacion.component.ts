import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem, MessageService } from 'primeng/api';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { ConfiguracionService } from '../../configuracion/configuracion.sevice';
import { Cliente } from '../../configuracion/to/cliente';
import { Parametro } from '../../configuracion/to/parametro';
import { CotizacionService } from '../cotizacion.service';
import { Utils } from 'src/app/utils/utils';
import * as XLSX from 'xlsx';

import { Breadcrumb2Service } from 'src/app/shared/breadcrumb2.service';
import { LISTAR_COTIZACION } from 'src/app/shared/breadcrumb/breadcrumb';
import { Cotizacion } from '../to/cotizacion';
import { Constantes } from 'src/app/utils/constantes';

@Component({
  selector: 'app-listar-cotizacion',
  templateUrl: './listar-cotizacion.component.html',
  styleUrls: ['./listar-cotizacion.component.scss']
})
export class ListarCotizacionComponent implements OnInit {
  filtro = {
    pedidos: [],
    clientes: [],
    fechaCotizacionInicio: Utils.formatDate(Utils.getPreviousYear()),
    fechaCotizacionFin: Utils.formatDate(new Date()),
    pagos: [],
    incoterms: [],
    documentos: []
  };

  filtroClientes: Cliente[] = [];

  selectedCotizacion: any;
  nuevoCodigoCotizacion: string = "";

  listaCotizaciones: Cotizacion[];

  codigos: string[] = [];
  clientes: Cliente[] = [];
  pagos: Parametro[] = [];
  incoterms: Parametro[] = [];
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
    private cotizacionService: CotizacionService
  ) { }

  ngOnInit(): void {
    this.inicializarBreadcrumb();
    this.inicializar();
    this.consultarCotizaciones();
  }

  inicializarBreadcrumb(){
    this.breadcrumb2Service.addBreadcrumbs(LISTAR_COTIZACION);
  }

  inicializar() {
    const filtroStg = sessionStorage.getItem(Constantes.P_STG_COTIZACION_FILTRO);
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
      this.configuracionService.listarParametroxDominio(Constantes.P_D034).toPromise(),//Condicion Pago Cotizacion
      this.configuracionService.listarParametroxDominio(Constantes.P_D003).toPromise(),//Incoterm
      this.configuracionService.listarParametroxDominio(Constantes.P_D010).toPromise(),//Estado
    ]).then(
      (data :any[]) => {
        this.settingsService.ocultarSpinner();
        this.pagos = Parametro.toArray(data[0]);
        this.incoterms = Parametro.toArray(data[1]);
        this.estados = Parametro.toArray(data[2]);
      },
      (err) => {
        this.settingsService.ocultarSpinner();
        this.messageService.add({
          severity: "error",
          summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
          detail: JSON.stringify(err.message)
        });
      }
    ).catch(function(err) {
			this.settingsService.ocultarSpinner();
			this.messageService.add({
			  severity: "error",
			  summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
			  detail: JSON.stringify(err.message)
			});
		});
  }

  consultarCotizaciones() {
    sessionStorage.setItem(Constantes.P_STG_COTIZACION_FILTRO, JSON.stringify(this.filtro));

    let request : any = {};
    request.formulario = this.filtro;
    request.usuario = this.settingsService.getUsername();
    this.settingsService.mostrarSpinner();
    Promise.all([
      this.cotizacionService.listarCotizacionesxFiltro(request).toPromise()
    ]).then(
      (data :any[]) => {
        this.settingsService.ocultarSpinner();
        this.listaCotizaciones = data[0];
      },
      (err) => {
        this.settingsService.ocultarSpinner();
        this.messageService.add({
          severity: "error",
			    summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
			    detail: JSON.stringify(err.message)
        });
      }
    ).catch(function(err) {
			this.settingsService.ocultarSpinner();
			this.messageService.add({
			  severity: "error",
			    summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
			    detail: JSON.stringify(err.message)
			});
		});
  }

  crearCotizacion() {
    this.router.navigate(['/exportaciones/cotización/crear-cotizacion']);
  }

  editarCotizacion(cotizacion: Cotizacion) {
    this.cotizacionService.setCotizacion(cotizacion);
    this.router.navigate(['/exportaciones/cotización/editar-cotizacion', cotizacion.idCotizacion]);
  }

  copiarCotizacion() {
    if(this.selectedCotizacion == null) return;
    if(this.nuevoCodigoCotizacion == null || this.nuevoCodigoCotizacion == "") {
      this.messageService.add({
        severity: "warn",
        summary: this.settingsService.MENSAJES['mensaje_validacion_error_campo_requerido'],
        detail: JSON.stringify('Ingrese un nuevo número de documento, por favor.')
      });
      return;
    }

    this.nuevoCodigoCotizacion = this.nuevoCodigoCotizacion.toUpperCase();
    if(!Utils.isValidCodePer(this.nuevoCodigoCotizacion)) {
      this.messageService.add({
        severity: "warn",
        summary: 'Formato no válido',
        detail: JSON.stringify('El formato del número de documento no es válido. Ejm: PER.XXX.XX')
      });
      return;
    }

    const copyCot = {
      idPedido: this.selectedCotizacion.idCotizacion,
      codigoNuevoPedido: this.nuevoCodigoCotizacion.trim()
    }

    let request : any = {};
    request.formulario = copyCot;
    request.usuario = this.settingsService.getUsername();
    this.settingsService.mostrarSpinner();
    Promise.all([
      this.cotizacionService.copiarCotizacion(request).toPromise()
    ]).then(
      (data : any[]) => {
        this.settingsService.ocultarSpinner();

        if(data[0].cod_rpta == 1) {
          this.modalService.dismissAll();
          this.messageService.add({
            severity: "success",
            summary: 'Información',
            detail: JSON.stringify('Documento ' + this.nuevoCodigoCotizacion + ' ha sido creado.')
          });
          this.selectedCotizacion == null;
          this.nuevoCodigoCotizacion = "";
          this.consultarCotizaciones();
        } else {
          this.messageService.add({
            severity: "warn",
            summary: 'Validación',
            detail: JSON.stringify(data[0].rpta)
          });
        }
      },
      (err) => {
        this.settingsService.ocultarSpinner();
        this.messageService.add({
          severity: "error",
			    summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
			    detail: JSON.stringify(err.message)
        });
      }
    ).catch(function(err) {
			this.settingsService.ocultarSpinner();
			this.messageService.add({
			  severity: "error",
        summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
        detail: JSON.stringify(err.message)
			});
		});
  }

  anular() {
    if(this.selectedCotizacion == null) return;

    this.settingsService.mostrarSpinner();
    Promise.all([
      this.cotizacionService.anularCotizacion(this.selectedCotizacion.idCotizacion, this.settingsService.getUsername()).toPromise()
    ]).then(
      (data :any[]) => {
        this.settingsService.ocultarSpinner();
        this.modalService.dismissAll();
        this.messageService.add({
          severity: "success",
          summary: 'Información',
          detail: JSON.stringify('El documento ' + this.selectedCotizacion.codigoPedido + ' ha sido anulado.')
        });
        this.selectedCotizacion == null;
        this.consultarCotizaciones();
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

  descargar(): void {
    if(this.listaCotizaciones.length <= 0) {
      return;
    }
    const name = 'Cotizaciones_' + Utils.getCurrentTimeId() + '.xlsx';
    let exportData = [];
    this.listaCotizaciones.map(
      row => {
        exportData.push({
          codigoPedido: row.codigoPedido,
          codigoCliente: row.codigoCliente,
          nombreCliente: row.nombreCliente,
          fechaSolicitud: Utils.dateToShortFormat(row.fechaSolicitud),
          codigoDestinatario: row.codigoDestinatario,
          nombreDestinatario: row.nombreDestinatario,
          codigoIncoterm: row.codigoIncoterm,
          nombreIncoterm: row.nombreIncoterm,
          codigoPuertoOrigen: row.codigoPuertoOrigen,
          nombrePuertoOrigen: row.nombrePuertoOrigen,
          codigoPuertoDestino: row.codigoPuertoDestino,
          nombrePuertoDestino: row.nombrePuertoDestino,
          codigoTipoTransporte: row.codigoTipoTransporte,
          nombreTipoTransporte: row.nombreTipoTransporte,
          codigoDespacho: row.codigoDespacho,
          nombreDespacho: row.nombreDespacho,
          codigoCondicionPago: row.codigoCondicionPago,
          nombreCondicionPago: row.nombreCondicionPago,
          fechaListaPrecio: Utils.dateToShortFormat(row.fechaListaPrecio),
          codigoListaPrecio: row.codigoListaPrecio,
          nombreListaPrecio: row.nombreListaPrecio,
          codigoRuta: row.codigoRuta,
          nombreRuta: row.nombreRuta,
          codigoMoneda: row.codigoMoneda,
          nombreMoneda: row.nombreMoneda,
          nombreEstadoDocumento: row.nombreEstadoDocumento
        });
    });

    const columns: any = {
      codigoPedido: 'Código',
      codigoCliente: 'Código Cliente',
      nombreCliente: 'Descripción',
      fechaSolicitud: 'Fecha Cotización',
      codigoDestinatario: 'Código Destinatario',
      nombreDestinatario: 'Destinatario Mercancia',
      codigoIncoterm: 'Código Incoterm',
      nombreIncoterm: 'Incoterm',
      codigoPuertoOrigen: 'Código Puerto Origen',
      nombrePuertoOrigen: 'Puerto Origen',
      codigoPuertoDestino: 'Código Puerto Destino',
      nombrePuertoDestino: 'Puerto Destino',
      codigoTipoTransporte: 'Código Tipo Transporte',
      nombreTipoTransporte: 'Tipo Transporte',
      codigoDespacho: 'Código Despacho',
      nombreDespacho: 'Despacho',
      codigoCondicionPago: 'Código Condición Pago',
      nombreCondicionPago: 'Condición Pago',
      fechaListaPrecio: 'Fecha Lista Precio',
      codigoListaPrecio: 'Código Lista Precio',
      nombreListaPrecio: 'Lista Precio',
      codigoRuta: 'Código Ruta',
      nombreRuta: 'Ruta',
      codigoMoneda: 'Código Moneda',
      nombreMoneda: 'Moneda',
      nombreEstadoDocumento: 'Estado',
    };
    exportData.unshift(columns);

    const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(exportData, { skipHeader: true });
    const book: XLSX.WorkBook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(book, worksheet, 'Cotizaciones');

    XLSX.writeFile(book, name);
  }

  seleccionarCodigos(items) {
    this.filtro.pedidos = items;
  }

  openModal(content: string, data: any) {
    this.selectedCotizacion = data;
    this.nuevoCodigoCotizacion = "";
    this.modalService.open(content, { centered: true, backdrop: 'static', keyboard: false });
  }

  seleccionarClientes(items: Cliente[]) {
    let codigos: string[] = [];
    items.forEach(cliente => {
      codigos.push(cliente.codigo);
    });
    this.filtro.clientes = [...codigos];
    this.filtroClientes = [...items];
  }
}
