import { Component, ElementRef, EventEmitter, Input, OnDestroy, OnInit, Output, ViewChild } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { MessageService } from 'primeng/api';
import { of, Subject } from 'rxjs';
import { catchError, debounceTime, distinctUntilChanged, filter, switchMap, tap } from 'rxjs/operators';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { Utils } from 'src/app/utils/utils';
import { ConfiguracionService } from '../configuracion.sevice';
import { Material } from '../to/material';

@Component({
  selector: 'app-modal-material',
  templateUrl: './modal-material.component.html',
  styleUrls: ['./modal-material.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      multi: true,
      useExisting: ModalMaterialComponent
    }
  ]
})
export class ModalMaterialComponent implements OnInit, OnDestroy, ControlValueAccessor {
  @Input() item: Material = null;
  @Output() itemChange : EventEmitter<Material> = new EventEmitter();
  @Output() onClear : EventEmitter<string> = new EventEmitter();
  @Output() onPasted : EventEmitter<string> = new EventEmitter();

  @Input('placeholder') placeholder: string = '';
  @Input() disabled: boolean = false;

  loadingData: boolean = false;
  minSearchCharacters = 1;

  filter: string;
  filterInput$ = new Subject<string>();
  listItems: Material[] = [];

  selectedItem: Material = null;

  onChange = (item: Material) => {};
  onTouched = () => {};
  touched = false;

  constructor(
    private messageService: MessageService,
    private modalService: NgbModal,
    private settingsService: SettingsService,
    private configuracionService: ConfiguracionService
  ) {
  }

  writeValue(item: Material): void {
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
      this.configuracionService.listarMateriales("").toPromise()
    ]).then(
      (data : any[]) => {
        this.settingsService.ocultarSpinner();
        this.listItems = Material.toArray(data[0]);
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
        return this.configuracionService.listarMateriales(term).pipe(
          catchError(() => of([])),
          tap(() => this.loadingData = false)
        )
      })
    );

    search$.subscribe((data: any[]) => {
      this.listItems = Material.toArray(data);
    });
  }

  unselectItems(codigo: string) {
    this.listItems.forEach(element => {
      if(element.checked && element.codigo != codigo) {
        element.checked = false;
      }
    });
  }

  onChangeItem(item: Material) {
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
        detail: 'Por favor, seleccione un material'
      });
      return;
    }
    this.item = this.selectedItem;
    this.onChange(this.selectedItem);
    this.itemChange.emit(this.selectedItem);
  }

  searchCode(event) {
    const codigo = event.target.value;
    if(codigo != null && codigo != "") {
      this.settingsService.mostrarSpinner();
      this.configuracionService.obtenerMaterial(Utils.toCodeMaterial(codigo)).subscribe((data) => {
        this.settingsService.ocultarSpinner();
        if(data == null) {
          this.messageService.add({
            severity: "warn",
            summary: "Alerta",
            detail: "¡Material no encontrado!"
          });
          this.onClear.emit("Limpiar posicion");
        } else {
          this.selectedItem = Material.toObject(data);
          this.onSelect();
        }
      }, (error) => {
        this.settingsService.ocultarSpinner();
        this.messageService.add({
          severity: "error",
          summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
          detail: JSON.stringify(error)
        });
      });
    }
  }

  onPastedFromExcel(event: ClipboardEvent) {
    const data = event.clipboardData.getData("text/plain");
    this.onPasted.emit(data);
  }

  removeLeftZeros(codigo: string) {
    return Utils.removeLeftZeros(codigo);
  }
}
