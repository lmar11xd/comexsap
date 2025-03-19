import { Routes } from '@angular/router';
import { CrearCotizacionComponent } from './crear-cotizacion/crear-cotizacion.component';
import { EditarCotizacionComponent } from './editar-cotizacion/editar-cotizacion.component';
import { ListarCotizacionComponent } from './listar-cotizacion/listar-cotizacion.component';

export const CotizacionRoutes: Routes = [
  {
    path: 'cotización',
    children: [
      { path: "", redirectTo: "listar-cotizacion", pathMatch: "full"},
      {
        path: 'listar-cotizacion',
        component: ListarCotizacionComponent,
        data: {
          breadcrumb: 'Lista de Cotizaciones'
        }
      },
      {
        path: 'crear-cotizacion',
        component: CrearCotizacionComponent,
        data: {
          breadcrumb: 'Crear Cotización'
        }
      },
      {
        path: 'editar-cotizacion/:id',
        component: EditarCotizacionComponent,
        data: {
          breadcrumb: 'Editar Cotización'
        }
      }
    ]
  }
];
