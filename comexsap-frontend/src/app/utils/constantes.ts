export class Constantes {
  public static readonly P_SI = 'S';
  public static readonly P_NO = 'N';

  public static readonly P_ESTADO_INICIAL = 5;
  public static readonly P_ESTADO_CONFIRMADO = 6;
  public static readonly P_ESTADO_ANULADO = 51;

  public static readonly P_AGENTE_ADUANA = 'BEAGLE';
  public static readonly P_SHIPPER = 'CORPORACION ACEROS AREQUIPA S.A.';
  public static readonly P_DIRECCION_SHIPPER = 'AV. ANTONIO MIRO QUESADA 425\nPISO 17, MAGDALENA DEL MAR\nLIMA, PERU\nRUC: 20370146994';
  public static readonly P_EMITIDO_EN = 'LIMA, PERÚ';
  public static readonly P_EMPAQUE = 'EMPAQUE';
  public static readonly P_MARCA = 'MARCAS';
  public static readonly P_UNIDAD_PRODUCTIVA = 'UNIDAD PRODUCTIVA';
  public static readonly P_PRECIO_UNITARIO = 'PRECIO UNITARIO';
  public static readonly P_FACTURACION = 'FACTURACIÓN';
  public static readonly P_FORMA_PAGO = 'FORMA DE PAGO';

  public static readonly P_CORP_AA_SA = '"CORPORACION ACEROS AREQUIPA S.A."';
  public static readonly P_CORP_AA_SRL = '"CORPORACION ACEROS AREQUIPA SRL"';
  public static readonly P_CORP_AA_NIT = '317032023';

  public static readonly P_D001 = "D001";
  public static readonly P_D002 = "D002";
  public static readonly P_D003 = "D003";//Incoterm
  public static readonly P_D004 = "D004";
  public static readonly P_D005 = "D005";
  public static readonly P_D006 = "D006";//Moneda
  public static readonly P_D007 = "D007";//Tipo Transporte
  public static readonly P_D008 = "D008";//Despacho
  public static readonly P_D009 = "D009";
  public static readonly P_D010 = "D010";//Estado
  public static readonly P_D011 = "D011";//Condicion Pago
  public static readonly P_D012 = "D012";
  public static readonly P_D013 = "D013";//Lista Precios
  public static readonly P_D014 = "D014";
  public static readonly P_D015 = "D015";//Agente Aduana
  public static readonly P_D016 = "D016";//Agente Naviera
  public static readonly P_D017 = "D017";//Agente Carga
  public static readonly P_D018 = "D018";
  public static readonly P_D019 = "D019";
  public static readonly P_D020 = "D020";
  public static readonly P_D021 = "D021";
  public static readonly P_D022 = "D022";//Régimen
  public static readonly P_D023 = "D023";//Partida Arancelaria
  public static readonly P_D024 = "D024";//Lugar de Despacho
  public static readonly P_D025 = "D025";//Facturación
  public static readonly P_D026 = "D026";//Peso Objetivo
  public static readonly P_D027 = "D027";//Tolerancia
  public static readonly P_D028 = "D028";//Almacenaje
  public static readonly P_D029 = "D029";//Peso en Etiquetas
  public static readonly P_D030 = "D030";//Número Etiqueta
  public static readonly P_D031 = "D031";
  public static readonly P_D032 = "D032";
  public static readonly P_D033 = "D033";
  public static readonly P_D034 = "D034";//Condicion Pago Cotizacion
  public static readonly P_D035 = "D035";
  public static readonly P_D036 = "D036";
  public static readonly P_D037 = "D037";
  public static readonly P_D038 = "D038";//Proveedores Control de Gastos Contenedores
  public static readonly P_D039 = "D039";//Conceptos Control de Gastos Contenedores
  public static readonly P_D040 = "D040";//Monedas Control de Gastos
  public static readonly P_D041 = "D041";//Conceptos Control de Gastos Carga Suelta

  public static readonly P_REGIMEN_DRAWBACK = 46;//Id Tabla Parametro
  public static readonly P_ENVIO_FISICO = 'F';
  public static readonly P_ENVIO_DIGITAL = 'D';
  public static readonly P_ID_INCOTERM_FOB = 5766;
  public static readonly P_ID_INCOTERM_CFR = 5748;
  public static readonly P_ID_INCOTERM_CIF = 5749;

  public static readonly P_ID_CONTENEDOR = 4;
  public static readonly P_ID_CARGASUELTA = 3;
  public static readonly P_ID_AEREO = 50;

  public static readonly P_ID_INTERCOMPANY = 49;

  public static readonly P_CARGASUELTA_ID_RUTA = 8587; //XA2001
  public static readonly P_CONTENEDOR_ID_RUTA = 8614; //XA3027

  public static readonly P_ID_DOLAR = 6151;

  public static readonly P_SOLICITUD_PEDIDO = 8; //PEDIDO FIRME
	public static readonly P_SOLICITUD_COTIZACION = 7; //COTIZACION

  public static readonly P_MSJ_SUPERO_LINEA_CREDITO = 'Cliente superó su línea de crédito';
  public static readonly P_MSJ_SALDO_INSUFICIENTE = 'Saldo insuficiente';
  public static readonly P_MSJ_SALDO_DISPONIBLE = 'Línea de crédito disponible';
  public static readonly P_MSJ_SIN_SALDO = 'Sin línea de crédito disponible';

  public static readonly P_STG_COTIZACION_FILTRO = 'filtroCotizacion';
  public static readonly P_STG_PEDIDOFIRME_FILTRO = 'filtroPedidoFirme';
  public static readonly P_STG_MARITIMO_CONTENEDORES_FILTRO = 'filtroMaritimoContenedores';
  public static readonly P_STG_MARITIMO_CARGASUELTA_FILTRO = 'filtroMaritimoCargaSuelta';
  public static readonly P_STG_MARITIMO_INTERCOMPANY_FILTRO = 'filtroMaritimoIntercompany';
  public static readonly P_STG_TERRESTRE_FILTRO = 'filtroTerrestre';
  public static readonly P_STG_AEREO_FILTRO = 'filtroAereo';
  public static readonly P_STG_PACKINGLIST_CONTENEDORES_FILTRO = 'filtroPackingListContenedores';
  public static readonly P_STG_PACKINGLIST_CARGASUELTA_FILTRO = 'filtroPackingListCargaSuelta';
  public static readonly P_STG_PACKINGLIST_AEREO_FILTRO = 'filtroPackingListAereo';
  public static readonly P_STG_REPORTE_CONTENEDORES_FILTRO = 'filtroReporteContenedores';
  public static readonly P_STG_REPORTE_CARGASUELTA_FILTRO = 'filtroReporteCargaSuelta';
  public static readonly P_STG_REPORTE_TERRESTRE_FILTRO = 'filtroReporteTerretre';
  public static readonly P_STG_CONTROLGASTOS_CONTENEDORES_FILTRO = 'filtroControlGastosContenedores';
  public static readonly P_STG_CONTROLGASTOS_CARGASUELTA_FILTRO = 'filtroControlGastosCargaSuelta';

  public static readonly P_CABECERA_CREAR_PEDIDO_SAP = {
    numeroPedido: "",
    orgVentas: "2000",
    canalDist: "20",
    sector: "00",
    tipoDoc: "ZEXP",
    grupoVend: "300",
    oficina: "3006",
    condExp: "ZJ",
    fechaPref: "",
    fechaFact: "",
    cotizacion: "",
    zonaVentas: "300000",
    tipoPago: "T",
    centroSum: "",//Primero de las Posiciones
    fechaOccli: "",
    motPedido: "ZVA",
    grupo: "",
    grupo1: "",
    grupoCli: "",
    condPago: "",
    fechaDoc: "",
    fechaPrecio: "",
    incoterm1: "",
    incoterm2: "",
    listaPrecio: "",
    moneda: "",
    numDespa: "PTL",
    glosa: ""
  }

  public static readonly P_TEXTOS_CREAR_PEDIDO_SAP = [
    {
      idTexto: 'ZV01',
      texto: ''
    },
    {
      idTexto: 'ZV02',
      texto: ''
    },
    {
      idTexto: 'ZVT1',
      texto: ''
    },
    {
      idTexto: 'ZVT2',
      texto: ''
    },
    {
      idTexto: 'ZVT3',
      texto: ''
    },
    {
      idTexto: 'ZVT4',
      texto: ''
    },
    {
      idTexto: 'ZVT5',
      texto: ''
    },
    {
      idTexto: 'ZVT6',
      texto: ''
    },
    {
      idTexto: 'ZVT7',
      texto: ''
    }
  ];

  public static readonly P_ROL_FECHA_DISPONIBILIDAD = "010";

  public static readonly P_ID_UM_KG = 140;
  public static readonly P_ID_UM_T = 295;
  public static readonly P_ID_UM_VAR = 310;

  public static readonly P_FACCOMERCIAL_PESOSAP = 1;
  public static readonly P_FACCOMERCIAL_PESOTEORICO = 2;
  public static readonly P_FACCOMERCIAL_PESOREAL = 3;

  public static readonly P_MONEDA_DEFECTO = 'USD';
}
