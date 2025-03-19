package com.caasa.comexsap.exportaciones.model.dao;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.ExportacionTerrestrePosicionTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionTerrestreTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroExportacionTerrestreRequestTO;

public interface ExportacionTerrestreDao {
	public List<ExportacionTerrestreTO> listarExportacionTerrestrexFiltro(FiltroExportacionTerrestreRequestTO request);
	public ExportacionTerrestreTO obtenerExportacionTerrestrexId(int idDocumento);
	public List<ExportacionTerrestrePosicionTO> listarPosicionxIdExportacion(int idExportacion);
	public int registrarExportacionTerretre(ExportacionTerrestreTO cabecera, List<ExportacionTerrestrePosicionTO> posiciones, String usuario);
}
