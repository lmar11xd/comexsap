import { Component, OnInit } from '@angular/core';
import { Parametro } from '../../configuracion/to/parametro';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MessageService } from 'primeng/api';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { LISTAR_DOCUMENTOTERRESTRE } from 'src/app/shared/breadcrumb/breadcrumb';
import { Breadcrumb2Service } from 'src/app/shared/breadcrumb2.service';
import { Utils } from 'src/app/utils/utils';
import { ConfiguracionService } from '../../configuracion/configuracion.sevice';
import { Cliente } from '../../configuracion/to/cliente';
import { DocumentoTerrestre } from '../to/documentoterrestre';
import * as XLSX from 'xlsx';
import { DocumentoTerrestreService } from '../documentoterrestre.service';
import { Constantes } from 'src/app/utils/constantes';

@Component({
  selector: 'app-listar',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.scss']
})
export class ListarComponent implements OnInit {
  filtro = {
    codigos: [],
    folio: null,
    clientes: [],
    fechaInicio: null,
    fechaFin: null,
    pagos: []
  };

  filtroClientes: Cliente[] = [];

  listado: DocumentoTerrestre[];

  pagos: Parametro[] = [];
  estados: Parametro[] = [];
  dataDocumento: DocumentoTerrestre;
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
    private service: DocumentoTerrestreService
  ) { }

  ngOnInit(): void {
    this.inicializarBreadcrumb();
    this.inicializar();
    this.consultar();
  }

  inicializarBreadcrumb(){
    this.breadcrumb2Service.addBreadcrumbs(LISTAR_DOCUMENTOTERRESTRE);
  }

  inicializar() {
    const filtroStg = sessionStorage.getItem(Constantes.P_STG_TERRESTRE_FILTRO);
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
      this.configuracionService.listarParametroxDominio(Constantes.P_D011).toPromise(),//Condicion Pago
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
    this.router.navigate(['/exportaciones/documentoterrestre/crear']);
  }

  irAEditar(documento: DocumentoTerrestre) {
    this.service.setDocumento(documento);
    this.router.navigate(['/exportaciones/documentoterrestre/editar', documento.id]);
  }

  seleccionarCodigos(items) {
    this.filtro.codigos = items;
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
    sessionStorage.setItem(Constantes.P_STG_TERRESTRE_FILTRO, JSON.stringify(this.filtro));

    let request : any = {};
    request.formulario = {...this.filtro};
    request.usuario = this.settingsService.getUsername();

    if(request.formulario.folio != null) {
      request.formulario.folio = '%' + request.formulario.folio + '%';
    }

    this.settingsService.mostrarSpinner();
    Promise.all([
      this.service.listar(request).toPromise()
    ]).then(
      (data :any[]) => {
        this.listado = data[0];
        this.settingsService.ocultarSpinner();
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
    const name = 'DocumentoTerrestre_' + Utils.getCurrentTimeId() + '.xlsx';
    let exportData = [];
    this.listado.map(
      row => {
        exportData.push({
          codigo: row.codigo,
          codigoFactura: row.codigoFactura,
          folioFactura: row.folioFactura,
          codigoCliente: row.codigoSapCliente,
          nombreCliente: row.nombreCliente,
          codigoDestinatario: row.codigoSapDestinatario,
          nombreDestinatario: row.nombreDestinatario,
          nombreOrigen: row.origen,
          nombreDestino: row.destino,
          codigoIncoterm: row.codigoIncoterm,
          nombreAlmacen: row.descripcionAlmacen,
          nombreCondicionPago: row.nombreCondicionPago,
          empresaTransporte: row.empresaTransporte,
          fechaLevante: row.fechaLevante,
          fechaTransito: row.fechaTransito,
          inicioEmbarque: row.inicioEmbarque,
          dam: row.dam,
          fechaDam: row.fechaDam,
        });
    });

    const columns: any = {
      codigo: 'Código',
      codigoFactura: 'Factura',
      folioFactura: 'Folio Factura',
      codigoCliente: 'Código Cliente',
      nombreCliente: 'Cliente',
      codigoDestinatario: 'Código Destinatario',
      nombreDestinatario: 'Destinatario',
      nombreOrigen: 'Punto origen',
      nombreDestino: 'Punto destino',
      codigoIncoterm: 'Incoterm',
      nombreAlmacen: 'Almacén',
      nombreCondicionPago: 'Condición Pago',
      empresaTransporte: 'Empresa transporte',
      fechaLevante: 'Fecha levante',
      fechaTransito: 'Fecha tránsito',
      inicioEmbarque: 'Inicio embarque',
      dam: 'DAM',
      fechaDam: 'Fecha DAM',
    };
    exportData.unshift(columns);

    const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(exportData, { skipHeader: true });
    const book: XLSX.WorkBook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(book, worksheet, 'Documentos Terrestres');

    XLSX.writeFile(book, name);
  }

  obtenerParametrosMasivos(){
    for(let i=0 ; i < this.listado.length; i++){
         Promise.all([
          this.service.obtener(this.listado[i].id.toString()).toPromise(),
        ]).then(
          (data :any[]) => {
            this.dataDocumento = null;
            this.dataDocumento = data[0].exportacion;
            this.descargarPdf(this.dataDocumento.codigo);
          },
          (err) => {
            this.settingsService.ocultarSpinner();
            this.messageService.add({
              severity: "warn",
              summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
              detail: JSON.stringify(err)
            });
          }
        );
    }
  }

  obtenerDatosCertificado() {
    const date = new Date();
    const data = {
      factura: this.dataDocumento.serieCodFactura,
      textoAduana: 'Aduana Nacional de Bolivia',
      textoCliente: 'La empresa ' + Constantes.P_CORP_AA_SA + ' certifica la venta al crédito a la firma',
      textoDestinatario: Constantes.P_CORP_AA_SRL + ' con ' + Constantes.P_CORP_AA_NIT + '  correspondiente',
      montoTotal: this.dataDocumento.importeFactura,
      formaPago: this.dataDocumento.nombreCondicionPago,
      textoFecha: date.getDate() + " días del mes de " + Utils.getNameMonth(date.getMonth() + 1) + " del " + date.getFullYear()
    };
    let request : any = {};
    request.formulario = data;
    request.usuario = this.settingsService.getUsername();

    return request;
  }

  descargarPdf(codigo: string) {
    const request = this.obtenerDatosCertificado();
    this.settingsService.mostrarSpinner();

    Promise.all([
      this.service.imprimirCertificadoTerrestre(request).toPromise()
    ]).then(
      (data) => {
        this.settingsService.ocultarSpinner();
        let url = window.URL.createObjectURL(data[0]);
        let a = document.createElement('a');
        document.body.appendChild(a);
        a.setAttribute('style', 'display: none');
        a.href = url;
        a.download = 'CertificadoVenta_' + codigo + '.pdf';
        a.click();
        window.URL.revokeObjectURL(url);
        a.remove();
      },
      (err) => {
        this.settingsService.ocultarSpinner();
        this.messageService.add({
          severity: "error",
          summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
          detail: JSON.stringify(err),
        });
      }
    );
  }
}
