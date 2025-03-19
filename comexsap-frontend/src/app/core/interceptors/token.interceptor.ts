import { Injectable } from '@angular/core';
import {
  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest
} from '@angular/common/http';
import { fromPromise } from 'rxjs/observable/fromPromise';
import { Observable, throwError } from 'rxjs';
import { SesionLocalService } from '../seguridad/sesion-local.service';
import { catchError, switchMap } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  router: Router;
  constructor(private authService: SesionLocalService) { }

//   intercept(req: HttpRequest<any>, next: HttpHandler) : Observable<HttpEvent<any>>{
//     return fromPromise(this.authService.getToken())
//           .pipe(switchMap(token => {
//                const headers = req.headers
//                         .set('Authorization', 'Bearer ' + token)
//                         .append('Content-Type', 'application/json');
//                const reqClone = req.clone({
//                  headers
//                 });
//               return next.handle(reqClone);
//          }));
//  }

  intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpEvent<any>> {
    let token = this.authService.getToken();
    if (token != null) {
      const authReq = req.clone({
        headers: req.headers.set('Authorization', 'Bearer ' + token)
        //.append('Content-Type', 'application/json')
      });

      return next.handle(authReq)
      // .pipe(
    //   catchError(error=>{
    //     let mensajeError = '';
    //     if(error instanceof ErrorEvent){
    //       mensajeError = 'Error al consultar servicio';
    //       this.mensajeService.mensajeError(mensajeError);
    //     }else{
    //       mensajeError = 'Su sesión de usuario a expirado o se ha perdido su conexión de red. Por favor ingrese nuevamente al sistema';
    //       // this.router.navigate(['']);
    //       this.authService.borrarSesion();
    //       this.mensajeService.mensajeError(mensajeError);
    //     }

    //     return throwError(mensajeError);
    //   }))
      ;
    }

    return next.handle(req)
    // .pipe(
    //   catchError(error=>{
    //     let mensajeError = '';
    //     if(error instanceof ErrorEvent){
    //       mensajeError = 'Error al consultar servicio';
    //       this.mensajeService.mensajeError(mensajeError);
    //     }else{
    //       mensajeError = 'Su sesión de usuario a expirado o se ha perdido su conexión de red. Por favor ingrese nuevamente al sistema';
    //       // this.router.navigate(['']);
    //       this.authService.borrarSesion();
    //       this.mensajeService.mensajeError(mensajeError);
    //     }

    //     return throwError(mensajeError);
    //   }))
      ;
  }
}
