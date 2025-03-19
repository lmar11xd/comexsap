import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MenuItem, MessageService } from 'primeng/api';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { EDITAR_COTIZACION, LISTAR_COTIZACION } from 'src/app/shared/breadcrumb/breadcrumb';
import { Breadcrumb2Service } from 'src/app/shared/breadcrumb2.service';
import { Utils } from 'src/app/utils/utils';
import { ConfiguracionService } from '../../configuracion/configuracion.sevice';
import { Cliente } from '../../configuracion/to/cliente';
import { Destinatario } from '../../configuracion/to/destinatario';
import { Material } from '../../configuracion/to/material';
import { Parametro } from '../../configuracion/to/parametro';
import { Puerto } from '../../configuracion/to/puerto';
import { UnidadMedida } from '../../configuracion/to/unidadmedida';
import { CotizacionService } from '../cotizacion.service';
import { Cotizacion } from '../to/cotizacion';
import { CotizacionPosicion } from '../to/cotizacionposicion';
import { NumerosALetras } from 'src/app/utils/numeroaletras';
import { ConfiguracionUtil } from '../../configuracion/configuracion.util';
import { Constantes } from 'src/app/utils/constantes';

@Component({
  selector: 'app-editar-cotizacion',
  templateUrl: './editar-cotizacion.component.html',
  styleUrls: ['./editar-cotizacion.component.scss']
})
export class EditarCotizacionComponent implements OnInit {
  @ViewChild('modalCotizacionExitosa') modalCotizacionExitosa: ElementRef;
  @ViewChild('modalConfirmacionExitosa') modalConfirmacionExitosa: ElementRef;
  @ViewChild('modalVerPdf') modalVerPdf: ElementRef;

  pdfSrc: Uint8Array;

  active = 1;
  activeDatos = 1;
  activeContacto = 1;

  idCotizacion: string = null;
  parent: string = null;

  dataCotizacion: Cotizacion;
  dataPosiciones: CotizacionPosicion[] = [];
  posicionesEliminadas: CotizacionPosicion[] = [];
  codigoPedidoFirme: string = "";

  cotizacionForm: FormGroup;

  destinatarios: Destinatario[] = [];
  pagos: Parametro[] = [];
  incoterms: Parametro[] = [];
  estados: Parametro[] = [];
  monedas: Parametro[] = [];
  despachos: Parametro[] = [];
  listaPrecios: Parametro[] = [];

  selectedCliente: Cliente;
  selectedDestinatario: Destinatario;
  selectedPuertoOrigen: Puerto;
  selectedPuertoDestino: Puerto;

  mensajeValidacion: string = "";

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private breadcrumb2Service: Breadcrumb2Service,
    private messageService: MessageService,
    private modalService: NgbModal,
    private settingsService: SettingsService,
    private configuracionService: ConfiguracionService,
    private cotizacionService: CotizacionService
  ) {
  }

  ngOnInit(): void {
    this.idCotizacion = this.activatedRoute.snapshot.paramMap.get("id");
    this.parent = this.activatedRoute.snapshot.paramMap.get("parent");
    this.inicializarBreadcrumb(this.idCotizacion);
    this.crearFormulario();
    this.inicializar();
  }

  inicializarBreadcrumb(id: string){
    EDITAR_COTIZACION[0].url = EDITAR_COTIZACION[0].url.replace(':id', id);
    let BREADCRUMBS: MenuItem[] = [];
    BREADCRUMBS = BREADCRUMBS.concat(LISTAR_COTIZACION);
    BREADCRUMBS = BREADCRUMBS.concat(EDITAR_COTIZACION);
    this.breadcrumb2Service.addBreadcrumbs(BREADCRUMBS);
  }

  inicializar() {
    this.settingsService.mostrarSpinner();
    const request = {
      formulario: {
        idPedido: this.idCotizacion
      }
    }
    Promise.all([
      this.configuracionService.listarParametroxDominio(Constantes.P_D034).toPromise(),//Condicion Pago Cotizacion
      this.configuracionService.listarParametroxDominio(Constantes.P_D003).toPromise(),//Incoterm
      this.configuracionService.listarParametroxDominio(Constantes.P_D010).toPromise(),//Estado
      this.configuracionService.listarParametroxDominio(Constantes.P_D006).toPromise(),//Moneda
      this.configuracionService.listarParametroxDominio(Constantes.P_D008).toPromise(),//Despacho
      this.configuracionService.listarParametroxDominio(Constantes.P_D013).toPromise(),//Lista Precios
      this.cotizacionService.obtenerCotizacionxId(request).toPromise()
    ]).then(
      (data :any[]) => {
        this.settingsService.ocultarSpinner();
        this.pagos = Parametro.toArray(data[0]);
        this.incoterms = Parametro.toArray(data[1]);
        this.estados = Parametro.toArray(data[2]);
        this.monedas = Parametro.toArray(data[3]);
        this.despachos = Parametro.toArray(data[4]);
        this.listaPrecios = Parametro.toArray(data[5]);
        this.dataCotizacion = data[6].cotizacion[0];
        this.dataPosiciones = data[6].posiciones;

        this.codigoPedidoFirme = this.dataCotizacion.codigoPedido;

        this.dataCotizacion.clienteSapExiste = Utils.isCodeSapCustomer(this.dataCotizacion.codigoCliente);
        if(this.dataCotizacion.clienteSapExiste){
          this.selectedCliente = new Cliente(
            this.dataCotizacion.codigoCliente,
            this.dataCotizacion.nombreCliente,
            ''
          );

          Promise.all([
            this.configuracionService.listarDestinatarios(this.dataCotizacion.codigoCliente).toPromise(),
          ]).then(
            (data : any[]) => {
              this.destinatarios = Destinatario.toArray(data[0]);

              if(!Utils.isNullOrEmpty(this.dataCotizacion.codigoDestinatario)){
                this.selectedDestinatario = new Destinatario(
                  this.dataCotizacion.codigoDestinatario,
                  this.dataCotizacion.codigoCliente,
                  this.dataCotizacion.nombreDestinatario
                )
              }
            },
            (err) => {
              this.destinatarios = [];
            }
          );
        }

        this.selectedPuertoOrigen = new Puerto(
          this.dataCotizacion.idPaisPuertoOrigen,
          this.dataCotizacion.codigoPuertoOrigen,
          this.dataCotizacion.nombrePuertoOrigen,
          this.dataCotizacion.codigoSapPaisPuestoOrig,
          this.dataCotizacion.nombrePaisPuertoOrigen
        )

        if(this.dataCotizacion.idPuertoDestino > 0) {
          this.selectedPuertoDestino = new Puerto(
            this.dataCotizacion.idPaisPuertoDestino,
            this.dataCotizacion.codigoPuertoDestino,
            this.dataCotizacion.nombrePuertoDestino,
            this.dataCotizacion.codigoSapPaisPuertoDest,
            this.dataCotizacion.nombrePaisPuertoDestino
          )
        }

        if(this.dataPosiciones != null) {
          this.dataPosiciones.forEach((posicion: CotizacionPosicion) => {
            const material = new Material(posicion.codigoSAP, posicion.descripcionProducto, posicion.codigoSAPUnidadMedida, posicion.codigoSAPUnidadMedida, 0, 0);
            const posicionForm = new FormGroup({
              checked: new FormControl(false),
              id: new FormControl(posicion.id),
              item: new FormControl(posicion.item),
              codigoMaterial: new FormControl(posicion.codigoSAP),
              descripcionMaterial: new FormControl(posicion.descripcionProducto),
              cantidad: new FormControl(posicion.cantidad),
              cantidadVenta: new FormControl(posicion.cantidadVenta),
              unidadMedidaVenta: new FormControl(posicion.codigoSAPUnidadMedidaVenta),
              pesoToneladas: new FormControl(posicion.pesoTonelada),
              precioUnitarioSap: new FormControl(posicion.precioUnitarioSAP),
              precioUnitario: new FormControl(posicion.precioUnitario),
              importe: new FormControl(posicion.importe),
              selectedMaterial: new FormControl(material),
              listaUnidadesMedida: new FormControl([]),
              selectedUnidadMedida: new FormControl(),
              precioMaterialSap: new FormControl()
            });
            this.posiciones.push(posicionForm);

            Promise.all([
              this.configuracionService.listarUnidadMedida(Utils.toCodeMaterial(posicion.codigoSAP)).toPromise(),
              this.configuracionService.obtenerUnidadMedidaxProducto(Utils.toCodeMaterial(posicion.codigoSAP), posicion.codigoSAPUnidadMedidaVenta).toPromise(),
            ])
            .then(
              (data: any[]) => {
                const listaUnidades = UnidadMedida.toArray(data[0]);
                const unidadMedida = UnidadMedida.toObject(data[1]);
                posicionForm.controls['listaUnidadesMedida'].patchValue(listaUnidades);
                posicionForm.controls['selectedUnidadMedida'].patchValue(unidadMedida);
              },
              (err) => {
                this.messageService.add({
                  severity: "warn",
                  summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
                  detail: JSON.stringify(err)
                });
              }
            );
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
    this.cotizacionForm = new FormGroup({
      codigo: new FormControl(),
      selectAllPosiciones: new FormControl(),
      existeClienteSap: new FormControl(),
      codigoCliente: new FormControl(),
      nombreCliente: new FormControl(),
      codigoDestinatario: new FormControl(),
      nombreDestinatario: new FormControl(),
      idPuertoOrigen: new FormControl(),
      idIncoterm: new FormControl(),
      idIncotermComercial: new FormControl(),
      importeFlete: new FormControl(),
      seguroInternacional: new FormControl(),
      idPuertoDestino: new FormControl(),
      numeroContenedor: new FormControl(),
      fechaCotizacion: new FormControl(),
      idTipoDespacho: new FormControl(),
      idCondicionPago: new FormControl(),
      idMoneda: new FormControl(),
      fechaListaPrecio: new FormControl(),
      idListaPrecio: new FormControl(),
      vigenciaOferta: new FormControl(),
      diasOferta: new FormControl(),
      tiempoEntrega: new FormControl(),
      contactoNombre: new FormControl(),
      contactoCargo: new FormControl(),
      contactoCorreo: new FormControl(),
      contactoAdicionalNombre: new FormControl(),
      contactoAdicionalCargo: new FormControl(),
      contactoAdicionalCorreo: new FormControl(),
      observaciones: new FormControl(),
      posiciones: new FormArray([]),
      estadoDocumento: new FormControl()
    });
  }

  inicializarFormulario() {
    if(this.dataCotizacion != null) {
      this.cotizacionForm.controls['codigo'].patchValue(this.dataCotizacion.codigoPedido, {onlySelf: true});
      this.cotizacionForm.controls['codigo'].disable();
      this.cotizacionForm.controls['codigo'].setValidators([Validators.required]);

      this.cotizacionForm.controls['existeClienteSap'].patchValue(this.dataCotizacion.clienteSapExiste, {onlySelf: true});
      this.cotizacionForm.controls['codigoCliente'].patchValue(this.dataCotizacion.clienteSapExiste ? this.dataCotizacion.codigoCliente : null, {onlySelf: true});
      this.cotizacionForm.controls['nombreCliente'].patchValue(this.dataCotizacion.clienteSapExiste ? "" : this.dataCotizacion.nombreCliente, {onlySelf: true});
      this.cotizacionForm.controls['codigoDestinatario'].patchValue(this.dataCotizacion.clienteSapExiste ? this.dataCotizacion.codigoDestinatario : null, {onlySelf: true});
      this.cotizacionForm.controls['nombreDestinatario'].patchValue(this.dataCotizacion.clienteSapExiste ? "" : this.dataCotizacion.nombreDestinatario, {onlySelf: true});
      this.cotizacionForm.controls['idPuertoOrigen'].patchValue(this.dataCotizacion.idPuertoOrigen, {onlySelf: true});
      this.cotizacionForm.controls['idIncoterm'].patchValue(this.dataCotizacion.idIncoterm == 0 ? null : this.dataCotizacion.idIncoterm, {onlySelf: true});
      this.cotizacionForm.controls['idIncotermComercial'].patchValue(this.dataCotizacion.idIncotermComercial == 0 ? null : this.dataCotizacion.idIncotermComercial, {onlySelf: true});
      this.cotizacionForm.controls['importeFlete'].patchValue(this.dataCotizacion.importeFlete == 0 ? null : this.dataCotizacion.importeFlete, {onlySelf: true});
      this.cotizacionForm.controls['seguroInternacional'].patchValue(this.dataCotizacion.seguroInternacional == 0 ? null : this.dataCotizacion.seguroInternacional, {onlySelf: true});
      this.cotizacionForm.controls['idPuertoDestino'].patchValue(this.dataCotizacion.idPuertoDestino, {onlySelf: true});
      this.cotizacionForm.controls['numeroContenedor'].patchValue(this.dataCotizacion.numeroContenedor == 0 ? null : this.dataCotizacion.numeroContenedor, {onlySelf: true});
      this.cotizacionForm.controls['fechaCotizacion'].patchValue(Utils.stringToDate(this.dataCotizacion.fechaSolicitud), {onlySelf: true});
      this.cotizacionForm.controls['idTipoDespacho'].patchValue(this.dataCotizacion.idDespacho, {onlySelf: true});
      this.cotizacionForm.controls['idCondicionPago'].patchValue(this.dataCotizacion.idCondicionPago, {onlySelf: true});
      this.cotizacionForm.controls['idMoneda'].patchValue(this.dataCotizacion.idMoneda, {onlySelf: true});
      this.cotizacionForm.controls['fechaListaPrecio'].patchValue(Utils.stringToDate(this.dataCotizacion.fechaListaPrecio), {onlySelf: true});
      this.cotizacionForm.controls['idListaPrecio'].patchValue(this.dataCotizacion.idListaPrecio == 0 ? null : this.dataCotizacion.idListaPrecio, {onlySelf: true});
      this.cotizacionForm.controls['vigenciaOferta'].patchValue(this.dataCotizacion.vigenciaOferta != null && this.dataCotizacion.vigenciaOferta > 0, {onlySelf: true});
      this.cotizacionForm.controls['diasOferta'].patchValue(this.dataCotizacion.vigenciaOferta, {onlySelf: true});
      this.cotizacionForm.controls['tiempoEntrega'].patchValue(this.dataCotizacion.tiempoEntrega, {onlySelf: true});
      this.cotizacionForm.controls['contactoNombre'].patchValue(this.dataCotizacion.nombreContacto, {onlySelf: true});
      this.cotizacionForm.controls['contactoCargo'].patchValue(this.dataCotizacion.cargoContacto, {onlySelf: true});
      this.cotizacionForm.controls['contactoCorreo'].patchValue(this.dataCotizacion.correoContacto, {onlySelf: true});
      this.cotizacionForm.controls['contactoAdicionalNombre'].patchValue(this.dataCotizacion.nombreContactoAdicional, {onlySelf: true});
      this.cotizacionForm.controls['contactoAdicionalCargo'].patchValue(this.dataCotizacion.cargoContactoAdicional, {onlySelf: true});
      this.cotizacionForm.controls['contactoAdicionalCorreo'].patchValue(this.dataCotizacion.correoContactoAdicional, {onlySelf: true});
      this.cotizacionForm.controls['observaciones'].patchValue(this.dataCotizacion.observacion, {onlySelf: true});
      this.cotizacionForm.controls['posiciones'].patchValue([], {onlySelf: true});
      this.cotizacionForm.controls['estadoDocumento'].patchValue(this.dataCotizacion.idEstadoDocumento, {onlySelf: true});

      if(this.dataCotizacion.idEstadoDocumento == 6) {
        this.cotizacionForm.controls['existeClienteSap'].disable();
        this.cotizacionForm.controls['codigoCliente'].disable();
        this.cotizacionForm.controls['nombreCliente'].disable();
        this.cotizacionForm.controls['codigoDestinatario'].disable();
        this.cotizacionForm.controls['nombreDestinatario'].disable();
        this.cotizacionForm.controls['idPuertoOrigen'].disable();
        this.cotizacionForm.controls['idIncoterm'].disable();
        this.cotizacionForm.controls['idIncotermComercial'].disable();
        this.cotizacionForm.controls['importeFlete'].disable();
        this.cotizacionForm.controls['seguroInternacional'].disable();
        this.cotizacionForm.controls['idPuertoDestino'].disable();
        this.cotizacionForm.controls['numeroContenedor'].disable();
        this.cotizacionForm.controls['fechaCotizacion'].disable();
        this.cotizacionForm.controls['idTipoDespacho'].disable();
        this.cotizacionForm.controls['idCondicionPago'].disable();
        this.cotizacionForm.controls['idMoneda'].disable();
        this.cotizacionForm.controls['fechaListaPrecio'].disable();
        this.cotizacionForm.controls['idListaPrecio'].disable();
        this.cotizacionForm.controls['vigenciaOferta'].disable();
        this.cotizacionForm.controls['diasOferta'].disable();
        this.cotizacionForm.controls['tiempoEntrega'].disable();
        this.cotizacionForm.controls['contactoNombre'].disable();
        this.cotizacionForm.controls['contactoCargo'].disable();
        this.cotizacionForm.controls['contactoCorreo'].disable();
        this.cotizacionForm.controls['contactoAdicionalNombre'].disable();
        this.cotizacionForm.controls['contactoAdicionalCargo'].disable();
        this.cotizacionForm.controls['contactoAdicionalCorreo'].disable();
        this.cotizacionForm.controls['observaciones'].disable();

        this.cotizacionForm.controls['existeClienteSap'].setValidators([Validators.required]);
        this.cotizacionForm.controls['codigoCliente'].setValidators([Validators.required]);
        this.cotizacionForm.controls['nombreCliente'].setValidators([Validators.required]);
        this.cotizacionForm.controls['codigoDestinatario'].setValidators([Validators.required]);
        this.cotizacionForm.controls['nombreDestinatario'].setValidators([Validators.required]);
        this.cotizacionForm.controls['idPuertoOrigen'].setValidators([Validators.required]);
        this.cotizacionForm.controls['idIncoterm'].setValidators([Validators.required]);
        this.cotizacionForm.controls['idIncotermComercial'].setValidators([Validators.required]);
        this.cotizacionForm.controls['importeFlete'].setValidators([Validators.required]);
        this.cotizacionForm.controls['seguroInternacional'].setValidators([Validators.required]);
        this.cotizacionForm.controls['idPuertoDestino'].setValidators([Validators.required]);
        this.cotizacionForm.controls['numeroContenedor'].setValidators([Validators.required]);
        this.cotizacionForm.controls['fechaCotizacion'].setValidators([Validators.required]);
        this.cotizacionForm.controls['idTipoDespacho'].setValidators([Validators.required]);
        this.cotizacionForm.controls['idCondicionPago'].setValidators([Validators.required]);
        this.cotizacionForm.controls['idMoneda'].setValidators([Validators.required]);
        this.cotizacionForm.controls['fechaListaPrecio'].setValidators([Validators.required]);
        this.cotizacionForm.controls['idListaPrecio'].setValidators([Validators.required]);
        this.cotizacionForm.controls['vigenciaOferta'].setValidators([Validators.required]);
        this.cotizacionForm.controls['diasOferta'].setValidators([Validators.required]);
        this.cotizacionForm.controls['tiempoEntrega'].setValidators([Validators.required]);
        this.cotizacionForm.controls['contactoNombre'].setValidators([Validators.required]);
        this.cotizacionForm.controls['contactoCargo'].setValidators([Validators.required]);
        this.cotizacionForm.controls['contactoCorreo'].setValidators([Validators.required]);
        this.cotizacionForm.controls['contactoAdicionalNombre'].setValidators([Validators.required]);
        this.cotizacionForm.controls['contactoAdicionalCargo'].setValidators([Validators.required]);
        this.cotizacionForm.controls['contactoAdicionalCorreo'].setValidators([Validators.required]);
        this.cotizacionForm.controls['observaciones'].setValidators([Validators.required]);
      }

      this.actualizarPrecios(false);
    }
  }

  limpiarValidadores() {
    //this.cotizacionForm.controls['codigo'].clearValidators();
    this.cotizacionForm.controls['existeClienteSap'].clearValidators();
    this.cotizacionForm.controls['codigoCliente'].clearValidators();
    this.cotizacionForm.controls['nombreCliente'].clearValidators();
    this.cotizacionForm.controls['codigoDestinatario'].clearValidators();
    this.cotizacionForm.controls['nombreDestinatario'].clearValidators();
    this.cotizacionForm.controls['idPuertoOrigen'].clearValidators();
    this.cotizacionForm.controls['idIncoterm'].clearValidators();
    this.cotizacionForm.controls['idIncotermComercial'].clearValidators();
    this.cotizacionForm.controls['importeFlete'].clearValidators();
    this.cotizacionForm.controls['seguroInternacional'].clearValidators();
    this.cotizacionForm.controls['idPuertoDestino'].clearValidators();
    this.cotizacionForm.controls['numeroContenedor'].clearValidators();
    this.cotizacionForm.controls['fechaCotizacion'].clearValidators();
    this.cotizacionForm.controls['idTipoDespacho'].clearValidators();
    this.cotizacionForm.controls['idCondicionPago'].clearValidators();
    this.cotizacionForm.controls['idMoneda'].clearValidators();
    this.cotizacionForm.controls['fechaListaPrecio'].clearValidators();
    this.cotizacionForm.controls['idListaPrecio'].clearValidators();
    this.cotizacionForm.controls['vigenciaOferta'].clearValidators();
    this.cotizacionForm.controls['diasOferta'].clearValidators();
    this.cotizacionForm.controls['tiempoEntrega'].clearValidators();
    this.cotizacionForm.controls['contactoNombre'].clearValidators();
    this.cotizacionForm.controls['contactoCargo'].clearValidators();
    this.cotizacionForm.controls['contactoCorreo'].clearValidators();
    this.cotizacionForm.controls['contactoAdicionalNombre'].clearValidators();
    this.cotizacionForm.controls['contactoAdicionalCargo'].clearValidators();
    this.cotizacionForm.controls['contactoAdicionalCorreo'].clearValidators();
    this.cotizacionForm.controls['observaciones'].clearValidators();
    this.cotizacionForm.controls['posiciones'].clearValidators();
  }

  refrescarValidadores() {
    //this.cotizacionForm.controls['codigo'].updateValueAndValidity();
    this.cotizacionForm.controls['existeClienteSap'].updateValueAndValidity();
    this.cotizacionForm.controls['codigoCliente'].updateValueAndValidity();
    this.cotizacionForm.controls['nombreCliente'].updateValueAndValidity();
    this.cotizacionForm.controls['codigoDestinatario'].updateValueAndValidity();
    this.cotizacionForm.controls['nombreDestinatario'].updateValueAndValidity();
    this.cotizacionForm.controls['idPuertoOrigen'].updateValueAndValidity();
    this.cotizacionForm.controls['idIncoterm'].updateValueAndValidity();
    this.cotizacionForm.controls['idIncotermComercial'].updateValueAndValidity();
    this.cotizacionForm.controls['importeFlete'].updateValueAndValidity();
    this.cotizacionForm.controls['seguroInternacional'].updateValueAndValidity();
    this.cotizacionForm.controls['idPuertoDestino'].updateValueAndValidity();
    this.cotizacionForm.controls['numeroContenedor'].updateValueAndValidity();
    this.cotizacionForm.controls['fechaCotizacion'].updateValueAndValidity();
    this.cotizacionForm.controls['idTipoDespacho'].updateValueAndValidity();
    this.cotizacionForm.controls['idCondicionPago'].updateValueAndValidity();
    this.cotizacionForm.controls['idMoneda'].updateValueAndValidity();
    this.cotizacionForm.controls['fechaListaPrecio'].updateValueAndValidity();
    this.cotizacionForm.controls['idListaPrecio'].updateValueAndValidity();
    this.cotizacionForm.controls['vigenciaOferta'].updateValueAndValidity();
    this.cotizacionForm.controls['diasOferta'].updateValueAndValidity();
    this.cotizacionForm.controls['tiempoEntrega'].updateValueAndValidity();
    this.cotizacionForm.controls['contactoNombre'].updateValueAndValidity();
    this.cotizacionForm.controls['contactoCargo'].updateValueAndValidity();
    this.cotizacionForm.controls['contactoCorreo'].updateValueAndValidity();
    this.cotizacionForm.controls['contactoAdicionalNombre'].updateValueAndValidity();
    this.cotizacionForm.controls['contactoAdicionalCargo'].updateValueAndValidity();
    this.cotizacionForm.controls['contactoAdicionalCorreo'].updateValueAndValidity();
    this.cotizacionForm.controls['observaciones'].updateValueAndValidity();
    this.cotizacionForm.controls['posiciones'].updateValueAndValidity();
  }

  get posiciones() {
    return this.cotizacionForm.controls["posiciones"] as FormArray;
  }

  obtenerSiguienteItem(): number {
    let item = 0;
    this.posiciones.controls.forEach(posicion => {
      if(posicion.value.item > item) { item = posicion.value.item; }
    });
    return item + 10;
  }

  existeMaterial(codigoMaterial: string): boolean {
    const posiciones = this.posiciones.controls.filter(posicion => posicion.value.codigoMaterial == codigoMaterial.replace(/^(0+)/g, ''));
    return posiciones.length > 0;
  }

  agregarPosicion() {
    if(this.validarFormularioPosiciones()) {
      this.desmarcarPosiciones();
      const item = this.obtenerSiguienteItem();
      const posicionForm = new FormGroup({
        checked: new FormControl(false),
        id: new FormControl(0),
        item: new FormControl(item),
        codigoMaterial: new FormControl(""),
        descripcionMaterial: new FormControl(),
        cantidad: new FormControl(),
        cantidadVenta: new FormControl(),
        unidadMedidaVenta: new FormControl(),
        pesoToneladas: new FormControl(0),
        precioUnitarioSap: new FormControl(0),
        precioUnitario: new FormControl(),
        importe: new FormControl("0"),
        selectedMaterial: new FormControl(),
        listaUnidadesMedida: new FormControl([]),
        selectedUnidadMedida: new FormControl(),
        precioMaterialSap: new FormControl()
      });
      this.posiciones.push(posicionForm);
    } else {
      this.messageService.add({
        severity: "warn",
        summary: "Posiciones",
        detail: "Por favor, ingresar los campos requeridos para las posiciones"
      });
    }
  }

  quitarPosiciones() {
    const posiciones = this.cotizacionForm.value.posiciones.filter(posicion => posicion.checked);
    if(posiciones.length > 0) {
      this.cotizacionForm.controls['selectAllPosiciones'].patchValue(false);
      posiciones.forEach(element => {
        const index = this.cotizacionForm.value.posiciones.findIndex(posicion => posicion.item == element.item);
        if(element.id != 0) {
          this.posicionesEliminadas.push(element);
        }
        this.posiciones.removeAt(index);
      });
      this.reiniciarPosicionItem();
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

  actualizarPrecios(actualizarPrecio: boolean) {
    const idListaPrecio = this.cotizacionForm.controls["idListaPrecio"].value;
    const fechaListaPrecio = this.cotizacionForm.controls["fechaListaPrecio"].value;

    const listaPrecio = ConfiguracionUtil.obtenerParametroxId(this.listaPrecios, idListaPrecio);

    if(Utils.isNullOrEmpty(fechaListaPrecio)) {
      return;
    }

    if(idListaPrecio == null || idListaPrecio == 0) {
      return;
    }

    this.posiciones.controls.forEach((posicionForm: FormGroup) => {
      const posicionData = posicionForm.getRawValue();
      const codigoMaterial = Utils.toCodeMaterial(posicionData.codigoMaterial);
      if(!Utils.isNullOrEmpty(codigoMaterial)) {
        const requestPrecio = {
          codigoMaterial: codigoMaterial,
          codigoListaPrecio: listaPrecio ? listaPrecio.codigo : "",
          fechaListaPrecio: fechaListaPrecio
        };
        this.configuracionService.obtenerPrecioMaterial(requestPrecio).subscribe((data) => {
          posicionForm.controls['precioMaterialSap'].patchValue(data);
          ConfiguracionUtil.actualizarPrecioUnitarioSap(posicionForm, actualizarPrecio);
        });
      }
    });
  }

  seleccionarCliente(cliente: Cliente) {
    this.cotizacionForm.controls['codigoDestinatario'].patchValue(null);
    this.selectedDestinatario = null;
    if(cliente) {
      this.cotizacionForm.controls['codigoCliente'].patchValue(cliente.codigo);
      this.buscarDestinatarios(cliente.codigo);
    } else {
      this.cotizacionForm.controls['codigoCliente'].patchValue(null);
    }
  }

  seleccionarDestinatario(destinatario: Destinatario) {
    this.selectedDestinatario = destinatario;
  }

  seleccionarPuertoOrigen(puerto: Puerto) {
    this.cotizacionForm.controls['idPuertoOrigen'].patchValue(puerto ? puerto.id : null);
  }

  seleccionarPuertoDestino(puerto: Puerto) {
    this.cotizacionForm.controls['idPuertoDestino'].patchValue(puerto ? puerto.id : null);
  }

  buscarDestinatarios(codigoCliente: string) {
    this.settingsService.mostrarSpinner();
    Promise.all([
      this.configuracionService.listarDestinatarios(codigoCliente).toPromise(),
    ]).then(
      (data : any[]) => {
        this.settingsService.ocultarSpinner();
        this.destinatarios = Destinatario.toArray(data[0]);
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

  seleccionarMaterial(material: Material, posicionForm: FormGroup) {
    if(material.codigo == Utils.toCodeMaterial(posicionForm.value.codigoMaterial)) return;

    this.limpiarPosicion(posicionForm);

    if(this.existeMaterial(material.codigo)) {
      this.messageService.add({
        severity: "warn",
        summary: "Material ya existe",
        detail: "No puedes agregar este material otra vez"
      });
      return;
    }

    posicionForm.controls['codigoMaterial'].patchValue(material.codigo);
    posicionForm.controls['descripcionMaterial'].patchValue(material.descripcion);
    this.buscarUnidadMedidaVenta(material.codigo, posicionForm);
  }

  limpiarPosicion(posicionForm: FormGroup) {
    ConfiguracionUtil.limpiarPosicion(posicionForm);
  }

  buscarUnidadMedidaVenta(codigoMaterial: string, posicionForm: FormGroup) {
    const idListaPrecio = this.cotizacionForm.value.idListaPrecio;
    const fechaListaPrecio = this.cotizacionForm.value.fechaListaPrecio;

    const listaPrecio = ConfiguracionUtil.obtenerParametroxId(this.listaPrecios, idListaPrecio);

    const requestPrecio = {
      codigoMaterial: codigoMaterial,
      codigoListaPrecio: listaPrecio ? listaPrecio.codigo : "",
      fechaListaPrecio: fechaListaPrecio
    };

    this.settingsService.mostrarSpinner();
    Promise.all([
      this.configuracionService.listarUnidadMedida(codigoMaterial).toPromise()
    ]).then(
      (data: any[]) => {
        this.settingsService.ocultarSpinner();
        posicionForm.controls['listaUnidadesMedida'].patchValue(UnidadMedida.toArray(data[0]));
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

    this.settingsService.mostrarSpinner();
    Promise.all([
      this.configuracionService.obtenerPrecioMaterial(requestPrecio).toPromise()
    ]).then(
      (data: any[]) => {
        this.settingsService.ocultarSpinner();
        posicionForm.controls['precioMaterialSap'].patchValue(data[0]);
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

  cambiarCantidadVenta(posicionForm: FormGroup) {
    ConfiguracionUtil.actualizarPesoToneladas(posicionForm);
    ConfiguracionUtil.actualizarImporte(posicionForm);
  }

  seleccionarUnidadMedidaVenta(unidadMedida: UnidadMedida, posicionForm: FormGroup) {
    posicionForm.controls['selectedUnidadMedida'].patchValue(unidadMedida);
    ConfiguracionUtil.actualizarPesoToneladas(posicionForm);
    ConfiguracionUtil.actualizarPrecioUnitarioSap(posicionForm, true);
    ConfiguracionUtil.actualizarImporte(posicionForm);
  }

  cambiarPrecioUnitario(posicionForm: FormGroup) {
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
    //this.cotizacionForm.controls['codigo'].setValidators([Validators.required]);
    if(this.cotizacionForm.controls['existeClienteSap'].value) {
      this.cotizacionForm.controls['codigoCliente'].setValidators([Validators.required]);
    }else{
      this.cotizacionForm.controls['nombreCliente'].setValidators([Validators.required]);
    }
    this.cotizacionForm.controls['idPuertoOrigen'].setValidators([Validators.required]);
    this.cotizacionForm.controls['idIncoterm'].setValidators([Validators.required]);
    this.cotizacionForm.controls['idIncotermComercial'].setValidators([Validators.required]);
    this.cotizacionForm.controls['fechaCotizacion'].setValidators([Validators.required]);
    this.cotizacionForm.controls['idTipoDespacho'].setValidators([Validators.required]);
    this.cotizacionForm.controls['idCondicionPago'].setValidators([Validators.required]);
    this.cotizacionForm.controls['idMoneda'].setValidators([Validators.required]);
    this.cotizacionForm.controls['fechaListaPrecio'].setValidators([Validators.required]);
    if(this.cotizacionForm.controls['vigenciaOferta'].value) {
      this.cotizacionForm.controls['diasOferta'].setValidators([Validators.required]);
    }
    this.cotizacionForm.controls['contactoNombre'].setValidators([Validators.required]);
    this.cotizacionForm.controls['contactoCorreo'].setValidators([Validators.required]);
    this.cotizacionForm.controls['posiciones'].setValidators([Validators.required, Validators.minLength(1)]);
    this.refrescarValidadores();
  }

  validarFormulario(): boolean {
    let formData: any = this.cotizacionForm.value;

    //Datos del Cliente
    if(formData.existeClienteSap) {//SAP
      if(Utils.isNullOrEmpty(formData.codigoCliente)) {
        this.mensajeValidacion = "Datos de Cotización >> Datos del Cliente: Seleccione un cliente.";
        return false;
      }
    } else {//No es SAP
      if(Utils.isNullOrEmpty(formData.nombreCliente)) {
        this.mensajeValidacion = "Datos de Cotización >> Datos del Cliente: Ingrese razón social del cliente.";
        return false;
      }
    }

    //Datos del envío
    if(formData.idPuertoOrigen == null) {
      this.mensajeValidacion = "Datos de Cotización >> Datos del Envío: Seleccione un Puerto Origen.";
      return false;
    }

    if(formData.idIncoterm == null) {
      this.mensajeValidacion = "Datos de Cotización >> Datos del Envío: Seleccione un Incoterm.";
      return false;
    }

    if(formData.idIncotermComercial == null) {
      this.mensajeValidacion = "Datos de Cotización >> Datos del Envío: Seleccione un Incoterm Comercial.";
      return false;
    }

    if(formData.idIncotermComercial == Constantes.P_ID_INCOTERM_CFR) {
      if(formData.importeFlete == null || formData.importeFlete <= 0) {
        this.mensajeValidacion = "Datos de Cotización >> Datos del Envío: Importe Flete no puede ser nulo, 0 o negativo.";
        return false;
      }
    } else if(formData.idIncotermComercial == Constantes.P_ID_INCOTERM_CIF) {
      if(formData.importeFlete == null || formData.importeFlete <= 0) {
        this.mensajeValidacion = "Datos de Cotización >> Datos del Envío: Importe Flete no puede ser nulo, 0 o negativo.";
        return false;
      }

      if(formData.seguroInternacional == null || formData.seguroInternacional <= 0) {
        this.mensajeValidacion = "Datos de Cotización >> Datos del Envío: Seguro Internacional no puede ser nulo, 0 o negativo.";
        return false;
      }
    }

    if(formData.numeroContenedor != null && formData.numeroContenedor <= 0) {
      this.mensajeValidacion = "Datos de Cotización >> Datos del Envío: Número Contenedor no puede ser nulo, 0 o negativo.";
      return false;
    }

    if(formData.numeroContenedor != null && formData.numeroContenedor <= 0) {
      this.mensajeValidacion = "Datos de Cotización >> Datos del Envío: Número Contenedor no puede ser 0 o negativo.";
      return false;
    }

    //Datos del pedido
    if(Utils.isNullOrEmpty(formData.fechaCotizacion)) {
      this.mensajeValidacion = "Datos de Cotización >> Datos del Pedido: Ingrese Fecha de Cotización.";
      return false;
    }

    if(formData.idTipoDespacho == null) {
      this.mensajeValidacion = "Datos de Cotización >> Datos del Pedido: Seleccione un Tipo de Despacho.";
      return false;
    }

    if(formData.idCondicionPago == null) {
      this.mensajeValidacion = "Datos de Cotización >> Datos del Pedido: Seleccione una Condición Pago.";
      return false;
    }

    if(formData.idMoneda == null) {
      this.mensajeValidacion = "Datos de Cotización >> Datos del Pedido: Seleccione una Moneda.";
      return false;
    }

    if(Utils.isNullOrEmpty(formData.fechaListaPrecio)) {
      this.mensajeValidacion = "Datos de Cotización >> Datos del Pedido: Ingrese Fecha Lista Precio.";
      return false;
    }

    if(formData.vigenciaOferta) {
      if(formData.diasOferta <= 0) {
        this.mensajeValidacion = "Datos de Cotización >> Datos del Pedido: Días no puede ser 0, negativo o vacío.";
        return false;
      }
    }

    //Contacto
    if(Utils.isNullOrEmpty(formData.contactoNombre)) {
      this.mensajeValidacion = "Contacto >> Datos del Cliente: Ingrese nombres de contacto.";
      return false;
    }

    if(Utils.isNullOrEmpty(formData.contactoCorreo)) {
      this.mensajeValidacion = "Contacto >> Datos del Cliente: Ingrese correo de contacto.";
      return false;
    }

    //Posiciones
    if(this.posiciones.length == 0) {
      this.mensajeValidacion = "Datos de Cotización >> Datos de Posición: Necesita agregar al menos una posición.";
      return false;
    }

    if(!this.validarFormularioPosiciones()) {
      return false;
    }

    return true;
  }

  validarFormularioPosiciones(): boolean {
    let isValid = true;
    this.posiciones.controls.forEach((posicionForm: FormGroup) => {
      let formData: any = posicionForm.value;

      if(Utils.isNullOrEmpty(formData.codigoMaterial)) {
        this.mensajeValidacion = "Datos de Cotización >> Datos de Posición: Seleccione un material.";
        isValid = false;
        return;
      }

      if(formData.cantidadVenta == null || formData.cantidadVenta <= 0) {
        this.mensajeValidacion = "Datos de Cotización >> Datos de Posición: Ingrese Cantidad Venta.";
        isValid = false;
        return;
      }

      if(Utils.isNullOrEmpty(formData.unidadMedidaVenta)) {
        this.mensajeValidacion = "Datos de Cotización >> Datos de Posición: Seleccione Unidad de Medida Venta.";
        isValid = false;
        return;
      }

      if(formData.precioUnitario == null || formData.precioUnitario <= 0) {
        this.mensajeValidacion = "Datos de Cotización >> Datos de Posición: Ingrese Precio Unitario.";
        isValid = false;
        return;
      }
    })
    return isValid;
  }

  obtenerDatosCotizacion() {
    const cotizacionFormData = this.cotizacionForm.value;
    const nombreDestinatario = this.selectedDestinatario ? this.selectedDestinatario.descripcion : "";

    const cabecera = {
      idCotizacion: this.dataCotizacion.idCotizacion,
      codigoPedido: this.dataCotizacion.codigoPedido,
      clienteSapExiste: cotizacionFormData.existeClienteSap,
      codigoCliente: cotizacionFormData.existeClienteSap ? cotizacionFormData.codigoCliente : "",
      nombreCliente: cotizacionFormData.existeClienteSap ? this.selectedCliente.descripcion : cotizacionFormData.nombreCliente,
      codigoDestinatario: cotizacionFormData.existeClienteSap ? cotizacionFormData.codigoDestinatario : "",
      nombreDestinatario: cotizacionFormData.existeClienteSap ? nombreDestinatario : cotizacionFormData.nombreDestinatario,
      idPuertoOrigen: cotizacionFormData.idPuertoOrigen,
      idPuertoDestino: cotizacionFormData.idPuertoDestino,
      idIncoterm: cotizacionFormData.idIncoterm,
      idIncotermComercial: cotizacionFormData.idIncotermComercial,
      importeFlete: cotizacionFormData.importeFlete,
      seguroInternacional: cotizacionFormData.seguroInternacional,
      numeroContenedor: cotizacionFormData.numeroContenedor,
      fechaSolicitud: cotizacionFormData.fechaCotizacion,
      idDespacho: cotizacionFormData.idTipoDespacho,
      idCondicionPago: cotizacionFormData.idCondicionPago,
      idMoneda: cotizacionFormData.idMoneda,
      fechaListaPrecio: cotizacionFormData.fechaListaPrecio,
      idListaPrecio: cotizacionFormData.idListaPrecio,
      vigenciaOferta: cotizacionFormData.vigenciaOferta ? cotizacionFormData.diasOferta : 0,
      tiempoEntrega: cotizacionFormData.tiempoEntrega,
      observacion: cotizacionFormData.observaciones,
      nombreContacto: cotizacionFormData.contactoNombre,
      cargoContacto: cotizacionFormData.contactoCargo,
      correoContacto: cotizacionFormData.contactoCorreo,
      nombreContactoAdicional: cotizacionFormData.contactoAdicionalNombre,
      cargoContactoAdicional: cotizacionFormData.contactoAdicionalCargo,
      correoContactoAdicional: cotizacionFormData.contactoAdicionalCorreo,
      idTipoTransporte: 1, //MARITIMO
      tipoSolicitud: 7, //COTIZACION
      idFormaPago: 1
    }

    let posiciones: any[] = [];
    this.posiciones.controls.forEach((posicionForm: FormGroup) => {
      const posicionFormData = posicionForm.value;
      const codigoUnidadMedida = Utils.isNullOrEmpty(posicionFormData.selectedMaterial.unidadVenta) ? posicionFormData.unidadMedidaVenta : posicionFormData.selectedMaterial.unidadVenta;
      const pesoNominal = posicionFormData.selectedUnidadMedida ? posicionFormData.selectedUnidadMedida.pesoNominal : 0;

      posiciones.push({
        id: posicionFormData.id,
        idPedido: this.dataCotizacion.idCotizacion,
        item: posicionFormData.item,
        codigoSAP: posicionFormData.codigoMaterial.replace(/^(0+)/g, ''),
        descripcionProducto: posicionFormData.selectedMaterial.descripcion ? posicionFormData.selectedMaterial.descripcion : "",
        cantidad: posicionFormData.cantidad,
        cantidadVenta: posicionFormData.cantidadVenta,
        codigoSAPUnidadMedida: codigoUnidadMedida,
        codigoSAPUnidadMedidaVenta: posicionFormData.unidadMedidaVenta,
        pesoTonelada: posicionFormData.pesoToneladas,
        precioUnitarioSAP: posicionFormData.precioUnitarioSap,
        precioUnitario: posicionFormData.precioUnitario,
        importe: posicionFormData.importe,
        pesoNominal: pesoNominal,
        estado: 1
      });
    });

    this.posicionesEliminadas.forEach((posicion) => {
      posiciones.push({id: posicion.id, estado: 0});
    });

    const cotizacion = {
      codigoPedidoFirme: "",
      cabecera: cabecera,
      posiciones: posiciones
    }

    let request : any = {};
    request.formulario = cotizacion;
    request.usuario = this.settingsService.getUsername();
    return request;
  }

  confirmarCotizacion() {
    if(this.codigoPedidoFirme == null || this.codigoPedidoFirme == "") {
      this.messageService.add({
        severity: "warn",
        summary: this.settingsService.MENSAJES['mensaje_validacion_error_campo_requerido'],
        detail: JSON.stringify('Ingrese un número de pedido firme, por favor.')
      });
      return;
    }

    this.codigoPedidoFirme = this.codigoPedidoFirme.toUpperCase();
    if(!Utils.isValidCodePer(this.codigoPedidoFirme)) {
      this.messageService.add({
        severity: "warn",
        summary: 'Formato no válido',
        detail: JSON.stringify('El formato del número de pedido firme no es válido. Ejm: PER.XXX.XX')
      });
      return;
    }

    const request = this.obtenerDatosCotizacion();
    request.formulario.codigoPedidoFirme = this.codigoPedidoFirme;

    this.settingsService.mostrarSpinner();
    Promise.all([
      this.cotizacionService.confirmarCotizacion(request).toPromise()
    ]).then(
      (data : any[]) => {
        this.settingsService.ocultarSpinner();
        if(data[0].cod_rpta == 1) {
          this.modalService.open(this.modalConfirmacionExitosa, { centered: true, backdrop: 'static', keyboard: false });
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
    );
  }

  guardar() {
    this.inicializarValidaciones();
    this.cotizacionForm.markAllAsTouched();

    const isValid = this.validarFormulario();
    if(!isValid) {
      this.messageService.add({
        severity: "warn",
        summary: "Revisar:",
        detail: this.mensajeValidacion
      });
      return;
    }

    const request = this.obtenerDatosCotizacion();

    this.settingsService.mostrarSpinner();
    Promise.all([
      this.cotizacionService.guardarCotizacion(request).toPromise()
    ]).then(
      (data : any) => {
        this.settingsService.ocultarSpinner();
        if(data[0].cod_rpta == '1') {
          this.modalService.open(this.modalCotizacionExitosa, { centered: true, backdrop: 'static', keyboard: false });
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

  mostrarModal(content: string) {
    this.modalService.open(content, { centered: true });
  }

  irAlListado() {
    this.modalService.dismissAll();
    this.router.navigate(['/exportaciones/cotización/listar-cotizacion']);
  }

  obtenerDatosGenerarPdf() {
    const cotizacionFormData = this.cotizacionForm.controls;

    let nombreCondicionPago = "", nombrePuertoOrigen = "", nombrePuertoDestino = "", lugarContacto = "", codigoIncoterm = "", codigoIncotermComercial = "", vigencia = "", codigoMoneda = "";

    if(this.pagos && cotizacionFormData.idCondicionPago.value) {
      const pago = this.pagos.find(o => o.id == cotizacionFormData.idCondicionPago.value);
      nombreCondicionPago = pago ? pago.descripcion: "";
    }

    if(this.selectedPuertoOrigen != null) {
      nombrePuertoOrigen = this.selectedPuertoOrigen.descripcion;
    }

    if(this.selectedPuertoDestino != null) {
      nombrePuertoDestino = this.selectedPuertoDestino.descripcion;
      lugarContacto = this.selectedPuertoDestino.descripcionPais;
    }

    if(this.monedas && cotizacionFormData.idMoneda.value) {
      const moneda = this.monedas.find(o => o.id == cotizacionFormData.idMoneda.value);
      codigoMoneda = moneda ? moneda.codigo: "";
    }

    if(this.incoterms && cotizacionFormData.idIncoterm.value) {
      const incoterm = this.incoterms.find(o => o.id == cotizacionFormData.idIncoterm.value);
      codigoIncoterm = incoterm ? incoterm.codigo: "";
    }

    if(this.incoterms && cotizacionFormData.idIncotermComercial.value) {
      const incoterm = this.incoterms.find(o => o.id == cotizacionFormData.idIncotermComercial.value);
      codigoIncotermComercial = incoterm ? incoterm.codigo: "";
    }

    if(cotizacionFormData.vigenciaOferta.value && cotizacionFormData.diasOferta.value) {
      vigencia = cotizacionFormData.diasOferta.value + ' Días';
    }

    const cabecera = {
      tipoDocumento: true,//cotizacionFormData.vigenciaOferta.value,
      idCotizacion: this.dataCotizacion.idCotizacion,
      codigoPedido: this.dataCotizacion.codigoPedido,
      codigoCliente: cotizacionFormData.existeClienteSap ? cotizacionFormData.codigoCliente.value : "",
      nombreContacto: cotizacionFormData.contactoNombre.value ? cotizacionFormData.contactoNombre.value : "",
      correoContacto: cotizacionFormData.contactoCorreo.value ? cotizacionFormData.contactoCorreo.value : "",
      correoContactoAdicional: cotizacionFormData.contactoAdicionalCorreo.value ? cotizacionFormData.contactoAdicionalCorreo.value : "",
      cargoContacto: cotizacionFormData.contactoCargo.value ? cotizacionFormData.contactoCargo.value : "",
      lugarContacto: lugarContacto,
      descripcionMaterial: "",
      nombrePuertoOrigen: nombrePuertoOrigen,
      nombrePuertoDestino: nombrePuertoDestino,
      razonSocial: this.selectedCliente != null ? this.selectedCliente.descripcion : cotizacionFormData.nombreCliente.value,
      nombreCliente: this.selectedCliente != null ? this.selectedCliente.descripcion : cotizacionFormData.nombreCliente.value,
      nombreCondicionPago: nombreCondicionPago,
      nombreIncoterm: codigoIncoterm,
      nombreIncotermComercial: codigoIncotermComercial,
      vigencia: vigencia,
      descripcionPrecio: "",
      observacion: cotizacionFormData.observaciones.value ? cotizacionFormData.observaciones.value.trim() : "",
      tiempoEntrega: cotizacionFormData.tiempoEntrega.value ? cotizacionFormData.tiempoEntrega.value: "",
      importeFlete: cotizacionFormData.importeFlete.value ? cotizacionFormData.importeFlete.value : 0,
      seguroInternacional: cotizacionFormData.seguroInternacional.value ? cotizacionFormData.seguroInternacional.value : 0,
      numeroContenedor: cotizacionFormData.numeroContenedor.value ? cotizacionFormData.numeroContenedor.value : 0,
      codigoPersonal: this.settingsService.getSesionSSO().strPv_cpersonal,
      tipoSolicitud: Constantes.P_SOLICITUD_COTIZACION
    }

    let posiciones: any[] = [];
    let total: number = 0, contadorPos = 0;
    this.posiciones.controls.forEach((posicionForm: FormGroup) => {
      const posicionFormData = posicionForm.getRawValue();

      let codigoMaterial = "", descripcionMaterial = "",
      cantidad = 0, numeroPaquete = 0, unidadPaquete = 0, pesoTeorico = 1, pesoTeoricoTotal = 1;
      let unidadMedida: UnidadMedida = null, listaUnidadesMedida: UnidadMedida[] = [];

      if(posicionFormData.selectedMaterial) {
        codigoMaterial = posicionFormData.selectedMaterial.codigo;
        descripcionMaterial = posicionFormData.selectedMaterial.descripcion;
      }

      if (posicionFormData.cantidadVenta) {
        cantidad = posicionFormData.cantidadVenta;
      }

      if(posicionFormData.selectedUnidadMedida) {
        unidadMedida = posicionFormData.selectedUnidadMedida;
        listaUnidadesMedida = posicionFormData.listaUnidadesMedida;
        if(unidadMedida.unidadMedidaBase == 'KG') {
          pesoTeorico = unidadMedida.umRez / unidadMedida.umRen;
        } else if (listaUnidadesMedida) {
          const unidadMedidaKg = listaUnidadesMedida.find(o => o.codigo == 'KG');
          pesoTeorico = unidadMedidaKg.pesoNominal;
        }

        pesoTeoricoTotal = pesoTeorico * cantidad / 1000;

        pesoTeorico = parseFloat(Utils.round(pesoTeorico, 2));
        pesoTeoricoTotal = parseFloat(Utils.round(pesoTeoricoTotal, 2));
        numeroPaquete = parseInt(Utils.round(pesoTeoricoTotal, 0));
        unidadPaquete = parseInt(Utils.round(cantidad / numeroPaquete, 0));
      }

      total += parseFloat(posicionFormData.importe);
      contadorPos += 1;

      posiciones.push({
        item: posicionFormData.item,
        idPosicion: contadorPos,
        codigoSAP: codigoMaterial,
        descripcionPedidoMaterial: descripcionMaterial,
        importe: cantidad,
        nroPaquete: numeroPaquete ? numeroPaquete : 0,
        unidadPaquete: unidadPaquete ? unidadPaquete : 0,
        unidadTotal: cantidad,
        pesoTeorico: pesoTeorico ? pesoTeorico : 0,
        pesoTeoricoTotal: pesoTeoricoTotal ? pesoTeoricoTotal : 0,
        precioCFR: posicionFormData.precioUnitario ? posicionFormData.precioUnitario : 0,
        subtotal: posicionFormData.importe ? posicionFormData.importe: 0
      });
    });

    const descripcionTotal = NumerosALetras(total);

    cabecera.descripcionPrecio = descripcionTotal.toUpperCase();

    let request : any = {};
    request.formulario = {
      cabecera: cabecera,
      posiciones: posiciones
    };
    request.usuario = this.settingsService.getFullname();
    return request;
  }

  verPdf() {
    this.settingsService.mostrarSpinner();

    const request = this.obtenerDatosGenerarPdf();

    Promise.all([
      this.cotizacionService.obtenerCotizacionPdf(request).toPromise()
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
    )
    .catch(function(err) {
      this.settingsService.ocultarSpinner();
      this.messageService.add({
        severity: "error",
        summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
        detail: JSON.stringify(err),
      });
    });
  }

  descargarPdf() {
    this.settingsService.mostrarSpinner();

    const request = this.obtenerDatosGenerarPdf();

    Promise.all([
      this.cotizacionService.obtenerCotizacionPdf(request).toPromise()
    ]).then(
      (data) => {
        this.settingsService.ocultarSpinner();
        let url = window.URL.createObjectURL(data[0]);
        let a = document.createElement('a');
        document.body.appendChild(a);
        a.setAttribute('style', 'display: none');
        a.href = url;
        a.download = this.dataCotizacion.codigoPedido + '.pdf';
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

  enviarCorreo() {
    this.settingsService.mostrarSpinner();

    const request = this.obtenerDatosGenerarPdf();

    Promise.all([
      this.cotizacionService.enviarCorreo(request).toPromise()
    ]).then(
      (data : any) => {
        this.settingsService.ocultarSpinner();
        if(data[0].cod_rpta == '1') {
          this.messageService.add({
            severity: "success",
            summary: 'Información',
            detail: 'Correo enviado correctamente',
          });
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
          detail: err,
        });
      }
    );
  }

  async onPastedFromExcel(data: string, currentPosicionForm: FormGroup) {
    const index = this.cotizacionForm.value.posiciones.findIndex(posicion => posicion.item == currentPosicionForm.value.item);
    this.posiciones.removeAt(index);

    const materiales = ConfiguracionUtil.dataMaterialToArray(data);
    materiales.forEach((material, index) => {
      const item = this.obtenerSiguienteItem();
      const posicionForm = new FormGroup({
        checked: new FormControl(false),
        item: new FormControl(item),
        codigoMaterial: new FormControl(),
        descripcionMaterial: new FormControl(),
        cantidadVenta: new FormControl(),
        unidadMedidaVenta: new FormControl(),
        pesoToneladas: new FormControl(0),
        precioUnitarioSap: new FormControl(0),
        precioUnitario: new FormControl(),
        importe: new FormControl(0),
        selectedMaterial: new FormControl(),
        listaUnidadesMedida: new FormControl([]),
        selectedUnidadMedida: new FormControl(),
        precioMaterialSap: new FormControl()
      });
      this.posiciones.push(posicionForm);

      const codigoMaterial = Utils.toCodeMaterial(material.codigo);
      const idListaPrecio = this.cotizacionForm.value.idListaPrecio;
      const fechaListaPrecio = this.cotizacionForm.value.fechaListaPrecio;

      const listaPrecio = ConfiguracionUtil.obtenerParametroxId(this.listaPrecios, idListaPrecio);

      const requestPrecio = {
        codigoMaterial: codigoMaterial,
        codigoListaPrecio: listaPrecio ? listaPrecio.codigo : "",
        fechaListaPrecio: fechaListaPrecio
      };

      Promise.all([
        this.configuracionService.obtenerMaterial(codigoMaterial).toPromise(),
        this.configuracionService.listarUnidadMedida(codigoMaterial).toPromise(),
        this.configuracionService.obtenerPrecioMaterial(requestPrecio).toPromise()
      ]).then(
        (data: any) => {
          if(data[0] != null) {
            const respMaterial = Material.toObject(data[0]);
            const listaUnidades = UnidadMedida.toArray(data[1]);
            posicionForm.controls["codigoMaterial"].patchValue(material.codigo);
            posicionForm.controls["descripcionMaterial"].patchValue(respMaterial.descripcion);
            posicionForm.controls["cantidadVenta"].patchValue(material.cantidad);
            posicionForm.controls["selectedMaterial"].patchValue(respMaterial);
            posicionForm.controls['listaUnidadesMedida'].patchValue(listaUnidades);
            posicionForm.controls['precioMaterialSap'].patchValue(data[2]);
            posicionForm.controls['precioUnitario'].patchValue(material.precio);
          }
        },
        (error) => {
          this.messageService.add({
            severity: "error",
            summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
            detail: JSON.stringify(error)
          });
        }
      );
    });
  }
}
