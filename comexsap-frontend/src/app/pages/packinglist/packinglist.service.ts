import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { PackingList } from './to/packing-list';

@Injectable()
export class PackingListService {

  private packinglist: PackingList;

  constructor (public http: HttpClient) {
  }

  public setPackingList(packinglist: PackingList) {
    this.packinglist = packinglist;
  }

  public getPackingList(): PackingList {
    return this.packinglist;
  }

  public listar(request: any) {
    const url = environment.apiUrlComex + 'api/packingListController/listarPackingListxFiltro/';
    return this.http.post(url, request);
  }

  public obtenerDetallePackingListxId(id: number) {
    const url = environment.apiUrlComex + 'api/packingListController/obtenerDetallePackingList/'+ id;
    return this.http.get(url, {});
  }

  public generarPdf(idExportacion: number, versionIngles: boolean, original: boolean) {
    const url = environment.apiUrlComex + 'api/packingListController/generarPdf/' + idExportacion + '?versionIngles=' + versionIngles + '&original=' + original;
    return this.http.get(url, { responseType: 'blob' });
  }

  public guardar(request: any) {
    const url = environment.apiUrlComex + 'api/packingListController/registrarPackingList/';
    return this.http.post(url, request);
  }

  public obtenerPackingListCargaSuelta(id: number) {
    const url = environment.apiUrlComex + 'api/packingListController/obtenerPackingListCargaSuelta/'+ id;
    return this.http.get(url, {});
  }

  public registrarPackingListCargaSuelta(request: any) {
    const url = environment.apiUrlComex + 'api/packingListController/registrarPackingListCargaSuelta/';
    return this.http.post(url, request);
  }

  public obtenerPackingListAereo(id: number) {
    const url = environment.apiUrlComex + 'api/packingListController/obtenerPackingListAereo/'+ id;
    return this.http.get(url, {});
  }

  public registrarPackingListAereo(request: any) {
    const url = environment.apiUrlComex + 'api/packingListController/registrarPackingListAereo/';
    return this.http.post(url, request);
  }
}
