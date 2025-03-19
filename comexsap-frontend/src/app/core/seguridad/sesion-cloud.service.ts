import { Injectable } from '@angular/core';
import { SettingsService } from '../settings/settings.service';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable()
export class SesionCloudService {

	 constructor(private http: HttpClient, private settings: SettingsService) { 
	    
   }
  
   login(request) {
     const url = environment.apiUrlSeguridad + 'api/sesionCloudController/loginAplicacion';
     return this.http.post(url,request);
   }

   token(request) {
     const url = environment.apiUrlSeguridad + 'api/sesionCloudController/token';
     return this.http.post(url,request);
   }

}
