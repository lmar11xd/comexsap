package com.caasa.comexsap.exportaciones.model.dao;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.DocumentoAgenteAduanaTO;

public interface DocumentoAgenteAduanaDao {
	public int obtenerSecuencia(String nombreTabla);
	public void registrarDocumentoAgenteAduana(List<DocumentoAgenteAduanaTO> posiciones, String usuario);
}
