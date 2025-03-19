package com.caasa.comexsap.exportaciones.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caasa.comexsap.exportaciones.model.dao.PuertoDao;
import com.caasa.comexsap.exportaciones.model.service.PuertoService;
import com.caasa.comexsap.exportaciones.model.to.PuertoTO;

@Service
public class PuertoServiceImpl implements PuertoService{
	
	@Autowired
	private PuertoDao puertoDao;	

	@Override
	public List<PuertoTO> listarPuertoxNombre(String buscar) {
		return puertoDao.listarPuertoxNombre(buscar);
	}
		
}