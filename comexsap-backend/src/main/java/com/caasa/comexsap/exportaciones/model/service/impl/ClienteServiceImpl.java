package com.caasa.comexsap.exportaciones.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caasa.comexsap.exportaciones.model.dao.ClienteDao;
import com.caasa.comexsap.exportaciones.model.service.ClienteService;
import com.caasa.comexsap.exportaciones.model.to.ClienteDatosVentaTO;
import com.caasa.comexsap.exportaciones.model.to.ClienteTO;
import com.caasa.comexsap.exportaciones.model.to.InfoClienteTO;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteDao clienteDao;	

	@Override
	public List<ClienteTO> listarClientexNombre(String cliente) {
		return clienteDao.listarClientexNombre(cliente);
	}

	@Override
	public int buscarClienteNuevoxDescripcion(String descripcionCliente) {
		return clienteDao.buscarClienteNuevoxDescripcion(descripcionCliente);
	}

	@Override
	public InfoClienteTO obtenerInfoClientexCodigo(String codigo) {
		return clienteDao.obtenerInfoClientexCodigo(codigo);
	}

	@Override
	public ClienteDatosVentaTO obtenerClienteDatosVentaxCodigo(String codigo) {
		return clienteDao.obtenerClienteDatosVentaxCodigo(codigo);
	}
		
}