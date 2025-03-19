package com.caasa.comexsap.exportaciones.model.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.caasa.comexsap.exportaciones.model.dao.CotizacionDao;
import com.caasa.comexsap.exportaciones.model.service.CotizacionService;
import com.caasa.comexsap.exportaciones.model.service.MailService;
import com.caasa.comexsap.exportaciones.model.service.ThymeleafService;
import com.caasa.comexsap.exportaciones.model.to.CotizacionTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroCotizacionRequestTO;
import com.caasa.comexsap.exportaciones.model.to.MailFileTO;
import com.caasa.comexsap.exportaciones.model.to.PedidoTO;
import com.caasa.comexsap.exportaciones.model.to.PosicionPedidoTO;
import com.caasa.comexsap.exportaciones.model.to.RequestCotizacionTO;
import com.caasa.comexsap.exportaciones.model.to.RequestDetalleCotizacionTO;
import com.caasa.comexsap.exportaciones.util.Constantes;

@Service
public class CotizacionServiceImpl implements CotizacionService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CotizacionDao cotizacionDao;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private ThymeleafService thymeleafService;

	@Override
	public List<CotizacionTO> listarCotizacionxFiltro(FiltroCotizacionRequestTO request) {
		return cotizacionDao.listarCotizacionxFiltro(request);
	}

	@Override
	public int obtenerPedido(int codigoPedido) {
		return cotizacionDao.obtenerPedido(codigoPedido);
	}

	@Override
	public void anularPedido(int idPedido, String usuario) {
		this.cotizacionDao.cambiarEstadoDocumento(idPedido, Constantes.ANULADO, usuario);
	}

	@Override
	public List<CotizacionTO> listarCotizacionxPedido(RequestCotizacionTO request) {
		return cotizacionDao.listarCotizacionxPedido(request);
	}

	@Override
	public List<PosicionPedidoTO> listarPosicionxPedido(RequestCotizacionTO request) {
		return cotizacionDao.listarPosicionxPedido(request);
	}

	@Override
	public int obtenerPedidoxCodigo(String codigoNuevoPedido, int tipoSolicitud) {
		return cotizacionDao.obtenerPedidoxCodigo(codigoNuevoPedido, tipoSolicitud);

	}

	@Override
	public int registrarCotizacion(CotizacionTO cotizacion, String usuario, String codigoNuevoPedido,
			int estadoDocumento) {
		return cotizacionDao.registrarCotizacion(cotizacion, usuario, codigoNuevoPedido, estadoDocumento);
	}

	@Override
	public void registrarPosicionPedido(List<PosicionPedidoTO> posiciones, String usuario, int idPedido, int moneda) {
		cotizacionDao.registrarPosicioPedido(posiciones, usuario, idPedido, moneda);
	}

	@Override
	public void confirmarPedido(int codigoPedido, String usuario, int estadoDocumento) {
		cotizacionDao.confirmarPedido(codigoPedido, usuario, estadoDocumento);
	}

	@Override
	public int obtenerEstadoDocumentoxPedido(int codigoPedido, int estadoDocumento) {
		return cotizacionDao.obtenerEstadoDocumentoxPedido(codigoPedido, estadoDocumento);
	}

	@Override
	public void actualizarCotizacion(RequestDetalleCotizacionTO cotizacion, String usuario) {
		cotizacionDao.actualizarCotizacion(cotizacion, usuario);
	}

	@Override
	public int obtenerTipoSolicitudxPedido(int codigoPedido, String tipoSolicitud) {
		return cotizacionDao.obtenerTipoSolicitudxPedido(codigoPedido, tipoSolicitud);
	}

	@Override
	public int registrarPedidoConfirmado(CotizacionTO cotizacion, String usuario, String codigoNuevoPedido,
			int estadoDocumento) {
		return cotizacionDao.registrarPedidoConfirmado(cotizacion, usuario, codigoNuevoPedido, estadoDocumento);
	}

	@Override
	public int registrarPosPedidoConfirmado(List<PosicionPedidoTO> posiciones, String usuario, int idPedido, int idMoneda) {
		return cotizacionDao.registrarPosPedidoConfirmado(posiciones, usuario, idPedido, idMoneda);
	}

	@Override
	public boolean enviarCorreoCotizacionProforma(CotizacionTO cotizacion, Resource resource) {
		String asunto = "";
		List<String> para = new ArrayList<>();
		List<String> copias = new ArrayList<>();
		
		if (cotizacion.isTipoDocumento()) {
			asunto = "Cotización CAASA N° " + cotizacion.getCodigoPedido();
		} else {
			asunto = "Proforma CAASA N° " + cotizacion.getCodigoPedido();
		}

		para.add(cotizacion.getCorreoContacto());
		
		if (cotizacion.getCorreoContactoAdicional() != null) {
			copias.add(cotizacion.getCorreoContactoAdicional());
		}

		try {
			String fileName = cotizacion.getCodigoPedido() + ".pdf";
			File temp = File.createTempFile("temp", ".tmp");
			IOUtils.copy(resource.getInputStream(), new FileOutputStream(temp));
			
			List<MailFileTO> adjuntos = new ArrayList<>();
			adjuntos.add(new MailFileTO(temp, fileName));
			
			String cuerpo = buildBodyEmailCotizacion(cotizacion);
			mailService.sendMailAsync(para, asunto, cuerpo, copias, adjuntos);
			
			return true;
		} catch (IOException e) {
			logger.error("enviarCorreoCotizacionProforma", e);
		} catch (Exception e) {
			logger.error("enviarCorreoCotizacionProforma", e);
		}

		return false;
	}

	public String buildBodyEmailCotizacion(CotizacionTO cotizacion) {
		String tipoDocumento = "proforma";
		if(cotizacion.isTipoDocumento()) {
			tipoDocumento = "cotización";
		}
		Map<String, Object> variables = new HashMap<>();
		variables.put("tipo_documento", tipoDocumento);
		variables.put("nombre_contacto", cotizacion.getNombreContacto());
		variables.put("cargo_contacto", cotizacion.getCargoContacto());
		variables.put("codigo_pedido", cotizacion.getCodigoPedido());
		return thymeleafService.createContent(Constantes.TEMPLATE_COTIZACION, variables);
	}

	@Override
	public PedidoTO obtenerPedidoxIdPedido(int codigoPedido, int estadoDocumento) {
		return this.cotizacionDao.obtenerPedidoxIdPedido(codigoPedido, estadoDocumento);
	}

}