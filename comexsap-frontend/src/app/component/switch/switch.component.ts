import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';

@Component({
  selector: 'app-switch',
  templateUrl: './switch.component.html',
  styleUrls: ['./switch.component.scss'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      multi: true,
      useExisting: SwitchComponent
    }
  ]
})
export class SwitchComponent implements ControlValueAccessor  {
  @Input() title: string = '';
  @Input() isRequired: boolean = false;
  @Input() value: boolean = false;
  @Input() disabled: boolean = false;

  onChange = (value: boolean) => {};

  onTouched = () => {};

  touched = false;

  constructor() { }

  writeValue(value: boolean): void {
    this.value = value;
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

  change(event) {
    this.onChange(event.target.checked);
  }
}
