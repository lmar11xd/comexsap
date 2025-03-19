import { Routes, RouterModule } from '@angular/router';
import { ListarControlGastosContenedoresComponent } from './contenedores/listar/listar.component';
import { RegistrarControlGastosContenedoresComponent } from './contenedores/registrar/registrar.component';
import { ListarControlGastosCargaSueltaComponent } from './cargasuelta/listar/listar.component';
import { RegistrarControlGastosCargaSueltaComponent } from './cargasuelta/registrar/registrar.component';

export const ControlGastosRoutes: Routes = [
  {
    path: 'controlgastos',
    children: [
      { path: "", redirectTo: "contenedores/listar", pathMatch: "full"},
      { path: "contenedores", redirectTo: "contenedores/listar", pathMatch: "full"},
      { path: "cargasuelta", redirectTo: "cargasuelta/listar", pathMatch: "full"},
      {
        path: 'contenedores/listar',
        component: ListarControlGastosContenedoresComponent,
        data: {
          breadcrumb: 'Reporte Marítimo - Contenedores'
        }
      },
      {
        path: 'contenedores/registrar/:ids',
        component: RegistrarControlGastosContenedoresComponent,
        data: {
          breadcrumb: 'Registrar Control de Gastos'
        }
      },
      {
        path: 'cargasuelta/listar',
        component: ListarControlGastosCargaSueltaComponent,
        data: {
          breadcrumb: 'Reporte Marítimo - Carga Suelta'
        }
      },
      {
        path: 'cargasuelta/registrar/:ids',
        component: RegistrarControlGastosCargaSueltaComponent,
        data: {
          breadcrumb: 'Registrar Control de Gastos'
        }
      }
    ]
  }
];
