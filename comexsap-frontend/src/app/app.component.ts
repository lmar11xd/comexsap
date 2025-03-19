import { Component } from '@angular/core';
import { NgSelectConfig } from '@ng-select/ng-select';
import { PrimeNGConfig } from 'primeng/api';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'app';
  typeSelected: string = 'ball-spin-fade-rotating';
  textoSpinner: string = 'Procesando...';

  ngOnInit() {
    this.setLocale( this.primengConfig, 'es' );
  }

  constructor(private config: NgSelectConfig, public primengConfig: PrimeNGConfig, ) {
    this.config.notFoundText = 'No se encuentran registros';
    this.config.appendTo = 'body';
    // set the bindValue to global config when you use the same
    // bindValue in most of the place.
    // You can also override bindValue for the specified template
    // by defining `bindValue` as property
    // Eg : <ng-select bindValue="some-new-value"></ng-select>
    this.config.bindValue = 'value';
  }

  setLocale( config: PrimeNGConfig, locale: string ): void {
    switch( locale.toLowerCase( ) ) {
      case 'en': {
        // already english
        const dow: string[] = config.getTranslation( 'dayNames' );
        if( dow[0] !== 'Sunday' ) {
          const msg: string = 'Default locale not EN.';
        }
        break;
      }
      case 'es': {
        config.setTranslation( {
          startsWith: 'Comienza con',
          contains: 'Contiene',
          notContains: 'No contiene',
          endsWith: 'Termina con',
          equals: 'Igual',
          notEquals: 'No es igual',
          noFilter: 'Sin filtro',
          lt: 'Menos que',
          lte: 'Menos que o igual a',
          gt: 'Mas grande que',
          gte: 'Mayor qué o igual a',
          is: 'Es',
          isNot: 'No es',
          before: 'Before',
          after: 'After',
          apply: 'Aplicar',
          matchAll: 'Coincidir con todos',
          matchAny: 'Coincidir con cualquiera',
          addRule: 'Agregar regla',
          removeRule: 'Eliminar regla',
          accept: 'Si',
          reject: 'No',
          choose: 'Escoger',
          upload: 'Subir',
          cancel: 'Cancelar',
          dayNames: [ 'Domingo','Lunes','Martes','Miércoles','Jueves','Viernes','Sábado' ],
          dayNamesShort: [ 'dom','lun','mar','mié','jue','vie','sáb' ],
          dayNamesMin: [ 'D','L','M','X','J','V','S' ],
          monthNames: [ 'Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre' ],
          monthNamesShort: [ 'Ene','Feb','Mar','Abr','May','Jun','Jul','Ago','Sep','Oct','Nov','Dic' ],
          today: 'Hoy',
          clear: 'Limpiar',
          weekHeader: 'Sm'
        });
        break;
      }
      case 'de': {
        config.setTranslation( {
          startsWith: 'Beginnt mit',
          contains: 'Enthält',
          notContains: 'Enthält nicht',
          endsWith: 'Endet mit',
          equals: 'Gleich',
          notEquals: 'Kein Filter',
          noFilter: 'Kein Filter',
          lt: 'Weniger als',
          lte: 'Weniger als oder gleich',
          gt: 'Größer als',
          gte: 'Größer als oder gleich wie',
          is: 'Ist',
          isNot: 'Ist nicht',
          before: 'Vor',
          after: 'Nach dem',
          apply: 'Anwenden',
          matchAll: 'Alle zusammenbringen',
          matchAny: 'Passen Sie zu einem',
          addRule: 'Regel hinzufügen',
          removeRule: 'Regel entfernen',
          accept: 'Ja',
          reject: 'Nein',
          choose: 'Wählen',
          upload: 'Hochladen',
          cancel: 'Stornieren',
          dayNames: ['Sonntag', 'Montag', 'Dienstag', 'Mittwoch', 'Donnerstag', 'Freitag', 'Samastag'],
          dayNamesShort: ['Son', 'Mon', 'Die', 'Mit', 'Don', 'Fre', 'Sam'],
          dayNamesMin: ['So','Mo','Di','Mi','Do','Fr','Sa'],
          monthNames: [ 'Januar','Februar','März','April','Mai','Juni','Juli','August','September','Oktober','November','Dezember' ],
          monthNamesShort: [ 'Jan', 'Feb', 'Mär', 'Apr', 'Mai', 'Jun','Jul', 'Aug', 'Sep', 'Okt', 'Nov', 'Dez' ],
          today: 'Heute',
          clear: 'Löschen',
          weekHeader: 'Wo'
        });
        break;
      }
      default: {
        const msg: string = `Locale: '${locale}' not available.`;
        break;
      }
    }
  }
}
