import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MessageService, MenuItem } from 'primeng/api';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { ConfiguracionService } from 'src/app/pages/configuracion/configuracion.sevice';
import { CREAR_MAESTRO_PARAMETRO, LISTAR_MAESTRO_PARAMETRO } from 'src/app/shared/breadcrumb/breadcrumb';
import { Breadcrumb2Service } from 'src/app/shared/breadcrumb2.service';
import { Utils } from 'src/app/utils/utils';
import { MaestrosService } from '../../maestros.service';
import { Dominio } from 'src/app/pages/configuracion/to/dominio';

@Component({
  selector: 'app-crear-parametro',
  templateUrl: './crear-parametro.component.html',
  styleUrls: ['./crear-parametro.component.scss']
})
export class CrearParametroComponent implements OnInit {
  @ViewChild('modalConfirmacionExitosa') modalConfirmacionExitosa: ElementRef;

  active = 1;
  activeDatos = 1;

  parametroForm: FormGroup;

  dominios: Dominio[] = [];

  constructor(
    private router: Router,
    private breadcrumb2Service: Breadcrumb2Service,
    private messageService: MessageService,
    private modalService: NgbModal,
    private settingsService: SettingsService,
    private configuracionService: ConfiguracionService,
    private service: MaestrosService
  ) { }

  ngOnInit(): void {
    this.inicializarBreadcrumb();
    this.crearFormulario();
    this.inicializar();
  }

  inicializarBreadcrumb(){
    let BREADCRUMBS: MenuItem[] = [];
    BREADCRUMBS = BREADCRUMBS.concat(LISTAR_MAESTRO_PARAMETRO);
    BREADCRUMBS = BREADCRUMBS.concat(CREAR_MAESTRO_PARAMETRO);
    this.breadcrumb2Service.addBreadcrumbs(BREADCRUMBS);
  }

  inicializar() {
    this.settingsService.mostrarSpinner();
    Promise.all([
      this.configuracionService.listarDominio().toPromise(),
    ]).then(
      (data :any[]) => {
        this.settingsService.ocultarSpinner();
        this.dominios = Dominio.toArray(data[0]);
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
    this.parametroForm = new FormGroup({
      codigo: new FormControl(),
      descripcion: new FormControl(),
      codigoDominio: new FormControl()
    });
  }

  inicializarFormulario() {
    this.parametroForm.controls['codigo'].patchValue(null, {onlySelf: true});
    this.parametroForm.controls['descripcion'].patchValue(null, {onlySelf: true});
    this.parametroForm.controls['codigoDominio'].patchValue(null, {onlySelf: true});
  }

  limpiarValidadores() {
    this.parametroForm.controls['codigo'].clearValidators();
    this.parametroForm.controls['descripcion'].clearValidators();
    this.parametroForm.controls['codigoDominio'].clearValidators();
  }

  refrescarValidadores() {
    this.parametroForm.controls['codigo'].updateValueAndValidity();
    this.parametroForm.controls['descripcion'].updateValueAndValidity();
    this.parametroForm.controls['codigoDominio'].updateValueAndValidity();
  }

  inicializarValidaciones() {
    this.limpiarValidadores();
    this.parametroForm.controls['codigo'].setValidators([Validators.required]);
    this.parametroForm.controls['descripcion'].setValidators([Validators.required]);
    this.parametroForm.controls['codigoDominio'].setValidators([Validators.required]);
    this.refrescarValidadores();
  }

  validarFormulario(): boolean {
    let isValido: boolean = true;
    let formData: any = this.parametroForm.value;

    if(Utils.isNullOrEmpty(formData.codigo)
      || Utils.isNullOrEmpty(formData.descripcion)) {
      isValido = false;
    }

    if(formData.codigoDominio == null) {
      isValido = false;
    }

    return isValido;
  }

  guardar() {
    this.inicializarValidaciones();
    this.parametroForm.markAllAsTouched();

    const isValid = this.validarFormulario();
    if(!isValid) {
      this.messageService.add({
        severity: "warn",
        summary: "Revisar:",
        detail: "Por favor complete todos los datos obligatorios"
      });
      return;
    }

    const formData = this.parametroForm.value;

    const formulario = {
      id: 0,
      codigo: formData.codigo.toUpperCase(),
      descripcion: formData.descripcion,
      codigoDominio: formData.codigoDominio
    }

    let request : any = {};
    request.formulario = formulario;
    request.usuario = this.settingsService.getUsername();

    this.settingsService.mostrarSpinner();
    Promise.all([
      this.service.guardarParametro(request).toPromise()
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
    this.router.navigate(['/maestros/parametros/listar-parametro']);
  }
}
