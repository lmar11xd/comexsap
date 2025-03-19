import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';

@Injectable()
export class InicioService {


  constructor (public http: HttpClient) {
  }

  public obtenerTotalPedidoExportacion() {
    const url = environment.apiUrlComex + 'api/exportacionMaritimoController/obtenerTotalPedidoExportacion';
    return this.http.get(url, {});
  }

  public obtenerTotalExportacion() {
    const url = environment.apiUrlComex + 'api/exportacionMaritimoController/obtenerTotalExportacion';
    return this.http.get(url, {});
  }

  public obtenerTotalExportacionPais() {
    const url = environment.apiUrlComex + 'api/exportacionMaritimoController/obtenerTotalExportacionPais';
    return this.http.get(url, {});
  }
}
