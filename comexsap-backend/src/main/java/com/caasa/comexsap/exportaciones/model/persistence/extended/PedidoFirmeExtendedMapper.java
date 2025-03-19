package com.caasa.comexsap.exportaciones.model.persistence.extended;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.caasa.comexsap.exportaciones.model.to.FiltroPedidoFirmeRequestTO;
import com.caasa.comexsap.exportaciones.model.to.PedidoFirmePosicionTO;
import com.caasa.comexsap.exportaciones.model.to.PedidoFirmeSubPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.PedidoFirmeTO;

public interface PedidoFirmeExtendedMapper {

	public List<PedidoFirmeTO> listarPedidoFirmexFiltro(FiltroPedidoFirmeRequestTO request);
	public List<PedidoFirmePosicionTO> listarPosicionxPedidoFirme(@Param("idPedido") int idPedido);
	public PedidoFirmeTO obtenerPedidoFirmexIdPedido(@Param("idPedido") int idPedido);
	public List<PedidoFirmeSubPosicionTO> listarSubPosicionPedidoFirme(@Param("idPadre") int idPadre);
	public int pedidoFirmeConExportacion(@Param("idPedido") int idPedido);

}