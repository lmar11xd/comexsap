package com.caasa.comexsap.exportaciones.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caasa.comexsap.exportaciones.model.service.TipoCambioService;
import com.caasa.comexsap.exportaciones.model.to.TipoCambioTO;

@RestController
@RequestMapping("/api/tipoCambioController")
public class TipoCambioController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TipoCambioService tipoCambioService;
	
	@GetMapping("/obtenerTipoCambioActual/{moneda}/{fecha}")
	public ResponseEntity<TipoCambioTO> obtenerTipoCambioActual(@PathVariable("moneda") String moneda, @PathVariable("fecha") String fecha) {
		try {
			TipoCambioTO tipoCambio = tipoCambioService.obtenerTipoCambioActual(moneda, fecha);
			return ResponseEntity.ok(tipoCambio);
		} catch (Exception e) {
			logger.error("Error al consultar servicio obtenerTipoCambioActual", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
