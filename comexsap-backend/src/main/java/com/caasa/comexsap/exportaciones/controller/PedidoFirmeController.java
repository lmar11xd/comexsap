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

import com.caasa.comexsap.exportaciones.model.response.HtmlMessageResponse;
import com.caasa.comexsap.exportaciones.model.response.PedidoFirmeResponse;
import com.caasa.comexsap.exportaciones.model.response.PedidoResponse;
import com.caasa.comexsap.exportaciones.model.service.CotizacionService;
import com.caasa.comexsap.exportaciones.model.service.PedidoFirmeService;
import com.caasa.comexsap.exportaciones.model.to.FiltroPedidoFirmeRequestTO;
import com.caasa.comexsap.exportaciones.model.to.PedidoFirmePosicionTO;
import com.caasa.comexsap.exportaciones.model.to.PedidoFirmeTO;
import com.caasa.comexsap.exportaciones.model.to.PedidoTO;
import com.caasa.comexsap.exportaciones.model.to.RequestCotizacionTO;
import com.caasa.comexsap.exportaciones.model.to.RequestDetallePedidoFirmeTO;
import com.caasa.comexsap.exportaciones.model.to.RequestPedidoFechasTO;
import com.caasa.comexsap.exportaciones.model.to.RequestTO;
import com.caasa.comexsap.exportaciones.util.Constantes;

@RestController
@RequestMapping("/api/pedidoFirmeController")
public class PedidoFirmeController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PedidoFirmeService pedidoFirmeService;

	@Autowired
	private CotizacionService cotizacionService;

	@PostMapping("/listarPedidoFirmexFiltro")
	public ResponseEntity<List<PedidoFirmeTO>> listarPedidoFirmexFiltro(
			@RequestBody RequestTO<FiltroPedidoFirmeRequestTO> request) {
		List<PedidoFirmeTO> listaPedidoFirme = null;
		try {
			listaPedidoFirme = pedidoFirmeService.listarPedidoFirmexFiltro(request.getFormulario());
		} catch (Exception e) {
			logger.error("Error en el servicio listarPedidoFirmexFiltro -->", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(listaPedidoFirme);
	}

	@GetMapping("/obtenerPedidoFirme/{id}")
	public ResponseEntity<PedidoFirmeResponse> obtenerPedidoFirme(@PathVariable("id") int id) {
		PedidoFirmeTO pedidoFirme = null;
		List<PedidoFirmePosicionTO> posiciones = null;
		PedidoFirmeResponse pedidoResponse = new PedidoFirmeResponse();
		try {
			pedidoFirme = pedidoFirmeService.obtenerPedidoFirmexIdPedido(id);
			posiciones = pedidoFirmeService.listarPosicionxPedidoFirme(id);
			pedidoResponse.setPedido(pedidoFirme);
			pedidoResponse.setPosiciones(posiciones);
			pedidoResponse.setCod_rpta(Constantes.UNO);
			pedidoResponse.setRpta("Posiciones: " + posiciones.size());
		} catch (Exception e) {
			logger.error("Error en el servicio listarPedidoFirmexFiltro -->", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(pedidoResponse);
	}

	@PostMapping("/guardarPedidoFirme")
	public ResponseEntity<PedidoResponse> guardarPedidoFirme(
			@RequestBody RequestTO<RequestDetallePedidoFirmeTO> request) throws Exception {
		PedidoResponse cotizacionResponse = new PedidoResponse();
		try {
			PedidoFirmeTO cabecera = request.getFormulario().getCabecera();
			int codigoPedidoExiste = 0;
			if (cabecera.getId() == 0) {
				codigoPedidoExiste = this.cotizacionService.obtenerPedidoxCodigo(cabecera.getCodigoPedido(),
						Constantes.TIPO_SOLICITUD_PEDIDO);
				if (codigoPedidoExiste > 0) {
					cotizacionResponse.setCod_rpta(Constantes.CERO);
					cotizacionResponse.setRpta("¡Código " + cabecera.getCodigoPedido() + " ya existe!");
				} else {
					int idPedido = pedidoFirmeService.registrarPedidoFirme(cabecera, request.getUsuario(),
							Constantes.VACIO, Constantes.INICIAL);
					pedidoFirmeService.registrarPosicionPedido(request.getFormulario().getPosiciones(),
							request.getUsuario(), idPedido, cabecera.getIdMoneda(), 0);
					if (cabecera.isEnviarCorreo()) {
						pedidoFirmeService.enviarCorreoPedidoFirme(idPedido);
					}
					cotizacionResponse.setCod_rpta(Constantes.UNO);
					cotizacionResponse.setRpta("¡Registro Exitoso -->" + idPedido);
				}
			} else {
				pedidoFirmeService.actualizarPedidoFirme(request.getFormulario().getCabecera(), request.getFormulario().getPosiciones(), request.getUsuario());
				if (cabecera.isEnviarCorreo()) {
					pedidoFirmeService.enviarCorreoPedidoFirme(request.getFormulario().getCabecera().getId());
				}
				cotizacionResponse.setCod_rpta(Constantes.UNO);
				cotizacionResponse
						.setRpta("¡Actualizacion Exitosa -->" + request.getFormulario().getCabecera().getId());
			}
		} catch (Exception e) {
			cotizacionResponse.setCod_rpta(Constantes.CERO);
			cotizacionResponse.setRpta("¡Error al guardar Pedido Firme!");
			logger.error("Error Guardar Pedido Firme --> ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(cotizacionResponse);
	}

	@PostMapping("/confirmarPedidoFirme")
	public ResponseEntity<PedidoResponse> confirmarCotizacion(
			@RequestBody RequestTO<RequestDetallePedidoFirmeTO> request) {
		PedidoResponse response = new PedidoResponse();
		boolean bolError = false;
		try {
			int estadoDocumentoExiste = this.cotizacionService
					.obtenerEstadoDocumentoxPedido(request.getFormulario().getCabecera().getId(), Constantes.INICIAL);
			if (estadoDocumentoExiste <= 0) {
				response.setCod_rpta(Constantes.CERO);
				response.setRpta("Documento No Existe O Ya Fue Confirmado");
				bolError = true;
			}

			if (!bolError) {
				pedidoFirmeService.actualizarPedidoFirme(request.getFormulario().getCabecera(),
						request.getFormulario().getPosiciones(), request.getUsuario());
				PedidoTO pedidoBean = this.cotizacionService
						.obtenerPedidoxIdPedido(request.getFormulario().getCabecera().getId(), Constantes.INICIAL);
				cotizacionService.confirmarPedido(request.getFormulario().getCabecera().getId(), request.getUsuario(),
						Constantes.CONFIRMADO);
				pedidoFirmeService.enviarCorreoPedidoFirmeConfirmado(request.getFormulario().getCabecera().getId());
				response.setCod_rpta(Constantes.UNO);
				response.setCod_pedido(pedidoBean.getCodigoPedido());
				response.setRpta("¡Pedido Firme Confirmado!");
			}
		} catch (Exception e) {
			response.setCod_rpta(Constantes.CERO);
			logger.error("Error en el servicio confirmarPedidoFirme -->", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(response);
	}

	@PostMapping("/modificarDocumento")
	public ResponseEntity<PedidoResponse> modificarDocumento(
			@RequestBody RequestTO<RequestDetallePedidoFirmeTO> request) {
		PedidoResponse cotizacion = new PedidoResponse();
		boolean bolError = false;
		try {
			int estadoDocumentoExiste = this.cotizacionService.obtenerEstadoDocumentoxPedido(
					request.getFormulario().getCabecera().getId(), Constantes.CONFIRMADO);
			if (estadoDocumentoExiste <= 0) {
				cotizacion.setCod_rpta(Constantes.CERO);
				cotizacion.setRpta("El Documento No Existe O No Esta Confirmado.");
				bolError = true;
			}

			int pedidoTieneExportacion = this.pedidoFirmeService.pedidoFirmeConExportacion(request.getFormulario().getCabecera().getId());
			if(pedidoTieneExportacion > 0) {
				cotizacion.setCod_rpta(Constantes.CERO);
				cotizacion.setRpta("Pedido firme ya tiene documento de exportación.");
				bolError = true;
			}
			
			if (!bolError) {
				cotizacionService.confirmarPedido(request.getFormulario().getCabecera().getId(), request.getUsuario(),
						Constantes.INICIAL);
				cotizacion.setCod_rpta(Constantes.UNO);
				cotizacion.setRpta("El documento ya puede ser modificado.");
			}

		} catch (Exception e) {
			cotizacion.setCod_rpta(Constantes.CERO);
			logger.error("Error en el servicio modificarDocumento -->", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(cotizacion);
	}

	@PostMapping("/duplicarPedidoFirme")
	public ResponseEntity<PedidoFirmeResponse> duplicarPedidoFirme(@RequestBody RequestTO<RequestCotizacionTO> request)
			throws Exception {
		PedidoFirmeTO pedidoBean = null;
		List<PedidoFirmePosicionTO> listaPosicionesPedido = null;
		PedidoFirmeResponse pedidoResponse = new PedidoFirmeResponse();
		int codigoPedidoExiste = 0;
		int idPedidoExiste = 0;
		boolean bolError = false;
		try {
			if (request.getFormulario().getCodigoNuevoPedido().trim() != "") {
				codigoPedidoExiste = this.cotizacionService.obtenerPedidoxCodigo(
						request.getFormulario().getCodigoNuevoPedido(), Constantes.TIPO_SOLICITUD_PEDIDO);
				if (codigoPedidoExiste > 0) {
					pedidoResponse.setCod_rpta(Constantes.CERO);
					pedidoResponse.setRpta("elPedidoFirmeExiste");
				} else {
					idPedidoExiste = this.cotizacionService.obtenerPedido(request.getFormulario().getIdPedido());
					if (idPedidoExiste == 0) {
						pedidoResponse.setCod_rpta(Constantes.CERO);
						pedidoResponse.setRpta("elPedidoFirmeNoExiste");
						bolError = true;
					}
					if (!bolError) {
						pedidoBean = pedidoFirmeService
								.obtenerPedidoFirmexIdPedido(request.getFormulario().getIdPedido());
						listaPosicionesPedido = pedidoFirmeService
								.listarPosicionxPedidoFirme(request.getFormulario().getIdPedido());
						pedidoBean.setIdCarpeta(0);
						int idPedidoFirme = pedidoFirmeService.registrarPedidoFirme(pedidoBean, request.getUsuario(),
								request.getFormulario().getCodigoNuevoPedido(), Constantes.INICIAL);
						pedidoFirmeService.registrarPosicionPedido(listaPosicionesPedido, request.getUsuario(), idPedidoFirme, pedidoBean.getIdMoneda(), 1);
						request.getFormulario().setIdPedido(idPedidoFirme);
						pedidoBean = new PedidoFirmeTO();
						listaPosicionesPedido = new ArrayList<PedidoFirmePosicionTO>();
						pedidoBean = pedidoFirmeService.obtenerPedidoFirmexIdPedido(idPedidoFirme);
						listaPosicionesPedido = pedidoFirmeService.listarPosicionxPedidoFirme(pedidoBean.getId());
						pedidoResponse.setPedido(pedidoBean);
						pedidoResponse.setPosiciones(listaPosicionesPedido);
						pedidoResponse.setCod_rpta(Constantes.UNO);
						pedidoResponse.setRpta("Se registro correctamente -->" + idPedidoFirme);

					}
				}
			} else {
				pedidoResponse.setCod_rpta(Constantes.CERO);
				pedidoResponse.setRpta("¡Código pedido requerido!");
			}
		} catch (Exception e) {
			pedidoResponse.setCod_rpta(Constantes.CERO);
			logger.error("Error Duplicar Pedido Firme --> ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(pedidoResponse);
	}

	@PostMapping("/verCorreoPedidoFirme")
	public ResponseEntity<HtmlMessageResponse> verCorreoPedidoFirme(@RequestBody PedidoFirmeTO pedido) {
		HtmlMessageResponse response = new HtmlMessageResponse();
		try {
			String html = pedidoFirmeService.verCorreoPedidoFirme(pedido.getId(), pedido.getMsjLineaCredito());
			response.setHtml(html);
			response.setCod_rpta(Constantes.UNO);
			response.setRpta("");
		} catch (Exception e) {
			response.setCod_rpta(Constantes.CERO);
			response.setRpta(e.getMessage());
			logger.error("Error en el servicio verCorreoPedidoFirme -->", e);
		}
		return ResponseEntity.ok(response);
	}

	@GetMapping("/enviarCorreoActualizacionPrecios/{idPedido}")
	public ResponseEntity<PedidoFirmeResponse> enviarCorreoActualizacionPrecios(
			@PathVariable("idPedido") int idPedido) {
		PedidoFirmeResponse response = new PedidoFirmeResponse();
		try {
			pedidoFirmeService.enviarCorreoActualizacionPrecios(idPedido);
			response.setCod_rpta(Constantes.UNO);
			response.setRpta("Correo actualización de precios enviado");
		} catch (Exception e) {
			response.setCod_rpta(Constantes.CERO);
			response.setRpta(e.getMessage());
			logger.error("Error en el servicio enviarCorreoActualizacionPrecios -->", e);
		}
		return ResponseEntity.ok(response);
	}

	@GetMapping("/enviarCorreoFechasDisponibilidad/{idPedido}")
	public ResponseEntity<PedidoFirmeResponse> enviarCorreoFechasDisponibilidad(
			@PathVariable("idPedido") int idPedido) {
		PedidoFirmeResponse response = new PedidoFirmeResponse();
		try {
			pedidoFirmeService.enviarCorreoFechasDisponibilidad(idPedido);
			response.setCod_rpta(Constantes.UNO);
			response.setRpta("Correo fechas de disponibilidad enviado.");
		} catch (Exception e) {
			response.setCod_rpta(Constantes.CERO);
			response.setRpta(e.getMessage());
			logger.error("Error en el servicio enviarCorreoFechasDisponibilidad -->", e);
		}
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/confirmarFechasDisponibilidad")
	public ResponseEntity<PedidoFirmeResponse> confirmarFechasDisponibilidad(@RequestBody RequestTO<RequestPedidoFechasTO> request) {
		PedidoFirmeResponse response = new PedidoFirmeResponse();
		try {
			pedidoFirmeService.confirmarFechasDisponibilidad(request.getFormulario(), request.getUsuario());
			response.setCod_rpta(Constantes.UNO);
			response.setRpta("Fechas de Disponibilidad Confirmadas.");
		} catch (Exception e) {
			response.setCod_rpta(Constantes.CERO);
			response.setRpta(e.getMessage());
			logger.error("Error en el servicio confirmarFechasDisponibilidad -->", e);
		}
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/anularPedido/{id}")
	public ResponseEntity<?> anularPedido(@RequestParam(required = true) String usuario, @PathVariable("id") int id) {
		PedidoResponse response = new PedidoResponse();
		try {
			int pedido = cotizacionService.obtenerPedido(id);
			if (pedido == 0) {
				return ResponseEntity.notFound().build();
			}
			
			int pedidoTieneExportacion = this.pedidoFirmeService.pedidoFirmeConExportacion(id);
			if(pedidoTieneExportacion > 0) {
				response.setCod_rpta(Constantes.CERO);
				response.setRpta("Pedido Firme ya tiene un Documento de Exportación.");
			} else {
				pedidoFirmeService.anularPedido(id, usuario);
				response.setCod_rpta(Constantes.UNO);
				response.setRpta("Documento anulado correctamente");
			}
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			logger.error("Error al consultar el servicio anularPedido", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
