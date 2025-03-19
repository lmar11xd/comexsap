import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';
import { Centro } from '../to/centro';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MessageService } from 'primeng/api';
import { Subject, of } from 'rxjs';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { ConfiguracionService } from '../configuracion.sevice';
import { filter, distinctUntilChanged, debounceTime, tap, switchMap, catchError } from 'rxjs/operators';

@Component({
  selector: 'app-modal-sedes',
  templateUrl: './modal-sedes.component.html',
  styleUrls: ['./modal-sedes.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      multi: true,
      useExisting: ModalSedesComponent
    }
  ]
})
export class ModalSedesComponent implements OnInit, OnDestroy, ControlValueAccessor {
  @Input() item: Centro = null;
  @Output() itemChange : EventEmitter<Centro[]> = new EventEmitter();

  @Input('placeholder') placeholder: string = '';
  @Input() disabled: boolean = false;

  loadingData: boolean = false;
  minSearchCharacters = 1;

  filter: string;
  filterInput$ = new Subject<string>();
  listItems: Centro[] = [];

  selectedItems: Centro[] = [];

  onChange = (items: Centro[]) => {};
  onTouched = () => {};
  touched = false;

  constructor(
    public messageService: MessageService,
    private modalService: NgbModal,
    private settingsService: SettingsService,
    private configuracionService: ConfiguracionService
  ) {
  }

  writeValue(item: Centro): void {
    this.item = item;
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
      this.configuracionService.listarCentros("").toPromise()
    ]).then(
      (data : any[]) => {
        this.settingsService.ocultarSpinner();
        this.listItems = Centro.toArray(data[0]);
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
        return this.configuracionService.listarCentros(term).pipe(
          catchError(() => of([])),
          tap(() => this.loadingData = false)
        )
      })
    );

    search$.subscribe((data: any[]) => {
      this.listItems = Centro.toArray(data);
    });
  }

  onChangeItem(item: Centro) {
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
        detail: 'Por favor, seleccione una Sede'
      });
      return;
    }
    this.onChange(this.selectedItems);
    this.itemChange.emit(this.selectedItems);
  }

  deleteItem(item: Centro) {
    this.selectedItems = this.selectedItems.filter(x => x.codigo != item.codigo);
    this.itemChange.emit(this.selectedItems);
  }
}
