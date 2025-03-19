import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { DocumentoMaritimoService } from '../../documentomaritimo.service';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MenuItem, MessageService } from 'primeng/api';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { Breadcrumb2Service } from 'src/app/shared/breadcrumb2.service';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { ConfiguracionService } from 'src/app/pages/configuracion/configuracion.sevice';
import { Destinatario } from 'src/app/pages/configuracion/to/destinatario';
import { Parametro } from 'src/app/pages/configuracion/to/parametro';
import { Ruta } from 'src/app/pages/configuracion/to/ruta';
import { CREAR_DOCUMENTOMARITIMO_CONTENEDORES, LISTAR_DOCUMENTOMARITIMO_CONTENEDORES } from 'src/app/shared/breadcrumb/breadcrumb';
import { Utils } from 'src/app/utils/utils';
import { Constantes } from 'src/app/utils/constantes';
import { PedidoFirme } from 'src/app/pages/pedidofirme/to/pedidofirme';
import { PedidoPosicion } from 'src/app/pages/configuracion/to/pedidoposicion';
import { Almacen } from 'src/app/pages/configuracion/to/almacen';
import { Centro } from 'src/app/pages/configuracion/to/centro';
import { MaterialStock } from '../../to/materialstock';
import { ConfiguracionUtil } from 'src/app/pages/configuracion/configuracion.util';
import { UnidadMedida } from 'src/app/pages/configuracion/to/unidadmedida';
import { Material } from 'src/app/pages/configuracion/to/material';
import { InfoCliente } from '../../to/infocliente';
import { ConversionFamilia } from '../../to/conversionfamilia';
import { OrdenInterna } from '../../to/ordeninterna';

@Component({
  selector: 'app-crear',
  templateUrl: './crear.component.html',
  styleUrls: ['./crear.component.scss']
})
export class CrearContenedorComponent implements OnInit {
  @ViewChild('modalGuardadoExitoso') modalGuardadoExitoso: ElementRef;

  active = 1;
  activeDatos = 1;
  activeContacto = 1;
  activeImpresion = 1;

  idDespacho = Constantes.P_ID_CONTENEDOR;

  documentoForm: FormGroup;

  destinatarios: Destinatario[] = [];
  pagos: Parametro[] = [];
  incoterms: Parametro[] = [];
  estados: Parametro[] = [];
  monedas: Parametro[] = [];
  despachos: Parametro[] = [];
  listaPrecios: Parametro[] = [];
  regimenes: Parametro[] = [];

  centros: Centro[] = [];
  partidas: Parametro[] = [];
  agentesAduana: Parametro[] = [];
  defaultAgenteAduana: string = "";

  selectedRuta: Ruta;

  codigoPedidos: string[] = [];

  mensajeValidacion: string = "";

  conversionFamilias: ConversionFamilia[] = [];

  constructor(
    private router: Router,
    private breadcrumb2Service: Breadcrumb2Service,
    private messageService: MessageService,
    private modalService: NgbModal,
    private settingsService: SettingsService,
    private configuracionService: ConfiguracionService,
    private service: DocumentoMaritimoService
  ) {
  }

  ngOnInit(): void {
    this.inicializarBreadcrumb();
    this.crearFormulario();
    this.inicializar();
  }

  inicializarBreadcrumb(){
    let BREADCRUMBS: MenuItem[] = [];
    BREADCRUMBS = BREADCRUMBS.concat(LISTAR_DOCUMENTOMARITIMO_CONTENEDORES);
    BREADCRUMBS = BREADCRUMBS.concat(CREAR_DOCUMENTOMARITIMO_CONTENEDORES);
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

      this.configuracionService.listarCentros("").toPromise(),
      this.configuracionService.listarParametroxDominio(Constantes.P_D023).toPromise(),//Partida Arancelarias
      this.configuracionService.listarParametroxDominio(Constantes.P_D015).toPromise(),//Agente Aduana
      this.configuracionService.listarFamiliasConversion().toPromise()
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
        this.centros = Centro.toArray(data[7]);
        this.partidas = Parametro.toArray(data[8]);
        this.agentesAduana = Parametro.toArray(data[9]);
        this.conversionFamilias = data[10];

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
      cliente: new FormControl(),
      idDestinatario: new FormControl(),
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

      numeroContenedor: new FormControl(),
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
      descripcionTotal: new FormControl(),

      posiciones: new FormArray([]),
      facturas: new FormArray([])
    });
  }

  inicializarFormulario() {
    //Datos Documento Marítimo
    this.documentoForm.controls['codigo'].patchValue('', {onlySelf: true});
    this.documentoForm.controls['idCliente'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['cliente'].patchValue(null, {onlySelf: true});
    this.documentoForm.controls['idDestinatario'].patchValue(null, {onlySelf: true});
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
    this.documentoForm.controls['numeroContenedor'].patchValue(null, {onlySelf: true});
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
    this.documentoForm.controls['descripcionTotal'].patchValue(null, {onlySelf: true});

    this.documentoForm.controls['posiciones'].patchValue([], {onlySelf: true});
    this.documentoForm.controls['facturas'].patchValue([], {onlySelf: true});

    //DESAHIBILITAR CABECERA PEDIDO
    this.documentoForm.controls['codigo'].disable();
    this.documentoForm.controls['idCliente'].disable();
    this.documentoForm.controls['cliente'].disable();
    this.documentoForm.controls['idDestinatario'].disable();
    this.documentoForm.controls['destinatario'].disable();
    this.documentoForm.controls['idPuertoOrigen'].disable();
    this.documentoForm.controls['puertoOrigen'].disable();
    this.documentoForm.controls['idPuertoDestino'].disable();
    this.documentoForm.controls['puertoDestino'].disable();
    this.documentoForm.controls['idIncoterm'].disable();
    this.documentoForm.controls['idIncotermComercial'].disable();
    this.documentoForm.controls['idTipoDespacho'].disable();
    this.documentoForm.controls['idCondicionPago'].disable();
    this.documentoForm.controls['idMoneda'].disable();
    this.documentoForm.controls['fechaListaPrecio'].disable();
    this.documentoForm.controls['idListaPrecio'].disable();
    this.documentoForm.controls['fechaFactura'].disable();

    this.documentoForm.controls['codigo'].setValidators([Validators.required]);
    this.documentoForm.controls['idCliente'].setValidators([Validators.required]);
    this.documentoForm.controls['cliente'].setValidators([Validators.required]);
    this.documentoForm.controls['idDestinatario'].setValidators([Validators.required]);
    this.documentoForm.controls['destinatario'].setValidators([Validators.required]);
    this.documentoForm.controls['idPuertoOrigen'].setValidators([Validators.required]);
    this.documentoForm.controls['puertoOrigen'].setValidators([Validators.required]);
    this.documentoForm.controls['idPuertoDestino'].setValidators([Validators.required]);
    this.documentoForm.controls['puertoDestino'].setValidators([Validators.required]);
    this.documentoForm.controls['idIncoterm'].setValidators([Validators.required]);
    this.documentoForm.controls['idIncotermComercial'].setValidators([Validators.required]);
    this.documentoForm.controls['idTipoDespacho'].setValidators([Validators.required]);
    this.documentoForm.controls['idCondicionPago'].setValidators([Validators.required]);
    this.documentoForm.controls['idMoneda'].setValidators([Validators.required]);
    this.documentoForm.controls['fechaListaPrecio'].setValidators([Validators.required]);
    this.documentoForm.controls['idListaPrecio'].setValidators([Validators.required]);
    this.documentoForm.controls['fechaFactura'].setValidators([Validators.required]);
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

  async seleccionarPosiciones(posiciones: PedidoPosicion[]) {
    let pedidosValidar = [].concat(this.codigoPedidos);
    posiciones.forEach(item => {
      pedidosValidar.push(item.codigoPedido);
    });
    pedidosValidar = pedidosValidar.filter(Utils.quitarDuplicados);
    const pedidos = await this.obtenerPedidos(pedidosValidar) as PedidoFirme[];
    if(pedidos != null && pedidos.length > 0) {
      if(ConfiguracionUtil.validarCabeceraPedidos(pedidos)) {
        const pedido = pedidos[0];
        this.documentoForm.controls['idCliente'].patchValue(pedido.idCliente);
        this.documentoForm.controls['cliente'].patchValue(pedido.codigoCliente + " - " + pedido.nombreCliente);
        this.documentoForm.controls['idDestinatario'].patchValue(pedido.idDestinatario);
        this.documentoForm.controls['destinatario'].patchValue(pedido.codigoDestinatario + " - " + pedido.nombreDestinatario);
        this.documentoForm.controls['idPuertoOrigen'].patchValue(pedido.idPuertoOrigen);
        this.documentoForm.controls['puertoOrigen'].patchValue(pedido.codigoPuertoOrigen + " - " + pedido.nombrePuertoOrigen);
        this.documentoForm.controls['idPuertoDestino'].patchValue(pedido.idPuertoDestino);
        this.documentoForm.controls['puertoDestino'].patchValue(pedido.codigoPuertoDestino + " - " + pedido.nombrePuertoDestino);
        this.documentoForm.controls['idIncoterm'].patchValue(pedido.idIncoterm);
        this.documentoForm.controls['idIncotermComercial'].patchValue(pedido.idIncotermComercial);
        this.documentoForm.controls['idTipoDespacho'].patchValue(pedido.idDespacho);
        this.documentoForm.controls['idCondicionPago'].patchValue(pedido.idCondicionPago);
        this.documentoForm.controls['idMoneda'].patchValue(pedido.idMoneda);
        this.documentoForm.controls['fechaListaPrecio'].patchValue(Utils.stringToDate(pedido.fechaListaPrecio));
        this.documentoForm.controls['idListaPrecio'].patchValue(pedido.idListaPrecio);

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

        //Obtener Orden Interna
        const requestOrdenInterna = {
          idDespacho: pedido.idDespacho,
          idPuertoDestino: pedido.idPuertoDestino,
          codigoCliente: pedido.codigoCliente,
          codigoDestinatario: pedido.codigoDestinatario
        };

        const ordenInterna = await this.obtenerOrdenInternaxFiltro(requestOrdenInterna) as OrdenInterna;
        if(ordenInterna != null) {
          this.documentoForm.controls['ordenInterna'].patchValue(ordenInterna.codigo);
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
      this.service.listarPedidoFirme(request).subscribe((data) => {
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

  seleccionarRuta(ruta: Ruta) {
    this.documentoForm.controls['idRuta'].patchValue(ruta ? ruta.id : null);
  }

  existePosicion(id: number) {
    let existe = false;
    this.posiciones.controls.forEach((posicionForm: FormGroup) => {
      if(posicionForm.controls['id'].value == id) {
        existe = true;
        return;
      }
    });
    return existe;
  }

  agregarPosicion(posicion: PedidoPosicion) {
    if(!this.existePosicion(posicion.id)) {
      let componentes: FormGroup[] = [];
      let textoComponente;
      if(posicion.componentes != null) {
        textoComponente = '';
        posicion.componentes.forEach((componente: any) => {
          const materialComp = new Material(componente.codigoSap, componente.descripcionProducto, componente.codigoSapUnidadMedida, componente.codigoSapUnidadMedida, 0, 0);
          textoComponente += '\n' + Utils.formatearCantidad(componente.cantidadVenta) + ' ' + componente.codigoSapUnidadMedidaVenta + ' ' + componente.descripcionProducto;
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
            listaUnidadesMedida: new FormControl([]),
            selectedUnidadMedida: new FormControl([]),
            precioMaterialSap: new FormControl()
          }));
        });
      }

      const material = new Material(posicion.codigoSap, posicion.descripcionProducto, posicion.codigoSapUnidadMedida, posicion.codigoSapUnidadMedida, 0, 0);
      textoComponente = Utils.formatearCantidad(posicion.cantidadVenta) + ' ' + posicion.codigoSapUnidadMedidaVenta + ' ' + posicion.descripcionProducto + textoComponente;
      const posicionForm = new FormGroup({
        checked: new FormControl(false),
        id: new FormControl(0),
        idPosicion: new FormControl(posicion.id),
        codigoPedido: new FormControl(posicion.codigoPedido),
        codigoMaterial: new FormControl(posicion.codigoSap),
        descripcion: new FormControl(posicion.descripcionProducto),
        descripcionMaterial: new FormControl(posicion.descripcionProducto),
        cantidadSaldo: new FormControl(0),
        cantidadSaldoInicial: new FormControl(posicion.cantidadSaldo),
        cantidad: new FormControl(),
        cantidadVenta: new FormControl(posicion.cantidadVenta),
        cantidadConversion: new FormControl(),
        idUnidadMedida: new FormControl(),
        idUnidadMedidaVenta: new FormControl(posicion.idUnidadMedidaVenta),
        idUnidadMedidaConversion: new FormControl(),
        unidadMedidaVenta: new FormControl(posicion.codigoSapUnidadMedidaVenta),
        selectedMaterial: new FormControl(material),
        listaUnidadesMedida: new FormControl([]),
        selectedUnidadMedida: new FormControl(),
        precioMaterialSap: new FormControl(),
        centros: new FormControl(this.centros),
        idCentroSuministro: new FormControl(),
        almacenes: new FormControl([]),
        idAlmacen: new FormControl(),
        stock: new FormControl(),
        partidas: new FormControl(this.partidas),
        idPartidaArancelaria: new FormControl(),
        pesoToneladas: new FormControl(posicion.pesoTonelada),
        fechaDisponibilidad: new FormControl(Utils.dateTimeToStringDate(posicion.fechaDisponibilidad)),
        precioUnitario: new FormControl(posicion.precioUnitario),
        precioUnitarioConversion: new FormControl(),
        importe: new FormControl(posicion.importe),
        folioFactura: new FormControl(),
        referencia: new FormControl(posicion.codigoPedido),
        componenteTexto:  new FormControl(textoComponente),
        esPadre: new FormControl(componentes.length > 0),
        codigoLinea: new FormControl(posicion.codigoLinea),
        componentes: new FormArray(componentes)
      });
      this.posiciones.push(posicionForm);
      this.buscarUnidadMedidaVenta(Utils.toCodeMaterial(posicion.codigoSap), posicionForm);
    }
  }

  quitarPosiciones() {
    const posiciones = this.documentoForm.value.posiciones.filter(posicion => posicion.checked);
    if(posiciones.length > 0) {
      this.documentoForm.controls['selectAllPosiciones'].patchValue(false);
      posiciones.forEach(element => {
        const index = this.documentoForm.value.posiciones.findIndex(posicion => posicion.idPosicion == element.idPosicion);
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

  buscarUnidadMedidaVenta(codigoMaterial: string, posicionForm: FormGroup) {
    const formulario = this.documentoForm.getRawValue();
    const posicionData = posicionForm.getRawValue();
    const idListaPrecio = formulario.idListaPrecio;
    const fechaListaPrecio = formulario.fechaListaPrecio;

    const listaPrecio = ConfiguracionUtil.obtenerParametroxId(this.listaPrecios, idListaPrecio);

    const requestPrecio = {
      codigoMaterial: codigoMaterial,
      codigoListaPrecio: listaPrecio ? listaPrecio.codigo : "",
      fechaListaPrecio: fechaListaPrecio
    };

    this.settingsService.mostrarSpinner();
    Promise.all([
      this.configuracionService.listarUnidadMedida(codigoMaterial).toPromise(),
      this.configuracionService.obtenerUnidadMedidaxProducto(codigoMaterial, posicionData.unidadMedidaVenta).toPromise(),
      this.configuracionService.obtenerPrecioMaterial(requestPrecio).toPromise()
    ]).then(
      (data: any[]) => {
        this.settingsService.ocultarSpinner();
        const listaUnidades = UnidadMedida.toArray(data[0]);
        const unidadMedida = UnidadMedida.toObject(data[1]);
        posicionForm.controls['listaUnidadesMedida'].patchValue(listaUnidades);
        posicionForm.controls['selectedUnidadMedida'].patchValue(unidadMedida);
        posicionForm.controls['precioMaterialSap'].patchValue(data[2]);
        ConfiguracionUtil.convertirMaterial(posicionForm, this.conversionFamilias);
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

  cambiarCantidadVenta(posicionForm: FormGroup) {
    ConfiguracionUtil.actualizarPesoToneladas(posicionForm);
    ConfiguracionUtil.actualizarImporte(posicionForm);
    ConfiguracionUtil.actualizarCantidadSaldo(posicionForm);
    ConfiguracionUtil.actualizarCantidadConversion(posicionForm, this.conversionFamilias);
    ConfiguracionUtil.actualizarComponenteTexto(posicionForm);
  }

  seleccionarUnidadMedidaVenta(unidadMedida: UnidadMedida, posicionForm: FormGroup) {
    posicionForm.controls['selectedUnidadMedida'].patchValue(unidadMedida);
    ConfiguracionUtil.actualizarPesoToneladas(posicionForm);
    ConfiguracionUtil.actualizarPrecioUnitarioSap(posicionForm, true);
    ConfiguracionUtil.actualizarImporte(posicionForm);
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
      || formData.idPuertoDestino == null
      || formData.idIncoterm == null
      || formData.idIncotermComercial ==  null
      || Utils.isNullOrEmpty(formData.fechaListaPrecio)
      || formData.idListaPrecio ==  null
      || formData.idTipoDespacho ==  null
      || formData.idMoneda ==  null
      || formData.idCondicionPago ==  null
      || formData.idRuta ==  null
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

    isValido = this.validarFormularioPosiciones();
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
        || posicionData.idCentroSuministro == null
        || posicionData.idCentroSuministro <= 0
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
      idCliente: formData.idCliente,
      idDestinatario: formData.idDestinatario,
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
      numeroContenedor: formData.numeroContenedor,
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
        idPosicion: posicionFormData.idPosicion,
        descripcionComercialProducto: posicionFormData.descripcionMaterial,
        idMoneda: formData.idMoneda,
        idUnidadMedida: posicionFormData.idUnidadMedida,
        idUnidadMedidaVenta: posicionFormData.idUnidadMedidaVenta,
        idUnidadMedidaConv: posicionFormData.idUnidadMedidaConversion,
        unidadMedidaVenta: posicionFormData.unidadMedidaVenta,
        cantidad: posicionFormData.cantidad,
        cantidadVenta: posicionFormData.cantidadVenta,
        cantidadConversion: posicionFormData.cantidadConversion,
        idAlmacen: posicionFormData.idAlmacen,
        pesoTonelada: posicionFormData.pesoToneladas,
        fechaDisponibilidad: posicionFormData.fechaDisponibilidad,
        precioUnitario: posicionFormData.precioUnitario,
        precioUnitarioConv: posicionFormData.precioUnitarioConversion,
        importe: posicionFormData.importe,
        idPartidaArancelaria: posicionFormData.idPartidaArancelaria,
        folio: posicionFormData.folioFactura,
        referencia: posicionFormData.referencia,
        componenteTexto: posicionFormData.componenteTexto,
        estado: 1
      });
    });

    const data = {
      exportacion: cabecera,
      posiciones: posiciones,
      facturas: []
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

  irAlListado() {
    this.modalService.dismissAll();
    this.router.navigate(['/exportaciones/documentomaritimo/contenedores/listar']);
  }

  seleccionarCentroSuministro(centro: Centro, posicionForm: FormGroup) {
    this.buscarAlmacen(centro.id, posicionForm);
  }

  buscarAlmacen(idCentro: number, posicionForm: FormGroup) {
    this.settingsService.mostrarSpinner();
    Promise.all([
      this.configuracionService.listarAlmacenes("" + idCentro).toPromise(),
    ]).then(
      (data: any[]) => {
        this.settingsService.ocultarSpinner();
        posicionForm.controls['almacenes'].patchValue(Almacen.toArray(data[0]));
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

  seleccionarAlmacen(almacen: Almacen, posicionForm: FormGroup) {
    this.actualizarStockPorPosicion(almacen, posicionForm);
  }

  validarDatosStock() {
    let isValido = true;
    this.posiciones.controls.forEach((posicionForm : FormGroup) => {
      let formData = posicionForm.value;
      if(formData.idCentroSuministro == null || formData.idCentroSuministro == 0
        || formData.idAlmacen == null || formData.idAlmacen == 0) {
        isValido = false;
      }
    });
    return isValido;
  }

  actualizarStock() {
    if(this.posiciones.length == 0) return;

    if(!this.validarDatosStock()) {
      this.messageService.add({
        severity: "warn",
        summary: 'Validación',
        detail: "Posición no tiene seleccionado centro y/o almacén",
      });
      return;
    }

    let detalles = [];
    let item = 0;

    this.posiciones.controls.forEach((posicionForm : FormGroup) => {
      item += 10;
      const posicionData = posicionForm.value;
      const centro = ConfiguracionUtil.obtenerParametroxId(posicionData.centros, posicionData.idCentroSuministro);
      const almacen = ConfiguracionUtil.obtenerParametroxId(posicionData.almacenes, posicionData.idAlmacen);

      detalles.push({
        centro: centro.codigo,
        almacen: almacen.codigo,
        cantidadSolicitada: posicionData.cantidadVenta,
        material: posicionData.codigoMaterial,
        multisociedad: "",
        posicion: item,
        unidad: posicionData.unidadMedidaVenta
      });
    });

    if(detalles.length > 0) {
      const data = {
        centro: detalles[0].centro,
        almacen: detalles[0].almacen,
        canalDistribucion: "20",
        grupoVendedor: "112",
        oficina: "1001",
        orgVentas: "2000",
        detalles: detalles
      }

      let request : any = {};
      request.formulario = data;
      request.usuario = this.settingsService.getUsername();

      this.settingsService.mostrarSpinner();
      this.service.consultarStock(request)
      .subscribe(
        (response: any) => {
          this.settingsService.ocultarSpinner();
          if(response != null) {
            this.agregarStock(response.datos as MaterialStock[]);
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

  agregarStock(stockMateriales: MaterialStock[]) {
    if(stockMateriales == null) return;
    stockMateriales.forEach(material => {
      this.posiciones.controls.forEach((posicionForm: FormGroup) => {
        if(posicionForm.controls['codigoMaterial'].value == material.material.replace(/^(0+)/g, '')) {
          const stock = parseFloat("" + material.stock);
          posicionForm.controls['stock'].patchValue(stock);
        }
      });
    });
  }

  actualizarStockPorPosicion(almacen: Almacen, posicionForm: FormGroup) {
    const posicionData = posicionForm.value;
    const centro = ConfiguracionUtil.obtenerParametroxId(posicionData.centros, posicionData.idCentroSuministro);

    let detalles = [];
    detalles.push({
      centro: centro.codigo,
      almacen: almacen.codigo,
      cantidadSolicitada: posicionData.cantidadVenta,
      material: posicionData.codigoMaterial,
      multisociedad: "",
      posicion: 10,
      unidad: posicionData.unidadMedidaVenta
    });

    const data = {
      centro: centro.codigo,
      almacen: almacen.codigo,
      canalDistribucion: "20",
      grupoVendedor: "112",
      oficina: "1001",
      orgVentas: "2000",
      detalles: detalles
    }

    let request : any = {};
    request.formulario = data;
    request.usuario = this.settingsService.getUsername();

    this.settingsService.mostrarSpinner();
    Promise.all([
      this.service.consultarStock(request).toPromise()
    ]).then(
      (data: any) => {
        this.settingsService.ocultarSpinner();
        const material = data[0].datos as MaterialStock[];
        if(material != null && material.length > 0) {
          const stock = parseFloat("" + material[0].stock);
          posicionForm.controls['stock'].patchValue(stock);
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

  fleteOblitario(): boolean {
    const formData: any = this.documentoForm.getRawValue();
    return formData.idIncotermComercial == Constantes.P_ID_INCOTERM_CFR || formData.idIncotermComercial == Constantes.P_ID_INCOTERM_CIF;
  }

  seguroOblitario(): boolean {
    const formData: any = this.documentoForm.getRawValue();
    return formData.idIncotermComercial == Constantes.P_ID_INCOTERM_CIF;
  }
}
