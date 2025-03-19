import { Injectable } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  Router,
  CanActivateChild
} from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { MessageService } from 'primeng/api';
import { Observable } from 'rxjs';
import { WindowRef } from './core/db/WindowRef';
import { SesionLocalService } from './core/seguridad/sesion-local.service';
import { SesionWebService } from './core/seguridad/sesion-web.service';
import { SettingsService } from './core/settings/settings.service';
import { VerticalSidebarService } from './shared/vertical-sidebar/vertical-sidebar.service';

@Injectable({
  providedIn: 'root'
})

export class AuthGuard implements CanActivate, CanActivateChild {

  constructor(private router: Router,
    private authServiceLocal: SesionLocalService,
    private authServiceWeb: SesionWebService,
    private settings: SettingsService,
    private winRef: WindowRef,
    private messageService: MessageService,
    private menuService: VerticalSidebarService,
    private cookieService: CookieService) { }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {
      if(this.settings.isMobileDevice()){
        if('/%23/login'!==state.url){
          if(this.authServiceLocal.tieneToken()){
            return true;
          }else{
            this.router.navigate(['login']);
            return false;
          }
        }
        return true;
      } else {
        this.settings.setUrlSessionOn("");
        let isMovil = true;
        let sesionSSO = this.authServiceLocal.obtenerSesionActual(isMovil);
        if (this.isEmpty(sesionSSO)) {
            this.settings.setUrlSessionOn(state.url);
            this.settings.mostrarSpinner();
            Promise.all([
              this.authServiceWeb.obtenerSesion().toPromise()
            ]).then(
              data => {
                  var response: any;
                  response = data[0];
                  this.settings.ocultarSpinner();
                  let sesionSSO = Object.assign({}, response.objPv_sesion);
                  this.menuService.setMenuItems(response.objPv_sesion.objPv_listaMenu);
                  this.authServiceLocal.grabarSesion(sesionSSO,isMovil);
                  if(this.settings.getUrlSessionOn().indexOf("home")>0){
                    this.router.navigate([""]);
                  }else{
                    this.router.navigate([this.settings.getUrlSessionOn()]);
                  }
                return true;
              },
              err => {
                this.settings.ocultarSpinner();
                return false;
              }
            );
        } else {
          this.authServiceLocal.grabarSesion(sesionSSO,false);
          this.menuService.setMenuItems(sesionSSO.objPv_listaMenu);
          return true;
        }
      }
  }

  canActivateChild(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    return this.canActivate(route, state);
  }

  isEmpty(obj) {
    for (var key in obj) {
      if (obj.hasOwnProperty(key))
        return false;
    }
    return true;
  }

}
