import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';

@Component({
  selector: 'app-input-chip',
  templateUrl: './input-chip.component.html',
  styleUrls: ['./input-chip.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      multi: true,
      useExisting: InputChipComponent
    }
  ]
})
export class InputChipComponent implements OnInit, OnDestroy, ControlValueAccessor {
  @Input() maxItems = 5;//Items por defecto
  @Input() items: string[] = [];
  @Input() disabled: boolean = false;
  @Output() selectedItems: EventEmitter<string[]> = new EventEmitter();

  value: string = "";

  onChange = (value: boolean) => {};

  onTouched = () => {};

  touched = false;

  constructor() { }

  writeValue(items: string[]): void {
    this.items = items;
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

  ngOnInit(): void {}

  ngOnDestroy(): void {
    this.selectedItems.unsubscribe();
  }

  addChip() {
    if(this.value.trim() == "") return;
    if(this.items.length < this.maxItems && !this.existItem(this.value)) {
      this.items.push(this.value);
      this.selectedItems.emit(this.items);
    }
    this.value = "";
  }

  deleteChip(item: string) {
    this.items = this.items.filter(x => x != item);
    this.selectedItems.emit(this.items);
  }

  existItem(value: string): boolean {
    const list = this.items.filter(x => x == value);
    if (list.length > 0) return true;
    return false;
  }
}
