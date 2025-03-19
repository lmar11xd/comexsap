import { Injectable } from '@angular/core';
import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from "rxjs/operators";
import { SettingsService } from '../settings/settings.service';
import { MessageService } from 'primeng/api';


@Injectable()
export class ErrorCatchingInterceptor implements HttpInterceptor {

    constructor(
        private messageService: MessageService,
        public settingsService: SettingsService) {
    }

    intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

        return next.handle(request)
            .pipe(
                map(res => {
                    return res
                }),
                catchError((error: HttpErrorResponse) => {
                    let errorMsg = '';
                    if (error.error instanceof ErrorEvent) {
                        errorMsg = `Error: ${error.error.message}`;
                    } else {
                        errorMsg = `Error Code: ${error.status},  Message: ${error.message}`;
                    }
                    this.settingsService.ocultarSpinner();
                       this.messageService.add({
                        severity: "error",
                        summary: this.settingsService.MENSAJES['MENSAJE_ERROR_SERVIDOR'],
                        detail: errorMsg
                        });
                    return throwError(errorMsg);
                })
            )
    }
}
