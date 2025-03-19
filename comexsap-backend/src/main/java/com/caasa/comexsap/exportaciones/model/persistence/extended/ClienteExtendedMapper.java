package com.caasa.comexsap.exportaciones.model.persistence.extended;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.caasa.comexsap.exportaciones.model.to.ClienteDatosVentaTO;
import com.caasa.comexsap.exportaciones.model.to.ClienteTO;
import com.caasa.comexsap.exportaciones.model.to.InfoClienteTO;

public interface ClienteExtendedMapper {

	public List<ClienteTO> listarClientexNombre(@Param("cliente") String cliente);
	public ClienteTO obtenerClientexCodigoSap(@Param("codigoSap") String codigo);
	public ClienteTO obtenerClienteNuevoxId(@Param("codigoCliente") String codigo); 
	public int buscarClientexCodigoSap(@Param("codigoClienteSap")  String codigo); 
	public int buscarClienteNuevoxDescripcion(@Param("descripcionCliente")  String descripcionCliente);
	public ClienteTO obtenerClienteNuevoxDescripcion(@Param("descripcionCliente")  String descripcionCliente);
	public ClienteTO obtenerClienteSapxCodigoSap(@Param("codigoSap") String codigo);
	public InfoClienteTO obtenerInfoClientexCodigo(@Param("codigoSap") String codigo);
	public ClienteDatosVentaTO obtenerClienteDatosVentaxCodigo(@Param("codigoSap") String codigo);
}