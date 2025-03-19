package com.caasa.comexsap.exportaciones.model.dao;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.ComponenteTO;
import com.caasa.comexsap.exportaciones.model.to.EntregaSapTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionAgrupadoTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionEtiquetaTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionFacturaTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionMaritimoPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionMaritimoTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionOperacionTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionPaisTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionPedidoPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.FacturaSapTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroExportacionMaritimoRequestTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroExportacionPedidoPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.PedidoFirmePosicionTO;
import com.caasa.comexsap.exportaciones.model.to.RequestExportacionMaritimoTO;
import com.caasa.comexsap.exportaciones.soap.to.Cabecera;

public interface ExportacionMaritimoDao {

	public List<ExportacionMaritimoTO> listarExportacionMaritimoxFiltro(FiltroExportacionMaritimoRequestTO request);

	public List<ExportacionMaritimoPosicionTO> listarPosicionxIdExportacion(int idExportacion);

	public ExportacionMaritimoTO obtenerExportacionMaritimoxId(int idDocumento);

	public int buscarExportacionMaritimoxId(int id, int estadoDocumento);
	
	public int registrarExportacionMaritimo(RequestExportacionMaritimoTO request, String usuario);

	public int obtenerSecuencia(String nombreTabla);

	public void registrarExportacionPedido(List<ExportacionMaritimoPosicionTO> posicion, int idExportacion,
			String usuario);

	public void registrarExportacionPosicionPedido(ExportacionMaritimoPosicionTO posicionPedido,
			int idExportacionPedido, String usuario);

	public void registrarExportacionFactura(List<ExportacionFacturaTO> facturas, int idExportacion, String usuario);
	
	public void registrarExportacionEtiqueta(List<ExportacionEtiquetaTO> etiquetas, int idExportacion, String usuario);

	public void registrarPosicionPedido(List<PedidoFirmePosicionTO> posiciones, String usuario, int codigoNuevoPedido,
			int moneda);

	public List<ExportacionPedidoPosicionTO> listarPedidoPosicion(FiltroExportacionPedidoPosicionTO request);
	
	public List<ComponenteTO> listarComponentexIdPosicion(int idPosicion);

	public void actualizarExportacionMaritimo(RequestExportacionMaritimoTO request, String usuario);

	public int buscarPedidoPosicion(int idPedido, int idExportacion, int estadoDocumento);

	public ExportacionMaritimoPosicionTO obtenerPedidoPosicion(int idPedido, int idExportacion, int estadoDocumento);

	public void confirmarExportacionMaritimo(int id, String usuario, int estadoDocumento);

	public List<ExportacionMaritimoPosicionTO> obtenerExportacionPedido(int id);

	public void eliminarExportacionMaritimo(int id, String usuario);

	public void eliminarExportacionMaritimoPosicion(int idExportacion, int idPosicion, String usuario);

	public List<ExportacionFacturaTO> listarExportacionFacturaxIdExportacion(int idExportacion);
	
	public List<ExportacionOperacionTO> obtenerTotalPedidosExportacion();

	public EntregaSapTO obtenerEntregaSap(String pedidoSap);
	
	public FacturaSapTO obtenerFacturaSap(String pedidoSap);
	
	public void guardarFacturaSap(ExportacionMaritimoTO documento, List<ExportacionMaritimoPosicionTO> posiciones, String pedidoSap);

	public List<ExportacionAgrupadoTO> obtenerTotalExportacion();

	public List<ExportacionPaisTO> obtenerTotalExportacionPais();

	public List<ExportacionAgrupadoTO> obtenerMesesExportacion(String nombreMes,String numeroMes,String anio);
	
	public String obtenerNumeroPedidoSAP(Cabecera cabeceraDocumento);
	
	public void actualizarEntrega(int idExportacion, String pedidoSap);
	
	public List<ExportacionEtiquetaTO> listarExportacionEtiquetas(int idExportacion);
}
