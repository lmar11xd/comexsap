package com.caasa.comexsap.exportaciones.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caasa.comexsap.exportaciones.model.dao.ExportacionTerrestreDao;
import com.caasa.comexsap.exportaciones.model.service.ExportacionTerrestreService;
import com.caasa.comexsap.exportaciones.model.to.ExportacionTerrestrePosicionTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionTerrestreTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroExportacionTerrestreRequestTO;

@Service
public class ExportacionTerrestreServiceImpl implements ExportacionTerrestreService {

	@Autowired
	private ExportacionTerrestreDao exportacionTerrestreDao;
	
	@Override
	public List<ExportacionTerrestreTO> listarExportacionTerrestrexFiltro(FiltroExportacionTerrestreRequestTO request) {
		return exportacionTerrestreDao.listarExportacionTerrestrexFiltro(request);
	}
	
	@Override
	public ExportacionTerrestreTO obtenerExportacionTerrestrexId(int idDocumento) {
		return exportacionTerrestreDao.obtenerExportacionTerrestrexId(idDocumento);
	}

	@Override
	public List<ExportacionTerrestrePosicionTO> listarPosicionxIdExportacion(int idExportacion) {
		return exportacionTerrestreDao.listarPosicionxIdExportacion(idExportacion);
	}

	@Override
	public int registrarExportacionTerretre(ExportacionTerrestreTO cabecera,
			List<ExportacionTerrestrePosicionTO> posiciones, String usuario) {
		return exportacionTerrestreDao.registrarExportacionTerretre(cabecera, posiciones, usuario);
	}

}
