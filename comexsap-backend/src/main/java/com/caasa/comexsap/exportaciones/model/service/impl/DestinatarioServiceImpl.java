package com.caasa.comexsap.exportaciones.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.caasa.comexsap.exportaciones.model.dao.DestinatarioDao;
import com.caasa.comexsap.exportaciones.model.service.DestinatarioService;
import com.caasa.comexsap.exportaciones.model.to.DestinatarioTO;

@Service
public class DestinatarioServiceImpl implements DestinatarioService{
	
	@Autowired
	private DestinatarioDao destinatarioDao;	

	@Override
	public List<DestinatarioTO> listarDestinatarioxCliente(String codCliente) {
		return destinatarioDao.listarDestinatarioxCliente(codCliente);
	}

	@Override
	public int buscarDestinatarioNuevoxDescripcion(String descripcionCliente) {
		return destinatarioDao.buscarDestinatarioNuevoxDescripcion(descripcionCliente);
	}
		
}