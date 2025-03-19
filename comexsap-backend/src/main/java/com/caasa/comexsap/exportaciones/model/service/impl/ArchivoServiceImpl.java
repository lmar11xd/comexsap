package com.caasa.comexsap.exportaciones.model.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import com.caasa.comexsap.exportaciones.model.dao.CarpetaDao;
import com.caasa.comexsap.exportaciones.model.dao.CotizacionDao;
import com.caasa.comexsap.exportaciones.model.domain.ComexstCarpeta;
import com.caasa.comexsap.exportaciones.model.service.ArchivoService;
import com.caasa.comexsap.exportaciones.model.to.ArchivoTO;
import com.caasa.comexsap.exportaciones.model.to.CarpetaTO;
import com.caasa.comexsap.exportaciones.spring.CarpetaProperties;
import com.caasa.comexsap.exportaciones.util.Constantes;

@Service
public class ArchivoServiceImpl implements ArchivoService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CarpetaDao carpetaDao;
	
	@Autowired
	private CotizacionDao cotizacionDao;
	
	private Path root;
	
	public ArchivoServiceImpl(CarpetaProperties carpetaProperties) {
		try {
			root = Paths.get(carpetaProperties.getRutaCarpetaRaiz());
			//Files.createDirectories(root);
		} catch (Exception e) {
			logger.error("No se pudo obtener la ruta raíz");
		}
	}

	@Override
	public void save(MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
		} catch (IOException e) {
			logger.error("save", e);
			throw new RuntimeException("No se puede guardar el archivo");
		}
	}

	@Override
	public Resource load(String filename) {
		try {
			Path file = root.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			
			if(resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("No se puede leer el archivo");
			}
		} catch (MalformedURLException e) {
			logger.error("load", e);
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(root.toFile());
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.root, 1)
					.filter(path -> !path.equals(this.root))
					.map(this.root::relativize);
		} catch (RuntimeException | IOException e) {
			logger.error("loadAll", e);
			throw new RuntimeException("No se pueden cargar los archivos");
		}
	}

	@Override
	public boolean deleteFile(String filename) {
		try {
			boolean delete = Files.deleteIfExists(this.root.resolve(filename));
			return delete;
		} catch (IOException e) {
			logger.error("deleteFile", e);
			throw new RuntimeException("No se pueden eliminar el archivo");
		}
	}

	@Override
	public void createDirectory(String name) {
		try {
			Path directory = this.root.resolve(name);
			if(Files.notExists(directory)) {
				Files.createDirectories(directory);
			} else {
				throw new RuntimeException("La carpeta ya existe");
			}
		} catch (IOException e) {
			logger.error("createDirectory", e);
			throw new RuntimeException("No se pudo crear la carpeta");
		}
	}

	@Override
	public void deleteDirectory(String name) {
		try {
			Path directory = this.root.resolve(name);
			if(Files.exists(directory)) {
				Files.delete(directory);
			} else {
				throw new RuntimeException("Carpeta no encontrada");
			}
		} catch (IOException e) {
			logger.error("deleteDirectory", e);
			throw new RuntimeException("No se pudo eliminar la carpeta");
		}
	}

	@Override
	public CarpetaTO guardarArchivosPedidoFirme(MultipartFile[] archivos, CarpetaTO carpetaInfo) {
		ComexstCarpeta carpeta = null;
		try {
			if(carpetaInfo.getIdPedido() == 0) {
				int existeCodigo = cotizacionDao.obtenerPedidoxCodigo(carpetaInfo.getNombre(), Constantes.TIPO_SOLICITUD_PEDIDO);
				if(existeCodigo > 0) {
					throw new RuntimeException("Código pedido " + carpetaInfo.getNombre() + " ya existe");
				}
			}
			
			if (carpetaInfo.getId() == 0) {
				int id = carpetaDao.registrarCarpeta(carpetaInfo.getNombre(), carpetaInfo.getUsuario());
				carpetaInfo.setId(id);
			}
			
			Path directorio = this.root.resolve(carpetaInfo.getNombre());
			if(Files.notExists(directorio)) {
				Files.createDirectories(directorio);
			}
			
			carpeta = carpetaDao.obtenerCarpetaxId(carpetaInfo.getId());
			
			guardarArchivos(carpeta, archivos);
			
			CarpetaTO response = new CarpetaTO();
			response.setId(carpeta.getCarpnId());
			response.setNombre(carpeta.getCarpvNombre());
			return response;
		} catch (IOException e) {
			logger.error("guardarArchivosPedidoFirme", e);
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public void guardarArchivos(ComexstCarpeta carpeta, MultipartFile[] archivos) throws IOException {
		Arrays.asList(archivos).stream().forEach(file -> {
			try {
				ArchivoTO archivo = carpetaDao.obtenerArchivoxNombre(file.getOriginalFilename(), carpeta.getCarpnId());
				if(archivo == null) {
					carpetaDao.registrarCarpetaDocumento(file.getOriginalFilename(), carpeta.getCarpnId(), carpeta.getCarpvUsuariocreacion());
				}
				Path directorio = this.root.resolve(carpeta.getCarpvNombre() + "/" + file.getOriginalFilename());
				if(Files.notExists(directorio)) {
					Files.copy(file.getInputStream(), this.root.resolve(carpeta.getCarpvNombre() + "/" + file.getOriginalFilename()));
				}
			} catch (IOException e) {
				logger.error("guardarArchivos", e);
				throw new RuntimeException("No se pudo guardar el archivo");
			}
		});
	}
	
	@Override
	public Stream<Path> obtenerArchivosxCarpeta(int idCarpeta) {
		try {
			ComexstCarpeta carpeta = carpetaDao.obtenerCarpetaxId(idCarpeta);
			if(carpeta != null) {
				Path directorio = Paths.get(this.root.toString(), carpeta.getCarpvNombre());
				if(Files.exists(directorio)) {
					return Files.walk(directorio, 1)
							.filter(path -> !path.equals(directorio))
							.map(directorio::relativize);
				}
			}
			return null;
		} catch (RuntimeException | IOException e) {
			logger.error("obtenerArchivosxCarpeta", e);
			throw new RuntimeException("No se pueden cargar los archivos");
		}
	}

	@Override
	public ArchivoTO obtenerArchivoxNombre(String nombreArchivo, int idCarpeta) {
		return carpetaDao.obtenerArchivoxNombre(nombreArchivo, idCarpeta);
	}
	
	@Override
	public boolean eliminarArchivoxId(int id, String usuario) {
		try {
			ArchivoTO archivo = carpetaDao.obtenerArchivoxId(id);
			carpetaDao.eliminarArchivo(id, usuario);
			boolean deleted = Files.deleteIfExists(this.root.resolve(archivo.getCarpeta()+ "/" + archivo.getNombre()));
			return deleted;
		} catch (IOException e) {
			logger.error("eliminarArchivoxId", e);
			throw new RuntimeException("El archivo no se puede eliminar");
		}
	}

	@Override
	public Resource obtenerArchivo(String carpeta, String nombreArchivo) {
		try {
			Path file = root.resolve(carpeta + "/" + nombreArchivo);
			Resource resource = new UrlResource(file.toUri());
			
			if(resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("No se puede leer el archivo");
			}
		} catch (MalformedURLException e) {
			logger.error("obtenerArchivo", e);
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}
}
