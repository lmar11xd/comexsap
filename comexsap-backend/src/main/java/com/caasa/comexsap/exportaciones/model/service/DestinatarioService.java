package com.caasa.comexsap.exportaciones.model.service;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.DestinatarioTO;


public interface DestinatarioService {
	
	public List<DestinatarioTO> listarDestinatarioxCliente(String codCliente);	
	public int buscarDestinatarioNuevoxDescripcion(String descripcionCliente); 

}
