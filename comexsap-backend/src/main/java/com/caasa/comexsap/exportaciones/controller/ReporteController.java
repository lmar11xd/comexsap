package com.caasa.comexsap.exportaciones.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caasa.comexsap.exportaciones.model.service.ReporteService;
import com.caasa.comexsap.exportaciones.model.to.FiltroReporteMaritimoRequest;
import com.caasa.comexsap.exportaciones.model.to.FiltroReporteTerrestreRequest;
import com.caasa.comexsap.exportaciones.model.to.ReporteMaritimoTO;
import com.caasa.comexsap.exportaciones.model.to.ReportePdfTO;
import com.caasa.comexsap.exportaciones.model.to.ReporteTerrestreTO;
import com.caasa.comexsap.exportaciones.model.to.RequestCertificadoTerrestreTO;
import com.caasa.comexsap.exportaciones.model.to.RequestReporteFacturaTO;
import com.caasa.comexsap.exportaciones.model.to.RequestTO;

@RestController
@RequestMapping("/api/reporteController")
public class ReporteController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ReporteService reporteService;

	@PostMapping("imprimirExportacionMaritimo")
	public ResponseEntity<Resource> imprimirExportacionMaritimo(
			@RequestBody RequestTO<RequestReporteFacturaTO> request) {
		try {
			ReportePdfTO reporte = this.reporteService.imprimirExportacionMaritimo(request.getFormulario());
			if(reporte != null) {
				InputStreamResource streamResource = new InputStreamResource(reporte.getStream());
				MediaType mediaType = MediaType.APPLICATION_PDF;
				
				return ResponseEntity.ok()
						.header("Content-Disposition", "inline; filename=\"" + reporte.getFilename() + "\"")
						.contentLength(reporte.getLength())
						.contentType(mediaType)
						.body(streamResource);
			} else {
				logger.error("error servicio imprimirExportacionMaritimo", "No se pudo obtener el reporte");
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
		} catch (Exception e) {
			logger.error("error servicio imprimirExportacionMaritimo", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("imprimirCertificadoTerrestre")
	public ResponseEntity<Resource> imprimirCertificadoTerrestre(
			@RequestBody RequestTO<RequestCertificadoTerrestreTO> request) {
		try {
			return this.reporteService.imprimirCertificadoTerrestre(request.getFormulario());
		} catch (Exception e) {
			logger.error("error servicio imprimirCertificacion", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("reporteMaritimoContenedores")
	public ResponseEntity<List<ReporteMaritimoTO>> reporteMaritimoContenedores(
			@RequestBody RequestTO<FiltroReporteMaritimoRequest> request) {
		List<ReporteMaritimoTO> reporte = null;
		try {
			reporte = this.reporteService.reporteMaritimoContenedores(request.getFormulario());
			return ResponseEntity.ok(reporte);
		} catch (Exception e) {
			logger.error("error servicio reporteMaritimoContenedores", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("reporteMaritimoCargaSuelta")
	public ResponseEntity<List<ReporteMaritimoTO>> reporteMaritimoCargaSuelta(
			@RequestBody RequestTO<FiltroReporteMaritimoRequest> request) {
		List<ReporteMaritimoTO> reporte = null;
		try {
			reporte = this.reporteService.reporteMaritimoCargaSuelta(request.getFormulario());
			return ResponseEntity.ok(reporte);
		} catch (Exception e) {
			logger.error("error servicio reporteMaritimoCargaSuelta", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("reporteTerrestre")
	public ResponseEntity<List<ReporteTerrestreTO>> reporteTerreste(
			@RequestBody RequestTO<FiltroReporteTerrestreRequest> request) {
		List<ReporteTerrestreTO> reporte = null;
		try {
			reporte = this.reporteService.reporteTerrestre(request.getFormulario());
			return ResponseEntity.ok(reporte);
		} catch (Exception e) {
			logger.error("error servicio reporteTerreste", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
