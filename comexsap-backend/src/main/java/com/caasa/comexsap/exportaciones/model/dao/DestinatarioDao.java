package com.caasa.comexsap.exportaciones.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.caasa.comexsap.exportaciones.model.to.DestinatarioTO;
import com.caasa.comexsap.exportaciones.model.to.InterlocutorTO;

public interface DestinatarioDao {

	public List<DestinatarioTO> listarDestinatarioxCliente(String codCliente);	
	public int buscarDestinatarioNuevoxDescripcion(String descripcionCliente); 
	public DestinatarioTO obtenerDestinatarioxDescripcion(@Param("descripcionDest")  String descripcionDest); 
	public List<InterlocutorTO> listarInterlocutorxCliente(String codigoCliente);

}
