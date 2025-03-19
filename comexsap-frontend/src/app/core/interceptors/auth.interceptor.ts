import { Injectable } from '@angular/core';
import {
  HttpEvent, HttpInterceptor, HttpHandler, HttpRequest
} from '@angular/common/http';

import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import { SesionLocalService } from '../seguridad/sesion-local.service';
import { MessageService } from 'primeng/api';
import { SettingsService } from '../settings/settings.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authService: SesionLocalService,
    private messageService: MessageService,
    private router: Router,
    private settingsService: SettingsService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpEvent<any>> {


    return next.handle(req).pipe(
      catchError(e => {
        if (e.status == 401) {

          if (this.authService.isAuthenticated()) {
            this.authService.borrarSesion();
          }
          this.router.navigate(['/login']);
        }

        if (e.status == 403) {
          this.messageService.add({
            severity: "error",
            summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
            detail: `Hola ${this.authService.getStrPv_username()} no tienes acceso a este recurso!`
            });
          this.router.navigate(['/inicio']);
        }
        return throwError(e);
      })
    );
  }
}
