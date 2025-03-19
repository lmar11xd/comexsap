import { Injectable } from '@angular/core';
import { SettingsService } from '../settings/settings.service';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class SesionWebService {

   constructor(private http: HttpClient, private settings: SettingsService) {

   }

   obtenerSesion() {
        let url = this.settings.getConstantesByDominioKey('APLICACION','URL_DOMAIN_SERVICES') + this.settings.getConstantesByDominioKey('API_URL_SEGURIDAD','api_sesionWebController_obtenerSesion');
        return this.http.get(url);
	 }

	 cerrarSesion(request) {
        let url = this.settings.getConstantesByDominioKey('APLICACION','URL_DOMAIN_SERVICES') + this.settings.getConstantesByDominioKey('API_URL_SEGURIDAD','api_sesionWebController_cerrarSesion');
        return this.http.get(url);
   }

}
