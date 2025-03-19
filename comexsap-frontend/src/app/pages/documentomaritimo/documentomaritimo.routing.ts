import { Routes } from '@angular/router';
import { ListarContenedorComponent } from './contenedores/listar/listar.component';
import { CrearContenedorComponent } from './contenedores/crear/crear.component';
import { EditarContenedorComponent } from './contenedores/editar/editar.component';
import { ListarCargaSueltaComponent } from './cargasuelta/listar/listar.component';
import { CrearCargaSueltaComponent } from './cargasuelta/crear/crear.component';
import { EditarCargaSueltaComponent } from './cargasuelta/editar/editar.component';
import { ListarIntercompanyComponent } from './intercompany/listar/listar.component';
import { EditarIntercompanyComponent } from './intercompany/editar/editar.component';
import { CrearIntercompanyComponent } from './intercompany/crear/crear.component';

export const DocumentoMaritimoRoutes: Routes = [
  {
    path: 'documentomaritimo',
    children: [
      { path: "", redirectTo: "contenedores/listar", pathMatch: "full"},
      { path: "contenedores", redirectTo: "contenedores/listar", pathMatch: "full"},
      { path: "cargasuelta", redirectTo: "cargasuelta/listar", pathMatch: "full"},
      {
        path: 'contenedores/listar',
        component: ListarContenedorComponent,
        data: {
          breadcrumb: 'Lista de Documentos Marítimos'
        }
      },
      {
        path: 'contenedores/crear',
        component: CrearContenedorComponent,
        data: {
          breadcrumb: 'Crear Documento Marítimo'
        }
      },
      {
        path: 'contenedores/editar/:id',
        component: EditarContenedorComponent,
        data: {
          breadcrumb: 'Editar Documento Marítimo'
        }
      },

      { path: "cargasuelta", redirectTo: "cargasuelta/listar", pathMatch: "full"},
      {
        path: 'cargasuelta/listar',
        component: ListarCargaSueltaComponent,
        data: {
          breadcrumb: 'Lista de Documentos Marítimos'
        }
      },
      {
        path: 'cargasuelta/crear',
        component: CrearCargaSueltaComponent,
        data: {
          breadcrumb: 'Crear Documento Marítimo'
        }
      },
      {
        path: 'cargasuelta/editar/:id',
        component: EditarCargaSueltaComponent,
        data: {
          breadcrumb: 'Editar Documento Marítimo'
        }
      },

      { path: "intercompany", redirectTo: "intercompany/listar", pathMatch: "full"},
      {
        path: 'intercompany/listar',
        component: ListarIntercompanyComponent,
        data: {
          breadcrumb: 'Lista de Documentos Marítimos'
        }
      },
      {
        path: 'intercompany/crear',
        component: CrearIntercompanyComponent,
        data: {
          breadcrumb: 'Crear Documento Marítimo'
        }
      },
      {
        path: 'intercompany/editar/:id',
        component: EditarIntercompanyComponent,
        data: {
          breadcrumb: 'Editar Documento Marítimo'
        }
      },
    ]
  }
];
