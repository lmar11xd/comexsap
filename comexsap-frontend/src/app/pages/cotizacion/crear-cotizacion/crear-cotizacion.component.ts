import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem, MessageService } from 'primeng/api';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { ConfiguracionService } from '../../configuracion/configuracion.sevice';
import { Cliente } from '../../configuracion/to/cliente';
import { Parametro } from '../../configuracion/to/parametro';
import { CotizacionService } from '../cotizacion.service';
import { Puerto } from '../../configuracion/to/puerto';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { Destinatario } from '../../configuracion/to/destinatario';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Material } from '../../configuracion/to/material';
import { UnidadMedida } from '../../configuracion/to/unidadmedida';
import { Constantes } from 'src/app/utils/constantes';
import { Utils } from 'src/app/utils/utils';
import { Breadcrumb2Service } from 'src/app/shared/breadcrumb2.service';
import { CREAR_COTIZACION, LISTAR_COTIZACION } from 'src/app/shared/breadcrumb/breadcrumb';
import { ConfiguracionUtil } from '../../configuracion/configuracion.util';

@Component({
  selector: 'app-crear-cotizacion',
  templateUrl: './crear-cotizacion.component.html',
  styleUrls: ['./crear-cotizacion.component.scss']
})
export class CrearCotizacionComponent implements OnInit {
  @ViewChild('modalCotizacionExitosa') modalCotizacionExitosa: ElementRef;

  active = 1;
  activeDatos = 1;
  activeContacto = 1;

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
    this.inicializarBreadcrumb();
    this.crearFormulario();
    this.inicializar();
  }

  inicializarBreadcrumb(){
    let BREADCRUMBS: MenuItem[] = [];
    BREADCRUMBS = BREADCRUMBS.concat(LISTAR_COTIZACION);
    BREADCRUMBS = BREADCRUMBS.concat(CREAR_COTIZACION);
    this.breadcrumb2Service.addBreadcrumbs(BREADCRUMBS);
  }

  inicializar() {
    this.settingsService.mostrarSpinner();
    Promise.all([
      this.configuracionService.listarParametroxDominio(Constantes.P_D034).toPromise(),//Condicion Pago Cotizacion
      this.configuracionService.listarParametroxDominio(Constantes.P_D003).toPromise(),//Incoterm
      this.configuracionService.listarParametroxDominio(Constantes.P_D010).toPromise(),//Estado
      this.configuracionService.listarParametroxDominio(Constantes.P_D006).toPromise(),//Moneda
      this.configuracionService.listarParametroxDominio(Constantes.P_D008).toPromise(),//Despacho
      this.configuracionService.listarParametroxDominio(Constantes.P_D013).toPromise(),//Lista Precios
    ]).then(
      (data :any[]) => {
        this.settingsService.ocultarSpinner();
        this.pagos = Parametro.toArray(data[0]);
        this.incoterms = Parametro.toArray(data[1]);
        this.estados = Parametro.toArray(data[2]);
        this.monedas = Parametro.toArray(data[3]);
        this.despachos = Parametro.toArray(data[4]);
        this.listaPrecios = Parametro.toArray(data[5]);
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
      codigo: new FormControl(null, null),
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
      posiciones: new FormArray([])
    });
  }

  inicializarFormulario() {
    this.cotizacionForm.controls['codigo'].patchValue('', {onlySelf: true});
    this.cotizacionForm.controls['existeClienteSap'].patchValue(true, {onlySelf: true});
    this.cotizacionForm.controls['codigoCliente'].patchValue(null, {onlySelf: true});
    this.cotizacionForm.controls['nombreCliente'].patchValue(null, {onlySelf: true});
    this.cotizacionForm.controls['codigoDestinatario'].patchValue(null, {onlySelf: true});
    this.cotizacionForm.controls['nombreDestinatario'].patchValue(null, {onlySelf: true});
    this.cotizacionForm.controls['idPuertoOrigen'].patchValue(null, {onlySelf: true});
    this.cotizacionForm.controls['idIncoterm'].patchValue(null, {onlySelf: true});
    this.cotizacionForm.controls['idIncotermComercial'].patchValue(null, {onlySelf: true});
    this.cotizacionForm.controls['importeFlete'].patchValue(null, {onlySelf: true});
    this.cotizacionForm.controls['seguroInternacional'].patchValue(null, {onlySelf: true});
    this.cotizacionForm.controls['idPuertoDestino'].patchValue(null, {onlySelf: true});
    this.cotizacionForm.controls['numeroContenedor'].patchValue(null, {onlySelf: true});
    this.cotizacionForm.controls['fechaCotizacion'].patchValue(new Date(), {onlySelf: true});
    this.cotizacionForm.controls['idTipoDespacho'].patchValue(null, {onlySelf: true});
    this.cotizacionForm.controls['idCondicionPago'].patchValue(null, {onlySelf: true});
    this.cotizacionForm.controls['idMoneda'].patchValue(Constantes.P_ID_DOLAR, {onlySelf: true});
    this.cotizacionForm.controls['fechaListaPrecio'].patchValue(new Date(), {onlySelf: true});
    this.cotizacionForm.controls['idListaPrecio'].patchValue(null, {onlySelf: true});
    this.cotizacionForm.controls['vigenciaOferta'].patchValue(true, {onlySelf: true});
    this.cotizacionForm.controls['diasOferta'].patchValue(null, {onlySelf: true});
    this.cotizacionForm.controls['tiempoEntrega'].patchValue(null, {onlySelf: true});
    this.cotizacionForm.controls['contactoNombre'].patchValue(null, {onlySelf: true});
    this.cotizacionForm.controls['contactoCargo'].patchValue(null, {onlySelf: true});
    this.cotizacionForm.controls['contactoCorreo'].patchValue(null, {onlySelf: true});
    this.cotizacionForm.controls['contactoAdicionalNombre'].patchValue(null, {onlySelf: true});
    this.cotizacionForm.controls['contactoAdicionalCargo'].patchValue(null, {onlySelf: true});
    this.cotizacionForm.controls['contactoAdicionalCorreo'].patchValue(null, {onlySelf: true});
    this.cotizacionForm.controls['observaciones'].patchValue(null, {onlySelf: true});
    this.cotizacionForm.controls['posiciones'].patchValue([], {onlySelf: true});
  }

  limpiarValidadores() {
    this.cotizacionForm.controls['codigo'].clearValidators();
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
    this.cotizacionForm.controls['codigo'].updateValueAndValidity();
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
        item: new FormControl(item),
        codigoMaterial: new FormControl(""),
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

  actualizarPrecios() {
    const idListaPrecio = this.cotizacionForm.controls["idListaPrecio"].value;
    const fechaListaPrecio = this.cotizacionForm.controls["fechaListaPrecio"].value;

    const listaPrecio = ConfiguracionUtil.obtenerParametroxId(this.listaPrecios, idListaPrecio);

    if(Utils.isNullOrEmpty(fechaListaPrecio)) {
      /*this.messageService.add({
        severity: "warn",
        summary: 'Alerta',
        detail: 'Ingrese Fecha Lista Precio'
      });*/
      return;
    }

    if(idListaPrecio == null || idListaPrecio == 0) {
      /*this.messageService.add({
        severity: "warn",
        summary: 'Alerta',
        detail: 'Seleccione una Lista de Precio'
      });*/
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
          ConfiguracionUtil.actualizarPrecioUnitarioSap(posicionForm, true);
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
      (data :any[]) => {
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
      this.configuracionService.listarUnidadMedida(codigoMaterial).toPromise(),
    ]).then(
      (data: any[]) => {
        this.settingsService.ocultarSpinner();
        posicionForm.controls['listaUnidadesMedida'].patchValue(UnidadMedida.toArray(data[0]));
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
    this.cotizacionForm.controls['codigo'].setValidators([Validators.required]);
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
    if(Utils.isNullOrEmpty(formData.codigo)) {
      this.mensajeValidacion = "Datos de Cotización >> Datos del Cliente: Ingrese un código para la cotización.";
      return false;
    } else {
      if(!Utils.isValidCodePer(formData.codigo)) {
        this.mensajeValidacion = "Datos de Cotización >> Datos del Cliente: Código de cotización no válido.";
        return false;
      }
    }

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
      this.mensajeValidacion = "Datos de Cotización >> Datos del Envío: Seleccione un Incoterm SAP.";
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

    const cotizacionFormData = this.cotizacionForm.value;
    const nombreDestinatario = this.selectedDestinatario ? this.selectedDestinatario.descripcion : "";

    const cabecera = {
      codigoPedido: cotizacionFormData.codigo.trim(),
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
        item: posicionFormData.item,
        codigoSAP: posicionFormData.codigoMaterial.replace(/^(0+)/g, ''),
        descripcionProducto: posicionFormData.selectedMaterial.descripcion,
        cantidad: posicionFormData.cantidadVenta,
        cantidadVenta: posicionFormData.cantidadVenta,
        codigoSAPUnidadMedida: codigoUnidadMedida,
        codigoSAPUnidadMedidaVenta: posicionFormData.unidadMedidaVenta,
        pesoTonelada: posicionFormData.pesoToneladas,
        precioUnitarioSAP: posicionFormData.precioUnitarioSap,
        precioUnitario: posicionFormData.precioUnitario,
        importe: posicionFormData.importe,
        pesoNominal: pesoNominal
      });
    });

    const cotizacion = {
      cabecera: cabecera,
      posiciones: posiciones
    }

    let request : any = {};
    request.formulario = cotizacion;
    request.usuario = this.settingsService.getUsername();

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

  irAlListado() {
    this.modalService.dismissAll();
    this.router.navigate(['/exportaciones/cotización/listar-cotizacion']);
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
