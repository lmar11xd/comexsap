import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { SettingsService } from 'src/app/core/settings/settings.service';
import { LISTAR_PEDIDOFIRME } from 'src/app/shared/breadcrumb/breadcrumb';
import { Breadcrumb2Service } from 'src/app/shared/breadcrumb2.service';

@Component({
  selector: 'app-inicio-comex',
  templateUrl: './inicio-comex.component.html',
  styleUrls: ['./inicio-comex.component.scss']
})
export class InicioComexComponent implements OnInit {

  fechaActual = new Date();
  
  constructor(
    private router: Router,
    private breadcrumb2Service: Breadcrumb2Service,
    public messageService: MessageService
  ) { }

  ngOnInit(): void {
    this.inicializarBreadcrumb();
  }

  inicializarBreadcrumb(){
    this.breadcrumb2Service.addBreadcrumbs([]);
  }
  
  
}
