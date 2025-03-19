import { Routes } from '@angular/router';
import { ListarPackingListContenedorComponent } from './contenedores/listar/listar.component';
import { EditarPackingListContenedorComponent } from './contenedores/editar/editar.component';
import { ListarPackingListCargaSueltaComponent } from './cargasuelta/listar/listar.component';
import { EditarPackingListCargaSueltaComponent } from './cargasuelta/editar/editar.component';
import { ListarPackingListAereoComponent } from './aereo/listar/listar.component';
import { EditarPackingListAereoComponent } from './aereo/editar/editar.component';

export const PackingListRoutes: Routes = [
  {
    path: 'packinglist',
    children: [
      { path: "", redirectTo: "contenedores/listar", pathMatch: "full"},
      { path: "contenedores", redirectTo: "contenedores/listar", pathMatch: "full"},
      { path: "cargasuelta", redirectTo: "cargasuelta/listar", pathMatch: "full"},
      { path: "aereo", redirectTo: "aereo/listar", pathMatch: "full"},
      {
        path: 'contenedores/listar',
        component: ListarPackingListContenedorComponent,
        data: {
          breadcrumb: 'Lista Packing List'
        }
      },
      {
        path: 'contenedores/editar/:id',
        component: EditarPackingListContenedorComponent,
        data: {
          breadcrumb: 'Editar Packing List'
        }
      },
      {
        path: 'cargasuelta/listar',
        component: ListarPackingListCargaSueltaComponent,
        data: {
          breadcrumb: 'Lista Packing List'
        }
      },
      {
        path: 'cargasuelta/editar/:id',
        component: EditarPackingListCargaSueltaComponent,
        data: {
          breadcrumb: 'Editar Packing List'
        }
      },
      {
        path: 'aereo/listar',
        component: ListarPackingListAereoComponent,
        data: {
          breadcrumb: 'Lista Packing List'
        }
      },
      {
        path: 'aereo/editar/:id',
        component: EditarPackingListAereoComponent,
        data: {
          breadcrumb: 'Editar Packing List'
        }
      }
    ]
  }
];
