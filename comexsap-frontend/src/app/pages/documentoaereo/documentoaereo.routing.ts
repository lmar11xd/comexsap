import { Routes, RouterModule } from '@angular/router';
import { ListarDocumentoAereoComponent } from './listar/listar.component';
import { CrearDocumentoAereoComponent } from './crear/crear.component';
import { EditarDocumentoAereoComponent } from './editar/editar.component';

export const DocumentoAereoRoutes: Routes = [
  {
    path: 'documentoaereo',
    children: [
      { path: "", redirectTo: "listar", pathMatch: "full"},
      {
        path: 'listar',
        component: ListarDocumentoAereoComponent,
        data: {
          breadcrumb: 'Lista de Documentos Aéreos'
        }
      },
      {
        path: 'crear',
        component: CrearDocumentoAereoComponent,
        data: {
          breadcrumb: 'Crear Documentos Aéreos'
        }
      },
      {
        path: 'editar/:id',
        component: EditarDocumentoAereoComponent,
        data: {
          breadcrumb: 'Editar Documentos Aéreos'
        }
      }
    ]
  }
];
