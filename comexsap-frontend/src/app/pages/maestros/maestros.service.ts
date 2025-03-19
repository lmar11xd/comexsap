import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable()
export class MaestrosService {

  constructor (public http: HttpClient) {
  }

  public listarServicio(request: any) {
    const url = environment.apiUrlComex + 'api/servicioController/listarServicioxFiltro/';
    return this.http.post(url, request);
  }

  public obtenerServicio(id: string) {
    const url = environment.apiUrlComex + 'api/servicioController/obtenerServicio/' + id;
    return this.http.get(url, {});
  }

  public guardarServicio(request: any) {
    const url = environment.apiUrlComex + 'api/servicioController/guardarServicio/';
    return this.http.post(url, request);
  }

  public eliminarServicio(id: number, usuario: string) {
    const url = environment.apiUrlComex + 'api/servicioController/eliminarServicio/' + id + '?usuario=' + usuario;
    return this.http.post(url, {});
  }

  public listarGrupoCorreo(request: any) {
    const url = environment.apiUrlComex + 'api/grupoCorreoDetalleController/listarGrupoCorreoxFiltro/';
    return this.http.post(url, request);
  }

  public obtenerGrupoCorreo(id: string) {
    const url = environment.apiUrlComex + 'api/grupoCorreoDetalleController/obtenerGrupoCorreo/' + id;
    return this.http.get(url, {});
  }

  public guardarGrupoCorreo(request: any) {
    const url = environment.apiUrlComex + 'api/grupoCorreoDetalleController/guardarGrupoCorreo/';
    return this.http.post(url, request);
  }

  public eliminarGrupoCorreo(id: number, usuario: string) {
    const url = environment.apiUrlComex + 'api/grupoCorreoDetalleController/eliminarGrupoCorreo/' + id + '?usuario=' + usuario;
    return this.http.post(url, {});
  }

  public listarDominio() {
    const url = environment.apiUrlComex + 'api/configuracionController/listarDominio';
    return this.http.post(url, {});
  }

  public listarParametro(request: any) {
    const url = environment.apiUrlComex + 'api/configuracionController/listarParametroxFiltro/';
    return this.http.post(url, request);
  }

  public obtenerParametro(id: string) {
    const url = environment.apiUrlComex + 'api/configuracionController/obtenerParametroxId/' + id;
    return this.http.get(url, {});
  }

  public guardarParametro(request: any) {
    const url = environment.apiUrlComex + 'api/configuracionController/guardarParametro/';
    return this.http.post(url, request);
  }

  public eliminarParametro(id: number, usuario: string) {
    const url = environment.apiUrlComex + 'api/configuracionController/eliminarParametro/' + id + '?usuario=' + usuario;
    return this.http.post(url, {});
  }
}
