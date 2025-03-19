package com.caasa.comexsap.exportaciones.model.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.caasa.comexsap.exportaciones.model.to.ArchivoTO;
import com.caasa.comexsap.exportaciones.model.to.CarpetaTO;

public interface ArchivoService {

	public void save(MultipartFile file);
	public Resource load(String filename);
	public void deleteAll();
	public Stream<Path> loadAll();
	public boolean deleteFile(String filename);
	
	public void createDirectory(String name);
	public void deleteDirectory(String name);
	
	public CarpetaTO guardarArchivosPedidoFirme(MultipartFile[] archivos, CarpetaTO carpetaInfo);
	public Stream<Path> obtenerArchivosxCarpeta(int idCarpeta);
	public ArchivoTO obtenerArchivoxNombre(String nombreArchivo, int idCarpeta);
	public Resource obtenerArchivo(String carpeta, String nombreArchivo);
	public boolean eliminarArchivoxId(int id, String usuario);
}
