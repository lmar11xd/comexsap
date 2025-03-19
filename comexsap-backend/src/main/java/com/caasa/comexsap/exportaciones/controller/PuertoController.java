package com.caasa.comexsap.exportaciones.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caasa.comexsap.exportaciones.model.service.PuertoService;
import com.caasa.comexsap.exportaciones.model.to.PuertoTO;

@RestController
@RequestMapping("/api/puertoController")
public class PuertoController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PuertoService puertoService;

	@GetMapping(value = { "/listarPuertoxNombre", "/listarPuertoxNombre/{buscar}" })
	public ResponseEntity<List<PuertoTO>> listarPuertoxNombre(
			@PathVariable(value = "buscar", required = false) String buscar) {
		try {
			List<PuertoTO> listarPuertoxNombre = puertoService.listarPuertoxNombre(buscar);
			return ResponseEntity.ok(listarPuertoxNombre);
		} catch (Exception ex) {
			logger.error("Error al consultar servicio listarPuertoxNombre", ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

}
