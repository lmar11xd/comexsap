package com.caasa.comexsap.exportaciones.enums;


public enum MensajesRespuestaEstandarEnum {
	
	MSG_OK_REGISTRO("Su registro se realizo correctamente"),
	MSG_ERROR_REGISTRO("Ocurrio un error al grabar su registro"),
	MSG_ERROR_BORRAR("Ocurrio un error al borrar registro"),
	MSG_ERROR_CONSULTA("Ocurrio un error al consultar los datos"),
	MSG_SIN_REGISTROS("No se encontraron registros"),
	MSG_ERROR_BLOQUEADO("El documento esta siendo tratado por "),
	MSG_ERROR_NO_BLOQUEADO("El documento no ha sido bloqueado por el usuario "),
	MSG_ERROR_DOCUMENTO_NO_PENDIENTE_FIRMA("El documento no se encuentra pendiente de firma "),
	MSG_ERROR_DOCUMENTO_NO_FIRMADO("El documento no fue firmado "),
	MSG_ERROR_DOCUMENTO_EVALUADO("El documento ya fue evaluado "),
	MSG_ERROR_ETAPA_FINALIZADA("La etapa que intenta evaluar ya fue finalizada "),
	MSG_ERROR_FIRMA_REALIZADA("Su evaluación ya fue realizada "),
	MSG_ERROR_DESTINATARIO_ATENDIDO("El documento fue atendido "),
	MSG_ERROR_DOCUMENTO_ATENDIDO_NO_PERMITIDO("El documento fue atendido, no es posible cambio de estado"),
	MSG_ERROR_SELECCIONAR_APROBADOR("Debe seleccionar aprobador(es)"),
	MSG_ERROR_DOCUMENTO_APROBADOR_UNICO("Solo tiene un aprobador en la etapa, puede modificar el actual"),
	MSG_ERROR_MOTIVO("Ya se creo el código del motivo"),
	MSG_DUPLICADO_REGISTROS("Ya se encuentra registrado el usuario al modelo"),
	MSG_ACTIVIDAD_MINIMA_NO_REGISTRADA("Debe registrar al menos "),
	MSG_ERROR_PERMISO_USUARIO("El usuario no tiene permisos para ver la Solución TI");
	
	
	private String strPv_valor;

	private MensajesRespuestaEstandarEnum(String strA_valor){
		this.strPv_valor = strA_valor;
	}
	
	public String getStrPv_valor() {
		return strPv_valor;
	}
	
}
