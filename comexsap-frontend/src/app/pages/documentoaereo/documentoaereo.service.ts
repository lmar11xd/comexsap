import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { DocumentoAereo } from './to/documentoaereo';

@Injectable()
export class DocumentoAereoService {

  private documento: DocumentoAereo;

  constructor (public http: HttpClient) {
  }

  public setDocumento(documento: DocumentoAereo) {
    this.documento = documento;
  }

  public getDocumento(): DocumentoAereo {
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

  public consultarStock(request: any) {
    const url = environment.apiUrlComex + 'api/exportacionMaritimoController/consultarStock/';
    return this.http.post(url, request);
  }

  public crearPedidoSap(request: any) {
    const url = environment.apiUrlComex + 'api/exportacionMaritimoController/crearPedidoSap/';
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
}
