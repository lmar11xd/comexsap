import { Routes } from '@angular/router';
import { ListarComponent } from './listar/listar.component';
import { CrearComponent } from './crear/crear.component';
import { EditarComponent } from './editar/editar.component';

export const DocumentoTerrestreRoutes: Routes = [
  {
    path: 'documentoterrestre',
    children: [
      { path: "", redirectTo: "listar", pathMatch: "full"},
      {
        path: 'listar',
        component: ListarComponent,
        data: {
          breadcrumb: 'Lista de Documentos Terrestres'
        }
      },
      {
        path: 'crear',
        component: CrearComponent,
        data: {
          breadcrumb: 'Crear Documento Terrestre'
        }
      },
      {
        path: 'editar/:id',
        component: EditarComponent,
        data: {
          breadcrumb: 'Editar Documento Terrestre'
        }
      },
    ]
  }
];
