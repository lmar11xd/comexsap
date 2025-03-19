package com.caasa.comexsap.exportaciones.model.service;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.EtiquetaTO;

public interface EtiquetaService {

	public List<EtiquetaTO> listarEtiquetas(String buscar);
	public void guardarEtiqueta(EtiquetaTO etiqueta, String usuario);
	public void eliminarEtiqueta(int id, String usuario);

}
