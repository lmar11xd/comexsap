import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import {  MenuItem, MessageService } from 'primeng/api';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { ConfiguracionService } from '../../configuracion/configuracion.sevice';
import { Cliente } from '../../configuracion/to/cliente';
import { Parametro } from '../../configuracion/to/parametro';
import { PedidoFirmeService } from '../pedidofirme.service';
import { Puerto } from '../../configuracion/to/puerto';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { Destinatario } from '../../configuracion/to/destinatario';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Material } from '../../configuracion/to/material';
import { UnidadMedida } from '../../configuracion/to/unidadmedida';
import { Utils } from 'src/app/utils/utils';
import { Breadcrumb2Service } from 'src/app/shared/breadcrumb2.service';
import { CREAR_PEDIDOFIRME, LISTAR_PEDIDOFIRME } from 'src/app/shared/breadcrumb/breadcrumb';
import { Ruta } from '../../configuracion/to/ruta';
import { Adjunto } from '../../configuracion/to/archivo';
import { ConfiguracionUtil } from '../../configuracion/configuracion.util';
import { Constantes } from 'src/app/utils/constantes';

@Component({
  selector: 'app-crear-pedido-firme',
  templateUrl: './crear-pedido-firme.component.html',
  styleUrls: ['./crear-pedido-firme.component.scss']
})
export class CrearPedidoFirmeComponent implements OnInit {
  @ViewChild('modalPedidoFirmeExitoso') modalPedidoFirmeExitoso: ElementRef;

  active = 1;
  activeDatos = 1;
  activeContacto = 1;

  //fechaActual = Utils.formatDate(new Date());
  fechaActual = new Date();

  pedidoFirmeForm: FormGroup;
  armarJuegoForm: FormGroup;

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

  selectedCliente: Cliente;
  selectedDestinatario: Destinatario;
  selectedPuertoOrigen: Puerto;
  selectedPuertoDestino: Puerto;
  selectedRuta: Ruta;

  selectedMaterialArmarJuego: Material;

  adjuntos: Adjunto[] = [];

  mensajeValidacion: string = "";

  constructor(
    private router: Router,
    private breadcrumb2Service: Breadcrumb2Service,
    private messageService: MessageService,
    private modalService: NgbModal,
    private settingsService: SettingsService,
    private configuracionService: ConfiguracionService,
    private pedidoFirmeService: PedidoFirmeService
  ) {
  }

  ngOnInit(): void {
    this.inicializarBreadcrumb();
    this.crearFormulario();
    this.inicializar();
  }

  inicializarBreadcrumb() {
    let BREADCRUMBS: MenuItem[] = [];
    BREADCRUMBS = BREADCRUMBS.concat(LISTAR_PEDIDOFIRME);
    BREADCRUMBS = BREADCRUMBS.concat(CREAR_PEDIDOFIRME);
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
    this.pedidoFirmeForm = new FormGroup({
      codigo: new FormControl(),
      selectAllPosiciones: new FormControl(),
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
      fechaPedidoFirme: new FormControl(),
      idTipoDespacho: new FormControl(),
      idCondicionPago: new FormControl(),
      idMoneda: new FormControl(),
      requiereAnticipo: new FormControl(),
      fechaListaPrecio: new FormControl(),
      idListaPrecio: new FormControl(),
      idRuta: new FormControl(),
      idLugarDespacho: new FormControl(),
      idFacturacion: new FormControl(),

      idPesoObjetivo: new FormControl(),
      condicionDescarga: new FormControl(),
      idTolerancia: new FormControl(),
      idAlmacenaje: new FormControl(),
      idPesoEtiqueta: new FormControl(),
      gastosOperativos: new FormControl(),
      fechaDespacho: new FormControl(),
      idNumeroEtiqueta: new FormControl(),
      customerName: new FormControl(),
      deal: new FormControl(),
      destinationPort: new FormControl(),
      lotNumber: new FormControl(),

      observaciones: new FormControl(),
      posiciones: new FormArray([])
    });

    this.armarJuegoForm = new FormGroup({
      codigo: new FormControl(),
      descripcion: new FormControl(),
      jerarquia: new FormControl(),
      unidadMedidaBase: new FormControl(),
      unidadMedidaVenta: new FormControl(),
      pesoBruto: new FormControl(),
      pesoNeto: new FormControl(),
    });
  }

  inicializarFormulario() {
    //Datos Pedido Firme
    this.pedidoFirmeForm.controls['codigo'].patchValue('', {onlySelf: true});
    this.pedidoFirmeForm.controls['codigoCliente'].patchValue(null, {onlySelf: true});
    this.pedidoFirmeForm.controls['codigoDestinatario'].patchValue(null, {onlySelf: true});
    this.pedidoFirmeForm.controls['idPuertoOrigen'].patchValue(null, {onlySelf: true});
    this.pedidoFirmeForm.controls['idIncoterm'].patchValue(null, {onlySelf: true});
    this.pedidoFirmeForm.controls['idIncotermComercial'].patchValue(null, {onlySelf: true});
    this.pedidoFirmeForm.controls['importeFlete'].patchValue(null, {onlySelf: true});
    this.pedidoFirmeForm.controls['seguroInternacional'].patchValue(null, {onlySelf: true});
    this.pedidoFirmeForm.controls['idPuertoDestino'].patchValue(null, {onlySelf: true});
    this.pedidoFirmeForm.controls['numeroContenedor'].patchValue(null, {onlySelf: true});
    this.pedidoFirmeForm.controls['fechaPedidoFirme'].patchValue(new Date(), {onlySelf: true});
    this.pedidoFirmeForm.controls['idTipoDespacho'].patchValue(null, {onlySelf: true});
    this.pedidoFirmeForm.controls['idCondicionPago'].patchValue(null, {onlySelf: true});
    this.pedidoFirmeForm.controls['idMoneda'].patchValue(Constantes.P_ID_DOLAR, {onlySelf: true});
    this.pedidoFirmeForm.controls['requiereAnticipo'].patchValue(false, {onlySelf: true});
    this.pedidoFirmeForm.controls['fechaListaPrecio'].patchValue(new Date(), {onlySelf: true});
    this.pedidoFirmeForm.controls['idListaPrecio'].patchValue(null, {onlySelf: true});
    this.pedidoFirmeForm.controls['idRuta'].patchValue(null, {onlySelf: true});
    this.pedidoFirmeForm.controls['idLugarDespacho'].patchValue(null, {onlySelf: true});
    this.pedidoFirmeForm.controls['idFacturacion'].patchValue(null, {onlySelf: true});
    this.pedidoFirmeForm.controls['observaciones'].patchValue(null, {onlySelf: true});
    this.pedidoFirmeForm.controls['posiciones'].patchValue([], {onlySelf: true});

    //Información
    this.pedidoFirmeForm.controls['idPesoObjetivo'].patchValue(null, {onlySelf: true});
    this.pedidoFirmeForm.controls['condicionDescarga'].patchValue(null, {onlySelf: true});
    this.pedidoFirmeForm.controls['idTolerancia'].patchValue(null, {onlySelf: true});
    this.pedidoFirmeForm.controls['idAlmacenaje'].patchValue(null, {onlySelf: true});
    this.pedidoFirmeForm.controls['idPesoEtiqueta'].patchValue(null, {onlySelf: true});
    this.pedidoFirmeForm.controls['gastosOperativos'].patchValue(null, {onlySelf: true});
    this.pedidoFirmeForm.controls['fechaDespacho'].patchValue(null, {onlySelf: true});
    this.pedidoFirmeForm.controls['idNumeroEtiqueta'].patchValue(null, {onlySelf: true});
    this.pedidoFirmeForm.controls['customerName'].patchValue(null, {onlySelf: true});
    this.pedidoFirmeForm.controls['deal'].patchValue(null, {onlySelf: true});
    this.pedidoFirmeForm.controls['destinationPort'].patchValue(null, {onlySelf: true});
    this.pedidoFirmeForm.controls['lotNumber'].patchValue(null, {onlySelf: true});

    //Armar Juego
    this.armarJuegoForm.controls['codigo'].disable();
    this.armarJuegoForm.controls['codigo'].setValidators([Validators.required]);

    this.armarJuegoForm.controls['descripcion'].disable();
    this.armarJuegoForm.controls['descripcion'].setValidators([Validators.required]);

    this.armarJuegoForm.controls['jerarquia'].disable();
    this.armarJuegoForm.controls['jerarquia'].setValidators([Validators.required]);

    this.armarJuegoForm.controls['unidadMedidaBase'].disable();
    this.armarJuegoForm.controls['unidadMedidaBase'].setValidators([Validators.required]);

    this.armarJuegoForm.controls['unidadMedidaVenta'].disable();
    this.armarJuegoForm.controls['unidadMedidaVenta'].setValidators([Validators.required]);

    this.armarJuegoForm.controls['pesoBruto'].disable();
    this.armarJuegoForm.controls['pesoBruto'].setValidators([Validators.required]);

    this.armarJuegoForm.controls['pesoNeto'].disable();
    this.armarJuegoForm.controls['pesoNeto'].setValidators([Validators.required]);

  }

  limpiarValidadores() {
    //Datos Pedido Firme
    this.pedidoFirmeForm.controls['codigo'].clearValidators();
    this.pedidoFirmeForm.controls['codigoCliente'].clearValidators();
    this.pedidoFirmeForm.controls['codigoDestinatario'].clearValidators();
    this.pedidoFirmeForm.controls['idPuertoOrigen'].clearValidators();
    this.pedidoFirmeForm.controls['idIncoterm'].clearValidators();
    this.pedidoFirmeForm.controls['idIncotermComercial'].clearValidators();
    this.pedidoFirmeForm.controls['importeFlete'].clearValidators();
    this.pedidoFirmeForm.controls['seguroInternacional'].clearValidators();
    this.pedidoFirmeForm.controls['idPuertoDestino'].clearValidators();
    this.pedidoFirmeForm.controls['numeroContenedor'].clearValidators();
    this.pedidoFirmeForm.controls['fechaPedidoFirme'].clearValidators();
    this.pedidoFirmeForm.controls['idTipoDespacho'].clearValidators();
    this.pedidoFirmeForm.controls['idCondicionPago'].clearValidators();
    this.pedidoFirmeForm.controls['idMoneda'].clearValidators();
    this.pedidoFirmeForm.controls['requiereAnticipo'].clearValidators();
    this.pedidoFirmeForm.controls['fechaListaPrecio'].clearValidators();
    this.pedidoFirmeForm.controls['idListaPrecio'].clearValidators();
    this.pedidoFirmeForm.controls['idRuta'].clearValidators();
    this.pedidoFirmeForm.controls['idLugarDespacho'].clearValidators();
    this.pedidoFirmeForm.controls['idFacturacion'].clearValidators();
    this.pedidoFirmeForm.controls['observaciones'].clearValidators();
    this.pedidoFirmeForm.controls['posiciones'].clearValidators();

    //Información
    this.pedidoFirmeForm.controls['idPesoObjetivo'].clearValidators();
    this.pedidoFirmeForm.controls['condicionDescarga'].clearValidators();
    this.pedidoFirmeForm.controls['idTolerancia'].clearValidators();
    this.pedidoFirmeForm.controls['idAlmacenaje'].clearValidators();
    this.pedidoFirmeForm.controls['idPesoEtiqueta'].clearValidators();
    this.pedidoFirmeForm.controls['gastosOperativos'].clearValidators();
    this.pedidoFirmeForm.controls['fechaDespacho'].clearValidators();
    this.pedidoFirmeForm.controls['idNumeroEtiqueta'].clearValidators();
    this.pedidoFirmeForm.controls['customerName'].clearValidators();
    this.pedidoFirmeForm.controls['deal'].clearValidators();
    this.pedidoFirmeForm.controls['destinationPort'].clearValidators();
    this.pedidoFirmeForm.controls['lotNumber'].clearValidators();
  }

  refrescarValidadores() {
    //Datos Pedido Firme
    this.pedidoFirmeForm.controls['codigo'].updateValueAndValidity();
    this.pedidoFirmeForm.controls['codigoCliente'].updateValueAndValidity();
    this.pedidoFirmeForm.controls['codigoDestinatario'].updateValueAndValidity();
    this.pedidoFirmeForm.controls['idPuertoOrigen'].updateValueAndValidity();
    this.pedidoFirmeForm.controls['idIncoterm'].updateValueAndValidity();
    this.pedidoFirmeForm.controls['idIncotermComercial'].updateValueAndValidity();
    this.pedidoFirmeForm.controls['importeFlete'].updateValueAndValidity();
    this.pedidoFirmeForm.controls['seguroInternacional'].updateValueAndValidity();
    this.pedidoFirmeForm.controls['idPuertoDestino'].updateValueAndValidity();
    this.pedidoFirmeForm.controls['numeroContenedor'].updateValueAndValidity();
    this.pedidoFirmeForm.controls['fechaPedidoFirme'].updateValueAndValidity();
    this.pedidoFirmeForm.controls['idTipoDespacho'].updateValueAndValidity();
    this.pedidoFirmeForm.controls['idCondicionPago'].updateValueAndValidity();
    this.pedidoFirmeForm.controls['idMoneda'].updateValueAndValidity();
    this.pedidoFirmeForm.controls['requiereAnticipo'].updateValueAndValidity();
    this.pedidoFirmeForm.controls['fechaListaPrecio'].updateValueAndValidity();
    this.pedidoFirmeForm.controls['idListaPrecio'].updateValueAndValidity();
    this.pedidoFirmeForm.controls['idRuta'].updateValueAndValidity();
    this.pedidoFirmeForm.controls['idLugarDespacho'].updateValueAndValidity();
    this.pedidoFirmeForm.controls['idFacturacion'].updateValueAndValidity();
    this.pedidoFirmeForm.controls['observaciones'].updateValueAndValidity();
    this.pedidoFirmeForm.controls['posiciones'].updateValueAndValidity();

    //Información
    this.pedidoFirmeForm.controls['idPesoObjetivo'].updateValueAndValidity();
    this.pedidoFirmeForm.controls['condicionDescarga'].updateValueAndValidity();
    this.pedidoFirmeForm.controls['idTolerancia'].updateValueAndValidity();
    this.pedidoFirmeForm.controls['idAlmacenaje'].updateValueAndValidity();
    this.pedidoFirmeForm.controls['idPesoEtiqueta'].updateValueAndValidity();
    this.pedidoFirmeForm.controls['gastosOperativos'].updateValueAndValidity();
    this.pedidoFirmeForm.controls['fechaDespacho'].updateValueAndValidity();
    this.pedidoFirmeForm.controls['idNumeroEtiqueta'].updateValueAndValidity();
    this.pedidoFirmeForm.controls['customerName'].updateValueAndValidity();
    this.pedidoFirmeForm.controls['deal'].updateValueAndValidity();
    this.pedidoFirmeForm.controls['destinationPort'].updateValueAndValidity();
    this.pedidoFirmeForm.controls['lotNumber'].updateValueAndValidity();
  }

  get posiciones() {
    return this.pedidoFirmeForm.controls["posiciones"] as FormArray;
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
        fechaDisponibilidad: new FormControl(),
        isLoadingPrecio: new FormControl(),
        precioUnitarioSap: new FormControl(0),
        precioUnitario: new FormControl(),
        importe: new FormControl(0),
        selectedMaterial: new FormControl(),
        listaUnidadesMedida: new FormControl([]),
        selectedUnidadMedida: new FormControl(),
        esPadre: new FormControl(false),
        componentes: new FormArray([]),
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
    const posiciones = this.pedidoFirmeForm.value.posiciones.filter(posicion => posicion.checked);
    if(posiciones.length > 0) {
      this.pedidoFirmeForm.controls['selectAllPosiciones'].patchValue(false);
      posiciones.forEach(element => {
        const index = this.pedidoFirmeForm.value.posiciones.findIndex(posicion => posicion.item == element.item);
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
    const idListaPrecio = this.pedidoFirmeForm.controls["idListaPrecio"].value;
    const fechaListaPrecio = this.pedidoFirmeForm.controls["fechaListaPrecio"].value;

    const listaPrecio = ConfiguracionUtil.obtenerParametroxId(this.listaPrecios, idListaPrecio);

    if(Utils.isNullOrEmpty(fechaListaPrecio)) {
      return;
    }

    if(idListaPrecio == null || idListaPrecio == 0) {
      return;
    }

    this.posiciones.controls.forEach((posicionForm: FormGroup) => {
      const componentes = posicionForm.controls['componentes'] as FormArray;
      const posicionData = posicionForm.getRawValue();
      const codigoMaterial = Utils.toCodeMaterial(posicionData.codigoMaterial);
      if(!Utils.isNullOrEmpty(codigoMaterial)) {
        //Actualizar Precio Componentes
        if(componentes != null) {
          componentes.controls.forEach((componenteForm: FormGroup) => {
            const componenteData = componenteForm.getRawValue();
            const codigoMaterialComp = Utils.toCodeMaterial(componenteData.codigoMaterial);

            const requestPrecioComp = {
              codigoMaterial: codigoMaterialComp,
              codigoListaPrecio: listaPrecio ? listaPrecio.codigo : "",
              fechaListaPrecio: fechaListaPrecio
            };

            this.configuracionService.obtenerPrecioMaterial(requestPrecioComp).subscribe(
              (data) => {
                componenteForm.controls['precioMaterialSap'].patchValue(data);
                ConfiguracionUtil.actualizarPrecioUnitarioSap(componenteForm, false);
              }
            );
          });
        }

        //Actualizar Precio Posicion
        const requestPrecio = {
          codigoMaterial: codigoMaterial,
          codigoListaPrecio: listaPrecio ? listaPrecio.codigo : "",
          fechaListaPrecio: fechaListaPrecio
        };
        posicionForm.controls['isLoadingPrecio'].patchValue(true);
        this.configuracionService.obtenerPrecioMaterial(requestPrecio).subscribe(
          (data) => {
            posicionForm.controls['isLoadingPrecio'].patchValue(false);
            posicionForm.controls['precioMaterialSap'].patchValue(data);
            ConfiguracionUtil.actualizarPrecioUnitarioSap(posicionForm, true);
          },
          error => {
            posicionForm.controls['isLoadingPrecio'].patchValue(false);
          }
        );
      }
    });
  }

  seleccionarCliente(cliente: Cliente) {
    this.pedidoFirmeForm.controls['codigoDestinatario'].patchValue(null);
    this.selectedDestinatario = null;
    if(cliente) {
      this.pedidoFirmeForm.controls['codigoCliente'].patchValue(cliente.codigo);
      this.buscarDestinatarios(cliente.codigo);
    } else {
      this.pedidoFirmeForm.controls['codigoCliente'].patchValue(null);
    }
  }

  seleccionarDestinatario(destinatario: Destinatario) {
    this.selectedDestinatario = destinatario;
  }

  seleccionarPuertoOrigen(puerto: Puerto) {
    this.pedidoFirmeForm.controls['idPuertoOrigen'].patchValue(puerto ? puerto.id : null);
  }

  seleccionarPuertoDestino(puerto: Puerto) {
    this.pedidoFirmeForm.controls['idPuertoDestino'].patchValue(puerto ? puerto.id : null);
  }

  seleccionarRuta(ruta: Ruta) {
    this.pedidoFirmeForm.controls['idRuta'].patchValue(ruta ? ruta.id : null);
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
    const idListaPrecio = this.pedidoFirmeForm.value.idListaPrecio;
    const fechaListaPrecio = this.pedidoFirmeForm.value.fechaListaPrecio;

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
    this.pedidoFirmeForm.controls['codigo'].setValidators([Validators.required]);
    this.pedidoFirmeForm.controls['codigoCliente'].setValidators([Validators.required]);
    this.pedidoFirmeForm.controls['idPuertoOrigen'].setValidators([Validators.required]);
    this.pedidoFirmeForm.controls['idIncoterm'].setValidators([Validators.required]);
    this.pedidoFirmeForm.controls['idIncotermComercial'].setValidators([Validators.required]);
    this.pedidoFirmeForm.controls['fechaPedidoFirme'].setValidators([Validators.required]);
    this.pedidoFirmeForm.controls['idTipoDespacho'].setValidators([Validators.required]);
    this.pedidoFirmeForm.controls['idCondicionPago'].setValidators([Validators.required]);
    this.pedidoFirmeForm.controls['idMoneda'].setValidators([Validators.required]);
    this.pedidoFirmeForm.controls['fechaListaPrecio'].setValidators([Validators.required]);
    this.pedidoFirmeForm.controls['posiciones'].setValidators([Validators.required, Validators.minLength(1)]);
    this.refrescarValidadores();
  }

  validarFormulario(): boolean {
    let formData: any = this.pedidoFirmeForm.value;

    //Datos del Cliente
    if(Utils.isNullOrEmpty(formData.codigo)) {
      this.mensajeValidacion = "Datos de Pedido Firme >> Datos del Cliente: Ingrese un código para la cotización.";
      return false;
    } else {
      if(!Utils.isValidCodePer(formData.codigo)) {
        this.mensajeValidacion = "Datos de Pedido Firme >> Datos del Cliente: Código de cotización no válido.";
        return false;
      }
    }

    if(Utils.isNullOrEmpty(formData.codigoCliente)) {
      this.mensajeValidacion = "Datos de Pedido Firme >> Datos del Cliente: Seleccione un cliente.";
      return false;
    }

    //Datos del envío
    if(formData.idPuertoOrigen == null) {
      this.mensajeValidacion = "Datos de Pedido Firme >> Datos del Envío: Seleccione un Puerto Origen.";
      return false;
    }

    if(formData.idIncoterm == null) {
      this.mensajeValidacion = "Datos de Pedido Firme >> Datos del Envío: Seleccione un Incoterm.";
      return false;
    }

    if(formData.idIncotermComercial == null) {
      this.mensajeValidacion = "Datos de Pedido Firme >> Datos del Envío: Seleccione un Incoterm Comercial.";
      return false;
    }

    if(formData.importeFlete != null && formData.importeFlete <= 0) {
      this.mensajeValidacion = "Datos de Pedido Firme >> Datos del Envío: Importe Flete no puede ser 0 o negativo.";
      return false;
    }

    if(formData.seguroInternacional != null && formData.seguroInternacional <= 0) {
      this.mensajeValidacion = "Datos de Pedido Firme >> Datos del Envío: Seguro Internacional no puede ser 0 o negativo.";
      return false;
    }

    if(formData.numeroContenedor != null && formData.numeroContenedor <= 0) {
      this.mensajeValidacion = "Datos de Pedido Firme >> Datos del Envío: Número Contenedor no puede ser 0 o negativo.";
      return false;
    }

    //Datos del pedido
    if(Utils.isNullOrEmpty(formData.fechaPedidoFirme)) {
      this.mensajeValidacion = "Datos de Pedido Firme >> Datos del Pedido: Ingrese Fecha de Pedido Firme.";
      return false;
    }

    if(formData.idTipoDespacho == null) {
      this.mensajeValidacion = "Datos de Pedido Firme >> Datos del Pedido: Seleccione un Tipo de Despacho.";
      return false;
    }

    if(formData.idCondicionPago == null) {
      this.mensajeValidacion = "Datos de Pedido Firme >> Datos del Pedido: Seleccione una Condición Pago.";
      return false;
    }

    if(formData.idMoneda == null) {
      this.mensajeValidacion = "Datos de Pedido Firme >> Datos del Pedido: Seleccione una Moneda.";
      return false;
    }

    if(Utils.isNullOrEmpty(formData.fechaListaPrecio)) {
      this.mensajeValidacion = "Datos de Pedido Firme >> Datos del Pedido: Ingrese Fecha Lista Precio.";
      return false;
    }

    if(formData.idListaPrecio == null) {
      this.mensajeValidacion = "Datos de Pedido Firme >> Datos del Pedido: Selecciona una Lista de Precio.";
      return false;
    }

    //Posiciones
    if(this.posiciones.length == 0) {
      this.mensajeValidacion = "Datos de Pedido Firme >> Datos de Posición: Necesita agregar al menos una posición.";
      return false;
    }

    if(!this.validarFormularioPosiciones()) {
      return false;
    }

    return true;
  }

  validarFormularioPosiciones(): boolean {
    let isValid = true;
    const posiciones = this.posiciones.getRawValue();
    for(var i = 0; i < posiciones.length; i++) {
      const formData = posiciones[i];

      if(Utils.isNullOrEmpty(formData.codigoMaterial)) {
        this.mensajeValidacion = "Datos de Pedido Firme >> Datos de Posición: Posición " + formData.item + ", Seleccione un material.";
        isValid = false;
        break;
      }

      if(formData.cantidadVenta == null || formData.cantidadVenta <= 0) {
        this.mensajeValidacion = "Datos de Pedido Firme >> Datos de Posición: Posición " + formData.item + ", Ingrese Cantidad Venta.";
        isValid = false;
        break;
      }

      if(Utils.isNullOrEmpty(formData.unidadMedidaVenta)) {
        this.mensajeValidacion = "Datos de Pedido Firme >> Datos de Posición: Posición " + formData.item + ", Seleccione Unidad de Medida Venta.";
        isValid = false;
        break;
      }

      if(formData.precioUnitario == null || formData.precioUnitario <= 0) {
        this.mensajeValidacion = "Datos de Pedido Firme >> Datos de Posición: Posición " + formData.item + ", Ingrese Precio Unitario.";
        isValid = false;
        break;
      }
    }
    return isValid;
  }

  async guardar(enviarCorreo: boolean) {
    this.inicializarValidaciones();
    this.pedidoFirmeForm.markAllAsTouched();

    const isValid = this.validarFormulario();
    if(!isValid) {
      this.messageService.add({
        severity: "warn",
        summary: "Revisar:",
        detail: this.mensajeValidacion
      });
      return;
    }

    //Consultar Linea Credito
    const responseLineaCredito = await this.consultarLineaCredito();
    const hayLineaCredito = ConfiguracionUtil.hayLineaCredito(responseLineaCredito);

    //Datos Pedido Firme
    const pedidoFirmeFormData = this.pedidoFirmeForm.value;
    const cabecera = {
      codigoPedido: pedidoFirmeFormData.codigo.trim(),
      codigoCliente: pedidoFirmeFormData.codigoCliente,
      nombreCliente: this.selectedCliente.descripcion,
      codigoDestinatario: pedidoFirmeFormData.codigoDestinatario ? pedidoFirmeFormData.codigoDestinatario : "",
      nombreDestinatario: this.selectedDestinatario ? this.selectedDestinatario.descripcion : "",
      idPuertoOrigen: pedidoFirmeFormData.idPuertoOrigen,
      idPuertoDestino: pedidoFirmeFormData.idPuertoDestino,
      idIncoterm: pedidoFirmeFormData.idIncoterm,
      idIncotermComercial: pedidoFirmeFormData.idIncotermComercial,
      importeFlete: pedidoFirmeFormData.importeFlete,
      seguroInternacional: pedidoFirmeFormData.seguroInternacional,
      numeroContenedor: pedidoFirmeFormData.numeroContenedor,
      fechaSolicitud: pedidoFirmeFormData.fechaPedidoFirme,
      idDespacho: pedidoFirmeFormData.idTipoDespacho,
      idCondicionPago: pedidoFirmeFormData.idCondicionPago,
      idMoneda: pedidoFirmeFormData.idMoneda,
      anticipo: pedidoFirmeFormData.requiereAnticipo ? 1 : 0,
      msjLineaCredito: hayLineaCredito ? Constantes.P_MSJ_SALDO_DISPONIBLE : Constantes.P_MSJ_SIN_SALDO,
      fechaListaPrecio: pedidoFirmeFormData.fechaListaPrecio,
      idListaPrecio: pedidoFirmeFormData.idListaPrecio,
      idRuta: pedidoFirmeFormData.idRuta,
      idLugarDespacho: pedidoFirmeFormData.idLugarDespacho,
      idFacturacion: pedidoFirmeFormData.idFacturacion,
      observacion: pedidoFirmeFormData.observaciones,
      idPesoObjetivo: pedidoFirmeFormData.idPesoObjetivo,
      condicionDescarga: pedidoFirmeFormData.condicionDescarga,
      idToleranciaProduccion: pedidoFirmeFormData.idTolerancia,
      idAlmacenaje: pedidoFirmeFormData.idAlmacenaje,
      idPesoEtiqueta: pedidoFirmeFormData.idPesoEtiqueta,
      gastosOperativos: pedidoFirmeFormData.gastosOperativos,
      fechaDespachoRequerido: pedidoFirmeFormData.fechaDespacho,
      idTipoTransporte: 1, //MARITIMO
      tipoSolicitud: 8, //PEDIDO FIRME
      idFormaPago: 1,
      enviarCorreo: enviarCorreo,
      idCarpeta: 0
    }

    let posiciones: any[] = [];
    this.posiciones.controls.forEach((posicionForm: FormGroup) => {
      let componentes: any[] = [];
      const componentesForm = posicionForm.controls['componentes'] as FormArray;
      componentesForm.controls.forEach((componenteForm: FormGroup) => {
        const componenteFormData = componenteForm.value;
        const codigoUnidadMedidaComp = Utils.isNullOrEmpty(componenteFormData.selectedMaterial.unidadVenta) ? componenteFormData.unidadMedidaVenta : componenteFormData.selectedMaterial.unidadVenta;
        const pesoNominalComp = componenteFormData.selectedUnidadMedida ? componenteFormData.selectedUnidadMedida.pesoNominal : 0;

        componentes.push({
          item: componenteFormData.item,
          codigoSAP: componenteFormData.codigoMaterial.replace(/^(0+)/g, ''),
          descripcionProducto: componenteFormData.selectedMaterial.descripcion,
          cantidad: componenteFormData.cantidadVenta,
          cantidadVenta: componenteFormData.cantidadVenta,
          codigoSAPUnidadMedida: codigoUnidadMedidaComp,
          codigoSAPUnidadMedidaVenta: componenteFormData.unidadMedidaVenta,
          pesoTonelada: componenteFormData.pesoToneladas,
          fechaDisponibilidad: Utils.stringToDate(componenteFormData.fechaDisponibilidad),
          precioUnitarioSAP: componenteFormData.precioUnitarioSap,
          precioUnitario: componenteFormData.precioUnitario,
          importe: componenteFormData.importe,
          pesoNominal: pesoNominalComp
        });
      });

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
        fechaDisponibilidad: Utils.stringToDate(posicionFormData.fechaDisponibilidad),
        precioUnitarioSAP: posicionFormData.precioUnitarioSap,
        precioUnitario: posicionFormData.precioUnitario,
        importe: posicionFormData.importe,
        pesoNominal: pesoNominal,
        subPosicion: componentes
      });
    });

    //Adjuntos
    let formData: FormData = new FormData();
    formData.append('idCarpeta', '0');
    formData.append('usuario', this.settingsService.getUsername());
    this.agregarAdjuntoFormData(formData);

    this.settingsService.mostrarSpinner();
    if (this.adjuntos != null && this.adjuntos.length > 0) {
      Promise.all([
        this.pedidoFirmeService.guardarArchivos(formData).toPromise()
      ]).then(
        (data: any) => {
          this.settingsService.ocultarSpinner();

          if(data[0].status == 200) {
            cabecera.idCarpeta = data[0].data.id;

            const pedidofirme = {
              cabecera: cabecera,
              posiciones: posiciones
            }

            let request : any = {};
            request.formulario = pedidofirme;
            request.usuario = this.settingsService.getUsername();

            this.guardarFormulario(request);
          } else {
            this.messageService.add({
              severity: "error",
              summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
              detail: JSON.stringify(data[0].message),
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
    } else {
      const pedidofirme = {
        cabecera: cabecera,
        posiciones: posiciones
      }

      let request : any = {};
      request.formulario = pedidofirme;
      request.usuario = this.settingsService.getUsername();

      this.guardarFormulario(request);
    }
  }

  guardarFormulario(request: any) {
    Promise.all([
      this.pedidoFirmeService.guardar(request).toPromise()
    ]).then(
      (data : any) => {
        this.settingsService.ocultarSpinner();
        if(data[0].cod_rpta == '1') {
          this.modalService.open(this.modalPedidoFirmeExitoso, { centered: true, backdrop: 'static', keyboard: false });
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
    this.router.navigate(['/exportaciones/pedidofirme/listar-pedidofirme']);
  }

  agregarAdjuntoFormData(formData: FormData){
    for (let i = 0; i < this.adjuntos.length; i++) {
      formData.append('adjuntos', this.adjuntos[i].file);
    }
  }

  agregarAdjunto(event) {
    const file = event.target.files[0];
    if(file != null) {
      let adjunto = new Adjunto();
      adjunto.id = 0;
      adjunto.nombre = file.name;
      adjunto.file = file;
      this.adjuntos.push(adjunto);
    }
  }

  eliminarAdjunto(adjunto: Adjunto) {
    const adjuntosFiltrados = this.adjuntos.filter(o => o.nombre != adjunto.nombre);
    this.adjuntos = adjuntosFiltrados;
  }

  mostrarModal(content: string) {
    this.modalService.open(content, { });
  }

  desactivarArmarJuego(): boolean {
    let desactivar = true;
    this.posiciones.controls.forEach((posicionForm: FormGroup) => {
      if(posicionForm.controls['checked'].value && !posicionForm.controls['esPadre'].value) { desactivar = false; }
    });

    if(desactivar) { return true; }

    if(this.validarFormularioPosiciones()) {
      return false;
    } else {
      return true;
    }
  }

  seleccionarMaterialArmarJuego(material: Material) {
    material.codigo = Utils.removeLeftZeros(material.codigo);
    this.selectedMaterialArmarJuego = material;
    this.armarJuegoForm.controls['codigo'].patchValue(material.codigo);
    this.armarJuegoForm.controls['descripcion'].patchValue(material.descripcion);
    this.armarJuegoForm.controls['jerarquia'].patchValue(material.descripcion);
    this.armarJuegoForm.controls['unidadMedidaBase'].patchValue(material.unidadBase ? material.unidadBase : "JG");
    this.armarJuegoForm.controls['unidadMedidaVenta'].patchValue(material.unidadVenta);
    this.armarJuegoForm.controls['pesoBruto'].patchValue(material.pesoNeto);
    this.armarJuegoForm.controls['pesoNeto'].patchValue(material.pesoBruto);
  }

  aceptarArmarJuego() {
    if(this.selectedMaterialArmarJuego != null) {
      let componentes: FormGroup[] = [];
      let pesoToneladas = 0, importe = 0;
      this.posiciones.controls.forEach((posicionForm: FormGroup) => {
        if(posicionForm.controls['checked'].value) {
          pesoToneladas += posicionForm.controls['pesoToneladas'].value;
          importe += posicionForm.controls['importe'].value;
          componentes.push(posicionForm);
        }
      });

      const item = componentes[0].controls['item'].value;
      const cantidadVenta = componentes[0].controls['cantidadVenta'].value;

      this.quitarPosiciones();

      let contadorComp = 0, precioUnitario = 0, itemComp = 10;
      componentes.forEach((componenteForm: FormGroup) => {
        componenteForm.controls['checked'].patchValue(false);
        componenteForm.controls['item'].patchValue(itemComp);
        if(contadorComp == 0) {
          precioUnitario += componenteForm.controls['precioUnitario'].value;
        } else {
          precioUnitario += componenteForm.controls['importe'].value / cantidadVenta;
        }
        contadorComp++;
        itemComp += 10;
      });

      const posicionForm = new FormGroup({
        checked: new FormControl(false),
        item: new FormControl(item),
        codigoMaterial: new FormControl(this.selectedMaterialArmarJuego.codigo),
        descripcionMaterial: new FormControl(this.selectedMaterialArmarJuego.descripcion),
        cantidadVenta: new FormControl(cantidadVenta),
        unidadMedidaVenta: new FormControl(this.selectedMaterialArmarJuego.unidadBase),
        pesoToneladas: new FormControl(pesoToneladas),
        fechaDisponibilidad: new FormControl(),
        isLoadingPrecio: new FormControl(),
        precioUnitarioSap: new FormControl(0),
        precioUnitario: new FormControl(precioUnitario),
        importe: new FormControl(importe),
        selectedMaterial: new FormControl(this.selectedMaterialArmarJuego),
        listaUnidadesMedida: new FormControl([]),
        selectedUnidadMedida: new FormControl(),
        esPadre: new FormControl(true),
        componentes: new FormArray(componentes),
        precioMaterialSap: new FormControl()
      });
      this.posiciones.push(posicionForm);

      this.agregarListaUnidades(posicionForm);

      this.reiniciarPosicionItem();
    }
  }

  calcularTotalImporte(): number {
    let totalImporte = 0;
    this.posiciones.controls.forEach((posicionForm: FormGroup) => {
      if(posicionForm.controls['importe'].value != null) {
        const importe = posicionForm.controls['importe'].value;
        totalImporte += importe;
      }
    })
    return totalImporte;
  }

  actualizarFormularioPosicion(event) {
  }

  async onPastedFromExcel(data: string, currentPosicionForm: FormGroup) {
    if(!Utils.hayDobleSaltoLinea(data)) return;
    const index = this.pedidoFirmeForm.value.posiciones.findIndex(posicion => posicion.item == currentPosicionForm.value.item);
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
        fechaDisponibilidad: new FormControl(),
        isLoadingPrecio: new FormControl(),
        precioUnitarioSap: new FormControl(0),
        precioUnitario: new FormControl(),
        importe: new FormControl(0),
        selectedMaterial: new FormControl(),
        listaUnidadesMedida: new FormControl([]),
        selectedUnidadMedida: new FormControl(),
        esPadre: new FormControl(false),
        componentes: new FormArray([]),
        precioMaterialSap: new FormControl()
      });
      this.posiciones.push(posicionForm);

      const codigoMaterial = Utils.toCodeMaterial(material.codigo);
      const idListaPrecio = this.pedidoFirmeForm.value.idListaPrecio;
      const fechaListaPrecio = this.pedidoFirmeForm.value.fechaListaPrecio;

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

  agregarListaUnidades(posicionForm: FormGroup) {
    const posicionData = posicionForm.getRawValue();
    const codigoMaterial = Utils.toCodeMaterial(posicionData.codigoMaterial);
    const codigoUnidad = posicionData.unidadMedidaVenta;

    Promise.all([
      this.configuracionService.listarUnidadMedida(codigoMaterial).toPromise(),
      this.configuracionService.obtenerUnidadMedidaxProducto(codigoMaterial, codigoUnidad).toPromise(),
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
          severity: "error",
          summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
          detail: JSON.stringify(err)
        });
      }
    );
  }

  consultarLineaCredito(): Promise<any> {
    const formulario = this.pedidoFirmeForm.getRawValue();
    const codigoPedido = formulario.codigo;
    const codigoCliente = formulario.codigoCliente;
    const fechaListaPrecio = formulario.fechaListaPrecio;

    let condicionPago: Parametro;
    let listaPrecio: Parametro;
    let moneda: Parametro;
    let incoterm: Parametro;

    if(formulario.idCondicionPago) {
      condicionPago = ConfiguracionUtil.obtenerParametroxId(this.pagos, formulario.idCondicionPago);
    }

    if(formulario.idMoneda) {
      moneda = ConfiguracionUtil.obtenerParametroxId(this.monedas, formulario.idMoneda);
    }

    if(formulario.idIncoterm) {
      incoterm = ConfiguracionUtil.obtenerParametroxId(this.incoterms, formulario.idIncoterm);
    }

    if(formulario.idListaPrecio) {
      listaPrecio = ConfiguracionUtil.obtenerParametroxId(this.listaPrecios, formulario.idListaPrecio);
    }

    const cabecera: any[] = [];
    cabecera.push({
      orgVentas: "2000",
      canalDist: "20",
      sector: "00",
      tipoDoc: "ZEXP",
      grupoVend: "300",
      oficina: "3006",
      condExp: "ZJ",
      fechaPref: Utils.dateYMD(new Date()),
      fechaFact: "",
      cotizacion: codigoPedido,
      zonaVentas: "300000",
      tipoPago: "T",
      centroSum: "",//Primero de las Posiciones
      fechaOccli: Utils.dateYMD(new Date()),
      motPedido: "ZVA",
      grupo: "",
      grupo1: "",
      grupoCli: "",
      condPago: condicionPago?.codigo,
      fechaDoc: Utils.dateYMD(new Date()),
      fechaPrecio: Utils.dateYMD(fechaListaPrecio),
      incoterm1: incoterm?.codigo,
      incoterm2: incoterm?.codigo,
      listaPrecio: listaPrecio?.codigo,
      moneda: moneda?.codigo,
      numDespa: "PTL",
      glosa: ""
    });

    let condiciones: any[] = [];
    let detalle: any[] = [];
    let repartos: any[] = [];
    let interlocutor: any[] = [];

    //Cliente
    interlocutor.push({
      codInte: codigoCliente,
      tipoInt: "WE"
    });

    //Destinatario
    interlocutor.push({
      codInte: codigoCliente,
      tipoInt: "AG"
    });

    //Vendedor
    interlocutor.push({
      codInte: "",
      tipoInt: "VE"
    });

    interlocutor.push({
      codInte: codigoCliente,
      tipoInt: "RE"
    });

    interlocutor.push({
      codInte: codigoCliente,
      tipoInt: "RG"
    });

    let item = 10;
    this.posiciones.controls.forEach((posicionForm: FormGroup) => {
      const posicionFormData = posicionForm.value;
      detalle.push({
        material: Utils.toCodeMaterial(posicionFormData.codigoMaterial),
        cantidad: posicionFormData.cantidadVenta,
        centro: "",
        almacen: "",
        ruta: "",
        posicion: item,
        tdesExs: '4',
        tdesFsu: '60',
        tipoPosicion: 'ZEXP',
        unidad: posicionFormData.unidadMedidaVenta,
        motRech: "",
        numDmo: "",
        numLote: "",
        numPmo: "",
        posSup: "",
        tdesLim: "",
        tipoNc: "",
      });

      repartos.push({
        cantidad: posicionFormData.cantidadVenta,
        fechaPrefe: Utils.dateYMD(new Date()),
        posnr: item
      });

      item += 10;
    });

    const data = {
      codigoCliente: codigoCliente,
      cabecera: cabecera,
      condiciones: condiciones,
      detalle: detalle,
      repartos: repartos,
      interlocutor: interlocutor,
      testrun: "X",
      textos: Constantes.P_TEXTOS_CREAR_PEDIDO_SAP,
    };

    let request : any = {};
    request.formulario = data;
    request.usuario = this.settingsService.getUsername();

    this.settingsService.mostrarSpinner();
    return new Promise((resolve, reject) => {
      this.pedidoFirmeService.consultarLineaCreadito(request)
        .subscribe({
          next: (data) => {this.settingsService.ocultarSpinner(); resolve(data);},
          error: (err) => {this.settingsService.ocultarSpinner(); reject(err);}
        })
    });
  }
}
