import { Component, Input, OnInit } from '@angular/core';
import { SettingsService } from '../../core/settings/settings.service';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { CustomValidators } from 'ngx-custom-validators';

declare var $:any;

@Component({
    selector: 'app-caasa-spinner-simple',
    templateUrl: './caasa-spinner-simple.component.html',
    styleUrls: ['./caasa-spinner-simple.component.scss']
})
export class CaasaSpinnerSimpleComponent implements OnInit {

  @Input() flagLoading: boolean = false;

  constructor() {

  }


  ngOnInit() {


  }

  ngAfterViewInit() {

  }

}
