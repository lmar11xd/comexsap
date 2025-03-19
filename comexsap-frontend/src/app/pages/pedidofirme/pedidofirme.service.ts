import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { PedidoFirme } from './to/pedidofirme';

@Injectable()
export class PedidoFirmeService {

  private pedidoFirme: PedidoFirme;

  constructor (public http: HttpClient) {
  }

  public setPedidoFirme(pedidoFirme: PedidoFirme) {
    this.pedidoFirme = pedidoFirme;
  }

  public getPedidoFirme(): PedidoFirme {
    return this.pedidoFirme;
  }

  public listar(request: any) {
    const url = environment.apiUrlComex + 'api/pedidoFirmeController/listarPedidoFirmexFiltro/';
    return this.http.post(url, request);
  }

  public obtener(id: string) {
    const url = environment.apiUrlComex + 'api/pedidoFirmeController/obtenerPedidoFirme/' + id;
    return this.http.get(url, {});
  }

  public guardar(request: any) {
    const url = environment.apiUrlComex + 'api/pedidoFirmeController/guardarPedidoFirme/';
    return this.http.post(url, request);
  }

  public guardarArchivos(request: any) {
    const url = environment.apiUrlComex + 'api/archivoController/guardarArchivosPedidoFirme/';
    return this.http.post(url, request);
  }

  public confirmar(request: any) {
    const url = environment.apiUrlComex + 'api/pedidoFirmeController/confirmarPedidoFirme/';
    return this.http.post(url, request);
  }

  public copiar(request: any) {
    const url = environment.apiUrlComex + 'api/pedidoFirmeController/duplicarPedidoFirme/';
    return this.http.post(url, request);
  }

  public modificarDocumento(request: any) {
    const url = environment.apiUrlComex + 'api/pedidoFirmeController/modificarDocumento/';
    return this.http.post(url, request);
  }

  public anular(id: number, usuario: string) {
    const url = environment.apiUrlComex + 'api/pedidoFirmeController/anularPedido/' + id + '?usuario=' + usuario;
    return this.http.post(url, {});
  }

  public verCorreo(request: any) {
    const url = environment.apiUrlComex + 'api/pedidoFirmeController/verCorreoPedidoFirme/';
    return this.http.post(url, request);
  }

  public enviarCorreoFechasDisponibilidad(id: string) {
    const url = environment.apiUrlComex + 'api/pedidoFirmeController/enviarCorreoFechasDisponibilidad/' + id;
    return this.http.get(url, {});
  }

  public enviarCorreoActualizacionPrecios(id: string) {
    const url = environment.apiUrlComex + 'api/pedidoFirmeController/enviarCorreoActualizacionPrecios/' + id;
    return this.http.get(url, {});
  }

  public eliminarArchivo(id: number, usuario: string) {
    const url = environment.apiUrlComex + 'api/archivoController/eliminarArchivoxId/' + id + '/' + usuario;
    return this.http.post(url, {});
  }

  public obtenerArchivosxCarpeta(idCarpeta: number) {
    const url = environment.apiUrlComex + 'api/archivoController/obtenerArchivosxCarpeta/' + idCarpeta;
    return this.http.get(url, {});
  }

  public obtenerArchivo(url: string) {
    return this.http.get(url, { responseType: 'blob' });
  }

  public consultarLineaCreadito(request: any) {
    const url = environment.apiUrlComex + 'api/exportacionMaritimoController/crearPedidoSap/';
    return this.http.post(url, request);
  }

  public confirmarFechasdisponibilidad(request: any) {
    const url = environment.apiUrlComex + 'api/pedidoFirmeController/confirmarFechasDisponibilidad/';
    return this.http.post(url, request);
  }

  public obtenerCotizacionPdf(request: any) {
    const url = environment.apiUrlComex + 'api/cotizacionController/generarCotizacionPdf/';
    return this.http.post(url, request, { responseType: 'blob' });
  }
}
