import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MessageService } from 'primeng/api';
import { of, Subject } from 'rxjs';
import { catchError, debounceTime, distinctUntilChanged, filter, switchMap, tap } from 'rxjs/operators';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { Utils } from 'src/app/utils/utils';
import { ConfiguracionService } from '../configuracion.sevice';
import { Puerto } from '../to/puerto';

@Component({
  selector: 'app-modal-puerto',
  templateUrl: './modal-puerto.component.html',
  styleUrls: ['./modal-puerto.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      multi: true,
      useExisting: ModalPuertoComponent
    }
  ]
})
export class ModalPuertoComponent implements OnInit, OnDestroy, ControlValueAccessor {

  @Input() item: Puerto = null;
  @Output() itemChange : EventEmitter<Puerto> = new EventEmitter();

  @Input('placeholder') placeholder: string = '';
  @Input() disabled: boolean = false;

  loadingData: boolean = false;
  minSearchCharacters = 1;

  filter: string;
  filterInput$ = new Subject<string>();
  listItems: Puerto[] = [];

  selectedItem: Puerto = null;

  onChange = (item: Puerto) => {};
  onTouched = () => {};
  touched = false;

  constructor(
    public messageService: MessageService,
    private modalService: NgbModal,
    private settingsService: SettingsService,
    private configuracionService: ConfiguracionService
  ) {
  }

  writeValue(item: Puerto): void {
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
      this.configuracionService.listarPuertos("").toPromise()
    ]).then(
      (data : any[]) => {
        this.settingsService.ocultarSpinner();
        this.listItems = Puerto.toArray(data[0]);
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

  clear() {
    this.selectedItem = null;
    this.item = this.selectedItem;
    this.onChange(this.selectedItem);
    this.itemChange.emit(this.selectedItem);
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
        return this.configuracionService.listarPuertos(term).pipe(
          catchError(() => of([])),
          tap(() => this.loadingData = false)
        )
      })
    );

    search$.subscribe((data: any[]) => {
      this.listItems = Puerto.toArray(data);
    });
  }

  unselectItems(codigo: string) {
    this.listItems.forEach(element => {
      if(element.checked && element.codigo != codigo) {
        element.checked = false;
      }
    });
  }

  onChangeItem(item: Puerto) {
    if(item.checked) {
      this.selectedItem = item;
      this.unselectItems(item.codigo);
    } else {
      this.selectedItem = null;
    }
  }

  onSelect() {
    if(Utils.isNullOrUndefined(this.selectedItem)) {
      this.messageService.add({
        severity: "warn",
        summary: 'Alerta',
        detail: 'Por favor, seleccione un puerto'
      });
      return;
    }
    this.item = this.selectedItem;
    this.onChange(this.selectedItem);
    this.itemChange.emit(this.selectedItem);
  }

}
