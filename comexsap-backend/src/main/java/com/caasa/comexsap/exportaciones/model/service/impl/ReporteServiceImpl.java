package com.caasa.comexsap.exportaciones.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.caasa.comexsap.exportaciones.model.dao.ReporteDao;
import com.caasa.comexsap.exportaciones.model.service.ReporteService;
import com.caasa.comexsap.exportaciones.model.to.FiltroReporteMaritimoRequest;
import com.caasa.comexsap.exportaciones.model.to.FiltroReporteTerrestreRequest;
import com.caasa.comexsap.exportaciones.model.to.ReporteMaritimoTO;
import com.caasa.comexsap.exportaciones.model.to.ReportePdfTO;
import com.caasa.comexsap.exportaciones.model.to.ReporteTerrestreTO;
import com.caasa.comexsap.exportaciones.model.to.RequestCertificadoTerrestreTO;
import com.caasa.comexsap.exportaciones.model.to.RequestReporteFacturaComercialTO;
import com.caasa.comexsap.exportaciones.model.to.RequestReporteFacturaTO;

@Service
public class ReporteServiceImpl implements ReporteService{
	
	@Autowired
	private ReporteDao reporteDao;

	@Override
	public ReportePdfTO imprimirExportacionMaritimo(RequestReporteFacturaTO request) throws Exception {
		return this.reporteDao.imprimirExportacionMaritimo(request);
	}

	@Override
	public ResponseEntity<Resource> imprimirFacturaComercial(RequestReporteFacturaComercialTO request) throws Exception {
		return this.reporteDao.imprimirFacturaComercial(request);
	}

	@Override
	public List<ReporteMaritimoTO> reporteMaritimoContenedores(FiltroReporteMaritimoRequest filtro) {
		return this.reporteDao.reporteMaritimoContenedores(filtro);
	}

	@Override
	public List<ReporteMaritimoTO> reporteMaritimoCargaSuelta(FiltroReporteMaritimoRequest filtro) {
		return this.reporteDao.reporteMaritimoCargaSuelta(filtro);
	}

	@Override
	public List<ReporteTerrestreTO> reporteTerrestre(FiltroReporteTerrestreRequest filtro) {
		return this.reporteDao.reporteTerrestre(filtro);
	}

	@Override
	public ResponseEntity<Resource> imprimirCertificadoTerrestre(RequestCertificadoTerrestreTO request) throws Exception {
		return this.reporteDao.imprimirCertificadoTerrestre(request);
	}

}