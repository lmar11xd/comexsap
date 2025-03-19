import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MenuItem, MessageService } from 'primeng/api';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { Breadcrumb2Service } from 'src/app/shared/breadcrumb2.service';
import { ConfiguracionService } from '../../configuracion/configuracion.sevice';
import { DocumentoTerrestreService } from '../documentoterrestre.service';
import { DocumentoTerrestre } from '../to/documentoterrestre';
import { Centro } from '../../configuracion/to/centro';
import { Destinatario } from '../../configuracion/to/destinatario';
import { Parametro } from '../../configuracion/to/parametro';
import { DocumentoTerrestrePosicion } from '../to/documentoterrestreposicion';
import { EDITAR_DOCUMENTOTERRESTRE, LISTAR_DOCUMENTOTERRESTRE } from 'src/app/shared/breadcrumb/breadcrumb';
import { Constantes } from 'src/app/utils/constantes';
import { Utils } from 'src/app/utils/utils';

@Component({
  selector: 'app-editar',
  templateUrl: './editar.component.html',
  styleUrls: ['./editar.component.scss']
})
export class EditarComponent implements OnInit {
  @ViewChild('modalGuardadoExitoso') modalGuardadoExitoso: ElementRef;
  @ViewChild('modalVerPdf') modalVerPdf: ElementRef;

  pdfSrc: Uint8Array;

  active = 1;
  activeDatos = 1;
  activeContacto = 1;
  activeImpresion = 1;

  idDocumento: string = null;
  dataDocumento: DocumentoTerrestre;
  dataPosiciones: DocumentoTerrestrePosicion[] = [];

  documentoForm: FormGroup;

  destinatarios: Destinatario[] = [];
  pagos: Parametro[] = [];
  incoterms: Parametro[] = [];
  estados: Parametro[] = [];
  monedas: Parametro[] = [];
  despachos: Parametro[] = [];
  listaPrecios: Parametro[] = [];
  lugaresDespacho: Parametro[] = [];
  facturaciones: Parametro[] = [];
  pesosObjetivo: Parametro[] = [];
  tolerancias: Parametro[] = [];
  almacenajes: Parametro[] = [];
  pesosEtiqueta: Parametro[] = [];
  numerosEtiquetas: Parametro[] = [];

  agentesNaviera: Parametro[] = [];
  agentesCarga: Parametro[] = [];
  regimenes: Parametro[] = [];

  centros: Centro[] = [];
  partidas: Parametro[] = [];

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private breadcrumb2Service: Breadcrumb2Service,
    private messageService: MessageService,
    private modalService: NgbModal,
    private settingsService: SettingsService,
    private configuracionService: ConfiguracionService,
    private service: DocumentoTerrestreService
  ) { }

  ngOnInit(): void {
    this.idDocumento = this.activatedRoute.snapshot.paramMap.get("id");
    this.inicializarBreadcrumb(this.idDocumento);
    this.crearFormulario();
    this.inicializar();
  }

  inicializarBreadcrumb(id: string){
    EDITAR_DOCUMENTOTERRESTRE[0].url = EDITAR_DOCUMENTOTERRESTRE[0].url.replace(':id', id);
    let BREADCRUMBS: MenuItem[] = [];
    BREADCRUMBS = BREADCRUMBS.concat(LISTAR_DOCUMENTOTERRESTRE);
    BREADCRUMBS = BREADCRUMBS.concat(EDITAR_DOCUMENTOTERRESTRE);
    this.breadcrumb2Service.addBreadcrumbs(BREADCRUMBS);
  }

  inicializar() {
    this.settingsService.mostrarSpinner();
    Promise.all([
      this.configuracionService.listarParametroxDominio(Constantes.P_D011).toPromise(),//Condicion Pago
      this.configuracionService.listarParametroxDominio(Constantes.P_D003).toPromise(),//Incoterm
      this.configuracionService.listarParametroxDominio(Constantes.P_D010).toPromise(),//Estado
      this.configuracionService.listarParametroxDominio(Constantes.P_D006).toPromise(),//Moneda
      this.configuracionService.listarParametroxDominio(Constantes.P_D008).toPromise(),//Despacho
      this.configuracionService.listarParametroxDominio(Constantes.P_D013).toPromise(),//Lista Precios

      this.configuracionService.listarParametroxDominio(Constantes.P_D024).toPromise(),//Lugar de Despacho
      this.configuracionService.listarParametroxDominio(Constantes.P_D025).toPromise(),//Facturación
      this.configuracionService.listarParametroxDominio(Constantes.P_D026).toPromise(),//Peso Objetivo
      this.configuracionService.listarParametroxDominio(Constantes.P_D027).toPromise(),//Tolerancia
      this.configuracionService.listarParametroxDominio(Constantes.P_D029).toPromise(),//Almacenaje
      this.configuracionService.listarParametroxDominio(Constantes.P_D028).toPromise(),//Peso en Etiquetas
      this.configuracionService.listarParametroxDominio(Constantes.P_D030).toPromise(),//Número Etiqueta
      this.configuracionService.listarParametroxDominio(Constantes.P_D016).toPromise(),//Agente Naviera
      this.configuracionService.listarParametroxDominio(Constantes.P_D017).toPromise(),//Agente Carga
      this.configuracionService.listarParametroxDominio(Constantes.P_D022).toPromise(),//Régimen

      this.configuracionService.listarCentros("").toPromise(),
      this.configuracionService.listarParametroxDominio(Constantes.P_D023).toPromise(),//Partida Arancelarias

      this.service.obtener(this.idDocumento).toPromise(),
    ]).then(
      (data :any[]) => {
        this.settingsService.ocultarSpinner();
        this.pagos = Parametro.toArray(data[0]);
        this.incoterms = Parametro.toArray(data[1]);
        this.estados = Parametro.toArray(data[2]);
        this.monedas = Parametro.toArray(data[3]);
        this.despachos = Parametro.toArray(data[4]);
        this.listaPrecios = Parametro.toArray(data[5]);
        this.lugaresDespacho = Parametro.toArray(data[6]);
        this.facturaciones = Parametro.toArray(data[7]);
        this.pesosObjetivo = Parametro.toArray(data[8]);
        this.tolerancias = Parametro.toArray(data[9]);
        this.almacenajes = Parametro.toArray(data[10]);
        this.pesosEtiqueta = Parametro.toArray(data[11]);
        this.numerosEtiquetas = Parametro.toArray(data[12]);
        this.agentesNaviera = Parametro.toArray(data[13]);
        this.agentesCarga = Parametro.toArray(data[14]);
        this.regimenes = Parametro.toArray(data[15]);
        this.centros = Centro.toArray(data[16]);
        this.partidas = Parametro.toArray(data[17]);

        this.dataDocumento = data[18].exportacion;
        this.dataPosiciones = data[18].posiciones;

        if(this.dataPosiciones != null) {
          this.dataPosiciones.forEach((posicion: DocumentoTerrestrePosicion) => {
            const posicionForm = new FormGroup({
              id: new FormControl(posicion.id),
              item: new FormControl(posicion.item),
              codigoMaterial: new FormControl(posicion.codigoSap),
              descripcionMaterial: new FormControl(posicion.descripcionComercialProducto),
              cantidadVenta: new FormControl(posicion.cantidadVenta),
              unidadMedidaVenta: new FormControl(posicion.codigoSapUnidadMedidaVenta),
              centro: new FormControl(posicion.codigoSapCentro + ' - ' + posicion.descripcionCentro),
              almacen: new FormControl(posicion.codigoSapAlmacen + ' - ' + posicion.descripcionAlmacen),
              pesoToneladas: new FormControl(posicion.pesoTonelada),
              precioUnitario: new FormControl(posicion.precioUnitario),
              importe: new FormControl(posicion.importe)
            });
            this.posiciones.push(posicionForm);
          });
        }

        this.inicializarFormulario();
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

  crearFormulario() {
    this.documentoForm = new FormGroup({
      //Datos Documento Marítimo
      codigo: new FormControl(),
      cliente: new FormControl(),
      destinatario: new FormControl(),

      origen: new FormControl(),
      destino: new FormControl(),
      almacen: new FormControl(),
      idIncoterm: new FormControl(),
      idTipoDespacho: new FormControl(),
      idCondicionPago: new FormControl(),
      idMoneda: new FormControl(),

      facturaSap: new FormControl(),
      fechaFactura: new FormControl(),
      horaSunat: new FormControl(),
      folioFactura: new FormControl(),
      fechaSalida: new FormControl(),
      importe: new FormControl(),

      agenteAduana: new FormControl(),
      ordenAgenteAduana: new FormControl(),
      estadoOrden: new FormControl(),
      pesoReal: new FormControl(),
      tiempoRegularizacion: new FormControl(),
      fechaEntregaDocumento: new FormControl(),
      mesDespacho: new FormControl(),
      empresaTransporte: new FormControl(),
      canal: new FormControl(),
      comentario: new FormControl(),

      //Información Marítima
      dam: new FormControl(),
      fechaDam: new FormControl(),
      fechaDamRegularizacion: new FormControl(),
      fechaDam41: new FormControl(),
      inicioEmbarque: new FormControl(),
      fechaDamComex: new FormControl(),
      fechaDamDrawback: new FormControl(),
      fechaLevante: new FormControl(),
      fechaTransito: new FormControl(),

      posiciones: new FormArray([]),
      estadoDocumento: new FormControl()
    });
  }

  inicializarFormulario() {
    if(this.dataDocumento != null) {
      //Datos Documento Marítimo
      this.documentoForm.controls['codigo'].patchValue(this.dataDocumento.codigo, {onlySelf: true});
      this.documentoForm.controls['cliente'].patchValue(this.dataDocumento.codigoSapCliente + " - " + this.dataDocumento.nombreCliente, {onlySelf: true});
      this.documentoForm.controls['destinatario'].patchValue(this.dataDocumento.codigoSapDestinatario + " - " + this.dataDocumento.nombreDestinatario, {onlySelf: true});
      this.documentoForm.controls['origen'].patchValue(this.dataDocumento.origen, {onlySelf: true});
      this.documentoForm.controls['destino'].patchValue(this.dataDocumento.destino, {onlySelf: true});
      this.documentoForm.controls['almacen'].patchValue(this.dataDocumento.codigoAlmacen + " - " + this.dataDocumento.descripcionAlmacen, {onlySelf: true});
      this.documentoForm.controls['idIncoterm'].patchValue(this.dataDocumento.idIncoterm, {onlySelf: true});
      this.documentoForm.controls['idTipoDespacho'].patchValue(this.dataDocumento.idDespacho, {onlySelf: true});
      this.documentoForm.controls['idCondicionPago'].patchValue(this.dataDocumento.idCondicionPago, {onlySelf: true});
      this.documentoForm.controls['idMoneda'].patchValue(this.dataDocumento.idMoneda, {onlySelf: true});

      this.documentoForm.controls['facturaSap'].patchValue(this.dataDocumento.codigoFactura, {onlySelf: true});
      this.documentoForm.controls['fechaFactura'].patchValue(Utils.stringToDate(this.dataDocumento.fechaFactura), {onlySelf: true});
      this.documentoForm.controls['horaSunat'].patchValue(this.dataDocumento.horaSunatFactura, {onlySelf: true});
      this.documentoForm.controls['folioFactura'].patchValue(this.dataDocumento.folioFactura, {onlySelf: true});
      this.documentoForm.controls['fechaSalida'].patchValue(Utils.stringToDate(this.dataDocumento.fechaSalidaFactura), {onlySelf: true});
      this.documentoForm.controls['importe'].patchValue(this.dataDocumento.importeFactura, {onlySelf: true});

      this.documentoForm.controls['agenteAduana'].patchValue(this.dataDocumento.agenteAduana, {onlySelf: true});
      this.documentoForm.controls['ordenAgenteAduana'].patchValue(this.dataDocumento.ordenAgenteAduana, {onlySelf: true});
      this.documentoForm.controls['estadoOrden'].patchValue(this.dataDocumento.estadoOrden, {onlySelf: true});
      this.documentoForm.controls['pesoReal'].patchValue(this.dataDocumento.pesoReal, {onlySelf: true});
      this.documentoForm.controls['tiempoRegularizacion'].patchValue(this.dataDocumento.tiempoRegulDam41 == 0 ? null : this.dataDocumento.tiempoRegulDam41, {onlySelf: true});
      this.documentoForm.controls['fechaEntregaDocumento'].patchValue(Utils.stringToDate(this.dataDocumento.fechaEntregaDocu), {onlySelf: true});
      this.documentoForm.controls['mesDespacho'].patchValue(this.dataDocumento.mesDespacho, {onlySelf: true});
      this.documentoForm.controls['empresaTransporte'].patchValue(this.dataDocumento.empresaTransporte, {onlySelf: true});
      this.documentoForm.controls['canal'].patchValue(this.dataDocumento.canalControlExport, {onlySelf: true});
      this.documentoForm.controls['comentario'].patchValue(this.dataDocumento.comentario, {onlySelf: true});

      this.documentoForm.controls['dam'].patchValue(this.dataDocumento.dam, {onlySelf: true});
      this.documentoForm.controls['fechaDam'].patchValue(Utils.stringToDate(this.dataDocumento.fechaDam), {onlySelf: true});
      this.documentoForm.controls['fechaDamRegularizacion'].patchValue(Utils.stringToDate(this.dataDocumento.fechaDamRegularizacion), {onlySelf: true});
      this.documentoForm.controls['fechaDam41'].patchValue(Utils.stringToDate(this.dataDocumento.fechaDam41), {onlySelf: true});
      this.documentoForm.controls['inicioEmbarque'].patchValue(Utils.stringToDate(this.dataDocumento.inicioEmbarque), {onlySelf: true});
      this.documentoForm.controls['fechaDamComex'].patchValue(Utils.stringToDate(this.dataDocumento.fechaEntregaDua), {onlySelf: true});
      this.documentoForm.controls['fechaDamDrawback'].patchValue(Utils.stringToDate(this.dataDocumento.fechaEntrega), {onlySelf: true});
      this.documentoForm.controls['fechaLevante'].patchValue(Utils.stringToDatetime(this.dataDocumento.fechaLevante), {onlySelf: true});
      this.documentoForm.controls['fechaTransito'].patchValue(Utils.stringToDatetime(this.dataDocumento.fechaTransito), {onlySelf: true});

      this.documentoForm.controls['posiciones'].patchValue([], {onlySelf: true});
      this.documentoForm.controls['estadoDocumento'].patchValue(this.dataDocumento.idEstadoDocumento, {onlySelf: true});

      //DESAHIBILITAR CABECERA PEDIDO
      this.documentoForm.controls['codigo'].disable();
      this.documentoForm.controls['cliente'].disable();
      this.documentoForm.controls['destinatario'].disable();
      this.documentoForm.controls['origen'].disable();
      this.documentoForm.controls['destino'].disable();
      this.documentoForm.controls['almacen'].disable();
      this.documentoForm.controls['idIncoterm'].disable();
      this.documentoForm.controls['idTipoDespacho'].disable();
      this.documentoForm.controls['idCondicionPago'].disable();
      this.documentoForm.controls['idMoneda'].disable();

      this.documentoForm.controls['facturaSap'].disable();
      this.documentoForm.controls['fechaFactura'].disable();
      this.documentoForm.controls['horaSunat'].disable();
      this.documentoForm.controls['folioFactura'].disable();
      this.documentoForm.controls['fechaSalida'].disable();
      this.documentoForm.controls['importe'].disable();

      this.documentoForm.controls['pesoReal'].disable();

      this.documentoForm.controls['codigo'].setValidators([Validators.required]);
      this.documentoForm.controls['cliente'].setValidators([Validators.required]);
      this.documentoForm.controls['destinatario'].setValidators([Validators.required]);
      this.documentoForm.controls['origen'].setValidators([Validators.required]);
      this.documentoForm.controls['destino'].setValidators([Validators.required]);
      this.documentoForm.controls['almacen'].setValidators([Validators.required]);
      this.documentoForm.controls['idIncoterm'].setValidators([Validators.required]);
      this.documentoForm.controls['idTipoDespacho'].setValidators([Validators.required]);
      this.documentoForm.controls['idCondicionPago'].setValidators([Validators.required]);
      this.documentoForm.controls['idMoneda'].setValidators([Validators.required]);

      this.documentoForm.controls['facturaSap'].setValidators([Validators.required]);
      this.documentoForm.controls['fechaFactura'].setValidators([Validators.required]);
      this.documentoForm.controls['horaSunat'].setValidators([Validators.required]);
      this.documentoForm.controls['folioFactura'].setValidators([Validators.required]);
      this.documentoForm.controls['fechaSalida'].setValidators([Validators.required]);
      this.documentoForm.controls['importe'].setValidators([Validators.required]);

      this.documentoForm.controls['pesoReal'].setValidators([Validators.required]);

      if(this.dataDocumento.idEstadoDocumento == 6) {
        this.documentoForm.controls['agenteAduana'].disable();
        this.documentoForm.controls['ordenAgenteAduana'].disable();
        this.documentoForm.controls['estadoOrden'].disable();
        this.documentoForm.controls['tiempoRegularizacion'].disable();
        this.documentoForm.controls['fechaEntregaDocumento'].disable();
        this.documentoForm.controls['mesDespacho'].disable();
        this.documentoForm.controls['empresaTransporte'].disable();
        this.documentoForm.controls['canal'].disable();
        this.documentoForm.controls['comentario'].disable();

        this.documentoForm.controls['dam'].disable();
        this.documentoForm.controls['fechaDam'].disable();
        this.documentoForm.controls['fechaDamRegularizacion'].disable();
        this.documentoForm.controls['fechaDam41'].disable();
        this.documentoForm.controls['inicioEmbarque'].disable();
        this.documentoForm.controls['fechaDamComex'].disable();
        this.documentoForm.controls['fechaDamDrawback'].disable();
        this.documentoForm.controls['fechaLevante'].disable();
        this.documentoForm.controls['fechaTransito'].disable();

        this.documentoForm.controls['agenteAduana'].setValidators([Validators.required]);
        this.documentoForm.controls['ordenAgenteAduana'].setValidators([Validators.required]);
        this.documentoForm.controls['estadoOrden'].setValidators([Validators.required]);
        this.documentoForm.controls['tiempoRegularizacion'].setValidators([Validators.required]);
        this.documentoForm.controls['fechaEntregaDocumento'].setValidators([Validators.required]);
        this.documentoForm.controls['mesDespacho'].setValidators([Validators.required]);
        this.documentoForm.controls['empresaTransporte'].setValidators([Validators.required]);
        this.documentoForm.controls['canal'].setValidators([Validators.required]);
        this.documentoForm.controls['comentario'].setValidators([Validators.required]);

        this.documentoForm.controls['dam'].setValidators([Validators.required]);
        this.documentoForm.controls['fechaDam'].setValidators([Validators.required]);
        this.documentoForm.controls['fechaDamRegularizacion'].setValidators([Validators.required]);
        this.documentoForm.controls['fechaDam41'].setValidators([Validators.required]);
        this.documentoForm.controls['inicioEmbarque'].setValidators([Validators.required]);
        this.documentoForm.controls['fechaDamComex'].setValidators([Validators.required]);
        this.documentoForm.controls['fechaDamDrawback'].setValidators([Validators.required]);
        this.documentoForm.controls['fechaLevante'].setValidators([Validators.required]);
        this.documentoForm.controls['fechaTransito'].setValidators([Validators.required]);
      }
    }
  }

  irAlListado() {
    this.router.navigate(['/exportaciones/documentoterrestre/listar']);
  }

  limpiarValidadores() {
    //this.documentoForm.controls['codigo'].clearValidators();
  }

  refrescarValidadores() {
    //this.documentoForm.controls['codigo'].updateValueAndValidity();
  }

  get posiciones() {
    return this.documentoForm.controls["posiciones"] as FormArray;
  }

  guardar() {
    const documentoFormData = this.documentoForm.value;

    const fechaLevante = documentoFormData.fechaLevante ? new Date(documentoFormData.fechaLevante.replace('T', ' ')): null;
    const fechaTransito = documentoFormData.fechaTransito ? new Date(documentoFormData.fechaTransito.replace('T', ' ')): null;

    const cabecera = {
      id: this.dataDocumento.id,
      empresaTransporte: documentoFormData.empresaTransporte,
      inicioEmbarque: documentoFormData.inicioEmbarque,
      canal: documentoFormData.canal,
      comentario: documentoFormData.comentario,
      fechaLevante: fechaLevante,
      fechaTransito: fechaTransito,
      ordenAgenteAduana: documentoFormData.ordenAgenteAduana,
      estadoOrden: documentoFormData.estadoOrden,
      dam: documentoFormData.dam,
      fechaDam: documentoFormData.fechaDam,
      mesDespacho: documentoFormData.mesDespacho,
      fechaDam41: documentoFormData.fechaDam41,
      agenteAduana: documentoFormData.agenteAduana,
      fechaDamRegularizacion: documentoFormData.fechaDamRegularizacion,
      fechaEntregaDua: documentoFormData.fechaDamComex,
      fechaEntrega: documentoFormData.fechaDamDrawback,
      tiempoRegulDam41: documentoFormData.tiempoRegularizacion,
      fechaEntregaDocu: documentoFormData.fechaEntregaDocumento
    };

    const formulario = {
      exportacion: cabecera
    }

    let request : any = {};
    request.formulario = formulario;
    request.usuario = this.settingsService.getUsername();

    this.settingsService.mostrarSpinner();
    Promise.all([
      this.service.guardar(request).toPromise()
    ]).then(
      (data : any) => {
        this.settingsService.ocultarSpinner();
        if(data[0].cod_rpta == '1') {
          this.modalService.open(this.modalGuardadoExitoso, { centered: true, backdrop: 'static', keyboard: false });
        } else {
          this.messageService.add({
            severity: "error",
            summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
            detail: JSON.stringify(data[0].rpta),
          });
        }
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

  verCertificado() {
    const request = this.obtenerDatosCertificado();
    this.settingsService.mostrarSpinner();
    Promise.all([
      this.service.imprimirCertificadoTerrestre(request).toPromise()
    ]).then(
      (data) => {
        this.settingsService.ocultarSpinner();
        const tempBlob = new Blob([data[0]], { type: 'application/pdf' });
        const fileReader = new FileReader();
        fileReader.onload = () => {
            this.pdfSrc = new Uint8Array(fileReader.result as ArrayBuffer);
        };
        fileReader.readAsArrayBuffer(tempBlob);
        this.modalService.open(this.modalVerPdf, {size: 'lg', centered: true});
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

  descargarPdf() {
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
        a.download = 'CertificadoVenta_' + this.dataDocumento.codigo + '.pdf';
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
