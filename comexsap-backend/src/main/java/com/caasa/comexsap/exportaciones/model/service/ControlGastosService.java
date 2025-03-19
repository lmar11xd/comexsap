package com.caasa.comexsap.exportaciones.model.service;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.DocumentoControlGastoTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroControlGastosTO;
import com.caasa.comexsap.exportaciones.model.to.RequestControlGastosTO;

public interface ControlGastosService {

	public List<DocumentoControlGastoTO> listarDocumentosxFiltro(FiltroControlGastosTO filtro);

	public void guardarControlGastos(RequestControlGastosTO formulario, String usuario);
}
