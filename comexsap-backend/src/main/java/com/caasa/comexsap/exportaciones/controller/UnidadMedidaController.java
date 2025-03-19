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

import com.caasa.comexsap.exportaciones.model.service.UnidadMedidaService;
import com.caasa.comexsap.exportaciones.model.to.FamiliaConversionTO;
import com.caasa.comexsap.exportaciones.model.to.UnidadMedidaTO;

@RestController
@RequestMapping("/api/unidadMedidaController")
public class UnidadMedidaController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UnidadMedidaService unidadMedidaService;

	@GetMapping("/listarUnidadMedidaxNombre/{codigoProducto}")
	public ResponseEntity<List<UnidadMedidaTO>> listarUnidadMedidaxNombre(
			@PathVariable("codigoProducto") String codigoProducto) {

		try {
			List<UnidadMedidaTO> listarUnidadMedidaxNombre = unidadMedidaService
					.listarUnidadMedidaxNombre(codigoProducto);

			return ResponseEntity.ok(listarUnidadMedidaxNombre);
		} catch (Exception ex) {
			logger.error("Error al consultar servicio listarUnidadMedidaxNombre", ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/obtenerUnidadMedidaxProductoUnidad/{codigoProducto}/{unidadMedida}")
	public ResponseEntity<UnidadMedidaTO> obtenerUnidadMedidaxProductoUnidad(
			@PathVariable("codigoProducto") String codigoProducto, @PathVariable("unidadMedida") String unidadMedida) {
		try {
			UnidadMedidaTO unidadMedidaTo = unidadMedidaService.obtenerUnidadMedidaxProductoUnidad(codigoProducto,
					unidadMedida);

			return ResponseEntity.ok(unidadMedidaTo);
		} catch (Exception ex) {
			logger.error("Error al consultar servicio obtenerUnidadMedidaxProductoUnidad", ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/listarFamiliasConversion")
	public ResponseEntity<List<FamiliaConversionTO>> listarFamiliasConversion() {

		try {
			List<FamiliaConversionTO> lista = unidadMedidaService.listarFamiliasConversion();
			return ResponseEntity.ok(lista);
		} catch (Exception ex) {
			logger.error("Error al consultar servicio listarFamiliasConversion", ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
