package com.caasa.comexsap.exportaciones.controller;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.caasa.comexsap.exportaciones.model.response.ArchivoResponse;
import com.caasa.comexsap.exportaciones.model.service.ArchivoService;
import com.caasa.comexsap.exportaciones.model.to.ArchivoTO;
import com.caasa.comexsap.exportaciones.model.to.CarpetaTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/archivoController")
public class ArchivoController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ArchivoService archivoService;

	@PostMapping("/guardarArchivosPedidoFirme")
	public ResponseEntity<ArchivoResponse> guardarArchivosPedidoFirme(@RequestParam("adjuntos") MultipartFile[] archivos, 
			@RequestParam("carpetaInfo") String carpetaInfo) {
		String message = "";
		try {
			CarpetaTO request = new ObjectMapper().readValue(carpetaInfo, CarpetaTO.class);  
			CarpetaTO carpeta = archivoService.guardarArchivosPedidoFirme(archivos, request);
			message = "Se subieron los archivos correctamente.";
			return ResponseEntity.status(HttpStatus.OK).body(new ArchivoResponse(200, message, carpeta));
		} catch (Exception e) {
			message = e.getMessage();
			logger.error("Error en el servicio guardarArchivosPedidoFirme -->" + e);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ArchivoResponse(400, message));
		}
	}
	
	@GetMapping("/obtenerArchivosxCarpeta/{idCarpeta}")
	public ResponseEntity<List<ArchivoTO>> obtenerArchivosxCarpeta(@PathVariable("idCarpeta") int idCarpeta) {
		List<ArchivoTO> filesInfo = new ArrayList<ArchivoTO>();
		try {
			Stream<Path> strArchivos = archivoService.obtenerArchivosxCarpeta(idCarpeta);
			if(strArchivos != null) {
				filesInfo = strArchivos.map(path -> {
					String nombre = path.getFileName().toString();
					ArchivoTO archivo = archivoService.obtenerArchivoxNombre(nombre, idCarpeta);
					String url = MvcUriComponentsBuilder
							.fromMethodName(ArchivoController.class, "obtenerArchivo", archivo.getCarpeta(), path.getFileName().toString())
							.build().toString();
					archivo.setUrl(url);
					return archivo;
				}).collect(Collectors.toList());
			}
		} catch (Exception e) {
			logger.error("Error en obtenerArchivosxCarpeta", e);
		}
		return ResponseEntity.status(HttpStatus.OK).body(filesInfo);
	}
	
	@GetMapping("/archivos/{carpeta}/{filename:.+}")
	public ResponseEntity<Resource> obtenerArchivo(@PathVariable String carpeta, @PathVariable String filename) {
		try {
			Resource file = archivoService.obtenerArchivo(carpeta, filename);
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"." + file.getFilename() + "\"")
					.body(file);		
		} catch (Exception e) {
			logger.error("Error en el servicio obtenerArchivo -->" + e);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
		}
	}
	
	@PostMapping("/eliminarArchivoxId/{id}/{usuario}") 
	public ResponseEntity<ArchivoResponse> eliminarArchivoxId(@PathVariable("id") int id, @PathVariable("usuario") String usuario) {
		try {
			archivoService.eliminarArchivoxId(id, usuario);
			return ResponseEntity.status(HttpStatus.OK).body(new ArchivoResponse("Archivo eliminado del Servidor"));
		} catch (Exception e) {
			logger.error("Error en el servicio eliminarArchivoxId -->" + e);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ArchivoResponse(400, "Error: " + e.getMessage()));
		}
	}
}
