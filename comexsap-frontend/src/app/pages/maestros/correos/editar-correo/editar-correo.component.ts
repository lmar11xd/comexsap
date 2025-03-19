import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MessageService, MenuItem } from 'primeng/api';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { ConfiguracionService } from 'src/app/pages/configuracion/configuracion.sevice';
import { Parametro } from 'src/app/pages/configuracion/to/parametro';
import { LISTAR_MAESTRO_CORREO, CREAR_MAESTRO_CORREO, EDITAR_MAESTRO_CORREO } from 'src/app/shared/breadcrumb/breadcrumb';
import { Breadcrumb2Service } from 'src/app/shared/breadcrumb2.service';
import { Constantes } from 'src/app/utils/constantes';
import { Utils } from 'src/app/utils/utils';
import { MaestrosService } from '../../maestros.service';
import { GrupoCorreo } from '../../to/grupocorreo';

@Component({
  selector: 'app-editar-correo',
  templateUrl: './editar-correo.component.html',
  styleUrls: ['./editar-correo.component.scss']
})
export class EditarCorreoComponent implements OnInit {
  @ViewChild('modalConfirmacionExitosa') modalConfirmacionExitosa: ElementRef;

  active = 1;
  activeDatos = 1;

  idCorreo: string;
  dataCorreo: GrupoCorreo;

  correoForm: FormGroup;

  grupos: Parametro[] = [];

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
    this.idCorreo = this.activatedRoute.snapshot.paramMap.get("id");
    this.inicializarBreadcrumb();
    this.crearFormulario();
    this.inicializar();
  }

  inicializarBreadcrumb() {
    EDITAR_MAESTRO_CORREO[0].url = EDITAR_MAESTRO_CORREO[0].url.replace(':id', this.idCorreo);
    let BREADCRUMBS: MenuItem[] = [];
    BREADCRUMBS = BREADCRUMBS.concat(LISTAR_MAESTRO_CORREO);
    BREADCRUMBS = BREADCRUMBS.concat(EDITAR_MAESTRO_CORREO);
    this.breadcrumb2Service.addBreadcrumbs(BREADCRUMBS);
  }

  inicializar() {
    this.settingsService.mostrarSpinner();
    Promise.all([
      this.configuracionService.listarParametroxDominio(Constantes.P_D014).toPromise(),//Areas
      this.service.obtenerGrupoCorreo(this.idCorreo).toPromise(),
    ]).then(
      (data :any[]) => {
        this.settingsService.ocultarSpinner();
        this.grupos = Parametro.toArray(data[0]);
        this.dataCorreo = data[1];
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
    this.correoForm = new FormGroup({
      nombre: new FormControl(),
      correo: new FormControl(),
      idGrupo: new FormControl()
    });
  }

  inicializarFormulario() {
    if(this.dataCorreo != null) {
      this.correoForm.controls['nombre'].patchValue(this.dataCorreo.nombre, {onlySelf: true});
      this.correoForm.controls['correo'].patchValue(this.dataCorreo.correo, {onlySelf: true});
      this.correoForm.controls['idGrupo'].patchValue(this.dataCorreo.idGrupo, {onlySelf: true});
    }
  }

  limpiarValidadores() {
    this.correoForm.controls['nombre'].clearValidators();
    this.correoForm.controls['correo'].clearValidators();
    this.correoForm.controls['idGrupo'].clearValidators();
  }

  refrescarValidadores() {
    this.correoForm.controls['nombre'].updateValueAndValidity();
    this.correoForm.controls['correo'].updateValueAndValidity();
    this.correoForm.controls['idGrupo'].updateValueAndValidity();
  }

  inicializarValidaciones() {
    this.limpiarValidadores();
    this.correoForm.controls['nombre'].setValidators([Validators.required]);
    this.correoForm.controls['correo'].setValidators([Validators.required]);
    this.correoForm.controls['idGrupo'].setValidators([Validators.required]);
    this.refrescarValidadores();
  }

  validarFormulario(): boolean {
    let isValido: boolean = true;
    let formData: any = this.correoForm.value;

    if(Utils.isNullOrEmpty(formData.nombre)
      || Utils.isNullOrEmpty(formData.correo)) {
      isValido = false;
    }

    if(formData.idGrupo == null) {
      isValido = false;
    }

    return isValido;
  }

  guardar() {
    this.inicializarValidaciones();
    this.correoForm.markAllAsTouched();

    const isValid = this.validarFormulario();
    if(!isValid) {
      this.messageService.add({
        severity: "warn",
        summary: "Revisar:",
        detail: "Por favor complete todos los datos obligatorios"
      });
      return;
    }

    const formData = this.correoForm.value;

    const formulario = {
      id: this.dataCorreo.id,
      nombre: formData.nombre,
      correo: formData.correo,
      idGrupo: formData.idGrupo,
    }

    let request : any = {};
    request.formulario = formulario;
    request.usuario = this.settingsService.getUsername();

    this.settingsService.mostrarSpinner();
    Promise.all([
      this.service.guardarGrupoCorreo(request).toPromise()
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
    this.router.navigate(['/maestros/correos/listar-correo']);
  }
}
