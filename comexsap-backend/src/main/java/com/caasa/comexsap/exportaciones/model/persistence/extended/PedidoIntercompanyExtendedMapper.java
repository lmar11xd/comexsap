package com.caasa.comexsap.exportaciones.model.persistence.extended;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.caasa.comexsap.exportaciones.model.to.FiltroPedidoIntercompany;
import com.caasa.comexsap.exportaciones.model.to.ExportacionIntercompanyPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionPedidoPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroExportacionPedidoPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.PedidoIntercompanyTO;

public interface PedidoIntercompanyExtendedMapper {

	public List<PedidoIntercompanyTO> listarPedidoIntercompanySapxFiltro(FiltroPedidoIntercompany filtro);
	public List<ExportacionIntercompanyPosicionTO> listarPosicionxIdExportacion(@Param("idExportacion") int idExportacion);
	public List<ExportacionPedidoPosicionTO> listarPedidosIntercompanySapDisponibles(FiltroExportacionPedidoPosicionTO filtro);
}
