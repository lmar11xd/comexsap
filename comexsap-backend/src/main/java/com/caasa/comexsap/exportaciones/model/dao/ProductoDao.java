package com.caasa.comexsap.exportaciones.model.dao;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.ProductoSapTO;
import com.caasa.comexsap.exportaciones.model.to.ProductoTO;

public interface ProductoDao {

	public List<ProductoTO> listarProductoxNombre(String buscar);
	public ProductoTO obtenerProductoxCodigo(String codigo);
	public ProductoSapTO obtenerProductoSap(String codigo);
	public int guardarProducto(String codigo, String descripcion, String unidadVenta, String usuario);
	public int obtenerSecuencia(String nombreTabla);
	public ProductoTO obtenerUnidadesPaquetexCodigo(String codigoSap);
}
