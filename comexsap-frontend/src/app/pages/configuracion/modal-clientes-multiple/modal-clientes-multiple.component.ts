import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MessageService } from 'primeng/api';
import { of, Subject } from 'rxjs';
import { catchError, debounceTime, distinctUntilChanged, filter, switchMap, tap } from 'rxjs/operators';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { ConfiguracionService } from '../configuracion.sevice';
import { Cliente } from '../to/cliente';

@Component({
  selector: 'app-modal-clientes-multiple',
  templateUrl: './modal-clientes-multiple.component.html',
  styleUrls: ['./modal-clientes-multiple.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      multi: true,
      useExisting: ModalClientesMultipleComponent
    }
  ]
})
export class ModalClientesMultipleComponent implements OnInit, OnDestroy, ControlValueAccessor {
  @Input() selectedItems: Cliente[] = [];
  @Output() itemChange : EventEmitter<Cliente[]> = new EventEmitter();

  @Input('placeholder') placeholder: string = '';
  @Input() disabled: boolean = false;

  loadingData: boolean = false;
  minSearchCharacters = 1;

  filter: string;
  filterInput$ = new Subject<string>();
  listItems: Cliente[] = [];

  onChange = (items: Cliente[]) => {};
  onTouched = () => {};
  touched = false;

  constructor(
    public messageService: MessageService,
    private modalService: NgbModal,
    private settingsService: SettingsService,
    private configuracionService: ConfiguracionService
  ) {
  }

  writeValue(items: Cliente[]): void {
    this.selectedItems = items;
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

  ngOnDestroy(): void {
    this.itemChange.unsubscribe();
  }

  ngOnInit(): void {
    this.search();
  }

  initData() {
    this.filter = "";
    this.settingsService.mostrarSpinner();
    Promise.all([
      this.configuracionService.listarClientes("").toPromise()
    ]).then(
      (data : any[]) => {
        this.settingsService.ocultarSpinner();
        this.listItems = Cliente.toArray(data[0]);
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

  open(content: string) {
    if(this.disabled) return;
    this.modalService.open(content, { size: 'lg' });
    this.initData();
  }

  onChangeFilter() {
    this.filterInput$.next(this.filter);
  }

  search() {
    const search$ = this.filterInput$.pipe(
      filter((q) => {
        return q !== null && q.length >= this.minSearchCharacters;
      }),
      //distinctUntilChanged(),
      debounceTime(800),
      tap(() => this.loadingData = true),
      switchMap(term => {
        return this.configuracionService.listarClientes(term).pipe(
          catchError(() => of([])),
          tap(() => this.loadingData = false)
        )
      })
    );

    search$.subscribe((data: any[]) => {
      this.listItems = Cliente.toArray(data);
    });
  }

  onChangeItem(item: Cliente) {
    if(item.checked) {
      this.selectedItems.push(item);
    } else {
      this.selectedItems = this.selectedItems.filter(o => o.codigo != item.codigo);
    }
  }

  onSelect() {
    if(this.selectedItems.length == 0) {
      this.messageService.add({
        severity: "warn",
        summary: 'Alerta',
        detail: 'Por favor, seleccione un cliente'
      });
      return;
    }
    this.onChange(this.selectedItems);
    this.itemChange.emit(this.selectedItems);
  }

  deleteItem(item: Cliente) {
    this.selectedItems = this.selectedItems.filter(x => x.codigo != item.codigo);
    this.itemChange.emit(this.selectedItems);
  }
}
