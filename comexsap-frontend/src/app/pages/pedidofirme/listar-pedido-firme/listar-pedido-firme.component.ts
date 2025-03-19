import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { ConfiguracionService } from '../../configuracion/configuracion.sevice';
import { Cliente } from '../../configuracion/to/cliente';
import { Parametro } from '../../configuracion/to/parametro';
import { PedidoFirmeService } from '../pedidofirme.service';
import { Utils } from 'src/app/utils/utils';
import * as XLSX from 'xlsx';

import { Breadcrumb2Service } from 'src/app/shared/breadcrumb2.service';
import { LISTAR_PEDIDOFIRME } from 'src/app/shared/breadcrumb/breadcrumb';
import { PedidoFirme } from '../to/pedidofirme';
import { Constantes } from 'src/app/utils/constantes';

@Component({
  selector: 'app-listar-pedido-firme',
  templateUrl: './listar-pedido-firme.component.html',
  styleUrls: ['./listar-pedido-firme.component.scss']
})
export class ListarPedidoFirmeComponent implements OnInit {
  filtro = {
    pedidos: [],
    clientes: [],
    fechaPedidoFirmeInicio: Utils.formatDate(Utils.getPreviousYear()),
    fechaPedidoFirmeFin: Utils.formatDate(new Date()),
    pagos: [],
    incoterms: [],
    documentos: []
  };

  filtroClientes: Cliente[] = [];

  selectedPedidoFirme: any;
  nuevoCodigoPedidoFirme: string = "";

  listado: PedidoFirme[];

  codigos: string[] = [];
  clientes: Cliente[] = [];
  pagos: Parametro[] = [];
  incoterms: Parametro[] = [];
  estados: Parametro[] = [];

  selectedItemsLabel: string = '{0} items seleccionados';
  displaySelectedLabel: boolean = true;
  maxSelectedLabels: number = 1;
  minSearchCharacters: number = 3;

  esPerfilFechaDisponibilidad: boolean = false;

  constructor(
    private router: Router,
    private breadcrumb2Service: Breadcrumb2Service,
    public messageService: MessageService,
    private modalService: NgbModal,
    private settingsService: SettingsService,
    private configuracionService: ConfiguracionService,
    private pedidoFirmeService: PedidoFirmeService
  ) { }

  ngOnInit(): void {
    this.esPerfilFechaDisponibilidad = this.settingsService.existeRol(Constantes.P_ROL_FECHA_DISPONIBILIDAD);
    this.inicializarBreadcrumb();
    this.inicializar();
    this.consultar();
  }

  inicializarBreadcrumb(){
    this.breadcrumb2Service.addBreadcrumbs(LISTAR_PEDIDOFIRME);
  }

  inicializar() {
    const filtroStg = sessionStorage.getItem(Constantes.P_STG_PEDIDOFIRME_FILTRO);
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
      this.configuracionService.listarParametroxDominio(Constantes.P_D003).toPromise(),//Incoterm
      this.configuracionService.listarParametroxDominio(Constantes.P_D010).toPromise(),//Estado
    ]).then(
      (data: any[]) => {
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

  seleccionarCodigos(items) {
    this.filtro.pedidos = items;
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
    sessionStorage.setItem(Constantes.P_STG_PEDIDOFIRME_FILTRO, JSON.stringify(this.filtro));

    let request : any = {};
    request.formulario = this.filtro;
    request.usuario = this.settingsService.getUsername();
    this.settingsService.mostrarSpinner();
    Promise.all([
      this.pedidoFirmeService.listar(request).toPromise()
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
    ).catch(function(err) {
			this.settingsService.ocultarSpinner();
			this.messageService.add({
			  severity: "error",
			    summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
			    detail: JSON.stringify(err.message)
			});
		});
  }

  irACrear() {
    this.router.navigate(['/exportaciones/pedidofirme/crear-pedidofirme']);
  }

  irAEditar(pedidoFirme: PedidoFirme) {
    this.pedidoFirmeService.setPedidoFirme(pedidoFirme);
    this.router.navigate(['/exportaciones/pedidofirme/editar-pedidofirme', pedidoFirme.id]);
  }

  copiarDocumento() {
    if(this.selectedPedidoFirme == null) return;
    if(this.nuevoCodigoPedidoFirme == null || this.nuevoCodigoPedidoFirme == "") {
      this.messageService.add({
        severity: "warn",
        summary: this.settingsService.MENSAJES['mensaje_validacion_error_campo_requerido'],
        detail: JSON.stringify('Ingrese un nuevo número de documento, por favor.')
      });
      return;
    }

    this.nuevoCodigoPedidoFirme = this.nuevoCodigoPedidoFirme.toUpperCase();
    if(!Utils.isValidCodePer(this.nuevoCodigoPedidoFirme)) {
      this.messageService.add({
        severity: "warn",
        summary: 'Formato no válido',
        detail: JSON.stringify('El formato del número de documento no es válido. Ejm: PER.XXX.XX')
      });
      return;
    }

    const copyCot = {
      idPedido: this.selectedPedidoFirme.id,
      codigoNuevoPedido: this.nuevoCodigoPedidoFirme.trim()
    }

    let request : any = {};
    request.formulario = copyCot;
    request.usuario = this.settingsService.getUsername();
    this.settingsService.mostrarSpinner();
    Promise.all([
      this.pedidoFirmeService.copiar(request).toPromise()
    ]).then(
      (data : any[]) => {
        this.settingsService.ocultarSpinner();
        if(data[0].cod_rpta == 1) {
          this.modalService.dismissAll();
          this.messageService.add({
            severity: "success",
            summary: 'Información',
            detail: JSON.stringify('Documento ' + this.nuevoCodigoPedidoFirme + ' ha sido creado.')
          });
          this.selectedPedidoFirme == null;
          this.nuevoCodigoPedidoFirme = "";
          this.consultar();
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
    if(this.selectedPedidoFirme == null) return;

    this.settingsService.mostrarSpinner();
    Promise.all([
      this.pedidoFirmeService.anular(this.selectedPedidoFirme.id, this.settingsService.getUsername()).toPromise()
    ]).then(
      (data: any[]) => {
        this.settingsService.ocultarSpinner();
        this.modalService.dismissAll();
        if(data[0].cod_rpta == '1') {
          this.messageService.add({
            severity: "success",
            summary: 'Información',
            detail: JSON.stringify('El documento ' + this.selectedPedidoFirme.codigoPedido + ' ha sido anulado.')
          });
          this.selectedPedidoFirme == null;
          this.consultar();
        } else {
          this.messageService.add({
            severity: "warn",
            summary: 'Alerta',
            detail: JSON.stringify(data[0].rpta)
          });
        }
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
    ).catch(function(err) {
			this.settingsService.ocultarSpinner();
			this.messageService.add({
			  severity: "error",
			    summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
			    detail: JSON.stringify(err.message)
			});
		});

  }

  descargar(): void {
    if(this.listado.length <= 0) {
      return;
    }
    const name = 'PedidosFirme_' + Utils.getCurrentTimeId() + '.xlsx';
    let exportData = [];
    this.listado.map(
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
          codigoIncotermComercial: row.codigoIncotermComercial,
          nombreIncotermComercial: row.nombreIncotermComercial,
          codigoPuertoOrigen: row.codigoPuertoOrigen,
          nombrePuertoOrigen: row.nombrePuertoOrigen,
          codigoPuertoDestino: row.codigoPuertoDestino,
          nombrePuertoDestino: row.nombrePuertoDestino,
          numeroContenedor: row.numeroContenedor,
          codigoLugarDespacho: row.codigoLugarDespacho,
          nombreLugarDespacho: row.nombreLugarDespacho,
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
          codigoFacturacion: row.codigoFacturacion,
          nombreFacturacion: row.nombreFacturacion,
          nombrePesoObjetivo: row.nombrePesoObjetivo,
          codigoToleranciaProduccion: row.codigoToleranciaProduccion,
          nombreToleranciaProd: row.nombreToleranciaProd,
          codigoPesoEtiqueta: row.codigoPesoEtiqueta,
          nombrePesoEtiqueta: row.nombrePesoEtiqueta,
          condicionDescarga: row.condicionDescarga,
          codigoAlmacenaje: row.codigoAlmacenaje,
          nombreAlmacenaje: row.nombreAlmacenaje,
          gastosOperativos: row.gastosOperativos,
          codigoNumeroEtiqueta: row.codigoNumeroEtiqueta,
          nombreNumeroEtiqueta: row.nombreNumeroEtiqueta,
          deal: row.deal,
          lote: row.lote,
          customerName: row.customerName,
          destinationPort: row.destinationPort,
          nombreEstadoDocumento: row.nombreEstadoDocumento
        });
    });

    const columns: any = {
      codigoPedido: 'Código',
      codigoCliente: 'Código Cliente',
      nombreCliente: 'Descripción',
      fechaSolicitud: 'Fecha Pedido Firme',
      codigoDestinatario: 'Código Destinatario',
      nombreDestinatario: 'Destinatario Mercancia',
      codigoIncoterm: 'Código Incoterm SAP',
      nombreIncoterm: 'Incoterm SAP',
      codigoIncotermComercial: 'Código Incoterm Comercial',
      nombreIncotermComercial: 'Incoterm Comercial',
      codigoPuertoOrigen: 'Código Puerto Origen',
      nombrePuertoOrigen: 'Puerto Origen',
      codigoPuertoDestino: 'Código Puerto Destino',
      nombrePuertoDestino: 'Puerto Destino',
      numeroContenedor: 'N° Contenedor',
      codigoLugarDespacho: 'Código lugar despacho',
      nombreLugarDespacho: 'Lugar Despacho',
      codigoTipoTransporte: 'Código Tipo Transporte',
      nombreTipoTransporte: 'TipoTransporte',
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
      codigoFacturacion: 'Código facturación',
      nombreFacturacion: 'Facturación',
      nombrePesoObjetivo: 'Peso objetivo',
      codigoToleranciaProduccion: 'Código tolerancia producción',
      nombreToleranciaProd: 'Tolerancia Producción',
      codigoPesoEtiqueta: 'Código peso etiqueta',
      nombrePesoEtiqueta: 'Peso Etiqueta',
      condicionDescarga: 'Condición Descarga',
      codigoAlmacenaje: 'Código almacenaje',
      nombreAlmacenaje: 'Almacenaje',
      gastosOperativos: 'Gastos operativos',
      codigoNumeroEtiqueta: 'Código número de etiqueta',
      nombreNumeroEtiqueta: 'Número de etiqueta',
      deal: 'DEAL',
      lote: 'lote',
      customerName: 'Customer name',
      destinationPort: 'Destination port',
      nombreEstadoDocumento: 'Estado'
    };
    exportData.unshift(columns);

    const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(exportData, { skipHeader: true });
    const book: XLSX.WorkBook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(book, worksheet, 'Pedidos Firme');

    XLSX.writeFile(book, name);
  }

  mostrarModal(content: string, data: any) {
    this.selectedPedidoFirme = data;
    this.nuevoCodigoPedidoFirme = "";
    this.modalService.open(content, { centered: true });
  }
}
