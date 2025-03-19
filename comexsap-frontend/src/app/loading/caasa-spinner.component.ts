import { Component, Input, OnInit } from '@angular/core';

declare var $:any;

@Component({
    selector: 'app-caasa-spinner',
    templateUrl: './caasa-spinner.component.html',
    styleUrls: ['./caasa-spinner.component.scss']
})
export class CaasaSpinnerComponent implements OnInit {

  @Input() flagLoading: boolean = false;

  constructor() {

  }


  ngOnInit() {


  }

  ngAfterViewInit() {

  }

}
