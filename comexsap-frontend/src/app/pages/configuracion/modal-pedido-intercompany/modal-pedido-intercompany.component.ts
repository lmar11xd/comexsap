import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { ConfiguracionService } from '../configuracion.sevice';
import { Cliente } from '../to/cliente';
import { PedidoPosicion } from '../to/pedidoposicion';
import { Constantes } from 'src/app/utils/constantes';

@Component({
  selector: 'app-modal-pedido-intercompany',
  templateUrl: './modal-pedido-intercompany.component.html',
  styleUrls: ['./modal-pedido-intercompany.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      multi: true,
      useExisting: ModalPedidoIntercompanyComponent
    }
  ]
})
export class ModalPedidoIntercompanyComponent implements OnInit, ControlValueAccessor {

  @Input() idDespacho: number = Constantes.P_ID_CONTENEDOR;
  @Input() items: PedidoPosicion[];
  @Output() itemsChange : EventEmitter<PedidoPosicion[]> = new EventEmitter();

  @Input() disabled: boolean = false;

  onChange = (posiciones: PedidoPosicion[]) => {};
  onTouched = () => {};
  touched = false;

  listItems: PedidoPosicion[] = [];
  selectedItems: PedidoPosicion[] = [];

  filtro = {
    idDespacho: this.idDespacho,
    codigos: [],
    clientes: []
  };

  constructor(
    public messageService: MessageService,
    private modalService: NgbModal,
    private settingsService: SettingsService,
    private configuracionService: ConfiguracionService
  ) { }

  writeValue(posiciones: PedidoPosicion[]): void {
    this.items = posiciones;
  }
  registerOnChange(onChange: any): void {
    this.onChange = onChange;
  }
  registerOnTouched(onTouched: any): void {
    this.onTouched = onTouched;
  }
  setDisabledState?(disabled: boolean): void {
    this.disabled = disabled;
  }

  ngOnInit(): void {
    //this.buscarPedidos();
  }

  open(content: string) {
    this.filtro = {
      idDespacho: this.idDespacho,
      codigos: [],
      clientes: []
    };
    this.selectedItems = [];
    this.buscarPedidos();
    this.modalService.open(content, { size: 'lg', centered: true, backdrop: 'static', keyboard: false });
  }

  buscarPedidos() {
    this.settingsService.mostrarSpinner();
    Promise.all([
      this.configuracionService.listarPedidosIntercompanySap({ formulario: this.filtro }).toPromise()
    ]).then(
      (data : any[]) => {
        this.settingsService.ocultarSpinner();
        this.listItems = data[0];
      },
      (err) => {
        this.settingsService.ocultarSpinner();
        this.listItems = [];
        this.messageService.add({
          severity: "error",
			    summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
			    detail: JSON.stringify(err.message)
        });
      }
    );
  }

  seleccionarCodigos(items) {
    this.filtro.codigos = items;
  }

  seleccionarClientes(items: Cliente[]) {
    let codigos: string[] = [];
    items.forEach(cliente => {
      codigos.push(cliente.codigo);
    });
    this.filtro.clientes = codigos;
  }

  selectAll(event) {
    if(event.target.checked) {
      this.selectedItems = this.listItems;
    } else {
      this.selectedItems = [];
    }
    this.changeValueAll(event.target.checked);
  }

  changeValueAll(checked: boolean) {
    this.listItems.forEach(o => o.checked = checked);
  }

  onChangeItem(item: PedidoPosicion) {
    if(item.checked) {
      this.selectedItems.push(item);
    } else {
      this.selectedItems = this.selectedItems.filter(o => o.id != item.id);
    }
  }

  onSelect() {
    if(this.selectedItems.length == 0) {
      this.messageService.add({
        severity: "warn",
        summary: 'Alerta',
        detail: 'Por favor, seleccione al menos un registro'
      });
      return;
    }
    this.onChange(this.selectedItems);
    this.itemsChange.emit(this.selectedItems);
  }

}
