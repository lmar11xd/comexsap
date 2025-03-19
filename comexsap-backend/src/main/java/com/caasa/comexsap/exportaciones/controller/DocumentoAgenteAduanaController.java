package com.caasa.comexsap.exportaciones.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caasa.comexsap.exportaciones.model.response.ResponseDocumentoAgenteAduana;
import com.caasa.comexsap.exportaciones.model.service.DocumentoAgenteAduanaService;
import com.caasa.comexsap.exportaciones.model.to.RequestDocumentoAgenteAduanaTO;
import com.caasa.comexsap.exportaciones.model.to.RequestTO;
import com.caasa.comexsap.exportaciones.util.Constantes;

@RestController
@RequestMapping("/api/documentoAgenteAduanaController")
public class DocumentoAgenteAduanaController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private DocumentoAgenteAduanaService documentoAgenteAduanaService;
	
	@PostMapping("/guardarDocumentoAgenteAduana")
	public ResponseEntity<ResponseDocumentoAgenteAduana> guardarDocumentoAgenteAduana(
			@RequestBody RequestTO<RequestDocumentoAgenteAduanaTO> request) throws Exception {
		ResponseDocumentoAgenteAduana response = new ResponseDocumentoAgenteAduana();
		try {
			this.documentoAgenteAduanaService.registrarDocumentoAgenteAduana(request.getFormulario().getPosiciones(), request.getUsuario());
			response.setCod_rpta(Constantes.UNO);
			response.setRpta("Documento Agente Aduana guardado.");
		} catch (DataAccessException e) {
			response.setCod_rpta(Constantes.CERO);
			response.setRpta("¡Error al guardar Documento Agente Aduana!");
			logger.error("Error en guardarDocumentoAgenteAduana --> ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(response);
	}
	
	
}
