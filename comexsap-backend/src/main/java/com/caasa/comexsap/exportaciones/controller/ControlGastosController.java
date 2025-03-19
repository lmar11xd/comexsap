package com.caasa.comexsap.exportaciones.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caasa.comexsap.exportaciones.model.service.ControlGastosService;
import com.caasa.comexsap.exportaciones.model.to.ControlGastosResponse;
import com.caasa.comexsap.exportaciones.model.to.DocumentoControlGastoTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroControlGastosTO;
import com.caasa.comexsap.exportaciones.model.to.RequestControlGastosTO;
import com.caasa.comexsap.exportaciones.model.to.RequestTO;
import com.caasa.comexsap.exportaciones.util.Constantes;

@RestController
@RequestMapping("/api/controlGastosController")
public class ControlGastosController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ControlGastosService controlGastosService;
	
	@PostMapping("/listarDocumentosxFiltro")
	public ResponseEntity<List<DocumentoControlGastoTO>> listarDocumentosxFiltro(
			@RequestBody RequestTO<FiltroControlGastosTO> request) {
		List<DocumentoControlGastoTO> listaExportacionMaritimo = null;
		try {
			listaExportacionMaritimo = controlGastosService.listarDocumentosxFiltro(request.getFormulario());
		} catch (Exception e) {
			logger.error("Error en el servicio listarDocumentosxFiltro -->", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(listaExportacionMaritimo);
	}
	
	@PostMapping("/guardarControlGastos")
	public ResponseEntity<ControlGastosResponse> guardarControlGastos(@RequestBody RequestTO<RequestControlGastosTO> request) {
		ControlGastosResponse response = new ControlGastosResponse();
		try {
			controlGastosService.guardarControlGastos(request.getFormulario(), request.getUsuario());
			response.setCod_rpta(Constantes.UNO);
			response.setRpta("¡Control de gastos registrado!");
		} catch (Exception e) {
			response.setCod_rpta(Constantes.CERO);
			response.setRpta("¡Error al guardar guardarControlGastos!");
			logger.error("Error Guardar Control de Gastos --> ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(response);
	}
	
}
