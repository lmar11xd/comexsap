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
import com.caasa.comexsap.exportaciones.model.service.DestinatarioService;
import com.caasa.comexsap.exportaciones.model.to.DestinatarioTO;

@RestController
@RequestMapping("/api/destinatarioController")
public class DestinatarioController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private DestinatarioService destinatarioService;

	@GetMapping("/listarDestinatarioxCliente/{codigoCliente}")
	public ResponseEntity<List<DestinatarioTO>> listarDestinatarioxCliente(@PathVariable("codigoCliente") String codigoCliente) {
		try {
			List<DestinatarioTO> listarDestinatarioxCliente = destinatarioService.listarDestinatarioxCliente(codigoCliente);
			return ResponseEntity.ok(listarDestinatarioxCliente);
		} catch (Exception ex) {
			logger.error("Error en el servicio listarDestinatarioxCliente", ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
