import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable()
export class ConfiguracionService {

  constructor (public http: HttpClient) {
  }

  public listarDominio() {
    const url = environment.apiUrlComex + 'api/configuracionController/listarDominio' ;
    return this.http.get(url, {});
  }

  public listarParametroxDominio(codigoDominio: string) {
    const url = environment.apiUrlComex + 'api/configuracionController/listarParametroxDominio/' + codigoDominio ;
    return this.http.get(url, {});
  }

  public listarParametroxCodigoSap(codigoDominio: string, codigoSap: String) {
    const url = environment.apiUrlComex + 'api/configuracionController/listarParametroxCodigoSap/' + codigoDominio + '/' + codigoSap ;
    return this.http.get(url, {});
  }

  public buscarParametroxDominio(codigoDominio: string, buscar: string) {
    const url = environment.apiUrlComex + 'api/configuracionController/buscarParametroxDominio/' + codigoDominio + '/' + buscar;
    return this.http.get(url, {});
  }

  public listarClientes(buscar: string) {
    const url = environment.apiUrlComex + 'api/clienteController/listarClientexNombre/' + buscar;
    return this.http.get(url, {});
  }

  public listarDestinatarios(codigoCliente: string) {
    const url = environment.apiUrlComex + 'api/destinatarioController/listarDestinatarioxCliente/' + codigoCliente;
    return this.http.get(url, {});
  }

  public listarPuertos(buscar: string) {
    const url = environment.apiUrlComex + 'api/puertoController/listarPuertoxNombre/' + buscar;
    return this.http.get(url, {});
  }

  public listarMateriales(buscar: string) {
    const url = environment.apiUrlComex + 'api/productoController/listarProductoxNombre/' + buscar;
    return this.http.get(url, {});
  }

  public obtenerMaterial(codigo: string) {
    const url = environment.apiUrlComex + 'api/productoController/obtenerProductoxCodigo/' + codigo;
    return this.http.get(url, {});
  }

  public listarUnidadMedida(codigoMaterial: string) {
    const url = environment.apiUrlComex + 'api/unidadMedidaController/listarUnidadMedidaxNombre/' + codigoMaterial;
    return this.http.get(url, {});
  }

  public obtenerUnidadMedidaxProducto(codigoMaterial: string, unidadMedida: string) {
    const url = environment.apiUrlComex + 'api/unidadMedidaController/obtenerUnidadMedidaxProductoUnidad/' + codigoMaterial + '/' + unidadMedida;
    return this.http.get(url, {});
  }

  public obtenerPrecioMaterial(request: any) {
    const url = environment.apiUrlComex + 'api/productoController/obtenerPrecioMaterial/';
    return this.http.post(url, request);
  }

  public listarPedidoPosicionDisponible(request: any) {
    const url = environment.apiUrlComex + 'api/exportacionMaritimoController/listarPedidoPosicion/';
    return this.http.post(url, request);
  }

  public listarCentros(buscar: string) {
    const url = environment.apiUrlComex + 'api/maestroController/listarCentro/' + buscar;
    return this.http.get(url, {});
  }

  public listarAlmacenes(idCentro: string) {
    const url = environment.apiUrlComex + 'api/maestroController/listarAlmacen/' + idCentro;
    return this.http.get(url, {});
  }

  public listarFamiliasConversion() {
    const url = environment.apiUrlComex + 'api/unidadMedidaController/listarFamiliasConversion';
    return this.http.get(url, {});
  }

  public listarPedidosIntercompanySap(request: any) {
    const url = environment.apiUrlComex + 'api/pedidoIntercompanyController/listarPedidosIntercompanySapDisponibles/';
    return this.http.post(url, request);
  }

  public listarEtiquetas(buscar: string) {
    const url = environment.apiUrlComex + 'api/etiquetaController/listarEtiquetas/' + buscar;
    return this.http.get(url, {});
  }

  public guardarEtiqueta(request: any) {
    const url = environment.apiUrlComex + 'api/etiquetaController/guardarEtiqueta/';
    return this.http.post(url, request);
  }

  public eliminarEtiqueta(id: number, usuario: string) {
    const url = environment.apiUrlComex + 'api/etiquetaController/eliminarEtiqueta/' + id + '?usuario=' + usuario;
    return this.http.post(url, {});
  }

  public obtenerInfoCliente(codigo: string) {
    const url = environment.apiUrlComex + 'api/clienteController/obtenerInfoClientexCodigo/' + codigo;
    return this.http.get(url, {});
  }

  public obtenerOrdenInternaxFiltro(request: any) {
    const url = environment.apiUrlComex + 'api/ordenInternaController/obtenerOrdenInternaxFiltro/';
    return this.http.post(url, request);
  }

  public obtenerTipoCambioActual(moneda: string, fecha: String) {
    const url = environment.apiUrlComex + 'api/tipoCambioController/obtenerTipoCambioActual/' + moneda + '/' + fecha ;
    return this.http.get(url, {});
  }
}
