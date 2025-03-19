package com.caasa.comexsap.exportaciones.controller;

import java.text.ParseException;
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
import org.springframework.web.bind.annotation.RestController;
import com.caasa.comexsap.exportaciones.api.to.ResultadoPrecioMaterialTO;
import com.caasa.comexsap.exportaciones.model.service.ProductoService;
import com.caasa.comexsap.exportaciones.model.to.PrecioMaterialRequestTO;
import com.caasa.comexsap.exportaciones.model.to.ProductoTO;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/api/productoController")
public class ProductoController {
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ProductoService productoService;
		
	@GetMapping(value = {"/listarProductoxNombre", "/listarProductoxNombre/{buscar}"})
	public ResponseEntity<List<ProductoTO>> listarProductoxNombre(@PathVariable(value = "buscar", required = false) String buscar){
		try {
			List<ProductoTO> listarProductoxNombre = productoService.listarProductoxNombre(buscar);
			return ResponseEntity.ok(listarProductoxNombre);
		} catch(Exception ex) {
			logger.error("Error al consultar servicio listarProductoxNombre", ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping(value = "/obtenerProductoxCodigo/{codigo}")
	public ResponseEntity<ProductoTO> obtenerProductoxCodigo(@PathVariable(value = "codigo") String codigo){
		try {
			ProductoTO producto = productoService.obtenerProductoxCodigo(codigo);
			return ResponseEntity.ok(producto);
		} catch(Exception ex) {
			logger.error("Error al consultar servicio listarProductoxNombre", ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/obtenerPrecioMaterial")
	public ResponseEntity<ResultadoPrecioMaterialTO> obtenerPrecioMaterial(@RequestBody PrecioMaterialRequestTO request) throws JsonProcessingException, ParseException{
		try {
			List<ResultadoPrecioMaterialTO> precioMaterialTO = productoService.obtenerPrecioMaterial(request);
			if (precioMaterialTO != null && precioMaterialTO.size() > 0) {
				return ResponseEntity.ok(precioMaterialTO.get(0));
			}
		} catch (Exception ex) {
			logger.error("Error al consultar servicio obtenerPrecioMaterial", ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}	
		return null;
	}
	
}
