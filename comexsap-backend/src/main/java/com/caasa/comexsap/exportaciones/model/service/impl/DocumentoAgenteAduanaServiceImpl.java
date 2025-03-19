package com.caasa.comexsap.exportaciones.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caasa.comexsap.exportaciones.model.dao.DocumentoAgenteAduanaDao;
import com.caasa.comexsap.exportaciones.model.service.DocumentoAgenteAduanaService;
import com.caasa.comexsap.exportaciones.model.to.DocumentoAgenteAduanaTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DocumentoAgenteAduanaServiceImpl implements DocumentoAgenteAduanaService {

	@Autowired
	private DocumentoAgenteAduanaDao documentoAgenteAduanaDao;
	
	@Override
	public void registrarDocumentoAgenteAduana(List<DocumentoAgenteAduanaTO> posiciones, String usuario) {
		documentoAgenteAduanaDao.registrarDocumentoAgenteAduana(posiciones, usuario);
	}

}
