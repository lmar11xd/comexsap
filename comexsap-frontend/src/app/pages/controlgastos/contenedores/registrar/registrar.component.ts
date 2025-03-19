import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
import { ControlGastosService } from '../../controlgastos.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MenuItem, MessageService } from 'primeng/api';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { ConfiguracionService } from 'src/app/pages/configuracion/configuracion.sevice';
import { Breadcrumb2Service } from 'src/app/shared/breadcrumb2.service';
import { Parametro } from 'src/app/pages/configuracion/to/parametro';
import { LISTAR_CONTROLGASTOS_CONTENEDORES, REGISTRAR_CONTROLGASTOS_CONTENEDORES } from 'src/app/shared/breadcrumb/breadcrumb';
import { Constantes } from 'src/app/utils/constantes';
import { Utils } from 'src/app/utils/utils';
import { DocumentoControlGasto } from '../../to/documentocontrolgasto';
import { TipoCambio } from 'src/app/pages/configuracion/to/tipocambio';
import { DAY_FORMAT } from 'src/app/utils/dayformat';

@Component({
  selector: 'app-registrar',
  templateUrl: './registrar.component.html',
  styleUrls: ['./registrar.component.scss']
})
export class RegistrarControlGastosContenedoresComponent implements OnInit {
  @ViewChild('modalGuardadoExitoso') modalGuardadoExitoso: ElementRef;

  active = 1;

  controlGastosForm: FormGroup;

  conceptos: Parametro[] = [];
  proveedores: Parametro[] = [];
  monedas: Parametro[] = [];

  documentos: DocumentoControlGasto[] = [];

  filtro = {
    documentos: []
  }

  mensajeValidacion: string = "";

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private breadcrumb2Service: Breadcrumb2Service,
    private messageService: MessageService,
    private modalService: NgbModal,
    private settingsService: SettingsService,
    private configuracionService: ConfiguracionService,
    private service: ControlGastosService
  ) { }

  ngOnInit(): void {
    const ids = this.activatedRoute.snapshot.paramMap.get("ids");
    if(!Utils.isNullOrEmpty(ids)) {
      this.filtro.documentos = ids.split(";").map(id => parseInt(id));
    }
    this.inicializarBreadcrumb();
    this.crearFormulario();
    this.inicializar();
  }

  inicializarBreadcrumb(){
    let BREADCRUMBS: MenuItem[] = [];
    BREADCRUMBS = BREADCRUMBS.concat(LISTAR_CONTROLGASTOS_CONTENEDORES);
    BREADCRUMBS = BREADCRUMBS.concat(REGISTRAR_CONTROLGASTOS_CONTENEDORES);
    this.breadcrumb2Service.addBreadcrumbs(BREADCRUMBS);
  }

  inicializar() {
    let request : any = {};
    request.formulario = this.filtro;

    this.settingsService.mostrarSpinner();
    Promise.all([
      this.configuracionService.listarParametroxDominio(Constantes.P_D038).toPromise(),//Proveedores
      this.configuracionService.listarParametroxDominio(Constantes.P_D039).toPromise(),//Conceptos
      this.configuracionService.listarParametroxDominio(Constantes.P_D040).toPromise(),//Monedas
      this.service.listarDocumentosxFiltro(request).toPromise()
    ]).then(
      (data :any[]) => {
        this.settingsService.ocultarSpinner();
        this.proveedores = Parametro.toArray(data[0]);
        this.conceptos = Parametro.toArray(data[1]);
        this.monedas = Parametro.toArray(data[2]);
        this.documentos = data[3] as DocumentoControlGasto[];

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
    this.controlGastosForm = new FormGroup({
      checkedGastos: new FormControl(false),
      documentos: new FormControl(),
      peso: new FormControl(),
      gastos: new FormArray([]),
      importe: new FormControl(),
      importeTonelada: new FormControl()
    });
  }

  inicializarFormulario() {
    const codigos = this.documentos.map(doc => doc.codigo).join(', ');
    let pesoToneladas = this.documentos.reduce((total, item) => total + item.pesoReal, 0);
    pesoToneladas = parseFloat(pesoToneladas.toFixed(2));

    this.controlGastosForm.controls['documentos'].patchValue(codigos, {onlySelf: true});
    this.controlGastosForm.controls['peso'].patchValue(pesoToneladas, {onlySelf: true});
    this.controlGastosForm.controls['gastos'].patchValue([], {onlySelf: true});
    this.controlGastosForm.controls['importe'].patchValue(0, {onlySelf: true});
    this.controlGastosForm.controls['importeTonelada'].patchValue(0, {onlySelf: true});

    this.controlGastosForm.controls['documentos'].disable();
    this.controlGastosForm.controls['peso'].disable();
    this.controlGastosForm.controls['documentos'].setValidators([Validators.required]);
    this.controlGastosForm.controls['peso'].setValidators([Validators.required]);
  }

  limpiarValidadores() {
    this.controlGastosForm.controls['gastos'].clearValidators();
    this.controlGastosForm.controls['importe'].clearValidators();
    this.controlGastosForm.controls['importeTonelada'].clearValidators();
  }

  refrescarValidadores() {
    this.controlGastosForm.controls['gastos'].updateValueAndValidity();
    this.controlGastosForm.controls['importe'].updateValueAndValidity();
    this.controlGastosForm.controls['importeTonelada'].updateValueAndValidity();
  }

  inicializarValidaciones() {
    this.limpiarValidadores();
    this.controlGastosForm.controls['peso'].setValidators([Validators.required]);
    this.controlGastosForm.controls['gastos'].setValidators([Validators.required, Validators.minLength(1)]);
    this.controlGastosForm.controls['importe'].setValidators([Validators.required]);
    this.controlGastosForm.controls['importeTonelada'].setValidators([Validators.required]);
    this.refrescarValidadores();
  }

  get gastos() {
    return this.controlGastosForm.controls["gastos"] as FormArray;
  }

  obtenerSiguienteItem(): number {
    let item = 0;
    this.gastos.controls.forEach(gasto => {
      if(gasto.value.item > item) { item = gasto.value.item; }
    });
    return item + 10;
  }

  desmarcarGastos() {
    this.gastos.controls.forEach((gastoForm: FormGroup) => {
      gastoForm.controls['checked'].patchValue(false);
    });
  }

  seleccionarGastos(event) {
    this.gastos.controls.forEach((gastoForm: FormGroup) => {
      gastoForm.controls['checked'].patchValue(event.target.checked);
    });
  }

  agregarGasto() {
    if(this.validarFormularioGastos()) {
      this.desmarcarGastos();
      const item = this.obtenerSiguienteItem();
      const posicionForm = new FormGroup({
        id: new FormControl(0),
        checked: new FormControl(false),
        item: new FormControl(item),
        idConcepto: new FormControl(),
        idProveedor: new FormControl(),
        facturas: new FormControl(),
        monto: new FormControl(0),
        codigoMoneda: new FormControl(Constantes.P_MONEDA_DEFECTO),
        montoTotal: new FormControl(0),
        adjuntos: new FormControl([])
      });
      this.gastos.push(posicionForm);
    } else {
      this.messageService.add({
        severity: "warn",
        summary: "Gastos",
        detail: "Por favor, ingresar los campos requeridos para los gastos"
      });
    }
  }

  quitarGastos() {
    const posiciones = this.controlGastosForm.value.posiciones.filter(posicion => posicion.checked);
    if(posiciones.length > 0) {
      this.controlGastosForm.controls['checkedGastos'].patchValue(false);
      posiciones.forEach(element => {
        const index = this.controlGastosForm.value.posiciones.findIndex(posicion => posicion.item == element.item);
        this.gastos.removeAt(index);
      });
    } else {
      this.messageService.add({
        severity: "warn",
        summary: "Alerta",
        detail: "Por favor, seleccione al menos un registro para eliminarlo"
      });
    }
  }

  obtenerTipoCambioActual(moneda: string, fecha: string) {
    this.settingsService.mostrarSpinner();
    return new Promise(resolve => {
      this.configuracionService.obtenerTipoCambioActual(moneda, fecha).subscribe((response) => {
        this.settingsService.ocultarSpinner();
        resolve(response);
      },
      (err) => {
        this.settingsService.ocultarSpinner();
        resolve(null);
      }
      );
    });
  }

  async calcularMonto(event, gastoForm: FormGroup) {
    const formData = gastoForm.getRawValue();
    const monto = formData.monto ? formData.monto : 0;
    let montoTotal = 0;

    if(formData.codigoMoneda != Constantes.P_MONEDA_DEFECTO) {
      const fechaActual = Utils.dateToStringFormat(new Date(), DAY_FORMAT.YYYYMMDD_GUION);
      const tipoCambio = await this.obtenerTipoCambioActual(formData.codigoMoneda, fechaActual) as TipoCambio;
      if(tipoCambio != null) {
        montoTotal = monto * tipoCambio.valor;
      } else {
        montoTotal = 0;
        this.messageService.add({
          severity: "warn",
          summary: "Alerta",
          detail: "Tipo de Cambio no disponible, por favor intente más tarde."
        });
      }
    } else {
      montoTotal = monto;
    }

    gastoForm.controls['montoTotal'].patchValue(montoTotal);
    this.calcularTotales();
  }

  calcularTotales() {
    const formData = this.controlGastosForm.getRawValue();
    const pesoToneladas = formData.peso ? formData.peso : 0;

    let importe = 0, importeTonelada = 0;

    this.gastos.controls.forEach((gastoForm: FormGroup) => {
      const gastoData = gastoForm.getRawValue();
      importe += gastoData.montoTotal;
    });

    if(importe > 0) {
      importeTonelada = importe / pesoToneladas;
    }

    this.controlGastosForm.controls['importe'].patchValue(importe);
    this.controlGastosForm.controls['importeTonelada'].patchValue(importeTonelada);
  }

  validarFormulario(): boolean {
    let formData = this.controlGastosForm.getRawValue();

    if(Utils.isNullOrEmpty(formData.documentos)) {
      this.mensajeValidacion = "Sin documentos de exportación.";
      return false;
    }

    if(formData.peso == null || formData.peso <= 0) {
      this.mensajeValidacion = "Total TN no ha sido calculado.";
      return false;
    }

    if(this.gastos.length == 0) {
      this.mensajeValidacion = "Necesita registrar al menos un gasto.";
      return false;
    }

    if(!this.validarFormularioGastos()) {
      return false;
    }

    if(formData.importe == null || formData.importe <= 0) {
      this.mensajeValidacion = "Total USD no ha sido calculado.";
      return false;
    }

    if(formData.importeTonelada == null || formData.importeTonelada <= 0) {
      this.mensajeValidacion = "Total USD/TN no ha sido calculado.";
      return false;
    }

    return true;
  }

  validarFormularioGastos(): boolean {
    let isValid = true;
    this.gastos.controls.forEach((posicionForm: FormGroup) => {
      let formData: any = posicionForm.value;

      if(formData.idConcepto == null || formData.idConcepto <= 0) {
        this.mensajeValidacion = "Gastos: Seleccione un concepto.";
        isValid = false;
        return;
      }

      if(formData.monto == null || formData.monto <= 0) {
        this.mensajeValidacion = "Gastos: Ingrese un monto.";
        isValid = false;
        return;
      }

      if(Utils.isNullOrEmpty(formData.codigoMoneda)) {
        this.mensajeValidacion = "Gastos: Seleccione una moneda.";
        isValid = false;
        return;
      }

      if(formData.montoTotal == null || formData.montoTotal <= 0) {
        this.mensajeValidacion = "Gastos: Monto total no calculado.";
        isValid = false;
        return;
      }
    });
    return isValid;
  }

  guardar() {
    this.inicializarValidaciones();
    this.controlGastosForm.markAllAsTouched();

    const isValid = this.validarFormulario();
    if(!isValid) {
      this.messageService.add({
        severity: "warn",
        summary: "Revisar:",
        detail: this.mensajeValidacion
      });
      return;
    }

    const formData = this.controlGastosForm.getRawValue();
    const cabecera = {
      id: 0,
      peso: formData.peso,
      importe: formData.importe,
      importeTonelada: formData.importeTonelada,
      tipoCambio: 3.75
    }

    let detalle: any[] = [];
    this.gastos.controls.forEach((gastoForm: FormGroup) => {
      const gastoData = gastoForm.value;
      detalle.push({
        id: 0,
        idConcepto: gastoData.idConcepto,
        idProveedor: gastoData.idProveedor,
        facturas: gastoData.facturas,
        monto: gastoData.monto,
        codigoMoneda: gastoData.codigoMoneda,
        montoTotal: gastoData.montoTotal
      });
    });

    const data = {
      cabecera: cabecera,
      detalle: detalle,
      documentos: this.documentos.map(doc => doc.id)
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
        } else {
          this.messageService.add({
            severity: "error",
            summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
            detail: JSON.stringify(response.rpta),
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
    this.router.navigate(['/exportaciones/controlgastos/contenedores/listar']);
  }
}
