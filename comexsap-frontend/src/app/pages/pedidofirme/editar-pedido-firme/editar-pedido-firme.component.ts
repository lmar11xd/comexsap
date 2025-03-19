import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
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
import { EDITAR_PEDIDOFIRME, LISTAR_PEDIDOFIRME } from 'src/app/shared/breadcrumb/breadcrumb';
import { PedidoFirme } from '../to/pedidofirme';
import { PedidoFirmePosicion } from '../to/pedidofirmeposicion';
import { Ruta } from '../../configuracion/to/ruta';
import { Adjunto } from '../../configuracion/to/archivo';
import { ConfiguracionUtil } from '../../configuracion/configuracion.util';
import { Constantes } from 'src/app/utils/constantes';
import { NumerosALetras } from 'src/app/utils/numeroaletras';

@Component({
  selector: 'app-editar-pedido-firme',
  templateUrl: './editar-pedido-firme.component.html',
  styleUrls: ['./editar-pedido-firme.component.scss']
})
export class EditarPedidoFirmeComponent implements OnInit {
  @ViewChild('modalPedidoFirmeExitoso') modalPedidoFirmeExitoso: ElementRef;
  @ViewChild('modalConfirmacionExitosa') modalConfirmacionExitosa: ElementRef;
  @ViewChild('modalVerCorreo') modalVerCorreo: ElementRef;
  @ViewChild('modalVerPdf') modalVerPdf: ElementRef;

  pdfSrc: Uint8Array;

  fechaActual = new Date();

  active = 1;
  activeDatos = 1;
  activeContacto = 1;

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

  idPedido: string = null;

  dataPedido: PedidoFirme;
  dataPosiciones: PedidoFirmePosicion[] = [];
  posicionesEliminadas: PedidoFirmePosicion[] = [];

  htmlCorreo: string = "";

  selectedMaterialArmarJuego: Material;

  adjuntos: Adjunto[] = [];

  mensajeValidacion: string = "";

  esPerfilFechaDisponibilidad: boolean = false;

  constructor(
    private activatedRoute: ActivatedRoute,
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
    this.esPerfilFechaDisponibilidad = this.settingsService.existeRol(Constantes.P_ROL_FECHA_DISPONIBILIDAD);
    this.idPedido = this.activatedRoute.snapshot.paramMap.get("id");
    this.inicializarBreadcrumb(this.idPedido);
    this.crearFormulario();
    this.inicializar();
  }

  inicializarBreadcrumb(id: string){
    EDITAR_PEDIDOFIRME[0].url = EDITAR_PEDIDOFIRME[0].url.replace(':id', id);
    let BREADCRUMBS: MenuItem[] = [];
    BREADCRUMBS = BREADCRUMBS.concat(LISTAR_PEDIDOFIRME);
    BREADCRUMBS = BREADCRUMBS.concat(EDITAR_PEDIDOFIRME);
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

      this.pedidoFirmeService.obtener(this.idPedido).toPromise()
    ]).then(
      (data: any[]) => {
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

        this.dataPedido = data[13].pedido;
        this.dataPosiciones = data[13].posiciones;

        if(!Utils.isNullOrEmpty(this.dataPedido.codigoCliente)) {
          this.selectedCliente = new Cliente(
            this.dataPedido.codigoCliente,
            this.dataPedido.nombreCliente,
            ''
          );

          Promise.all([
            this.configuracionService.listarDestinatarios(this.dataPedido.codigoCliente).toPromise(),
            this.pedidoFirmeService.obtenerArchivosxCarpeta(this.dataPedido.idCarpeta).toPromise()
          ]).then(
            (data : any[]) => {
              this.destinatarios = Destinatario.toArray(data[0]);
              this.adjuntos = data[1];

              if(!Utils.isNullOrEmpty(this.dataPedido.codigoDestinatario)){
                this.selectedDestinatario = new Destinatario(
                  this.dataPedido.codigoDestinatario,
                  this.dataPedido.codigoCliente,
                  this.dataPedido.nombreDestinatario
                )
              }
            },
            (err) => {
              this.destinatarios = [];
            }
          );
        }

        this.selectedPuertoOrigen = new Puerto(
          this.dataPedido.idPaisPuertoOrigen,
          this.dataPedido.codigoPuertoOrigen,
          this.dataPedido.nombrePuertoOrigen,
          "PE",
          this.dataPedido.nombrePaisPuertoOrigen
        )

        if(this.dataPedido.idPuertoDestino > 0) {
          this.selectedPuertoDestino = new Puerto(
            this.dataPedido.idPaisPuertoDestino,
            this.dataPedido.codigoPuertoDestino,
            this.dataPedido.nombrePuertoDestino,
            "PE",
            this.dataPedido.nombrePaisPuertoDestino
          )
        }

        if(this.dataPosiciones != null) {
          this.dataPosiciones.forEach((posicion: PedidoFirmePosicion) => {
            let componentes: FormGroup[] = [];
            if(posicion.subPosicion != null) {
              posicion.subPosicion.forEach((componente: any) => {
                const materialComp = new Material(componente.codigoSAP, componente.descripcionProducto, componente.codigoSAPUnidadMedida, componente.codigoSAPUnidadMedida, 0, 0);
                const componenteForm = new FormGroup({
                  checked: new FormControl(false),
                  id: new FormControl(componente.id),
                  idPadre: new FormControl(componente.idPadre),
                  item: new FormControl(componente.item),
                  codigoMaterial: new FormControl(componente.codigoSAP),
                  descripcionMaterial: new FormControl(componente.descripcionProducto),
                  cantidadVenta: new FormControl(componente.cantidadVenta),
                  unidadMedidaVenta: new FormControl(componente.codigoSAPUnidadMedidaVenta),
                  pesoToneladas: new FormControl(componente.pesoTonelada),
                  fechaDisponibilidad: new FormControl(Utils.dateTimeToStringDate(componente.fechaDisponibilidad)),
                  precioUnitarioSap: new FormControl(componente.precioUnitarioSAP),
                  precioUnitario: new FormControl(componente.precioUnitario),
                  importe: new FormControl(componente.importe),
                  selectedMaterial: new FormControl(materialComp),
                  listaUnidadesMedida: new FormControl([]),
                  selectedUnidadMedida: new FormControl([]),
                  precioMaterialSap: new FormControl()
                });
                componentes.push(componenteForm);

                this.agregarListaUnidades(componenteForm);
              });
            }

            const material = new Material(posicion.codigoSAP, posicion.descripcionProducto, posicion.codigoSAPUnidadMedida, posicion.codigoSAPUnidadMedida, 0, 0);
            const posicionForm = new FormGroup({
              checked: new FormControl(false),
              id: new FormControl(posicion.id),
              idPadre: new FormControl(posicion.idPadre),
              item: new FormControl(posicion.item),
              codigoMaterial: new FormControl(posicion.codigoSAP),
              descripcionMaterial: new FormControl(posicion.descripcionProducto),
              cantidadVenta: new FormControl(posicion.cantidadVenta),
              unidadMedidaVenta: new FormControl(posicion.codigoSAPUnidadMedidaVenta),
              pesoToneladas: new FormControl(posicion.pesoTonelada),
              fechaDisponibilidad: new FormControl(Utils.dateTimeToStringDate(posicion.fechaDisponibilidad)),
              isLoadingPrecio: new FormControl(),
              precioUnitarioSap: new FormControl(posicion.precioUnitarioSAP),
              precioUnitario: new FormControl(posicion.precioUnitario),
              importe: new FormControl(posicion.importe),
              selectedMaterial: new FormControl(material),
              listaUnidadesMedida: new FormControl([]),
              selectedUnidadMedida: new FormControl([]),
              esPadre: new FormControl(posicion.subPosicion != null && posicion.subPosicion.length > 0),
              componentes: new FormArray(componentes),
              precioMaterialSap: new FormControl()
            });
            this.posiciones.push(posicionForm);

            this.agregarListaUnidades(posicionForm);
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
      observaciones: new FormControl(),
      posiciones: new FormArray([]),

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

      estadoDocumento: new FormControl()
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
    if(this.dataPedido != null) {
      //Datos Pedido Firme
      this.pedidoFirmeForm.controls['codigo'].patchValue(this.dataPedido.codigoPedido, {onlySelf: true});
      this.pedidoFirmeForm.controls['codigo'].disable();
      this.pedidoFirmeForm.controls['codigo'].setValidators([Validators.required]);

      this.pedidoFirmeForm.controls['codigoCliente'].patchValue(this.dataPedido.codigoCliente, {onlySelf: true});
      this.pedidoFirmeForm.controls['codigoDestinatario'].patchValue(this.dataPedido.codigoDestinatario, {onlySelf: true});
      this.pedidoFirmeForm.controls['idPuertoOrigen'].patchValue(this.dataPedido.idPuertoOrigen, {onlySelf: true});
      this.pedidoFirmeForm.controls['idIncoterm'].patchValue(this.dataPedido.idIncoterm, {onlySelf: true});
      this.pedidoFirmeForm.controls['idIncotermComercial'].patchValue(this.dataPedido.idIncotermComercial == 0 ? null : this.dataPedido.idIncotermComercial, {onlySelf: true});
      this.pedidoFirmeForm.controls['importeFlete'].patchValue(this.dataPedido.importeFlete == 0 ? null : this.dataPedido.importeFlete, {onlySelf: true});
      this.pedidoFirmeForm.controls['seguroInternacional'].patchValue(this.dataPedido.seguroInternacional == 0 ? null : this.dataPedido.seguroInternacional, {onlySelf: true});
      this.pedidoFirmeForm.controls['idPuertoDestino'].patchValue(this.dataPedido.idPuertoDestino, {onlySelf: true});
      this.pedidoFirmeForm.controls['numeroContenedor'].patchValue(this.dataPedido.numeroContenedor == 0 ? null : this.dataPedido.numeroContenedor, {onlySelf: true});
      this.pedidoFirmeForm.controls['fechaPedidoFirme'].patchValue(Utils.stringToDate(this.dataPedido.fechaSolicitud), {onlySelf: true});
      this.pedidoFirmeForm.controls['idTipoDespacho'].patchValue(this.dataPedido.idDespacho == 0 ? null : this.dataPedido.idDespacho, {onlySelf: true});
      this.pedidoFirmeForm.controls['idCondicionPago'].patchValue(this.dataPedido.idCondicionPago == 0 ? null : this.dataPedido.idCondicionPago, {onlySelf: true});
      this.pedidoFirmeForm.controls['idMoneda'].patchValue(this.dataPedido.idMoneda, {onlySelf: true});
      this.pedidoFirmeForm.controls['requiereAnticipo'].patchValue(this.dataPedido.anticipo == 1 ? true : false, {onlySelf: true});
      this.pedidoFirmeForm.controls['fechaListaPrecio'].patchValue(Utils.stringToDate(this.dataPedido.fechaListaPrecio), {onlySelf: true});
      this.pedidoFirmeForm.controls['idListaPrecio'].patchValue(this.dataPedido.idListaPrecio == 0 ? null : this.dataPedido.idListaPrecio, {onlySelf: true});
      this.pedidoFirmeForm.controls['idRuta'].patchValue(this.dataPedido.idRuta == 0 ? null : this.dataPedido.idRuta, {onlySelf: true});
      this.pedidoFirmeForm.controls['idLugarDespacho'].patchValue(this.dataPedido.idLugarDespacho == 0 ? null : this.dataPedido.idLugarDespacho, {onlySelf: true});
      this.pedidoFirmeForm.controls['idFacturacion'].patchValue(this.dataPedido.idFacturacion == 0 ? null : this.dataPedido.idFacturacion, {onlySelf: true});
      this.pedidoFirmeForm.controls['observaciones'].patchValue(this.dataPedido.observacion, {onlySelf: true});
      this.pedidoFirmeForm.controls['posiciones'].patchValue([], {onlySelf: true});
      this.pedidoFirmeForm.controls['estadoDocumento'].patchValue(this.dataPedido.idEstadoDocumento, {onlySelf: true});

      //Información
      this.pedidoFirmeForm.controls['idPesoObjetivo'].patchValue(this.dataPedido.idPesoObjetivo == 0 ? null : this.dataPedido.idPesoObjetivo, {onlySelf: true});
      this.pedidoFirmeForm.controls['condicionDescarga'].patchValue(this.dataPedido.condicionDescarga, {onlySelf: true});
      this.pedidoFirmeForm.controls['idTolerancia'].patchValue(this.dataPedido.idToleranciaProduccion == 0 ? null : this.dataPedido.idToleranciaProduccion, {onlySelf: true});
      this.pedidoFirmeForm.controls['idAlmacenaje'].patchValue(this.dataPedido.idAlmacenaje == 0 ? null : this.dataPedido.idAlmacenaje, {onlySelf: true});
      this.pedidoFirmeForm.controls['idPesoEtiqueta'].patchValue(this.dataPedido.idPesoEtiqueta == 0 ? null : this.dataPedido.idPesoEtiqueta, {onlySelf: true});
      this.pedidoFirmeForm.controls['gastosOperativos'].patchValue(this.dataPedido.gastosOperativos, {onlySelf: true});
      this.pedidoFirmeForm.controls['fechaDespacho'].patchValue(Utils.stringToDate(this.dataPedido.fechaDespachoRequerido), {onlySelf: true});
      this.pedidoFirmeForm.controls['idNumeroEtiqueta'].patchValue(this.dataPedido.idNumeroEtiqueta == 0 ? null : this.dataPedido.idNumeroEtiqueta, {onlySelf: true});
      this.pedidoFirmeForm.controls['customerName'].patchValue(this.dataPedido.customerName, {onlySelf: true});
      this.pedidoFirmeForm.controls['deal'].patchValue(this.dataPedido.deal, {onlySelf: true});
      this.pedidoFirmeForm.controls['destinationPort'].patchValue(this.dataPedido.destinationPort, {onlySelf: true});
      this.pedidoFirmeForm.controls['lotNumber'].patchValue(this.dataPedido.lote, {onlySelf: true});

      if(this.dataPedido.idEstadoDocumento == 6 || this.esPerfilFechaDisponibilidad) {
        //Datos Pedido Firme
        this.pedidoFirmeForm.controls['codigoCliente'].disable();
        this.pedidoFirmeForm.controls['codigoDestinatario'].disable();
        this.pedidoFirmeForm.controls['idPuertoOrigen'].disable();
        this.pedidoFirmeForm.controls['idIncoterm'].disable();
        this.pedidoFirmeForm.controls['idIncotermComercial'].disable();
        this.pedidoFirmeForm.controls['importeFlete'].disable();
        this.pedidoFirmeForm.controls['seguroInternacional'].disable();
        this.pedidoFirmeForm.controls['idPuertoDestino'].disable();
        this.pedidoFirmeForm.controls['numeroContenedor'].disable();
        this.pedidoFirmeForm.controls['fechaPedidoFirme'].disable();
        this.pedidoFirmeForm.controls['idTipoDespacho'].disable();
        this.pedidoFirmeForm.controls['idCondicionPago'].disable();
        this.pedidoFirmeForm.controls['idMoneda'].disable();
        this.pedidoFirmeForm.controls['requiereAnticipo'].disable();
        this.pedidoFirmeForm.controls['fechaListaPrecio'].disable();
        this.pedidoFirmeForm.controls['idListaPrecio'].disable();
        this.pedidoFirmeForm.controls['idRuta'].disable();
        this.pedidoFirmeForm.controls['idLugarDespacho'].disable();
        this.pedidoFirmeForm.controls['idFacturacion'].disable();
        this.pedidoFirmeForm.controls['observaciones'].disable();
        if(this.dataPedido.idEstadoDocumento == 6) this.pedidoFirmeForm.controls['posiciones'].disable();
        this.pedidoFirmeForm.controls['estadoDocumento'].disable();

        //Información
        this.pedidoFirmeForm.controls['idPesoObjetivo'].disable();
        this.pedidoFirmeForm.controls['condicionDescarga'].disable();
        this.pedidoFirmeForm.controls['idTolerancia'].disable();
        this.pedidoFirmeForm.controls['idAlmacenaje'].disable();
        this.pedidoFirmeForm.controls['idPesoEtiqueta'].disable();
        this.pedidoFirmeForm.controls['gastosOperativos'].disable();
        this.pedidoFirmeForm.controls['fechaDespacho'].disable();
        this.pedidoFirmeForm.controls['idNumeroEtiqueta'].disable();
        this.pedidoFirmeForm.controls['customerName'].disable();
        this.pedidoFirmeForm.controls['deal'].disable();
        this.pedidoFirmeForm.controls['destinationPort'].disable();
        this.pedidoFirmeForm.controls['lotNumber'].disable();

        //Datos Pedido Firme
        this.pedidoFirmeForm.controls['codigoCliente'].setValidators([Validators.required]);
        this.pedidoFirmeForm.controls['codigoDestinatario'].setValidators([Validators.required]);
        this.pedidoFirmeForm.controls['idPuertoOrigen'].setValidators([Validators.required]);
        this.pedidoFirmeForm.controls['idIncoterm'].setValidators([Validators.required]);
        this.pedidoFirmeForm.controls['idIncotermComercial'].setValidators([Validators.required]);
        this.pedidoFirmeForm.controls['importeFlete'].setValidators([Validators.required]);
        this.pedidoFirmeForm.controls['seguroInternacional'].setValidators([Validators.required]);
        this.pedidoFirmeForm.controls['idPuertoDestino'].setValidators([Validators.required]);
        this.pedidoFirmeForm.controls['numeroContenedor'].setValidators([Validators.required]);
        this.pedidoFirmeForm.controls['fechaPedidoFirme'].setValidators([Validators.required]);
        this.pedidoFirmeForm.controls['idTipoDespacho'].setValidators([Validators.required]);
        this.pedidoFirmeForm.controls['idCondicionPago'].setValidators([Validators.required]);
        this.pedidoFirmeForm.controls['idMoneda'].setValidators([Validators.required]);
        this.pedidoFirmeForm.controls['requiereAnticipo'].setValidators([Validators.required]);
        this.pedidoFirmeForm.controls['fechaListaPrecio'].setValidators([Validators.required]);
        this.pedidoFirmeForm.controls['idListaPrecio'].setValidators([Validators.required]);
        this.pedidoFirmeForm.controls['idRuta'].setValidators([Validators.required]);
        this.pedidoFirmeForm.controls['idLugarDespacho'].setValidators([Validators.required]);
        this.pedidoFirmeForm.controls['idFacturacion'].setValidators([Validators.required]);
        this.pedidoFirmeForm.controls['observaciones'].setValidators([Validators.required]);
        this.pedidoFirmeForm.controls['posiciones'].setValidators([Validators.required]);
        this.pedidoFirmeForm.controls['estadoDocumento'].setValidators([Validators.required]);

        //Información
        this.pedidoFirmeForm.controls['idPesoObjetivo'].setValidators([Validators.required]);
        this.pedidoFirmeForm.controls['condicionDescarga'].setValidators([Validators.required]);
        this.pedidoFirmeForm.controls['idTolerancia'].setValidators([Validators.required]);
        this.pedidoFirmeForm.controls['idAlmacenaje'].setValidators([Validators.required]);
        this.pedidoFirmeForm.controls['idPesoEtiqueta'].setValidators([Validators.required]);
        this.pedidoFirmeForm.controls['gastosOperativos'].setValidators([Validators.required]);
        this.pedidoFirmeForm.controls['fechaDespacho'].setValidators([Validators.required]);
        this.pedidoFirmeForm.controls['idNumeroEtiqueta'].setValidators([Validators.required]);
        this.pedidoFirmeForm.controls['customerName'].setValidators([Validators.required]);
        this.pedidoFirmeForm.controls['deal'].setValidators([Validators.required]);
        this.pedidoFirmeForm.controls['destinationPort'].setValidators([Validators.required]);
        this.pedidoFirmeForm.controls['lotNumber'].setValidators([Validators.required]);
      }

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

      this.actualizarPrecios(false);
    }
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
    if(this.validarFormularioPosiciones(false)) {
      this.desmarcarPosiciones();
      const item = this.obtenerSiguienteItem();
      const posicionForm = new FormGroup({
        checked: new FormControl(false),
        id: new FormControl(0),
        idPadre: new FormControl(0),
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
    const idListaPrecio = this.pedidoFirmeForm.controls["idListaPrecio"].value;
    const fechaListaPrecio = this.pedidoFirmeForm.controls["fechaListaPrecio"].value;

    if(Utils.isNullOrEmpty(fechaListaPrecio)) {
      return;
    }

    if(idListaPrecio == null || idListaPrecio == 0) {
      return;
    }

    const listaPrecio = ConfiguracionUtil.obtenerParametroxId(this.listaPrecios, idListaPrecio);

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
                ConfiguracionUtil.actualizarPrecioUnitarioSap(componenteForm, actualizarPrecio);
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
            ConfiguracionUtil.actualizarPrecioUnitarioSap(posicionForm, actualizarPrecio);
          },
          error => {
            posicionForm.controls['isLoadingPrecio'].patchValue(false);
            this.messageService.add({
              severity: "error",
              summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
              detail: JSON.stringify(error.message),
            });
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

  validarFormulario(confirmar: boolean): boolean {
    let formData: any = this.pedidoFirmeForm.value;

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

    if(!this.validarFormularioPosiciones(confirmar)) {
      return false;
    }

    return true;
  }

  validarFormularioPosiciones(confirmar: boolean): boolean {
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

      if(confirmar) {
        if(formData.precioUnitarioSap == null || formData.precioUnitarioSap <= 0) {
          this.mensajeValidacion = "Datos de Pedido Firme >> Datos de Posición: Posición " + formData.item + ", No tiene Precio Unitario SAP.";
          isValid = false;
          break;
        }

        if(Utils.isNullOrEmpty(formData.fechaDisponibilidad)) {
          this.mensajeValidacion = "Datos de Pedido Firme >> Datos de Posición: Posición " + formData.item + ", Ingrese Fecha Disponibilidad.";
          isValid = false;
          break;
        }
      }
    }

    return isValid;
  }

  obtenerDatosFormulario(enviarCorreo: boolean) {
    const pedidoFirmeFormData = this.pedidoFirmeForm.value;
    const cabecera = {
      id: this.dataPedido.id,
      codigoPedido: this.dataPedido.codigoPedido,
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
      msjLineaCredito: '',
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
      idCarpeta: this.dataPedido.idCarpeta
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
          id: componenteFormData.id,
          idPadre: componenteFormData.idPadre,
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

      const posicionFormData = posicionForm.getRawValue();
      const codigoUnidadMedida = Utils.isNullOrEmpty(posicionFormData.selectedMaterial.unidadVenta) ? posicionFormData.unidadMedidaVenta : posicionFormData.selectedMaterial.unidadVenta;
      const pesoNominal = posicionFormData.selectedUnidadMedida ? posicionFormData.selectedUnidadMedida.pesoNominal : 0;

      posiciones.push({
        id: posicionFormData.id,
        item: posicionFormData.item,
        codigoSAP: posicionFormData.codigoMaterial.replace(/^(0+)/g, ''),
        descripcionProducto: posicionFormData.selectedMaterial.descripcion,
        //cantidad: posicionFormData.cantidadVenta,
        cantidadVenta: posicionFormData.cantidadVenta,
        codigoSAPUnidadMedida: codigoUnidadMedida,
        codigoSAPUnidadMedidaVenta: posicionFormData.unidadMedidaVenta,
        pesoTonelada: posicionFormData.pesoToneladas,
        fechaDisponibilidad: Utils.stringToDate(posicionFormData.fechaDisponibilidad),
        precioUnitarioSAP: posicionFormData.precioUnitarioSap,
        precioUnitario: posicionFormData.precioUnitario,
        importe: posicionFormData.importe,
        estado: 1,
        pesoNominal: pesoNominal,
        subPosicion: componentes
      });
    });

    this.posicionesEliminadas.forEach((posicion) => {
      posiciones.push({id: posicion.id, estado: 0});
    });

    return { cabecera: cabecera, posiciones: posiciones }
  }

  async confirmar() {
    this.inicializarValidaciones();
    this.pedidoFirmeForm.markAllAsTouched();

    const isValid = this.validarFormulario(true);
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
    let pedidoFirme = this.obtenerDatosFormulario(false);
    pedidoFirme.cabecera.msjLineaCredito = hayLineaCredito ? Constantes.P_MSJ_SALDO_DISPONIBLE : Constantes.P_MSJ_SIN_SALDO;

    const carpetaInfo = {
      id: this.dataPedido.idCarpeta,
      idPedido: this.dataPedido.id,
      nombre: this.dataPedido.codigoPedido,
      usuario: this.settingsService.getUsername()
    }

    //Adjuntos
    let formData: FormData = new FormData();
    formData.append('carpetaInfo', JSON.stringify(carpetaInfo));
    this.agregarAdjuntoFormData(formData);

    this.settingsService.mostrarSpinner();
    if (this.hayAdjuntos()) {
      Promise.all([
        this.pedidoFirmeService.guardarArchivos(formData).toPromise()
      ]).then(
        (data: any) => {
          this.settingsService.ocultarSpinner();
          if(data[0].status == 200) {
            pedidoFirme.cabecera.idCarpeta = data[0].data.id;

            let request : any = {};
            request.formulario = pedidoFirme;
            request.usuario = this.settingsService.getUsername();
            this.confirmarFormulario(request);
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
      let request : any = {};
      request.formulario = pedidoFirme;
      request.usuario = this.settingsService.getUsername();

      this.confirmarFormulario(request);
    }
  }

  async guardar(enviarCorreo: boolean) {
    this.inicializarValidaciones();
    this.pedidoFirmeForm.markAllAsTouched();

    const isValid = this.validarFormulario(false);
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
    let pedidoFirme = this.obtenerDatosFormulario(enviarCorreo);
    pedidoFirme.cabecera.msjLineaCredito = hayLineaCredito ? Constantes.P_MSJ_SALDO_DISPONIBLE : Constantes.P_MSJ_SIN_SALDO;

    const carpetaInfo = {
      id: this.dataPedido.idCarpeta,
      idPedido: this.dataPedido.id,
      nombre: this.dataPedido.codigoPedido,
      usuario: this.settingsService.getUsername()
    }

    //Adjuntos
    let formData: FormData = new FormData();
    formData.append('carpetaInfo', JSON.stringify(carpetaInfo));

    this.agregarAdjuntoFormData(formData);

    this.settingsService.mostrarSpinner();
    if (this.hayAdjuntos()) {
      Promise.all([
        this.pedidoFirmeService.guardarArchivos(formData).toPromise()
      ]).then(
        (data: any) => {
          this.settingsService.ocultarSpinner();
          if(data[0].status == 200) {
            pedidoFirme.cabecera.idCarpeta = data[0].data.id;

            let request : any = {};
            request.formulario = pedidoFirme;
            request.usuario = this.settingsService.getUsername();

            this.guardarFormulario(request);
          } else {
            this.messageService.add({
              severity: "error",
              summary: "Error",
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
      let request : any = {};
      request.formulario = pedidoFirme;
      request.usuario = this.settingsService.getUsername();

      this.guardarFormulario(request);
    }
  }

  confirmarFormulario(request: any) {
    Promise.all([
      this.pedidoFirmeService.confirmar(request).toPromise()
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

  modificarDocumento() {
    const cabecera = { id: this.dataPedido.id };
    let request : any = {};
    request.formulario = { cabecera: cabecera };
    request.usuario = this.settingsService.getUsername();

    Promise.all([
      this.pedidoFirmeService.modificarDocumento(request).toPromise()
    ]).then(
      (data : any) => {
        this.settingsService.ocultarSpinner();
        if(data[0].cod_rpta == '1') {
          this.messageService.add({
            severity: "success",
            summary: "Información",
            detail: "Ya puedes modificar el documento",
          });
          this.ngOnInit();
        } else {
          this.messageService.add({
            severity: "warn",
            summary: 'Validación',
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
    this.router.navigate(['/exportaciones/pedidofirme/listar-pedidofirme']);
  }

  async verCorreo() {
    //Consultar Linea Credito
    const responseLineaCredito = await this.consultarLineaCredito();
    const hayLineaCredito = ConfiguracionUtil.hayLineaCredito(responseLineaCredito);
    let msjLineaCredito = hayLineaCredito ? Constantes.P_MSJ_SALDO_DISPONIBLE : Constantes.P_MSJ_SIN_SALDO;

    this.settingsService.mostrarSpinner();
    Promise.all([
      this.pedidoFirmeService.verCorreo({id: this.idPedido, msjLineaCredito: msjLineaCredito}).toPromise()
    ]).then(
      (data : any) => {
        this.settingsService.ocultarSpinner();
        if(data[0].cod_rpta == '1') {
          this.htmlCorreo = data[0].html;
          this.modalService.open(this.modalVerCorreo, { centered: true, size: 'lg', backdrop: 'static', keyboard: false });
        } else {
          this.messageService.add({
            severity: "warn",
            summary: 'Alerta',
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

  enviarCorreoActualizacionPrecios() {
    this.settingsService.mostrarSpinner();
    Promise.all([
      this.pedidoFirmeService.enviarCorreoActualizacionPrecios(this.idPedido).toPromise()
    ]).then(
      (data : any) => {
        this.settingsService.ocultarSpinner();
        if(data[0].cod_rpta == 1) {
          this.messageService.add({
            severity: "success",
            summary: "Correo enviado",
            detail: "Actualización de precios enviada.",
          });
        } else {
          this.messageService.add({
            severity: "warn",
            summary: "Correo no enviado",
            detail: data[0].rpta,
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

  enviarCorreoFechasDisponibilidad() {
    this.settingsService.mostrarSpinner();
    Promise.all([
      this.pedidoFirmeService.enviarCorreoFechasDisponibilidad(this.idPedido).toPromise()
    ]).then(
      (data : any) => {
        this.settingsService.ocultarSpinner();
        if(data[0].cod_rpta == 1) {
          this.messageService.add({
            severity: "success",
            summary: "Correo enviado",
            detail: "Fechas de disponibilidad enviadas.",
          });
        } else {
          this.messageService.add({
            severity: "warn",
            summary: "Correo no enviado",
            detail: data[0].rpta.replace("java.lang.RuntimeException: ", "").replace("java.lang.Exception: ", ""),
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

  agregarAdjuntoFormData(formData: FormData){
    if(this.adjuntos != null) {
      this.adjuntos.forEach((adjunto: Adjunto) => {
        if(adjunto.file != null) {
          formData.append('adjuntos', adjunto.file);
        }
      });
    }
  }

  hayAdjuntos(): boolean {
    let hayAdjuntos = false;
    if(this.adjuntos != null) {
      this.adjuntos.forEach((adjunto: Adjunto) => {
        if(adjunto.file != null) {
          hayAdjuntos = true;
        }
      });
    }
    return hayAdjuntos;
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

  descargarAdjunto(adjunto: Adjunto) {
    if(adjunto.id == 0) {
      //Nada
    } else {
      this.settingsService.mostrarSpinner();
      Promise.all([
        this.pedidoFirmeService.obtenerArchivo(adjunto.url).toPromise()
      ]).then(
        (data) => {
          this.settingsService.ocultarSpinner();
          let url = window.URL.createObjectURL(data[0]);
          let a = document.createElement('a');
          document.body.appendChild(a);
          a.setAttribute('style', 'display: none');
          a.href = url;
          a.download = adjunto.nombre;
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

  eliminarAdjunto(adjunto: Adjunto) {
    if(adjunto.id == 0){
      const adjuntosFiltrados = this.adjuntos.filter(o => o.nombre != adjunto.nombre);
      this.adjuntos = adjuntosFiltrados;
    } else {
      this.settingsService.mostrarSpinner();
      Promise.all([
        this.pedidoFirmeService.eliminarArchivo(adjunto.id, this.settingsService.getUsername()).toPromise()
      ]).then(
        (data : any[]) => {
          this.settingsService.ocultarSpinner();
          if(data[0].status == 200) {
            const adjuntosFiltrados = this.adjuntos.filter(o => o.nombre != adjunto.nombre);
            this.adjuntos = adjuntosFiltrados;
            this.messageService.add({
              severity: "success",
              summary: 'Eliminar Archivo',
              detail: "Archivo eliminado del Servidor"
            });
          } else {
            this.messageService.add({
              severity: "warn",
              summary: 'Error',
              detail: "No se pudo eliminar el archivo del Servidor"
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
  }

  desactivarArmarJuego(): boolean {
    let desactivar = true;
    this.posiciones.controls.forEach((posicionForm: FormGroup) => {
      if(posicionForm.controls['checked'].value && !posicionForm.controls['esPadre'].value) { desactivar = false; }
    });

    if(desactivar) { return true; }

    if(this.validarFormularioPosiciones(false)) {
      return false;
    } else {
      return true;
    }
  }

  seleccionarMaterialArmarJuego(material: Material) {
    material.codigo = Utils.removeLeftZeros(material.codigo);
    this.selectedMaterialArmarJuego = material;
    this.armarJuegoForm.controls['codigo'].patchValue(Utils.removeLeftZeros(material.codigo));
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
        componentes: new FormArray(componentes)
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
        id: new FormControl(0),
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

  confirmarFechas() {
    let mensaje = "", isValid = true, confirmar = true;
    const posicionesValidar = this.posiciones.getRawValue();
    for(var i = 0; i < posicionesValidar.length; i++) {
      const formData = posicionesValidar[i];

      if(formData.precioUnitarioSap == null || formData.precioUnitarioSap <= 0) {
        confirmar = false;
      }

      if(Utils.isNullOrEmpty(formData.fechaDisponibilidad)) {
        mensaje = "Datos de Pedido Firme >> Datos de Posición: Posición " + formData.item + ", Ingrese Fecha Disponibilidad.";
        isValid = false;
        confirmar = false;
        break;
      }
    }

    if(!isValid) {
      this.messageService.add({
        severity: "warn",
        summary: 'Validar',
        detail: mensaje,
      });
      return;
    }

    let posiciones: any[] = [];
    this.posiciones.controls.forEach((posicionForm: FormGroup) => {
      let componentes: any[] = [];
      const componentesForm = posicionForm.controls['componentes'] as FormArray;
      componentesForm.controls.forEach((componenteForm: FormGroup) => {
        const componenteFormData = componenteForm.value;
        componentes.push({
          id: componenteFormData.id,
          fechaDisponibilidad: Utils.stringToDate(componenteFormData.fechaDisponibilidad),
        });
      });

      const posicionFormData = posicionForm.getRawValue();
      posiciones.push({
        id: posicionFormData.id,
        fechaDisponibilidad: Utils.stringToDate(posicionFormData.fechaDisponibilidad),
      });
    });

    if(posiciones.length == 0) return;

    const data = {
      idPedido: this.dataPedido.id,
      confirmar: confirmar ? 1 : 0,
      posiciones: posiciones
    }

    let request : any = {};
    request.formulario = data;
    request.usuario = this.settingsService.getUsername();

    this.settingsService.mostrarSpinner();
    this.pedidoFirmeService.confirmarFechasdisponibilidad(request)
      .subscribe((response: any) => {
        this.settingsService.ocultarSpinner();
        if(response.cod_rpta == '1') {
          this.messageService.add({
            severity: "success",
            summary: 'Correcto',
            detail: response.rpta,
          });
          this.ngOnInit();
        } else {
          this.messageService.add({
            severity: "error",
            summary: 'Error',
            detail: response.rpta,
          });
        }
      },
      error => {
        this.settingsService.ocultarSpinner();
        this.messageService.add({
          severity: "error",
          summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
          detail: JSON.stringify(error),
        });
      });
  }

  obtenerDatosGenerarPdf() {
    const cotizacionFormData = this.pedidoFirmeForm.controls;

    let nombreCondicionPago = "", nombrePuertoOrigen = "", nombrePuertoDestino = "", lugarContacto = "", codigoIncoterm = "", codigoIncotermComercial = "", codigoMoneda = "";

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

    const cabecera = {
      tipoDocumento: true,
      idCotizacion: this.dataPedido.id,
      codigoPedido: this.dataPedido.codigoPedido,
      codigoCliente: cotizacionFormData.existeClienteSap ? cotizacionFormData.codigoCliente.value : "",
      nombreContacto: "",
      correoContacto: "",
      correoContactoAdicional: "",
      cargoContacto: "",
      lugarContacto: lugarContacto,
      descripcionMaterial: "",
      nombrePuertoOrigen: nombrePuertoOrigen,
      nombrePuertoDestino: nombrePuertoDestino,
      razonSocial: this.selectedCliente != null ? this.selectedCliente.descripcion : cotizacionFormData.nombreCliente.value,
      nombreCliente: this.selectedCliente != null ? this.selectedCliente.descripcion : cotizacionFormData.nombreCliente.value,
      nombreCondicionPago: nombreCondicionPago,
      nombreIncoterm: codigoIncoterm,
      nombreIncotermComercial: codigoIncotermComercial,
      vigencia: "",
      descripcionPrecio: "",
      observacion: cotizacionFormData.observaciones.value ? cotizacionFormData.observaciones.value.trim() : "",
      tiempoEntrega: "",
      importeFlete: cotizacionFormData.importeFlete.value ? cotizacionFormData.importeFlete.value : 0,
      seguroInternacional: cotizacionFormData.seguroInternacional.value ? cotizacionFormData.seguroInternacional.value : 0,
      numeroContenedor: cotizacionFormData.numeroContenedor.value ? cotizacionFormData.numeroContenedor.value : 0,
      codigoPersonal: this.settingsService.getSesionSSO().strPv_cpersonal,
      tipoSolicitud: Constantes.P_SOLICITUD_PEDIDO
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
      this.pedidoFirmeService.obtenerCotizacionPdf(request).toPromise()
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
      this.pedidoFirmeService.obtenerCotizacionPdf(request).toPromise()
    ]).then(
      (data) => {
        this.settingsService.ocultarSpinner();
        let url = window.URL.createObjectURL(data[0]);
        let a = document.createElement('a');
        document.body.appendChild(a);
        a.setAttribute('style', 'display: none');
        a.href = url;
        a.download = this.dataPedido.codigoPedido + '.pdf';
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
