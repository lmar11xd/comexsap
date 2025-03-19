package com.caasa.comexsap.exportaciones.model.service;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.ClienteDatosVentaTO;
import com.caasa.comexsap.exportaciones.model.to.ClienteTO;
import com.caasa.comexsap.exportaciones.model.to.InfoClienteTO;

public interface ClienteService {
	
	public List<ClienteTO> listarClientexNombre(String cliente);	
	public int buscarClienteNuevoxDescripcion(String descripcionCliente);
	public InfoClienteTO obtenerInfoClientexCodigo(String codigo);
	public ClienteDatosVentaTO obtenerClienteDatosVentaxCodigo(String codigo);
}
