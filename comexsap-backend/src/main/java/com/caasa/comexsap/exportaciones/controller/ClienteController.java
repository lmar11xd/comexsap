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
import com.caasa.comexsap.exportaciones.model.service.ClienteService;
import com.caasa.comexsap.exportaciones.model.to.ClienteTO;
import com.caasa.comexsap.exportaciones.model.to.InfoClienteTO;

@RestController
@RequestMapping("/api/clienteController")
public class ClienteController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ClienteService clienteService;
		
	@GetMapping(value = {"/listarClientexNombre", "/listarClientexNombre/{cliente}"})
	public ResponseEntity<List<ClienteTO>> listarClientexNombre(@PathVariable(value = "cliente", required = false) String cliente){

		try {
			List<ClienteTO> listarClientexNombre = clienteService.listarClientexNombre(cliente);
			if (listarClientexNombre == null || (listarClientexNombre != null && listarClientexNombre.size()==0)) {
				return ResponseEntity.notFound().build();			
			}
			return ResponseEntity.ok(listarClientexNombre);
		}catch(Exception ex) {
			logger.error("Error al consultar servicio listarClientexNombre", ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/obtenerInfoClientexCodigo/{codigo}")
	public ResponseEntity<InfoClienteTO> obtenerInfoClientexCodigo(@PathVariable(value = "codigo") String codigo){

		try {
			InfoClienteTO info = clienteService.obtenerInfoClientexCodigo(codigo);
			return ResponseEntity.ok(info);
		}catch(Exception ex) {
			logger.error("Error al consultar servicio listarClientexNombre", ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
