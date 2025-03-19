import { Routes } from '@angular/router';
import { ListarServicioComponent } from './servicios/listar-servicio/listar-servicio.component';
import { CrearServicioComponent } from './servicios/crear-servicio/crear-servicio.component';
import { EditarServicioComponent } from './servicios/editar-servicio/editar-servicio.component';
import { ListarCorreoComponent } from './correos/listar-correo/listar-correo.component';
import { CrearCorreoComponent } from './correos/crear-correo/crear-correo.component';
import { EditarCorreoComponent } from './correos/editar-correo/editar-correo.component';
import { ListarParametroComponent } from './parametros/listar-parametro/listar-parametro.component';
import { CrearParametroComponent } from './parametros/crear-parametro/crear-parametro.component';
import { EditarParametroComponent } from './parametros/editar-parametro/editar-parametro.component';
export const MaestrosRoutes: Routes = [
  {
    path: 'maestros',
    children: [
      { path: "", redirectTo: "servicios/listar-servicio", pathMatch: "full" },
      {
        path: "servicios",
        children: [
          { path: "", redirectTo: "listar-servicio", pathMatch: "full"},
          { path: "listar-servicio", component: ListarServicioComponent },
          { path: "crear-servicio", component: CrearServicioComponent },
          { path: "editar-servicio/:id", component: EditarServicioComponent }
        ]
      },
      {
        path: "correos",
        children: [
          { path: "", redirectTo: "listar-correo", pathMatch: "full"},
          { path: "listar-correo", component: ListarCorreoComponent },
          { path: "crear-correo", component: CrearCorreoComponent },
          { path: "editar-correo/:id", component: EditarCorreoComponent }
        ]
      },
      {
        path: "parametros",
        children: [
          { path: "", redirectTo: "listar-parametro", pathMatch: "full"},
          { path: "listar-parametro", component: ListarParametroComponent },
          { path: "crear-parametro", component: CrearParametroComponent },
          { path: "editar-parametro/:id", component: EditarParametroComponent }
        ]
      },
    ]
  },

  {
    path: 'maestros',
    children: [
      { path: "", redirectTo: "servicios/listar-servicio", pathMatch: "full"},
      {
        path: "servicios",
        children: [
          { path: "", redirectTo: "listar-servicio", pathMatch: "full"},
          { path: "listar-servicio", component: ListarServicioComponent },
          { path: "crear-servicio", component: CrearServicioComponent },
          { path: "editar-servicio/:id", component: EditarServicioComponent }
        ]
      },
      {
        path: "correos",
        children: [
          { path: "", redirectTo: "listar-correo", pathMatch: "full"},
          { path: "listar-correo", component: ListarCorreoComponent },
          { path: "crear-correo", component: CrearCorreoComponent },
          { path: "editar-correo/:id", component: EditarCorreoComponent }
        ]
      },
      {
        path: "parametros",
        children: [
          { path: "", redirectTo: "listar-parametro", pathMatch: "full"},
          { path: "listar-parametro", component: ListarParametroComponent },
          { path: "crear-parametro", component: CrearParametroComponent },
          { path: "editar-parametro/:id", component: EditarParametroComponent }
        ]
      },
    ]
  }
];
