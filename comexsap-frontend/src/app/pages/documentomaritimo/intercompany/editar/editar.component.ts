import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormControl, FormArray, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MessageService, MenuItem } from 'primeng/api';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { ConfiguracionService } from 'src/app/pages/configuracion/configuracion.sevice';
import { ConfiguracionUtil } from 'src/app/pages/configuracion/configuracion.util';
import { Destinatario } from 'src/app/pages/configuracion/to/destinatario';
import { Parametro } from 'src/app/pages/configuracion/to/parametro';
import { PedidoPosicion } from 'src/app/pages/configuracion/to/pedidoposicion';
import { Puerto } from 'src/app/pages/configuracion/to/puerto';
import { Ruta } from 'src/app/pages/configuracion/to/ruta';
import { LISTAR_DOCUMENTOMARITIMO_INTERCOMPANY, EDITAR_DOCUMENTOMARITIMO_INTERCOMPANY } from 'src/app/shared/breadcrumb/breadcrumb';
import { Breadcrumb2Service } from 'src/app/shared/breadcrumb2.service';
import { Constantes } from 'src/app/utils/constantes';
import { Utils } from 'src/app/utils/utils';
import { DocumentoMaritimoService } from '../../documentomaritimo.service';
import { InfoCliente } from '../../to/infocliente';
import { OrdenInterna } from '../../to/ordeninterna';
import { PedidoIntercompany } from '../../to/pedidointercompany';
import { DocumentoMaritimo } from '../../to/documentomaritimo';
import { DocumentoMaritimoPosicion } from '../../to/documentomaritimoposicion';
import { ExportacionFactura } from '../../to/exportacionfactura';
import { Validacion } from 'src/app/pages/configuracion/to/validacion';
import { Etiqueta } from 'src/app/pages/configuracion/to/etiqueta';
import { ExportacionEtiqueta } from '../../to/exportacionetiqueta';
import { POSICION_ETIQUETA } from 'src/app/utils/posicionetiqueta';

@Component({
  selector: 'app-editar',
  templateUrl: './editar.component.html',
  styleUrls: ['./editar.component.scss']
})
export class EditarIntercompanyComponent implements OnInit {

  @ViewChild('modalGuardadoExitoso') modalGuardadoExitoso: ElementRef;
  @ViewChild('modalVerPdf') modalVerPdf: ElementRef;

  pdfSrc: Uint8Array;

  active = 1;
  activeDatos = 1;
  activeContacto = 1;
  activeImpresion = 1;

  documentoForm: FormGroup;

  idDocumento: string = null;
  dataDocumento: DocumentoMaritimo;
  dataPosiciones: DocumentoMaritimoPosicion[] = [];
  dataFactura: ExportacionFactura[] = [];
  dataEtiquetas: ExportacionEtiqueta[] = [];
  posicionesEliminadas: DocumentoMaritimoPosicion[] = [];
  etiquetasEliminadas: ExportacionEtiqueta[] = [];

  destinatarios: Destinatario[] = [];
  pagos: Parametro[] = [];
  incoterms: Parametro[] = [];
  estados: Parametro[] = [];
  monedas: Parametro[] = [];
  despachos: Parametro[] = [];
  listaPrecios: Parametro[] = [];
  regimenes: Parametro[] = [];
  etiquetas: Etiqueta[] = [];

  partidas: Parametro[] = [];
  agentesAduana: Parametro[] = [];
  defaultAgenteAduana: string = "";

  selectedPuertoOrigen: Puerto;
  selectedPuertoDestino: Puerto;
  selectedRuta: Ruta;

  codigoPedidos: string[] = [];

  tipoDocumento = 0; //0: exportacion - 1:factura comercial

  mensajeValidacion: string = "";

  versionIngles: boolean = false;
  original: boolean = true;
  tipoFactura: number = Constantes.P_FACCOMERCIAL_PESOSAP;

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private breadcrumb2Service: Breadcrumb2Service,
    private messageService: MessageService,
    private modalService: NgbModal,
    private settingsService: SettingsService,
    private configuracionService: ConfiguracionService,
    private service: DocumentoMaritimoService
  ) { }

  ngOnInit(): void {
    this.idDocumento = this.activatedRoute.snapshot.paramMap.get("id");
    this.inicializarBreadcrumb(this.idDocumento);
    this.crearFormulario();
    this.inicializar();
  }

  inicializarBreadcrumb(id: string){
    EDITAR_DOCUMENTOMARITIMO_INTERCOMPANY[0].url = EDITAR_DOCUMENTOMARITIMO_INTERCOMPANY[0].url.replace(':id', id);
    let BREADCRUMBS: MenuItem[] = [];
    BREADCRUMBS = BREADCRUMBS.concat(LISTAR_DOCUMENTOMARITIMO_INTERCOMPANY);
    BREADCRUMBS = BREADCRUMBS.concat(EDITAR_DOCUMENTOMARITIMO_INTERCOMPANY);
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
      this.configuracionService.listarParametroxDominio(Constantes.P_D022).toPromise(),//Régimen
      this.configuracionService.listarParametroxDominio(Constantes.P_D023).toPromise(),//Partida Arancelarias
      this.configuracionService.listarEtiquetas("").toPromise(),
      this.service.obtenerExportacionIntercompany(this.idDocumento).toPromise(),
      this.service.listarExportacionEtiquetas(this.idDocumento).toPromise(),
    ]).then(
      (data :any[]) => {
        this.settingsService.ocultarSpinner();
        this.pagos = Parametro.toArray(data[0]);
        this.incoterms = Parametro.toArray(data[1]);
        this.estados = Parametro.toArray(data[2]);
        this.monedas = Parametro.toArray(data[3]);
        this.despachos = Parametro.toArray(data[4]);
        this.listaPrecios = Parametro.toArray(data[5]);
        this.regimenes = Parametro.toArray(data[6]);
        this.partidas = Parametro.toArray(data[7]);
        this.etiquetas = data[8];

        this.dataDocumento = data[9].exportacion;
        this.dataPosiciones = data[9].posiciones;
        this.dataFactura = data[9].facturaImpresion;

        this.dataEtiquetas = data[10];

        if(this.dataDocumento != null) {
          this.selectedPuertoOrigen = new Puerto(
            this.dataDocumento.idPuertoOrigen,
            this.dataDocumento.codigoSapPuertoOrigen,
            this.dataDocumento.puertoOrigen,
            this.dataDocumento.codigoPaisPuertoOrig,
            this.dataDocumento.paisPuertoOrigen
          );

          this.selectedPuertoDestino = new Puerto(
            this.dataDocumento.idPuertoDestino,
            this.dataDocumento.codigoPuertoDestino,
            this.dataDocumento.puertoDestino,
            this.dataDocumento.codigoPaisPuertoDestino,
            this.dataDocumento.paisPuertoDestino
          );

          this.selectedRuta = new Ruta(
            this.dataDocumento.idRuta,
            this.dataDocumento.codigoRuta,
            this.dataDocumento.nombreRuta
          );
        }

        if(this.dataPosiciones != null) {
          this.dataPosiciones.forEach((posicion: DocumentoMaritimoPosicion) => {
            const posicionForm = new FormGroup({
              checked: new FormControl(false),
              id: new FormControl(posicion.id),
              codigoPedido: new FormControl(posicion.codigoPedido),
              codigoMaterial: new FormControl(posicion.codigoSAP),
              descripcion: new FormControl(posicion.descripcionProducto),
              descripcionMaterial: new FormControl(posicion.descripcionComercialProducto),
              cantidadSaldo: new FormControl(posicion.cantidadSaldo),
              cantidadSaldoInicial: new FormControl(posicion.cantidadSaldo),
              cantidad: new FormControl(posicion.cantidad),
              cantidadVenta: new FormControl(posicion.cantidadVenta),
              idUnidadMedida: new FormControl(posicion.idUnidadMedida),
              idUnidadMedidaVenta: new FormControl(posicion.idUnidadMedidaVenta),
              unidadMedidaVenta: new FormControl(posicion.codigoSAPUnidadMedidaVenta),
              idCentro: new FormControl(posicion.idCentro),
              codigoCentro: new FormControl(posicion.codigoSapCentro),
              centro: new FormControl(posicion.codigoSapCentro + ' - ' + posicion.descripcionCentro),
              idAlmacen: new FormControl(posicion.idAlmacen),
              codigoAlmacen: new FormControl(posicion.codigoSapAlmacen),
              almacen: new FormControl(posicion.codigoSapAlmacen + ' - ' + posicion.descripcionAlmacen),
              partidas: new FormControl(this.partidas),
              idPartidaArancelaria: new FormControl(posicion.idPartidaArancelaria == 0 ? null : posicion.idPartidaArancelaria),
              partidaArancelaria: new FormControl(posicion.nombrePartidaArancelaria),
              pesoToneladas: new FormControl(posicion.pesoTonelada),
              fechaDisponibilidad: new FormControl(posicion.fechaDisponibilidad),
              precioUnitario: new FormControl(posicion.precioUnitario),
              importe: new FormControl(posicion.importe),
              pedidoSap: new FormControl(posicion.pedidoSap),
              entrega: new FormControl(posicion.entrega),
              bloqueo: new FormControl(posicion.bloqueo),
              folioFactura: new FormControl(posicion.folio),
              referencia: new FormControl(posicion.referencia),
              esPadre: new FormControl(false)
            });
            this.posiciones.push(posicionForm);
          });
        }

        if(this.dataFactura != null) {
          this.dataFactura.forEach((factura: ExportacionFactura) => {
            const facturaForm = new FormGroup({
              checked: new FormControl(false),
              id: new FormControl(factura.id),
              idPedido: new FormControl(factura.idPedido),
              codigoPedido: new FormControl(factura.codigoPedido),
              factura: new FormControl(factura.factura),
              etiquetaFlete: new FormControl(factura.etiquetaFlete),
              etiquetaImporteFlete: new FormControl(factura.etiquetaImporteFlete),
              etiquetaTotal: new FormControl(factura.etiquetaTotal),
              etiquetaImporteTotal: new FormControl(factura.etiquetaImporteTotal),
              etiquetaUnidadMedida: new FormControl(factura.etiquetaUnidadMedida),
              montoFlete: new FormControl(factura.montoFlete),
              montoImporteTotal: new FormControl(factura.montoImporteTotal),
              montoTotal: new FormControl(factura.montoTotal),
              estado: new FormControl(factura.estado),
            });
            this.facturas.push(facturaForm);
          });
        }

        if(this.dataEtiquetas != null) {
          this.dataEtiquetas.forEach((etiqueta: ExportacionEtiqueta) => {
            const etiquetaForm = new FormGroup({
              id: new FormControl(etiqueta.id),
              idEtiqueta: new FormControl(etiqueta.idEtiqueta),
              contenido: new FormControl(etiqueta.contenido),
              estado: new FormControl(etiqueta.estado)
            });

            if(etiqueta.posicion == POSICION_ETIQUETA.ARRIBA) {
              this.etiquetasArriba.push(etiquetaForm);
            } else {
              this.etiquetasAbajo.push(etiquetaForm);
            }
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
      selectAllPosiciones: new FormControl(),
      idCliente: new FormControl(),
      codigoCliente: new FormControl(),
      nombreCliente: new FormControl(),
      cliente: new FormControl(),
      idDestinatario: new FormControl(),
      codigoDestinatario: new FormControl(),
      nombreDestinatario: new FormControl(),
      destinatario: new FormControl(),

      idPuertoOrigen: new FormControl(),
      puertoOrigen: new FormControl(),
      idPuertoDestino: new FormControl(),
      puertoDestino: new FormControl(),
      idIncoterm: new FormControl(),
      idIncotermComercial: new FormControl(),
      idTipoDespacho: new FormControl(),
      idCondicionPago: new FormControl(),
      idMoneda: new FormControl(),
      fechaListaPrecio: new FormControl(),
      idListaPrecio: new FormControl(),
      idRuta: new FormControl(),

      agenteAduana: new FormControl(),
      shipper: new FormControl(),
      direccionShipper: new FormControl(),
      consignatario: new FormControl(),
      direccionConsignatario: new FormControl(),
      notificante: new FormControl(),
      direccionNotificante: new FormControl(),
      glosa: new FormControl(),
      descripcionTotal: new FormControl(),

      //Orden Interna
      ordenInterna: new FormControl(),

      //Información Marítima
      agenteNaviera: new FormControl(),
      agenteCarga: new FormControl(),
      nave: new FormControl(),

      etaOrigen: new FormControl(),
      etaDestino: new FormControl(),
      fechaBlProgramado: new FormControl(),
      fechaBlReal: new FormControl(),
      fechaCarguio: new FormControl(),
      fechaMaximaIngreso: new FormControl(),
      fechaEnvioDocumento: new FormControl(),
      importeFlete: new FormControl(),
      importeSeguro: new FormControl(),

      fleteTm: new FormControl(),
      booking: new FormControl(),
      billOfLanding: new FormControl(),
      tipoEnvio: new FormControl(),
      guiaDHL: new FormControl(),

      dam: new FormControl(),
      idRegimen: new FormControl(),
      fechaDamDrawback: new FormControl(),
      fechaManifiestoAduana: new FormControl(),
      fechaDam: new FormControl(),
      fechaDamRegularizacion: new FormControl(),
      fechaDam41: new FormControl(),
      fechaRecepcionDam41: new FormControl(),
      armador: new FormControl(),

      //Datos de Impresión
      emitidoEn: new FormControl(),
      etiquetaEmpaque: new FormControl(),
      empaque: new FormControl(),
      etiquetaMarca: new FormControl(),
      marca: new FormControl(),
      etiquetaUnidadProductiva: new FormControl(),
      unidadProductiva: new FormControl(),
      etiquetaPrecioUnitario: new FormControl(),
      precioUnitario: new FormControl(),
      etiquetaFacturacion: new FormControl(),
      facturacion: new FormControl(),
      etiquetaFormaPago: new FormControl(),
      formaPago: new FormControl(),
      fechaFactura: new FormControl(),
      estadoDocumento: new FormControl(),

      posiciones: new FormArray([]),
      facturas: new FormArray([]),
      etiquetasArriba: new FormArray([]),
      etiquetasAbajo: new FormArray([])
    });
  }

  inicializarFormulario() {
    if(this.dataDocumento != null) {
      //Datos Documento Marítimo
      this.documentoForm.controls['codigo'].patchValue(this.dataDocumento.codigo, {onlySelf: true});
      this.documentoForm.controls['idCliente'].patchValue(this.dataDocumento.idCliente, {onlySelf: true});
      this.documentoForm.controls['codigoCliente'].patchValue(this.dataDocumento.codigoSapCliente, {onlySelf: true});
      this.documentoForm.controls['cliente'].patchValue(this.dataDocumento.codigoSapCliente + " - " + this.dataDocumento.nombreCliente, {onlySelf: true});
      this.documentoForm.controls['idDestinatario'].patchValue(this.dataDocumento.idDestinatario, {onlySelf: true});
      this.documentoForm.controls['codigoDestinatario'].patchValue(this.dataDocumento.codigoSapDestinatario, {onlySelf: true});
      this.documentoForm.controls['destinatario'].patchValue(this.dataDocumento.codigoSapDestinatario + " - " + this.dataDocumento.nombreDestinatario, {onlySelf: true});
      this.documentoForm.controls['idPuertoOrigen'].patchValue(this.dataDocumento.idPuertoOrigen, {onlySelf: true});
      this.documentoForm.controls['idPuertoDestino'].patchValue(this.dataDocumento.idPuertoDestino, {onlySelf: true});
      this.documentoForm.controls['idIncoterm'].patchValue(this.dataDocumento.idIncoterm, {onlySelf: true});
      this.documentoForm.controls['idIncotermComercial'].patchValue(this.dataDocumento.idIncotermComercial, {onlySelf: true});
      this.documentoForm.controls['idTipoDespacho'].patchValue(this.dataDocumento.idDespacho, {onlySelf: true});
      this.documentoForm.controls['idCondicionPago'].patchValue(this.dataDocumento.idCondicionPago, {onlySelf: true});
      this.documentoForm.controls['idMoneda'].patchValue(this.dataDocumento.idMoneda, {onlySelf: true});
      this.documentoForm.controls['fechaListaPrecio'].patchValue(Utils.stringToDate(this.dataDocumento.fechaListaPrecio), {onlySelf: true});
      this.documentoForm.controls['idListaPrecio'].patchValue(this.dataDocumento.idListaPrecio, {onlySelf: true});
      this.documentoForm.controls['idRuta'].patchValue(this.dataDocumento.idRuta, {onlySelf: true});
      this.documentoForm.controls['agenteAduana'].patchValue(this.dataDocumento.agenteAduana, {onlySelf: true});
      this.documentoForm.controls['shipper'].patchValue(this.dataDocumento.shipper, {onlySelf: true});
      this.documentoForm.controls['direccionShipper'].patchValue(this.dataDocumento.direccionShipper, {onlySelf: true});
      this.documentoForm.controls['consignatario'].patchValue(this.dataDocumento.consignatario, {onlySelf: true});
      this.documentoForm.controls['direccionConsignatario'].patchValue(this.dataDocumento.direccionConsignatario, {onlySelf: true});
      this.documentoForm.controls['notificante'].patchValue(this.dataDocumento.notificante, {onlySelf: true});
      this.documentoForm.controls['direccionNotificante'].patchValue(this.dataDocumento.direccionNotificante, {onlySelf: true});
      this.documentoForm.controls['glosa'].patchValue(this.dataDocumento.glosa, {onlySelf: true});
      this.documentoForm.controls['descripcionTotal'].patchValue(this.dataDocumento.descripcionTotal, {onlySelf: true});
      this.documentoForm.controls['ordenInterna'].patchValue(this.dataDocumento.ordenInterna, {onlySelf: true});
      this.documentoForm.controls['agenteNaviera'].patchValue(this.dataDocumento.agenteNaviera, {onlySelf: true});
      this.documentoForm.controls['agenteCarga'].patchValue(this.dataDocumento.agenteCarga, {onlySelf: true});
      this.documentoForm.controls['nave'].patchValue(this.dataDocumento.nave, {onlySelf: true});
      this.documentoForm.controls['etaOrigen'].patchValue(Utils.stringToDate(this.dataDocumento.etaOrigen), {onlySelf: true});
      this.documentoForm.controls['etaDestino'].patchValue(Utils.stringToDate(this.dataDocumento.etaDestino), {onlySelf: true});
      this.documentoForm.controls['fechaBlProgramado'].patchValue(Utils.stringToDate(this.dataDocumento.fechaBlProgramado), {onlySelf: true});
      this.documentoForm.controls['fechaBlReal'].patchValue(Utils.stringToDate(this.dataDocumento.fechaBlReal), {onlySelf: true});
      this.documentoForm.controls['fechaCarguio'].patchValue(Utils.stringToDate(this.dataDocumento.fechaCarguio), {onlySelf: true});
      this.documentoForm.controls['fechaMaximaIngreso'].patchValue(Utils.stringToDate(this.dataDocumento.fechaMaximaIngreso), {onlySelf: true});
      this.documentoForm.controls['fechaEnvioDocumento'].patchValue(Utils.stringToDate(this.dataDocumento.fechaEnvioDocum), {onlySelf: true});
      this.documentoForm.controls['importeFlete'].patchValue(this.dataDocumento.flete == 0 ? null : this.dataDocumento.flete, {onlySelf: true});
      this.documentoForm.controls['importeSeguro'].patchValue(this.dataDocumento.seguro == 0 ? null: this.dataDocumento.seguro, {onlySelf: true});
      this.documentoForm.controls['fleteTm'].patchValue(this.dataDocumento.fleteTm ? this.dataDocumento.fleteTm : null, {onlySelf: true});
      this.documentoForm.controls['booking'].patchValue(this.dataDocumento.booking, {onlySelf: true});
      this.documentoForm.controls['billOfLanding'].patchValue(this.dataDocumento.bl, {onlySelf: true});
      this.documentoForm.controls['tipoEnvio'].patchValue(this.dataDocumento.tipoEnvio ? this.dataDocumento.tipoEnvio : Constantes.P_ENVIO_FISICO, {onlySelf: true});
      this.documentoForm.controls['guiaDHL'].patchValue(this.dataDocumento.guiaDhl, {onlySelf: true});
      this.documentoForm.controls['dam'].patchValue(this.dataDocumento.dam, {onlySelf: true});
      this.documentoForm.controls['idRegimen'].patchValue(this.dataDocumento.idRegimen ? this.dataDocumento.idRegimen : Constantes.P_REGIMEN_DRAWBACK, {onlySelf: true});
      this.documentoForm.controls['fechaDamDrawback'].patchValue(Utils.stringToDate(this.dataDocumento.fechaEntrega), {onlySelf: true});
      this.documentoForm.controls['fechaManifiestoAduana'].patchValue(Utils.stringToDate(this.dataDocumento.fechaManifAduana), {onlySelf: true});
      this.documentoForm.controls['fechaDam'].patchValue(Utils.stringToDate(this.dataDocumento.fechaDam), {onlySelf: true});
      this.documentoForm.controls['fechaDamRegularizacion'].patchValue(Utils.stringToDate(this.dataDocumento.fechaDamRegularizacion), {onlySelf: true});
      this.documentoForm.controls['fechaDam41'].patchValue(Utils.stringToDate(this.dataDocumento.fechaDam41), {onlySelf: true});
      this.documentoForm.controls['fechaRecepcionDam41'].patchValue(Utils.stringToDate(this.dataDocumento.fechaRecepcionDam41), {onlySelf: true});
      this.documentoForm.controls['armador'].patchValue(this.dataDocumento.armador, {onlySelf: true});
      this.documentoForm.controls['emitidoEn'].patchValue(this.dataDocumento.emitidoEn, {onlySelf: true});
      this.documentoForm.controls['etiquetaEmpaque'].patchValue(this.dataDocumento.etiquetaEmpaque, {onlySelf: true});
      this.documentoForm.controls['empaque'].patchValue(this.dataDocumento.empaque, {onlySelf: true});
      this.documentoForm.controls['etiquetaMarca'].patchValue(this.dataDocumento.etiquetaMarca, {onlySelf: true});
      this.documentoForm.controls['marca'].patchValue(this.dataDocumento.marca, {onlySelf: true});
      this.documentoForm.controls['etiquetaUnidadProductiva'].patchValue(this.dataDocumento.etiquetaUnidProd, {onlySelf: true});
      this.documentoForm.controls['unidadProductiva'].patchValue(this.dataDocumento.unidadProductiva, {onlySelf: true});
      this.documentoForm.controls['etiquetaPrecioUnitario'].patchValue(this.dataDocumento.etiquetaPrecioUnitario, {onlySelf: true});
      this.documentoForm.controls['precioUnitario'].patchValue(this.dataDocumento.precioUnitario, {onlySelf: true});
      this.documentoForm.controls['etiquetaFacturacion'].patchValue(this.dataDocumento.etiquetaFacturacion, {onlySelf: true});
      this.documentoForm.controls['facturacion'].patchValue(this.dataDocumento.facturacion, {onlySelf: true});
      this.documentoForm.controls['etiquetaFormaPago'].patchValue(this.dataDocumento.etiquetaFormaPago, {onlySelf: true});
      this.documentoForm.controls['formaPago'].patchValue(this.dataDocumento.formaPago, {onlySelf: true});
      this.documentoForm.controls['fechaFactura'].patchValue(this.dataDocumento.fechaFactura, {onlySelf: true});
      this.documentoForm.controls['estadoDocumento'].patchValue(this.dataDocumento.idEstadoDocumento, {onlySelf: true});

      this.documentoForm.controls['posiciones'].patchValue([], {onlySelf: true});
      this.documentoForm.controls['facturas'].patchValue([], {onlySelf: true});

      //DESAHIBILITAR CABECERA PEDIDO
      this.documentoForm.controls['codigo'].disable();
      this.documentoForm.controls['idCliente'].disable();
      this.documentoForm.controls['cliente'].disable();
      this.documentoForm.controls['idDestinatario'].disable();
      this.documentoForm.controls['destinatario'].disable();
      this.documentoForm.controls['idIncoterm'].disable();
      this.documentoForm.controls['idIncotermComercial'].disable();
      this.documentoForm.controls['idMoneda'].disable();
      this.documentoForm.controls['fechaListaPrecio'].disable();
      this.documentoForm.controls['idListaPrecio'].disable();
      this.documentoForm.controls['fechaFactura'].disable();
      this.documentoForm.controls['idPuertoOrigen'].disable();
      this.documentoForm.controls['idPuertoDestino'].disable();
      this.documentoForm.controls['idRuta'].disable();

      this.documentoForm.controls['codigo'].setValidators([Validators.required]);
      this.documentoForm.controls['idCliente'].setValidators([Validators.required]);
      this.documentoForm.controls['cliente'].setValidators([Validators.required]);
      this.documentoForm.controls['idDestinatario'].setValidators([Validators.required]);
      this.documentoForm.controls['destinatario'].setValidators([Validators.required]);
      this.documentoForm.controls['idIncoterm'].setValidators([Validators.required]);
      this.documentoForm.controls['idIncotermComercial'].setValidators([Validators.required]);
      this.documentoForm.controls['idMoneda'].setValidators([Validators.required]);
      this.documentoForm.controls['fechaListaPrecio'].setValidators([Validators.required]);
      this.documentoForm.controls['idListaPrecio'].setValidators([Validators.required]);
      this.documentoForm.controls['fechaFactura'].setValidators([Validators.required]);
      this.documentoForm.controls['idPuertoOrigen'].setValidators([Validators.required]);
      this.documentoForm.controls['idPuertoDestino'].setValidators([Validators.required]);
      this.documentoForm.controls['idRuta'].setValidators([Validators.required]);
    }
  }

  limpiarValidadores() {
    //Datos Documento Marítimo
    this.documentoForm.controls['idCliente'].clearValidators();
    this.documentoForm.controls['idDestinatario'].clearValidators();
    this.documentoForm.controls['idPuertoOrigen'].clearValidators();
    this.documentoForm.controls['idPuertoDestino'].clearValidators();
    this.documentoForm.controls['idIncoterm'].clearValidators();
    this.documentoForm.controls['idIncotermComercial'].clearValidators();
    this.documentoForm.controls['idTipoDespacho'].clearValidators();
    this.documentoForm.controls['idCondicionPago'].clearValidators();
    this.documentoForm.controls['idMoneda'].clearValidators();
    this.documentoForm.controls['fechaListaPrecio'].clearValidators();
    this.documentoForm.controls['idListaPrecio'].clearValidators();
    this.documentoForm.controls['idRuta'].clearValidators();
    this.documentoForm.controls['glosa'].clearValidators();
    this.documentoForm.controls['ordenInterna'].clearValidators();
    this.documentoForm.controls['posiciones'].clearValidators();
  }

  refrescarValidadores() {
    //Datos Documento Marítimo
    this.documentoForm.controls['idCliente'].updateValueAndValidity();
    this.documentoForm.controls['idDestinatario'].updateValueAndValidity();
    this.documentoForm.controls['idPuertoOrigen'].updateValueAndValidity();
    this.documentoForm.controls['idPuertoDestino'].updateValueAndValidity();
    this.documentoForm.controls['idIncoterm'].updateValueAndValidity();
    this.documentoForm.controls['idIncotermComercial'].updateValueAndValidity();
    this.documentoForm.controls['idTipoDespacho'].updateValueAndValidity();
    this.documentoForm.controls['idCondicionPago'].updateValueAndValidity();
    this.documentoForm.controls['idMoneda'].updateValueAndValidity();
    this.documentoForm.controls['fechaListaPrecio'].updateValueAndValidity();
    this.documentoForm.controls['idListaPrecio'].updateValueAndValidity();
    this.documentoForm.controls['idRuta'].updateValueAndValidity();
    this.documentoForm.controls['glosa'].updateValueAndValidity();
    this.documentoForm.controls['ordenInterna'].updateValueAndValidity();
    this.documentoForm.controls['posiciones'].updateValueAndValidity();
  }

  get posiciones() {
    return this.documentoForm.controls["posiciones"] as FormArray;
  }

  get facturas() {
    return this.documentoForm.controls["facturas"] as FormArray;
  }

  get etiquetasArriba() {
    return this.documentoForm.controls["etiquetasArriba"] as FormArray;
  }

  get etiquetasAbajo() {
    return this.documentoForm.controls["etiquetasAbajo"] as FormArray;
  }

  async seleccionarPosiciones(posiciones: PedidoPosicion[]) {
    let pedidosValidar = [].concat(this.codigoPedidos);
    posiciones.forEach(item => {
      pedidosValidar.push(item.codigoPedido);
    });
    pedidosValidar = pedidosValidar.filter(Utils.quitarDuplicados);
    const pedidos = await this.obtenerPedidos(pedidosValidar) as PedidoIntercompany[];
    if(pedidos != null && pedidos.length > 0) {
      if(ConfiguracionUtil.validarCabeceraPedidosIntercompany(pedidos)) {
        const pedido = pedidos[0];
        this.documentoForm.controls['idCliente'].patchValue(pedido.idCliente);
        this.documentoForm.controls['codigoCliente'].patchValue(pedido.codigoCliente);
        this.documentoForm.controls['nombreCliente'].patchValue(pedido.nombreCliente);
        this.documentoForm.controls['cliente'].patchValue(pedido.codigoCliente + " - " + pedido.nombreCliente);
        this.documentoForm.controls['idDestinatario'].patchValue(pedido.idDestinatario);
        this.documentoForm.controls['codigoDestinatario'].patchValue(pedido.codigoDestinatario);
        this.documentoForm.controls['nombreDestinatario'].patchValue( pedido.nombreDestinatario);
        this.documentoForm.controls['destinatario'].patchValue(pedido.codigoDestinatario + " - " + pedido.nombreDestinatario);
        this.documentoForm.controls['puertoOrigen'].patchValue(pedido.codigoPuertoOrigen + " - " + pedido.nombrePuertoOrigen);
        this.documentoForm.controls['puertoDestino'].patchValue(pedido.codigoPuertoDestino + " - " + pedido.nombrePuertoDestino);
        this.documentoForm.controls['idIncoterm'].patchValue(pedido.idIncoterm);
        this.documentoForm.controls['idIncotermComercial'].patchValue(pedido.idIncoterm);
        this.documentoForm.controls['idCondicionPago'].patchValue(pedido.idCondicionPago);
        this.documentoForm.controls['idMoneda'].patchValue(pedido.idMoneda);
        this.documentoForm.controls['fechaListaPrecio'].patchValue(Utils.stringToDate(pedido.fechaListaPrecio));
        this.documentoForm.controls['idListaPrecio'].patchValue(pedido.idListaPrecio);
        this.documentoForm.controls['idTipoDespacho'].patchValue(pedido.idDespacho);

        if(pedido.idPuertoOrigen != 0) {
          this.documentoForm.controls['idPuertoOrigen'].patchValue(pedido.idPuertoOrigen);
          this.selectedPuertoOrigen = new Puerto(
            pedido.idPuertoOrigen,
            pedido.codigoPuertoOrigen,
            pedido.nombrePuertoOrigen,
            null,
            null
          );
        }

        if(pedido.idPuertoDestino != 0) {
          this.documentoForm.controls['idPuertoDestino'].patchValue(pedido.idPuertoDestino);
          this.selectedPuertoDestino = new Puerto(
            pedido.idPuertoDestino,
            pedido.codigoPuertoDestino,
            pedido.nombrePuertoDestino,
            null,
            null
          );
        }

        if(pedido.idDespacho != 0) {
          const despacho = new Parametro(pedido.idDespacho, pedido.codigoDespacho, pedido.nombreDespacho);
          this.seleccionarTipoDespacho(despacho);
        }

        if(pedido.idRuta != 0) {
          this.documentoForm.controls['idRuta'].patchValue(pedido.idRuta);
          this.selectedRuta = new Ruta(
            pedido.idRuta,
            pedido.codigoRuta,
            pedido.nombreRuta
          );
        }

        posiciones.forEach(posicion => this.agregarPosicion(posicion));

        this.codigoPedidos.push(pedido.codigoPedido);

        //Obtener Info Cliente
        const infoCliente = await this.obtenerInfoCliente(pedido.codigoCliente) as InfoCliente;
        if(infoCliente != null) {
          this.documentoForm.controls['consignatario'].patchValue(infoCliente.razonSocial);
          this.documentoForm.controls['notificante'].patchValue(infoCliente.razonSocial);
          this.documentoForm.controls['direccionConsignatario'].patchValue(infoCliente.direccion);
          this.documentoForm.controls['direccionNotificante'].patchValue(infoCliente.direccion);
        }
      } else {
        //Debe seleccionar posiciones que tengan los mismos datos de cabecera de tu listado actual
        this.settingsService.ocultarSpinner();
        this.messageService.add({
          severity: "warn",
          summary: "Advertencia",
          detail: "Debe seleccionar posiciones que tengan los mismos datos de cabecera: Cliente, Destinatario de mercancía, Puerto de origen, Puerto de destino, Incoterm, Condición de pago, Lista de Precio y Moneda."
        });
      }
    }
  }

  obtenerPedidos(codigos: string[]) {
    let request = {
      formulario: {
        pedidos: codigos
      }
    };
    this.settingsService.mostrarSpinner();
    return new Promise(resolve => {
      this.service.listarPedidoIntercompanyxFiltro(request).subscribe((data) => {
        this.settingsService.ocultarSpinner();
        resolve(data);
      },
      (err) => {
        this.settingsService.ocultarSpinner();
        resolve([]);
      }
      );
    });
  }

  obtenerInfoCliente(codigo: string) {
    this.settingsService.mostrarSpinner();
    return new Promise(resolve => {
      this.configuracionService.obtenerInfoCliente(codigo).subscribe((data) => {
        this.settingsService.ocultarSpinner();
        resolve(data);
      },
      (err) => {
        this.settingsService.ocultarSpinner();
        resolve(null);
      }
      );
    });
  }

  obtenerOrdenInternaxFiltro(request: any) {
    this.settingsService.mostrarSpinner();
    return new Promise(resolve => {
      this.configuracionService.obtenerOrdenInternaxFiltro(request).subscribe((data) => {
        this.settingsService.ocultarSpinner();
        resolve(data);
      },
      (err) => {
        this.settingsService.ocultarSpinner();
        resolve(null);
      }
      );
    });
  }

  seleccionarPuertoOrigen(puerto: Puerto) {
    this.documentoForm.controls['idPuertoOrigen'].patchValue(puerto ? puerto.id : null);
  }

  seleccionarPuertoDestino(puerto: Puerto) {
    this.documentoForm.controls['idPuertoDestino'].patchValue(puerto ? puerto.id : null);
  }

  seleccionarRuta(ruta: Ruta) {
    this.documentoForm.controls['idRuta'].patchValue(ruta ? ruta.id : null);
  }

  async seleccionarTipoDespacho(tipoDespacho: Parametro) {
    const formData = this.documentoForm.getRawValue();
    //Obtener Orden Interna
    const requestOrdenInterna = {
      idDespacho: tipoDespacho.id,
      idPuertoDestino: formData.idPuertoDestino,
      codigoCliente: formData.codigoCliente,
      codigoDestinatario: formData.codigoDestinatario
    };

    const ordenInterna = await this.obtenerOrdenInternaxFiltro(requestOrdenInterna) as OrdenInterna;
    if(ordenInterna != null) {
      this.documentoForm.controls['ordenInterna'].patchValue(ordenInterna.codigo);
    }
  }

  existePosicion(codigoPedido: string, codigoMaterial: string) {
    let existe = false;
    this.posiciones.controls.forEach((posicionForm: FormGroup) => {
      if(posicionForm.controls['codigoPedido'].value == codigoPedido && posicionForm.controls['codigoMaterial'].value == codigoMaterial) {
        existe = true;
        return;
      }
    });
    return existe;
  }

  agregarPosicion(posicion: PedidoPosicion) {
    if(!this.existePosicion(posicion.codigoPedido, posicion.codigoSap)) {
      const posicionForm = new FormGroup({
        checked: new FormControl(false),
        id: new FormControl(0),
        codigoPedido: new FormControl(posicion.codigoPedido),
        codigoMaterial: new FormControl(posicion.codigoSap),
        descripcion: new FormControl(posicion.descripcionProducto),
        descripcionMaterial: new FormControl(posicion.descripcionProducto),
        cantidadSaldo: new FormControl(0),
        cantidadSaldoInicial: new FormControl(posicion.cantidadSaldo),
        cantidad: new FormControl(posicion.cantidad),
        cantidadVenta: new FormControl(posicion.cantidadVenta),
        idUnidadMedida: new FormControl(posicion.idUnidadMedida),
        idUnidadMedidaVenta: new FormControl(posicion.idUnidadMedidaVenta),
        unidadMedidaVenta: new FormControl(posicion.codigoSapUnidadMedidaVenta),
        idCentro: new FormControl(posicion.idCentro),
        codigoCentro: new FormControl(posicion.codigoSapCentro),
        centro: new FormControl(posicion.codigoSapCentro + " " + posicion.descripcionCentro),
        idAlmacen: new FormControl(posicion.idAlmacen),
        codigoAlmacen: new FormControl(posicion.codigoSapAlmacen),
        almacen: new FormControl(posicion.codigoSapAlmacen + " " + posicion.descripcionAlmacen),
        partidas: new FormControl(this.partidas),
        idPartidaArancelaria: new FormControl(),
        pesoToneladas: new FormControl(posicion.pesoTonelada),
        fechaPackingList: new FormControl(Utils.dateTimeToStringDate(posicion.fechaPackingList)),
        precioUnitario: new FormControl(posicion.precioUnitario),
        importe: new FormControl(posicion.importe),
        pedidoSap: new FormControl(posicion.codigoPedido),
        folioFactura: new FormControl(),
        referencia: new FormControl(posicion.codigoPedido),
        esPadre: new FormControl(false)
      });
      this.posiciones.push(posicionForm);
    }
  }

  quitarPosiciones() {
    const posiciones = this.documentoForm.value.posiciones.filter(posicion => posicion.checked);
    if(posiciones.length > 0) {
      this.documentoForm.controls['selectAllPosiciones'].patchValue(false);
      posiciones.forEach(element => {
        const index = this.documentoForm.value.posiciones.findIndex(posicion => posicion.codigoPedido == element.codigoPedido && posicion.codigoMaterial == element.codigoMaterial);
        this.posiciones.removeAt(index);
      });
    } else {
      this.messageService.add({
        severity: "warn",
        summary: "Alerta",
        detail: "Por favor, seleccione al menos un registro para eliminarlo"
      });
    }
  }

  seleccionarTodasLasPosiciones(event) {
    this.posiciones.controls.forEach((posicionForm: FormGroup) => {
      posicionForm.controls['checked'].patchValue(event.target.checked);
    });
  }

  desmarcarPosiciones() {
    this.posiciones.controls.forEach((posicionForm: FormGroup) => {
      posicionForm.controls['checked'].patchValue(false);
    });
  }

  inicializarValidaciones() {
    this.limpiarValidadores();
    this.documentoForm.controls['idCliente'].setValidators([Validators.required]);
    this.documentoForm.controls['idDestinatario'].setValidators([Validators.required]);
    this.documentoForm.controls['idPuertoOrigen'].setValidators([Validators.required]);
    this.documentoForm.controls['idPuertoDestino'].setValidators([Validators.required]);
    this.documentoForm.controls['idIncoterm'].setValidators([Validators.required]);
    this.documentoForm.controls['idIncotermComercial'].setValidators([Validators.required]);
    this.documentoForm.controls['idTipoDespacho'].setValidators([Validators.required]);
    this.documentoForm.controls['idCondicionPago'].setValidators([Validators.required]);
    this.documentoForm.controls['idMoneda'].setValidators([Validators.required]);
    this.documentoForm.controls['fechaListaPrecio'].setValidators([Validators.required]);
    this.documentoForm.controls['idListaPrecio'].setValidators([Validators.required]);
    this.documentoForm.controls['idRuta'].setValidators([Validators.required]);
    this.documentoForm.controls['glosa'].setValidators([Validators.required]);
    this.documentoForm.controls['ordenInterna'].setValidators([Validators.required]);
    this.documentoForm.controls['posiciones'].setValidators([Validators.required, Validators.minLength(1)]);
    this.refrescarValidadores();
  }

  validarFormulario(): boolean {
    let isValido: boolean = true;
    const formData: any = this.documentoForm.getRawValue();

    if(Utils.isNullOrEmpty(formData.idCliente)
      || Utils.isNullOrEmpty(formData.idDestinatario)) {
      isValido = false;
    }

    //Datos del envío
    if(formData.idPuertoOrigen == null
      || formData.idPuertoOrigen <= 0
      || formData.idPuertoDestino == null
      || formData.idPuertoDestino <= 0
      || formData.idIncoterm == null
      || formData.idIncotermComercial == null
      || Utils.isNullOrEmpty(formData.fechaListaPrecio)
      || formData.idListaPrecio == null
      || formData.idListaPrecio <= 0
      || formData.idTipoDespacho == null
      || formData.idMoneda == null
      || formData.idMoneda <= 0
      || formData.idCondicionPago == null
      || formData.idCondicionPago <= 0
      || formData.idRuta == null
      || Utils.isNullOrEmpty(formData.glosa)
      || Utils.isNullOrEmpty(formData.ordenInterna)) {
      isValido = false;
    }

    if(!isValido) {
      this.mensajeValidacion = "Por favor complete los campos obligatorios.";
      return false;
    }

    if(formData.idIncotermComercial == Constantes.P_ID_INCOTERM_CFR) {
      if(formData.importeFlete == null || formData.importeFlete <= 0) {
        this.mensajeValidacion = "Por favor, ingrese Importe Flete.";
        return false;
      }
    } else if(formData.idIncotermComercial == Constantes.P_ID_INCOTERM_CIF) {
      if(formData.importeFlete == null || formData.importeFlete <= 0) {
        this.mensajeValidacion = "Por favor, ingrese Importe Flete.";
        return false;
      }

      if(formData.importeSeguro == null || formData.importeSeguro <= 0) {
        this.mensajeValidacion = "Por favor, ingrese Importe Seguro.";
        return false;
      }
    }

    if(formData.numeroContenedor != null && formData.numeroContenedor <= 0 && !Utils.esEntero(formData.numeroContenedor)) {
      this.mensajeValidacion = "Número de Contenedor no válido.";
      return false;
    }

    //etaOrigen < etaDestino
    if(formData.etaOrigen != null && formData.etaDestino != null) {
      if(formData.etaOrigen.getTime() > formData.etaDestino.getTime()) {
        this.mensajeValidacion = "ETA Origen debe ser menor o igual a ETA Destino.";
        return false;
      }
    }

    //Posiciones
    if(this.posiciones.length == 0) {
      this.mensajeValidacion = "Faltan agregar posiciones.";
      return false;
    }

    if(!this.validarFormularioPosiciones()) return false;

    if(!this.validarFormularioEtiquetas()) return false;

    return isValido;
  }

  validarFormularioPosiciones(): boolean {
    let isValido: boolean = true;
    let importeTotal: number = 0;
    this.posiciones.controls.forEach((posicionForm: FormGroup) => {
      let posicionData: any = posicionForm.value;
      importeTotal += posicionData.importe;
      if(Utils.isNullOrEmpty(posicionData.descripcionMaterial)
        || posicionData.cantidadVenta == null
        || posicionData.cantidadVenta <= 0
        || posicionData.unidadMedidaVenta == null
        || posicionData.idCentro == null
        || posicionData.idCentro <= 0
        || posicionData.idAlmacen == null
        || posicionData.idAlmacen <= 0
      ) {
        isValido = false;
      }
    });

    if(!isValido) {
      this.mensajeValidacion = "Campos obligatorios de una Posición: Descripción, Cantidad Venta, UM Venta, Centro Suministro, Almacén.";
      return false;
    }

    const formData: any = this.documentoForm.getRawValue();
    if(formData.importeFlete > importeTotal) {
      this.mensajeValidacion = "Flete no puede ser mayor al importe total.";
      return false;
    }

    return isValido;
  }

  validarFormularioEtiquetas(): boolean {
    let esValido: boolean = true;
    this.etiquetasArriba.controls.forEach((etiquetaForm: FormGroup) => {
      let etiquetaData: any = etiquetaForm.value;
      if(etiquetaData.idEtiqueta == null
        || etiquetaData.idEtiqueta <= 0
        || Utils.isNullOrEmpty(etiquetaData.contenido)
      ) {
        esValido = false;
      }
    });

    this.etiquetasAbajo.controls.forEach((etiquetaForm: FormGroup) => {
      let etiquetaData: any = etiquetaForm.value;
      if(etiquetaData.idEtiqueta == null
        || etiquetaData.idEtiqueta <= 0
        || Utils.isNullOrEmpty(etiquetaData.contenido)
      ) {
        esValido = false;
      }
    });

    if(!esValido) {
      this.mensajeValidacion = "Hay etiquetas con campos vacíos";
      return false;
    }

    return esValido;
  }

  guardar() {
    this.inicializarValidaciones();
    this.documentoForm.markAllAsTouched();

    const isValid = this.validarFormulario();
    if(!isValid) {
      this.messageService.add({
        severity: "warn",
        summary: "Revisar:",
        detail: this.mensajeValidacion
      });
      return;
    }

    const formData = this.documentoForm.getRawValue();
    const cabecera = {
      id: this.dataDocumento.id,
      intercompany: 1,

      idCliente: formData.idCliente,
      codigoCliente: formData.codigoCliente,
      nombreCliente: formData.nombreCliente,

      idDestinatario: formData.idDestinatario,
      codigoDestinatario: formData.codigoDestinatario,
      nombreDestinatario: formData.nombreDestinatario,

      idPuertoOrigen: formData.idPuertoOrigen,
      idPuertoDestino: formData.idPuertoDestino,
      idIncoterm: formData.idIncoterm,
      idIncotermComercial: formData.idIncotermComercial,
      fechaListaPrecio: formData.fechaListaPrecio,
      idListaPrecio: formData.idListaPrecio,
      idDespacho: formData.idTipoDespacho,
      idMoneda: formData.idMoneda,
      idCondicionPago: formData.idCondicionPago,
      idRuta: formData.idRuta,

      agenteAduana: formData.agenteAduana,
      shipper: formData.shipper,
      direccionShipper: formData.direccionShipper,
      consignatario: formData.consignatario,
      direccionConsignatario: formData.direccionConsignatario,
      notificante: formData.notificante,
      direccionNotificante: formData.direccionNotificante,
      glosa: formData.glosa,
      descripcionTotal: formData.descripcionTotal,

      ordenInterna: formData.ordenInterna,

      agenteNaviera: formData.agenteNaviera,
      agenteCarga: formData.agenteCarga,
      nave: formData.nave,
      etaOrigen: formData.etaOrigen,
      etaDestino: formData.etaDestino,
      fechaBlProgramado: formData.fechaBlProgramado,
      fechaBlReal: formData.fechaBlReal,
      fechaCarguio: formData.fechaCarguio,
      fechaMaximaIngreso: formData.fechaMaximaIngreso,
      fechaEnvioDocum: formData.fechaEnvioDocumento,
      flete: formData.importeFlete,
      seguro: formData.importeSeguro,
      fleteTm: formData.fleteTm,
      booking: formData.booking,
      bl: formData.billOfLanding,
      tipoEnvio: formData.tipoEnvio,
      guiaDhl: formData.tipoEnvio == Constantes.P_ENVIO_FISICO ? formData.guiaDHL : null,
      dam: formData.dam,
      fechaDam: formData.fechaDam,
      idRegimen: formData.idRegimen,
      fechaDamRegularizacion: formData.fechaDamRegularizacion,
      fechaEntrega: formData.fechaDamDrawback,
      fechaDam41: formData.fechaDam41,
      fechaManifAduana: formData.fechaManifiestoAduana,
      fechaRecepcionDam41: formData.fechaRecepcionDam41,
      armador: formData.armador,

      emitidoEn: formData.emitidoEn,
      etiquetaEmpaque: formData.etiquetaEmpaque,
      empaque: formData.empaque,
      etiquetaMarca: formData.etiquetaMarca,
      marca: formData.marca,
      etiquetaUnidProd: formData.etiquetaUnidadProductiva,
      unidadProductiva: formData.unidadProductiva,
      etiquetaPrecioUnitario: formData.etiquetaPrecioUnitario,
      precioUnitario: formData.precioUnitario,
      etiquetaFacturacion: formData.etiquetaFacturacion,
      facturacion: formData.facturacion,
      etiquetaFormaPago: formData.etiquetaFormaPago,
      formaPago: formData.formaPago,
      fechaFactura: formData.fechaFactura
    };

    let posiciones: any[] = [];
    this.posiciones.controls.forEach((posicionForm: FormGroup) => {
      const posicionFormData = posicionForm.value;

      posiciones.push({
        id: posicionFormData.id,
        pedidoSap: posicionFormData.pedidoSap,
        codigoSAP: posicionFormData.codigoMaterial,
        descripcionComercialProducto: posicionFormData.descripcionMaterial,
        idMoneda: formData.idMoneda,
        idUnidadMedida: posicionFormData.idUnidadMedida,
        idUnidadMedidaVenta: posicionFormData.idUnidadMedidaVenta,
        unidadMedidaVenta: posicionFormData.unidadMedidaVenta,
        cantidad: posicionFormData.cantidad,
        cantidadVenta: posicionFormData.cantidadVenta,
        idCentro: posicionFormData.idCentro,
        idAlmacen: posicionFormData.idAlmacen,
        pesoTonelada: posicionFormData.pesoToneladas,
        fechaPackingList: posicionFormData.fechaPackingList,
        precioUnitario: posicionFormData.precioUnitario,
        importe: posicionFormData.importe,
        idPartidaArancelaria: posicionFormData.idPartidaArancelaria,
        folio: posicionFormData.folioFactura,
        referencia: posicionFormData.referencia,
        estado: 1
      });
    });

    let etiquetas = [];
    this.etiquetasArriba.controls.forEach((etiquetaForm: FormGroup) => {
      const etiquetaFormData = etiquetaForm.value;
      etiquetas.push({
        id: etiquetaFormData.id,
        idEtiqueta: etiquetaFormData.idEtiqueta,
        contenido: etiquetaFormData.contenido,
        posicion: POSICION_ETIQUETA.ARRIBA,
        estado: 1
      });
    });
    this.etiquetasAbajo.controls.forEach((etiquetaForm: FormGroup) => {
      const etiquetaFormData = etiquetaForm.value;
      etiquetas.push({
        id: etiquetaFormData.id,
        idEtiqueta: etiquetaFormData.idEtiqueta,
        contenido: etiquetaFormData.contenido,
        posicion: POSICION_ETIQUETA.ABAJO,
        estado: 1
      });
    });

    this.etiquetasEliminadas.forEach((etiqueta) => {
      etiquetas.push({
        id: etiqueta.id, estado: 0
      })
    });

    const data = {
      exportacion: cabecera,
      posiciones: posiciones,
      facturas: [],
      etiquetas: etiquetas
    }

    let request : any = {};
    request.formulario = data;
    request.usuario = this.settingsService.getUsername();

    this.settingsService.mostrarSpinner();
    this.service.guardar(request).subscribe(
      (response: any) => {
        this.settingsService.ocultarSpinner();
        if(response.cod_rpta == 1) {
          this.modalService.open(this.modalGuardadoExitoso, { centered: true, backdrop: 'static', keyboard: false });

          //Enviar Flete a SAP
          //this.modificarPedidoSap();
        } else {
          this.messageService.add({
            severity: "error",
            summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
            detail: JSON.stringify(response.rpta),
          });
        }
      },
      (error) => {
        this.settingsService.ocultarSpinner();
        this.messageService.add({
          severity: "error",
          summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
          detail: JSON.stringify(error),
        });
      }
    );
  }

  validarDatosPedidoSap() {
    let validacion: Validacion;
    let msjStock = '', msjPedido = '';
    this.posiciones.controls.forEach((posicionForm: FormGroup) => {
      const posicionFormData = posicionForm.value;
      console.log(posicionFormData);
      if(posicionFormData.stock == null || posicionFormData.stock == 0) {
        msjStock += 'Material <<' + posicionFormData.codigoMaterial + '>> no tiene Stock.\n'
        validacion.estado = false;
      }
    });

    validacion.mensaje = msjStock;

    return validacion;
  }

  modificarPedidoSap() {
    const data = ConfiguracionUtil.obtenerDatosPedidoSap(true, this.dataDocumento, this.documentoForm, this.posiciones, [], this.selectedRuta);
    let request : any = {};
    request.formulario = data;
    request.usuario = this.settingsService.getUsername();

    this.settingsService.mostrarSpinner();
    this.service.modificarPedidoSap(request).subscribe(
      (response) => {
        this.settingsService.ocultarSpinner();
        const responsePedido = ConfiguracionUtil.obtenerRespuestaSap(response);
        if(responsePedido.hayError) {
          this.messageService.add({
            severity: "warn",
            summary: 'Alerta',
            detail: responsePedido.mensajeError
          });
        } else {
          this.messageService.add({
            severity: "success",
            summary: 'Pedido SAP',
            detail: 'Pedido <<' + responsePedido.numeroPedido + '>> modificado correctamente.'
          });
        }

        setTimeout(() => this.ngOnInit(), 1500);
      },
      (error) => {
        this.settingsService.ocultarSpinner();
        this.messageService.add({
          severity: "error",
          summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
          detail: JSON.stringify(error),
        });
      }
    );
  }

  irAlListado() {
    this.modalService.dismissAll();
    this.router.navigate(['/exportaciones/documentomaritimo/intercompany/listar']);
  }

  actualizarEntrega() {
    if(this.posiciones.length == 0) return;
    const posicion = this.posiciones.controls[0].value;
    this.settingsService.mostrarSpinner();
    this.service.actualizarEntrega(this.dataDocumento.id, posicion.pedidoSap).subscribe(
      (response: any) => {
        this.settingsService.ocultarSpinner();
        if(response.cod_rpta == 1) {
          this.ngOnInit();
        } else {
          this.messageService.add({
            severity: "error",
            summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
            detail: JSON.stringify(response.rpta),
          });
        }
      },
      (error) => {
        this.settingsService.ocultarSpinner();
        this.messageService.add({
          severity: "error",
          summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
          detail: JSON.stringify(error),
        });
      }
    )
  }

  verExportacion() {
    const request = ConfiguracionUtil.obtenerDatosExportacion(this.dataDocumento, this.documentoForm, this.posiciones, this.regimenes, this.partidas, this.settingsService.getUsername());
    this.settingsService.mostrarSpinner();
    Promise.all([
      this.service.imprimirExportacionMaritimo(request).toPromise()
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
        this.tipoDocumento = 0;
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

  verFacturaComercial(versionIngles: boolean, tipoFactura: number) {
    this.versionIngles = versionIngles;
    this.original = true;
    this.tipoFactura = tipoFactura;

    const nombreUsuario = this.settingsService.getFullname();
    const usuario = this.settingsService.getUsername();
    const codigoPersonal = this.settingsService.getSesionSSO().strPv_cpersonal;

    const request = ConfiguracionUtil.obtenerDatosFacturaComercial(this.dataDocumento, this.documentoForm, this.posiciones, this.facturas, this.incoterms, this.partidas, nombreUsuario, usuario, codigoPersonal);
    this.settingsService.mostrarSpinner();
    Promise.all([
      this.service.imprimirFacturaComercial(request, versionIngles, true, tipoFactura).toPromise()
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
        this.tipoDocumento = 1;
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

  descargarPdf(original: boolean) {
    this.original = original;
    const nombreUsuario = this.settingsService.getFullname();
    const usuario = this.settingsService.getUsername();
    const codigoPersonal = this.settingsService.getSesionSSO().strPv_cpersonal;

    this.settingsService.mostrarSpinner();
    if( this.tipoDocumento == 0) {
      const request = ConfiguracionUtil.obtenerDatosExportacion(this.dataDocumento, this.documentoForm, this.posiciones, this.regimenes, this.partidas, usuario);
      Promise.all([
        this.service.imprimirExportacionMaritimo(request).toPromise()
      ]).then(
        (data) => {
          this.settingsService.ocultarSpinner();
          let url = window.URL.createObjectURL(data[0]);
          let a = document.createElement('a');
          document.body.appendChild(a);
          a.setAttribute('style', 'display: none');
          a.href = url;
          a.download = "EXPORTACION_MARITIMA_" + this.dataDocumento.codigo + '.pdf';
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
    } else {
      const request = ConfiguracionUtil.obtenerDatosFacturaComercial(this.dataDocumento, this.documentoForm, this.posiciones, this.facturas, this.incoterms, this.partidas, nombreUsuario, usuario, codigoPersonal);
      Promise.all([
        this.service.imprimirFacturaComercial(request, this.versionIngles, this.original, this.tipoFactura).toPromise()
      ]).then(
        (data) => {
          this.settingsService.ocultarSpinner();
          let url = window.URL.createObjectURL(data[0]);
          let a = document.createElement('a');
          document.body.appendChild(a);
          a.setAttribute('style', 'display: none');
          a.href = url;
          a.download = "FACTURA_COMERCIAL_" + this.dataDocumento.codigo + '.pdf';
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

  fleteOblitario(): boolean {
    const formData: any = this.documentoForm.getRawValue();
    return formData.idIncotermComercial == Constantes.P_ID_INCOTERM_CFR || formData.idIncotermComercial == Constantes.P_ID_INCOTERM_CIF;
  }

  seguroOblitario(): boolean {
    const formData: any = this.documentoForm.getRawValue();
    return formData.idIncotermComercial == Constantes.P_ID_INCOTERM_CIF;
  }

  esContenedor(): boolean {
    const formData: any = this.documentoForm.getRawValue();
    return formData.idTipoDespacho == Constantes.P_ID_CONTENEDOR;
  }

  agregarEtiqueta(posicion: POSICION_ETIQUETA) {
    const etiquetaForm = new FormGroup({
      id: new FormControl(0),
      idEtiqueta: new FormControl(),
      contenido: new FormControl()
    });

    if(posicion == POSICION_ETIQUETA.ARRIBA) {
      this.etiquetasArriba.push(etiquetaForm);
    } else {
      this.etiquetasAbajo.push(etiquetaForm);
    }
  }

  eliminarEtiquetaArriba(index: number) {
    const etiquetaForm = this.etiquetasArriba.at(index) as FormGroup;
    const dataEtiqueta = etiquetaForm.getRawValue();
    if(dataEtiqueta.id > 0) {
      this.etiquetasEliminadas.push(dataEtiqueta);
    }
    this.etiquetasArriba.removeAt(index);
  }

  eliminarEtiquetaAbajo(index: number) {
    const etiquetaForm = this.etiquetasAbajo.at(index) as FormGroup;
    const dataEtiqueta = etiquetaForm.getRawValue();
    if(dataEtiqueta.id > 0) {
      this.etiquetasEliminadas.push(dataEtiqueta);
    }
    this.etiquetasAbajo.removeAt(index);
  }

  refrescarEtiquetas(event) {
    this.settingsService.mostrarSpinner();
    this.configuracionService.listarEtiquetas("")
    .subscribe(
      (response: any) => {
        this.settingsService.ocultarSpinner();
        this.etiquetas = response as Etiqueta[];
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
