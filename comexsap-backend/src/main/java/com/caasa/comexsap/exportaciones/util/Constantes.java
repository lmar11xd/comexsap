package com.caasa.comexsap.exportaciones.util;

public class Constantes {

	public static final String PARAMETRO_D002 = "D002"; //Datos Correo
	public static final String PARAMETRO_D003 = "D003";
	public static final String PARAMETRO_D005 = "D005";
	public static final String PARAMETRO_D006 = "D006";
	public static final String PARAMETRO_D007 = "D007";
	public static final String PARAMETRO_D008 = "D008";
	public static final String PARAMETRO_D009 = "D009";
	public static final String PARAMETRO_D010 = "D010";
	public static final String PARAMETRO_D011 = "D011";
	public static final String PARAMETRO_D013 = "D013";
	public static final String PARAMETRO_D020 = "D020";
	public static final String APP_CODE = "COMEXSAP";
	public static final String MONEDA_USD = "USD";
	public static final String VACIO = "";
	public static final String CERO = "0";
	public static final String UNO = "1";
	public static final int INICIAL = 5;
	public static final int CONFIRMADO = 6;
	public static final int ANULADO = 51;
	public static final long CODIGO_SAP_INICIAL = 9900000000L;
	public static final int TIPO_SOLICITUD_PEDIDO = 8;
	public static final int TIPO_SOLICITUD_COTIZACION = 7;
	public static final int TIPO_TRANSPORTE_MARITIMO = 1;
	public static final int TIPO_TRANSPORTE_TERRESTRE = 2;
	public static final String CODE_TABLE_PACKING = "PL";
	public static final int LENGTH_TABLE_PACKING = 3;
	public static final int DESPACHO_CONTENEDOR = 4;
	public static final int DESPACHO_CARGA_SUELTA = 3;
	public static final int DESPACHO_AEREO = 50;
	
	//Grupos Correo
	public static final int ID_GRUPO_PEDIDOFIRME_PCP = 15;
	public static final int ID_GRUPO_PEDIDOFIRME_PRODUCCION = 16;
	public static final int ID_GRUPO_PEDIDOFIRME_SUMINISTRO = 17;
	public static final int ID_GRUPO_PEDIDOFIRME_PLANEAMIENTO = 18;
	public static final int ID_GRUPO_PEDIDOFIRME_COMERCIAL = 19;
	public static final int ID_GRUPO_PEDIDOFIRME_PLANEAMIENTO_COMERCIAL = 20;
	public static final int ID_GRUPO_PEDIDOFIRME_COMEX = 21;
	public static final int ID_GRUPO_PEDIDOFIRME_PRECIOS = 24;
	public static final int ID_GRUPO_MARITIMO_COMEX = 22;
	public static final int ID_GRUPO_TERRESTRE_COMEX = 23;

	// Reporte
	public static final String REPORTE_COTIZACION = "Reporte_cotizacion";
	public static final String PROFORMA = "N° FACTURA PROFORMA: N° CAASA - ";
	public static final String COTIZACION = "COTIZACIÓN: N° CAASA - ";
	public static final String TEXTO_COTIZACION = "cotización";
	public static final String TEXTO_PROFORMA = "proforma";
	public static final String SEGURO_INTERNACIONAL = "SEGURO INTERNACIONAL";
	public static final String CIF = "CIF";//Puerto Destino
	public static final String FCA = "FCA";//Puerto Origen
	public static final String CPT = "CPT";//Puerto Destino
	public static final String EXW = "EXW";//Lugar Despacho
	public static final String CFR = "CFR";//Puerto Destino
	public static final String FOB = "FOB";//Puerto Origen
	public static final String TOTAL = "TOTAL";
	public static final String ESPACIO = " ";
	public static final String FLETE = "FLETE";
	public static final String CNT = "40'CNT";
	public static final String SUBINDICE_CODIGO_MARITIMO = "DOCEXP";
	public static final String TRANSPORTE = "MARITIMO";
	public static final float TON_A_KG = 1000;
	public static final String KG = "KG";

	public static final String MARITIMO_EXPORTACION = "MaritimoExportacion";
	public static final String MARITIMO_FACTURA_COMERCIAL = "FacturaComercial";
	public static final String TERRESTRE_CERTIFICADO = "CertificadoVentaCredito";
	public static final String PRECIO = "PRECIO";
	public static final String MONEDA = "USD/UN";
	public static final String CODIGO_CLIENTE = "99";
	public static final String PEDIDOS_CONFIRMADOS = "Pedidos Firmes Confirmados";
	public static final String TOTAL_MARITIMO_ENVIADOS = "Documentos Maritimos Enviados";
	public static final String TOTAL_TERRESTRE_ENVIADO = "Documentos Terrestres Enviados";
	public static final String MARITIMO_CONTENEDOR = "Contenedores";
	public static final String MARITIMO_CARGA_SUELTA = "Carga Suelta";
	
	public static final String TEMPLATE_COTIZACION = "cotizacion.html";
	public static final String TEMPLATE_PEDIDO_FIRME = "pedido_firme.html";
	public static final String TEMPLATE_ACTUALIZACION_PRECIOS = "actualizacion_precios.html";
	public static final String TEMPLATE_FECHAS_DISPONIBILIDAD = "fechas_disponibilidad.html";

	//Factura
	public static final String FAC_SECTOR = "00";
	public static final String FAC_CLASE_EXP = "01";
	public static final String FAC_ESTADO = "Contabilizado";
	public static final String FAC_ORG_VENTA = "2000";
	public static final String FAC_CANAL = "200";
	public static final String FAC_USUARIO = "SYSTEM";
	public static final String FAC_ETIQ_SUBTOTAL_FOB = "TOTAL FOB";
	public static final String FAC_ETIQ_SUBTOTAL_CFR = "TOTAL CFR";
	public static final String FAC_ETIQ_SUBTOTAL_CIF = "TOTAL CIF";
	public static final String FAC_ETIQ_FLETE = "FLETES - EXPORT";
	public static final String FAC_ETIQ_IMPORTE_TOTAL = "IMPORTE TOTAL";
	public static final String FAC_ETIQ_IMPORTE_TOTAL_FOB = "IMPORTE TOTAL FOB";
	
	public static final short ACTIVO = 1;
	public static final short INACTIVO = 0;
	
	public static final short ETIQUETA_ARRIBA = 1;
	public static final short ETIQUETA_ABAJO = 2;
	
	public static final short FACT_COM_PRECIO_SAP = 1;//Precio coincide con SAP
	public static final short FACT_COM_PESO_TEORICO = 2;//A peso teorico
	public static final short FACT_COM_PESO_REAL = 3;//A peso real
}
