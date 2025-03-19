package com.caasa.comexsap.exportaciones.model.dao;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.DocumentoControlGastoTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroControlGastosTO;
import com.caasa.comexsap.exportaciones.model.to.RequestControlGastosTO;

public interface ControlGastosDao {
	public List<DocumentoControlGastoTO> listarDocumentosxFiltro(FiltroControlGastosTO filtro);

	public void guardarControlGastos(RequestControlGastosTO formulario, String usuario);
}
