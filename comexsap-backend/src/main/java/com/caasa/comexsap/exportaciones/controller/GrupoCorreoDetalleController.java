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

import com.caasa.comexsap.exportaciones.model.response.GrupoCorreoResponse;
import com.caasa.comexsap.exportaciones.model.service.GrupoCorreoDetalleService;
import com.caasa.comexsap.exportaciones.model.to.FiltroGrupoCorreoRequestTO;
import com.caasa.comexsap.exportaciones.model.to.GrupoCorreoDetalleTO;
import com.caasa.comexsap.exportaciones.model.to.RequestTO;
import com.caasa.comexsap.exportaciones.util.Constantes;

@RestController
@RequestMapping("/api/grupoCorreoDetalleController")
public class GrupoCorreoDetalleController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private GrupoCorreoDetalleService grupoCorreoDetalleService;
	
	@PostMapping("/listarGrupoCorreoxFiltro")
	public ResponseEntity<List<GrupoCorreoDetalleTO>> listarGrupoCorreoxFiltro(@RequestBody RequestTO<FiltroGrupoCorreoRequestTO> request){
		try {
			List<GrupoCorreoDetalleTO> listarGrupoCorreoDetalle = this.grupoCorreoDetalleService.listarGrupoCorreoxFiltro(request.getFormulario());
			return ResponseEntity.ok(listarGrupoCorreoDetalle);
		}catch(Exception ex) {
			logger.error("Error al consultar GrupoCorreo listarGrupoCorreoxFiltro", ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/obtenerGrupoCorreo/{id}")
	public ResponseEntity<GrupoCorreoDetalleTO> obtenerGrupoCorreo(@PathVariable("id") int id) {
		GrupoCorreoDetalleTO GrupoCorreo = new GrupoCorreoDetalleTO();
		try {
			GrupoCorreo = this.grupoCorreoDetalleService.obtenerGrupoCorreoxId(id);
		} catch (Exception e) {
			logger.error("Error en el GrupoCorreo obtenerGrupoCorreo -->", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(GrupoCorreo);
	}
	
	@PostMapping("/guardarGrupoCorreo")
	public ResponseEntity<GrupoCorreoResponse> guardarGrupoCorreo(@RequestBody RequestTO<GrupoCorreoDetalleTO> request) throws Exception {
		GrupoCorreoResponse response = new GrupoCorreoResponse();
		try {
			int id = this.grupoCorreoDetalleService.registrarGrupoCorreo(request.getFormulario(), request.getUsuario());
			response.setCod_rpta(Constantes.UNO);
			response.setRpta("GrupoCorreo guardado --> " + id);
		} catch (DataAccessException e) {
			response.setCod_rpta(Constantes.CERO);
			response.setRpta("Error al guardar GrupoCorreo");
			logger.error("Error en el GrupoCorreo guardarGrupoCorreo -->", e);
		}
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/eliminarGrupoCorreo/{id}")
	public ResponseEntity<GrupoCorreoResponse> eliminarGrupoCorreo(@PathVariable("id") int id, @RequestParam(required = true) String usuario) {
		GrupoCorreoResponse response = new GrupoCorreoResponse();
		try {
			this.grupoCorreoDetalleService.eliminarGrupoCorreo(id, usuario);
			response.setCod_rpta(Constantes.UNO);
			response.setRpta("GrupoCorreo eliminado");
		} catch (Exception e) {
			response.setCod_rpta(Constantes.CERO);
			response.setRpta("Error al eliminar GrupoCorreo");
			logger.error("Error en el servicio eliminarGrupoCorreo -->", e);
		}
		return ResponseEntity.ok(response);
	}
}
