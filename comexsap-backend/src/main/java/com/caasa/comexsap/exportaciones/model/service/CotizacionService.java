package com.caasa.comexsap.exportaciones.model.service;

import java.util.List;

import org.springframework.core.io.Resource;

import com.caasa.comexsap.exportaciones.model.to.CotizacionTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroCotizacionRequestTO;
import com.caasa.comexsap.exportaciones.model.to.PedidoTO;
import com.caasa.comexsap.exportaciones.model.to.PosicionPedidoTO;
import com.caasa.comexsap.exportaciones.model.to.RequestCotizacionTO;
import com.caasa.comexsap.exportaciones.model.to.RequestDetalleCotizacionTO;

public interface CotizacionService {

	public List<CotizacionTO> listarCotizacionxFiltro(FiltroCotizacionRequestTO request);

	public int obtenerPedido(int codigoPedido);

	public void anularPedido(int codigoPedido, String usuario);

	public List<CotizacionTO> listarCotizacionxPedido(RequestCotizacionTO request);

	public List<PosicionPedidoTO> listarPosicionxPedido(RequestCotizacionTO request);

	public int obtenerPedidoxCodigo(String codigoNuevoPedido, int tipoSolicitud);

	public int registrarCotizacion(CotizacionTO cotizacion, String usuario, String codigoNuevoPedid,
			int estadoDocumento);

	public void registrarPosicionPedido(List<PosicionPedidoTO> posiciones, String usuario, int idPedido, int moneda);

	public void confirmarPedido(int codigoPedido, String usuario, int estadoDocumento);

	public int obtenerEstadoDocumentoxPedido(int codigoPedido, int estadoDocumento);

	public void actualizarCotizacion(RequestDetalleCotizacionTO cotizacion, String usuario);

	public int obtenerTipoSolicitudxPedido(int codigoPedido, String tipoSolicitud);

	public int registrarPedidoConfirmado(CotizacionTO cotizacion, String usuario, String codigoNuevoPedido,
			int estadoDocumento);

	public int registrarPosPedidoConfirmado(List<PosicionPedidoTO> posiciones, String usuario, int idPedido, int idMoneda);

	public boolean enviarCorreoCotizacionProforma(CotizacionTO cotizacion, Resource resource);

	public PedidoTO obtenerPedidoxIdPedido(int codigoPedido, int estadoDocumento);
}
