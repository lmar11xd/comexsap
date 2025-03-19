package com.caasa.comexsap.exportaciones.model.dao.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.caasa.comexsap.exportaciones.enums.AplicacionEnum;
import com.caasa.comexsap.exportaciones.enums.TablasAplicativoEnum;
import com.caasa.comexsap.exportaciones.model.dao.CarpetaDao;
import com.caasa.comexsap.exportaciones.model.domain.ComexstCarpeta;
import com.caasa.comexsap.exportaciones.model.domain.ComexstCarpetadocumento;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstCarpetaMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstCarpetadocumentoMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.ArchivoExtendedMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.TablaSecuenciaExtendedMapper;
import com.caasa.comexsap.exportaciones.model.to.ArchivoTO;
import com.caasa.comexsap.exportaciones.util.Constantes;

@Repository("CarpetaDao")
public class CarpetaDaoImpl implements CarpetaDao {
	@Autowired
	private TablaSecuenciaExtendedMapper tablaSecuenciaExtendedMapper;
	
	@Autowired
	private ComexstCarpetaMapper comexstCarpetaMapper;
	
	@Autowired
	private ComexstCarpetadocumentoMapper comexstCarpetadocumentoMapper;
	
	@Autowired
	private ArchivoExtendedMapper archivoExtendedMapper;

	@Override
	public int obtenerSecuencia(String nombreTabla) {
		return tablaSecuenciaExtendedMapper.obtenerSecuencia(AplicacionEnum.APLICACION.getValue(), nombreTabla);
	}

	@Override
	public int registrarCarpeta(String nombre, String usuario) {
		ComexstCarpeta carpetaNueva = new ComexstCarpeta();
		int intL_secuencia = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_CARPETA.getValue());
		carpetaNueva.setCarpnId(intL_secuencia);
		carpetaNueva.setCarpvNombre(nombre);
		carpetaNueva.setCarpvUsuariocreacion(usuario);
		carpetaNueva.setCarpdFechacreacion(new Date());
		carpetaNueva.setCarpnEstado(Constantes.ACTIVO);
		this.comexstCarpetaMapper.insert(carpetaNueva);
		return intL_secuencia;
	}

	@Override
	public ComexstCarpeta obtenerCarpetaxId(int idCarpeta) {
		return this.comexstCarpetaMapper.selectByPrimaryKey(idCarpeta);
	}

	@Override
	public ComexstCarpetadocumento registrarCarpetaDocumento(String nombreArchivo, int idCarpeta, String usuario) {
		ComexstCarpetadocumento documentoNuevo = new ComexstCarpetadocumento();
		int intL_secuencia = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_CARPETADOCUMENTO.getValue());
		documentoNuevo.setCdocnId(intL_secuencia);
		documentoNuevo.setCdocnIdcarpeta(idCarpeta);
		documentoNuevo.setCdocvNombre(nombreArchivo);
		documentoNuevo.setCdocvUsuariocreacion(usuario);
		documentoNuevo.setCdocdFechacreacion(new Date());
		documentoNuevo.setCdocnEstado(Constantes.ACTIVO);
		this.comexstCarpetadocumentoMapper.insert(documentoNuevo);
		return documentoNuevo;
	}

	@Override
	public ArchivoTO obtenerArchivoxNombre(String nombreArchivo, int idCarpeta) {
		return this.archivoExtendedMapper.obtenerArchivoxNombre(nombreArchivo, idCarpeta);
	}

	@Override
	public ArchivoTO obtenerArchivoxId(int id) {
		return this.archivoExtendedMapper.obtenerArchivoxId(id);
	}

	@Override
	public void eliminarArchivo(int id, String usuario) {
		this.archivoExtendedMapper.eliminarArchivo(id, usuario);
	}
}
