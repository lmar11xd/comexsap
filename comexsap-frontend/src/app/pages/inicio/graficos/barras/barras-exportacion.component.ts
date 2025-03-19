import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { LISTAR_PEDIDOFIRME } from 'src/app/shared/breadcrumb/breadcrumb';
import { Breadcrumb2Service } from 'src/app/shared/breadcrumb2.service';
import { InicioService } from '../../inicio.service';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { ExportacionAgrupado } from 'src/app/pages/configuracion/to/exportacionagrupado';
import { Parametro } from 'src/app/pages/configuracion/to/parametro';
import { ConfiguracionService } from 'src/app/pages/configuracion/configuracion.sevice';
import { Constantes } from 'src/app/utils/constantes';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-barras-exportacion',
  templateUrl: './barras-exportacion.component.html',
  styleUrls: ['./barras-exportacion.component.scss']
})
export class BarrasExportacionComponent implements OnInit  {
// multi: any[];
view: any[] = [1030, 350];
barras: ExportacionAgrupado[];
// options
showXAxis: boolean = true;
showYAxis: boolean = true;
gradient: boolean = true;
showLegend: boolean = true;
showXAxisLabel: boolean = true;
xAxisLabel: string = 'Meses';
showYAxisLabel: boolean = true;
showDataLabel: boolean = true;
yAxisLabel: string = '';
legendTitle: string = '';
barraGrafico: Parametro[] = [];
color : string;
colores : any[] = [];
// colorScheme = {
//   domain: ['#052B94', '#60BEF9', '#AAAAAA']
// };
colorScheme : any;
constructor(
  public messageService: MessageService,
  private settingsService: SettingsService,
  private inicioService: InicioService,
  private configuracionService: ConfiguracionService
) { }

ngOnInit(): void {
  this.inicializar();
 }

inicializar() {
  this.settingsService.mostrarSpinner();
  Promise.all([this.inicioService.obtenerTotalExportacion().toPromise(),
    this.configuracionService.listarParametroxDominio(Constantes.P_D036).toPromise()
  ]).then(
    (data: any[]) => {
      this.settingsService.ocultarSpinner();
      this.barras = data[0];
      this.barraGrafico = Parametro.toArray(data[1]);
      if (this.barraGrafico != null) {
        let componentes: FormGroup[] = [];
        this.barraGrafico.forEach((componente: any) => {
          this.color = componente.codigo;
          this.colores.push(this.color);
        });
      }
      this.colorScheme = {
        domain: this.colores
      };
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

onSelect(data): void {
}

onActivate(data): void {
}

onDeactivate(data): void {
}

}
