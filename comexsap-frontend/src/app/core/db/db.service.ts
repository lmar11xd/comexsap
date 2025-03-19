import { Injectable } from '@angular/core';
import { SettingsService } from '../settings/settings.service';
import { WindowRef } from './WindowRef';
//import { AsyncLocalStorage } from 'angular-async-local-storage';
//import { SQLite, SQLiteObject } from '@ionic-native/sqlite/ngx';

@Injectable()
export class DbService {
  
   db: any;
   tablas: any[];
   columnas: any[];
   queryString: string;

	 constructor(private winRef: WindowRef, private settings: SettingsService) { 
	    this.init();
   }
  
   init() {
   }
  
   setItem(id, objeto){
     this.winRef.nativeWindow.localStorage.setItem(id, JSON.stringify(objeto));
   }
  
   getItem(id){
    let objeto = this.winRef.nativeWindow.localStorage.getItem(id);
    if( this.winRef.nativeWindow.localStorage.getItem(id) !== null ){
      return JSON.parse(objeto);
    }else{ 
      return null;
    }
   }
  
   removeItem(id){
    return this.winRef.nativeWindow.localStorage.removeItem(id);
   }

}
