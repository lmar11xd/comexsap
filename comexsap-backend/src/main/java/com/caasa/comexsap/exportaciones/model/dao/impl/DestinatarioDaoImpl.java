package com.caasa.comexsap.exportaciones.model.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.caasa.comexsap.exportaciones.model.dao.DestinatarioDao;
import com.caasa.comexsap.exportaciones.model.persistence.extended.DestinatarioExtendedMapper;
import com.caasa.comexsap.exportaciones.model.to.DestinatarioTO;
import com.caasa.comexsap.exportaciones.model.to.InterlocutorTO;

@Repository("DestinatarioDao")
public class DestinatarioDaoImpl implements DestinatarioDao{

	@Autowired
	private DestinatarioExtendedMapper destinatarioExtendedMapper;

	@Override
	public List<DestinatarioTO> listarDestinatarioxCliente(String codCliente) {
		return destinatarioExtendedMapper.listarDestinatarioxCliente(codCliente);

	}

	@Override
	public int buscarDestinatarioNuevoxDescripcion(String descripcionCliente) {
		return destinatarioExtendedMapper.buscarDestinatarioNuevoxDescripcion(descripcionCliente);
	}

	@Override
	public DestinatarioTO obtenerDestinatarioxDescripcion(String descripcionDest) {
		return destinatarioExtendedMapper.obtenerDestinatarioxDescripcion(descripcionDest);
	}

	@Override
	public List<InterlocutorTO> listarInterlocutorxCliente(String codigoCliente) {
		return this.destinatarioExtendedMapper.listarInterlocutorxCliente(codigoCliente);
	}

}
