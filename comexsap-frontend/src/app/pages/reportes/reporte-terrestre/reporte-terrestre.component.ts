import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { REPORTETERRESTRE } from 'src/app/shared/breadcrumb/breadcrumb';
import { Breadcrumb2Service } from 'src/app/shared/breadcrumb2.service';
import { Utils } from 'src/app/utils/utils';
import { ConfiguracionService } from '../../configuracion/configuracion.sevice';
import { Cliente } from '../../configuracion/to/cliente';
import { ReportesService } from '../reportes.service';
import { ReporteTerrestre } from '../to/reporteterrestre';
import * as XLSX from 'xlsx';
import { Constantes } from 'src/app/utils/constantes';
import { Centro } from '../../configuracion/to/centro';

@Component({
  selector: 'app-reporte-terrestre',
  templateUrl: './reporte-terrestre.component.html',
  styleUrls: ['./reporte-terrestre.component.scss']
})
export class ReporteTerrestreComponent implements OnInit {
  filtro = {
    mes: Utils.formatYearMonth(new Date()),
    sedes: [],
    clientes: [],
    fechaSalidaInicio: '',
    fechaSalidaFin: '',
    factura: '',
    facturas: []
  };

  filtroSedes: Centro[] = [];
  filtroClientes: Cliente[] = [];

  selectedItemsLabel: string = '{0} items seleccionados';
  displaySelectedLabel: boolean = true;
  maxSelectedLabels: number = 1;

  listado: ReporteTerrestre[];

  constructor(
    private router: Router,
    private breadcrumb2Service: Breadcrumb2Service,
    public messageService: MessageService,
    private settingsService: SettingsService,
    private configuracionService: ConfiguracionService,
    private service: ReportesService
  ) { }

  ngOnInit(): void {
    this.inicializarBreadcrumb();
    this.inicializar();
    this.consultar();
  }

  inicializarBreadcrumb() {
    this.breadcrumb2Service.addBreadcrumbs(REPORTETERRESTRE);
  }

  inicializar() {
    const filtroStg = sessionStorage.getItem(Constantes.P_STG_REPORTE_TERRESTRE_FILTRO);
    if(filtroStg != null) {
      this.filtro = JSON.parse(filtroStg);

      if(this.filtro.sedes != null) {
        this.filtro.sedes.forEach(codigo => {
          this.filtroSedes.push(new Centro(0, codigo, ''));
        });
      }

      if(this.filtro.clientes != null) {
        this.filtro.clientes.forEach(codigo => {
          this.filtroClientes.push(new Cliente(codigo, '', ''));
        });
      }
    }
  }

  seleccionarSedes(items: Centro[]) {
    let codigos: string[] = [];
    items.forEach(item => {
      codigos.push(item.codigo);
    });
    this.filtro.sedes = [...codigos];
    this.filtroSedes = [...items];
  }

  seleccionarClientes(items: Cliente[]) {
    let codigos: string[] = [];
    items.forEach(cliente => {
      codigos.push(cliente.codigo);
    });
    this.filtro.clientes = [...codigos];
    this.filtroClientes = [...items];
  }

  consultar() {
    sessionStorage.setItem(Constantes.P_STG_REPORTE_TERRESTRE_FILTRO, JSON.stringify(this.filtro));

    let request : any = {};
    request.formulario = this.filtro;
    if(!Utils.isNullOrEmpty(this.filtro.factura)) {
      request.formulario.facturas = this.filtro.factura.split(';').map(item => `%${item}%`);
    } else {
      request.formulario.facturas = [];
    }
    request.usuario = this.settingsService.getUsername();
    this.settingsService.mostrarSpinner();
    Promise.all([
      this.service.reporteTerrestre(request).toPromise()
    ]).then(
      (data :any[]) => {
        this.settingsService.ocultarSpinner();
        this.listado = data[0];
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

  descargar(): void {
    if(this.listado.length <= 0) {
      return;
    }
    const name = 'ReporteTerrestre_' + Utils.getCurrentTimeId() + '.xlsx';
    let exportData = [];
    this.listado.map(
      row => {
        exportData.push({
          anio: row.anio,
          mes: row.mes,
          folio: row.folio,
          sede: row.sede,
          destinatario: row.destinatario,
          empresaTransporte: row.empresaTransporte,
          comentario: row.comentario,
          pesoReal: row.pesoReal,
          producto: row.producto,
          incoterm: row.codigoSapIncoterm,
          destino: row.destino,
          serie: row.serie,
          factura: row.factura,
          fechaContabilizacion: row.fechaContabilizacion,
          fechaFactura: row.fechaFactura,
          mesFactura: row.mesFactura,
          turno: row.turno,
          fechaEntregaDocumento: row.fechaEntregaDocumento,
          fechaLevante: row.fechaLevante,
          fechaTransito: row.fechaTransito,
          tiempoAtencion: row.tiempoAtencion,
          kpiIntegral: row.kpiIntegral,
          cumpleIntegral: row.cumpleIntegral,
          kpiLevante: row.kpiLevante,
          kpiTransito: row.kpiTransito,
          agenteAduana: row.agenteAduana,
          dam: row.dam,
          ordenAgente: row.ordenAgente,
          fechaDam: row.fechaDam,
          canalControl: row.canalControl,
          inicioEmbarque: row.inicioEmbarque,
          fechaDamReg: row.fechaDamReg,
          tiempoRegDam41: row.tiempoRegDam41,
          fechaEntregaDua: row.fechaEntregaDua,
          fechaEntregaDrawback: row.fechaEntregaDrawback,
          tiempoEntregaCaasa: row.tiempoEntregaCaasa,
          tiempoEntregaDrawback: row.tiempoEntregaDrawback,
          TiempoEntregaComex: row.TiempoEntregaComex,
          diasEsperaDam: row.diasEsperaDam,
        });
    });

    const columns: any = {
      anio: 'Año',
      mes: 'Mes',
      folio: 'Folio',
      sede: 'Sede',
      destinatario: 'Destinatario',
      empresaTransporte: 'Empresa Transporte',
      comentario: 'Comentario',
      pesoReal: 'Peso Real',
      producto: 'Producto',
      incoterm: 'Incoterm',
      destino: 'Destino',
      serie: 'Serie',
      factura: 'Factura',
      fechaContabilizacion: 'Fecha Contb.',
      fechaFactura: 'Fecha Factura',
      mesFactura: 'Mes Factura',
      turno: 'Turno',
      fechaEntregaDocumento: 'Fecha Entrega Doc.',
      fechaLevante: 'Fecha Levante',
      fechaTransito: 'Fecha Tránsito',
      tiempoAtencion: 'Tiempo Atención (Días)',
      kpiIntegral: 'KPI Integral (Horas)',
      cumpleIntegral: 'Cumple Integral?',
      kpiLevante: 'KPI Levante (Horas)',
      kpiTransito: 'KPI Transito (Horas)',
      agenteAduana: 'Agente Aduana',
      dam: 'DAM',
      ordenAgente: 'Orden Agente',
      fechaDam: 'Fecha DAM',
      canalControl: 'Canal Control',
      inicioEmbarque: 'Inicio Embarque',
      fechaDamReg: 'Fecha DAM Reg.',
      tiempoRegDam41: 'Tiempo Reg. DAM41',
      fechaEntregaDua: 'Fecha Entrega DUA',
      fechaEntregaDrawback: 'Fecha Entrega Drawback',
      tiempoEntregaCaasa: 'Tiempo Entrega CAASA (Días)',
      tiempoEntregaDrawback: 'Tiempo Entrega Drawback (Días)',
      TiempoEntregaComex: 'Tiempo Entrega COMEX (Días)',
      diasEsperaDam: 'Días Espera DAM',
    };
    exportData.unshift(columns);

    const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(exportData, { skipHeader: true });
    const book: XLSX.WorkBook = XLSX.utils.book_new();
    XLSX.utils.book_append_sheet(book, worksheet, 'Reporte');

    XLSX.writeFile(book, name);
  }

}
