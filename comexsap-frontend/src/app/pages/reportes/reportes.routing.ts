import { Routes } from '@angular/router';
import { ReporteTerrestreComponent } from './reporte-terrestre/reporte-terrestre.component';
import { CargasueltaComponent } from './reporte-maritimo/cargasuelta/cargasuelta.component';
import { ContenedoresComponent } from './reporte-maritimo/contenedores/contenedores.component';

export const ReportesRoutes: Routes = [
  {
    path: 'reportes',
    children: [
      { path: "", redirectTo: "reportemaritimo", pathMatch: "full" },
      {
        path: 'reportemaritimo',
        children: [
          { path: "", redirectTo: "contenedores", pathMatch: "full" },
          { path: "contenedores", component: ContenedoresComponent },
          { path: "cargasuelta", component: CargasueltaComponent }
        ]
      },
      {
        path: 'reporteterrestre',
        component: ReporteTerrestreComponent,
        data: {
          breadcrumb: 'Reporte Terrestre'
        }
      }
    ]
  }
];
