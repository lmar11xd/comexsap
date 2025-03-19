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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caasa.comexsap.exportaciones.model.service.MaestroService;
import com.caasa.comexsap.exportaciones.model.to.AlmacenTO;
import com.caasa.comexsap.exportaciones.model.to.CentroTO;

@RestController
@RequestMapping("/api/maestroController")
public class MaestroController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MaestroService maestroService;

	@GetMapping("/listarAlmacen/{idCentro}")
	public ResponseEntity<List<AlmacenTO>> listarAlmacen(@PathVariable("idCentro") int idCentro) {
		List<AlmacenTO> listaAlmacen = null;
		try {
			listaAlmacen = maestroService.listarAlmacen(idCentro);
			return ResponseEntity.ok(listaAlmacen);
		} catch (Exception e) {
			logger.error("Error en el servicio listarAlmacen", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping(value = { "/listarCentro", "/listarCentro/{buscar}" })
	public ResponseEntity<List<CentroTO>> listarCentro(@PathVariable(value = "buscar", required = false) String buscar) {
		List<CentroTO> listaCentro= null;
		try {
			listaCentro = maestroService.listarCentro(buscar);
			return ResponseEntity.ok(listaCentro);
		} catch (DataAccessException e) {
			logger.error("Error en el servicio listarCentro", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
