package com.caasa.comexsap.exportaciones.model.persistence.extended;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.caasa.comexsap.exportaciones.model.to.FiltroReporteMaritimoRequest;
import com.caasa.comexsap.exportaciones.model.to.FiltroReporteTerrestreRequest;
import com.caasa.comexsap.exportaciones.model.to.ReporteMaritimoTO;
import com.caasa.comexsap.exportaciones.model.to.ReporteTerrestreTO;

public interface ReporteExtendedMapper {
	public List<ReporteMaritimoTO> reporteMaritimoContenedores(FiltroReporteMaritimoRequest filtro);
	public List<ReporteMaritimoTO> reporteMaritimoCargaSuelta(FiltroReporteMaritimoRequest filtro);
	public List<ReporteTerrestreTO> reporteTerrestre(FiltroReporteTerrestreRequest filtro);
	public float obtenerPesoPackingList(@Param("idExportacionPedido") int idExportacionPedido);
	public String obtenerCargoUsuario(@Param("codigoPersonal") String codigoPersonal);
}
