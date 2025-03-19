package com.caasa.comexsap.exportaciones.model.persistence.extended;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.caasa.comexsap.exportaciones.model.to.DestinatarioTO;
import com.caasa.comexsap.exportaciones.model.to.InterlocutorTO;

public interface DestinatarioExtendedMapper {

	public List<DestinatarioTO> listarDestinatarioxCliente(@Param("codigoCliente") String codigoCliente);
	public int buscarDestinatarioxCodigoSap(@Param("codigoSap")  String codigoSap); 
	public DestinatarioTO obtenerDestinatarioxCodigoSap(@Param("codigoSap") String codigo); 
	public int buscarDestinatarioNuevoxDescripcion(@Param("descripcionDest")String descripcionDest); 
	public DestinatarioTO obtenerDestinatarioxDescripcion(@Param("descripcionDest")  String descripcionDest); 
	public List<InterlocutorTO> listarInterlocutorxCliente(@Param("codigoCliente") String codigoCliente);

}
