import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { LISTAR_PEDIDOFIRME } from 'src/app/shared/breadcrumb/breadcrumb';
import { Breadcrumb2Service } from 'src/app/shared/breadcrumb2.service';
import { InicioService } from '../../inicio.service';
import { Pais } from 'src/app/pages/configuracion/to/pais';
import { Color } from 'src/app/utils/color';
import { Parametro } from 'src/app/pages/configuracion/to/parametro';
import { ConfiguracionService } from 'src/app/pages/configuracion/configuracion.sevice';
import { Constantes } from 'src/app/utils/constantes';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-circular-exportacion',
  templateUrl: './circular-exportacion.component.html',
  styleUrls: ['./circular-exportacion.component.scss']
})
export class CircularExportacionComponent {

  //single: any[];
  view: any[] = [830, 320];
  pais: Pais[];
  // options
  gradient: boolean = true;
  showLegend: boolean = true;
  showLabels: boolean = true;
  isDoughnut: boolean = false;
  legendPosition: string = 'below';
  legendTitle: string = 'Leyenda';
  showDataLabel: boolean = true;
  // colorScheme = {
  //   domain : [Color.C0001,Color.C0002,Color.C0003,Color.C0004,Color.C0005,
  //           Color.C0006,Color.C0007,Color.C0008,Color.C0009,Color.C0010]
  // };
  circuloGrafico: Parametro[] = [];
  color : string;
  colores : any[] = [];
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
    Promise.all([this.inicioService.obtenerTotalExportacionPais().toPromise(),
      this.configuracionService.listarParametroxDominio(Constantes.P_D037).toPromise()
    ]).then(
      (data: any[]) => {
        this.settingsService.ocultarSpinner();
        this.pais = data[0];
        this.circuloGrafico = Parametro.toArray(data[1]);
        if (this.circuloGrafico != null) {
          let componentes: FormGroup[] = [];
          this.circuloGrafico.forEach((componente: any) => {
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
