package com.caasa.comexsap.exportaciones.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caasa.comexsap.exportaciones.model.dao.PedidoIntercompanyDao;
import com.caasa.comexsap.exportaciones.model.service.PedidoIntercompanyService;
import com.caasa.comexsap.exportaciones.model.to.FiltroPedidoIntercompany;
import com.caasa.comexsap.exportaciones.model.to.ExportacionIntercompanyPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionMaritimoTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionPedidoPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroExportacionPedidoPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.PedidoIntercompanyTO;

@Service
public class PedidoIntercompanyServiceImpl implements PedidoIntercompanyService {

	@Autowired
	private PedidoIntercompanyDao pedidoIntercompanyDao;
	
	@Override
	public List<PedidoIntercompanyTO> listarPedidoIntercompanySapxFiltro(FiltroPedidoIntercompany filtro) {
		return pedidoIntercompanyDao.listarPedidoIntercompanySapxFiltro(filtro);
	}

	@Override
	public List<ExportacionIntercompanyPosicionTO> listarPosicionxIdExportacion(int idExportacion) {
		return pedidoIntercompanyDao.listarPosicionxIdExportacion(idExportacion);
	}

	@Override
	public void guardarFacturaSap(ExportacionMaritimoTO documento, List<ExportacionIntercompanyPosicionTO> posiciones,
			String pedidoSap) {
		pedidoIntercompanyDao.guardarFacturaSap(documento, posiciones, pedidoSap);
	}

	@Override
	public List<ExportacionPedidoPosicionTO> listarPedidosIntercompanySapDisponibles(
			FiltroExportacionPedidoPosicionTO filtro) {
		return pedidoIntercompanyDao.listarPedidosIntercompanySapDisponibles(filtro);
	}

}
