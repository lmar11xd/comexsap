import { Injectable } from '@angular/core';
declare var $: any;
import { NgxSpinnerService } from 'ngx-spinner';

@Injectable()
export class SettingsService {

    private user: any;
    private app: any;
    private layout: any;

    public sesionSSO: any;
    public API_URL_SEGURIDAD: any;
    public MENSAJES: any;
    public ICONS_MSG: any;
    public CONSTANTES: any[];
    public urlSessionOn: any;
    public SOCIEDAD_ACEROS_AREQUIPA = 'AA10';

    public error: any;
    public warning: any;

    public httpCodes: any;
    public loader: boolean = false;

    constructor(
        private spinnerService: NgxSpinnerService
        ) {

        // User Settings
        // -----------------------------------
        this.user = {
            name: 'John',
            job: 'ng-developer',
            picture: 'assets/img/user/02.jpg'
        };

        // App Settings
        // -----------------------------------
        this.app = {
            name: 'Angle',
            description: 'Modulo de Exportaciones',
            year: ((new Date()).getFullYear()),
            logo: 'assets/images/logo-icon.svg',
            //Datos
            VERSION: '1.3.0',
            aplicacion: 'COMEXSAP',
            cache: false,
            timeout: 60000,
            flag_aplicacion_web: '0',
            flag_cordova: '0',

        };

        this.sesionSSO = {};

        // Layout Settings
        // -----------------------------------
        this.layout = {
            isFixed: true,
            isCollapsed: false,
            isBoxed: false,
            isRTL: false,
            horizontal: false,
            isFloat: false,
            asideHover: false,
            theme: null,
            asideScrollbar: false,
            isCollapsedText: false,
            useFullLayout: false,
            hiddenFooter: false,
            offsidebarOpen: false,
            asideToggled: false,
            viewAnimation: 'ng-fadeInUp'
        };

        this.API_URL_SEGURIDAD = (function () {
            return {
                // Sesion
                'api_sesionCloudController_login': 'api/sesionCloudController/loginAplicacion',
                'api_sesionCloudController_obtenerSesion': 'api/sesionCloudController/obtenerSesion',
                'api_sesionCloudController_invalidate': 'api/sesionCloudController/invalidate',
                'api_sesionWebController_obtenerSesion': 'api/sesionController/obtenerSesion',
                'api_sesionWebController_obtenerMenu': 'api/sesionController/obtenerMenu',
                'api_sesionCloudController_token': 'api/sesionCloudController/token'
            }
        })();

        this.MENSAJES = {
            'msg_wait_title': 'Espere',
            'msg_wait_body': 'Procesando',
            'swal_error_conexion_titulo': 'No se puede conectar',
            'swal_error_conexion_mensaje': 'Comprueba tu red y vuelve a intentarlo, si persiste por favor comunica a helpdesk',
            'swal_error_titulo': 'Error!',
            'swal_error_tipo': 'error',
            'swal_error_general': 'Ocurrio un problema con su peticion, porfavor vuelva a ingresar al sistema.',
            'swal_error_navegador': 'Lo sentimos su navegador no es compatible con la opcion de descarga.',
            'swal_error_boton_ok': 'Aceptar',
            'swal_precaucion_titulo': 'Atencion',
            'swal_precaucion_tipo': 'warning',
            'swal_exito_titulo': 'Excelente',
            'swal_exito_tipo': 'success',
            'swal_exito_boton_ok': 'Aceptar',
            'swal_confirmacion_titulo': 'Confirmación',
            'swal_confirmacion_tipo': 'warning',
            'swal_confirmacion_boton_ok': 'Aceptar',
            'swal_confirmacion_boton_cancelar': 'Cancelar',
            'swal_continuar_titulo': 'Listo!',
            'swal_continuar_tipo': 'info',
            'swal_continuar_boton_ok': 'Si!',
            'swal_continuar_boton_cancelar': 'No',
            'swal_exito_continuar_tipo': 'info',
            'swal_exito_continuar_boton_ok': 'Ok',
            'swal_info_titulo': 'Aviso',
            'swal_warning_titulo': 'Aviso',
            'swal_help_titulo': 'Ayuda',
            'mensaje_validacion_error_campo_requerido': 'Este campo es requerido',
            'mensaje_validacion_error_campo_max_500': 'Este campo ha sobrepasado el tama?o de 500 caracteres',
            'mensaje_validacion_error_campo_max_400': 'Este campo ha sobrepasado el tama?o de 400 caracteres',
            'mensaje_validacion_error_campo_max_300': 'Este campo ha sobrepasado el tama?o de 300 caracteres',
            'mensaje_validacion_error_campo_max_200': 'Este campo ha sobrepasado el tama?o de 200 caracteres',
            'mensaje_validacion_error_campo_max_1': 'Este campo ha sobrepasado el tama?o de 1 caracterer',
            'mensaje_validacion_error_campo_date': 'La fecha ingresada es anterior a la fecha actual',
            'mensaje_validacion_error_campo_min': 'Este campo no cumple con la cantidad m?nima de caracteres',
            'mensaje_validacion_error_campo_max': 'Este campo no cumple con la cantidad m?xima de caracteres',
            'mensaje_validacion_cuestionario': 'El cuestionario es requerido, debe responder las preguntas',
            'mensaje_problema_conexion_servidor': 'No hay conexi?n con el servidor, por favor vuelva a intentar en un momento',
            'mensaje_confirmar_borrado': 'Desea eliminar el registro?',
            'mensaje_confirmar_salir_sistema': 'Desea salir del sistema?',
            'mensaje_problema_peticion': 'Ocurrio un problema con su solicitud por favor comunicar a helpdesk',
            'mensaje_grabar_exito': 'Su solicitud se ha grabado',
            'mensaje_confirmar_desocupar': 'Desea desocupar permanentemente la ubicacion?',
            'swal_confirmar_firmar': 'Usted a seleccionado \"Firmar\".',
            'swal_confirmar_rechazar': 'Usted a seleccionado \"Rechazar\".',
            'swal_confirmar_concluir': 'Usted a seleccionado \"Concluir\".',
            'swal_confirmar_rechazado': 'Usted a seleccionado \"Rechazar\".',
            'swal_confirmar_cancelar': 'Usted a seleccionado \"Anular\".',
            'titulo_modificar_aprobador': 'Modificar Aprobador',
            'mensaje_grabar_solicitud_exito': 'Se ha creado la solicitud: ',
            'MENSAJE_ERROR_SERVIDOR': 'Error interno del servidor',
            'MENSAJE_VALIDACION_COMPLETAR': 'Por favor complete todos los campos obligatorios'
        };

        this.ICONS_MSG = {
            'ERROR': 'fa-warning',
            'SUCCESS': 'fa-check',
            'WARNIG': 'fa-warning',
            'INFO': 'fa-info-circle',
        };

        this.CONSTANTES = [];
        this.CONSTANTES['APLICACION'] = this.app;
        this.CONSTANTES['API_URL_SEGURIDAD'] = this.API_URL_SEGURIDAD;
        this.CONSTANTES['MENSAJES'] = this.MENSAJES;
        this.CONSTANTES['ICONS_MSG'] = this.ICONS_MSG;

        this.error = {
            severity: 'error',
            detail: 'Hay problemas de conexión.'
        };

        this.warning = {
            severity: 'warning',
            detail: 'No se encontró los datos solicitados.'
        };

        this.httpCodes = {
            OK: '200'
        };

    }

    getAppSetting(name) {
        return name ? this.app[name] : this.app;
    }
    getUserSetting(name) {
        return name ? this.user[name] : this.user;
    }
    getLayoutSetting(name) {
        return name ? this.layout[name] : this.layout;
    }

    setAppSetting(name, value) {
        if (typeof this.app[name] !== 'undefined') {
            this.app[name] = value;
        }
    }
    setUserSetting(name, value) {
        if (typeof this.user[name] !== 'undefined') {
            this.user[name] = value;
        }
    }
    setLayoutSetting(name, value) {
        if (typeof this.layout[name] !== 'undefined') {
            return this.layout[name] = value;
        }
    }
    toggleLayoutSetting(name) {
        return this.setLayoutSetting(name, !this.getLayoutSetting(name));
    }
    getConstantesByDominioKey(dominio, key) {
        if (typeof this.CONSTANTES[dominio] !== 'undefined') {
            return (this.CONSTANTES[dominio])[key];
        }
    }
    isSmallScreen() {
        return $(window).width() < 800;
    }
    isMobileDevice() {
        return this.getConstantesByDominioKey('APLICACION', 'flag_aplicacion_web') === '0';
    }
    isCordovaEnabled() {
        return (this.getConstantesByDominioKey('APLICACION', 'flag_cordova') === '1');
    }
    getSesionSSO() {
        return this.sesionSSO;
    }
    setSesionSSO(sesionSSO: any) {
        this.sesionSSO = Object.assign({}, sesionSSO);
        this.user.name = this.sesionSSO.strPv_fullname;
        this.user.job = this.sesionSSO.strPv_username;
        this.sesionSSO.username = this.sesionSSO.strPv_username;
    }
    setUrlSessionOn(urlSessionOn: string) {
        this.urlSessionOn = urlSessionOn;
    }
    getUrlSessionOn() {
        return this.urlSessionOn;
    }
    isEmpty(valor) {
        return (valor == null || valor === undefined || !valor || valor.length === 0 || valor === '');
    }
    public mostrarSpinner(): void {
        this.spinnerService.show();
    }
    public ocultarSpinner(): void {
        this.spinnerService.hide();
    }
    getUsername(){
        return this.sesionSSO.strPv_username;
    }
    getSociedad(){
        return this.sesionSSO.strPv_sociedad;
    }
    obtenerSociedadxDefecto(){
        let sociedad = this.sesionSSO.strPv_sociedad;
        if(sociedad == null || sociedad == ''){
            sociedad = this.SOCIEDAD_ACEROS_AREQUIPA;
        }
        return sociedad;
    }
    getFullname(){
        return this.sesionSSO.strPv_fullname;
    }
    getRoles() {
      return this.sesionSSO.objPv_listaMapaRoles;
    }
    existeRol(codigo: string) {
      const rol = this.getRoles()?.find(o => o.rolename == codigo);
      return rol != undefined && rol != null;
    }
}
