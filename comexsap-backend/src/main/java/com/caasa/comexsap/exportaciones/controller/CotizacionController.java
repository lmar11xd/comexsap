package com.caasa.comexsap.exportaciones.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.caasa.comexsap.exportaciones.model.response.PedidoResponse;
import com.caasa.comexsap.exportaciones.model.service.CotizacionService;
import com.caasa.comexsap.exportaciones.model.service.PdfService;
import com.caasa.comexsap.exportaciones.model.to.CotizacionTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroCotizacionRequestTO;
import com.caasa.comexsap.exportaciones.model.to.PosicionPedidoTO;
import com.caasa.comexsap.exportaciones.model.to.RequestCotizacionTO;
import com.caasa.comexsap.exportaciones.model.to.RequestDetalleCotizacionTO;
import com.caasa.comexsap.exportaciones.model.to.RequestTO;
import com.caasa.comexsap.exportaciones.util.Constantes;

@RestController
@RequestMapping("/api/cotizacionController")
public class CotizacionController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CotizacionService cotizacionService;

	@Autowired
	private PdfService pdfService;

	@PostMapping("/listarCotizacionxFiltro")
	public ResponseEntity<List<CotizacionTO>> listarCotizacionxFiltro(
			@RequestBody RequestTO<FiltroCotizacionRequestTO> request) {

		List<CotizacionTO> listaCotizaciones = null;
		try {
			listaCotizaciones = cotizacionService.listarCotizacionxFiltro(request.getFormulario());
			return ResponseEntity.ok(listaCotizaciones);
		} catch (Exception e) {
			logger.error("Error al consultar el servicio listarCotizacionxFiltro", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/anularPedido/{id}")
	public ResponseEntity<?> anularPedido(@RequestParam(required = true) String usuario, @PathVariable("id") int id) {
		Map<String, Object> response = new HashMap<String, Object>();
		try {
			int pedido = cotizacionService.obtenerPedido(id);
			if (pedido == 0) {
				return ResponseEntity.notFound().build();
			}
			cotizacionService.anularPedido(id, usuario);
			response.put("message", "Documento anulado correctamente");
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			logger.error("Error al consultar el servicio anularPedido", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/duplicarCotizacion")
	public ResponseEntity<PedidoResponse> duplicarCotizacion(@RequestBody RequestTO<RequestCotizacionTO> request)
			throws Exception {
		List<CotizacionTO> listaCotizaciones = null;
		List<PosicionPedidoTO> listaPosicionesPedido = null;
		PedidoResponse cotizacionResponse = new PedidoResponse();
		int codigoPedidoExiste = 0;
		try {
			if (request.getFormulario().getCodigoNuevoPedido().trim() != "") {
				codigoPedidoExiste = this.cotizacionService.obtenerPedidoxCodigo(
						request.getFormulario().getCodigoNuevoPedido(), Constantes.TIPO_SOLICITUD_COTIZACION);
				if (codigoPedidoExiste > 0) {
					cotizacionResponse.setCod_rpta(Constantes.CERO);
					cotizacionResponse
							.setRpta("¡Código " + request.getFormulario().getCodigoNuevoPedido() + " ya existe!");
				} else {
					listaCotizaciones = cotizacionService.listarCotizacionxPedido(request.getFormulario());

					for (CotizacionTO cotizacion : listaCotizaciones) {
						cotizacion.setFechaSolicitud(new Date(System.currentTimeMillis()));
						if (cotizacion.getCodigoCliente().startsWith(Constantes.CODIGO_CLIENTE)) {
							cotizacion.setClienteSapExiste(false);
						} else {
							cotizacion.setClienteSapExiste(true);
						}
					}

					listaPosicionesPedido = cotizacionService.listarPosicionxPedido(request.getFormulario());
					int idCotizacion = cotizacionService.registrarCotizacion(listaCotizaciones.get(0), request.getUsuario(),
							request.getFormulario().getCodigoNuevoPedido(), Constantes.INICIAL);
					cotizacionService.registrarPosicionPedido(listaPosicionesPedido, request.getUsuario(), idCotizacion,
							listaCotizaciones.get(0).getIdMoneda());
					request.getFormulario().setIdPedido(idCotizacion);
					listaCotizaciones = new ArrayList<CotizacionTO>();
					listaPosicionesPedido = new ArrayList<PosicionPedidoTO>();
					listaCotizaciones = cotizacionService.listarCotizacionxPedido(request.getFormulario());
					listaPosicionesPedido = cotizacionService.listarPosicionxPedido(request.getFormulario());
					cotizacionResponse.setCotizacion(listaCotizaciones);
					cotizacionResponse.setPosiciones(listaPosicionesPedido);
					cotizacionResponse.setCod_rpta(Constantes.UNO);
				}
			} else {
				cotizacionResponse.setCod_rpta(Constantes.CERO);
				cotizacionResponse.setRpta("¡Código pedido requerido!");
			}
		} catch (DataAccessException e) {
			cotizacionResponse.setCod_rpta(Constantes.CERO);
			logger.error("Error Duplicar Cotizacion --> ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(cotizacionResponse);
	}

	@PostMapping("/editarCotizacion")
	public ResponseEntity<PedidoResponse> editarCotizacion(@RequestBody RequestTO<RequestCotizacionTO> request)
			throws Exception {
		List<CotizacionTO> listaCotizaciones = null;
		List<PosicionPedidoTO> listaPosicionesPedido = null;
		PedidoResponse cotizacionResponse = new PedidoResponse();
		try {
			listaCotizaciones = cotizacionService.listarCotizacionxPedido(request.getFormulario());
			listaPosicionesPedido = cotizacionService.listarPosicionxPedido(request.getFormulario());
			cotizacionResponse.setCotizacion(listaCotizaciones);
			cotizacionResponse.setPosiciones(listaPosicionesPedido);
			cotizacionResponse.setCod_rpta(Constantes.UNO);
		} catch (DataAccessException e) {
			cotizacionResponse.setCod_rpta(Constantes.CERO);
			logger.error("No se pudo obetener la cotización --> ", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(cotizacionResponse);
	}

	@PostMapping("/confirmarCotizacion")
	public ResponseEntity<PedidoResponse> confirmarCotizacion(
			@RequestBody RequestTO<RequestDetalleCotizacionTO> request) {
		PedidoResponse cotizacion = new PedidoResponse();
		boolean bolError = false;
		try {
			CotizacionTO cabecera = request.getFormulario().getCabecera();
			int estadoDocumentoExiste = this.cotizacionService.obtenerEstadoDocumentoxPedido(cabecera.getIdCotizacion(), Constantes.INICIAL);
			
			if (estadoDocumentoExiste <= 0) {
				cotizacion.setCod_rpta(Constantes.CERO);
				cotizacion.setRpta("elDocumentoNoExisteOYaFueConfirmado");
				bolError = true;
			}
			
			if (!bolError) {
				int tipoSolicitud = this.cotizacionService.obtenerPedidoxCodigo(request.getFormulario().getCodigoPedidoFirme(),	Constantes.TIPO_SOLICITUD_PEDIDO);
				if (tipoSolicitud > 0) {
					cotizacion.setCod_rpta(Constantes.CERO);
					cotizacion.setRpta("elPedidoFirmeExiste");
					bolError = true;
				}
			}
			
			if (!bolError) {
				cotizacionService.actualizarCotizacion(request.getFormulario(), request.getUsuario());
				cotizacionService.confirmarPedido(request.getFormulario().getCabecera().getIdCotizacion(),
						request.getUsuario(), Constantes.CONFIRMADO);

				int pedidoInsertado = this.cotizacionService.registrarPedidoConfirmado(cabecera,
						request.getUsuario(), request.getFormulario().getCodigoPedidoFirme(), Constantes.INICIAL);
				if (pedidoInsertado > 0) {
					RequestCotizacionTO requestPos = new RequestCotizacionTO();
					requestPos.setIdPedido(cabecera.getIdCotizacion());
					List<PosicionPedidoTO> listaPosicionesPedido = this.cotizacionService.listarPosicionxPedido(requestPos);
					this.cotizacionService.registrarPosPedidoConfirmado(listaPosicionesPedido, request.getUsuario(), pedidoInsertado, cabecera.getIdMoneda());
					cotizacion.setCod_rpta(Constantes.UNO);
					cotizacion.setRpta("confirmacion exitosa!");
				}
			}
		} catch (Exception e) {
			cotizacion.setCod_rpta(Constantes.CERO);
			cotizacion.setCod_rpta(e.getMessage());
			logger.error("Error en el servicio confirmarCotizacion -->", e);
		}
		return ResponseEntity.ok(cotizacion);
	}

	@PostMapping("/guardarCotizacion")
	public ResponseEntity<PedidoResponse> guardarCotizacion(@RequestBody RequestTO<RequestDetalleCotizacionTO> request)
			throws Exception {
		PedidoResponse cotizacionResponse = new PedidoResponse();
		try {
			CotizacionTO cabecera = request.getFormulario().getCabecera();
			int codigoPedidoExiste = 0;
			if (cabecera.getIdCotizacion() == 0) {
				RequestCotizacionTO requestCotizacion = new RequestCotizacionTO();
				requestCotizacion.setCodigoNuevoPedido(cabecera.getCodigoPedido());
				codigoPedidoExiste = this.cotizacionService
						.obtenerPedidoxCodigo(
						requestCotizacion.getCodigoNuevoPedido(), Constantes.TIPO_SOLICITUD_COTIZACION);
				if (codigoPedidoExiste > 0) {
					cotizacionResponse.setCod_rpta(Constantes.CERO);
					cotizacionResponse.setRpta("¡Código " + requestCotizacion.getCodigoNuevoPedido() + " ya existe!");
				} else {
					int idCotizacion = cotizacionService.registrarCotizacion(cabecera, request.getUsuario(),
							Constantes.VACIO, Constantes.INICIAL);
					cotizacionService.registrarPosicionPedido(request.getFormulario().getPosiciones(),
							request.getUsuario(), idCotizacion, request.getFormulario().getCabecera().getIdMoneda());
					cotizacionResponse.setCod_rpta(Constantes.UNO);
					cotizacionResponse.setRpta("¡Cotización guardada correctamente -->" + idCotizacion);
				}
			} else {
				int estadoDocumento = cotizacionService.obtenerEstadoDocumentoxPedido(cabecera.getIdCotizacion(), Constantes.CONFIRMADO);
				if (estadoDocumento > 0) {
					cotizacionResponse.setCod_rpta(Constantes.CERO);
					cotizacionResponse.setRpta("¡No se puede actualizar - estadoDocumento Confirmado");
				} else {
					cotizacionService.actualizarCotizacion(request.getFormulario(), request.getUsuario());
					cotizacionResponse.setCod_rpta(Constantes.UNO);
					cotizacionResponse.setRpta("¡Actualizacion Exitosa -->" + cabecera.getIdCotizacion());
				}
			}
		} catch (Exception e) {
			cotizacionResponse.setCod_rpta(Constantes.CERO);
			cotizacionResponse.setRpta("¡Error al guardar Cotización!");
			logger.error("Error Guardar Cotizacion --> ", e);
		}
		return ResponseEntity.ok(cotizacionResponse);
	}

	@PostMapping("/enviarCorreoCotizacionProforma")
	public ResponseEntity<PedidoResponse> enviarCorreoCotizacionProforma(
			@RequestBody RequestTO<RequestDetalleCotizacionTO> request) {
		PedidoResponse response = new PedidoResponse();
		try {
			CotizacionTO cabecera = request.getFormulario().getCabecera();
			List<PosicionPedidoTO> posiciones = request.getFormulario().getPosiciones();
			Resource resource = this.pdfService.generarCotizacionPdf(cabecera, posiciones, request.getUsuario())
					.getBody();
			boolean enviado = cotizacionService.enviarCorreoCotizacionProforma(cabecera, resource);
			if (enviado) {
				response.setCod_rpta(Constantes.UNO);
				response.setRpta("Correo enviado");
			} else {
				response.setCod_rpta(Constantes.CERO);
				response.setRpta("Correo no enviado");
			}
		} catch (Exception e) {
			response.setCod_rpta(Constantes.CERO);
			response.setRpta(e.getMessage());
			logger.error("Error al enviar correo -->" + e);
		}

		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/generarCotizacionPdf")
	public ResponseEntity<Resource> exportarReporte(@RequestBody RequestTO<RequestDetalleCotizacionTO> request) {
		try {
			CotizacionTO cabecera = request.getFormulario().getCabecera();
			List<PosicionPedidoTO> posiciones = request.getFormulario().getPosiciones();
			return this.pdfService.generarCotizacionPdf(cabecera, posiciones, request.getUsuario());
		} catch (Exception e) {
			logger.error("Error en generarCotizacionPdf", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
