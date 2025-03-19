import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, NG_VALUE_ACCESSOR, Validators } from '@angular/forms';
import { Etiqueta } from '../to/etiqueta';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MessageService } from 'primeng/api';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { ConfiguracionService } from '../configuracion.sevice';
import { Utils } from 'src/app/utils/utils';

@Component({
  selector: 'app-modal-etiqueta',
  templateUrl: './modal-etiqueta.component.html',
  styleUrls: ['./modal-etiqueta.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      multi: true,
      useExisting: ModalEtiquetaComponent
    }
  ]
})
export class ModalEtiquetaComponent implements OnInit {
  @Output() onCloseEvent = new EventEmitter<string>();

  items: Etiqueta[] = [];

  etiquetaForm: FormGroup;

  buscar = ""

  constructor(
    public messageService: MessageService,
    private modalService: NgbModal,
    private settingsService: SettingsService,
    private configuracionService: ConfiguracionService
  ) { }

  ngOnInit(): void {
    this.crearFormulario();
  }

  crearFormulario() {
    this.etiquetaForm = new FormGroup({
      id: new FormControl(0),
      nombre: new FormControl(),
      nombreIngles: new FormControl()
    });
  }

  limpiarValidadores() {
    this.etiquetaForm.controls['nombre'].clearValidators();
    this.etiquetaForm.controls['nombreIngles'].clearValidators();
  }

  refrescarValidadores() {
    this.etiquetaForm.controls['nombre'].updateValueAndValidity();
    this.etiquetaForm.controls['nombreIngles'].updateValueAndValidity();
  }

  inicializarValidaciones() {
    this.limpiarValidadores();
    this.etiquetaForm.controls['nombre'].setValidators([Validators.required]);
    this.etiquetaForm.controls['nombreIngles'].setValidators([Validators.required]);
    this.refrescarValidadores();
  }

  open(content: string) {
    this.items = [];
    this.consultar();
    this.modalService.open(content, { size: 'lg', centered: true, backdrop: 'static', keyboard: false });
  }

  consultar() {
    this.settingsService.mostrarSpinner();
    this.configuracionService.listarEtiquetas(this.buscar)
    .subscribe(
      (response: any) => {
        this.settingsService.ocultarSpinner();
        this.items = response as Etiqueta[];
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

  editarEtiqueta(etiqueta: Etiqueta) {
    this.etiquetaForm.controls['id'].patchValue(etiqueta.id);
    this.etiquetaForm.controls['nombre'].patchValue(etiqueta.nombre);
    this.etiquetaForm.controls['nombreIngles'].patchValue(etiqueta.nombreIngles);
  }

  eliminarEtiqueta(etiqueta: Etiqueta) {
    this.settingsService.mostrarSpinner();
    this.configuracionService.eliminarEtiqueta(etiqueta.id, this.settingsService.getUsername())
    .subscribe(
      (response: any) => {
        this.settingsService.ocultarSpinner();
        if(response.cod_rpta == 1) {
          this.messageService.add({
            severity: "success",
            summary: "Información",
            detail: "Etiqueta eliminada"
          });
          this.consultar();
        } else {
          this.messageService.add({
            severity: "warn",
            summary: "Alerta",
            detail: "No se pudo eliminar la etiqueta"
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

  validarFormulario(): boolean {
    let esValido: boolean = true;
    const formData: any = this.etiquetaForm.getRawValue();

    if(Utils.isNullOrEmpty(formData.nombre)
      || Utils.isNullOrEmpty(formData.nombreIngles)) {
      esValido = false;
    }

    return esValido;
  }

  guardarEtiqueta() {
    this.inicializarValidaciones();
    this.etiquetaForm.markAllAsTouched();

    const isValid = this.validarFormulario();
    if(!isValid) {
      this.messageService.add({
        severity: "warn",
        summary: "Revisar",
        detail: "Ingrese todos los campos obligatorios"
      });
      return;
    }

    const formData = this.etiquetaForm.getRawValue();
    const formulario = {
      id: formData.id,
      nombre: formData.nombre,
      nombreIngles: formData.nombreIngles
    }

    let request : any = {};
    request.formulario = formulario;
    request.usuario = this.settingsService.getUsername();

    this.settingsService.mostrarSpinner();
    this.configuracionService.guardarEtiqueta(request)
    .subscribe(
      (response: any) => {
        this.settingsService.ocultarSpinner();
        if(response.cod_rpta == '1') {
          this.messageService.add({
            severity: "success",
            summary: "Información",
            detail: "Etiqueta guardada correctamente.",
          });

          this.crearFormulario();
          this.consultar();
        } else {
          this.messageService.add({
            severity: "warn",
            summary: "Alerta",
            detail: JSON.stringify(response.rpta),
          });
        }
      },
      (err) => {
        this.messageService.add({
          severity: "error",
          summary: "Error",
          detail: JSON.stringify(err.message),
        });
      }
    );
  }

  onClose() {
    this.onCloseEvent.emit("Cerrar Modal Etiquetas");
  }

}
