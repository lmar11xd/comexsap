import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { DocumentoMaritimo } from './to/documentomaritimo';

@Injectable()
export class DocumentoMaritimoService {

  private documento: DocumentoMaritimo;

  constructor (public http: HttpClient) {
  }

  public setDocumento(documento: DocumentoMaritimo) {
    this.documento = documento;
  }

  public getDocumento(): DocumentoMaritimo {
    return this.documento;
  }

  public listar(request: any) {
    const url = environment.apiUrlComex + 'api/exportacionMaritimoController/listarExportacionMaritimoxFiltro/';
    return this.http.post(url, request);
  }

  public obtener(id: string) {
    const url = environment.apiUrlComex + 'api/exportacionMaritimoController/obtenerExportacionMaritimo/' + id;
    return this.http.get(url, {});
  }

  public obtenerExportacionIntercompany(id: string) {
    const url = environment.apiUrlComex + 'api/pedidoIntercompanyController/obtenerExportacionIntercompany/' + id;
    return this.http.get(url, {});
  }

  public eliminar(id: number, usuario: string) {
    const url = environment.apiUrlComex + 'api/exportacionMaritimoController/eliminarExportacion/' + id + '?usuario=' + usuario;
    const headers = {'Content-Type': 'application/json'}
    return this.http.post(url, {headers: headers});
  }

  public listarPedidoFirme(request: any) {
    const url = environment.apiUrlComex + 'api/pedidoFirmeController/listarPedidoFirmexFiltro/';
    return this.http.post(url, request);
  }

  public guardar(request: any) {
    const url = environment.apiUrlComex + 'api/exportacionMaritimoController/guardarExportacionMaritimo/';
    return this.http.post(url, request);
  }

  public confirmar(request: any) {
    const url = environment.apiUrlComex + 'api/exportacionMaritimoController/confirmarExportacion/';
    return this.http.post(url, request);
  }

  public crearPedidoSap(request: any) {
    const url = environment.apiUrlComex + 'api/exportacionMaritimoController/crearPedidoSap/';
    return this.http.post(url, request);
  }

  public modificarPedidoSap(request: any) {
    const url = environment.apiUrlComex + 'api/exportacionMaritimoController/modificarPedidoSap/';
    return this.http.post(url, request);
  }

  public imprimirExportacionMaritimo(request: any) {
    const url = environment.apiUrlComex + 'api/exportacionMaritimoController/generarExportacionMaritimaPdf/';
    return this.http.post(url, request, { responseType: 'blob' });
  }

  public imprimirFacturaComercial(request: any, versionIngles: boolean, original: boolean, tipoFactura: number) {
    const url = environment.apiUrlComex + 'api/exportacionMaritimoController/generarFacturaComercialPdf?versionIngles=' + versionIngles + '&original=' + original+ '&tipoFactura=' + tipoFactura;
    return this.http.post(url, request, { responseType: 'blob' });
  }

  public consultarStock(request: any) {
    const url = environment.apiUrlComex + 'api/exportacionMaritimoController/consultarStock/';
    return this.http.post(url, request);
  }

  public listarPedidoIntercompanyxFiltro(request: any) {
    const url = environment.apiUrlComex + 'api/pedidoIntercompanyController/listarPedidoIntercompanySapxFiltro/';
    return this.http.post(url, request);
  }

  public actualizarEntrega(idExportacion: number, pedidoSap: string) {
    const url = environment.apiUrlComex + 'api/exportacionMaritimoController/actualizarEntrega/' + idExportacion + '?pedidoSap=' + pedidoSap;
    return this.http.get(url, {});
  }

  public listarExportacionEtiquetas(idExportacion: string) {
    const url = environment.apiUrlComex + 'api/exportacionMaritimoController/listarExportacionEtiquetas/' + idExportacion;
    return this.http.get(url, {});
  }
}
