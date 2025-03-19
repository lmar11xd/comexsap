package com.caasa.comexsap.exportaciones.model.service;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.DocumentoAgenteAduanaTO;

public interface DocumentoAgenteAduanaService {
	
	public void registrarDocumentoAgenteAduana(List<DocumentoAgenteAduanaTO> posiciones, String usuario);

}
