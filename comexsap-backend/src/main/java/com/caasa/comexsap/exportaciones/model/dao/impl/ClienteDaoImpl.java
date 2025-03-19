package com.caasa.comexsap.exportaciones.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.caasa.comexsap.exportaciones.model.dao.ClienteDao;
import com.caasa.comexsap.exportaciones.model.persistence.extended.ClienteExtendedMapper;
import com.caasa.comexsap.exportaciones.model.to.ClienteDatosVentaTO;
import com.caasa.comexsap.exportaciones.model.to.ClienteTO;
import com.caasa.comexsap.exportaciones.model.to.InfoClienteTO;

@Repository("ClienteDao")
public class ClienteDaoImpl implements ClienteDao{

	@Autowired
	private ClienteExtendedMapper clienteExtendedMapper;

	@Override
	public List<ClienteTO> listarClientexNombre(String cliente) {
		return clienteExtendedMapper.listarClientexNombre(cliente);

	}

	@Override
	public int buscarClienteNuevoxDescripcion(String descripcionCliente) {
		return clienteExtendedMapper.buscarClienteNuevoxDescripcion(descripcionCliente);
	}

	@Override
	public InfoClienteTO obtenerInfoClientexCodigo(String codigo) {
		return clienteExtendedMapper.obtenerInfoClientexCodigo(codigo);
	}

	@Override
	public ClienteDatosVentaTO obtenerClienteDatosVentaxCodigo(String codigo) {
		return clienteExtendedMapper.obtenerClienteDatosVentaxCodigo(codigo);
	}

}
