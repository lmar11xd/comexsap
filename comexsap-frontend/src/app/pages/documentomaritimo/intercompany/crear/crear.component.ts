import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { Destinatario } from 'src/app/pages/configuracion/to/destinatario';
import { Parametro } from 'src/app/pages/configuracion/to/parametro';
import { Ruta } from 'src/app/pages/configuracion/to/ruta';
import { Constantes } from 'src/app/utils/constantes';
import { Router } from '@angular/router';
import { Breadcrumb2Service } from 'src/app/shared/breadcrumb2.service';
import { MenuItem, MessageService } from 'primeng/api';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { ConfiguracionService } from 'src/app/pages/configuracion/configuracion.sevice';
import { DocumentoMaritimoService } from '../../documentomaritimo.service';
import { CREAR_DOCUMENTOMARITIMO_INTERCOMPANY, LISTAR_DOCUMENTOMARITIMO_INTERCOMPANY } from 'src/app/shared/breadcrumb/breadcrumb';
import { ConfiguracionUtil } from 'src/app/pages/configuracion/configuracion.util';
import { Utils } from 'src/app/utils/utils';
import { PedidoPosicion } from 'src/app/pages/configuracion/to/pedidoposicion';
import { InfoCliente } from '../../to/infocliente';
import { OrdenInterna } from '../../to/ordeninterna';
import { PedidoIntercompany } from '../../to/pedidointercompany';
import { Puerto } from 'src/app/pages/configuracion/to/puerto';
import { Etiqueta } from 'src/app/pages/configuracion/to/etiqueta';
import { POSICION_ETIQUETA } from 'src/app/utils/posicionetiqueta';
import { DocumentoMaritimo } from '../../to/documentomaritimo';

@Component({
  selector: 'app-crear',
  templateUrl: './crear.component.html',
  styleUrls: ['./crear.component.scss']
})
export class CrearIntercompanyComponent implements OnInit {

  @ViewChild('modalGuardadoExitoso') modalGuardadoExitoso: ElementRef;

  active = 1;
  activeDatos = 1;
  activeContacto = 1;
  activeImpresion = 1;

  documentoForm: FormGroup;

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

  mensajeValidacion: string = "";

  constructor(
    private router: Router,
    private breadcrumb2Service: Breadcrumb2Service,
    private messageService: MessageService,
    private modalService: NgbModal,
    private settingsService: SettingsService,
    private configuracionService: ConfiguracionService,
    private service: DocumentoMaritimoService
  ) { }

  ngOnInit(): void {
    this.inicializarBreadcrumb();
    this.crearFormulario();
    this.inicializar();
  }

  inicializarBreadcrumb(){
    let BREADCRUMBS: MenuItem[] = [];
    BREADCRUMBS = BREADCRUMBS.concat(LISTAR_DOCUMENTOMARITIMO_INTERCOMPANY);
    BREADCRUMBS = BREADCRUMBS.concat(CREAR_DOCUMENTOMARITIMO_INTERCOMPANY);
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
      this.configuracionService.listarParametroxDominio(Constantes.P_D015).toPromise(),//Agente Aduana
      this.configuracionService.listarEtiquetas("").toPromise()
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
        this.agentesAduana = Parametro.toArray(data[8]);
        this.etiquetas = data[9];

        if(this.agentesAduana != null && this.agentesAduana.length > 0) {
          this.defaultAgenteAduana = this.agentesAduana[0].descripcion;
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

      posiciones: new FormArray([]),
      facturas: new FormArray([]),
      etiquetasArriba: new FormArray([]),
      etiquetasAbajo: new FormArray([])
    });
  }

  inicializarFormulario() {
    //Datos Documento Marítimo
    this.documentoForm.controls['codigo'].patchValue('', {onlySelf: true});
    this.documentoForm.controls['idCliente'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['codigoCliente'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['cliente'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['idDestinatario'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['codigoDestinatario'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['destinatario'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['idPuertoOrigen'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['puertoOrigen'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['idPuertoDestino'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['puertoDestino'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['idIncoterm'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['idIncotermComercial'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['idTipoDespacho'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['idCondicionPago'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['idMoneda'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['fechaListaPrecio'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['idListaPrecio'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['idRuta'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['agenteAduana'].patchValue(this.defaultAgenteAduana, {onlySelf: true});
    this.documentoForm.controls['shipper'].patchValue(Constantes.P_SHIPPER, {onlySelf: true});
    this.documentoForm.controls['direccionShipper'].patchValue(Constantes.P_DIRECCION_SHIPPER, {onlySelf: true});
    this.documentoForm.controls['consignatario'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['direccionConsignatario'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['notificante'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['direccionNotificante'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['glosa'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['descripcionTotal'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['ordenInterna'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['agenteNaviera'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['agenteCarga'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['nave'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['etaOrigen'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['etaDestino'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['fechaBlProgramado'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['fechaBlReal'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['fechaCarguio'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['fechaMaximaIngreso'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['fechaEnvioDocumento'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['importeFlete'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['importeSeguro'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['fleteTm'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['booking'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['billOfLanding'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['tipoEnvio'].patchValue(Constantes.P_ENVIO_FISICO, {onlySelf: true});
    this.documentoForm.controls['guiaDHL'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['dam'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['idRegimen'].patchValue(Constantes.P_REGIMEN_DRAWBACK, {onlySelf: true});
    this.documentoForm.controls['fechaDamDrawback'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['fechaManifiestoAduana'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['fechaDam'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['fechaDamRegularizacion'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['fechaDam41'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['fechaRecepcionDam41'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['armador'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['emitidoEn'].patchValue(Constantes.P_EMITIDO_EN, {onlySelf: true});
    this.documentoForm.controls['etiquetaEmpaque'].patchValue(Constantes.P_EMPAQUE, {onlySelf: true});
    this.documentoForm.controls['empaque'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['etiquetaMarca'].patchValue(Constantes.P_MARCA, {onlySelf: true});
    this.documentoForm.controls['marca'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['etiquetaUnidadProductiva'].patchValue(Constantes.P_UNIDAD_PRODUCTIVA, {onlySelf: true});
    this.documentoForm.controls['unidadProductiva'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['etiquetaPrecioUnitario'].patchValue(Constantes.P_PRECIO_UNITARIO, {onlySelf: true});
    this.documentoForm.controls['precioUnitario'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['etiquetaFacturacion'].patchValue(Constantes.P_FACTURACION, {onlySelf: true});
    this.documentoForm.controls['facturacion'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['etiquetaFormaPago'].patchValue(Constantes.P_FORMA_PAGO, {onlySelf: true});
    this.documentoForm.controls['formaPago'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['fechaFactura'].patchValue(null, {onlySelf: true});

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
        referencia: new FormControl(posicion.codigoPedido)
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
      id: 0,
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
        id: 0,
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

  modificarPedidoSap() {
    const formData = this.documentoForm.getRawValue();

    let codigoCondicionPago = '', codigoListaPrecio = '', codigoMoneda = '', codigoIncoterm = '';

    if(this.pagos && formData.idCondicionPago) {
      const condicionPago = this.pagos.find(o => o.id == formData.idCondicionPago);
      codigoCondicionPago = condicionPago ? condicionPago.codigo: "";
    }

    if(this.listaPrecios && formData.idListaPrecio) {
      const listaPrecio = this.listaPrecios.find(o => o.id == formData.idListaPrecio);
      codigoListaPrecio = listaPrecio ? listaPrecio.codigo: "";
    }

    if(this.monedas && formData.idMoneda) {
      const moneda = this.monedas.find(o => o.id == formData.idMoneda);
      codigoMoneda = moneda ? moneda.codigo: "";
    }

    if(this.incoterms && formData.idIncoterm) {
      const incoterm = this.incoterms.find(o => o.id == formData.idIncoterm);
      codigoIncoterm = incoterm ? incoterm.codigo: "";
    }

    let dataDocumento: DocumentoMaritimo = new DocumentoMaritimo();
    dataDocumento.codigo = 'DOCEXP0000';
    dataDocumento.codigoSapCliente = formData.codigoCliente;
    dataDocumento.codigoCondicionPago = codigoCondicionPago;
    dataDocumento.codigoListaPrecio = codigoListaPrecio;
    dataDocumento.fechaListaPrecio = formData.fechaListaPrecio;
    dataDocumento.codigoIncoterm = codigoIncoterm;
    dataDocumento.codigoMoneda = codigoMoneda;
    dataDocumento.glosa = formData.glosa;

    const data = ConfiguracionUtil.obtenerDatosPedidoSap(true, dataDocumento, this.documentoForm, this.posiciones, [], this.selectedRuta);
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

        setTimeout(() => this.irAlListado(), 1500);
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

  fleteOblitario(): boolean {
    const formData: any = this.documentoForm.getRawValue();
    return formData.idIncotermComercial == Constantes.P_ID_INCOTERM_CFR || formData.idIncotermComercial == Constantes.P_ID_INCOTERM_CIF;
  }

  seguroOblitario(): boolean {
    const formData: any = this.documentoForm.getRawValue();
    return formData.idIncotermComercial == Constantes.P_ID_INCOTERM_CIF;
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
    this.etiquetasArriba.removeAt(index);
  }

  eliminarEtiquetaAbajo(index: number) {
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
