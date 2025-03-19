package com.caasa.comexsap.exportaciones.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.caasa.comexsap.exportaciones.model.response.PackingListCargaSueltaResponse;
import com.caasa.comexsap.exportaciones.model.response.PackingListDetalleResponse;
import com.caasa.comexsap.exportaciones.model.response.PackingListResponse;
import com.caasa.comexsap.exportaciones.model.service.ExportacionMaritimoService;
import com.caasa.comexsap.exportaciones.model.service.PackingListService;
import com.caasa.comexsap.exportaciones.model.service.PdfService;
import com.caasa.comexsap.exportaciones.model.service.PedidoIntercompanyService;
import com.caasa.comexsap.exportaciones.model.to.ComponenteTO;
import com.caasa.comexsap.exportaciones.model.to.ContenedorTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionIntercompanyPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionMaritimoPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionMaritimoTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroPackingListRequestTO;
import com.caasa.comexsap.exportaciones.model.to.PackingListAereoResponse;
import com.caasa.comexsap.exportaciones.model.to.PackingListAereoTO;
import com.caasa.comexsap.exportaciones.model.to.PackingListCargaSueltaTO;
import com.caasa.comexsap.exportaciones.model.to.PackingListTO;
import com.caasa.comexsap.exportaciones.model.to.RequestPackingListAereoTO;
import com.caasa.comexsap.exportaciones.model.to.RequestPackingListCargaSueltaTO;
import com.caasa.comexsap.exportaciones.model.to.RequestPackingListTO;
import com.caasa.comexsap.exportaciones.model.to.RequestTO;
import com.caasa.comexsap.exportaciones.util.Constantes;

@RestController
@RequestMapping("/api/packingListController")
public class PackingListController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PackingListService packingListService;

	@Autowired
	private ExportacionMaritimoService exportacionMaritimoService;

	@Autowired
	private PedidoIntercompanyService pedidoIntercompanyService;

	@Autowired
	private PdfService pdfService;

	@PostMapping("/listarPackingListxFiltro")
	public ResponseEntity<List<ExportacionMaritimoTO>> listarPackingListxFiltro(
			@RequestBody RequestTO<FiltroPackingListRequestTO> request) {
		List<ExportacionMaritimoTO> listaExportacion = null;
		try {
			listaExportacion = packingListService.listarPackingListxFiltro(request.getFormulario());
		} catch (Exception e) {
			logger.error("Error en el servicio listarPackingListxFiltro -->", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(listaExportacion);
	}

	@GetMapping("/obtenerDetallePackingList/{id}")
	public ResponseEntity<PackingListDetalleResponse> obtenerDetallePackingList(
			@PathVariable(value = "id", required = false) int id) {
		PackingListDetalleResponse packingListDetalleResponse = new PackingListDetalleResponse();
		List<ExportacionMaritimoPosicionTO> posicionesExportacion = new ArrayList<>();
		List<ExportacionIntercompanyPosicionTO> posicionesIntercompany = new ArrayList<>();
		try {
			ExportacionMaritimoTO exportacion = this.packingListService.obtenerExportacionxId(id);
			if (exportacion.getIntercompany() == 1) {
				posicionesIntercompany = pedidoIntercompanyService.listarPosicionxIdExportacion(id);
			} else {
				posicionesExportacion = this.exportacionMaritimoService.listarPosicionxIdExportacion(id);
				if (posicionesExportacion != null) {
					for (ExportacionMaritimoPosicionTO posicion : posicionesExportacion) {
						List<ComponenteTO> componentes = exportacionMaritimoService
								.listarComponentexIdPosicion(posicion.getIdPosicion());
						posicion.setComponentes(componentes);
					}
				}
			}
			List<ContenedorTO> listaContenedores = this.packingListService.listarContenedorxId(id);
			List<PackingListTO> listaPackingList = this.packingListService.listarPackingListxId(id);
			packingListDetalleResponse.setExportacion(exportacion);
			packingListDetalleResponse.setPosiciones(posicionesExportacion);
			packingListDetalleResponse.setPosicionesIntercompany(posicionesIntercompany);
			packingListDetalleResponse.setContenedores(listaContenedores);
			packingListDetalleResponse.setPackingList(listaPackingList);
			return ResponseEntity.ok(packingListDetalleResponse);
		} catch (Exception ex) {
			logger.error("Error al consultar servicio obtenerDetallePackingList", ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/registrarPackingList")
	public ResponseEntity<PackingListResponse> registrarPackingList(
			@RequestBody RequestTO<RequestPackingListTO> request) throws Exception {
		PackingListResponse packingListResponse = new PackingListResponse();
		try {
			if (request.getFormulario().getIdExportacion() != 0) {
				int idExportacion = this.packingListService.registrarPackingList(
						request.getFormulario().getContenedores(), request.getFormulario().getPackings(),
						request.getUsuario(), request.getFormulario().getIdExportacion());

				packingListResponse.setCod_rpta(Constantes.UNO);
				packingListResponse.setRpta("¡Registro Exitoso -->" + idExportacion);

			} else {
				packingListResponse.setCod_rpta(Constantes.CERO);
				packingListResponse.setRpta("¡idExportacion requerido");
			}
		} catch (Exception e) {
			packingListResponse.setCod_rpta(Constantes.CERO);
			packingListResponse.setRpta("¡Error al guardar registrarPackingList!");
			logger.error("Error Guardar registrarPackingList --> ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(packingListResponse);
	}

	@GetMapping("/generarPdf/{idExportacion}")
	public ResponseEntity<Resource> generarPdf(@PathVariable(value = "idExportacion") int idExportacion, @RequestParam("versionIngles") boolean versionIngles, @RequestParam("original") boolean original) {
		try {
			return pdfService.generarPackingListPdf(idExportacion, versionIngles, original);
		} catch (Exception e) {
			logger.error("Error al generar PDF PackingList ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/obtenerPackingListCargaSuelta/{idExportacion}")
	public ResponseEntity<PackingListCargaSueltaResponse> obtenerPackingListCargaSuelta(@PathVariable(value = "idExportacion", required = false) int idExportacion){
		PackingListCargaSueltaResponse response = new PackingListCargaSueltaResponse();
		List<ExportacionMaritimoPosicionTO> posicionesExportacion = new ArrayList<>();
		List<ExportacionIntercompanyPosicionTO> posicionesIntercompany = new ArrayList<>();
		try {
			ExportacionMaritimoTO exportacion = this.packingListService.obtenerExportacionxId(idExportacion);
			
			if(exportacion.getIntercompany() == 1) {
				posicionesIntercompany = pedidoIntercompanyService.listarPosicionxIdExportacion(idExportacion);
			} else {
				posicionesExportacion = this.exportacionMaritimoService.listarPosicionxIdExportacion(idExportacion);
				if(posicionesExportacion != null) {
					for (ExportacionMaritimoPosicionTO posicion : posicionesExportacion) {
						List<ComponenteTO> componentes = exportacionMaritimoService.listarComponentexIdPosicion(posicion.getIdPosicion());
						posicion.setComponentes(componentes);
					}
				}
			}

			List<PackingListCargaSueltaTO> packingList = this.packingListService.obtenerPackingListCargaSuelta(idExportacion);
			response.setExportacion(exportacion);
			response.setPosiciones(posicionesExportacion);
			response.setPosicionesIntercompany(posicionesIntercompany);
			response.setPackingList(packingList);
			return ResponseEntity.ok(response);
		} catch(Exception ex) {
			logger.error("Error al consultar servicio obtenerPackingListCargaSuelta", ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/registrarPackingListCargaSuelta")
	public ResponseEntity<PackingListResponse> registrarPackingListCargaSuelta(
			@RequestBody RequestTO<RequestPackingListCargaSueltaTO> request) throws Exception {
		PackingListResponse packingListResponse = new PackingListResponse();
		try {
			this.packingListService.registrarPackingListCargaSuelta(request.getFormulario().getIdExportacion(),
					request.getFormulario().getPacking(), request.getUsuario());
			packingListResponse.setCod_rpta(Constantes.UNO);
			packingListResponse.setRpta("¡PackingList registrado correctamente!");
		} catch (Exception e) {
			packingListResponse.setCod_rpta(Constantes.CERO);
			packingListResponse.setRpta("¡Error al registrar PackingList!");
			logger.error("Error en registrarPackingListCargaSuelta --> ", e);
		}
		return ResponseEntity.ok(packingListResponse);
	}
	
	@GetMapping("/obtenerPackingListAereo/{idExportacion}")
	public ResponseEntity<PackingListAereoResponse> obtenerPackingListAereo(@PathVariable(value = "idExportacion", required = false) int idExportacion){
		PackingListAereoResponse response = new PackingListAereoResponse();
		List<ExportacionMaritimoPosicionTO> posicionesExportacion = new ArrayList<>();
		List<ExportacionIntercompanyPosicionTO> posicionesIntercompany = new ArrayList<>();
		try {
			ExportacionMaritimoTO exportacion = this.packingListService.obtenerExportacionxId(idExportacion);
			
			if(exportacion.getIntercompany() == 1) {
				posicionesIntercompany = pedidoIntercompanyService.listarPosicionxIdExportacion(idExportacion);
			} else {
				posicionesExportacion = this.exportacionMaritimoService.listarPosicionxIdExportacion(idExportacion);
				if(posicionesExportacion != null) {
					for (ExportacionMaritimoPosicionTO posicion : posicionesExportacion) {
						List<ComponenteTO> componentes = exportacionMaritimoService.listarComponentexIdPosicion(posicion.getIdPosicion());
						posicion.setComponentes(componentes);
					}
				}
			}

			List<PackingListAereoTO> packingList = this.packingListService.obtenerPackingListAereo(idExportacion);
			response.setExportacion(exportacion);
			response.setPosiciones(posicionesExportacion);
			response.setPosicionesIntercompany(posicionesIntercompany);
			response.setPackingList(packingList);
			return ResponseEntity.ok(response);
		} catch(Exception ex) {
			logger.error("Error al consultar servicio obtenerPackingListAereo", ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/registrarPackingListAereo")
	public ResponseEntity<PackingListResponse> registrarPackingListAereo(
			@RequestBody RequestTO<RequestPackingListAereoTO> request) throws Exception {
		PackingListResponse packingListResponse = new PackingListResponse();
		try {
			this.packingListService.registrarPackingListAereo(request.getFormulario().getIdExportacion(),
					request.getFormulario().getPacking(), request.getUsuario());
			packingListResponse.setCod_rpta(Constantes.UNO);
			packingListResponse.setRpta("¡PackingList registrado correctamente!");
		} catch (Exception e) {
			packingListResponse.setCod_rpta(Constantes.CERO);
			packingListResponse.setRpta("¡Error al registrar PackingList!");
			logger.error("Error en registrarPackingListAereos --> ", e);
		}
		return ResponseEntity.ok(packingListResponse);
	}

}
