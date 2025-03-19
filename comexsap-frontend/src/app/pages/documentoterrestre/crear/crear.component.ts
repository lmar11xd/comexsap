import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MessageService } from 'primeng/api';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { Breadcrumb2Service } from 'src/app/shared/breadcrumb2.service';
import { ConfiguracionService } from '../../configuracion/configuracion.sevice';
import { DocumentoTerrestreService } from '../documentoterrestre.service';
import { CREAR_DOCUMENTOTERRESTRE } from 'src/app/shared/breadcrumb/breadcrumb';
import * as XLSX from 'xlsx';
import { AgenteAduana } from '../to/agenteaduana';

@Component({
  selector: 'app-crear',
  templateUrl: './crear.component.html',
  styleUrls: ['./crear.component.scss']
})
export class CrearComponent implements OnInit {
  @ViewChild('modalGuardadoExitoso') modalGuardadoExitoso: ElementRef;

  active = 1;
  activeDatos = 1;

  documentoForm: FormGroup;
  listado: AgenteAduana[] = [];

  constructor(
    private router: Router,
    private breadcrumb2Service: Breadcrumb2Service,
    private messageService: MessageService,
    private modalService: NgbModal,
    private settingsService: SettingsService,
    private configuracionService: ConfiguracionService,
    private service: DocumentoTerrestreService
  ) { }

  ngOnInit(): void {
    this.inicializarBreadcrumb();
  }

  inicializarBreadcrumb(){
    this.breadcrumb2Service.addBreadcrumbs(CREAR_DOCUMENTOTERRESTRE);
  }

  importar(event) {
    if (event.target.files.length == 0) {
      return;
    }
    let file: File = event.target.files[0];
    const reader: FileReader = new FileReader();
    reader.readAsBinaryString(file);
    reader.onload = (e: any) => {
      const binarystr: string = e.target.result;
      const wb: XLSX.WorkBook = XLSX.read(binarystr, { type: 'binary' });

      const wsname: string = wb.SheetNames[0];
      const ws: XLSX.WorkSheet = wb.Sheets[wsname];

      const arrayData = XLSX.utils.sheet_to_json(ws, {header: 0, raw: false, defval: '',});
      this.listado = [];
      arrayData.forEach((item: any) => {
        let agente = new AgenteAduana();
        agente.agenteAduana = item.AgenteAduana;
        agente.mesDespacho = item.MesDespacho;
        agente.sede = item.Sede;
        agente.cliente = item.Cliente;
        agente.pesoReal = item.PesoReal;
        agente.empresaTransporte = item.EmpresaTransporte;
        agente.destino = item.Destino;
        agente.factura = item.Factura;
        agente.serie = item.Serie;
        agente.mesFactura = item.MesFactura;
        agente.incoterm = item.Incoterm;
        agente.fechaLevante = item.FechaLevante;
        agente.fechaTransito = item.FechaTransito;
        agente.comentario = item.Comentario;
        agente.dam = item.DAM;
        agente.ordenAgenteAduana = item.OrdenAgenteAduana;
        agente.fechaDAM40 = item.FechaDAM40;
        agente.canalControl = item.CanalControl;
        agente.inicioEmbarqueFrontera = item.InicioEmbarqueFrontera;
        agente.fechaRegularizacionDAM41 = item.FechaRegularizacionDAM41;
        agente.tiempoRegularizacion = item.TiempoRegularizacion;
        agente.fechaEntregaDUA = item.FechaEntregaDUA;
        agente.estadoOrden = item.EstadoOrden;
        agente.fechaEntregaDrawback = item.FechaEntregaDrawback;
        this.listado.push(agente);
      });
    };
  }

  guardar() {
    if(this.listado.length > 0) {
      let posiciones = [];
      this.listado.forEach((agente: AgenteAduana) => {
        const posicion = {
          id: 0,
          agenteAduana: agente.agenteAduana,
          mesDespacho: agente.mesDespacho,
          sede: agente.sede,
          cliente: agente.serie,
          pesoReal: agente.pesoReal,
          empresaTransporte: agente.empresaTransporte,
          destino: agente.destino,
          factura: agente.factura,
          serie: agente.serie,
          mesFactura: agente.mesFactura,
          incoterm: agente.incoterm,
          fechaLevante: agente.fechaLevante ? new Date(agente.fechaLevante) : null,
          fechaTransito: agente.fechaTransito ? new Date(agente.fechaTransito) : null,
          comentario: agente.comentario,
          dam: agente.dam,
          ordenAgenteAduana: agente.ordenAgenteAduana,
          fechaDam40: agente.fechaDAM40 ? new Date(agente.fechaDAM40) : null,
          canalControl: agente.canalControl,
          inicioEmbarque: agente.inicioEmbarqueFrontera ? new Date(agente.inicioEmbarqueFrontera) : null,
          fechaRegularizacionDAM41: agente.fechaRegularizacionDAM41 ? new Date(agente.fechaRegularizacionDAM41) : null,
          tiempoRegularizacion: agente.tiempoRegularizacion,
          fechaEntregaDUA: agente.fechaEntregaDUA ? new Date(agente.fechaEntregaDUA) : null,
          estadoOrden: agente.estadoOrden,
          fechaEntregaDrawback: agente.fechaEntregaDrawback ? new Date(agente.fechaEntregaDrawback) : null
        };

        posiciones.push({
          ...posicion,
          dataAnterior: JSON.stringify(posicion)
        });
      });

      const data = {
        posiciones: posiciones
      };

      let request : any = {};
      request.formulario = data;
      request.usuario = this.settingsService.getUsername();

      this.settingsService.mostrarSpinner();
      Promise.all([
        this.service.guardarAgenteAduana(request).toPromise(),
      ]).then(
        (data: any[]) => {
          this.settingsService.ocultarSpinner();
          if(data[0].cod_rpta == '1') {
            this.modalService.open(this.modalGuardadoExitoso, { centered: true, backdrop: 'static', keyboard: false });
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
            severity: "warn",
            summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
            detail: JSON.stringify(err)
          });
        }
      );

    } else {
      this.messageService.add({
        severity: "warn",
        summary: "Alerta",
        detail: "No se han cargado datos"
      });
    }
  }

  irAlListado() {
    this.modalService.dismissAll();
    this.router.navigate(['/exportaciones/documentoterrestre/listar']);
  }
}
