import { Routes } from '@angular/router';
import { ListarPedidoFirmeComponent } from './listar-pedido-firme/listar-pedido-firme.component';
import { CrearPedidoFirmeComponent } from './crear-pedido-firme/crear-pedido-firme.component';
import { EditarPedidoFirmeComponent } from './editar-pedido-firme/editar-pedido-firme.component';

export const PedidoFirmeRoutes: Routes = [
  {
    path: 'pedidofirme',
    children: [
      { path: '', redirectTo: 'listar-pedidofirme', pathMatch: 'full' },
      {
        path: 'listar-pedidofirme',
        component: ListarPedidoFirmeComponent,
        data: {
          breadcrumb: 'Lista de Pedido Firme'
        }
      },
      {
        path: 'crear-pedidofirme',
        component: CrearPedidoFirmeComponent,
        data: {
          breadcrumb: 'Crear Pedido Firme'
        }
      },
      {
        path: 'editar-pedidofirme/:id',
        component: EditarPedidoFirmeComponent,
        data: {
          breadcrumb: 'Editar Pedido Firme'
        }
      }
    ]
  }
];
