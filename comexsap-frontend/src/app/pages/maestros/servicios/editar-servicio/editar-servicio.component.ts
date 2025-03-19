import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MessageService, MenuItem } from 'primeng/api';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { ConfiguracionService } from 'src/app/pages/configuracion/configuracion.sevice';
import { Parametro } from 'src/app/pages/configuracion/to/parametro';
import { LISTAR_MAESTRO_SERVICIO, EDITAR_MAESTRO_SERVICIO } from 'src/app/shared/breadcrumb/breadcrumb';
import { Breadcrumb2Service } from 'src/app/shared/breadcrumb2.service';
import { Constantes } from 'src/app/utils/constantes';
import { Utils } from 'src/app/utils/utils';
import { MaestrosService } from '../../maestros.service';
import { Servicio } from '../../to/servicio';

@Component({
  selector: 'app-editar-servicio',
  templateUrl: './editar-servicio.component.html',
  styleUrls: ['./editar-servicio.component.scss']
})
export class EditarServicioComponent implements OnInit {
  @ViewChild('modalConfirmacionExitosa') modalConfirmacionExitosa: ElementRef;

  active = 1;
  activeDatos = 1;
  activeContacto = 1;

  idServicio: string;
  dataServicio: Servicio;

  servicioForm: FormGroup;

  tiposTransporte: Parametro[] = [];
  despachos: Parametro[] = [];
  monedas: Parametro[] = [];

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private breadcrumb2Service: Breadcrumb2Service,
    private messageService: MessageService,
    private modalService: NgbModal,
    private settingsService: SettingsService,
    private configuracionService: ConfiguracionService,
    private service: MaestrosService
  ) { }

  ngOnInit(): void {
    this.idServicio = this.activatedRoute.snapshot.paramMap.get("id");
    this.inicializarBreadcrumb();
    this.crearFormulario();
    this.inicializar();
  }

  inicializarBreadcrumb(){
    EDITAR_MAESTRO_SERVICIO[0].url = EDITAR_MAESTRO_SERVICIO[0].url.replace(':id', this.idServicio);
    let BREADCRUMBS: MenuItem[] = [];
    BREADCRUMBS = BREADCRUMBS.concat(LISTAR_MAESTRO_SERVICIO);
    BREADCRUMBS = BREADCRUMBS.concat(EDITAR_MAESTRO_SERVICIO);
    this.breadcrumb2Service.addBreadcrumbs(BREADCRUMBS);
  }

  inicializar() {
    this.settingsService.mostrarSpinner();
    Promise.all([
      this.configuracionService.listarParametroxDominio(Constantes.P_D007).toPromise(),//Tipo Transporte
      this.configuracionService.listarParametroxDominio(Constantes.P_D008).toPromise(),//Despacho
      this.configuracionService.listarParametroxDominio(Constantes.P_D006).toPromise(),//Moneda
      this.service.obtenerServicio(this.idServicio).toPromise(),
    ]).then(
      (data :any[]) => {
        this.settingsService.ocultarSpinner();
        this.tiposTransporte = Parametro.toArray(data[0]);
        this.despachos = Parametro.toArray(data[1]);
        this.monedas = Parametro.toArray(data[2]);
        this.dataServicio = data[3];
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
    this.servicioForm = new FormGroup({
      concepto: new FormControl(),
      descripcion: new FormControl(),
      idTipoTransporte: new FormControl(),
      idDespacho: new FormControl(),
      idMoneda: new FormControl(),
      precio: new FormControl(),
      unidad: new FormControl()
    });
  }

  inicializarFormulario() {
    if(this.dataServicio != null) {
      this.servicioForm.controls['concepto'].patchValue(this.dataServicio.concepto, {onlySelf: true});
      this.servicioForm.controls['descripcion'].patchValue(this.dataServicio.descripcion, {onlySelf: true});
      this.servicioForm.controls['idTipoTransporte'].patchValue(this.dataServicio.idTipoTransporte, {onlySelf: true});
      this.servicioForm.controls['idDespacho'].patchValue(this.dataServicio.idDespacho, {onlySelf: true});
      this.servicioForm.controls['idMoneda'].patchValue(this.dataServicio.idMoneda, {onlySelf: true});
      this.servicioForm.controls['precio'].patchValue(this.dataServicio.precio, {onlySelf: true});
      this.servicioForm.controls['unidad'].patchValue(this.dataServicio.unidad, {onlySelf: true});
    }
  }

  limpiarValidadores() {
    this.servicioForm.controls['concepto'].clearValidators();
    this.servicioForm.controls['descripcion'].clearValidators();
    this.servicioForm.controls['idTipoTransporte'].clearValidators();
    this.servicioForm.controls['idDespacho'].clearValidators();
    this.servicioForm.controls['idMoneda'].clearValidators();
    this.servicioForm.controls['precio'].clearValidators();
    this.servicioForm.controls['unidad'].clearValidators();
  }

  refrescarValidadores() {
    this.servicioForm.controls['concepto'].updateValueAndValidity();
    this.servicioForm.controls['descripcion'].updateValueAndValidity();
    this.servicioForm.controls['idTipoTransporte'].updateValueAndValidity();
    this.servicioForm.controls['idDespacho'].updateValueAndValidity();
    this.servicioForm.controls['idMoneda'].updateValueAndValidity();
    this.servicioForm.controls['precio'].updateValueAndValidity();
    this.servicioForm.controls['unidad'].updateValueAndValidity();
  }

  inicializarValidaciones() {
    this.limpiarValidadores();
    this.servicioForm.controls['concepto'].setValidators([Validators.required]);
    this.servicioForm.controls['descripcion'].setValidators([Validators.required]);
    this.servicioForm.controls['idTipoTransporte'].setValidators([Validators.required]);
    this.servicioForm.controls['idDespacho'].setValidators([Validators.required]);
    this.servicioForm.controls['idMoneda'].setValidators([Validators.required]);
    this.servicioForm.controls['precio'].setValidators([Validators.required, Validators.min(1)]);
    this.servicioForm.controls['unidad'].setValidators([Validators.required]);
    this.refrescarValidadores();
  }

  validarFormulario(): boolean {
    let isValido: boolean = true;
    let formData: any = this.servicioForm.value;

    if(Utils.isNullOrEmpty(formData.concepto)
      || Utils.isNullOrEmpty(formData.descripcion)
      || Utils.isNullOrEmpty(formData.unidad)) {
      isValido = false;
    }

    if(formData.idTipoTransporte == null
      || formData.idDespacho == null
      || formData.idMoneda == null) {
      isValido = false;
    }

    if(formData.precio == null || formData.precio <= 0) {
      isValido = false;
    }

    return isValido;
  }

  guardar() {
    this.inicializarValidaciones();
    this.servicioForm.markAllAsTouched();

    const isValid = this.validarFormulario();
    if(!isValid) {
      this.messageService.add({
        severity: "warn",
        summary: "Revisar:",
        detail: "Por favor complete todos los datos obligatorios"
      });
      return;
    }

    const formData = this.servicioForm.value;

    const formulario = {
      id: this.dataServicio.id,
      concepto: formData.concepto.toUpperCase(),
      descripcion: formData.descripcion,
      idTipoTransporte: formData.idTipoTransporte,
      idDespacho: formData.idDespacho,
      idMoneda: formData.idMoneda,
      precio: formData.precio,
      unidad: formData.unidad.toUpperCase()
    }

    let request : any = {};
    request.formulario = formulario;
    request.usuario = this.settingsService.getUsername();

    this.settingsService.mostrarSpinner();
    Promise.all([
      this.service.guardarServicio(request).toPromise()
    ]).then(
      (data : any) => {
        this.settingsService.ocultarSpinner();
        if(data[0].cod_rpta == '1') {
          this.modalService.open(this.modalConfirmacionExitosa, { centered: true, backdrop: 'static', keyboard: false });
        } else {
          this.messageService.add({
            severity: "error",
            summary: "Error",
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
    this.router.navigate(['/maestros/servicios/listar-servicio']);
  }
}
