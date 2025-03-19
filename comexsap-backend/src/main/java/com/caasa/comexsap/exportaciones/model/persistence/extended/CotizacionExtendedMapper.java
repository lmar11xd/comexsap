package com.caasa.comexsap.exportaciones.model.persistence.extended;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.caasa.comexsap.exportaciones.model.to.CotizacionTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroCotizacionRequestTO;
import com.caasa.comexsap.exportaciones.model.to.PedidoTO;
import com.caasa.comexsap.exportaciones.model.to.PosicionPedidoTO;
import com.caasa.comexsap.exportaciones.model.to.RequestCotizacionTO;
import com.caasa.comexsap.exportaciones.model.to.RequestDetalleCotizacionTO;

public interface CotizacionExtendedMapper {

	public List<CotizacionTO> listarCotizacionxFiltro(FiltroCotizacionRequestTO request);	
	public int obtenerPedido(@Param("codigoPedido") int codigoPedido); 
	public void eliminarPedido(@Param("codigoPedido") int codigoPedido,@Param("usuario") String usuario); 
	public List<CotizacionTO> listarCotizacionxPedido(RequestCotizacionTO request);
	public CotizacionTO listarCotizacionxPedido(int idPedido);
	public List<PosicionPedidoTO> listarPosicionxPedido(RequestCotizacionTO request);
	public List<PosicionPedidoTO> listarPosicionxPedido(int idPedido);
	public int obtenerPedidoxCodigo(@Param("codigoNuevoPedido") String codigoNuevoPedido,@Param("tipoSolicitud") int tipoSolicitud); 
	public void confirmarPedido(@Param("codigoPedido") int codigoPedido,@Param("usuario") String usuario,@Param("estadoDocumento") int estadoDocumento);
	public int obtenerEstadoDocumentoxPedido(@Param("codigoPedido") int codigoPedido,@Param("estadoDocumento") int estadoDocumento); 
	public void actualizarCotizacion(RequestDetalleCotizacionTO cotizacion);
	public int obtenerTipoSolicitudxPedido(@Param("codigoPedido") int codigoPedido,@Param("tipoSolicitud") String tipoSolicitud); 
	public List<PedidoTO> listarPedido(); 
	public PedidoTO obtenerPedidoxIdPedido(@Param("codigoPedido") int codigoPedido,@Param("estadoDocumento") int estadoDocumento);
	public void cambiarEstadoDocumento(@Param("idPedido") int idPedido, @Param("estadoDocumento") int estadoDocumento, @Param("usuario") String usuario);
}