package com.caasa.comexsap.exportaciones.model.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.caasa.comexsap.exportaciones.enums.AplicacionEnum;
import com.caasa.comexsap.exportaciones.enums.TablasAplicativoEnum;
import com.caasa.comexsap.exportaciones.model.dao.ControlGastosDao;
import com.caasa.comexsap.exportaciones.model.domain.ComexstControlGastos;
import com.caasa.comexsap.exportaciones.model.domain.ComexstControlGastosDetalle;
import com.caasa.comexsap.exportaciones.model.domain.ComexstExportacion;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstControlGastosDetalleMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstControlGastosMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstExportacionMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.ControlGastosExtendedMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.TablaSecuenciaExtendedMapper;
import com.caasa.comexsap.exportaciones.model.to.ControlGastosDetalleTO;
import com.caasa.comexsap.exportaciones.model.to.ControlGastosTO;
import com.caasa.comexsap.exportaciones.model.to.DocumentoControlGastoTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroControlGastosTO;
import com.caasa.comexsap.exportaciones.model.to.RequestControlGastosTO;
import com.caasa.comexsap.exportaciones.util.Constantes;

@Repository("ControlGastosDao")
public class ControlGastosDaoImpl implements ControlGastosDao {

	@Autowired
	private ControlGastosExtendedMapper controlGastosExtendedMapper;
	
	@Autowired
	private ComexstControlGastosMapper comexstControlGastosMapper;
	
	@Autowired
	private ComexstControlGastosDetalleMapper comexstControlGastosDetalleMapper;
	
	@Autowired
	private ComexstExportacionMapper comexstExportacionMapper;
	
	@Autowired
	private TablaSecuenciaExtendedMapper tablaSecuenciaExtendedMapper;
	
	@Override
	public List<DocumentoControlGastoTO> listarDocumentosxFiltro(FiltroControlGastosTO filtro) {
		return controlGastosExtendedMapper.listarDocumentosxFiltro(filtro);
	}

	@Override
	public void guardarControlGastos(RequestControlGastosTO formulario, String usuario) {
		ControlGastosTO cabecera = formulario.getCabecera();
		int id = 0;
		ComexstControlGastos comexstControlGastos = new ComexstControlGastos();
		
		if(cabecera.getId() == 0) {//Crear
			id = tablaSecuenciaExtendedMapper.obtenerSecuencia(AplicacionEnum.APLICACION.getValue(), TablasAplicativoEnum.COMEXST_CONTROL_GASTOS.getValue());
			comexstControlGastos.setCgasnId(id);
			comexstControlGastos.setCgasnPeso(cabecera.getPeso());
			comexstControlGastos.setCgasnImporte(cabecera.getImporte());
			comexstControlGastos.setCgasnImporteTonelada(cabecera.getImporteTonelada());
			comexstControlGastos.setCgasnTipoCambio(cabecera.getTipoCambio());
			comexstControlGastos.setCgasdFechaCreacion(new Date());
			comexstControlGastos.setCgasvUsuarioCreacion(usuario);
			comexstControlGastos.setCgasnEstado(Constantes.ACTIVO);
			comexstControlGastosMapper.insert(comexstControlGastos);
		} else {//Actualizar
			id = cabecera.getId();
			comexstControlGastos.setCgasnId(id);
			comexstControlGastos.setCgasnPeso(cabecera.getPeso());
			comexstControlGastos.setCgasnImporte(cabecera.getImporte());
			comexstControlGastos.setCgasnImporteTonelada(cabecera.getImporteTonelada());
			comexstControlGastos.setCgasnTipoCambio(cabecera.getTipoCambio());
			comexstControlGastos.setCgasdFechaModificacion(new Date());
			comexstControlGastos.setCgasvUsuarioModificacion(usuario);
			comexstControlGastosMapper.updateByPrimaryKeySelective(comexstControlGastos);
		}
		
		//Guardar Detalle
		guardarControlGastosDetalle(id, formulario.getDetalle(), usuario);
		
		//Actualizar control de gasto en documentos de exportacion
		ComexstExportacion comexstExportacion = null;
		for (int idDocumento : formulario.getDocumentos()) {
			comexstExportacion = new ComexstExportacion();
			comexstExportacion.setExponId(idDocumento);
			comexstExportacion.setExponIdControlGasto(id);
			comexstExportacionMapper.updateByPrimaryKeySelective(comexstExportacion);
		}
	}

	private void guardarControlGastosDetalle(int idControlGasto, List<ControlGastosDetalleTO> detalle, String usuario) {
		ComexstControlGastosDetalle comexstControlGastosDetalle = null;
		int id = 0;
		for (ControlGastosDetalleTO item : detalle) {
			comexstControlGastosDetalle = new ComexstControlGastosDetalle();
			if(item.getId() == 0) {//Crear
				id = tablaSecuenciaExtendedMapper.obtenerSecuencia(AplicacionEnum.APLICACION.getValue(), TablasAplicativoEnum.COMEXST_CONTROL_GASTOS_DETALLE.getValue());
				comexstControlGastosDetalle.setCgdenId(id);
				comexstControlGastosDetalle.setCgdenIdControlGasto(idControlGasto);
				comexstControlGastosDetalle.setCgdenIdConcepto(item.getIdConcepto());
				comexstControlGastosDetalle.setCgdenIdProveedor(item.getIdProveedor());
				comexstControlGastosDetalle.setCgdevFacturas(item.getFacturas());
				comexstControlGastosDetalle.setCgdenMonto(item.getMonto());
				comexstControlGastosDetalle.setCgdevMoneda(item.getMoneda());
				comexstControlGastosDetalle.setCgdenMontoTotal(item.getMontoTotal());
				comexstControlGastosDetalle.setCgdedFechaCreacion(new Date());
				comexstControlGastosDetalle.setCgdevUsuarioCreacion(usuario);
				comexstControlGastosDetalle.setCgdenEstado(Constantes.ACTIVO);
				comexstControlGastosDetalleMapper.insert(comexstControlGastosDetalle);
			} else {//Actualizar
				id = item.getId();
				comexstControlGastosDetalle.setCgdenId(id);
				comexstControlGastosDetalle.setCgdenIdControlGasto(idControlGasto);
				comexstControlGastosDetalle.setCgdenIdConcepto(item.getIdConcepto());
				comexstControlGastosDetalle.setCgdenIdProveedor(item.getIdProveedor());
				comexstControlGastosDetalle.setCgdevFacturas(item.getFacturas());
				comexstControlGastosDetalle.setCgdenMonto(item.getMonto());
				comexstControlGastosDetalle.setCgdevMoneda(item.getMoneda());
				comexstControlGastosDetalle.setCgdenMontoTotal(item.getMontoTotal());
				comexstControlGastosDetalle.setCgdedFechaModificacion(new Date());
				comexstControlGastosDetalle.setCgdevUsuarioModificacion(usuario);
				comexstControlGastosDetalleMapper.updateByPrimaryKeySelective(comexstControlGastosDetalle);
			}
		}
	}
}
