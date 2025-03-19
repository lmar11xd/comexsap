import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Parametro } from 'src/app/pages/configuracion/to/parametro';
import { MaestrosService } from '../../maestros.service';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MessageService } from 'primeng/api';
import { ConfiguracionService } from 'src/app/pages/configuracion/configuracion.sevice';
import { Breadcrumb2Service } from 'src/app/shared/breadcrumb2.service';
import { LISTAR_MAESTRO_SERVICIO } from 'src/app/shared/breadcrumb/breadcrumb';
import { Constantes } from 'src/app/utils/constantes';
import { Servicio } from '../../to/servicio';
import * as XLSX from 'xlsx';
import { Utils } from 'src/app/utils/utils';

@Component({
  selector: 'app-listar-servicio',
  templateUrl: './listar-servicio.component.html',
  styleUrls: ['./listar-servicio.component.scss']
})
export class ListarServicioComponent implements OnInit {
  @ViewChild('modalEliminar') modalEliminar: ElementRef;

  filtro = {
    transportes: [],
    despachos: [],
    buscar: "",
  };

  tiposTransporte: Parametro[] = [];
  despachos: Parametro[] = [];

  listado: Servicio[];
  selectedServicio: Servicio;

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
    this.breadcrumb2Service.addBreadcrumbs(LISTAR_MAESTRO_SERVICIO);
  }

  inicializar() {
    this.settingsService.mostrarSpinner();
    Promise.all([
      this.configuracionService.listarParametroxDominio(Constantes.P_D007).toPromise(),//Tipo Transporte
      this.configuracionService.listarParametroxDominio(Constantes.P_D008).toPromise(),//Despacho
    ]).then(
      (data: any[]) => {
        this.settingsService.ocultarSpinner();
        this.tiposTransporte = Parametro.toArray(data[0]);
        this.despachos = Parametro.toArray(data[1]);
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
    this.router.navigate(['/maestros/servicios/crear-servicio']);
  }

  irAEditar(item: Servicio) {
    this.router.navigate(['/maestros/servicios/editar-servicio', item.id]);
  }

  mostrarModal(content: any, item: Servicio) {
    this.selectedServicio = item;
    this.modalService.open(content, {centered: true, backdrop: 'static', keyboard: false});
  }

  consultar() {
    let request : any = {};
    request.formulario = this.filtro;
    request.usuario = this.settingsService.getUsername();
    this.settingsService.mostrarSpinner();
    Promise.all([
      this.service.listarServicio(request).toPromise()
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
    const name = 'Servicios_' + Utils.getCurrentTimeId() + '.xlsx';
    let exportData = [];
    this.listado.map(
      row => {
        exportData.push({
          id: row.id,
          concepto: row.concepto,
          descripcion: row.descripcion,
          tipoTransporte: row.tipoTransporte,
          despacho: row.despacho,
          precio: row.precio,
          moneda: row.moneda,
          unidad: row.unidad,
        });
    });

    const columns: any = {
      id: "Id",
      concepto: "Servicio/Concepto",
      descripcion: "Descripción",
      tipoTransporte: "Tipo Transporte",
      despacho: "Despacho",
      precio: "Precio",
      moneda: "Moneda",
      unidad: "Unidad"
    };
    exportData.unshift(columns);

    const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(exportData, { skipHeader: true });
    const book: XLSX.WorkBook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(book, worksheet, 'Servicios');

    XLSX.writeFile(book, name);
  }

  eliminar() {
    if(this.selectedServicio != null) {
      this.settingsService.mostrarSpinner();
      Promise.all([
        this.service.eliminarServicio(this.selectedServicio.id, this.settingsService.getUsername()).toPromise()
      ]).then(
        (data: any[]) => {
          this.settingsService.ocultarSpinner();
          if(data[0].cod_rpta == 1) {
            this.messageService.add({
              severity: "success",
              summary: "Alerta",
              detail: "Servicio eliminado"
            });
            this.consultar();
          } else {
            this.messageService.add({
              severity: "error",
              summary: "Alerta",
              detail: "Hubo un error al eliminar Servicio"
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
