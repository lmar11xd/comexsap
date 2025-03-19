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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.caasa.comexsap.exportaciones.model.response.ServicioResponse;
import com.caasa.comexsap.exportaciones.model.service.ServicioService;
import com.caasa.comexsap.exportaciones.model.to.FiltroServicioRequestTO;
import com.caasa.comexsap.exportaciones.model.to.RequestTO;
import com.caasa.comexsap.exportaciones.model.to.ServicioTO;
import com.caasa.comexsap.exportaciones.util.Constantes;

@RestController
@RequestMapping("/api/servicioController")
public class ServicioController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ServicioService servicioService;

	@PostMapping("/listarServicioxFiltro")
	public ResponseEntity<List<ServicioTO>> listarServicioxFiltro(@RequestBody RequestTO<FiltroServicioRequestTO> request) {
		List<ServicioTO> lista = null;
		try {
			lista = this.servicioService.listarServicioxFiltro(request.getFormulario());
		} catch (Exception e) {
			logger.error("Error en el servicio listarServicioxFiltro -->", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/obtenerServicio/{idServicio}")
	public ResponseEntity<ServicioTO> obtenerServicio(@PathVariable("idServicio") int id) {
		ServicioTO servicio = new ServicioTO();
		try {
			servicio = this.servicioService.obtenerServicioxId(id);
		} catch (Exception e) {
			logger.error("Error en el servicio obtenerServicio -->", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(servicio);
	}

	@PostMapping("/guardarServicio")
	public ResponseEntity<ServicioResponse> guardarServicio(@RequestBody RequestTO<ServicioTO> request) throws Exception {
		ServicioResponse response = new ServicioResponse();
		try {
			int id = this.servicioService.registrarServicio(request.getFormulario(), request.getUsuario());
			response.setCod_rpta(Constantes.UNO);
			response.setRpta("Servicio guardado --> " + id);
			return ResponseEntity.ok(response);
		} catch (DataAccessException e) {
			response.setCod_rpta(Constantes.CERO);
			response.setRpta("Error al guardar Servicio");
			logger.error("Error en el servicio guardarServicio -->", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/eliminarServicio/{id}")
	public ResponseEntity<ServicioResponse> eliminarServicio(@PathVariable("id") int id, @RequestParam(required = true) String usuario) {
		ServicioResponse response = new ServicioResponse();
		try {
			this.servicioService.eliminarServicio(id, usuario);
			response.setCod_rpta(Constantes.UNO);
			response.setRpta("Servicio eliminado");
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.setCod_rpta(Constantes.CERO);
			response.setRpta("Error al eliminar Servicio");
			logger.error("Error en el servicio eliminarServicio -->", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
