package com.caasa.comexsap.exportaciones.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
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

import com.caasa.comexsap.exportaciones.model.response.ConsultarStockResponse;
import com.caasa.comexsap.exportaciones.model.response.ExportacionMaritimoResponse;
import com.caasa.comexsap.exportaciones.model.response.PedidoExportacionResponse;
import com.caasa.comexsap.exportaciones.model.service.ExportacionMaritimoService;
import com.caasa.comexsap.exportaciones.model.service.PdfService;
import com.caasa.comexsap.exportaciones.model.to.ComponenteTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionAgrupadoTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionEtiquetaTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionFacturaTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionMaritimoPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionMaritimoTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionOperacionTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionPaisTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionPedidoPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroDTConsultarStockReqTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroDTCreaPedidosReqTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroExportacionMaritimoRequestTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroExportacionPedidoPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.RequestExportacionMaritimoTO;
import com.caasa.comexsap.exportaciones.model.to.RequestReporteFacturaComercialTO;
import com.caasa.comexsap.exportaciones.model.to.RequestReporteFacturaTO;
import com.caasa.comexsap.exportaciones.model.to.RequestTO;
import com.caasa.comexsap.exportaciones.util.Constantes;

@RestController
@RequestMapping("/api/exportacionMaritimoController")
public class ExportacionMaritimoController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ExportacionMaritimoService exportacionMaritimoService;
	
	@Autowired
	private PdfService pdfService;

	@PostMapping("/listarExportacionMaritimoxFiltro")
	public ResponseEntity<List<ExportacionMaritimoTO>> listarExportacionMaritimoxFiltro(
			@RequestBody RequestTO<FiltroExportacionMaritimoRequestTO> request) {
		List<ExportacionMaritimoTO> listaExportacionMaritimo = null;
		try {
			listaExportacionMaritimo = exportacionMaritimoService
					.listarExportacionMaritimoxFiltro(request.getFormulario());
		} catch (Exception e) {
			logger.error("Error en el servicio listarExportacionMaritimoxFiltro -->", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(listaExportacionMaritimo);
	}

	@GetMapping("/obtenerExportacionMaritimo/{idExportacion}")
	public ResponseEntity<ExportacionMaritimoResponse> obtenerExportacionMaritimo(
			@PathVariable("idExportacion") int id) {
		ExportacionMaritimoTO exportacionMaritimoTO = null;
		List<ExportacionMaritimoPosicionTO> listaPosicionExportacion = null;
		List<ExportacionFacturaTO> listaExportacionFactura = null;
		ExportacionMaritimoResponse response = new ExportacionMaritimoResponse();
		try {
			exportacionMaritimoTO = exportacionMaritimoService.obtenerExportacionMaritimoxId(id);
			listaPosicionExportacion = exportacionMaritimoService.listarPosicionxIdExportacion(id);
			if(listaPosicionExportacion != null) {
				String pedidoSap = "";
				for (ExportacionMaritimoPosicionTO posicion : listaPosicionExportacion) {
					pedidoSap = posicion.getPedidoSap();
					List<ComponenteTO> componentes = exportacionMaritimoService.listarComponentexIdPosicion(posicion.getIdPosicion());
					posicion.setComponentes(componentes);
				}
				if(pedidoSap != null && pedidoSap != "") {
					exportacionMaritimoService.guardarFacturaSap(exportacionMaritimoTO, listaPosicionExportacion, pedidoSap);
				}
			}
			listaExportacionFactura = exportacionMaritimoService.listarExportacionFacturaxIdExportacion(id);
			response.setExportacion(exportacionMaritimoTO);
			response.setPosiciones(listaPosicionExportacion);
			response.setFacturaImpresion(listaExportacionFactura);
			response.setCod_rpta(Constantes.UNO);
			response.setRpta("Posiciones: " + listaPosicionExportacion.size());
		} catch (Exception e) {
			response.setCod_rpta(Constantes.CERO);
			response.setRpta(e.getMessage());
			logger.error("Error en el servicio obtenerExportacionMaritimo -->", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(response);
	}

	@PostMapping("/guardarExportacionMaritimo")
	public ResponseEntity<ExportacionMaritimoResponse> guardarExportacionMaritimo(
			@RequestBody RequestTO<RequestExportacionMaritimoTO> request) throws Exception {
		ExportacionMaritimoResponse exportacionMaritimoResponse = new ExportacionMaritimoResponse();
		try {
			ExportacionMaritimoTO cabecera = request.getFormulario().getExportacion();
			if (cabecera.getId() == 0) {
				int idExportacion = this.exportacionMaritimoService.registrarExportacionMaritimo(request.getFormulario(), request.getUsuario());

				exportacionMaritimoResponse.setCod_rpta(Constantes.UNO);
				exportacionMaritimoResponse.setRpta("¡Registro Exitoso -->" + idExportacion);

			} else {
				this.exportacionMaritimoService.actualizarExportacionMaritimo(request.getFormulario(), request.getUsuario());

				exportacionMaritimoResponse.setCod_rpta(Constantes.UNO);
				exportacionMaritimoResponse
						.setRpta("¡Actualizacion Exitosa -->" + request.getFormulario().getExportacion().getId());
			}
		} catch (Exception e) {
			exportacionMaritimoResponse.setCod_rpta(Constantes.CERO);
			exportacionMaritimoResponse.setRpta("¡Error al guardar ExportacionMaritimo!");
			logger.error("Error Guardar Cotizacion --> ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(exportacionMaritimoResponse);
	}

	@PostMapping("/listarPedidoPosicion")
	public ResponseEntity<List<ExportacionPedidoPosicionTO>> listarPedidoPosicion(
			@RequestBody RequestTO<FiltroExportacionPedidoPosicionTO> request) {
		List<ExportacionPedidoPosicionTO> listaExportacionPedidoPosicion = null;
		try {
			listaExportacionPedidoPosicion = exportacionMaritimoService.listarPedidoPosicion(request.getFormulario());
			if(listaExportacionPedidoPosicion != null) {
				for (ExportacionPedidoPosicionTO posicion : listaExportacionPedidoPosicion) {
					List<ComponenteTO> componentes = exportacionMaritimoService.listarComponentexIdPosicion(posicion.getId());
					posicion.setComponentes(componentes);
				}
			}
		} catch (Exception e) {
			logger.error("Error en el servicio listarPedidoPosicion -->", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(listaExportacionPedidoPosicion);
	}

	@PostMapping("/confirmarExportacion")
	public ResponseEntity<ExportacionMaritimoResponse> confirmarExportacion(
			@RequestBody RequestTO<RequestExportacionMaritimoTO> request) {
		ExportacionMaritimoResponse exportacionResponse = new ExportacionMaritimoResponse();
		boolean bolError = false;
		try {
			int estadoDocumentoExiste = this.exportacionMaritimoService
					.buscarExportacionMaritimoxId(request.getFormulario().getExportacion().getId(), Constantes.INICIAL);
			if (estadoDocumentoExiste <= 0) {
				exportacionResponse.setCod_rpta(Constantes.CERO);
				exportacionResponse.setRpta("elDocumentoNoExisteOYaFueConfirmado");
				bolError = true;
			}
			if (!bolError) {
				this.exportacionMaritimoService.confirmarExportacionMaritimo(
						request.getFormulario().getExportacion().getId(), request.getUsuario(), Constantes.CONFIRMADO);
				exportacionResponse.setCod_rpta(Constantes.UNO);
				exportacionResponse
						.setRpta("Confirmacion Exitosa! id-->" + request.getFormulario().getExportacion().getId());
			}
		} catch (Exception e) {
			exportacionResponse.setCod_rpta(Constantes.CERO);
			logger.error("Error en el servicio confirmarExportacion -->", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(exportacionResponse);
	}

	@PostMapping("/eliminarExportacion/{id}")
	public ResponseEntity<ExportacionMaritimoResponse> eliminarExportacion(
			@RequestParam(required = true) String usuario, @PathVariable("id") int id) {
		ExportacionMaritimoResponse exportacionMaritimoResponse = new ExportacionMaritimoResponse();
		try {
			this.exportacionMaritimoService.eliminarExportacionMaritimo(id, usuario);
			List<ExportacionMaritimoPosicionTO> listaPosicion = this.exportacionMaritimoService
					.obtenerExportacionPedido(id);
			for (ExportacionMaritimoPosicionTO posicionMaritimo : listaPosicion) {
				this.exportacionMaritimoService.eliminarExportacionMaritimoPosicion(posicionMaritimo.getId(),
						posicionMaritimo.getIdPosicion(), usuario);
			}
			exportacionMaritimoResponse.setCod_rpta(Constantes.UNO);
			exportacionMaritimoResponse.setRpta("Registro eliminado correctamente! id-->" + id);
		} catch (Exception e) {
			exportacionMaritimoResponse.setCod_rpta(Constantes.CERO);
			exportacionMaritimoResponse.setRpta(e.getMessage());
			logger.error("Error en el servicio eliminarExportacion -->", e);
		}
		return ResponseEntity.ok(exportacionMaritimoResponse);
	}

	@PostMapping("/crearPedidoSap")
	public ResponseEntity<PedidoExportacionResponse> crearPedidoSap(
			@RequestBody RequestTO<FiltroDTCreaPedidosReqTO> request) throws Exception {
		PedidoExportacionResponse salida = null;
		try {
			salida = exportacionMaritimoService.crearPedidoSap(request.getFormulario());
			return ResponseEntity.ok(salida);
		} catch (DataAccessException e) {
			logger.error("Error en el servicio crearPedidoSap -->", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/modificarPedidoSap")
	public ResponseEntity<PedidoExportacionResponse> modificarPedidoSap(
			@RequestBody RequestTO<FiltroDTCreaPedidosReqTO> request) throws Exception {
		PedidoExportacionResponse salida = null;
		try {
			salida = exportacionMaritimoService.modificarPedidoSap(request.getFormulario());
			return ResponseEntity.ok(salida);
		} catch (DataAccessException e) {
			logger.error("Error en el servicio modificarPedidoSap -->", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/consultarStock")
	public ResponseEntity<ConsultarStockResponse> consultarStock(
			@RequestBody RequestTO<FiltroDTConsultarStockReqTO> request) throws Exception {
		ConsultarStockResponse salida = null;
		try {
			salida = exportacionMaritimoService.consultarStockPedido(request.getFormulario());
			return ResponseEntity.ok(salida);
		} catch (DataAccessException e) {
			logger.error("Error en el servicio consultarStock-->", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/obtenerTotalPedidoExportacion")
	public ResponseEntity<List<ExportacionOperacionTO>> obtenerTotalPedidoExportacion() {
		List<ExportacionOperacionTO> listaExportacion;
		try {
			listaExportacion = this.exportacionMaritimoService.obtenerTotalPedidosExportacion();
		} catch (Exception e) {
			logger.error("Error en el servicio obtenerTotalPedidoExportacion -->", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(listaExportacion);
	}
	
	@GetMapping("/obtenerTotalExportacion")
	public ResponseEntity<List<ExportacionAgrupadoTO>> obtenerTotalExportacion() {
		List<ExportacionAgrupadoTO> listaExportacion;
		try {
			listaExportacion = this.exportacionMaritimoService.obtenerTotalExportacion();
		} catch (Exception e) {
			logger.error("Error en el servicio obtenerTotalExportacion -->", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(listaExportacion);
	}
	
	@GetMapping("/obtenerTotalExportacionPais")
	public ResponseEntity<List<ExportacionPaisTO>> obtenerTotalExportacionPais() {
		List<ExportacionPaisTO> listaExportacion;
		try {
			listaExportacion = this.exportacionMaritimoService.obtenerTotalExportacionPais();
		} catch (Exception e) {
			logger.error("Error en el servicio obtenerTotalExportacionPais -->", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(listaExportacion);
	}
	
	@PostMapping("/generarFacturaComercialPdf")
	public ResponseEntity<Resource> generarFacturaComercialPdf(@RequestBody RequestTO<RequestReporteFacturaComercialTO> request, @RequestParam("versionIngles") boolean versionIngles, @RequestParam("original") boolean original, @RequestParam("tipoFactura") int tipoFactura) {
		try {
			return this.pdfService.generarFacturaComercialPdf(request.getFormulario(), versionIngles, original, tipoFactura);
		} catch (Exception e) {
			logger.error("Error en generarFacturaComercialPdf", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@PostMapping("/generarExportacionMaritimaPdf")
	public ResponseEntity<Resource> generarExportacionMaritimaPdf(@RequestBody RequestTO<RequestReporteFacturaTO> request) {
		try {
			return this.pdfService.generarExportacionMaritimaPdf(request.getFormulario());
		} catch (Exception e) {
			logger.error("Error en generarExportacionMaritimaPdf", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/actualizarEntrega/{idExportacion}")
	public ResponseEntity<ExportacionMaritimoResponse> actualizarEntrega(
			@PathVariable("idExportacion") int idExportacion, 
			@RequestParam(required = true) String pedidoSap
	) {
		ExportacionMaritimoResponse response = new ExportacionMaritimoResponse();
		try {
			this.exportacionMaritimoService.actualizarEntrega(idExportacion, pedidoSap);
			response.setCod_rpta(Constantes.UNO);
		} catch (Exception e) {
			response.setCod_rpta(Constantes.CERO);
			response.setRpta(e.getMessage());
			logger.error("Error en actualizarEntrega --> ", e);
		}
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/listarExportacionEtiquetas/{idExportacion}")
	public ResponseEntity<?> listarExportacionEtiquetas(@PathVariable("idExportacion") int idExportacion) {
		List<ExportacionEtiquetaTO> lista = new ArrayList<>();
		try {
			lista = this.exportacionMaritimoService.listarExportacionEtiquetas(idExportacion);
			return ResponseEntity.ok(lista);
		} catch (Exception e) {
			logger.error("Error en listarExportacionEtiquetas --> ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
