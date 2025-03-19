import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { NgSelectModule } from '@ng-select/ng-select';
import { CommonModule } from "@angular/common";
import { TableModule } from 'primeng/table';
import { ModalClientesComponent } from "./modal-clientes/modal-clientes.component";
import { ModalClientesMultipleComponent } from "./modal-clientes-multiple/modal-clientes-multiple.component";
import { ModalMaterialComponent } from "./modal-material/modal-material.component";
import { ModalPuertoComponent } from "./modal-puerto/modal-puerto.component";
import { ModalRutasComponent } from "./modal-rutas/modal-rutas.component";
import { ModalComponentesComponent } from './modal-componentes/modal-componentes.component';
import { ModalPedidoDisponibleComponent } from './modal-pedido-disponible/modal-pedido-disponible.component';
import { ComponentsModule } from "src/app/component/component.module";
import { ModalSedesComponent } from './modal-sedes/modal-sedes.component';
import { ModalPedidoIntercompanyComponent } from "./modal-pedido-intercompany/modal-pedido-intercompany.component";
import { ModalEtiquetaComponent } from './modal-etiqueta/modal-etiqueta.component';

@NgModule({
  declarations: [
    ModalClientesComponent,
    ModalClientesMultipleComponent,
    ModalMaterialComponent,
    ModalPuertoComponent,
    ModalRutasComponent,
    ModalComponentesComponent,
    ModalPedidoDisponibleComponent,
    ModalPedidoIntercompanyComponent,
    ModalSedesComponent,
    ModalEtiquetaComponent
  ],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    TableModule,
    NgSelectModule,
    ComponentsModule
  ],
  exports: [
    ModalClientesComponent,
    ModalClientesMultipleComponent,
    ModalMaterialComponent,
    ModalPuertoComponent,
    ModalRutasComponent,
    ModalComponentesComponent,
    ModalPedidoDisponibleComponent,
    ModalPedidoIntercompanyComponent,
    ModalSedesComponent,
    ModalEtiquetaComponent
  ]
})
export class ConfiguracionModule { }
