import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { environment } from 'src/environments/environment';
import { SesionCloudService } from '../core/seguridad/sesion-cloud.service';
import { SesionLocalService } from '../core/seguridad/sesion-local.service';
import { SettingsService } from '../core/settings/settings.service';
import { VerticalSidebarService } from '../shared/vertical-sidebar/vertical-sidebar.service';
import { Constantes } from '../utils/constantes';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent {
  valForm: FormGroup;
  mensaje: String;
  flagLoading: boolean = false;
  nombreAplicacion = '';
  urlLogoAplicacion = '';

  constructor(public settings: SettingsService, fb: FormBuilder, private router: Router,
    private sesionCloudService: SesionCloudService,
    private sesionLocalService: SesionLocalService,
    private menuService: VerticalSidebarService,
    private messageService: MessageService) {
      this.nombreAplicacion = settings.getAppSetting('description');
      this.urlLogoAplicacion = settings.getAppSetting('logo');

      this.valForm = fb.group({
          'username': [null, Validators.required],
          'password': [null, Validators.required],
          'mfa': [null, null]
      });

  }

  loginform = true;
  recoverform = false;

  showRecoverForm() {
    this.loginform = !this.loginform;
    this.recoverform = !this.recoverform;
  }
  submitForm($ev, value: any) {
    $ev.preventDefault();
    for (let c in this.valForm.controls) {
        this.valForm.controls[c].markAsTouched();
    }
    if (this.valForm.valid) {
        let cuenta: any = {};
        cuenta.strPv_username = value.username.toLowerCase();
        cuenta.strPv_clearpwd = value.password;
        cuenta.strPv_appcode = environment.appcode;
        cuenta.strPv_mfa = value.mfa;
        this.flagLoading = true;
        this.sesionCloudService.login(cuenta)
        .subscribe(
          data => {
            let response: any;
            response = data;
            let token_req: any = {};
            token_req.code = response.code;
            token_req.username = cuenta.strPv_username;
            token_req.grant_type = 'access_token';
            token_req.client_secret = environment.clientSecret;
            token_req.scope = environment.apiUrlComex;
            token_req.client_id = environment.appcode;
            this.sesionCloudService.token(token_req)
            .subscribe(
              data_token => {
                let response_token: any;
                response_token = data_token;
                this.flagLoading = false;
                if (response_token.strPv_codigo === '1') {
                  let sesionSSO = Object.assign({}, response.objPv_sesion);
                  sesionSSO.token = response_token.access_token;
                  sesionSSO.token_type = response_token.token_type;
                  sesionSSO.expires_in = response_token.expires_in;
                  sesionSSO.scope = response.scope;
                  sesionSSO.refresh_token = response.refresh_token;
                  this.menuService.setMenuItems(sesionSSO.objPv_listaMenu);
                  let isMovil = true;
                  this.sesionLocalService.grabarSesion(sesionSSO,isMovil);
                  this.settings.setSesionSSO(sesionSSO);

                  //Limpiar Filtros
                  sessionStorage.removeItem(Constantes.P_STG_COTIZACION_FILTRO);
                  sessionStorage.removeItem(Constantes.P_STG_PEDIDOFIRME_FILTRO);
                  sessionStorage.removeItem(Constantes.P_STG_MARITIMO_CONTENEDORES_FILTRO);
                  sessionStorage.removeItem(Constantes.P_STG_MARITIMO_CARGASUELTA_FILTRO);
                  sessionStorage.removeItem(Constantes.P_STG_MARITIMO_INTERCOMPANY_FILTRO);
                  sessionStorage.removeItem(Constantes.P_STG_TERRESTRE_FILTRO);
                  sessionStorage.removeItem(Constantes.P_STG_AEREO_FILTRO);
                  sessionStorage.removeItem(Constantes.P_STG_PACKINGLIST_CONTENEDORES_FILTRO);
                  sessionStorage.removeItem(Constantes.P_STG_PACKINGLIST_CARGASUELTA_FILTRO);
                  sessionStorage.removeItem(Constantes.P_STG_PACKINGLIST_AEREO_FILTRO);
                  sessionStorage.removeItem(Constantes.P_STG_REPORTE_CONTENEDORES_FILTRO);
                  sessionStorage.removeItem(Constantes.P_STG_REPORTE_CARGASUELTA_FILTRO);
                  sessionStorage.removeItem(Constantes.P_STG_REPORTE_TERRESTRE_FILTRO);
                  sessionStorage.removeItem(Constantes.P_STG_CONTROLGASTOS_CONTENEDORES_FILTRO);
                  sessionStorage.removeItem(Constantes.P_STG_CONTROLGASTOS_CARGASUELTA_FILTRO);

                  this.router.navigate(['/inicio/inicio-comex']);
                }else{
                  this.mensaje = response.strPv_mensaje;
                }
              }
              ,
              err => {
                this.flagLoading = false;
                this.mensaje = 'Ocurrio un problema al obtener token, verifique su conexion y vuelva a intentar en un momento';
              }
           );

          }
          ,
          err => {
            this.flagLoading = false;
            this.mensaje = 'Ocurrio un problema de comunicacion, verifique su conexion y vuelva a intentar en un momento';
          }
       );
    }
  }

  getVersion(){
    return this.settings.getAppSetting('VERSION');
  }
}
