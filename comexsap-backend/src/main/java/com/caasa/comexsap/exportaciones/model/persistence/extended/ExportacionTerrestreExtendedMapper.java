package com.caasa.comexsap.exportaciones.model.persistence.extended;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.caasa.comexsap.exportaciones.model.to.ExportacionTerrestrePosicionTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionTerrestreTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroExportacionTerrestreRequestTO;

public interface ExportacionTerrestreExtendedMapper {
	public List<ExportacionTerrestreTO> listarExportacionTerrestrexFiltro(FiltroExportacionTerrestreRequestTO request);
	public ExportacionTerrestreTO obtenerExportacionTerrestrexId(@Param("idDocumento") int idDocumento);
	public ExportacionTerrestreTO obtenerExportacionTerrestrexFactura(@Param("idFactura") int idFactura, @Param("idEstadoDocumento") int idEstadoDocumento);
	public List<ExportacionTerrestrePosicionTO> listarPosicionxIdExportacion(@Param("idExportacion") int idExportacion);
}
