package com.caasa.comexsap.exportaciones.model.service;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.FiltroPedidoFirmeRequestTO;
import com.caasa.comexsap.exportaciones.model.to.PedidoFirmePosicionTO;
import com.caasa.comexsap.exportaciones.model.to.PedidoFirmeSubPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.PedidoFirmeTO;
import com.caasa.comexsap.exportaciones.model.to.RequestPedidoFechasTO;

public interface PedidoFirmeService {
	
	public List<PedidoFirmeTO> listarPedidoFirmexFiltro(FiltroPedidoFirmeRequestTO request);
	public List<PedidoFirmePosicionTO> listarPosicionxPedidoFirme(int idPedido);
	public int registrarPedidoFirme(PedidoFirmeTO pedidos,String usuario,String codigoNuevoPedido,int estadoDocumento);
	public void actualizarPedidoFirme(PedidoFirmeTO pedidos,List<PedidoFirmePosicionTO> posiciones,String usuario);
	public PedidoFirmeTO obtenerPedidoFirmexIdPedido(int idPedido);	
	public void registrarPosicionPedido(List<PedidoFirmePosicionTO> posiciones, String usuario,int idPedido,int moneda,int duplicar);
	public List<PedidoFirmeSubPosicionTO> listarSubPosicionPedidoFirme(int idPadre);	
	public String verCorreoPedidoFirme(int idPedido, String msjLineaCredito);
	public int enviarCorreoPedidoFirme(int idPedido);
	public int enviarCorreoActualizacionPrecios(int idPedido);
	public int enviarCorreoFechasDisponibilidad(int idPedido);
	public int enviarCorreoPedidoFirmeConfirmado(int idPedido);
	public void confirmarFechasDisponibilidad(RequestPedidoFechasTO formulario, String usuario);
	public int pedidoFirmeConExportacion(int idPedido);
	public void anularPedido(int id, String usuario);
}
