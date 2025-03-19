import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormControl, FormArray, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MessageService, MenuItem } from 'primeng/api';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { ConfiguracionService } from 'src/app/pages/configuracion/configuracion.sevice';
import { Parametro } from 'src/app/pages/configuracion/to/parametro';
import { DocumentoMaritimo } from 'src/app/pages/documentomaritimo/to/documentomaritimo';
import { DocumentoMaritimoPosicion } from 'src/app/pages/documentomaritimo/to/documentomaritimoposicion';
import { LISTAR_PACKINGLIST_CARGASUELTA, EDITAR_PACKINGLIST_CARGASUELTA } from 'src/app/shared/breadcrumb/breadcrumb';
import { Breadcrumb2Service } from 'src/app/shared/breadcrumb2.service';
import { Constantes } from 'src/app/utils/constantes';
import { Utils } from 'src/app/utils/utils';
import { PackingListService } from '../../packinglist.service';
import { PackingCargaSuelta } from '../../to/packing-carga-suelta';
import * as XLSX from 'xlsx';

@Component({
  selector: 'app-editar',
  templateUrl: './editar.component.html',
  styleUrls: ['./editar.component.scss']
})
export class EditarPackingListCargaSueltaComponent implements OnInit {
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
  dataPackingList: PackingCargaSuelta[] = [];
  packingListEliminadas: PackingCargaSuelta[] = [];
  item : number = 0 ;
  itemPacking : number = 0 ;
  packinglistForm: FormGroup;

  pagos: Parametro[] = [];
  incoterms: Parametro[] = [];
  estados: Parametro[] = [];
  monedas: Parametro[] = [];
  despachos: Parametro[] = [];
  listaPrecios: Parametro[] = [];
  agentesNaviera: Parametro[] = [];
  agentesCarga: Parametro[] = [];
  regimenes: Parametro[] = [];

  versionIngles: boolean = false;
  original: boolean = true;

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
    EDITAR_PACKINGLIST_CARGASUELTA[0].url = EDITAR_PACKINGLIST_CARGASUELTA[0].url.replace(':id', id);
    let BREADCRUMBS: MenuItem[] = [];
    BREADCRUMBS = BREADCRUMBS.concat(LISTAR_PACKINGLIST_CARGASUELTA);
    BREADCRUMBS = BREADCRUMBS.concat(EDITAR_PACKINGLIST_CARGASUELTA);
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
      this.packingListService.obtenerPackingListCargaSuelta(this.idExportacion).toPromise()
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

        this.dataDocumento = data[9].exportacion;
        if(this.dataDocumento.intercompany == 1) {
          this.dataPosiciones = data[9].posicionesIntercompany;
        } else {
          this.dataPosiciones = data[9].posiciones;
        }

        this.dataPackingList = data[9].packingList;

        if(this.dataPosiciones != null) {
          this.dataPosiciones.forEach((posicion: DocumentoMaritimoPosicion) => {
            let componentes: FormGroup[] = [];
            if(posicion.componentes != null) {
              posicion.componentes.forEach((componente: any) => {
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
                  importe: new FormControl(componente.importe)
                }));
              });
            }

            const posicionForm = new FormGroup({
              id: new FormControl(posicion.id),
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
              pesoToneladas: new FormControl(posicion.pesoTonelada),
              fechaDisponibilidad: new FormControl(Utils.dateTimeToStringDate(posicion.fechaDisponibilidad)),
              precioUnitario: new FormControl(posicion.precioUnitario),
              importe: new FormControl(posicion.importe),
              esPadre: new FormControl(componentes.length > 0),
              componentes: new FormArray(componentes)
            });
            this.posiciones.push(posicionForm);
          });
        }

        if(this.dataPackingList != null) {
          this.dataPackingList.forEach((packing: PackingCargaSuelta) => {
            this.itemPacking =this.itemPacking + 1;
            const packingsForm = new FormGroup({
              checked: new FormControl(false),
              id: new FormControl(packing.id),
              codigo: new FormControl(packing.codigo),
              material: new FormControl(packing.material),
              pesoTeorico: new FormControl(packing.pesoTeorico),
              pesoNeto: new FormControl(packing.pesoNeto),
              pesoBruto: new FormControl(packing.pesoBruto),
              paquetes: new FormControl(packing.paquetes),
              piezas: new FormControl(packing.piezas),
              color: new FormControl(packing.color)
            });
            this.packinglist.push(packingsForm);
          });
        }

        this.inicializarFormulario();
      },
      (err) => {
        this.settingsService.ocultarSpinner();
        this.messageService.add({
          severity: "error",
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

  get packinglist() {
    return this.packinglistForm.controls["packinglist"] as FormArray;
  }

  quitarPackingList() {
    const packings = this.packinglist.value.filter(packing => packing.checked);
    if(packings.length > 0) {
      packings.forEach(element => {
        const index = this.packinglist.value.findIndex(item => item.codigo == element.codigo && item.checked == true);
        if(element.id != 0) {
          this.packingListEliminadas.push(element);
        }
        this.packinglist.removeAt(index);
      });
    } else {
      this.messageService.add({
        severity: "warn",
        summary: "Alerta",
        detail: "Por favor, seleccione al menos un registro para eliminarlo"
      });
    }
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

  cargarPackingList(event: any) {
    let file = event.target.files[0];
    let fileReader = new FileReader();
    fileReader.readAsBinaryString(file);
    fileReader.onload = (e) => {
      let pesoTeorico = 0, pesoNeto = 0, pesoBruto = 0;
      let color = '';
      var workbook = XLSX.read(fileReader.result,{type:'binary'});
      var sheetNames = workbook.SheetNames;

      const excelData = XLSX.utils.sheet_to_json(workbook.Sheets[sheetNames[0]]);
      excelData.forEach((row: any) => {
        if(row.CODIGO != null && row.CODIGO != '') {
          pesoTeorico = parseFloat(Utils.round(row.PESO_TEORICO, 3));
          pesoNeto = parseFloat(Utils.round(row.PESO_NETO, 3));
          pesoBruto = parseFloat(Utils.round(row.PESO_BRUTO, 3));

          color = row.COLOR;

          if(pesoNeto == 0 || pesoBruto == 0) return;

          if(color == null || color == '') {
            color = 'Sin color';
          }

          const packingForm = new FormGroup({
            checked: new FormControl(false),
            id: new FormControl(0),
            codigo: new FormControl(row.CODIGO),
            material: new FormControl(row.MATERIAL),
            pesoTeorico: new FormControl(pesoTeorico),
            pesoNeto: new FormControl(pesoNeto),
            pesoBruto: new FormControl(pesoBruto),
            paquetes: new FormControl(row.PAQUETES),
            piezas: new FormControl(row.PIEZAS),
            color: new FormControl(color)
          });

          this.packinglist.push(packingForm);
        }
      });
    }
  }

  existeCodigo(codigo: string) {
    let existe = false;
    this.packinglist.value.forEach(element => {
      console.log(element);
      if(element.codigo == codigo) existe = true;
    });
    return existe;
  }

  guardarPackingList() {
    const formData = this.packinglistForm.getRawValue();
    let idExportacion = formData.idExportacion;

    let packing: any[] = [];
    this.packinglist.controls.forEach((packingForm: FormGroup) => {
      const packingFormData = packingForm.value;
      packing.push({
        id: packingFormData.id,
        codigo: packingFormData.codigo,
        material: packingFormData.material,
        pesoTeorico: packingFormData.pesoTeorico,
        pesoNeto: packingFormData.pesoNeto,
        pesoBruto: packingFormData.pesoBruto,
        paquetes: packingFormData.paquetes,
        piezas: packingFormData.piezas,
        color: packingFormData.color,
        estado: 1
      });
    });

    this.packingListEliminadas.forEach((item) => {
      packing.push({
        id: item.id,
        estado: 0
      });
    });

    const data = {
      idExportacion: idExportacion,
      packing: packing
    }

    let request : any = {};
    request.formulario = data;
    request.usuario = this.settingsService.getUsername();

    this.settingsService.mostrarSpinner();
    Promise.all([
      this.service.registrarPackingListCargaSuelta(request).toPromise()
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

  descargarPackingList(): void {
    const name = 'PackingList_CargaSuelta_' + Utils.getCurrentTimeId() + '.xlsx';
    let exportData = [];
    this.packinglist.controls.forEach((packingForm: FormGroup) => {
      const packingFormData = packingForm.value;
      exportData.push({
        codigo: packingFormData.codigo,
        material: packingFormData.material,
        pesoTeorico: packingFormData.pesoTeorico,
        pesoNeto: packingFormData.pesoNeto,
        pesoBruto: packingFormData.pesoBruto,
        paquetes: packingFormData.paquetes,
        piezas: packingFormData.piezas,
        color: packingFormData.color
      });
    });

    const columns: any = {
      codigo: 'Código',
      material: 'Material',
      pesoTeorico: 'Peso Teórico',
      pesoNeto: 'Peso Neto',
      pesoBruto: 'Peso Bruto',
      paquetes: 'Nro. Paquetes',
      piezas: 'Nro. Piezas',
      color: 'Color'
    };
    exportData.unshift(columns);

    const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(exportData, { skipHeader: true });
    const book: XLSX.WorkBook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(book, worksheet, 'PackingList');

    XLSX.writeFile(book, name);
  }

  generarPdf(versionIngles: boolean, original: boolean) {
    this.versionIngles = versionIngles;
    this.original = original;
    this.settingsService.mostrarSpinner();
    this.service.generarPdf(this.dataDocumento.id, versionIngles, original).subscribe({
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
    this.service.generarPdf(this.dataDocumento.id, this.versionIngles, this.original).subscribe({
      next: (data) => {
        this.settingsService.ocultarSpinner();
        let url = window.URL.createObjectURL(data);
        let a = document.createElement('a');
        document.body.appendChild(a);
        a.setAttribute('style', 'display: none');
        a.href = url;
        a.download = "PACKINGLIST_CARGASUELTA_" + this.dataDocumento.codigoPacking + '.pdf';
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
