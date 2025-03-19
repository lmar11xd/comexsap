package com.caasa.comexsap.exportaciones.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sap.api")
public class SapApiProperties {

	private String jwtToken;
	private String connectionTimeout;
	private String receiveTimeout;

	private String sap_cuentacte_api_tableDisplayController_readTable;
	private String sap_pedidos_api_pedidosController_obtenerDescripcionBloqueo;
	private String sap_pedidos_api_pedidosController_obtenerFechaFactura;
	private String sap_get_precio_material;

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(String connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public String getReceiveTimeout() {
		return receiveTimeout;
	}

	public void setReceiveTimeout(String receiveTimeout) {
		this.receiveTimeout = receiveTimeout;
	}

	public String getSap_cuentacte_api_tableDisplayController_readTable() {
		return sap_cuentacte_api_tableDisplayController_readTable;
	}

	public void setSap_cuentacte_api_tableDisplayController_readTable(
			String sap_cuentacte_api_tableDisplayController_readTable) {
		this.sap_cuentacte_api_tableDisplayController_readTable = sap_cuentacte_api_tableDisplayController_readTable;
	}

	public String getSap_pedidos_api_pedidosController_obtenerDescripcionBloqueo() {
		return sap_pedidos_api_pedidosController_obtenerDescripcionBloqueo;
	}

	public void setSap_pedidos_api_pedidosController_obtenerDescripcionBloqueo(
			String sap_pedidos_api_pedidosController_obtenerDescripcionBloqueo) {
		this.sap_pedidos_api_pedidosController_obtenerDescripcionBloqueo = sap_pedidos_api_pedidosController_obtenerDescripcionBloqueo;
	}

	public String getSap_pedidos_api_pedidosController_obtenerFechaFactura() {
		return sap_pedidos_api_pedidosController_obtenerFechaFactura;
	}

	public void setSap_pedidos_api_pedidosController_obtenerFechaFactura(
			String sap_pedidos_api_pedidosController_obtenerFechaFactura) {
		this.sap_pedidos_api_pedidosController_obtenerFechaFactura = sap_pedidos_api_pedidosController_obtenerFechaFactura;
	}

	public String getSap_get_precio_material() {
		return sap_get_precio_material;
	}

	public void setSap_get_precio_material(String sap_get_precio_material) {
		this.sap_get_precio_material = sap_get_precio_material;
	}
	
	
}