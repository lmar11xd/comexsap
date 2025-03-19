package com.caasa.comexsap.exportaciones.model.dao;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.EtiquetaTO;

public interface EtiquetaDao {
	public List<EtiquetaTO> listarEtiquetas(String buscar);
	
	public void guardarEtiqueta(EtiquetaTO etiquetaTO, String usuario);
	
	public void eliminarEtiqueta(int id, String usuario);
}
