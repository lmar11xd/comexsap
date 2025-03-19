import { Component, Input, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-observacion-sap',
  templateUrl: './observacion-sap.component.html',
  styleUrls: ['./observacion-sap.component.scss']
})
export class ObservacionSapComponent implements OnInit {
  @Input() mensaje: string;

  mensajes : string[] = [];

  constructor(private modalService: NgbModal) { }

  ngOnInit(): void {
  }

  mostrarModal(content: string) {
    if(this.mensaje != null && this.mensaje != '') {
      this.mensajes = this.mensaje.split('***');
      this.mensajes = this.mensajes.filter(msg => msg != '');
    }
    this.modalService.open(content, {size: 'xs', centered: true});
  }

}
