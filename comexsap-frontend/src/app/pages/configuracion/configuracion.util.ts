import { FormArray, FormGroup } from "@angular/forms";
import { Parametro } from "./to/parametro";
import { UnidadMedida } from "./to/unidadmedida";
import { PrecioMaterial } from "./to/preciomaterial";
import { Utils } from "src/app/utils/utils";
import { ExcelMaterial } from "./to/excelmaterial";
import { Constantes } from "src/app/utils/constantes";
import * as moment from 'moment';
import { DAY_FORMAT } from 'src/app/utils/dayformat';
import { ConversionFamilia } from '../documentomaritimo/to/conversionfamilia';
import { PedidoFirme } from "../pedidofirme/to/pedidofirme";
import { PedidoIntercompany } from "../documentomaritimo/to/pedidointercompany";
import { DocumentoMaritimo } from "../documentomaritimo/to/documentomaritimo";
import { Almacen } from "./to/almacen";
import { Ruta } from "./to/ruta";
import { Centro } from "./to/centro";

export class ConfiguracionUtil {
  public static obtenerParametroxId(lista: Parametro[], id: number) {
    if(lista != null) {
      return lista.find(o => o.id == id);
    }
    return null;
  }

  public static obtenerPesoNominal(posicionForm: FormGroup) {
    let pesoNominal = 0;
    if(posicionForm.value.selectedUnidadMedida != null
      && posicionForm.value.listaUnidadesMedida != null) {
      const unidadSeleccionada = posicionForm.value.selectedUnidadMedida as UnidadMedida;
      if(unidadSeleccionada.codigo == "KG") {
        pesoNominal = 1;
      } else {
        const listaUnidades = posicionForm.value.listaUnidadesMedida as UnidadMedida[];
        const unidadKilogramos = listaUnidades.find(um => um.codigo == "KG");
        pesoNominal = unidadKilogramos.pesoNominal;

        if(unidadSeleccionada.codigo != unidadSeleccionada.unidadMedidaBase) {
          pesoNominal = pesoNominal * (1 / unidadSeleccionada.pesoNominal);
        }
      }
    }
    return pesoNominal;
  }

  public static actualizarPesoToneladas(posicionForm: FormGroup) {
    let pesoToneladas = 0;
    if(posicionForm.value.cantidadVenta != null
      && posicionForm.value.cantidadVenta > 0) {
      const pesoNominal = this.obtenerPesoNominal(posicionForm);
      pesoToneladas = (posicionForm.value.cantidadVenta * pesoNominal) / 1000;
      posicionForm.controls['pesoToneladas'].patchValue(pesoToneladas);
    }
  }

  public static actualizarPrecioUnitarioSap(posicionForm: FormGroup, actualizarPrecio: boolean) {
    let precioUnitario = 0;
    if(posicionForm.value.precioMaterialSap != null
      && posicionForm.value.listaUnidadesMedida != null
      && posicionForm.value.selectedUnidadMedida != null) {
        let factor = 1;
        const precioMaterial = posicionForm.value.precioMaterialSap as PrecioMaterial;
        const unidadSeleccionada = posicionForm.value.selectedUnidadMedida as UnidadMedida;

        if(unidadSeleccionada.codigo == precioMaterial.unidadMedida) {
          factor = 1;
        } else {
          const listaUnidades = posicionForm.value.listaUnidadesMedida as UnidadMedida[];
          const unidadPrecio = listaUnidades.find(um => um.codigo == precioMaterial.unidadMedida);
          factor = unidadPrecio.pesoNominal;

          if(unidadPrecio.unidadMedidaBase != unidadSeleccionada.codigo) {
            factor = factor * (1 / unidadSeleccionada.pesoNominal);
          }
        }

        precioUnitario = parseFloat(Utils.round(factor * precioMaterial.importe / precioMaterial.cantidad, 2));
        posicionForm.controls['precioUnitarioSap'].patchValue(precioUnitario);
        if(actualizarPrecio) {
          posicionForm.controls['precioUnitario'].patchValue(precioUnitario);
        }
    } else {
      posicionForm.controls['precioUnitarioSap'].patchValue(0);
    }
  }

  public static actualizarPrecioUnitario(posicionForm: FormGroup) {//Solo Documento Maritimo
    let precioUnitario = 0;
    if(posicionForm.value.precioMaterialSap != null
      && posicionForm.value.listaUnidadesMedida != null
      && posicionForm.value.selectedUnidadMedida != null) {
        let factor = 1;
        const precioMaterial = posicionForm.value.precioMaterialSap as PrecioMaterial;
        const unidadSeleccionada = posicionForm.value.selectedUnidadMedida as UnidadMedida;
        if(unidadSeleccionada.codigo == precioMaterial.unidadMedida) {
          factor = 1;
        } else {
          const listaUnidades = posicionForm.value.listaUnidadesMedida as UnidadMedida[];
          const unidadPrecio = listaUnidades.find(um => um.codigo == precioMaterial.unidadMedida);
          factor = unidadPrecio.pesoNominal;

          if(unidadPrecio.unidadMedidaBase != unidadSeleccionada.codigo) {
            factor = factor * (1 / unidadSeleccionada.pesoNominal);
          }
        }

        precioUnitario = parseFloat(Utils.round(factor * precioMaterial.importe / precioMaterial.cantidad, 2));
        posicionForm.controls['precioUnitario'].patchValue(precioUnitario);
    }
  }

  public static actualizarImporte(posicionForm: FormGroup) {
    let importe = 0;
    if(posicionForm.value.cantidadVenta != null
      && posicionForm.value.cantidadVenta > 0
      && posicionForm.value.selectedUnidadMedida != null
    ) {
      importe = posicionForm.value.cantidadVenta * posicionForm.value.precioUnitario;
      posicionForm.controls['importe'].patchValue(importe);
    }
  }

  public static actualizarCantidadSaldo(posicionForm: FormGroup) {
    const cantidadSaldoInicial = posicionForm.controls['cantidadSaldoInicial'].value;
    let cantidadVenta = posicionForm.controls['cantidadVenta'].value;
    if(cantidadVenta == null) cantidadVenta = 0;
    posicionForm.controls['cantidadSaldo'].patchValue(cantidadSaldoInicial - cantidadVenta);
  }

  public static actualizarComponenteTexto(posicionForm: FormGroup) {
    let textoComponente = '';

    if(posicionForm.value.componentes != null) {
      posicionForm.value.componentes.forEach((componente: any) => {
        textoComponente += '\n' + Utils.formatearCantidad(componente.cantidadVenta) + ' ' + componente.unidadMedidaVenta + ' ' + componente.descripcionMaterial;
      });

      textoComponente = Utils.formatearCantidad(posicionForm.value.cantidadVenta) + ' ' + posicionForm.value.unidadMedidaVenta + ' ' + posicionForm.value.descripcion + textoComponente;
      posicionForm.controls['componenteTexto'].patchValue(textoComponente);
    }
  }

  public static limpiarPosicion(posicionForm: FormGroup) {
    posicionForm.controls['codigoMaterial'].patchValue("");
    posicionForm.controls['descripcionMaterial'].patchValue(null);
    posicionForm.controls['cantidadVenta'].patchValue(null);
    posicionForm.controls['unidadMedidaVenta'].patchValue(null);
    posicionForm.controls['selectedUnidadMedida'].patchValue(null);
    posicionForm.controls['listaUnidadesMedida'].patchValue([]);
    posicionForm.controls['pesoToneladas'].patchValue(0);
    posicionForm.controls['precioUnitarioSap'].patchValue(0);
    posicionForm.controls['precioUnitario'].patchValue(null);
    posicionForm.controls['importe'].patchValue(0);
    posicionForm.controls['precioMaterialSap'].patchValue(null);
  }

  public static dataMaterialToArray(data: string) {//Ejm: '402785\t\t200\r\n402798\t\t150\r\n400487\t\t30.5\r\n408144\t\t130\r\n'
    const isNumeric = n => !isNaN(n);
    let result: ExcelMaterial[] = [];
    if(data != null && data != "" && data.trim() != "") {
      const arrData = data.split('\n');
      arrData.forEach(element => {
        if(element != "" && element.trim() != "") {
          const linea = element.replace(/[\t]+/gm, '#');
          const arrElement = linea.replace('\r', '').split('#');
          const n = arrElement.length;
          if(n == 1) {
            result.push(new ExcelMaterial(arrElement[0], 1, 0));
          } else if(n == 2) {
            result.push(new ExcelMaterial(arrElement[0], parseFloat(arrElement[1].replace(',', '')), 0));
          } else if (n == 3) {
            if (isNumeric(arrElement[1].replace(',',''))) {
              result.push(new ExcelMaterial(arrElement[0], parseFloat(arrElement[1].replace(',', '')), parseFloat(arrElement[2])));
            } else {
              result.push(new ExcelMaterial(arrElement[0], parseFloat(arrElement[2]), 0));
            }
          } else if (n > 3) {
            result.push(new ExcelMaterial(arrElement[0], parseFloat(arrElement[n-2]), parseFloat(arrElement[n-1])));
          }
        }
      });
    }
    return result;
  }

  public static hayLineaCredito(responseLineaCredito: any) {
    let hayLineaCredito = true;
    if(responseLineaCredito != null) {
      const salida = responseLineaCredito.salida;
      salida?.forEach(item => {
        if(item.descMsj.includes(Constantes.P_MSJ_SALDO_INSUFICIENTE)
          || item.descMsj.includes(Constantes.P_MSJ_SUPERO_LINEA_CREDITO)) {//S: SUCCESS
          hayLineaCredito = false;
        }
      });
    }
    return hayLineaCredito;
  }

  public static obtenerNumeroPedido(responsePedidoSap: any) {
    let estado = 0, hayError = false, mensajeError = '', numeroPedido = '';
    if(responsePedidoSap != null) {
      const salida = responsePedidoSap.salida;
      salida?.forEach(item => {
        if(item.tipoMsj == "S") {//S: SUCCESS
          estado = 1;
          numeroPedido = item.numPedido;
        } else if(item.tipoMsj == "PE") {//PEDIDO YA EXISTE
          estado = 1;
          mensajeError = item.descMsj;
        } else {
          estado = 1;
          mensajeError += "***" + item.descMsj;
          hayError = true;
        }
      });
    }
    return {estado, hayError, mensajeError, numeroPedido};
  }

  public static obtenerRespuestaSap(responsePedidoSap: any) {
    let hayError = false, mensajeError = '', numeroPedido = '';
    if(responsePedidoSap != null) {
      const salida = responsePedidoSap.salida;
      salida?.forEach(item => {
        if(item.tipoMsj == "S") {//S: SUCCESS
          numeroPedido = item.numPedido;
        } else {
          mensajeError += "***" + item.descMsj;
          hayError = true;
        }
      });
    }
    return {hayError, mensajeError, numeroPedido};
  }

  public static obtenerDatosFacturaComercial(dataDocumento: any, formDocumento: FormGroup, formPosiciones: FormArray, formFacturas: FormArray, incoterms: Parametro[], partidas: Parametro[], nombreUsuario: string, usuario: string, codigoPersonal: string) {
    let posiciones = [], facturas = [], totales = [];
    let item = 0;
    let subTotal = 0, importeTotal = 0, importeFlete = 0, importeSeguro = 0;
    let esPesoPackingList = false;
    let unidadMedida = 'UN', condicionFlete = '';

    const fechaFactura = dataDocumento.fechaFactura;
    const textoFecha = fechaFactura ?  moment(fechaFactura).locale("es-PE").format('MMMM DD, YYYY').toUpperCase() : '';

    formPosiciones.controls.forEach((posicionForm: FormGroup) => {
      const posicionFormData = posicionForm.value;
      item += 1;
      subTotal += posicionFormData.importe;

      let descripcionPartida = '';

      if(posicionFormData.idPartidaArancelaria != null && posicionFormData.idPartidaArancelaria != 0) {
        const partida = partidas?.find(o => o.id == posicionFormData.idPartidaArancelaria);
        if(partida != null) {
          descripcionPartida = partida.descripcion + '<br/>NANDINA No.: ' + partida.codigo;
        }
      }

      if(dataDocumento.idDespacho == Constantes.P_ID_CONTENEDOR
        && (posicionFormData.unidadMedidaVenta == 'T' || posicionFormData.unidadMedidaVenta == 'KG')) {
          esPesoPackingList = true;
      }

      unidadMedida = posicionFormData.unidadMedidaVenta

      if(unidadMedida == 'T') {
        unidadMedida = 'TM';
      }

      posiciones.push({
        id: posicionFormData.id,
        item: item,
        codigo: posicionFormData.codigoMaterial,
        descripcion: posicionFormData.descripcion,
        descripcionMaterial: posicionFormData.descripcionMaterial,
        componenteTexto: posicionFormData.componenteTexto,
        descripcionPartida: descripcionPartida,
        pesoNeto: posicionFormData.pesoToneladas,
        cantidad: posicionFormData.cantidadVenta,
        precio: posicionFormData.precioUnitario,
        importe: posicionFormData.importe,
        referencia: posicionFormData.referencia,
        padre: posicionFormData.esPadre
      });
    });

    formFacturas.controls.forEach((facturaForm: FormGroup) => {
      const facturaFormData = facturaForm.value;
      facturas.push({
        codigoPedido: facturaFormData.codigoPedido,
        factura: facturaFormData.factura,
        etiquetaUnidadMedida: facturaFormData.etiquetaUnidadMedida,
        etiquetaTotal: facturaFormData.etiquetaTotal,
        etiquetaFlete: facturaFormData.etiquetaFlete,
        etiquetaImporteTotal: facturaFormData.etiquetaImporteTotal,
        montoTotal: facturaFormData.montoTotal,
        montoFlete: facturaFormData.montoFlete,
        montoImporteTotal: facturaFormData.montoImporteTotal
      });
    });

    const formData = formDocumento.getRawValue();

    importeFlete = formData.importeFlete ? formData.importeFlete : 0;
    importeSeguro = formData.importeSeguro ? formData.importeSeguro : 0;

    const incoterm = this.obtenerParametroxId(incoterms, formData.idIncoterm);
    const incotermComercial = this.obtenerParametroxId(incoterms, formData.idIncotermComercial);
    let etiquetaTotal ="TOTAL " + incotermComercial?.codigo,
    etiquetaSeguro = "SEGURO INTERNACIONAL",
    etiquetaFlete = 'FLETES - EXPORT',
    etiquetaImporteTotal = "IMPORTE TOTAL";

    if(facturas.length > 0) {
      unidadMedida = facturas[0].etiquetaUnidadMedida;
      etiquetaTotal = facturas[0].etiquetaTotal;
      etiquetaFlete = facturas[0].etiquetaFlete;
      etiquetaImporteTotal = facturas[0].etiquetaImporteTotal;
      subTotal = facturas[0].montoTotal;
      importeFlete = facturas[0].montoFlete;
      importeTotal = facturas[0].montoImporteTotal;
    }

    if(formData.idIncotermComercial == Constantes.P_ID_INCOTERM_CFR && formData.idIncoterm == Constantes.P_ID_INCOTERM_CFR) {
      importeTotal = subTotal - importeFlete - importeSeguro;
    } else if(formData.idIncotermComercial == Constantes.P_ID_INCOTERM_CIF && formData.idIncoterm == Constantes.P_ID_INCOTERM_CIF) {
      importeTotal = subTotal - importeFlete - importeSeguro;
    } else {
      importeTotal = subTotal + importeFlete + importeSeguro;
    }

    if(formData.idIncotermComercial == Constantes.P_ID_INCOTERM_CFR || formData.idIncotermComercial == Constantes.P_ID_INCOTERM_CIF) {
      condicionFlete = 'PREPAID';
    } else {
      condicionFlete = 'COLLECT';
    }

    totales.push({etiqueta: etiquetaTotal, importe: subTotal});

    if(importeSeguro > 0) {
      totales.push({etiqueta: etiquetaSeguro, importe: importeSeguro});
    }

    if(importeFlete > 0) {
      totales.push({etiqueta: etiquetaFlete, importe: importeFlete});
    }

    totales.push({etiqueta: etiquetaImporteTotal, importe: importeTotal});

    const shipper = "<strong>" + formData.shipper + "</strong><br/>" + formData.direccionShipper;
    const direccionConsignatario = formData.direccionConsignatario ? formData.direccionConsignatario : "";

    const data = {
      idExportacion: dataDocumento.id,
      factura: dataDocumento.serieCodFactura ? dataDocumento.serieCodFactura : '',
      textoFecha: textoFecha,
      emitidoEn: formData.emitidoEn ? formData.emitidoEn : "",
      direccionShipper: shipper,
      consignatario: formData.consignatario,
      direccionConsignatario: direccionConsignatario,
      cliente: dataDocumento.codigoSapCliente,
      puertoEmbarque: dataDocumento.puertoOrigen + " - " + dataDocumento.paisPuertoOrigen,
      navio: formData.nave ? formData.nave : "",
      puertoDescarga: dataDocumento.puertoDestino + " - " + dataDocumento.paisPuertoDestino,
      booking: formData.booking ? formData.booking : "",
      condicionFlete: condicionFlete,
      formaPago: dataDocumento.nombreCondicionPago,
      paisOrigen: dataDocumento.paisDescripOrigen,
      referencia: posiciones.length > 0 ? posiciones[0].referencia : "",
      etiquetaTotal: etiquetaTotal,
      descripcionTotal: formData.descripcionTotal,
      importeTotal: importeTotal,
      unidadMedida: unidadMedida,
      nombreUsuario: nombreUsuario,
      posiciones: posiciones,
      incoterm: incoterm?.codigo,
      incotermComercial: incotermComercial?.codigo,
      flete: importeFlete,
      seguro: importeSeguro,
      totales: totales,
      pesoPackingList: esPesoPackingList,
      padre: formData.esPadre,
      tipoDespacho: formData.idTipoDespacho,
      codigoPersonal: codigoPersonal
    };

    let request : any = {};
    request.formulario = data;
    request.usuario = usuario;
    return request;
  }

  public static obtenerDatosExportacion(dataDocumento: any, formDocumento: FormGroup, formPosiciones: FormArray, regimenes: Parametro[], partidas: Parametro[], usuario: string) {
    let posiciones = [];
    let item = 0;
    let importeTotal = 0, importeFlete = 0, importeSeguro = 0, subTotal = 0;

    const formData = formDocumento.getRawValue();
    const idRegimen = formData.idRegimen ? formData.idRegimen : 0;
    const regimen = ConfiguracionUtil.obtenerParametroxId(regimenes, idRegimen);

    importeFlete = formData.importeFlete ? formData.importeFlete : 0;
    importeSeguro = formData.importeSeguro ? formData.importeSeguro : 0

    formPosiciones.controls.forEach((posicionForm: FormGroup) => {
      const posicionFormData = posicionForm.value;
      const idPartida = posicionFormData.idPartidaArancelaria ? posicionFormData.idPartidaArancelaria : 0;
      const partida = ConfiguracionUtil.obtenerParametroxId(partidas, idPartida);

      item += 10;
      subTotal += posicionFormData.importe;

      posiciones.push({
        item: item,
        descripcion: posicionFormData.descripcionMaterial,
        regimen: regimen ? regimen.descripcion : "",
        cantidad: posicionFormData.cantidadVenta,
        valorFOB: posicionFormData.importe,
        partida: partida ? partida.codigo: ""
      });
    });

    let etiquetaValor = '';
    if(formData.idIncotermComercial == Constantes.P_ID_INCOTERM_CFR || formData.idIncotermComercial == Constantes.P_ID_INCOTERM_CIF) {
      importeTotal = subTotal - (importeFlete + importeSeguro);
      etiquetaValor = 'Valor CFR Total';
    } else {
      importeTotal = subTotal + importeFlete + importeSeguro;
      etiquetaValor = 'Valor FOB Total';
    }

    const data = {
      booking: formData.booking ? formData.booking : "",
      agenteAduana: formData.agenteAduana ? formData.agenteAduana : "",
      shipper: formData.shipper ? formData.shipper : "",
      direccionShipper: formData.direccionShipper ? formData.direccionShipper : "",
      consignatario: formData.consignatario ? formData.consignatario : "",
      direccionConsignatario: formData.direccionConsignatario ? formData.direccionConsignatario : "",
      notificante: formData.notificante ? formData.notificante : "",
      direccionNotificante: formData.direccionNotificante ? formData.direccionNotificante : "",
      puertoOrigen: dataDocumento.puertoOrigen + " - " + dataDocumento.paisPuertoOrigen,
      puertoDestino: dataDocumento.puertoDestino + " - " + dataDocumento.paisPuertoDestino,
      fechaCarguio: formData.fechaCarguio ? Utils.dateToStringFormat(formData.fechaCarguio, DAY_FORMAT.DDMMYYYY_SLASH) : "",
      tipoTransporte: "MARÍTIMO",
      motonave: formData.nave ? formData.nave : "",
      regimen: regimen ? regimen.descripcion : "",
      etiquetaValor: etiquetaValor,
      valorFOBTotal: subTotal,
      fleteTotal: importeFlete,
      seguroTotal: importeSeguro,
      valorCFRTotal: importeTotal,
      posiciones: posiciones
    };

    let request : any = {};
    request.formulario = data;
    request.usuario = usuario;
    return request;
  }

  public static convertirMaterial(posicionForm: FormGroup, conversionFamilias: ConversionFamilia[]) {
    const posicionData = posicionForm.getRawValue();
    const conversion = conversionFamilias.find(o => o.codigo == posicionData.codigoLinea && o.unidadMedida == posicionData.unidadMedidaVenta);

    if(conversion != null) {
      const listaUnidadesMedida = posicionData.listaUnidadesMedida as UnidadMedida[];

      //Convertir T a VAR
      const unidadMedidaVarillas = listaUnidadesMedida.find(o => o.codigo == conversion.unidadMedidaConversion) as UnidadMedida;
      if(unidadMedidaVarillas == null) return;

      let cantidadKilogramos = 0, cantidadVarillas = 0;
      const cantidadToneladas = posicionData.cantidadSaldoInicial;

      cantidadKilogramos = cantidadToneladas * 1000;
      cantidadVarillas = cantidadKilogramos * unidadMedidaVarillas.pesoNominal;

      cantidadKilogramos = parseFloat(Utils.round(cantidadKilogramos, 0));
      cantidadVarillas = parseFloat(Utils.round(cantidadVarillas, 2));

      posicionForm.controls['cantidadSaldoInicial'].patchValue(cantidadVarillas);
      posicionForm.controls['cantidad'].patchValue(cantidadKilogramos);
      posicionForm.controls['cantidadVenta'].patchValue(cantidadVarillas);
      posicionForm.controls['cantidadConversion'].patchValue(cantidadToneladas);
      posicionForm.controls['unidadMedidaVenta'].patchValue(conversion.unidadMedidaConversion);
      posicionForm.controls['idUnidadMedida'].patchValue(Constantes.P_ID_UM_KG);
      posicionForm.controls['idUnidadMedidaVenta'].patchValue(Constantes.P_ID_UM_VAR);
      posicionForm.controls['idUnidadMedidaConversion'].patchValue(Constantes.P_ID_UM_T);
      posicionForm.controls['selectedUnidadMedida'].patchValue(unidadMedidaVarillas);
      posicionForm.controls['precioUnitarioConversion'].patchValue(posicionData.precioUnitarioConversion);

      this.actualizarPrecioUnitario(posicionForm);
    }
  }

  public static actualizarCantidadConversion(posicionForm: FormGroup, conversionFamilias: ConversionFamilia[]) {
    const posicionData = posicionForm.getRawValue();
    const conversion = conversionFamilias.find(o => o.codigo == posicionData.codigoLinea && o.unidadMedidaConversion == posicionData.unidadMedidaVenta);

    //Se hizo conversion?
    if(conversion) {
      const cantidadVenta = posicionData.cantidadVenta;
      const unidadMedidaVarillas = posicionData.selectedUnidadMedida as UnidadMedida;

      //Conversion VAR a T
      let cantidadConversion = (cantidadVenta / unidadMedidaVarillas.pesoNominal) / 1000;
      cantidadConversion = parseFloat(Utils.round(cantidadConversion, 4));
      posicionForm.controls['cantidadConversion'].patchValue(cantidadConversion);

    }
  }

  public static calcularCantidadSaldo(posicionForm: FormGroup, conversionFamilias: ConversionFamilia[]) {
    const posicionData = posicionForm.getRawValue();
    const conversion = conversionFamilias.find(o => o.codigo == posicionData.codigoLinea && o.unidadMedidaConversion == posicionData.unidadMedidaVenta);
    const cantidadSaldoInicial = posicionData.cantidadSaldoInicial;

    if(conversion) {
      const listaUnidadesMedida = posicionData.listaUnidadesMedida as UnidadMedida[];

      //Convertir T a VAR
      const unidadMedidaVarillas = listaUnidadesMedida.find(o => o.codigo == conversion.unidadMedidaConversion) as UnidadMedida;
      if(unidadMedidaVarillas == null) return;

      let cantidadKilogramos = 0, cantidadVarillas = 0;

      cantidadKilogramos = cantidadSaldoInicial * 1000;
      cantidadVarillas = cantidadKilogramos * unidadMedidaVarillas.pesoNominal;

      cantidadVarillas = parseFloat(Utils.round(cantidadVarillas, 2));

      posicionForm.controls['cantidadSaldo'].patchValue(cantidadVarillas);
      posicionForm.controls['cantidadSaldoInicial'].patchValue(cantidadVarillas + posicionData.cantidadVenta);
    } else {
      posicionForm.controls['cantidadSaldo'].patchValue(cantidadSaldoInicial);
      posicionForm.controls['cantidadSaldoInicial'].patchValue(cantidadSaldoInicial + posicionData.cantidadVenta);
    }
  }

  public static obtenerDatosPedidoSap(esIntercompany: boolean, dataDocumento: DocumentoMaritimo, formDocumento: FormGroup, formPosiciones: FormArray, centros: Centro[], ruta: Ruta) {
    const cabecera: any[] = [];
    let dataCabecera = Constantes.P_CABECERA_CREAR_PEDIDO_SAP;
    dataCabecera.fechaPref = Utils.dateYMD(new Date());
    dataCabecera.cotizacion = dataDocumento.codigo;
    dataCabecera.fechaOccli = Utils.dateYMD(new Date());
    dataCabecera.condPago = dataDocumento.codigoCondicionPago;
    dataCabecera.fechaDoc = Utils.dateYMD(new Date());
    dataCabecera.fechaPrecio = Utils.dateYMD(dataDocumento.fechaListaPrecio);
    dataCabecera.incoterm1 = dataDocumento.codigoIncoterm;
    dataCabecera.incoterm2 = dataDocumento.codigoIncoterm;
    dataCabecera.listaPrecio = dataDocumento.codigoListaPrecio;
    dataCabecera.moneda = dataDocumento.codigoMoneda;
    dataCabecera.glosa = dataDocumento.glosa ? dataDocumento.glosa : "";
    cabecera.push(dataCabecera);

    let condiciones: any[] = [];
    let detalle: any[] = [];
    let repartos: any[] = [];
    let interlocutor: any[] = [];

    let pedidoSap = '';

    //Cliente
    interlocutor.push({
      codInte: dataDocumento.codigoSapCliente,
      tipoInt: "WE"
    });

    //Destinatario
    interlocutor.push({
      codInte: dataDocumento.codigoSapCliente,
      tipoInt: "AG"
    });

    //Vendedor
    interlocutor.push({
      codInte: "",
      tipoInt: "VE"
    });

    interlocutor.push({
      codInte: dataDocumento.codigoSapCliente,
      tipoInt: "RE"
    });

    interlocutor.push({
      codInte: dataDocumento.codigoSapCliente,
      tipoInt: "RG"
    });

    const formData = formDocumento.getRawValue();

    const importeFlete = formData.importeFlete ? formData.importeFlete : 0;
    const importeSeguro = formData.importeSeguro ? formData.importeSeguro : 0;

    //Condiciones
    if(Utils.toNumber(importeFlete) > 0) {
      condiciones.push({
        condPos: "",
        condPr: "ZFLE",
        condBp: Utils.toNumber(importeFlete),
        condVal: Utils.toNumber(importeFlete),
        koein: dataDocumento.codigoMoneda,
        kmein: "",
        kpein: ""
      });
    }

    if(Utils.toNumber(importeSeguro) > 0) {
      condiciones.push({
        condPos: "",
        condPr: "ZSEG",
        condBp: Utils.toNumber(importeSeguro),
        condVal: Utils.toNumber(importeSeguro),
        koein: dataDocumento.codigoMoneda,
        kmein: "",
        kpein: ""
      });
    }

    let item = 10;

    let codigoCentro = '';
    let codigoAlmacen = '';

    formPosiciones.controls.forEach((posicionForm: FormGroup) => {
      const posicionFormData = posicionForm.value;
      const codigoRuta = ruta ? ruta.codigo : '';

      pedidoSap = posicionFormData.pedidoSap;

      if(esIntercompany) {
        codigoCentro = posicionFormData.codigoCentro;
        codigoAlmacen = posicionFormData.codigoAlmacen;
      } else {
        const centro = centros.find(o => o.id == posicionFormData.idCentro);
        const almacenes = posicionFormData.almacenes as Almacen[];
        const almacen = almacenes.find(o => o.id == posicionFormData.idAlmacen);
        codigoCentro = centro ? centro.codigo : '';
        codigoAlmacen = almacen ? almacen.codigo : '';
      }

      detalle.push({
        material: Utils.toCodeMaterial(posicionFormData.codigoMaterial),
        cantidad: posicionFormData.cantidadVenta,
        centro: codigoCentro,
        almacen: codigoAlmacen,
        ruta: codigoRuta,
        posicion: item,
        tdesExs: '4',
        tdesFsu: '60',
        tipoPosicion: 'ZEXP',
        unidad: posicionFormData.unidadMedidaVenta,
        motRech: "",
        numDmo: "",
        numLote: "",
        numPmo: "",
        posSup: "",
        tdesLim: "",
        tipoNc: "",
      });

      repartos.push({
        cantidad: posicionFormData.cantidadVenta,
        fechaPrefe: Utils.dateYMD(new Date()),
        posnr: item
      });

      item += 10;
    });

    cabecera[0].centroSum = codigoCentro;
    cabecera[0].numeroPedido = pedidoSap;

    const data = {
      codigoCliente: dataDocumento.codigoSapCliente,
      cabecera: cabecera,
      condiciones: condiciones,
      detalle: detalle,
      repartos: repartos,
      interlocutor: interlocutor,
      testrun: "",
      textos: Constantes.P_TEXTOS_CREAR_PEDIDO_SAP,
    };

    return data;
  }

  public static validarCabeceraPedidos(pedidos: PedidoFirme[]) {
    //Las cabeceras de los pedidos seleccionados deben coincidir en los siguientes campos:
    //Cliente, Destinatario de mercancía, Puerto de origen, Puerto de destino, Incoterm, Condición de pago, Lista de Precio y Moneda
    const primerPedido = pedidos[0];
    let esValido = true;
    pedidos.forEach((pedido: PedidoFirme) => {
      if(pedido.idCliente != primerPedido.idCliente ||
        pedido.idDestinatario != primerPedido.idDestinatario ||
        pedido.idPuertoOrigen != primerPedido.idPuertoOrigen ||
        pedido.idPuertoDestino != primerPedido.idPuertoDestino ||
        pedido.idIncoterm != primerPedido.idIncoterm ||
        //pedido.idIncotermComercial != primerPedido.idIncotermComercial ||
        pedido.idCondicionPago != primerPedido.idCondicionPago ||
        pedido.idListaPrecio != primerPedido.idListaPrecio ||
        pedido.idMoneda != primerPedido.idMoneda) {
        esValido = false;
        return;
      }
    });
    return esValido;
  }

  public static validarCabeceraPedidosIntercompany(pedidos: PedidoIntercompany[]) {
    //Las cabeceras de los pedidos seleccionados deben coincidir en los siguientes campos:
    //Cliente, Destinatario de mercancía, Puerto de origen, Puerto de destino, Incoterm, Condición de pago, Lista de Precio y Moneda
    const primerPedido = pedidos[0];
    let esValido = true;
    pedidos.forEach((pedido: PedidoIntercompany) => {
      if(pedido.codigoCliente != primerPedido.codigoCliente ||
        pedido.codigoDestinatario != primerPedido.codigoDestinatario ||
        pedido.idPuertoOrigen != primerPedido.idPuertoOrigen ||
        pedido.idPuertoDestino != primerPedido.idPuertoDestino ||
        pedido.idIncoterm != primerPedido.idIncoterm ||
        //pedido.idIncotermComercial != primerPedido.idIncotermComercial ||
        pedido.idCondicionPago != primerPedido.idCondicionPago ||
        pedido.idListaPrecio != primerPedido.idListaPrecio ||
        pedido.idMoneda != primerPedido.idMoneda) {
        esValido = false;
        return;
      }
    });
    return esValido;
  }
}
