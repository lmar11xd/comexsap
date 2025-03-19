package com.caasa.comexsap.exportaciones.model.dao;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.CotizacionTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroCotizacionRequestTO;
import com.caasa.comexsap.exportaciones.model.to.PedidoTO;
import com.caasa.comexsap.exportaciones.model.to.PosicionPedidoTO;
import com.caasa.comexsap.exportaciones.model.to.RequestCotizacionTO;
import com.caasa.comexsap.exportaciones.model.to.RequestDetalleCotizacionTO;

public interface CotizacionDao {

	public List<CotizacionTO> listarCotizacionxFiltro(FiltroCotizacionRequestTO request);	
	public int obtenerPedido(int codigoPedido); 
	public void eliminarPedido(int codigoPedido,String usuario);
	public List<CotizacionTO> listarCotizacionxPedido(RequestCotizacionTO request);	
	public CotizacionTO obtenerCotizacionxPedido(int idPedido);	
	public List<PosicionPedidoTO> listarPosicionxPedido(RequestCotizacionTO request);
	public List<PosicionPedidoTO> listarPosicionxPedido(int idPedido);	
	public int obtenerPedidoxCodigo(String codigoNuevoPedido , int tipoSolicitud); 
	public int registrarCotizacion(CotizacionTO cotizaciones,String usuario,String codigoNuevoPedido,int estadoDocumento);
	public void registrarPosicioPedido(List<PosicionPedidoTO> posiciones, String usuario,int codigoNuevoPedido,int moneda);
	public int obtenerSecuencia(String nombreTabla);
	public void confirmarPedido(int codigoPedido,String usuario,int estadoDocumento);
	public int obtenerEstadoDocumentoxPedido(int codigoPedido,int estadoDocumento); 
	public PedidoTO obtenerPedidoxIdPedido(int codigoPedido,int estadoDocumento); 
	public void actualizarCotizacion(RequestDetalleCotizacionTO cotizacion,String usuario);
	public int obtenerTipoSolicitudxPedido(int codigoPedido,String tipoSolicitud); 
	public int registrarPedidoConfirmado(CotizacionTO cotizacion, String usuario,String codigoNuevoPedido, int estadoDocumento);
	public int registrarPosPedidoConfirmado(List<PosicionPedidoTO> posiciones, String usuario,int idPedido, int idMoneda);
	public void cambiarEstadoDocumento(int idPedido, int estadoDocumento, String usuario); 
}
