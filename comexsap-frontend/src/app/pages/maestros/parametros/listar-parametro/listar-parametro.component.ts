import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MessageService } from 'primeng/api';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { ConfiguracionService } from 'src/app/pages/configuracion/configuracion.sevice';
import { Parametro } from 'src/app/pages/configuracion/to/parametro';
import { LISTAR_MAESTRO_PARAMETRO } from 'src/app/shared/breadcrumb/breadcrumb';
import { Breadcrumb2Service } from 'src/app/shared/breadcrumb2.service';
import { Utils } from 'src/app/utils/utils';
import { MaestrosService } from '../../maestros.service';
import * as XLSX from 'xlsx';
import { Dominio } from 'src/app/pages/configuracion/to/dominio';

@Component({
  selector: 'app-listar-parametro',
  templateUrl: './listar-parametro.component.html',
  styleUrls: ['./listar-parametro.component.scss']
})
export class ListarParametroComponent implements OnInit {
  @ViewChild('modalEliminar') modalEliminar: ElementRef;

  filtro = {
    dominios: [],
    buscar: "",
  };

  dominios: Dominio[] = [];

  listado: Parametro[];
  selectedParametro: Parametro;

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
    this.breadcrumb2Service.addBreadcrumbs(LISTAR_MAESTRO_PARAMETRO);
  }

  inicializar() {
    this.settingsService.mostrarSpinner();
    Promise.all([
      this.configuracionService.listarDominio().toPromise(),//Dominios
    ]).then(
      (data: any[]) => {
        this.settingsService.ocultarSpinner();
        this.dominios = Dominio.toArray(data[0]);
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
    this.router.navigate(['/maestros/parametros/crear-parametro']);
  }

  irAEditar(item: Parametro) {
    this.router.navigate(['/maestros/parametros/editar-parametro', item.id]);
  }

  mostrarModal(content: any, item: Parametro) {
    this.selectedParametro= item;
    this.modalService.open(content, {centered: true, backdrop: 'static', keyboard: false});
  }

  consultar() {
    let request : any = {};
    request.formulario = this.filtro;
    request.usuario = this.settingsService.getUsername();
    this.settingsService.mostrarSpinner();
    Promise.all([
      this.service.listarParametro(request).toPromise()
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
    const name = 'Parametros_' + Utils.getCurrentTimeId() + '.xlsx';
    let exportData = [];
    this.listado.map(
      row => {
        exportData.push({
          id: row.id,
          codigoDominio: row.codigoDominio,
          nombreDominio: row.nombreDominio,
          codigo: row.codigo,
          nombreParametro: row.descripcion,
        });
    });

    const columns: any = {
      id: "Id",
      codigoDominio: "Código Maestro",
      nombreDominio: "Nombre Maestro",
      codigo: "Código Parámetro",
      nombreParametro: "Nombre Parámetro",
    };
    exportData.unshift(columns);

    const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(exportData, { skipHeader: true });
    const book: XLSX.WorkBook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(book, worksheet, 'Parametros');

    XLSX.writeFile(book, name);
  }

  eliminar() {
    if(this.selectedParametro != null) {
      this.settingsService.mostrarSpinner();
      Promise.all([
        this.service.eliminarParametro(this.selectedParametro.id, this.settingsService.getUsername()).toPromise()
      ]).then(
        (data: any[]) => {
          this.settingsService.ocultarSpinner();
          if(data[0].cod_rpta == 1) {
            this.messageService.add({
              severity: "success",
              summary: "Alerta",
              detail: "Parámetro eliminado"
            });
            this.consultar();
          } else {
            this.messageService.add({
              severity: "error",
              summary: "Alerta",
              detail: "Hubo un error al eliminar Parámetro"
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
