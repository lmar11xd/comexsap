import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MessageService } from 'primeng/api';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { ConfiguracionService } from 'src/app/pages/configuracion/configuracion.sevice';
import { Parametro } from 'src/app/pages/configuracion/to/parametro';
import { Breadcrumb2Service } from 'src/app/shared/breadcrumb2.service';
import { MaestrosService } from '../../maestros.service';
import { GrupoCorreo } from '../../to/grupocorreo';
import { LISTAR_MAESTRO_CORREO } from 'src/app/shared/breadcrumb/breadcrumb';
import { Constantes } from 'src/app/utils/constantes';
import { Utils } from 'src/app/utils/utils';
import * as XLSX from 'xlsx';

@Component({
  selector: 'app-listar-correo',
  templateUrl: './listar-correo.component.html',
  styleUrls: ['./listar-correo.component.scss']
})
export class ListarCorreoComponent implements OnInit {
  filtro = {
    grupos: [],
    buscar: "",
  };

  grupos: Parametro[] = [];

  listado: GrupoCorreo[];
  selectedGrupoCorreo: GrupoCorreo;

  selectedItemsLabel: string = '{0} items seleccionados';
  displaySelectedLabel: boolean = true;
  maxSelectedLabels: number = 1;
  minSearchCharacters: number = 3;

  constructor(
    private router: Router,
    private breadcrumb2Service: Breadcrumb2Service,
    public messageService: MessageService,
    private modalService: NgbModal,
    private settingsService: SettingsService,
    private configuracionService: ConfiguracionService,
    private service: MaestrosService
  ) { }

  ngOnInit(): void {
    this.inicializarBreadcrumb();
    this.inicializar();
    this.consultar();
  }

  inicializarBreadcrumb(){
    this.breadcrumb2Service.addBreadcrumbs(LISTAR_MAESTRO_CORREO);
  }

  inicializar() {
    this.settingsService.mostrarSpinner();
    Promise.all([
      this.configuracionService.listarParametroxDominio(Constantes.P_D014).toPromise(),//Areas
    ]).then(
      (data: any[]) => {
        this.settingsService.ocultarSpinner();
        this.grupos = Parametro.toArray(data[0]);
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

  irACrear() {
    this.router.navigate(['/maestros/correos/crear-correo']);
  }

  irAEditar(item: GrupoCorreo) {
    this.router.navigate(['/maestros/correos/editar-correo', item.id]);
  }

  mostrarModal(content: any, item: GrupoCorreo) {
    this.selectedGrupoCorreo = item;
    this.modalService.open(content, {centered: true, backdrop: 'static', keyboard: false});
  }

  consultar() {
    let request : any = {};
    request.formulario = this.filtro;
    request.usuario = this.settingsService.getUsername();
    this.settingsService.mostrarSpinner();
    Promise.all([
      this.service.listarGrupoCorreo(request).toPromise()
    ]).then(
      (data :any[]) => {
        this.listado = data[0];
        this.settingsService.ocultarSpinner();
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

  descargar() {
    if(this.listado.length <= 0) {
      return;
    }
    const name = 'GrupoCorreoDetalle_' + Utils.getCurrentTimeId() + '.xlsx';
    let exportData = [];
    this.listado.map(
      row => {
        exportData.push({
          id: row.id,
          codigoGrupo: row.codigo,
          grupo: row.descripcion,
          nombres: row.nombre,
          correo: row.correo,
        });
    });

    const columns: any = {
      id: "Id",
      codigoGrupo: "Código Grupo",
      grupo: "Grupo",
      nombres: "Nombres y Apellidos",
      correo: "Correo Electrónico",
    };
    exportData.unshift(columns);

    const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(exportData, { skipHeader: true });
    const book: XLSX.WorkBook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(book, worksheet, 'Grupo Correo');

    XLSX.writeFile(book, name);
  }

  eliminar() {
    if(this.selectedGrupoCorreo != null) {
      this.settingsService.mostrarSpinner();
      Promise.all([
        this.service.eliminarGrupoCorreo(this.selectedGrupoCorreo.id, this.settingsService.getUsername()).toPromise()
      ]).then(
        (data: any[]) => {
          this.settingsService.ocultarSpinner();
          if(data[0].cod_rpta == 1) {
            this.messageService.add({
              severity: "success",
              summary: "Alerta",
              detail: "Grupo Correo eliminado"
            });
            this.consultar();
          } else {
            this.messageService.add({
              severity: "error",
              summary: "Alerta",
              detail: "Hubo un error al eliminar Grupo Correo"
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
}
