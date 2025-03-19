import { ControlGastosModule } from './pages/controlgastos/controlgastos.module';
import { Routes } from "@angular/router";
import { FullComponent } from "./layouts/full/full.component";
import { BlankComponent } from "./layouts/blank/blank.component";
import { AuthGuard } from './auth.guard';
import { LoginComponent } from "./login/login.component";

export const Approutes: Routes = [
  {
    path: "",
    component: FullComponent,
    children: [
      { path: "", redirectTo: "/inicio/inicio-comex", pathMatch: "full"},
      {
        path: "inicio",
        loadChildren: () => import("./pages/inicio/inicio.module").then((m) => m.InicioModule),
        canActivate: [AuthGuard]
      },
      {
        path: 'exportaciones',
        loadChildren: () => import('./pages/cotizacion/cotizacion.module').then(m => m.CotizacionModule),
        canActivate: [AuthGuard]
      },
      {
        path: 'exportaciones',
        loadChildren: () => import('./pages/pedidofirme/pedidofirme.module').then(m => m.PedidoFirmeModule),
        canActivate: [AuthGuard]
      },
      {
        path: 'exportaciones',
        loadChildren: () => import('./pages/documentomaritimo/documentomaritimo.module').then(m => m.DocumentoMaritimoModule),
        canActivate: [AuthGuard]
      },
      {
        path: 'exportaciones',
        loadChildren: () => import('./pages/documentoterrestre/documentoterrestre.module').then(m => m.DocumentoTerrestreModule),
        canActivate: [AuthGuard]
      },
      {
        path: 'exportaciones',
        loadChildren: () => import('./pages/documentoaereo/documentoaereo.module').then(m => m.DocumentoAereoModule),
        canActivate: [AuthGuard]
      },
      {
        path: 'exportaciones',
        loadChildren: () => import('./pages/packinglist/packinglist.module').then(m => m.PackingListModule),
        canActivate: [AuthGuard]
      },
      {
        path: 'exportaciones',
        loadChildren: () => import('./pages/controlgastos/controlgastos.module').then(m => m.ControlGastosModule),
        canActivate: [AuthGuard]
      },
      {
        path: '',
        loadChildren: () => import('./pages/maestros/maestros.module').then(m => m.MaestrosModule),
        canActivate: [AuthGuard]
      },
      {
        path: '',
        loadChildren: () => import('./pages/reportes/reportes.module').then(m => m.ReportesModule),
        canActivate: [AuthGuard]
      }
    ],
  },
  {
    path: '**',
    redirectTo: '/login'
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: "",
    component: BlankComponent,
    children: [
      {
        path: "authentication",
        loadChildren: () =>
          import("./authentication/authentication.module").then(
            (m) => m.AuthenticationModule
          ),
      },
    ],
  },
  {
    path: "**",
    redirectTo: "/authentication/404",
  },
];
