package com.caasa.comexsap.exportaciones.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sap.soap")
public class SapSoapProperties {

	private String username;
	private String password;
	private String connectionTimeout;
	private String receiveTimeout;

	private String endpoint_crear_pedido;
	private String endpoint_modificar_pedido;
	private String endpoint_consultar_stock;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getEndpoint_crear_pedido() {
		return endpoint_crear_pedido;
	}

	public void setEndpoint_crear_pedido(String endpoint_crear_pedido) {
		this.endpoint_crear_pedido = endpoint_crear_pedido;
	}

	public String getEndpoint_modificar_pedido() {
		return endpoint_modificar_pedido;
	}

	public void setEndpoint_modificar_pedido(String endpoint_modificar_pedido) {
		this.endpoint_modificar_pedido = endpoint_modificar_pedido;
	}
	
	public String getEndpoint_consultar_stock() {
		return endpoint_consultar_stock;
	}

	public void setEndpoint_consultar_stock(String endpoint_consultar_stock) {
		this.endpoint_consultar_stock = endpoint_consultar_stock;
	}

}
