import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Cotizacion } from './to/cotizacion';

@Injectable()
export class CotizacionService {

  private cotizacion: Cotizacion;

  constructor (public http: HttpClient) {
  }

  public setCotizacion(cotizacion: Cotizacion) {
    this.cotizacion = cotizacion;
  }

  public getCotizacion(): Cotizacion {
    return this.cotizacion;
  }

  public listarCotizacionesxFiltro(request: any) {
    const url = environment.apiUrlComex + 'api/cotizacionController/listarCotizacionxFiltro/';
    return this.http.post(url, request);
  }

  public obtenerCotizacionxId(request: any) {
    const url = environment.apiUrlComex + 'api/cotizacionController/editarCotizacion/';
    return this.http.post(url, request);
  }

  public guardarCotizacion(request: any) {
    const url = environment.apiUrlComex + 'api/cotizacionController/guardarCotizacion/';
    return this.http.post(url, request);
  }

  public copiarCotizacion(request: any) {
    const url = environment.apiUrlComex + 'api/cotizacionController/duplicarCotizacion/';
    return this.http.post(url, request);
  }

  public anularCotizacion(id: number, usuario: string) {
    const url = environment.apiUrlComex + 'api/cotizacionController/anularPedido/' + id + '?usuario=' + usuario;
    return this.http.post(url, {});
  }

  public confirmarCotizacion(request: any) {
    const url = environment.apiUrlComex + 'api/cotizacionController/confirmarCotizacion/';
    return this.http.post(url, request);
  }

  public enviarCorreo(request: any) {
    const url = environment.apiUrlComex + 'api/cotizacionController/enviarCorreoCotizacionProforma/';
    return this.http.post(url, request);
  }

  public obtenerCotizacionPdf(request: any) {
    //const url = environment.apiUrlComex + 'api/reporteController/exportarPDF/';
    const url = environment.apiUrlComex + 'api/cotizacionController/generarCotizacionPdf/';
    return this.http.post(url, request, { responseType: 'blob' });
  }
}
