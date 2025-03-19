package com.caasa.comexsap.exportaciones.model.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caasa.comexsap.exportaciones.model.dao.CotizacionDao;
import com.caasa.comexsap.exportaciones.model.dao.GrupoCorreoDetalleDao;
import com.caasa.comexsap.exportaciones.model.dao.PedidoFirmeDao;
import com.caasa.comexsap.exportaciones.model.service.MailService;
import com.caasa.comexsap.exportaciones.model.service.PedidoFirmeService;
import com.caasa.comexsap.exportaciones.model.service.ThymeleafService;
import com.caasa.comexsap.exportaciones.model.to.FiltroGrupoCorreoRequestTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroPedidoFirmeRequestTO;
import com.caasa.comexsap.exportaciones.model.to.GrupoCorreoDetalleTO;
import com.caasa.comexsap.exportaciones.model.to.MailPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.PedidoFirmePosicionTO;
import com.caasa.comexsap.exportaciones.model.to.PedidoFirmeSubPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.PedidoFirmeTO;
import com.caasa.comexsap.exportaciones.model.to.RequestPedidoFechasTO;
import com.caasa.comexsap.exportaciones.util.Constantes;

@Service
public class PedidoFirmeServiceImpl implements PedidoFirmeService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PedidoFirmeDao pedidoFirmeDao;
	
	@Autowired
	private CotizacionDao cotizacionDao;

	@Autowired
	private GrupoCorreoDetalleDao grupoCorreoDetalleDao;

	@Autowired
	private MailService mailService;

	@Autowired
	private ThymeleafService thymeleafService;

	@Override
	public List<PedidoFirmeTO> listarPedidoFirmexFiltro(FiltroPedidoFirmeRequestTO request) {
		return this.pedidoFirmeDao.listarPedidoFirmexFiltro(request);
	}

	@Override
	public List<PedidoFirmePosicionTO> listarPosicionxPedidoFirme(int idPedido) {
		return this.pedidoFirmeDao.listarPosicionxPedidoFirme(idPedido);
	}

	@Override
	public int registrarPedidoFirme(PedidoFirmeTO pedidos, String usuario, String codigoNuevoPedido,
			int estadoDocumento) {
		return this.pedidoFirmeDao.registrarPedidoFirme(pedidos, usuario, codigoNuevoPedido, estadoDocumento);
	}

	@Override
	public void actualizarPedidoFirme(PedidoFirmeTO pedidos, List<PedidoFirmePosicionTO> posiciones, String usuario) {
		this.pedidoFirmeDao.actualizarPedidoFirme(pedidos, posiciones, usuario);
	}

	@Override
	public PedidoFirmeTO obtenerPedidoFirmexIdPedido(int idPedido) {
		return this.pedidoFirmeDao.obtenerPedidoFirmexIdPedido(idPedido);
	}

	@Override
	public void registrarPosicionPedido(List<PedidoFirmePosicionTO> posiciones, String usuario, int idPedido, int moneda, int duplicar) {
		this.pedidoFirmeDao.registrarPosicionPedido(posiciones, usuario, idPedido, moneda, duplicar);
	}

	@Override
	public List<PedidoFirmeSubPosicionTO> listarSubPosicionPedidoFirme(int idPadre) {
		return this.pedidoFirmeDao.listarSubPosicionPedidoFirme(idPadre);

	}

	@Override
	public String verCorreoPedidoFirme(int idPedido, String msjLineaCredito) {
		PedidoFirmeTO pedido = obtenerPedidoFirmexIdPedido(idPedido);
		List<PedidoFirmePosicionTO> posiciones = listarPosicionxPedidoFirme(idPedido);
		pedido.setMsjLineaCredito(msjLineaCredito);
		return buildBodyEmailCrearPedidoFirme(pedido, posiciones);
	}

	@Override
	public int enviarCorreoPedidoFirme(int idPedido) {
		try {
			PedidoFirmeTO pedido = obtenerPedidoFirmexIdPedido(idPedido);
			List<PedidoFirmePosicionTO> posiciones = listarPosicionxPedidoFirme(idPedido);
			String subject = "PEDIDO FIRME " + pedido.getCodigoPedido();
			String body = buildBodyEmailCrearPedidoFirme(pedido, posiciones);
			
			String[] idgrupos = { 
					"" + Constantes.ID_GRUPO_PEDIDOFIRME_PCP,
					"" + Constantes.ID_GRUPO_PEDIDOFIRME_PRODUCCION,
					"" + Constantes.ID_GRUPO_PEDIDOFIRME_SUMINISTRO,
					"" + Constantes.ID_GRUPO_PEDIDOFIRME_PLANEAMIENTO,
					"" + Constantes.ID_GRUPO_PEDIDOFIRME_COMERCIAL,
					"" + Constantes.ID_GRUPO_PEDIDOFIRME_PLANEAMIENTO_COMERCIAL,
					"" + Constantes.ID_GRUPO_PEDIDOFIRME_COMEX,
					"" + Constantes.ID_GRUPO_PEDIDOFIRME_PRECIOS
			};
			
			FiltroGrupoCorreoRequestTO filtroCorreos = new FiltroGrupoCorreoRequestTO();
			filtroCorreos.setGrupos(idgrupos);
			
			List<GrupoCorreoDetalleTO> grupos = grupoCorreoDetalleDao.listarGrupoCorreoxFiltro(filtroCorreos);
			List<String> to = new ArrayList<String>();
			for (GrupoCorreoDetalleTO grupo : grupos) {
				to.add(grupo.getCorreo());
			}
			mailService.sendMailAsync(to, subject, body, null, null);
		} catch (Exception ex) {
			logger.error("enviarCorreoPedidoFirme", ex);
			return 0;
		}
		return 1;
	}

	public int enviarCorreoActualizacionPrecios(int idPedido) {
		try {
			PedidoFirmeTO pedido = obtenerPedidoFirmexIdPedido(idPedido);
			List<PedidoFirmePosicionTO> posiciones = listarPosicionxPedidoFirme(idPedido);
			String subject = "PEDIDO FIRME " + pedido.getCodigoPedido() + " - ACTUALIZACIÓN DE PRECIOS";
			String body = buildBodyEmailActualizacionPrecios(pedido, posiciones);
			
			String[] idgrupos = { "" + Constantes.ID_GRUPO_PEDIDOFIRME_PRECIOS };
			
			FiltroGrupoCorreoRequestTO filtroCorreos = new FiltroGrupoCorreoRequestTO();
			filtroCorreos.setGrupos(idgrupos);
			
			List<GrupoCorreoDetalleTO> grupos = grupoCorreoDetalleDao.listarGrupoCorreoxFiltro(filtroCorreos);
			List<String> to = new ArrayList<String>();
			for (GrupoCorreoDetalleTO grupo : grupos) {
				to.add(grupo.getCorreo());
			}
			mailService.sendMailAsync(to, subject, body, null, null);
			return 1;
		} catch (Exception ex) {
			logger.error("enviarCorreoActualizacionPrecios", ex);
			throw new RuntimeException(ex);
		}
	}

	public int enviarCorreoFechasDisponibilidad(int idPedido) {
		try {
			PedidoFirmeTO pedido = obtenerPedidoFirmexIdPedido(idPedido);
			List<PedidoFirmePosicionTO> posiciones = listarPosicionxPedidoFirme(idPedido);
			String subject = "PEDIDO FIRME " + pedido.getCodigoPedido() + " - FECHAS DE DISPONIBILIDAD";
			if(posiciones != null) {
				for (PedidoFirmePosicionTO posicion : posiciones) {
					if(posicion.getFechaDisponibilidad() == null) {
						throw new Exception("Material <<" + posicion.getCodigoSAP() + ">> no tiene fecha de disponibilidad.");
					}
				}
			}
			
			String body = buildBodyEmailFechasDisponibilidad(pedido, posiciones);
			String[] idgrupos = { 
					"" + Constantes.ID_GRUPO_PEDIDOFIRME_PCP,
					"" + Constantes.ID_GRUPO_PEDIDOFIRME_PRODUCCION,
					"" + Constantes.ID_GRUPO_PEDIDOFIRME_SUMINISTRO,
					"" + Constantes.ID_GRUPO_PEDIDOFIRME_PLANEAMIENTO,
					"" + Constantes.ID_GRUPO_PEDIDOFIRME_COMERCIAL,
					"" + Constantes.ID_GRUPO_PEDIDOFIRME_PLANEAMIENTO_COMERCIAL,
					"" + Constantes.ID_GRUPO_PEDIDOFIRME_COMEX,
					"" + Constantes.ID_GRUPO_PEDIDOFIRME_PRECIOS
			};
			
			FiltroGrupoCorreoRequestTO filtroCorreos = new FiltroGrupoCorreoRequestTO();
			filtroCorreos.setGrupos(idgrupos);
			
			List<GrupoCorreoDetalleTO> grupos = grupoCorreoDetalleDao.listarGrupoCorreoxFiltro(filtroCorreos);
			List<String> to = new ArrayList<String>();
			for (GrupoCorreoDetalleTO grupo : grupos) {
				to.add(grupo.getCorreo());
			}
			mailService.sendMailAsync(to, subject, body, null, null);
			return 1;
		} catch (Exception ex) {
			logger.error("enviarCorreoFechasDisponibilidad", ex);
			throw new RuntimeException(ex);
		}
	}

	public int enviarCorreoPedidoFirmeConfirmado(int idPedido) {
		try {
			PedidoFirmeTO pedido = obtenerPedidoFirmexIdPedido(idPedido);
			List<PedidoFirmePosicionTO> posiciones = listarPosicionxPedidoFirme(idPedido);
			String subject = "PEDIDO FIRME " + pedido.getCodigoPedido() + " - CONFIRMADO";
			String body = buildBodyEmailCrearPedidoFirme(pedido, posiciones);
			
			String[] idgrupos = { 
					"" + Constantes.ID_GRUPO_PEDIDOFIRME_PCP,
					"" + Constantes.ID_GRUPO_PEDIDOFIRME_PRODUCCION,
					"" + Constantes.ID_GRUPO_PEDIDOFIRME_SUMINISTRO,
					"" + Constantes.ID_GRUPO_PEDIDOFIRME_PLANEAMIENTO,
					"" + Constantes.ID_GRUPO_PEDIDOFIRME_COMERCIAL,
					"" + Constantes.ID_GRUPO_PEDIDOFIRME_PLANEAMIENTO_COMERCIAL,
					"" + Constantes.ID_GRUPO_PEDIDOFIRME_COMEX,
					"" + Constantes.ID_GRUPO_PEDIDOFIRME_PRECIOS
			};
			
			FiltroGrupoCorreoRequestTO filtroCorreos = new FiltroGrupoCorreoRequestTO();
			filtroCorreos.setGrupos(idgrupos);
			
			List<GrupoCorreoDetalleTO> grupos = grupoCorreoDetalleDao.listarGrupoCorreoxFiltro(filtroCorreos);
			List<String> to = new ArrayList<String>();
			for (GrupoCorreoDetalleTO grupo : grupos) {
				to.add(grupo.getCorreo());
			}
			mailService.sendMailAsync(to, subject, body, null, null);
			return 1;
		} catch (Exception ex) {
			logger.error("enviarCorreoPedidoFirmeConfirmado", ex);
			throw new RuntimeException(ex);
		}
	}

	private String buildBodyEmailCrearPedidoFirme(PedidoFirmeTO pedido, List<PedidoFirmePosicionTO> posiciones) {
		String codigoDestinatario = "", nombreDestinatario = "", nombrePuertoDestino = "", incoterm = "", despacho = "",
				verificacionCredito = "";

		if (pedido.getCodigoDestinatario() != null) {
			codigoDestinatario = pedido.getCodigoDestinatario().trim();
		}
		if (pedido.getNombreDestinatario() != null) {
			nombreDestinatario = pedido.getNombreDestinatario().trim();
		}
		if (pedido.getNombrePuertoDestino() != null) {
			nombrePuertoDestino = pedido.getNombrePuertoDestino();
		}
		if (pedido.getNombreIncotermComercial() != null) {
			incoterm = pedido.getCodigoIncotermComercial() + " - " + pedido.getNombreIncotermComercial();
		}
		if (pedido.getNombreDespacho() != null) {
			despacho = pedido.getNombreDespacho();
		}

		verificacionCredito = pedido.getMsjLineaCredito();

		String observacion = "";
		if(pedido.getObservacion() != null) {
			observacion = pedido.getObservacion().replace("\n", "<br/>");
		}
		
		Map<String, Object> variables = new HashMap<>();
		variables.put("codigo_pedido", pedido.getCodigoPedido());
		variables.put("codigo_cliente", pedido.getCodigoCliente());
		variables.put("nombre_cliente", pedido.getNombreCliente());
		variables.put("codigo_destinatario", codigoDestinatario);
		variables.put("nombre_destinatario", nombreDestinatario);
		variables.put("incoterm", incoterm);
		variables.put("puerto_origen", pedido.getNombrePuertoOrigen());
		variables.put("puerto_destino", nombrePuertoDestino);
		variables.put("lugar_despacho", pedido.getNombreLugarDespacho());
		variables.put("nombre_facturacion", pedido.getNombreFacturacion());
		variables.put("tipo_despacho", despacho);
		variables.put("verificacion_credito", verificacionCredito);
		variables.put("observaciones", observacion);
		getEmailPosiciones(posiciones, variables);
		return thymeleafService.createContent(Constantes.TEMPLATE_PEDIDO_FIRME, variables);
	}

	private String buildBodyEmailActualizacionPrecios(PedidoFirmeTO pedido, List<PedidoFirmePosicionTO> posiciones) {
		String fechaListaPrecio = "", listaPrecio = "", incoterm = "";
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

		if (pedido.getFechaListaPrecio() != null) {
			fechaListaPrecio = formatoFecha.format(pedido.getFechaListaPrecio());
		}
		if (pedido.getNombreListaPrecio() != null) {
			listaPrecio = pedido.getCodigoListaPrecio() + " - " + pedido.getNombreListaPrecio().trim();
		}
		if (pedido.getNombreIncotermComercial() != null) {
			incoterm = pedido.getCodigoIncotermComercial() + " - " + pedido.getNombreIncotermComercial();
		}

		Map<String, Object> variables = new HashMap<>();
		variables.put("codigo_pedido", pedido.getCodigoPedido());
		variables.put("codigo_cliente", pedido.getCodigoCliente());
		variables.put("nombre_cliente", pedido.getNombreCliente());
		variables.put("incoterm", incoterm);
		variables.put("moneda", pedido.getCodigoMoneda());
		variables.put("fecha_lista_precio", fechaListaPrecio);
		variables.put("lista_precio", listaPrecio);
		variables.put("observaciones", pedido.getObservacion());
		getEmailPosiciones(posiciones, variables);
		return thymeleafService.createContent(Constantes.TEMPLATE_ACTUALIZACION_PRECIOS, variables);
	}

	private String buildBodyEmailFechasDisponibilidad(PedidoFirmeTO pedido, List<PedidoFirmePosicionTO> posiciones) {
		String codigoDestinatario = "", nombreDestinatario = "", nombrePuertoDestino = "", incoterm = "", despacho = "",
				verificacionCredito = "";

		if (pedido.getCodigoDestinatario() != null) {
			codigoDestinatario = pedido.getCodigoDestinatario().trim();
		}
		if (pedido.getNombreDestinatario() != null) {
			nombreDestinatario = pedido.getNombreDestinatario().trim();
		}
		if (pedido.getNombrePuertoDestino() != null) {
			nombrePuertoDestino = pedido.getNombrePuertoDestino();
		}
		if (pedido.getNombreIncotermComercial() != null) {
			incoterm = pedido.getCodigoIncotermComercial() + " - " + pedido.getNombreIncotermComercial();
		}
		if (pedido.getNombreDespacho() != null) {
			despacho = pedido.getNombreDespacho();
		}
		
		verificacionCredito = pedido.getMsjLineaCredito();

		Map<String, Object> variables = new HashMap<>();
		variables.put("codigo_pedido", pedido.getCodigoPedido());
		variables.put("codigo_cliente", pedido.getCodigoCliente());
		variables.put("nombre_cliente", pedido.getNombreCliente());
		variables.put("codigo_destinatario", codigoDestinatario);
		variables.put("nombre_destinatario", nombreDestinatario);
		variables.put("incoterm", incoterm);
		variables.put("puerto_origen", pedido.getNombrePuertoOrigen());
		variables.put("puerto_destino", nombrePuertoDestino);
		variables.put("lugar_despacho", pedido.getNombreLugarDespacho());
		variables.put("nombre_facturacion", pedido.getNombreFacturacion());
		variables.put("tipo_despacho", despacho);
		variables.put("verificacion_credito", verificacionCredito);
		variables.put("observaciones", pedido.getObservacion());
		getEmailPosiciones(posiciones, variables);
		return thymeleafService.createContent(Constantes.TEMPLATE_FECHAS_DISPONIBILIDAD, variables);
	}

	private Map<String, Object> getEmailPosiciones(List<PedidoFirmePosicionTO> posiciones,
			Map<String, Object> variables) {
		int indice = 0;
		float cantidadTotal = 0, pesoTotal = 0;
		SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

		List<MailPosicionTO> lstPosiciones = new ArrayList<>();
		List<MailPosicionTO> lstComponentes = new ArrayList<>();

		if (posiciones != null && posiciones.size() > 0) {
			for (PedidoFirmePosicionTO posicion : posiciones) {
				indice++;
				cantidadTotal += posicion.getCantidadVenta().floatValue();
				pesoTotal += posicion.getPesoTonelada().floatValue();
				String fechaDisponibilidad = "";

				if (posicion.getFechaDisponibilidad() != null) {
					fechaDisponibilidad = formatoFecha.format(posicion.getFechaDisponibilidad());
				}

				MailPosicionTO mailPosicion = new MailPosicionTO();
				mailPosicion.setIndice("" + indice);
				mailPosicion.setCodigo(posicion.getCodigoSAP());
				mailPosicion.setDescripcion(posicion.getDescripcionProducto());
				mailPosicion.setCantidad("" + posicion.getCantidadVenta());
				mailPosicion.setUm(posicion.getCodigoSAPUnidadMedidaVenta());
				mailPosicion.setPeso("" + posicion.getPesoTonelada());
				mailPosicion.setFecha(fechaDisponibilidad);
				mailPosicion.setPrecioUnitarioSap("" + posicion.getPrecioUnitarioSAP());
				mailPosicion.setPrecioUnitario("" + posicion.getPrecioUnitario());
				lstPosiciones.add(mailPosicion);

				if (posicion.getSubPosicion() != null && posicion.getSubPosicion().size() > 0) {
					MailPosicionTO mailPosicionComp = new MailPosicionTO();
					mailPosicionComp.setIndice("" + indice);
					mailPosicionComp.setCodigo(posicion.getCodigoSAP());
					mailPosicionComp.setDescripcion(posicion.getDescripcionProducto());
					lstComponentes.add(mailPosicionComp);

					int subindice = 0;
					for (PedidoFirmeSubPosicionTO componente : posicion.getSubPosicion()) {
						subindice++;

						MailPosicionTO mailComponente = new MailPosicionTO();
						mailComponente.setIndice(indice + "." + subindice);
						mailComponente.setCodigo(componente.getCodigoSAP());
						mailComponente.setDescripcion(componente.getDescripcionProducto());
						mailComponente.setCantidad("" + componente.getCantidadVenta());
						mailComponente.setUm(componente.getCodigoSAPUnidadMedidaVenta());
						mailComponente.setPrecioUnitarioSap("" + posicion.getPrecioUnitarioSAP());
						mailComponente.setPrecioUnitario("" + componente.getPrecioUnitario());
						lstComponentes.add(mailComponente);
					}
				}
			}
		}

		variables.put("cantidad_total", cantidadTotal);
		variables.put("peso_total", pesoTotal);
		variables.put("posiciones", lstPosiciones);
		variables.put("componentes", lstComponentes);
		return variables;
	}

	@Override
	public void confirmarFechasDisponibilidad(RequestPedidoFechasTO formulario, String usuario) {
		this.pedidoFirmeDao.confirmarFechasDisponibilidad(formulario, usuario);
		this.enviarCorreoFechasDisponibilidad(formulario.getIdPedido());
	}

	@Override
	public int pedidoFirmeConExportacion(int idPedido) {
		return this.pedidoFirmeDao.pedidoFirmeConExportacion(idPedido);	
	}

	@Override
	public void anularPedido(int id, String usuario) {
		//Logica para verificar si ya tiene DocExp generado
		
		//Anular
		this.cotizacionDao.cambiarEstadoDocumento(id, Constantes.ANULADO, usuario);
	}
}