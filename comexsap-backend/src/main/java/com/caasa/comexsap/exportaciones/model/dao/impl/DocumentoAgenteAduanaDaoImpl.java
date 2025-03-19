package com.caasa.comexsap.exportaciones.model.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.caasa.comexsap.exportaciones.enums.AplicacionEnum;
import com.caasa.comexsap.exportaciones.enums.TablasAplicativoEnum;
import com.caasa.comexsap.exportaciones.model.dao.DocumentoAgenteAduanaDao;
import com.caasa.comexsap.exportaciones.model.domain.ComexstDocumentoagenteaduana;
import com.caasa.comexsap.exportaciones.model.domain.ComexstExportacion;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstDocumentoagenteaduanaMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstExportacionMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.DocumentoAgenteAduanaExtendedMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.ExportacionTerrestreExtendedMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.FacturaExtendedMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.TablaSecuenciaExtendedMapper;
import com.caasa.comexsap.exportaciones.model.to.DocumentoAgenteAduanaTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionTerrestreTO;
import com.caasa.comexsap.exportaciones.model.to.FacturaTO;
import com.caasa.comexsap.exportaciones.util.Constantes;

@Repository("DocumentoAgenteAduanaDao")
public class DocumentoAgenteAduanaDaoImpl implements DocumentoAgenteAduanaDao {

	@Autowired
	private TablaSecuenciaExtendedMapper tablaSecuenciaExtendedMapper;
	
	@Autowired
	private ComexstDocumentoagenteaduanaMapper comexstDocumentoagenteaduanaMapper;
	
	@Autowired
	private FacturaExtendedMapper facturaExtendedMapper;
	
	@Autowired
	private DocumentoAgenteAduanaExtendedMapper documentoAgenteAduanaExtendedMapper;
	
	@Autowired
	private ExportacionTerrestreExtendedMapper exportacionTerrestreExtendedMapper;
	
	@Autowired
	private ComexstExportacionMapper comexstExportacionMapper;
	
	@Override
	public int obtenerSecuencia(String nombreTabla) {
		return tablaSecuenciaExtendedMapper.obtenerSecuencia(AplicacionEnum.APLICACION.getValue(), nombreTabla);
	}
	
	@Override
	public void registrarDocumentoAgenteAduana(List<DocumentoAgenteAduanaTO> posiciones, String usuario) {
		for (DocumentoAgenteAduanaTO posicion : posiciones) {
			FacturaTO factura = this.facturaExtendedMapper.obtenerFacturaxSerieCodigo(posicion.getFactura());
			DocumentoAgenteAduanaTO agenteAduana = this.documentoAgenteAduanaExtendedMapper.obtenerDocumentoAgenteAduanaxFactura(posicion.getFactura());
			
			ComexstDocumentoagenteaduana agenteNuevo = new ComexstDocumentoagenteaduana();
			if(agenteAduana == null) {
				int intL_secuencia = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_DOCUMENTOAGENTEADUANA.getValue());
				agenteNuevo.setDaadnId(intL_secuencia);
				agenteNuevo.setDaadnIdfactura(factura == null ? null: factura.getId());
				agenteNuevo.setDaadvAgenteaduana(posicion.getAgenteAduana());
				agenteNuevo.setDaadvMesdespacho(posicion.getMesDespacho());
				agenteNuevo.setDaadvSede(posicion.getSede());
				agenteNuevo.setDaadvCliente(posicion.getCliente());
				agenteNuevo.setDaadnPesoreal(posicion.getPesoReal());
				agenteNuevo.setDaadvEmpresatransporte(posicion.getEmpresaTransporte());
				agenteNuevo.setDaadvDestino(posicion.getDestino());
				agenteNuevo.setDaadvFactura(posicion.getFactura());
				agenteNuevo.setDaadvSerie(posicion.getSerie());
				agenteNuevo.setDaadvMesfactura(posicion.getMesFactura());
				agenteNuevo.setDaadvIncoterm(posicion.getIncoterm());
				agenteNuevo.setDaaddFechalevante(posicion.getFechaLevante());
				agenteNuevo.setDaaddFechatransito(posicion.getFechaTransito());
				agenteNuevo.setDaadvComentario(posicion.getComentario());
				agenteNuevo.setDaadvDam(posicion.getDam());
				agenteNuevo.setDaadvOrdenagenteaduana(posicion.getOrdenAgenteAduana());
				agenteNuevo.setDaaddFechadam40(posicion.getFechaDam40());
				agenteNuevo.setDaadvCanalcontrol(posicion.getCanalControl());
				agenteNuevo.setDaaddInicioembarque(posicion.getInicioEmbarque());
				agenteNuevo.setDaaddFecharegularizaciondam41(posicion.getFechaRegularizacionDAM41());
				agenteNuevo.setDaadnTiemporegularizacion(posicion.getTiempoRegularizacion());
				agenteNuevo.setDaaddFechaentregadua(posicion.getFechaEntregaDUA());
				agenteNuevo.setDaadvEstadoorden(posicion.getEstadoOrden());
				agenteNuevo.setDaaddFechaentrega(posicion.getFechaEntregaDrawback());
				agenteNuevo.setDaadvDataanterior(posicion.getDataAnterior());
				agenteNuevo.setDaadvDataanterior2(posicion.getDataAnterior());
				agenteNuevo.setDaadvUsuariocreacion(usuario);
				agenteNuevo.setDaaddFechacreacion(new Date());
				agenteNuevo.setDaadnEstado(Constantes.ACTIVO);
				this.comexstDocumentoagenteaduanaMapper.insert(agenteNuevo);
			} else {
				agenteNuevo = this.comexstDocumentoagenteaduanaMapper.selectByPrimaryKey(agenteAduana.getId());
				agenteNuevo.setDaadnIdfactura(factura == null ? null: factura.getId());
				agenteNuevo.setDaadvAgenteaduana(posicion.getAgenteAduana());
				agenteNuevo.setDaadvMesdespacho(posicion.getMesDespacho());
				agenteNuevo.setDaadvSede(posicion.getSede());
				agenteNuevo.setDaadvCliente(posicion.getCliente());
				agenteNuevo.setDaadnPesoreal(posicion.getPesoReal());
				agenteNuevo.setDaadvEmpresatransporte(posicion.getEmpresaTransporte());
				agenteNuevo.setDaadvDestino(posicion.getDestino());
				agenteNuevo.setDaadvFactura(posicion.getFactura());
				agenteNuevo.setDaadvSerie(posicion.getSerie());
				agenteNuevo.setDaadvMesfactura(posicion.getMesFactura());
				agenteNuevo.setDaadvIncoterm(posicion.getIncoterm());
				agenteNuevo.setDaaddFechalevante(posicion.getFechaLevante());
				agenteNuevo.setDaaddFechatransito(posicion.getFechaTransito());
				agenteNuevo.setDaadvComentario(posicion.getComentario());
				agenteNuevo.setDaadvDam(posicion.getDam());
				agenteNuevo.setDaadvOrdenagenteaduana(posicion.getOrdenAgenteAduana());
				agenteNuevo.setDaaddFechadam40(posicion.getFechaDam40());
				agenteNuevo.setDaadvCanalcontrol(posicion.getCanalControl());
				agenteNuevo.setDaaddInicioembarque(posicion.getInicioEmbarque());
				agenteNuevo.setDaaddFecharegularizaciondam41(posicion.getFechaRegularizacionDAM41());
				agenteNuevo.setDaadnTiemporegularizacion(posicion.getTiempoRegularizacion());
				agenteNuevo.setDaaddFechaentregadua(posicion.getFechaEntregaDUA());
				agenteNuevo.setDaadvEstadoorden(posicion.getEstadoOrden());
				agenteNuevo.setDaaddFechaentrega(posicion.getFechaEntregaDrawback());
				agenteNuevo.setDaadvDataanterior(agenteNuevo.getDaadvDataanterior2());
				agenteNuevo.setDaadvDataanterior2(posicion.getDataAnterior());
				agenteNuevo.setDaadvUsuariomodificacion(usuario);
				agenteNuevo.setDaaddFechamodificacion(new Date());
				this.comexstDocumentoagenteaduanaMapper.updateByPrimaryKey(agenteNuevo);
			}
			
			if(factura != null) {
				ExportacionTerrestreTO documentoExp = this.exportacionTerrestreExtendedMapper.obtenerExportacionTerrestrexFactura(factura.getId(), Constantes.INICIAL);
				if(documentoExp != null) {
					ComexstExportacion exportacion = this.comexstExportacionMapper.selectByPrimaryKey(documentoExp.getId());
					exportacion.setExpovEmpresatransporte(posicion.getEmpresaTransporte());
					exportacion.setExpovCanal(posicion.getCanalControl());
					exportacion.setExpovComentario(posicion.getComentario());
					exportacion.setExpodInicioembarque(posicion.getInicioEmbarque());
					exportacion.setExpodFechalevante(posicion.getFechaLevante());
					exportacion.setExpodFechatransito(posicion.getFechaTransito());
					exportacion.setExpovOrdenagenteaduana(posicion.getOrdenAgenteAduana());
					exportacion.setExpovEstadoorden(posicion.getEstadoOrden());
					exportacion.setExpovDam(posicion.getDam());
					exportacion.setExpodFechadam(posicion.getFechaDam40());
					exportacion.setExpodFechadamregularizacion(posicion.getFechaRegularizacionDAM41());
					exportacion.setExponTiemporegularizaciondam41(posicion.getTiempoRegularizacion());
					exportacion.setExpovMesdespacho(posicion.getMesDespacho());
					exportacion.setExpovAgenteaduana(posicion.getAgenteAduana());
					exportacion.setExpodFechaentregadua(posicion.getFechaEntregaDUA());
					exportacion.setExpodFechaentrega(posicion.getFechaEntregaDrawback());
					exportacion.setExpovUsuariomodificacion(usuario);
					exportacion.setExpodFechamodificacion(new Date());
					this.comexstExportacionMapper.updateByPrimaryKey(exportacion);
				}
			}			
		}
	}

}
