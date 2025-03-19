package com.caasa.comexsap.exportaciones.model.dao;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.FiltroPedidoFirmeRequestTO;
import com.caasa.comexsap.exportaciones.model.to.PedidoFirmePosicionTO;
import com.caasa.comexsap.exportaciones.model.to.PedidoFirmeSubPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.PedidoFirmeTO;
import com.caasa.comexsap.exportaciones.model.to.RequestPedidoFechasTO;

public interface PedidoFirmeDao {

	public List<PedidoFirmeTO> listarPedidoFirmexFiltro(FiltroPedidoFirmeRequestTO request);
	public List<PedidoFirmePosicionTO> listarPosicionxPedidoFirme(int idPedido);
	public int registrarPedidoFirme(PedidoFirmeTO pedido, String usuario, String codigoNuevoPedido,
			int estadoDocumento);
	public int obtenerSecuencia(String nombreTabla);
	public void actualizarPedidoFirme(PedidoFirmeTO pedido, List<PedidoFirmePosicionTO> posiciones, String usuario);
	public PedidoFirmeTO obtenerPedidoFirmexIdPedido(int idPedido);	
	public void registrarPosicionPedido(List<PedidoFirmePosicionTO> posiciones, String usuario,int idPedido,int moneda, int duplicar);
	public List<PedidoFirmeSubPosicionTO> listarSubPosicionPedidoFirme(int idPadre);
	public void confirmarFechasDisponibilidad(RequestPedidoFechasTO formulario, String usuario);
	public int pedidoFirmeConExportacion(int idPedido);	

}
