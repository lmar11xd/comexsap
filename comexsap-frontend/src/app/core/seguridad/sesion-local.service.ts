import { Injectable } from '@angular/core';
import { SettingsService } from '../settings/settings.service';
import { SesionDbService } from '../db/sesion-db.service';
import { VerticalSidebarService } from 'src/app/shared/vertical-sidebar/vertical-sidebar.service';

@Injectable()
export class SesionLocalService {

   public bgCover: String;
   public sesionSSO: any = {};
   public objPv_tipoDeCambio: any = {};

	 constructor(
     private settings: SettingsService,
     private sesionDbService: SesionDbService,
     private menuService: VerticalSidebarService
   ) {

   }

   getStrPv_idParticipante(){
    return this.sesionSSO.strPv_idParticipante;
   }

  getStrPv_email() {
		return this.sesionSSO.strPv_email;
	}
	setStrPv_email(strPv_email) {
		this.sesionSSO.strPv_email = strPv_email;
	}

  getStrPv_sector() {
		return this.objPv_tipoDeCambio.strPv_sector;
	}
	setStrPv_sector(strPv_sector) {
		this.objPv_tipoDeCambio.strPv_sector = strPv_sector;
	}

  getStrPv_unidadOrganizativa() {
		return this.objPv_tipoDeCambio.strPv_unidadOrganizativa;
	}
	setStrPv_unidadOrganizativa(strPv_unidadOrganizativa) {
		this.objPv_tipoDeCambio.strPv_unidadOrganizativa = strPv_unidadOrganizativa;
	}
	getStrPv_canalDistribucion() {
		return this.objPv_tipoDeCambio.strPv_canalDistribucion;
	}
	setStrPv_canalDistribucion(strPv_canalDistribucion) {
		this.objPv_tipoDeCambio.strPv_canalDistribucion = strPv_canalDistribucion;
	}

   getObjPv_igv() {
		return this.objPv_tipoDeCambio.objPv_igv;
	 }
	 setObjPv_igv(objPv_igv) {
		this.objPv_tipoDeCambio.objPv_igv = objPv_igv;
	 }

   setObjPv_tipoDeCambio(objPv_tipoDeCambio) {
    this.objPv_tipoDeCambio = objPv_tipoDeCambio;
   }

   getObjPv_tipoDeCambio() {
     return this.objPv_tipoDeCambio;
   }

   setStrPv_sociedad(sociedad) {
    this.sesionSSO.strPv_sociedad = sociedad;
   }

   getStrPv_sociedad() {
     return this.sesionSSO.strPv_sociedad;
   }

   setToken(token) {
     this.sesionSSO.token = token;
   }

   getToken() {
     return this.sesionSSO.token;
   }

   setStrPv_username(username) {
     this.sesionSSO.strPv_username = username;
   }

   getStrPv_username() {
     return this.sesionSSO.strPv_username;
   }

   setStrPv_cookie(cookie) {
     this.sesionSSO.strPv_cookie = cookie;
   }

   getStrPv_cookie() {
     return this.sesionSSO.strPv_cookie;
   }

   setStrPv_appcode(appcode) {
     this.sesionSSO.strPv_appcode = appcode;
   }

   getStrPv_appcode() {
     return this.sesionSSO.strPv_appcode;
   }

   setStrPv_deviceId(deviceId) {
     this.sesionSSO.strPv_deviceId = deviceId;
   }

   getStrPv_deviceId() {
     return this.sesionSSO.strPv_deviceId;
   }

   setExpires_in(expires_in) {
     this.sesionSSO.expires_in = expires_in;
   }

   getExpires_in() {
     return this.sesionSSO.expires_in;
   }

   setStrPv_fullname(fullname) {
     this.sesionSSO.strPv_fullname = fullname;
   }

   getStrPv_fullname() {
     return this.sesionSSO.strPv_fullname;
   }

   setStrPv_codigoEmpresa(codigoEmpresa) {
     this.sesionSSO.strPv_codigoEmpresa = codigoEmpresa;
   }

   getStrPv_codigoEmpresa() {
     return this.sesionSSO.strPv_codigoEmpresa;
   }

   setObjPv_listaMenu(listaMenu) {
     this.sesionSSO.objPv_listaMenu = listaMenu;
   }

   getObjPv_listaMenu() {
     return this.sesionSSO.objPv_listaMenu;
   }

  setObjPv_bgImage(bgImage) {
    this.sesionSSO.objPv_bgImage = bgImage;
  }

  getObjPv_bgImage() {
    return this.sesionSSO.objPv_bgImage;
  }

  setStrPv_cpersonal(strPv_cpersonal) {
    this.sesionSSO.strPv_cpersonal = strPv_cpersonal;
  }

  getStrPv_cpersonal() {
    return this.sesionSSO.strPv_cpersonal;
  }

  setStrPv_flagAdministrador(strPv_flagAdministrador) {
    this.sesionSSO.strPv_flagAdministrador = strPv_flagAdministrador;
  }

  getStrPv_flagAdministrador() {
    return this.sesionSSO.strPv_flagAdministrador;
  }

  getStrPv_tipoParticipante(){
    return this.sesionSSO.strPv_tipoParticipante;
  }
  getStrPv_urlImagenParticipante(){
    return this.sesionSSO.strPv_urlImagenParticipante;
  }

   grabarSesion(objA_sesionSSO,isMovil : Boolean) {
    this.sesionSSO = Object.assign({},objA_sesionSSO);
     if(isMovil){
      this.sesionDbService.logout();
      this.sesionDbService.grabar(objA_sesionSSO);
     }else{
      this.settings.sesionSSO = this.sesionSSO;
     }

   }

   actualizarSesion(objA_sesionSSO) {
     this.sesionSSO = Object.assign({},objA_sesionSSO);
   }

   borrarSesion() {
     this.sesionDbService.logout();
     this.sesionSSO = {};
   }

   obtenerSesionActual(isMovil : Boolean) {
     if(isMovil){
        let sesionSSO = null;
        sesionSSO = this.sesionDbService.obtenerActual();
        if(sesionSSO == null){
          this.sesionSSO = {};
        }else{
          this.sesionSSO = Object.assign({}, sesionSSO);
        }
     }
     return this.sesionSSO;
  }

  grabarTipoDeCambio(objA_tipoDeCambio, isMovil : Boolean) {
    if(isMovil){
     this.sesionDbService.grabarTipoCambio(objA_tipoDeCambio);
    }
    this.objPv_tipoDeCambio = Object.assign({},objA_tipoDeCambio);
  }

  obtenerTipoDeCambio(isMovil : Boolean) {
    if(isMovil){
       let objL_tipoDeCambio = null;
       objL_tipoDeCambio = this.sesionDbService.obtenerTipoCambio();
       if(objL_tipoDeCambio == null){
         this.objPv_tipoDeCambio = {};
       }else{
         this.objPv_tipoDeCambio = Object.assign({}, objL_tipoDeCambio);
       }
    }
    return this.objPv_tipoDeCambio;
 }

 obtenerTipoDeCambioActual(isMovil : Boolean) {
  if(isMovil){
     let objPv_tipoDeCambio = null;
     objPv_tipoDeCambio = this.sesionDbService.obtenerTipoCambio();
     if(objPv_tipoDeCambio == null){
       this.objPv_tipoDeCambio = {};
     }else{
       this.objPv_tipoDeCambio = Object.assign({}, objPv_tipoDeCambio);
     }
  }
  return this.objPv_tipoDeCambio;
}

  tieneToken() {
     let isMovil = true;
     let blnL_flagTieneToken = false;
     this.sesionSSO = this.obtenerSesionActual(isMovil);
     if(this.sesionSSO !== null && this.sesionSSO.token !== null && typeof this.sesionSSO.token !== 'undefined' && this.sesionSSO.token !== '') {
      this.settings.setSesionSSO(this.sesionSSO);
      this.menuService.setMenuItems(this.sesionSSO.objPv_listaMenu);
      let ahora = new Date();
      let ahoraMiliseg = ahora.getTime();
      let diaSesion = new Date(this.sesionSSO.expires_in);
      let diaSesionFin = new Date(diaSesion.getFullYear(),diaSesion.getMonth(),diaSesion.getDate(),23,59,59,0);
      if(diaSesionFin.getTime() > ahoraMiliseg){
          blnL_flagTieneToken = true;
          this.objPv_tipoDeCambio = Object.assign({}, this.obtenerTipoDeCambioActual(isMovil));
      }else{
          this.borrarSesion();
      }
     }
     return blnL_flagTieneToken;
  }

  tieneSesionWeb() {
    let isMovil = false;
    let blnL_flagTienSesion = false;
    this.sesionSSO = this.obtenerSesionActual(isMovil);
    if (!this.isEmpty(this.sesionSSO)) {
      blnL_flagTienSesion = true;
    }
    return blnL_flagTienSesion;
 }

  formatDate(d) {
    let dd = d.getDate();
    if (dd < 10){ dd = '0' + dd; }
    let mm = d.getMonth() + 1;
    if (mm < 10){ mm = '0' + mm; }
    let yyyy = d.getFullYear();
    return dd + '-' + mm + '-' + yyyy;
  }

  formatoFecha(d, format) {
    let date = d,
      day = date.getDate(),
      month = date.getMonth() + 1,
      year = date.getFullYear(),
      hours = date.getHours(),
      minutes = date.getMinutes(),
      seconds = date.getSeconds();

    if (!format) {
      format = "MM/dd/yyyy";
    }

    format = format.replace("MM", month.toString().replace(/^(\d)$/, '0$1'));

    if (format.indexOf("yyyy") > -1) {
      format = format.replace("yyyy", year.toString());
    } else if (format.indexOf("yy") > -1) {
      format = format.replace("yy", year.toString().substr(2, 2));
    }

    format = format.replace("dd", day.toString().replace(/^(\d)$/, '0$1'));

    if (format.indexOf("t") > -1) {
      if (hours > 11) {
        format = format.replace("t", "pm");
      } else {
        format = format.replace("t", "am");
      }
    }

    if (format.indexOf("HH") > -1) {
      format = format.replace("HH", hours.toString().replace(/^(\d)$/, '0$1'));
    }

    if (format.indexOf("hh") > -1) {
      if (hours > 12) {
        hours -= 12;
      }

      if (hours === 0) {
        hours = 12;
      }
      format = format.replace("hh", hours.toString().replace(/^(\d)$/, '0$1'));
    }

    if (format.indexOf("mm") > -1) {
      format = format.replace("mm", minutes.toString().replace(/^(\d)$/, '0$1'));
    }

    if (format.indexOf("ss") > -1) {
      format = format.replace("ss", seconds.toString().replace(/^(\d)$/, '0$1'));
    }

    return format;
  }

  isEmpty(obj) {
    for (var key in obj) {
      if (obj.hasOwnProperty(key))
        return false;
    }
    return true;
  }

  obtenerDatosToken(accessToken: string): any {
    if (accessToken != null) {
      return JSON.parse(atob(accessToken.split(".")[1]));
    }
    return null;
  }

  isAuthenticated(): boolean {
    let payload = this.obtenerDatosToken(this.getToken());
    if (payload != null && payload.username && payload.username.length > 0) {
      return true;
    }
    return false;
  }

}
