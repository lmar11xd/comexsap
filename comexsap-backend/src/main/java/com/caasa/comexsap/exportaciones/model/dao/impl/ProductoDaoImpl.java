package com.caasa.comexsap.exportaciones.model.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.caasa.comexsap.exportaciones.enums.AplicacionEnum;
import com.caasa.comexsap.exportaciones.enums.TablasAplicativoEnum;
import com.caasa.comexsap.exportaciones.model.dao.ProductoDao;
import com.caasa.comexsap.exportaciones.model.domain.ComexstProducto;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstProductoMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.ProductoExtendedMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.TablaSecuenciaExtendedMapper;
import com.caasa.comexsap.exportaciones.model.to.ProductoSapTO;
import com.caasa.comexsap.exportaciones.model.to.ProductoTO;
import com.caasa.comexsap.exportaciones.util.Constantes;

@Repository("ProductoDao")
public class ProductoDaoImpl implements ProductoDao{

	@Autowired
	private ComexstProductoMapper comexstProductoMapper;
	
	@Autowired
	private ProductoExtendedMapper productoExtendedMapper;

	@Autowired
	private TablaSecuenciaExtendedMapper tablaSecuenciaExtendedMapper;

	@Override
	public List<ProductoTO> listarProductoxNombre(String buscar) {
		return productoExtendedMapper.listarProductoxNombre(buscar);
	}

	@Override
	public ProductoTO obtenerProductoxCodigo(String codigo) {
		return productoExtendedMapper.obtenerProductoxCodigo(codigo);
	}

	@Override
	public ProductoSapTO obtenerProductoSap(String codigo) {
		return productoExtendedMapper.obtenerProductoSap(codigo);
	}

	@Override
	public int obtenerSecuencia(String nombreTabla) {
		return tablaSecuenciaExtendedMapper.obtenerSecuencia(AplicacionEnum.APLICACION.getValue(), nombreTabla);
	}
	
	@Override
	public int guardarProducto(String codigo, String descripcion, String unidadVenta, String usuario) {
		int productoExiste = this.productoExtendedMapper.buscarProductoxCodigoSap(codigo);
		ComexstProducto producto = new ComexstProducto();
		if(productoExiste == 0) {
			int id = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_PRODUCTO.getValue());
			producto.setProdnId(id);
			producto.setProdvCodigosap(codigo);
			producto.setProdvUnidadmedidaventa(unidadVenta);
			producto.setProdvDescripcion(descripcion);
			try {
				ProductoSapTO productoSap = this.productoExtendedMapper.obtenerProductoSap(codigo);
				if(productoSap != null) {
					producto.setProdvDescripcion(productoSap.getDescripcion());
					producto.setProdvUnidadmedidabase(productoSap.getUnidadBase());
					producto.setProdvCodigojerarquia(productoSap.getCodigoLinea());
					producto.setProdvDescripcionjerarquia(productoSap.getDescripcionLinea());
					producto.setProdvCodigojerarquia2(productoSap.getCodigoFamilia());
					producto.setProdvDescripcionjerarquia2(productoSap.getDescripcionFamilia());
					producto.setProdnPesoneto(productoSap.getPesoNeto());
					producto.setProdnPesobruto(productoSap.getPesoBruto());
				}
			} catch (Exception e) {
			}
			producto.setProddFechacreacion(new Date());
			producto.setProdvUsuariocreacion(usuario);
			producto.setProdnEstado(Constantes.ACTIVO);
			this.comexstProductoMapper.insert(producto);
			return id;
		} else {
			ProductoTO productoEncontrado = this.productoExtendedMapper.obtenerProductoxCodigoSap(codigo);
			return productoEncontrado.getIdProducto();
		}
	}

	@Override
	public ProductoTO obtenerUnidadesPaquetexCodigo(String codigoSap) {
		return this.productoExtendedMapper.obtenerUnidadesPaquetexCodigo(codigoSap);
	}

}
