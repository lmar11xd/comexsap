import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { ControlValueAccessor, FormArray, FormControl, FormGroup, NG_VALUE_ACCESSOR, Validators } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MessageService } from 'primeng/api';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { ConfiguracionService } from '../configuracion.sevice';
import { Utils } from 'src/app/utils/utils';
import { UnidadMedida } from '../to/unidadmedida';
import { PedidoFirmePosicion } from '../../pedidofirme/to/pedidofirmeposicion';
import { Material } from '../to/material';
import { ConfiguracionUtil } from '../configuracion.util';
import * as _ from 'lodash';
import { Parametro } from '../to/parametro';
import { PrecioMaterial } from '../to/preciomaterial';
import { Constantes } from 'src/app/utils/constantes';

@Component({
  selector: 'app-modal-componentes',
  templateUrl: './modal-componentes.component.html',
  styleUrls: ['./modal-componentes.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      multi: true,
      useExisting: ModalComponentesComponent
    }
  ]
})
export class ModalComponentesComponent implements OnInit, OnDestroy, ControlValueAccessor {
  @Input('form') posicionForm: FormGroup;
  @Output() formChange : EventEmitter<FormGroup> = new EventEmitter();

  @Input('fechaListaPrecio') fechaListaPrecio: string;
  @Input('idListaPrecio') idListaPrecio: number;
  @Input('listaPrecios') listaPrecios: Parametro[] = [];
  @Input('estadoDocumento') estadoDocumento: number = Constantes.P_ESTADO_INICIAL;
  @Input('esMaritimo') esMaritimo: boolean = false;

  @Input() disabled: boolean = false;

  onChange = (form: FormGroup) => {};
  onTouched = () => {};
  touched = false;

  componentesEliminados: PedidoFirmePosicion[] = [];

  constructor(
    public messageService: MessageService,
    private modalService: NgbModal,
    private settingsService: SettingsService,
    private configuracionService: ConfiguracionService
  ) { }

  writeValue(form: FormGroup): void {
    this.posicionForm = form;
  }
  registerOnChange(onChange: any): void {
    this.onChange = onChange;
  }
  registerOnTouched(onTouched: any): void {
    this.onTouched = onTouched;
  }
  setDisabledState?(disabled: boolean): void {
    this.disabled = disabled;
  }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
  }

  open(content: string) {
    this.inicializar();
    this.modalService.open(content, { size: 'lg', centered: true, backdrop: 'static', keyboard: false });
  }

  inicializar() {
    //this.settingsService.mostrarSpinner();
    if(this.esMaritimo) {
      if(this.estadoDocumento == Constantes.P_ESTADO_CONFIRMADO) {
        this.posicionForm.controls['componenteTexto'].disable();
        this.posicionForm.controls['componenteTexto'].setValidators([Validators.required]);
      } else {
        this.posicionForm.controls['componenteTexto'].clearValidators();
      }
    }

    this.componentes.controls.forEach((componenteForm: FormGroup) => {
      const componenteFormData = componenteForm.value;
      componenteForm.controls['checked'].patchValue(false);
      Promise.all([
        this.configuracionService.listarUnidadMedida(Utils.toCodeMaterial(componenteFormData.codigoMaterial)).toPromise(),
        this.configuracionService.obtenerUnidadMedidaxProducto(Utils.toCodeMaterial(componenteFormData.codigoMaterial), componenteFormData.unidadMedidaVenta).toPromise()
      ])
      .then(
        (data: any[]) => {
          //this.settingsService.ocultarSpinner();
          const listaUnidades = UnidadMedida.toArray(data[0]);
          const unidadMedida = UnidadMedida.toObject(data[1]);
          componenteForm.controls['listaUnidadesMedida'].patchValue(listaUnidades);
          componenteForm.controls['selectedUnidadMedida'].patchValue(unidadMedida);
        },
        (err) => {
          //this.settingsService.ocultarSpinner();
          this.messageService.add({
            severity: "error",
            summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
            detail: JSON.stringify(err.message)
          });
        }
      )
      .catch();
    });
  }

  get componentes() {
    return this.posicionForm.controls["componentes"] as FormArray;
  }

  seleccionarTodo(event) {
    this.componentes.controls.forEach((posicionForm: FormGroup) => {
      posicionForm.controls['checked'].patchValue(event.target.checked);
    });
  }

  obtenerSiguienteItem(): number {
    let item = 0;
    this.componentes.controls.forEach(posicion => {
      if(posicion.value.item > item) { item = posicion.value.item; }
    });
    return item + 10;
  }

  existeMaterial(codigoMaterial: string): boolean {
    const posiciones = this.componentes.controls.filter(posicion => posicion.value.codigoMaterial == codigoMaterial);
    return posiciones.length > 0;
  }

  agregarPosicion() {
    if(this.validarFormularioComponentes()) {
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
      this.componentes.push(posicionForm);
    } else {
      this.messageService.add({
        severity: "warn",
        summary: "Componentes",
        detail: "Por favor, ingresar los campos requeridos para los componentes"
      });
    }
  }

  quitarPosiciones() {
    const posiciones = this.posicionForm.value.componentes.filter(posicion => posicion.checked);
    if(posiciones.length > 0) {
      posiciones.forEach(element => {
        const index = this.posicionForm.value.componentes.findIndex(posicion => posicion.item == element.item);
        if(element.id != 0) {
          this.componentesEliminados.push(element);
        }
        this.componentes.removeAt(index);
      });
      this.reiniciarComponenteItem();
    } else {
      this.messageService.add({
        severity: "warn",
        summary: "Alerta",
        detail: "Por favor, seleccione al menos un registro para eliminarlo"
      });
    }
  }

  reiniciarComponenteItem() {
    let item = 10;
    this.componentes.controls.forEach((componenteForm: FormGroup) => {
      componenteForm.controls['item'].patchValue(item);
      item += 10;
    });
  }

  seleccionarMaterial(material: Material, componenteForm: FormGroup) {
    if(material.codigo == Utils.toCodeMaterial(componenteForm.value.codigoMaterial)) return;

    if(this.existeMaterial(material.codigo)) {
      componenteForm.controls['selectedMaterial'].patchValue(null);
      componenteForm.controls['listaUnidadesMedida'].patchValue([]);
      componenteForm.controls['codigoMaterial'].patchValue("");
      componenteForm.controls['descripcionMaterial'].patchValue(null);
      componenteForm.controls['pesoToneladas'].patchValue(0);
      this.messageService.add({
        severity: "warn",
        summary: "Material ya existe",
        detail: "No puedes ingresar el mismo material"
      });
      return;
    }
    componenteForm.controls['codigoMaterial'].patchValue(material.codigo);
    componenteForm.controls['descripcionMaterial'].patchValue(material.descripcion);
    componenteForm.controls['cantidadVenta'].patchValue(null);
    componenteForm.controls['unidadMedidaVenta'].patchValue(null);
    componenteForm.controls['selectedUnidadMedida'].patchValue(null);
    componenteForm.controls['listaUnidadesMedida'].patchValue([]);
    componenteForm.controls['pesoToneladas'].patchValue(0);
    componenteForm.controls['precioUnitario'].patchValue(null);
    componenteForm.controls['importe'].patchValue(0);
    componenteForm.controls['precioMaterialSap'].patchValue(null);
    this.buscarUnidadMedidaVenta(material.codigo, componenteForm);
  }

  buscarUnidadMedidaVenta(codigoMaterial: string, componenteForm: FormGroup) {
    const listaPrecio = ConfiguracionUtil.obtenerParametroxId(this.listaPrecios, this.idListaPrecio);

    const requestPrecio = {
      codigoMaterial: codigoMaterial,
      codigoListaPrecio: listaPrecio ? listaPrecio.codigo : "",
      fechaListaPrecio: this.fechaListaPrecio ? this.fechaListaPrecio : ""
    };

    this.settingsService.mostrarSpinner();
    Promise.all([
      this.configuracionService.listarUnidadMedida(codigoMaterial).toPromise(),
      this.configuracionService.obtenerPrecioMaterial(requestPrecio).toPromise()
    ]).then(
      (data: any[]) => {
        this.settingsService.ocultarSpinner();
        componenteForm.controls['listaUnidadesMedida'].patchValue(UnidadMedida.toArray(data[0]));
        componenteForm.controls['precioMaterialSap'].patchValue(data[1]);
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

  cambiarCantidadVenta(componenteForm: FormGroup) {
    this.actualizarPesoToneladas(componenteForm);
    this.actualizarImporte(componenteForm);
  }

  seleccionarUnidadMedidaVenta(unidadMedida: UnidadMedida, componenteForm: FormGroup) {
    componenteForm.controls['selectedUnidadMedida'].patchValue(unidadMedida);
    this.actualizarPesoToneladas(componenteForm);
    this.actualizarPrecioUnitarioSap(componenteForm);
    this.actualizarImporte(componenteForm);
  }

  cambiarPrecioUnitario(componenteForm: FormGroup) {
    this.actualizarImporte(componenteForm);
  }

  actualizarPesoToneladas(componenteForm: FormGroup) {
    let pesoToneladas = 0;
    if(componenteForm.value.cantidadVenta != null
      && componenteForm.value.cantidadVenta > 0
      && componenteForm.value.selectedUnidadMedida != null
    ) {
      const pesoNominal = ConfiguracionUtil.obtenerPesoNominal(componenteForm);
      pesoToneladas = (componenteForm.value.cantidadVenta * pesoNominal) / 1000;
      componenteForm.controls['pesoToneladas'].patchValue(pesoToneladas);
    }
  }

  actualizarPrecioUnitarioSap(posicionForm: FormGroup) {
    let precioUnitario = 0;
    if(posicionForm.value.precioMaterialSap != null
      && posicionForm.value.selectedUnidadMedida != null) {
        const pesoNominal = ConfiguracionUtil.obtenerPesoNominal(posicionForm);
        const precioMaterial = posicionForm.value.precioMaterialSap as PrecioMaterial;
        precioUnitario = parseFloat(Utils.round(pesoNominal * precioMaterial.importe / precioMaterial.cantidad, 2));
        posicionForm.controls['precioUnitarioSap'].patchValue(precioUnitario);
        posicionForm.controls['precioUnitario'].patchValue(precioUnitario);
    }
  }

  actualizarImporte(componenteForm: FormGroup) {
    let importe = 0;
    if(componenteForm.value.cantidadVenta != null
      && componenteForm.value.cantidadVenta > 0
      && componenteForm.value.selectedUnidadMedida != null
    ) {
      importe = componenteForm.value.cantidadVenta * componenteForm.value.precioUnitario;
      componenteForm.controls['importe'].patchValue(importe);
    }
  }

  limpiarPosicion(componenteForm: FormGroup) {
    ConfiguracionUtil.limpiarPosicion(componenteForm);
  }

  validarFormularioComponentes(): boolean {
    let isValido: boolean = true;
    this.componentes.controls.forEach((componenteForm: FormGroup) => {
      let formData: any = componenteForm.value;
      if(Utils.isNullOrEmpty(formData.codigoMaterial)
        || formData.cantidadVenta == null
        || formData.cantidadVenta <= 0
        || Utils.isNullOrEmpty(formData.unidadMedidaVenta)
        || formData.precioUnitario == null
        || formData.precioUnitario <= 0
        || formData.importe == null
        || formData.importe <= 0
      ) {
        isValido = false;
      }
    })
    return isValido;
  }

  aceptar() {
    if(!this.esMaritimo && this.estadoDocumento == Constantes.P_ESTADO_INICIAL) {
      if(!this.validarFormularioComponentes()) {
        this.messageService.add({
          severity: "warn",
          summary: 'Validación',
          detail: 'Por favor, completar los campos obligatorios para los componentes.'
        });
        return;
      }
      //Recalcular
      let contador = 0, pesoToneladas = 0, importe = 0, precioUnitario = 0;
      let cantidadVenta = 0;
      this.componentes.controls.forEach((componenteForm: FormGroup) => {
        if(contador == 0) {
          cantidadVenta = componenteForm.controls['cantidadVenta'].value;
          precioUnitario += componenteForm.controls['precioUnitario'].value;
        } else {
          precioUnitario += componenteForm.controls['importe'].value / cantidadVenta;
        }
        pesoToneladas += componenteForm.controls['pesoToneladas'].value;
        importe += componenteForm.controls['importe'].value;
        contador++;
      });

      this.posicionForm.controls['cantidadVenta'].patchValue(cantidadVenta);
      this.posicionForm.controls['pesoToneladas'].patchValue(pesoToneladas);
      this.posicionForm.controls['precioUnitario'].patchValue(precioUnitario);
      if(!this.posicionForm.controls['precioUnitarioSap'].value) {
        this.posicionForm.controls['precioUnitarioSap'].patchValue(0);
      }
      this.posicionForm.controls['importe'].patchValue(importe);

      //this.form = this.posicionForm;
      this.onChange(this.posicionForm);
      this.formChange.emit(this.posicionForm);
    }

    this.modalService.dismissAll();
  }
}
