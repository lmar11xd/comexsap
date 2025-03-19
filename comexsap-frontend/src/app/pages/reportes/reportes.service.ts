import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable()
export class ReportesService {

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

  public reporteTerrestre(request: any) {
    const url = environment.apiUrlComex + 'api/reporteController/reporteTerrestre/';
    return this.http.post(url, request);
  }
}
