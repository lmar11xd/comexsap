package com.caasa.comexsap.exportaciones.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.caasa.comexsap.exportaciones.model.response.Response;
import com.caasa.comexsap.exportaciones.model.service.EtiquetaService;
import com.caasa.comexsap.exportaciones.model.to.EtiquetaTO;
import com.caasa.comexsap.exportaciones.model.to.RequestTO;
import com.caasa.comexsap.exportaciones.util.Constantes;

@RestController
@RequestMapping("/api/etiquetaController")
public class EtiquetaController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private EtiquetaService etiquetaService;
	
	@GetMapping(value = { "/listarEtiquetas", "/listarEtiquetas/{buscar}" })
	public ResponseEntity<List<EtiquetaTO>> listarEtiquetas(@PathVariable(value = "buscar", required = false) String buscar) {
		List<EtiquetaTO> lista = new ArrayList<>();
		try {
			lista = etiquetaService.listarEtiquetas(buscar);
		} catch (Exception e) {
			logger.error("Error en el servicio listarEtiquetasxFiltro -->", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping("/guardarEtiqueta")
	public ResponseEntity<?> guardarEtiqueta(@RequestBody RequestTO<EtiquetaTO> request) {
		Response response = new Response();
		try {
			etiquetaService.guardarEtiqueta(request.getFormulario(), request.getUsuario());
			response.setCod_rpta(Constantes.UNO);
			response.setRpta("Etiqueta guardada");
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.setCod_rpta(Constantes.CERO);
			response.setRpta(e.getMessage());
			logger.error("Error en el servicio guardarEtiqueta -->", e);
		}
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/eliminarEtiqueta/{id}")
	public ResponseEntity<?> eliminarEtiqueta(@PathVariable("id") int id, @RequestParam(required = true) String usuario) {
		Response response = new Response();
		try {
			etiquetaService.eliminarEtiqueta(id, usuario);
			response.setCod_rpta(Constantes.UNO);
			response.setRpta("Etiqueta eliminada");
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.setCod_rpta(Constantes.CERO);
			response.setRpta(e.getMessage());
			logger.error("Error en el servicio eliminarEtiqueta -->", e);
		}
		return ResponseEntity.ok(response);
	}
}
