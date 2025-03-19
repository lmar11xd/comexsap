package com.caasa.comexsap.exportaciones.model.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.caasa.comexsap.exportaciones.model.dao.ExportacionTerrestreDao;
import com.caasa.comexsap.exportaciones.model.domain.ComexstExportacion;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstExportacionMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.ExportacionTerrestreExtendedMapper;
import com.caasa.comexsap.exportaciones.model.to.ExportacionTerrestrePosicionTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionTerrestreTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroExportacionTerrestreRequestTO;

@Repository("ExportacionTerrestreDao")
public class ExportacionTerrestreDaoImpl implements ExportacionTerrestreDao {

	@Autowired
	private ExportacionTerrestreExtendedMapper exportacionTerrestreExtendedMapper;
	
	@Autowired
	private ComexstExportacionMapper comexstExportacionMapper;
	
	@Override
	public List<ExportacionTerrestreTO> listarExportacionTerrestrexFiltro(FiltroExportacionTerrestreRequestTO request) {
		return exportacionTerrestreExtendedMapper.listarExportacionTerrestrexFiltro(request);
	}

	@Override
	public ExportacionTerrestreTO obtenerExportacionTerrestrexId(int idDocumento) {		
		return exportacionTerrestreExtendedMapper.obtenerExportacionTerrestrexId(idDocumento);
	}

	@Override
	public List<ExportacionTerrestrePosicionTO> listarPosicionxIdExportacion(int idExportacion) {
		return exportacionTerrestreExtendedMapper.listarPosicionxIdExportacion(idExportacion);
	}
	
	@Override
	public int registrarExportacionTerretre(ExportacionTerrestreTO cabecera,
			List<ExportacionTerrestrePosicionTO> posiciones, String usuario) {
		if(cabecera.getId() >= 0) {
			ComexstExportacion exportacion = this.comexstExportacionMapper.selectByPrimaryKey(cabecera.getId());
			exportacion.setExpovEmpresatransporte(cabecera.getEmpresaTransporte());
			exportacion.setExpovCanal(cabecera.getCanalControlExport());
			exportacion.setExpovComentario(cabecera.getComentario());
			exportacion.setExpodInicioembarque(cabecera.getInicioEmbarque());
			exportacion.setExpodFechalevante(cabecera.getFechaLevante());
			exportacion.setExpodFechatransito(cabecera.getFechaTransito());
			exportacion.setExpovOrdenagenteaduana(cabecera.getOrdenAgenteAduana());
			exportacion.setExpovEstadoorden(cabecera.getEstadoOrden());
			exportacion.setExpovDam(cabecera.getDam());
			exportacion.setExpodFechadam(cabecera.getFechaDam());
			exportacion.setExpodFechadam41(cabecera.getFechaDam41());
			exportacion.setExpodFechadamregularizacion(cabecera.getFechaDamRegularizacion());
			exportacion.setExponTiemporegularizaciondam41(cabecera.getTiempoRegulDam41());
			exportacion.setExpovMesdespacho(cabecera.getMesDespacho());
			exportacion.setExpovAgenteaduana(cabecera.getAgenteAduana());
			exportacion.setExpodFechaentregadua(cabecera.getFechaEntregaDua());
			exportacion.setExpodFechaentrega(cabecera.getFechaEntrega());
			exportacion.setExpodFechaentregadocumentaria(cabecera.getFechaEntregaDocu());
			exportacion.setExponGuardadoexportacion(1);
			exportacion.setExpovUsuariomodificacion(usuario);
			exportacion.setExpodFechamodificacion(new Date());
			this.comexstExportacionMapper.updateByPrimaryKey(exportacion);
			return exportacion.getExponId();
		}
		return 0;
	}

}
