package com.caasa.comexsap.exportaciones.model.persistence.extended;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.caasa.comexsap.exportaciones.model.to.ComponenteTO;
import com.caasa.comexsap.exportaciones.model.to.EntregaSapTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionFacturaTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionMaritimoPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionMaritimoTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionPedidoPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.FacturaSapTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroExportacionMaritimoRequestTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroExportacionPedidoPosicionTO;
import com.caasa.comexsap.exportaciones.soap.to.Cabecera;

public interface ExportacionMaritimoExtendedMapper {

	public List<ExportacionMaritimoTO> listarExportacionMaritimoxFiltro(FiltroExportacionMaritimoRequestTO request);
	public List<ExportacionMaritimoPosicionTO> listarPosicionxIdExportacion(@Param("idExportacion")int idExportacion);
	public ExportacionMaritimoTO obtenerExportacionMaritimoxId(@Param("idDocumento") int idDocumento);	
	public int buscarExportacionMaritimoxId(@Param("id")int id,@Param("estadoDocumento")int estadoDocumento); 
	public void actualizarCodigoPacking(@Param("id") int id,@Param("codigoPacking")String codigoPacking); 
	public List<ExportacionPedidoPosicionTO> listarPedidoPosicion(FiltroExportacionPedidoPosicionTO request);
	public int buscarPedidoPosicion(@Param("idPedido")int idPedido,@Param("idExportacion")int idExportacion ,@Param("estadoDocumento") int estadoDocumento); 
	public ExportacionMaritimoPosicionTO obtenerPedidoPosicion(@Param("idPedido")int idPedido,@Param("idExportacion")int idExportacion ,@Param("estadoDocumento") int estadoDocumento); 
	public void confirmarExportacionMaritimo(@Param("id") int id,@Param("usuario") String usuario,int estadoDocumento);
	public List<ExportacionMaritimoPosicionTO> obtenerExportacionPedido(@Param("idExportacion") int id);	
	public void eliminarExportacionMaritimo(@Param("id")int id,@Param("usuario") String usuario);
	public void eliminarExportacionMaritimoPosicion(@Param("idExportacion")int idExportacion,@Param("idPosicion")int idPosicion,@Param("usuario")String usuario);
	public List<ExportacionFacturaTO> listarExportacionFacturaxIdExportacion(@Param("idExportacion")int idExportacion);
	public List<ComponenteTO> listarComponentexIdPosicion(@Param("idPosicion") int idPosicion);
	public int obtenerTotalPedidosEnviados(@Param("anio") String anio);
	public int obtenerTotalPedidosTerrestresEnviados(@Param("anio") String anio);
	public int obtenerPedidoFirmeConfirmado(@Param("anio") String anio);
	public int obtenerTotalExportacion(@Param("tipoTransporte") int tipoTransporte,@Param("idDespacho") int estadoDocumento,@Param("mes") String mes
			,@Param("anio") String anio);
	public List<ExportacionMaritimoTO> listarPuertoDestino(@Param("estadoDocumento") int estadoDocumento,@Param("anio") String anio);
	public List<ExportacionMaritimoTO> obtenerPaisExportacion(@Param("estadoDocumento") int estadoDocumento,@Param("anio") String anio);
	public EntregaSapTO obtenerEntregaSap(@Param("pedidoSap") String pedidoSap);
	public FacturaSapTO obtenerFacturaSap(@Param("pedidoSap") String pedidoSap);
	public String obtenerNumeroPedidoSAP(Cabecera cabeceraDocumento);
	public void eliminarExportacionFactura(@Param("idExportacion")int idExportacion, @Param("usuario") String usuario);
}