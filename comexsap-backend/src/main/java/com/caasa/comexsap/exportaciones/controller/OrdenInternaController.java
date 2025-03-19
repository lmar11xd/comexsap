package com.caasa.comexsap.exportaciones.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caasa.comexsap.exportaciones.model.service.OrdenInternaService;
import com.caasa.comexsap.exportaciones.model.to.FiltroOrdenInterna;
import com.caasa.comexsap.exportaciones.model.to.OrdenInternaTO;

@RestController
@RequestMapping("/api/ordenInternaController")
public class OrdenInternaController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private OrdenInternaService ordenInternaService;
	
	@PostMapping("/obtenerOrdenInternaxFiltro")
	public ResponseEntity<OrdenInternaTO> obtenerOrdenInternaxFiltro(@RequestBody FiltroOrdenInterna filtro) {
		try {
			OrdenInternaTO ordenInternaTO = ordenInternaService.obtenerOrdenInternaxFiltro(filtro);
			return ResponseEntity.ok(ordenInternaTO);
		} catch (Exception ex) {
			logger.error("Error al consultar servicio obtenerOrdenInternaxFiltro", ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/obtenerOrdenInterna/{id}")
	public ResponseEntity<OrdenInternaTO> obtenerOrdenInterna(@PathVariable(value = "id") int id) {
		try {
			OrdenInternaTO ordenInternaTO = ordenInternaService.obtenerOrdenInterna(id);
			return ResponseEntity.ok(ordenInternaTO);
		} catch (Exception ex) {
			logger.error("Error al consultar servicio obtenerOrdenInterna", ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
