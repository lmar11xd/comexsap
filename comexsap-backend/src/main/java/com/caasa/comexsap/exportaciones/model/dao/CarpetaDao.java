package com.caasa.comexsap.exportaciones.model.dao;

import com.caasa.comexsap.exportaciones.model.domain.ComexstCarpeta;
import com.caasa.comexsap.exportaciones.model.domain.ComexstCarpetadocumento;
import com.caasa.comexsap.exportaciones.model.to.ArchivoTO;

public interface CarpetaDao {
	public int obtenerSecuencia(String nombreTabla);
	public int registrarCarpeta(String nombre, String usuario);
	public ComexstCarpeta obtenerCarpetaxId(int idCarpeta);
	public ComexstCarpetadocumento registrarCarpetaDocumento(String nombreArchivo, int idCarpeta , String usuario);
	public ArchivoTO obtenerArchivoxNombre(String nombreArchivo, int idCarpeta);
	public ArchivoTO obtenerArchivoxId(int id);
	public void eliminarArchivo(int id, String usuario);
}
