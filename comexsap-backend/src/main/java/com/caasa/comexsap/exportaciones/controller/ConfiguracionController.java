package com.caasa.comexsap.exportaciones.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import com.caasa.comexsap.exportaciones.enums.AplicacionEnum;
import com.caasa.comexsap.exportaciones.model.response.ParametroResponse;
import com.caasa.comexsap.exportaciones.model.service.ConfiguracionService;
import com.caasa.comexsap.exportaciones.model.to.DominioTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroConfiguracionRequestTO;
import com.caasa.comexsap.exportaciones.model.to.ParametroTO;
import com.caasa.comexsap.exportaciones.model.to.RequestTO;
import com.caasa.comexsap.exportaciones.util.Constantes;

@RestController
@RequestMapping("/api/configuracionController")
public class ConfiguracionController {

	private Logger logger = LogManager.getLogger(getClass());

	@Autowired
	private ConfiguracionService configuracionService;

	@GetMapping("/listarParametroxDominio/{codigoDominio}")
	public ResponseEntity<List<ParametroTO>> listarParametroxDominio(
			@PathVariable("codigoDominio") String codigoDominio) {
		try {
			List<ParametroTO> listaParametros = configuracionService
					.listarParametroxDominio(AplicacionEnum.APLICACION.getValue(), codigoDominio);
			if (listaParametros == null || (listaParametros != null && listaParametros.size() == 0)) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(listaParametros);
		} catch (Exception ex) {
			logger.error("Error al consultar servicio listarParametroxDominio", ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
	
	@GetMapping("/listarParametroxCodigoSap/{codigoDominio}/{codigoSap}")
	public ResponseEntity<List<ParametroTO>> listarParametroxCodigoSap(
			@PathVariable("codigoDominio") String codigoDominio, @PathVariable("codigoSap") String codigoSap) {
		try {
			List<ParametroTO> listaParametros = configuracionService
					.listarParametroxCodigoSap(AplicacionEnum.APLICACION.getValue(), codigoDominio, codigoSap);
			if (listaParametros == null || (listaParametros != null && listaParametros.size() == 0)) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(listaParametros);
		} catch (Exception ex) {
			logger.error("Error al consultar servicio listarParametroxCodigoSap", ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@GetMapping(value = { "buscarParametroxDominio/{codigoDominio}",
			"/buscarParametroxDominio/{codigoDominio}/{buscar}" })
	public ResponseEntity<List<ParametroTO>> buscarParametroxDominio(
			@PathVariable("codigoDominio") String codigoDominio,
			@PathVariable(value = "buscar", required = false) String buscar) {

		try {
			List<ParametroTO> listaParametros = configuracionService
					.buscarParametroxDominio(AplicacionEnum.APLICACION.getValue(), codigoDominio, buscar);

			if (listaParametros == null || (listaParametros != null && listaParametros.size() == 0)) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(listaParametros);
		} catch (Exception ex) {
			logger.error("Error al consultar servicio buscarParametroxDominio", ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@GetMapping("/obtenerParametroxId/{id}")
	public ResponseEntity<ParametroTO> obtenerParametroxId(@PathVariable("id") Integer id) {
		try {
			ParametroTO parametro = configuracionService.obtenerParametroxId(id);

			if (parametro == null) {
				return ResponseEntity.notFound().build();
			}

			return ResponseEntity.ok(parametro);
		} catch (Exception ex) {
			logger.error("Error al consultar servicio obtenerParametroxId", ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@GetMapping("/listarDominio")
	public ResponseEntity<List<DominioTO>> listarDominio() {
		try {
			List<DominioTO> listaDominio = configuracionService.listarDominio(AplicacionEnum.APLICACION.getValue());
			if (listaDominio == null || (listaDominio != null && listaDominio.size() == 0)) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(listaDominio);
		} catch (Exception ex) {
			logger.error("Error al consultar servicio listarDominio", ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@PostMapping("/listarParametroxFiltro")
	public ResponseEntity<List<ParametroTO>> listarParametroxFiltro(@RequestBody RequestTO<FiltroConfiguracionRequestTO> request) {
		try {
			List<ParametroTO> listaParametros = configuracionService.listarParametroxFiltro(request.getFormulario());
			return ResponseEntity.ok(listaParametros);
		} catch (Exception ex) {
			logger.error("Error al consultar servicio listarParametroxFiltro", ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/guardarParametro")
	public ResponseEntity<ParametroResponse> guardarParametro(@RequestBody RequestTO<ParametroTO> request) throws Exception {
		ParametroResponse response = new ParametroResponse();
		try {
			int id = this.configuracionService.registrarParametro(request.getFormulario(), request.getUsuario());
			if(id == -1) {
				response.setCod_rpta(Constantes.CERO);
				response.setRpta("Código Parámetro ya existe para el Maestro seleccionado.");
			} else {
				response.setCod_rpta(Constantes.UNO);
				response.setRpta("Parametro guardado --> " + id);
			}
			return ResponseEntity.ok(response);
		} catch (DataAccessException e) {
			response.setCod_rpta(Constantes.CERO);
			response.setRpta("Error al guardar Parametro");
			logger.error("Error en el servicio guardarParametro -->", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/eliminarParametro/{id}")
	public ResponseEntity<ParametroResponse> eliminarParametro(@RequestParam(required = true) String usuario,
			@PathVariable("id") Integer id) {
		ParametroResponse response = new ParametroResponse();
		try {
			ParametroTO parametro = configuracionService.obtenerParametroxId(id);

			if (parametro == null) {
				return ResponseEntity.notFound().build();
			}

			configuracionService.borrarParametro(parametro, usuario);
			response.setCod_rpta(Constantes.UNO);
			response.setRpta("Parámetro eliminado");

			return ResponseEntity.ok(response);
		} catch (Exception ex) {
			response.setCod_rpta(Constantes.CERO);
			response.setRpta("Error al consultar servicio borrarParametro");
			logger.error("Error al consultar servicio borrarParametro", ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

}
