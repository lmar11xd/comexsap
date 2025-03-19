package com.caasa.comexsap.exportaciones.model.dao;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.FiltroPedidoIntercompany;
import com.caasa.comexsap.exportaciones.model.to.ExportacionIntercompanyPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionMaritimoTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionPedidoPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroExportacionPedidoPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.PedidoIntercompanyTO;

public interface PedidoIntercompanyDao {

	public List<PedidoIntercompanyTO> listarPedidoIntercompanySapxFiltro(FiltroPedidoIntercompany filtro);
	public List<ExportacionIntercompanyPosicionTO> listarPosicionxIdExportacion(int idExportacion);
	public void guardarFacturaSap(ExportacionMaritimoTO documento, List<ExportacionIntercompanyPosicionTO> posiciones, String pedidoSap);
	public List<ExportacionPedidoPosicionTO> listarPedidosIntercompanySapDisponibles(FiltroExportacionPedidoPosicionTO filtro);
}
