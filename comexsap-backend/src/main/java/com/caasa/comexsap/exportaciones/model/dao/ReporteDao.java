package com.caasa.comexsap.exportaciones.model.dao;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import com.caasa.comexsap.exportaciones.model.to.FiltroReporteMaritimoRequest;
import com.caasa.comexsap.exportaciones.model.to.FiltroReporteTerrestreRequest;
import com.caasa.comexsap.exportaciones.model.to.ParametroTO;
import com.caasa.comexsap.exportaciones.model.to.ReporteMaritimoTO;
import com.caasa.comexsap.exportaciones.model.to.ReportePdfTO;
import com.caasa.comexsap.exportaciones.model.to.ReporteTerrestreTO;
import com.caasa.comexsap.exportaciones.model.to.RequestCertificadoTerrestreTO;
import com.caasa.comexsap.exportaciones.model.to.RequestReporteFacturaComercialTO;
import com.caasa.comexsap.exportaciones.model.to.RequestReporteFacturaTO;

public interface ReporteDao {

	public ParametroTO obtenerParametroxId(int id);
	
	public ReportePdfTO imprimirExportacionMaritimo(RequestReporteFacturaTO request) throws Exception;
	public ResponseEntity<Resource> imprimirFacturaComercial(RequestReporteFacturaComercialTO request) throws Exception;
	public ResponseEntity<Resource> imprimirCertificadoTerrestre(RequestCertificadoTerrestreTO request) throws Exception;

	public List<ReporteMaritimoTO> reporteMaritimoContenedores(FiltroReporteMaritimoRequest filtro);
	public List<ReporteMaritimoTO> reporteMaritimoCargaSuelta(FiltroReporteMaritimoRequest filtro);
	public List<ReporteTerrestreTO> reporteTerrestre(FiltroReporteTerrestreRequest filtro);
	public float obtenerPesoPackingList(int idExportacionPedido);
}
