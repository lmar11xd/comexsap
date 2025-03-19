package com.caasa.comexsap.exportaciones.model.persistence.extended;

import com.caasa.comexsap.exportaciones.model.to.ArchivoTO;

public interface ArchivoExtendedMapper {
	public ArchivoTO obtenerArchivoxNombre(String nombreArchivo, int idCarpeta);
	public ArchivoTO obtenerArchivoxId(int id);
	public void eliminarArchivo(int id, String usuario);
}
