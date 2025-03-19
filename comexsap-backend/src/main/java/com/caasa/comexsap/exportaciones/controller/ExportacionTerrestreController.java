package com.caasa.comexsap.exportaciones.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caasa.comexsap.exportaciones.model.response.ExportacionTerrestreResponse;
import com.caasa.comexsap.exportaciones.model.service.ExportacionTerrestreService;
import com.caasa.comexsap.exportaciones.model.to.ExportacionTerrestrePosicionTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionTerrestreTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroExportacionTerrestreRequestTO;
import com.caasa.comexsap.exportaciones.model.to.RequestExportacionTerrestreTO;
import com.caasa.comexsap.exportaciones.model.to.RequestTO;
import com.caasa.comexsap.exportaciones.util.Constantes;

@RestController
@RequestMapping("/api/exportacionTerrestreController")
public class ExportacionTerrestreController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ExportacionTerrestreService exportacionTerrestreService;
	
	@PostMapping("/listarExportacionTerrestrexFiltro")
	public ResponseEntity<List<ExportacionTerrestreTO>> listarExportacionTerrestrexFiltro(
			@RequestBody RequestTO<FiltroExportacionTerrestreRequestTO> request) {
		List<ExportacionTerrestreTO> lista = null;
		try {
			lista = this.exportacionTerrestreService.listarExportacionTerrestrexFiltro(request.getFormulario());
		} catch (Exception e) {
			logger.error("Error en el servicio listarExportacionTerrestrexFiltro -->", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/obtenerExportacionTerrestre/{idExportacion}")
	public ResponseEntity<ExportacionTerrestreResponse> obtenerExportacionTerrestre(
			@PathVariable("idExportacion") int id) {
		ExportacionTerrestreResponse response = new ExportacionTerrestreResponse();
		try {
			ExportacionTerrestreTO exportacion = this.exportacionTerrestreService.obtenerExportacionTerrestrexId(id);
			List<ExportacionTerrestrePosicionTO> posiciones = this.exportacionTerrestreService.listarPosicionxIdExportacion(id);
			response.setExportacion(exportacion);
			response.setPosiciones(posiciones);
			response.setCod_rpta(Constantes.UNO);
			response.setRpta("Posiciones: " + posiciones.size());
		} catch (Exception e) {
			response.setCod_rpta(Constantes.CERO);
			response.setRpta("Error en el servicio obtenerExportacionTerrestre");
			logger.error("Error en el servicio obtenerExportacionTerrestre -->", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/guardarExportacionTerrestre")
	public ResponseEntity<ExportacionTerrestreResponse> guardarExportacionTerrestre(
			@RequestBody RequestTO<RequestExportacionTerrestreTO> request) throws Exception {
		ExportacionTerrestreResponse response = new ExportacionTerrestreResponse();
		
		try {
			ExportacionTerrestreTO cabecera = request.getFormulario().getExportacion();
			List<ExportacionTerrestrePosicionTO> posiciones = request.getFormulario().getPosiciones();
			int id = this.exportacionTerrestreService.registrarExportacionTerretre(cabecera, posiciones, request.getUsuario());
			response.setCod_rpta(Constantes.UNO);
			response.setRpta("Exportacion Terrestre guardado correctamente --> " + id);
		} catch (DataAccessException e) {
			response.setCod_rpta(Constantes.CERO);
			response.setRpta("¡Error al guardar Exportacion Terrestre!");
			logger.error("Error en guardarExportacionTerrestre --> ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		return ResponseEntity.ok(response);
	}
}
