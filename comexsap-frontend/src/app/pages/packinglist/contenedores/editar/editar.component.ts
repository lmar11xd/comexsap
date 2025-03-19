import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { Utils } from 'src/app/utils/utils';
import { PackingList } from '../../to/packing-list';
import { Contenedor } from '../../to/contenedor';
import { Material } from 'src/app/pages/configuracion/to/material';
import { DocumentoMaritimoPosicion } from 'src/app/pages/documentomaritimo/to/documentomaritimoposicion';
import { Parametro } from 'src/app/pages/configuracion/to/parametro';
import { Centro } from 'src/app/pages/configuracion/to/centro';
import { Constantes } from 'src/app/utils/constantes';
import { EDITAR_PACKINGLIST_CONTENEDORES, LISTAR_PACKINGLIST_CONTENEDORES } from 'src/app/shared/breadcrumb/breadcrumb';
import { MenuItem, MessageService } from 'primeng/api';
import { ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { ConfiguracionService } from 'src/app/pages/configuracion/configuracion.sevice';
import { Destinatario } from 'src/app/pages/configuracion/to/destinatario';
import { CotizacionPosicion } from 'src/app/pages/cotizacion/to/cotizacionposicion';
import { DocumentoMaritimo } from 'src/app/pages/documentomaritimo/to/documentomaritimo';
import { Breadcrumb2Service } from 'src/app/shared/breadcrumb2.service';
import { PackingListService } from '../../packinglist.service';
import * as XLSX from 'xlsx';

@Component({
  selector: 'app-editar',
  templateUrl: './editar.component.html',
  styleUrls: ['./editar.component.scss']
})
export class EditarPackingListContenedorComponent implements OnInit {
  @ViewChild('modalGuardadoExitoso') modalGuardadoExitoso: ElementRef;
  @ViewChild('modalVerPdf') modalVerPdf: ElementRef;

  pdfSrc: Uint8Array;

  active = 3;
  activeDatos = 1;
  activeContacto = 1;
  codigoPacking : string  = '';
  idExportacion: any = null;
  parent: string = null;
  dataDocumento: DocumentoMaritimo;
  dataPosiciones: DocumentoMaritimoPosicion[] = [];
  dataContenedor: Contenedor[] = [];
  dataPackings: PackingList[] = [];
  posicionesEliminadas: CotizacionPosicion[] = [];
  contenedoresEliminadas: Contenedor[] = [];
  packingsListEliminadas: PackingList[] = [];
  item : number = 0 ;
  itemPacking : number = 0 ;
  packinglistForm: FormGroup;
  destinatarios: Destinatario[] = [];

  pagos: Parametro[] = [];
  incoterms: Parametro[] = [];
  estados: Parametro[] = [];
  monedas: Parametro[] = [];
  despachos: Parametro[] = [];
  listaPrecios: Parametro[] = [];
  agentesNaviera: Parametro[] = [];
  agentesCarga: Parametro[] = [];
  regimenes: Parametro[] = [];
  centros: Centro[] = [];
  partidas: Parametro[] = [];

  ExcelData : any;

  constructor(
    private activatedRoute: ActivatedRoute,
    private breadcrumb2Service: Breadcrumb2Service,
    private messageService: MessageService,
    private modalService: NgbModal,
    private settingsService: SettingsService,
    private configuracionService: ConfiguracionService,
    private packingListService: PackingListService,
    private service: PackingListService
  ) {
  }

  ngOnInit(): void {
    this.idExportacion = this.activatedRoute.snapshot.paramMap.get("id");
    this.parent = this.activatedRoute.snapshot.paramMap.get("parent");
    this.inicializarBreadcrumb(this.idExportacion);
    this.crearFormulario();
    this.inicializar();
  }

  inicializarBreadcrumb(id: string){
    EDITAR_PACKINGLIST_CONTENEDORES[0].url = EDITAR_PACKINGLIST_CONTENEDORES[0].url.replace(':id', id);
    let BREADCRUMBS: MenuItem[] = [];
    BREADCRUMBS = BREADCRUMBS.concat(LISTAR_PACKINGLIST_CONTENEDORES);
    BREADCRUMBS = BREADCRUMBS.concat(EDITAR_PACKINGLIST_CONTENEDORES);
    this.breadcrumb2Service.addBreadcrumbs(BREADCRUMBS);
  }

  inicializar() {
    this.settingsService.mostrarSpinner();
    Promise.all([
      this.configuracionService.listarParametroxDominio(Constantes.P_D011).toPromise(),//Condicion Pago Cotizacion
      this.configuracionService.listarParametroxDominio(Constantes.P_D003).toPromise(),//Incoterm
      this.configuracionService.listarParametroxDominio(Constantes.P_D010).toPromise(),//Estado
      this.configuracionService.listarParametroxDominio(Constantes.P_D006).toPromise(),//Moneda
      this.configuracionService.listarParametroxDominio(Constantes.P_D008).toPromise(),//Despacho
      this.configuracionService.listarParametroxDominio(Constantes.P_D013).toPromise(),//Lista Precios
      this.configuracionService.listarParametroxDominio(Constantes.P_D016).toPromise(),//Agente Naviera
      this.configuracionService.listarParametroxDominio(Constantes.P_D017).toPromise(),//Agente Carga
      this.configuracionService.listarParametroxDominio(Constantes.P_D022).toPromise(),//Regimen
      this.configuracionService.listarCentros("").toPromise(),
      this.configuracionService.listarParametroxDominio(Constantes.P_D023).toPromise(),//Partida Arancelarias
      this.packingListService.obtenerDetallePackingListxId(this.idExportacion).toPromise()
    ]).then(
      (data :any[]) => {
        this.settingsService.ocultarSpinner();
        this.pagos = Parametro.toArray(data[0]);
        this.incoterms = Parametro.toArray(data[1]);
        this.estados = Parametro.toArray(data[2]);
        this.monedas = Parametro.toArray(data[3]);
        this.despachos = Parametro.toArray(data[4]);
        this.listaPrecios = Parametro.toArray(data[5]);
        this.agentesNaviera = Parametro.toArray(data[6]);
        this.agentesCarga = Parametro.toArray(data[7]);
        this.regimenes = Parametro.toArray(data[8]);
        this.centros = Centro.toArray(data[9]);
        this.partidas = Parametro.toArray(data[10]);

        this.dataDocumento = data[11].exportacion;
        if(this.dataDocumento.intercompany == 1) {
          this.dataPosiciones = data[11].posicionesIntercompany;
        } else {
          this.dataPosiciones = data[11].posiciones;
        }
        this.dataContenedor = data[11].contenedores;
        this.dataPackings = data[11].packingList;

        if(this.dataPosiciones != null) {
          this.dataPosiciones.forEach((posicion: DocumentoMaritimoPosicion) => {
            let componentes: FormGroup[] = [];
            if(posicion.componentes != null) {
              posicion.componentes.forEach((componente: any) => {
                const materialComp = new Material(componente.codigoSap, componente.descripcionProducto, componente.codigoSapUnidadMedida, componente.codigoSapUnidadMedida, 0, 0);
                componentes.push(new FormGroup({
                  checked: new FormControl(false),
                  id: new FormControl(componente.id),
                  idPadre: new FormControl(componente.idPadre),
                  item: new FormControl(componente.item),
                  codigoMaterial: new FormControl(componente.codigoSap),
                  descripcionMaterial: new FormControl(componente.descripcionProducto),
                  cantidadVenta: new FormControl(componente.cantidadVenta),
                  unidadMedidaVenta: new FormControl(componente.codigoSapUnidadMedidaVenta),
                  pesoToneladas: new FormControl(componente.pesoTonelada),
                  fechaDisponibilidad: new FormControl(Utils.dateTimeToStringDate(componente.fechaDisponibilidad)),
                  precioUnitarioSap: new FormControl(componente.precioUnitarioSap),
                  precioUnitario: new FormControl(componente.precioUnitario),
                  importe: new FormControl(componente.importe),
                  selectedMaterial: new FormControl(materialComp),
                }));
              });
            }

            const posicionForm = new FormGroup({
              id: new FormControl(posicion.id),
              idPosicion: new FormControl(posicion.idPosicion),
              codigoPedido: new FormControl(posicion.codigoPedido),
              codigoMaterial: new FormControl(posicion.codigoSAP),
              descripcion: new FormControl(posicion.descripcionProducto),
              descripcionMaterial: new FormControl(posicion.descripcionComercialProducto),
              cantidadVenta: new FormControl(posicion.cantidadVenta),
              idUnidadMedidaVenta: new FormControl(posicion.idUnidadMedidaVenta),
              unidadMedidaVenta: new FormControl(posicion.codigoSAPUnidadMedidaVenta),
              idCentroSuministro: new FormControl(posicion.idCentro),
              descripcionCentro: new FormControl(posicion.codigoSapCentro + ' - ' + posicion.descripcionCentro),
              idAlmacen: new FormControl(posicion.idAlmacen),
              descripcionAlmacen: new FormControl(posicion.codigoSapAlmacen + ' - ' + posicion.descripcionAlmacen),
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
              componenteTexto: new FormControl(posicion.componenteTexto),
              esPadre: new FormControl(componentes.length > 0),
              componentes: new FormArray(componentes)
            });
            this.posiciones.push(posicionForm);
          });
        }

        if(this.dataContenedor != null) {
          this.dataContenedor.forEach((contenedor: Contenedor) => {
            this.item =this.item + 1;
            const contenedorForm = new FormGroup({
              checked: new FormControl(false),
              id: new FormControl(contenedor.id),
              item : new FormControl(this.item) ,
              descripcion: new FormControl(contenedor.descripcion),
              numero: new FormControl(contenedor.numero),
              dtSAP: new FormControl(contenedor.dtSAP),
              chofer: new FormControl(contenedor.chofer),
              placa: new FormControl(contenedor.placa),
              placaCarrete: new FormControl(contenedor.placaCarrete),
              brevete: new FormControl(contenedor.brevete),
              codigo: new FormControl(contenedor.codigo),
              precinto1: new FormControl(contenedor.precinto1),
              precinto2: new FormControl(contenedor.precinto2),
              bulto: new FormControl(contenedor.bulto),
              taco: new FormControl(contenedor.taco),
              booking: new FormControl(contenedor.booking),
              horaInicio: new FormControl(contenedor.horaInicio),
              horaFinal: new FormControl(contenedor.horaFinal),
              pesoComex: new FormControl(contenedor.pesoComex),
              idExportacion: new FormControl(contenedor.idExportacion)

            });
            this.contenedores.push(contenedorForm);

          });
        }

        if(this.dataPackings != null) {
          this.dataPackings.forEach((packings: PackingList) => {
            this.itemPacking =this.itemPacking + 1;
            const packingsForm = new FormGroup({
              checked: new FormControl(false),
              id: new FormControl(packings.id),
              item: new FormControl(this.itemPacking),
              codigoPedido: new FormControl(packings.codigoPedido),
              codigo: new FormControl(packings.codigo),
              codigoAgrupador: new FormControl(packings.codigoAgrupador),
              denominacion: new FormControl(packings.denominacion),
              lote: new FormControl(packings.lote),
              colada: new FormControl(packings.colada),
              pesoNetoTonelada: new FormControl(packings.pesoNetoTonelada),
              hu: new FormControl(packings.hu),
              numero: new FormControl(packings.numero),
              idExportacion: new FormControl(packings.idExportacion),
              cantidad: new FormControl(packings.cantidad),
              dimension: new FormControl(packings.dimension),
              idContenedor: new FormControl(packings.idContenedor),
              pesoComex: new FormControl(packings.pesoComex),
              unAlmacen: new FormControl(packings.unAlmacen),
              peso: new FormControl(packings.peso),
              pesoRealTonelada: new FormControl(packings.pesoRealTonelada),
              unidad: new FormControl(packings.unidad),
              centro: new FormControl(packings.centro),
              piezasStock: new FormControl(packings.piezasStock),
              almacen: new FormControl(packings.almacen),
              pesoTeorico: new FormControl(packings.pesoTeorico),
              pesoTeoricoTonelada: new FormControl(packings.pesoTeoricoTonelada),
              cliente: new FormControl(packings.cliente),
              nave: new FormControl(packings.nave),
              puertoDestino: new FormControl(packings.puertoDestino),
              loteCliente: new FormControl(packings.loteCliente),
              colorStick: new FormControl(packings.colorStick),
              medida: new FormControl(packings.medida),
              longitud: new FormControl(packings.longitud),
              grado: new FormControl(packings.grado),
              estado: new FormControl(packings.estado)
            });
            this.packinglist.push(packingsForm);
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
    this.packinglistForm = new FormGroup({
      codigo: new FormControl(),
      existeClienteSap: new FormControl(),
      codigoSapCliente: new FormControl(),
      codigoCliente: new FormControl(),
      nombreCliente: new FormControl(),
      codigoDestinatario: new FormControl(),
      nombreDestinatario: new FormControl(),
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
      ruta: new FormControl(),
      agenteAduana: new FormControl(),
      shipper: new FormControl(),
      direccionShipper: new FormControl(),
      consignatario: new FormControl(),
      direccionConsignatario: new FormControl(),
      notificante: new FormControl(),
      direccionNotificante: new FormControl(),
      glosa: new FormControl(),
      vigenciaOferta: new FormControl(),
      diasOferta: new FormControl(),
      tiempoEntrega: new FormControl(),
      contactoCargo: new FormControl(),
      observaciones: new FormControl(),
      posiciones: new FormArray([]),
      estadoDocumento: new FormControl(),
      contenedores: new FormArray([]),
      packinglist: new FormArray([]),
      idExportacion: new FormControl(),
      item: new FormControl(),

      //Informacion Mari­tima
      agenteNaviera: new FormControl(),
      agenteCarga: new FormControl(),
      nave: new FormControl(),

      etaOrigen: new FormControl(),
      etaDestino: new FormControl(),
      fechaBlProgramado: new FormControl(),
      fechaBlReal: new FormControl(),
      fechaCarguio: new FormControl(),
      fechaEnvioDocumento: new FormControl(),
      importeFlete: new FormControl(),
      importeSeguro: new FormControl(),

      numeroContenedor: new FormControl(),
      booking: new FormControl(),
      billOfLanding: new FormControl(),
      guiaDHL: new FormControl(),

      dam: new FormControl(),
      idRegimen: new FormControl(),
      fechaDamDrawback: new FormControl(),
      fechaManifiestoAduana: new FormControl(),
      fechaDam: new FormControl(),
      fechaDamRegularizacion: new FormControl(),
      fechaDam41: new FormControl()
    });
  }

  inicializarFormulario() {
    if(this.dataDocumento != null) {
      this.packinglistForm.controls['codigo'].patchValue(this.dataDocumento.codigo, {onlySelf: true});
      this.packinglistForm.controls['idExportacion'].patchValue(this.dataDocumento.id, {onlySelf: true});
      this.packinglistForm.controls['nombreCliente'].patchValue(this.dataDocumento.codigoSapCliente + ' - ' + this.dataDocumento.nombreCliente, {onlySelf: true});
      this.packinglistForm.controls['codigoDestinatario'].patchValue(this.dataDocumento.codigoSapDestinatario, {onlySelf: true});
      this.packinglistForm.controls['nombreDestinatario'].patchValue(this.dataDocumento.codigoSapDestinatario + ' - ' + this.dataDocumento.nombreDestinatario, {onlySelf: true});
      this.packinglistForm.controls['idPuertoOrigen'].patchValue(this.dataDocumento.idPuertoOrigen, {onlySelf: true});
      this.packinglistForm.controls['puertoOrigen'].patchValue(this.dataDocumento.codigoSapPuertoOrigen + ' - ' + this.dataDocumento.puertoOrigen, {onlySelf: true});
      this.packinglistForm.controls['idPuertoDestino'].patchValue(this.dataDocumento.idPuertoDestino, {onlySelf: true});
      this.packinglistForm.controls['puertoDestino'].patchValue(this.dataDocumento.codigoPuertoDestino + ' - ' + this.dataDocumento.puertoDestino, {onlySelf: true});
      this.packinglistForm.controls['idIncoterm'].patchValue(this.dataDocumento.idIncoterm, {onlySelf: true});
      this.packinglistForm.controls['idIncotermComercial'].patchValue(this.dataDocumento.idIncotermComercial, {onlySelf: true});
      this.packinglistForm.controls['idTipoDespacho'].patchValue(this.dataDocumento.idDespacho, {onlySelf: true});
      this.packinglistForm.controls['idCondicionPago'].patchValue(this.dataDocumento.idCondicionPago, {onlySelf: true});
      this.packinglistForm.controls['idMoneda'].patchValue(this.dataDocumento.idMoneda, {onlySelf: true});
     // this.packinglistForm.controls['fechaListaPrecio'].patchValue(Utils.dateTimeToDate(this.dataDocumento.fechaListaPrecio), {onlySelf: true});
      this.packinglistForm.controls['idListaPrecio'].patchValue(this.dataDocumento.idListaPrecio, {onlySelf: true});
      this.packinglistForm.controls['idRuta'].patchValue(this.dataDocumento.idRuta, {onlySelf: true});
      this.packinglistForm.controls['ruta'].patchValue(this.dataDocumento.codigoRuta + ' - ' + this.dataDocumento.nombreRuta, {onlySelf: true});
      this.packinglistForm.controls['agenteAduana'].patchValue(this.dataDocumento.agenteAduana, {onlySelf: true});
      this.packinglistForm.controls['shipper'].patchValue(this.dataDocumento.shipper, {onlySelf: true});
      this.packinglistForm.controls['direccionShipper'].patchValue(this.dataDocumento.direccionShipper, {onlySelf: true});
      this.packinglistForm.controls['consignatario'].patchValue(this.dataDocumento.consignatario, {onlySelf: true});
      this.packinglistForm.controls['direccionConsignatario'].patchValue(this.dataDocumento.direccionConsignatario, {onlySelf: true});
      this.packinglistForm.controls['notificante'].patchValue(this.dataDocumento.notificante, {onlySelf: true});
      this.packinglistForm.controls['direccionNotificante'].patchValue(this.dataDocumento.direccionNotificante, {onlySelf: true});
      this.packinglistForm.controls['glosa'].patchValue(this.dataDocumento.glosa, {onlySelf: true});
      this.packinglistForm.controls['agenteNaviera'].patchValue(this.dataDocumento.agenteNaviera, {onlySelf: true});
      this.packinglistForm.controls['agenteCarga'].patchValue(this.dataDocumento.agenteCarga, {onlySelf: true});
      this.packinglistForm.controls['nave'].patchValue(this.dataDocumento.nave, {onlySelf: true});
      this.packinglistForm.controls['etaOrigen'].patchValue(Utils.stringToDate(this.dataDocumento.etaOrigen), {onlySelf: true});
      this.packinglistForm.controls['etaDestino'].patchValue(Utils.stringToDate(this.dataDocumento.etaDestino), {onlySelf: true});
      this.packinglistForm.controls['fechaBlProgramado'].patchValue(Utils.stringToDate(this.dataDocumento.fechaBlProgramado), {onlySelf: true});
      this.packinglistForm.controls['fechaBlReal'].patchValue(Utils.stringToDate(this.dataDocumento.fechaBlReal), {onlySelf: true});
      this.packinglistForm.controls['fechaCarguio'].patchValue(Utils.stringToDate(this.dataDocumento.fechaCarguio), {onlySelf: true});
      this.packinglistForm.controls['fechaEnvioDocumento'].patchValue(Utils.stringToDate(this.dataDocumento.fechaEnvioDocum), {onlySelf: true});
      this.packinglistForm.controls['importeFlete'].patchValue(this.dataDocumento.flete, {onlySelf: true});
      this.packinglistForm.controls['importeSeguro'].patchValue(this.dataDocumento.seguro, {onlySelf: true});
      this.packinglistForm.controls['numeroContenedor'].patchValue(this.dataDocumento.numeroContenedor ? this.dataDocumento.numeroContenedor : null, {onlySelf: true});
      this.packinglistForm.controls['booking'].patchValue(this.dataDocumento.booking, {onlySelf: true});
      this.packinglistForm.controls['billOfLanding'].patchValue(this.dataDocumento.bl, {onlySelf: true});
      this.packinglistForm.controls['guiaDHL'].patchValue(this.dataDocumento.guiaDhl, {onlySelf: true});
      this.packinglistForm.controls['dam'].patchValue(this.dataDocumento.dam, {onlySelf: true});
      this.packinglistForm.controls['idRegimen'].patchValue(this.dataDocumento.idRegimen ? this.dataDocumento.idRegimen : null, {onlySelf: true});
      this.packinglistForm.controls['fechaDamDrawback'].patchValue(Utils.stringToDate(this.dataDocumento.fechaEntrega), {onlySelf: true});
      this.packinglistForm.controls['fechaManifiestoAduana'].patchValue(Utils.stringToDate(this.dataDocumento.fechaManifAduana), {onlySelf: true});
      this.packinglistForm.controls['fechaDam'].patchValue(Utils.stringToDate(this.dataDocumento.fechaDam), {onlySelf: true});
      this.packinglistForm.controls['fechaDamRegularizacion'].patchValue(Utils.stringToDate(this.dataDocumento.fechaDamRegularizacion), {onlySelf: true});
      this.packinglistForm.controls['fechaDam41'].patchValue(Utils.stringToDate(this.dataDocumento.fechaDam41), {onlySelf: true});

      this.packinglistForm.controls['estadoDocumento'].patchValue(this.dataDocumento.idEstadoDocumento, {onlySelf: true});
      this.codigoPacking = this.dataDocumento.codigoPacking;

      this.packinglistForm.controls['codigo'].disable();
      this.packinglistForm.controls['existeClienteSap'].disable();
      this.packinglistForm.controls['codigoCliente'].disable();
      this.packinglistForm.controls['nombreCliente'].disable();
      this.packinglistForm.controls['codigoDestinatario'].disable();
      this.packinglistForm.controls['nombreDestinatario'].disable();
      this.packinglistForm.controls['idPuertoOrigen'].disable();
      this.packinglistForm.controls['puertoOrigen'].disable();
      this.packinglistForm.controls['idPuertoDestino'].disable();
      this.packinglistForm.controls['puertoDestino'].disable();
      this.packinglistForm.controls['idIncoterm'].disable();
      this.packinglistForm.controls['idIncotermComercial'].disable();
      this.packinglistForm.controls['idTipoDespacho'].disable();
      this.packinglistForm.controls['idCondicionPago'].disable();
      this.packinglistForm.controls['idRuta'].disable();
      this.packinglistForm.controls['ruta'].disable();
      this.packinglistForm.controls['idMoneda'].disable();
      this.packinglistForm.controls['fechaListaPrecio'].disable();
      this.packinglistForm.controls['idListaPrecio'].disable();

      this.packinglistForm.controls['agenteNaviera'].disable();
      this.packinglistForm.controls['agenteCarga'].disable();
      this.packinglistForm.controls['nave'].disable();
      this.packinglistForm.controls['etaOrigen'].disable();
      this.packinglistForm.controls['etaDestino'].disable();
      this.packinglistForm.controls['fechaBlProgramado'].disable();
      this.packinglistForm.controls['fechaBlReal'].disable();
      this.packinglistForm.controls['fechaCarguio'].disable();
      this.packinglistForm.controls['fechaEnvioDocumento'].disable();
      this.packinglistForm.controls['importeFlete'].disable();
      this.packinglistForm.controls['importeSeguro'].disable();
      this.packinglistForm.controls['numeroContenedor'].disable();
      this.packinglistForm.controls['booking'].disable();
      this.packinglistForm.controls['billOfLanding'].disable();
      this.packinglistForm.controls['guiaDHL'].disable();
      this.packinglistForm.controls['dam'].disable();
      this.packinglistForm.controls['fechaDam'].disable();
      this.packinglistForm.controls['idRegimen'].disable();
      this.packinglistForm.controls['fechaDamRegularizacion'].disable();
      this.packinglistForm.controls['fechaDamDrawback'].disable();
      this.packinglistForm.controls['fechaDam41'].disable();

      this.packinglistForm.controls['agenteAduana'].disable();
      this.packinglistForm.controls['shipper'].disable();
      this.packinglistForm.controls['direccionShipper'].disable();
      this.packinglistForm.controls['consignatario'].disable();
      this.packinglistForm.controls['direccionConsignatario'].disable();
      this.packinglistForm.controls['notificante'].disable();
      this.packinglistForm.controls['direccionNotificante'].disable();
      this.packinglistForm.controls['glosa'].disable();

      this.packinglistForm.controls['codigo'].setValidators([Validators.required]);
      this.packinglistForm.controls['existeClienteSap'].setValidators([Validators.required]);
      this.packinglistForm.controls['codigoCliente'].setValidators([Validators.required]);
      this.packinglistForm.controls['nombreCliente'].setValidators([Validators.required]);
      this.packinglistForm.controls['codigoDestinatario'].setValidators([Validators.required]);
      this.packinglistForm.controls['nombreDestinatario'].setValidators([Validators.required]);
      this.packinglistForm.controls['idPuertoOrigen'].setValidators([Validators.required]);
      this.packinglistForm.controls['puertoOrigen'].setValidators([Validators.required]);
      this.packinglistForm.controls['idPuertoDestino'].setValidators([Validators.required]);
      this.packinglistForm.controls['puertoDestino'].setValidators([Validators.required]);
      this.packinglistForm.controls['idIncoterm'].setValidators([Validators.required]);
      this.packinglistForm.controls['idIncotermComercial'].setValidators([Validators.required]);
      this.packinglistForm.controls['idTipoDespacho'].setValidators([Validators.required]);
      this.packinglistForm.controls['idCondicionPago'].setValidators([Validators.required]);
      this.packinglistForm.controls['idRuta'].setValidators([Validators.required]);
      this.packinglistForm.controls['ruta'].setValidators([Validators.required]);
      this.packinglistForm.controls['idMoneda'].setValidators([Validators.required]);
      this.packinglistForm.controls['fechaListaPrecio'].setValidators([Validators.required]);
      this.packinglistForm.controls['idListaPrecio'].setValidators([Validators.required]);

      this.packinglistForm.controls['agenteNaviera'].setValidators([Validators.required]);
      this.packinglistForm.controls['agenteCarga'].setValidators([Validators.required]);
      this.packinglistForm.controls['nave'].setValidators([Validators.required]);
      this.packinglistForm.controls['etaOrigen'].setValidators([Validators.required]);
      this.packinglistForm.controls['etaDestino'].setValidators([Validators.required]);
      this.packinglistForm.controls['fechaBlProgramado'].setValidators([Validators.required]);
      this.packinglistForm.controls['fechaBlReal'].setValidators([Validators.required]);
      this.packinglistForm.controls['fechaCarguio'].setValidators([Validators.required]);
      this.packinglistForm.controls['fechaEnvioDocumento'].setValidators([Validators.required]);
      this.packinglistForm.controls['importeFlete'].setValidators([Validators.required]);
      this.packinglistForm.controls['importeSeguro'].setValidators([Validators.required]);
      this.packinglistForm.controls['numeroContenedor'].setValidators([Validators.required]);
      this.packinglistForm.controls['booking'].setValidators([Validators.required]);
      this.packinglistForm.controls['billOfLanding'].setValidators([Validators.required]);
      this.packinglistForm.controls['guiaDHL'].setValidators([Validators.required]);
      this.packinglistForm.controls['dam'].setValidators([Validators.required]);
      this.packinglistForm.controls['fechaDam'].setValidators([Validators.required]);
      this.packinglistForm.controls['idRegimen'].setValidators([Validators.required]);
      this.packinglistForm.controls['fechaDamRegularizacion'].setValidators([Validators.required]);
      this.packinglistForm.controls['fechaDamDrawback'].setValidators([Validators.required]);
      this.packinglistForm.controls['fechaDam41'].setValidators([Validators.required]);

      this.packinglistForm.controls['agenteAduana'].setValidators([Validators.required]);
      this.packinglistForm.controls['shipper'].setValidators([Validators.required]);
      this.packinglistForm.controls['direccionShipper'].setValidators([Validators.required]);
      this.packinglistForm.controls['consignatario'].setValidators([Validators.required]);
      this.packinglistForm.controls['direccionConsignatario'].setValidators([Validators.required]);
      this.packinglistForm.controls['notificante'].setValidators([Validators.required]);
      this.packinglistForm.controls['direccionNotificante'].setValidators([Validators.required]);
      this.packinglistForm.controls['glosa'].setValidators([Validators.required]);
    }
  }

  get posiciones() {
    return this.packinglistForm.controls["posiciones"] as FormArray;
  }

  get contenedores() {
    return this.packinglistForm.controls["contenedores"] as FormArray;
  }

  get packinglist() {
    return this.packinglistForm.controls["packinglist"] as FormArray;
  }

  obtenerSiguienteItem(): number {
    let item = 0;
    this.posiciones.controls.forEach(posicion => {
      if(posicion.value.item > item) { item = posicion.value.item; }
    });
    return item + 10;
  }

  obtenerSiguienteContenedor(): number {
    let item = 0;
    this.contenedores.controls.forEach(contenedor => {
      if(contenedor.value.numero > item) { item = contenedor.value.numero; }
    });
    return item + 1;
  }

  agregarContenedor() {

      this.desmarcarContenedores();
      const item = this.obtenerSiguienteContenedor();
      const contenedorForm = new FormGroup({
        checked: new FormControl(false),
        id: new FormControl(0) ,
        item: new FormControl(item),
        numero: new FormControl(item),
        descripcion: new FormControl(""),
        dtSAP: new FormControl(""),
        chofer: new FormControl(""),
        placa: new FormControl(""),
        placaCarrete: new FormControl(""),
        brevete: new FormControl(""),
        horaInicio: new FormControl(),
        horaFinal: new FormControl(),
        pesoComex: new FormControl()
      });
      this.contenedores.push(contenedorForm);
  }

  quitarContenedores() {
    const contenedores = this.packinglistForm.value.contenedores.filter(contenedor => contenedor.checked);
    if(contenedores.length > 0) {
      contenedores.forEach(element => {
        const index = this.packinglistForm.value.contenedores.findIndex(contenedor => contenedor.item == element.item);
        if(element.id != 0) {
          this.contenedoresEliminadas.push(element);
        }
        this.contenedores.removeAt(index);
      });
      this.reiniciarContenedorItem();
    } else {
      this.messageService.add({
        severity: "warn",
        summary: "Alerta",
        detail: "Por favor, seleccione al menos un registro para eliminarlo"
      });
    }
  }

  quitarPackingsList() {
    const packings = this.packinglistForm.value.packinglist.filter(packing => packing.checked);
    if(packings.length > 0) {
      packings.forEach(element => {
        const index = this.packinglistForm.value.packinglist.findIndex(packing => packing.item == element.item);
        if(element.id != 0) {
          this.packingsListEliminadas.push(element);
        }
        this.packinglist.removeAt(index);
      });
      this.reiniciarPackingItem();
    } else {
      this.messageService.add({
        severity: "warn",
        summary: "Alerta",
        detail: "Por favor, seleccione al menos un registro para eliminarlo"
      });
    }
  }

  reiniciarPosicionItem() {
    let item = 10;
    this.posiciones.controls.forEach((posicionForm: FormGroup) => {
      posicionForm.controls['item'].patchValue(item);
      item += 10;
    });
  }

  reiniciarContenedorItem() {
    let item = 10;
    this.contenedores.controls.forEach((contenedorForm: FormGroup) => {
      contenedorForm.controls['item'].patchValue(item);
      item += 10;
    });
  }

  reiniciarPackingItem() {
    let item = 10;
    this.packinglist.controls.forEach((packingForm: FormGroup) => {
      packingForm.controls['item'].patchValue(item);
      item += 10;
    });
  }

  seleccionarTodasLosContenedores(event) {
    this.contenedores.controls.forEach((contenedorForm: FormGroup) => {
      contenedorForm.controls['checked'].patchValue(event.target.checked);
    });
  }

  seleccionarTodasLosPackingList(event) {
    this.packinglist.controls.forEach((packingForm: FormGroup) => {
      packingForm.controls['checked'].patchValue(event.target.checked);
    });
  }

  desmarcarPosiciones() {
    this.posiciones.controls.forEach((posicionForm: FormGroup) => {
      posicionForm.controls['checked'].patchValue(false);
    });
  }

  desmarcarContenedores() {
    this.contenedores.controls.forEach((contenedorForm: FormGroup) => {
      contenedorForm.controls['checked'].patchValue(false);
    });
  }

  mostrarModal(content: string) {
    this.modalService.open(content, { centered: true });
  }

  irAlListado() {
    this.modalService.dismissAll();
   // this.router.navigate(['/exportaciones/packinglist/listar-packinglist']);
  }

  cargarExcelPacking(event:any){
    let file = event.target.files[0];
    let fileReader = new FileReader();
    fileReader.readAsBinaryString(file);
    fileReader.onload = (e) => {
      var workbook = XLSX.read(fileReader.result,{type:'binary'});
      var sheetNames = workbook.SheetNames;
      this.ExcelData = XLSX.utils.sheet_to_json(workbook.Sheets[sheetNames[0]]);
      this.ExcelData.forEach((packings: PackingList) => {
        const packingsForm = new FormGroup({
          checked: new FormControl(false),
          id: new FormControl(packings.id),
          codigoPedido: new FormControl(packings.PER),
          codigo: new FormControl(packings.CODIGO),
          item : new FormControl(this.item) ,
          codigoAgrupador: new FormControl(packings.COD_PERNO_SPLITBOLT),
          denominacion: new FormControl(packings.DENOMINACION),
          lote: new FormControl(packings.LOTE),
          colada: new FormControl(packings.COLADA),
          pesoNetoTonelada: new FormControl(packings.PESO),
          hu: new FormControl(packings.HU),
          numero: new FormControl(packings.CONTENEDOR),
          idExportacion: new FormControl(packings.idExportacion),
          cantidad: new FormControl(packings.UN),
          dimension: new FormControl(packings.DIMENSION),
          idContenedor: new FormControl(packings.idContenedor),
          pesoComex: new FormControl(packings.pesoComex),
          unAlmacen: new FormControl(packings.unAlmacen),
          peso: new FormControl(packings.peso),
          pesoRealTonelada: new FormControl(packings.pesoRealTonelada),
         // unidad: new FormControl(packings.UN),
          centro: new FormControl(packings.centro),
          piezasStock: new FormControl(packings.piezasStock),
          almacen: new FormControl(packings.almacen),
          pesoTeorico: new FormControl(packings.pesoTeorico),
          pesoTeoricoTonelada: new FormControl(packings.pesoTeoricoTonelada),
          cliente: new FormControl(packings.cliente),
          nave: new FormControl(packings.nave),
          puertoDestino: new FormControl(packings.puertoDestino),
          loteCliente: new FormControl(packings.loteCliente),
          colorStick: new FormControl(packings.colorStick),
          medida: new FormControl(packings.medida),
          longitud: new FormControl(packings.longitud),
          grado: new FormControl(packings.grado),
          estado: new FormControl(packings.estado)
        });
        this.packinglist.push(packingsForm);
      });
    }
  }

  guardarPackingList() {
    const formData = this.packinglistForm.getRawValue();
    let idExportacion = formData.idExportacion;
    let contenedores: any[] = [];
    let numeros: any[] = [];
    let contenedoresDuplicados: any[] = [];
    this.contenedores.controls.forEach((contenedorForm: FormGroup) => {
      const contenedorFormData = contenedorForm.value;
      contenedores.push({
        id: contenedorFormData.id,
        numero: contenedorFormData.numero,
        descripcion: contenedorFormData.descripcion,
        dtSAP: contenedorFormData.dtSAP,
        chofer: contenedorFormData.chofer,
        placa: contenedorFormData.placa,
        placaCarrete: contenedorFormData.placaCarrete,
        brevete: contenedorFormData.brevete,
        idExportacion: contenedorFormData.idExportacion,
        horaInicio: contenedorFormData.horaInicio,
        horaFinal: contenedorFormData.horaFinal,
        pesoComex: contenedorFormData.pesoComex,
        estado: 1
      });
      numeros.push(contenedorFormData.numero);
    });
    const tempNumeros = numeros.sort();
    for (let i = 0; i < tempNumeros.length; i++) {
       if (tempNumeros[i + 1] === tempNumeros[i]) {
         contenedoresDuplicados.push(tempNumeros[i]);
       }
    }
    this.contenedoresEliminadas.forEach((contenedor) => {
      contenedores.push({
        id: contenedor.id,
        numero: contenedor.numero,
        descripcion: contenedor.descripcion,
        dtSAP: contenedor.dtSAP,
        chofer: contenedor.chofer,
        placa: contenedor.placa,
        placaCarrete: contenedor.placaCarrete,
        brevete: contenedor.brevete,
        idExportacion: contenedor.idExportacion,
        horaInicio: contenedor.horaInicio,
        horaFinal: contenedor.horaFinal,
        pesoComex: contenedor.pesoComex,
        estado: 0});
    });

    let packings: any[] = [];
    this.packinglist.controls.forEach((packingForm: FormGroup) => {
      const packingFormData = packingForm.value;
      packings.push({
        id: packingFormData.id,
        codigoPedido: packingFormData.codigoPedido,
        codigo: packingFormData.codigo,
        codigoAgrupador: packingFormData.codigoAgrupador,
        denominacion: packingFormData.denominacion,
        lote: packingFormData.lote,
        colada: packingFormData.colada,
        pesoNetoTonelada: packingFormData.pesoNetoTonelada,
        hu: packingFormData.hu,
        cantidad: packingFormData.cantidad,
        dimension: packingFormData.dimension,
        numero: packingFormData.numero,
        idExportacion: packingFormData.idExportacion,
        pesoRealTonelada: packingFormData.pesoRealTonelada,
        pesoTeoricoTonelada: packingFormData.pesoNetoTonelada,
        estado: 1
      });
    });

    this.packingsListEliminadas.forEach((packinglist) => {
      packings.push({
        id: packinglist.id,
        codigoPedido: packinglist.codigoPedido,
        codigo: packinglist.codigo,
        codigoAgrupador: packinglist.codigoAgrupador,
        denominacion: packinglist.denominacion,
        lote: packinglist.lote,
        colada: packinglist.colada,
        pesoNetoTonelada: packinglist.pesoNetoTonelada,
        hu: packinglist.hu,
        cantidad: packinglist.cantidad,
        dimension: packinglist.dimension,
        numero: packinglist.numero,
        pesoComex: packinglist.pesoRealTonelada,
        idExportacion: packinglist.idExportacion,
        pesoRealTonelada: packinglist.pesoRealTonelada,
        pesoTeoricoTonelada: packinglist.pesoNetoTonelada,
        estado: 0});
    });

    const data = {
      idExportacion: idExportacion,
      contenedores: contenedores,
      packings: packings
    }

    let request : any = {};
    request.formulario = data;
    request.usuario = this.settingsService.getUsername();
     if(contenedoresDuplicados.length > 0 ){
      this.messageService.add({
        severity: "warn",
        summary: "Revisar:",
        detail: "Numero de contenedores duplicados"
      });
     } else {
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
  }

  calcularPeso(item) {
    let pesoNetoTotal = 0;
    let pesoContenedor = item.pesoComex;
    let bultos = 0;
    let constante = 0;
    let nuevoPeso = 0;
    let nuevoPesoTotal = 0;
    let ultimoPackingForm: FormGroup;

    this.packinglist.controls.forEach((packingForm: FormGroup)  => {
      const packingFormData = packingForm.value;
      if(packingFormData.numero == item.numero){
        pesoNetoTotal = pesoNetoTotal + packingFormData.pesoNetoTonelada;
        bultos = bultos + 1;
      }
    });

    constante = (pesoContenedor -  Number(pesoNetoTotal)) / bultos;
    this.packinglist.controls.forEach((packingForm: FormGroup)  => {
      const packingFormData = packingForm.value;
      if(packingFormData.numero == item.numero){
        nuevoPeso = Math.abs(Number(Number(packingFormData.pesoNetoTonelada + constante).toFixed(3)));
        nuevoPesoTotal += nuevoPeso;
        packingForm.controls['pesoRealTonelada'].patchValue(nuevoPeso);
        ultimoPackingForm = packingForm;
      }
    });

    const delta = pesoContenedor - nuevoPesoTotal;
    if(delta != 0) {
      const pesoReal = Number(Number(nuevoPeso + delta).toFixed(3));
      ultimoPackingForm.controls['pesoRealTonelada'].patchValue(pesoReal);
    }
  }

   actualizarNumero(contenedor){
   // alert('evento activado:'+contenedor.numero);
    this.packinglist.controls.forEach((packingForm: FormGroup)  => {
      const packingFormData = packingForm.value;
      if((packingFormData.idContenedor == contenedor.id) && contenedor.id != 0){
        packingForm.controls['numero'].patchValue(contenedor.numero);
      }
    });
  }

  descargarContenedor(): void {
    if(this.contenedores.length <= 0) {
      return;
    }
    const name = 'Contenedores_' + Utils.getCurrentTimeId() + '.xlsx';
    let exportData = [];
    let contenedores: any[] = [];
    this.contenedores.controls.forEach((contenedorForm: FormGroup) => {
      const contenedorFormData = contenedorForm.value;

      exportData.push({
        numero: contenedorFormData.numero,
        descripcion: contenedorFormData.descripcion,
        dtSAP: contenedorFormData.dtSAP,
        chofer: contenedorFormData.chofer,
        placa: contenedorFormData.placa,
        placaCarrete: contenedorFormData.placaCarrete,
        brevete: contenedorFormData.brevete,
        horaInicio: contenedorFormData.horaInicio,
        horaFinal: contenedorFormData.horaFinal,
        pesoComex: contenedorFormData.pesoComex,

      });
    });
    const columns: any = {
      numero: 'Número',
      descripcion: 'Contenedor',
      dtSAP: 'Tipo Transporte',
      codigoCliente: 'DT SAP',
      chofer: 'Chofer',
      placa: 'Placa',
      placaCarrete: 'Placa carrete',
      brevete: 'Brevete',
      horaInicio: 'Hora Inicial',
      horaFinal: 'Hora Fin',
      pesoComex: 'Peso Real'
    };
    exportData.unshift(columns);

    const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(exportData, { skipHeader: true });
    const book: XLSX.WorkBook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(book, worksheet, 'Documentos Maritimos');

    XLSX.writeFile(book, name);
  }

  descargarPackingList(): void {
    if(this.contenedores.length <= 0) {
      return;
    }
    const name = 'PackingList_' + Utils.getCurrentTimeId() + '.xlsx';
    let exportData = [];
    let packings: any[] = [];
    this.packinglist.controls.forEach((packingForm: FormGroup) => {
      const packingFormData = packingForm.value;

      exportData.push({
        codigo: packingFormData.codigo,
        codigoAgrupador: packingFormData.codigoAgrupador,
        denominacion: packingFormData.denominacion,
        lote: packingFormData.lote,
        colada: packingFormData.colada,
        pesoNetoTonelada: packingFormData.pesoNetoTonelada,
        hu: packingFormData.hu,
        cantidad: packingFormData.cantidad,
        dimension: packingFormData.dimension,
        numero: packingFormData.numero,
        pesoRealTonelada: packingFormData.pesoRealTonelada
      });
    });
    const columns: any = {
      codigo: 'Código',
      codigoAgrupador: 'Cod. Perno o Splitbolt',
      denominacion: 'Denominación',
      lote: 'Lote',
      colada: 'Colada',
      pesoNetoTonelada: 'Peso',
      hu: 'HU',
      cantidad: 'Cantidad',
      dimension: 'Dimensión',
      numero: 'Contenedor',
      pesoRealTonelada: 'Peso Real'
    };
    exportData.unshift(columns);

    const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(exportData, { skipHeader: true });
    const book: XLSX.WorkBook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(book, worksheet, 'Documentos Maritimos');

    XLSX.writeFile(book, name);
  }

  generarPdf() {
    this.settingsService.mostrarSpinner();
    this.service.generarPdf(this.dataDocumento.id, false, true).subscribe({
      next: (data) => {
        this.settingsService.ocultarSpinner();
        if(data != null && data.size > 0) {
          const tempBlob = new Blob([data], { type: 'application/pdf' });
          const fileReader = new FileReader();
          fileReader.onload = () => {
            this.pdfSrc = new Uint8Array(fileReader.result as ArrayBuffer);
          };
          fileReader.readAsArrayBuffer(tempBlob);
          this.modalService.open(this.modalVerPdf, {size: 'lg', centered: true});
        } else {
          this.messageService.add({
            severity: "warn",
            summary: "Alerta",
            detail: 'No hay data para visualizar',
          });
        }
      },
      error: (err) => {
        this.settingsService.ocultarSpinner();
        this.messageService.add({
          severity: "error",
          summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
          detail: JSON.stringify(err),
        });
      }
    });
  }

  descargarPdf() {
    this.settingsService.mostrarSpinner();
    this.service.generarPdf(this.dataDocumento.id, false, true).subscribe({
      next: (data) => {
        this.settingsService.ocultarSpinner();
        let url = window.URL.createObjectURL(data);
        let a = document.createElement('a');
        document.body.appendChild(a);
        a.setAttribute('style', 'display: none');
        a.href = url;
        a.download = "PACKINGLIST_" + this.dataDocumento.codigoPacking + '.pdf';
        a.click();
        window.URL.revokeObjectURL(url);
        a.remove();
      },
      error: (err) => {
        this.settingsService.ocultarSpinner();
        this.messageService.add({
          severity: "error",
          summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
          detail: JSON.stringify(err),
        });
      }
    });
  }

}
