package com.caasa.comexsap.exportaciones.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.caasa.comexsap.exportaciones.model.dao.OrdenInternaDao;
import com.caasa.comexsap.exportaciones.model.domain.ComexstOrdenInterna;
import com.caasa.comexsap.exportaciones.model.domain.ComexstOrdenInternaExample;
import com.caasa.comexsap.exportaciones.model.domain.ComexstPuerto;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstOrdenInternaMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstPuertoMapper;
import com.caasa.comexsap.exportaciones.model.to.FiltroOrdenInterna;
import com.caasa.comexsap.exportaciones.model.to.OrdenInternaTO;

@Repository("OrdenInternaDao")
public class OrdenInternaDaoImpl implements OrdenInternaDao {

	@Autowired
	private ComexstOrdenInternaMapper ordenInternaMapper;
	
	@Autowired
	private ComexstPuertoMapper puertoMapper;
	

	@Override
	public OrdenInternaTO obtenerOrdenInternaxFiltro(FiltroOrdenInterna filtro) {
		OrdenInternaTO ordenInternaTO = new OrdenInternaTO();
		List<ComexstOrdenInterna> ordenes = new ArrayList<>();
		ComexstOrdenInternaExample ordenInternaExample = new ComexstOrdenInternaExample();
		ComexstPuerto puerto = puertoMapper.selectByPrimaryKey(filtro.getIdPuertoDestino());
		if(puerto != null) {
			ordenInternaExample.createCriteria()
				.andOintnIdDespachoEqualTo(filtro.getIdDespacho())
				.andOintnIdPaisEqualTo(puerto.getPuernIdpais())
				.andOintvCodigoClienteEqualTo(filtro.getCodigoCliente())
				.andOintvCodigoDestinatarioEqualTo(filtro.getCodigoDestinatario());
			ordenes = ordenInternaMapper.selectByExample(ordenInternaExample);
			
			if(ordenes.isEmpty()) {//Orden Interna por Defecto
				ordenInternaExample = new ComexstOrdenInternaExample();
				ordenInternaExample.createCriteria()
				.andOintnIdDespachoEqualTo(filtro.getIdDespacho())
				.andOintnIdPaisIsNull();
				ordenes = ordenInternaMapper.selectByExample(ordenInternaExample);
			}
			
			if(!ordenes.isEmpty()) {
				ordenInternaTO.setId(ordenes.get(0).getOintnId());
				ordenInternaTO.setCodigo(ordenes.get(0).getOintvCodigo());
				ordenInternaTO.setIdDespacho(ordenes.get(0).getOintnIdDespacho());
				ordenInternaTO.setIdPais(ordenes.get(0).getOintnIdPais());
				ordenInternaTO.setCodigoCliente(ordenes.get(0).getOintvCodigoCliente());
				ordenInternaTO.setCodigoDestinatario(ordenes.get(0).getOintvCodigoDestinatario());
				return ordenInternaTO;
			}
		}
		return null;
	}
	
	@Override
	public OrdenInternaTO obtenerOrdenInterna(int id) {
		ComexstOrdenInterna ordenInterna = ordenInternaMapper.selectByPrimaryKey(id);
		if(ordenInterna != null) {
			OrdenInternaTO ordenInternaTO = new OrdenInternaTO();
			ordenInternaTO.setId(ordenInterna.getOintnId());
			ordenInternaTO.setCodigo(ordenInterna.getOintvCodigo());
			ordenInternaTO.setIdDespacho(ordenInterna.getOintnIdDespacho());
			ordenInternaTO.setIdPais(ordenInterna.getOintnIdPais());
			ordenInternaTO.setCodigoCliente(ordenInterna.getOintvCodigoCliente());
			ordenInternaTO.setCodigoDestinatario(ordenInterna.getOintvCodigoDestinatario());
			return ordenInternaTO;
		}
		return null;
	}

}
