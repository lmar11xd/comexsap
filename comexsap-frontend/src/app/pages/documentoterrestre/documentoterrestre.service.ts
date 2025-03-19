import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { DocumentoTerrestre } from './to/documentoterrestre';

@Injectable()
export class DocumentoTerrestreService {

  private documento: DocumentoTerrestre;

  constructor (public http: HttpClient) {
  }

  public setDocumento(documento: DocumentoTerrestre) {
    this.documento = documento;
  }

  public getDocumento(): DocumentoTerrestre {
    return this.documento;
  }

  public listar(request: any) {
    const url = environment.apiUrlComex + 'api/exportacionTerrestreController/listarExportacionTerrestrexFiltro/';
    return this.http.post(url, request);
  }

  public obtener(id: string) {
    const url = environment.apiUrlComex + 'api/exportacionTerrestreController/obtenerExportacionTerrestre/' + id;
    return this.http.get(url, {});
  }

  public guardar(request: any) {
    const url = environment.apiUrlComex + 'api/exportacionTerrestreController/guardarExportacionTerrestre/';
    return this.http.post(url, request);
  }

  public guardarAgenteAduana(request: any) {
    const url = environment.apiUrlComex + 'api/documentoAgenteAduanaController/guardarDocumentoAgenteAduana/';
    return this.http.post(url, request);
  }

  public eliminar(id: number, usuario: string) {
    const url = environment.apiUrlComex + 'api/exportacionTerrestreController/eliminar/' + id + '?usuario=' + usuario;
    return this.http.post(url, {});
  }

  public imprimirCertificadoTerrestre(request: any) {
    const url = environment.apiUrlComex + 'api/reporteController/imprimirCertificadoTerrestre/';
    return this.http.post(url, request, { responseType: 'blob' });
  }
}
