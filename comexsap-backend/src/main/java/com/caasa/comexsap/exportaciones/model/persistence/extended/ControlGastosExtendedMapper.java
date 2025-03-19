package com.caasa.comexsap.exportaciones.model.persistence.extended;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.DocumentoControlGastoTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroControlGastosTO;

public interface ControlGastosExtendedMapper {
	public List<DocumentoControlGastoTO> listarDocumentosxFiltro(FiltroControlGastosTO filtro);
}
