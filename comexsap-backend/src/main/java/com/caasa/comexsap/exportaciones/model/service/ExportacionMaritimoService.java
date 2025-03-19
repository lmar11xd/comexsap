package com.caasa.comexsap.exportaciones.model.service;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.response.ConsultarStockResponse;
import com.caasa.comexsap.exportaciones.model.response.PedidoExportacionResponse;
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

public interface ExportacionMaritimoService {

	public List<ExportacionMaritimoTO> listarExportacionMaritimoxFiltro(FiltroExportacionMaritimoRequestTO request);

	public List<ExportacionMaritimoPosicionTO> listarPosicionxIdExportacion(int idExportacion);

	public ExportacionMaritimoTO obtenerExportacionMaritimoxId(int idDocumento);

	public int buscarExportacionMaritimoxId(int id, int estadoDocumento);
	
	public int registrarExportacionMaritimo(RequestExportacionMaritimoTO request, String usuario);

	public List<ExportacionPedidoPosicionTO> listarPedidoPosicion(FiltroExportacionPedidoPosicionTO request);

	public void actualizarExportacionMaritimo(RequestExportacionMaritimoTO request, String usuario);

	public void confirmarExportacionMaritimo(int id, String usuario, int estadoDocumento);

	public List<ExportacionMaritimoPosicionTO> obtenerExportacionPedido(int id);

	public void eliminarExportacionMaritimo(int id, String usuario);

	public void eliminarExportacionMaritimoPosicion(int idExportacion, int idPosicion, String usuario);

	public PedidoExportacionResponse crearPedidoSap(FiltroDTCreaPedidosReqTO filtro) throws Exception;
	public PedidoExportacionResponse modificarPedidoSap(FiltroDTCreaPedidosReqTO filtro) throws Exception;

	public List<ExportacionFacturaTO> listarExportacionFacturaxIdExportacion(int idExportacion);
	
	public ConsultarStockResponse consultarStockPedido(FiltroDTConsultarStockReqTO filtro) throws Exception;

	public List<ComponenteTO> listarComponentexIdPosicion(int idPosicion);
	
	public List<ExportacionOperacionTO> obtenerTotalPedidosExportacion();

	public List<ExportacionAgrupadoTO> obtenerTotalExportacion();

	public List<ExportacionPaisTO> obtenerTotalExportacionPais();
	
	public void guardarFacturaSap(ExportacionMaritimoTO documento, List<ExportacionMaritimoPosicionTO> posiciones, String pedidoSap);
	
	public void actualizarEntrega(int idExportacion, String pedidoSap);
	
	public List<ExportacionEtiquetaTO> listarExportacionEtiquetas(int idExportacion);
}
