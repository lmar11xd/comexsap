package com.caasa.comexsap.exportaciones.model.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caasa.comexsap.exportaciones.api.client.SapComexApiClient;
import com.caasa.comexsap.exportaciones.api.to.FiltroPrecioMaterialTO;
import com.caasa.comexsap.exportaciones.api.to.ResultadoPrecioMaterialEnvelopTO;
import com.caasa.comexsap.exportaciones.api.to.ResultadoPrecioMaterialTO;
import com.caasa.comexsap.exportaciones.model.dao.ProductoDao;
import com.caasa.comexsap.exportaciones.model.service.ProductoService;
import com.caasa.comexsap.exportaciones.model.to.PrecioMaterialRequestTO;
import com.caasa.comexsap.exportaciones.model.to.ProductoSapTO;
import com.caasa.comexsap.exportaciones.model.to.ProductoTO;
import com.caasa.comexsap.exportaciones.util.Constantes;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoDao productoDao;

	@Autowired
	private SapComexApiClient sapComexApiClient;

	@Override
	public List<ProductoTO> listarProductoxNombre(String buscar) {
		return productoDao.listarProductoxNombre(buscar);
	}

	@Override
	public List<ResultadoPrecioMaterialTO> obtenerPrecioMaterial(PrecioMaterialRequestTO request)
			throws JsonProcessingException, ParseException {
		FiltroPrecioMaterialTO filtro = new FiltroPrecioMaterialTO();
		filtro.setCodigoMaterial(request.getCodigoMaterial());
		filtro.setCodigoListaPrecio(request.getCodigoListaPrecio());
		ResultadoPrecioMaterialEnvelopTO resultadoPrecioMaterialTO = sapComexApiClient.getObtenerPrecioMaterial(filtro);
		List<ResultadoPrecioMaterialTO> listaPrecioMaterial = new ArrayList<ResultadoPrecioMaterialTO>();
		Date fechaInicio = null;
		Date fechaFin = null;
		Date fechaListaPrecio = null;
		if (resultadoPrecioMaterialTO == null) {
			return null;
		}
		for (ResultadoPrecioMaterialTO precioMaterial : resultadoPrecioMaterialTO.getListaPrecioMaterial()) {
			SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
			try {
				if (request.getFechaListaPrecio() != null || !request.getFechaListaPrecio().equals(Constantes.VACIO)) {
					fechaListaPrecio = formato.parse(request.getFechaListaPrecio());
					fechaInicio = formato.parse(precioMaterial.getFechaInicio());
					fechaFin = formato.parse(precioMaterial.getFechaFin());

					if ((fechaListaPrecio.equals(fechaInicio) || fechaListaPrecio.after(fechaInicio))
							&& (fechaListaPrecio.equals(fechaFin) || fechaListaPrecio.before(fechaFin))) {
						ResultadoPrecioMaterialTO precioMaterialResponse = new ResultadoPrecioMaterialTO();
						precioMaterialResponse.setMandate(precioMaterial.getMandate());
						precioMaterialResponse.setNroMaterial(precioMaterial.getNroMaterial());
						precioMaterialResponse.setListaPrecio(precioMaterial.getListaPrecio());
						precioMaterialResponse.setImporte(precioMaterial.getImporte());
						precioMaterialResponse.setMoneda(precioMaterial.getMoneda());
						precioMaterialResponse.setCantidad(precioMaterial.getCantidad());
						precioMaterialResponse.setUnidadMedida(precioMaterial.getUnidadMedida());
						precioMaterialResponse.setFechaInicio(precioMaterial.getFechaInicio());
						precioMaterialResponse.setFechaFin(precioMaterial.getFechaFin());
						listaPrecioMaterial.add(precioMaterialResponse);
					}
				}	
			} catch (Exception e) {
			}
		}

		return listaPrecioMaterial;
	}

	@Override
	public ProductoTO obtenerProductoxCodigo(String codigo) {
		return productoDao.obtenerProductoxCodigo(codigo);
	}

	@Override
	public ProductoSapTO obtenerProductoSap(String codigo) {
		return productoDao.obtenerProductoSap(codigo);
	}

}