import { Routes } from '@angular/router';
import { InicioComexComponent } from './inicio-comex/inicio-comex.component';

export const InicioRoutes: Routes = [
  {
    path: '',
    children: [
      {
        path: 'inicio-comex',
        component: InicioComexComponent,
        data: {
          breadcrumb: 'Inicio Comex'
        }
      }
    ]
  }
];
