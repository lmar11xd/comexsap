import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { Tarjeta } from 'src/app/pages/configuracion/to/tarjeta';
import { InicioService } from '../../inicio.service';
import { ConfiguracionService } from 'src/app/pages/configuracion/configuracion.sevice';
import { Constantes } from 'src/app/utils/constantes';
import { Parametro } from 'src/app/pages/configuracion/to/parametro';
import { FormArray, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-tarjetas-exportacion',
  templateUrl: './tarjetas-exportacion.component.html',
  styleUrls: ['./tarjetas-exportacion.component.scss']
})
export class TarjetasExportacionComponent implements OnInit {

  tarjeta: Tarjeta[];
  tarjetaGrafico: Parametro[] = [];
  view: any[] = [1000, 200];
  //colores : Parametro[]= [];
  coloreslistForm: FormGroup;
  colorScheme = {
    domain: ['#5AA454', '#E44D25', '#F68311']
  };

  dominio: any[] = [];
  //cardColor: string = '#BFBFBF';
  cardColor: any;

  constructor(
    public messageService: MessageService,
    private settingsService: SettingsService,
    private inicioService: InicioService,
    private configuracionService: ConfiguracionService
  ) { }

  ngOnInit(): void {
    this.inicializar();
  }
  onSelect(event) {
  }

  get colores() {
    return this.coloreslistForm.controls["colores"] as FormArray;
  }
  inicializar() {
    this.settingsService.mostrarSpinner();
    Promise.all([
      this.inicioService.obtenerTotalPedidoExportacion().toPromise(),
      this.configuracionService.listarParametroxDominio(Constantes.P_D035).toPromise()
    ]).then(
      (data: any[]) => {
        this.settingsService.ocultarSpinner();
        this.tarjeta = data[0];
        this.tarjetaGrafico = Parametro.toArray(data[1]);
        if (this.tarjetaGrafico != null) {
          this.tarjetaGrafico.forEach((componente: any) => {
            this.cardColor = componente.codigo;
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
