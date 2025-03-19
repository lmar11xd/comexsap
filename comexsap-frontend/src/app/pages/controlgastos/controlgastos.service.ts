import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable()
export class ControlGastosService {

  constructor (public http: HttpClient) {
  }

  public reporteMaritimoContenedores(request: any) {
    const url = environment.apiUrlComex + 'api/reporteController/reporteMaritimoContenedores/';
    return this.http.post(url, request);
  }

  public reporteMaritimoCargaSuelta(request: any) {
    const url = environment.apiUrlComex + 'api/reporteController/reporteMaritimoCargaSuelta/';
    return this.http.post(url, request);
  }

  public listarDocumentosxFiltro(request: any) {
    const url = environment.apiUrlComex + 'api/controlGastosController/listarDocumentosxFiltro/';
    return this.http.post(url, request);
  }

  public guardar(request: any) {
    const url = environment.apiUrlComex + 'api/controlGastosController/guardarControlGastos/';
    return this.http.post(url, request);
  }
}
