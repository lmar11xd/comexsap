import { Injectable } from '@angular/core';
import { SettingsService } from '../settings/settings.service';
import { HttpClient } from '@angular/common/http';// import the WindowRef provider
import { DbService } from './db.service';

@Injectable()
export class SesionDbService {

	 constructor(private dbService: DbService, private settings: SettingsService) {
   }

   grabar(sesion) {
     try {
       this.dbService.setItem('SESIONSSO',sesion);
     }
     catch (e) {
     }
   };

   obtenerActual() {
     let sesion: any;
     try {
        sesion = this.dbService.getItem('SESIONSSO');
     }
     catch (e) {
     }
     return sesion;
   };

   logout() {
     try {
       this.dbService.removeItem('SESIONSSO');
       this.dbService.removeItem('TC');
     }
     catch (e) {
     }
   };

   grabarTipoCambio(objA_tipoDeCambio) {
    try {
      this.dbService.setItem('TC',objA_tipoDeCambio);
    }
    catch (e) {
    }
  };

  obtenerTipoCambio() {
    let objL_tipoDeCambio: any;
    try {
      objL_tipoDeCambio = this.dbService.getItem('TC');
    }
    catch (e) {
    }
    return objL_tipoDeCambio;
  };

}
