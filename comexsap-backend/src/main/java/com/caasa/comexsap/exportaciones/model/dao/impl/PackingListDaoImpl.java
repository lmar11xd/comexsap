package com.caasa.comexsap.exportaciones.model.dao.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.caasa.comexsap.exportaciones.enums.AplicacionEnum;
import com.caasa.comexsap.exportaciones.enums.TablasAplicativoEnum;
import com.caasa.comexsap.exportaciones.model.dao.PackingListDao;
import com.caasa.comexsap.exportaciones.model.domain.ComexstContenedor;
import com.caasa.comexsap.exportaciones.model.domain.ComexstPackinglist;
import com.caasa.comexsap.exportaciones.model.domain.ComexstPackinglistAereo;
import com.caasa.comexsap.exportaciones.model.domain.ComexstPackinglistCargasuelta;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstContenedorMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstPackinglistAereoMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstPackinglistCargasueltaMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstPackinglistMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.PackingListExtendedMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.TablaSecuenciaExtendedMapper;
import com.caasa.comexsap.exportaciones.model.to.ContenedorTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionMaritimoTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroPackingListRequestTO;
import com.caasa.comexsap.exportaciones.model.to.PackingListAereoTO;
import com.caasa.comexsap.exportaciones.model.to.PackingListCargaSueltaTO;
import com.caasa.comexsap.exportaciones.model.to.PackingListTO;
import com.caasa.comexsap.exportaciones.model.to.ReporteContenedorMaterialTO;
import com.caasa.comexsap.exportaciones.model.to.ReporteContenedorTO;
import com.caasa.comexsap.exportaciones.model.to.ReportePackingListTO;
import com.caasa.comexsap.exportaciones.util.Constantes;

@Repository("PackingListDao")
public class PackingListDaoImpl implements PackingListDao {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PackingListExtendedMapper packingListExtendedMapper;

	@Autowired
	private TablaSecuenciaExtendedMapper tablaSecuenciaExtendedMapper;

	@Autowired
	private ComexstContenedorMapper comexstContenedorMapper;

	@Autowired
	private ComexstPackinglistMapper comexstPackinglistMapper;
	
	@Autowired
	private ComexstPackinglistCargasueltaMapper comexstPackinglistCargasueltaMapper;
	
	@Autowired
	private ComexstPackinglistAereoMapper comexstPackinglistAereoMapper;

	@Override
	public List<ExportacionMaritimoTO> listarPackingListxFiltro(FiltroPackingListRequestTO request) {
		return this.packingListExtendedMapper.listarPackingListxFiltro(request);
	}

	@Override
	public ExportacionMaritimoTO obtenerExportacionxId(int id) {
		return this.packingListExtendedMapper.obtenerExportacionxId(id);
	}

	@Override
	public List<ContenedorTO> listarContenedorxId(int id) {
		return this.packingListExtendedMapper.listarContenedorxId(id);
	}

	@Override
	public List<PackingListTO> listarPackingListxId(int id) {
		return this.packingListExtendedMapper.listarPackingListxId(id);
	}

	@Override
	public int registrarPackingList(List<ContenedorTO> contenedores, List<PackingListTO> packings, String usuario,
			int idExportacion) {
		try {
			this.registrarContenedores(contenedores, idExportacion, usuario);
			this.registrarPackings(packings, idExportacion, usuario);
			return 1;
		} catch (Exception ex) {
			logger.error("Error al registrar packinglist", ex);
			return 0;
		}
	}

	@Override
	public void registrarContenedores(List<ContenedorTO> contenedores, int idExportacion, String usuario) {
		for (ContenedorTO contenedor : contenedores) {
			ComexstContenedor contenedorBean = new ComexstContenedor();
			if (contenedor.getNumero() != 0) {
				if (contenedor.getId() == 0) {
					int intL_secuencia = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_CONTENEDOR.getValue());
					contenedorBean.setContnId(intL_secuencia);
					contenedorBean.setContvTaco(contenedor.getTaco());
					contenedorBean.setContvPrecinto1(contenedor.getPrecinto1());
					contenedorBean.setContvPrecinto2(contenedor.getPrecinto2());
					contenedorBean.setContvPlaca(contenedor.getPlaca());
					contenedorBean.setContvPlacacarrete(contenedor.getPlacaCarrete());
					contenedorBean.setContnPesocomex(contenedor.getPesoComex());
					contenedorBean.setContnNumero(contenedor.getNumero());
					contenedorBean.setContnIdexportacion(idExportacion);
					contenedorBean.setContvHorainicio(contenedor.getHoraInicio());
					contenedorBean.setContvHorafinal(contenedor.getHoraFinal());
					contenedorBean.setContnEstado(contenedor.getEstado());
					contenedorBean.setContvDtsap(contenedor.getDtSAP());
					contenedorBean.setContvDescripcion(contenedor.getDescripcion());
					contenedorBean.setContvCodigo(contenedor.getCodigo());
					contenedorBean.setContvChofer(contenedor.getChofer());
					contenedorBean.setContvBrevete(contenedor.getBrevete());
					contenedorBean.setContvBulto(contenedor.getBulto());
					contenedorBean.setContvBooking(contenedor.getBooking());
					contenedorBean.setContdFechacreacion(new Date());
					contenedorBean.setContvUsuariocreacion(usuario);
					this.comexstContenedorMapper.insert(contenedorBean);
				} else {
					contenedorBean.setContnId(contenedor.getId());
					contenedorBean.setContvTaco(contenedor.getTaco());
					contenedorBean.setContvPrecinto1(contenedor.getPrecinto1());
					contenedorBean.setContvPrecinto2(contenedor.getPrecinto2());
					contenedorBean.setContvPlaca(contenedor.getPlaca());
					contenedorBean.setContvPlacacarrete(contenedor.getPlacaCarrete());
					contenedorBean.setContnPesocomex(contenedor.getPesoComex());
					contenedorBean.setContnNumero(contenedor.getNumero());
					contenedorBean.setContnIdexportacion(contenedor.getIdExportacion());
					contenedorBean.setContvHorainicio(contenedor.getHoraInicio());
					contenedorBean.setContvHorafinal(contenedor.getHoraFinal());
					contenedorBean.setContnEstado(contenedor.getEstado());
					contenedorBean.setContvDtsap(contenedor.getDtSAP());
					contenedorBean.setContvDescripcion(contenedor.getDescripcion());
					contenedorBean.setContvCodigo(contenedor.getCodigo());
					contenedorBean.setContvChofer(contenedor.getChofer());
					contenedorBean.setContvBrevete(contenedor.getBrevete());
					contenedorBean.setContvBulto(contenedor.getBulto());
					contenedorBean.setContvBooking(contenedor.getBooking());
					contenedorBean.setContdFechamodificacion(new Date());
					contenedorBean.setContvUsuariomodificacion(usuario);
					this.comexstContenedorMapper.updateByPrimaryKey(contenedorBean);
				}
			}
		}
	}

	@Override
	public int obtenerSecuencia(String nombreTabla) {
		return tablaSecuenciaExtendedMapper.obtenerSecuencia(AplicacionEnum.APLICACION.getValue(), nombreTabla);
	}

	@Override
	public void registrarPackings(List<PackingListTO> packings, int idExportacion, String usuario) {
		for (PackingListTO packing : packings) {
			String numero = "";
			ComexstPackinglist packingBean = new ComexstPackinglist();
			if (packing.getId() == 0) {
				int intL_secuencia = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_PACKINGLIST.getValue());
				packingBean.setPlisnId(intL_secuencia);
				packingBean.setPlisvCodigoPedido(packing.getCodigoPedido());
				packingBean.setPlisvCodigo(packing.getCodigo());
				packingBean.setPlisvCodigoagrupador(packing.getCodigoAgrupador());
				packingBean.setPlisvDenominacion(packing.getDenominacion());
				packingBean.setPlisvColada(packing.getColada());
				packingBean.setPlisvLote(packing.getLote());
				packingBean.setPlisnPesonetotonelada(packing.getPesoNetoTonelada());
				packingBean.setPlisvHu(packing.getHu());
				packingBean.setPlisnCantidad(packing.getCantidad());
				packingBean.setPlisvDimension(packing.getDimension());
				numero = packing.getNumero() != null ? packing.getNumero() : "";
				ContenedorTO contenedorBean = this.obtenerContenedorxNumero(idExportacion, numero);
				packingBean.setPlisnIdcontenedor(contenedorBean != null ? contenedorBean.getId() : null);
				packingBean.setPlisnIdexportacion(idExportacion);
				packingBean.setPlisnPesocomex(packing.getPesoRealTonelada());
				packingBean.setPlisdFechacreacion(new Date());
				packingBean.setPlisvUsuariocreacion(usuario);
				packingBean.setPlisnEstado(packing.getEstado());
				this.comexstPackinglistMapper.insert(packingBean);
			} else {
				packingBean.setPlisnId(packing.getId());
				packingBean.setPlisvCodigoPedido(packing.getCodigoPedido());
				packingBean.setPlisvCodigo(packing.getCodigo());
				packingBean.setPlisvCodigoagrupador(packing.getCodigoAgrupador());
				packingBean.setPlisvDenominacion(packing.getDenominacion());
				packingBean.setPlisvColada(packing.getColada());
				packingBean.setPlisvLote(packing.getLote());
				packingBean.setPlisnPeso(packing.getPeso());
				packingBean.setPlisnPesonetotonelada(packing.getPesoNetoTonelada());
				packingBean.setPlisvHu(packing.getHu());
				packingBean.setPlisnCantidad(packing.getCantidad());
				packingBean.setPlisvDimension(packing.getDimension());
				numero = packing.getNumero() != null ? packing.getNumero() : "";
				ContenedorTO contenedorBean = this.obtenerContenedorxNumero(idExportacion, numero);
				packingBean.setPlisnIdcontenedor(contenedorBean != null ? contenedorBean.getId() : null);
				packingBean.setPlisnPesocomex(packing.getPesoRealTonelada());
				packingBean.setPlisnIdexportacion(packing.getIdExportacion());
				packingBean.setPlisdFechamodificacion(new Date());
				packingBean.setPlisnEstado(packing.getEstado());
				packingBean.setPlisvUsuariomodificacion(usuario);
				this.comexstPackinglistMapper.updateByPrimaryKey(packingBean);
			}

		}
	}

	@Override
	public ContenedorTO obtenerContenedorxNumero(int idExportacion, String numero) {
		return this.packingListExtendedMapper.obtenerContenedorxNumero(idExportacion, numero, Constantes.ACTIVO);
	}

	@Override
	public List<ReporteContenedorTO> reporteContenedor(int idExportacion) {
		return this.packingListExtendedMapper.reporteContenedor(idExportacion);
	}

	@Override
	public List<ReporteContenedorMaterialTO> reporteContenedorMaterial(int idContenedor) {
		return this.packingListExtendedMapper.reporteContenedorMaterial(idContenedor);
	}

	@Override
	public List<ReportePackingListTO> reportePackingList(int idContenedor, String codigoMaterial) {
		return this.packingListExtendedMapper.reportePackingList(idContenedor, codigoMaterial);
	}

	@Override
	public void registrarPackingListCargaSuelta(int idExportacion, List<PackingListCargaSueltaTO> packing, String usuario) {
		for (PackingListCargaSueltaTO packingList : packing) {
			ComexstPackinglistCargasuelta packinglistCargasuelta = new ComexstPackinglistCargasuelta();
			if(packingList.getId() == 0) {
				int id = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_PACKINGLIST_CARGASUELTA.getValue());
				packinglistCargasuelta.setPlcsnId(id);
				packinglistCargasuelta.setPlcsnIdExportacion(idExportacion);
				packinglistCargasuelta.setPlcsvCodigo(packingList.getCodigo());
				packinglistCargasuelta.setPlcsvMaterial(packingList.getMaterial());
				packinglistCargasuelta.setPlcsnPesoTeorico(packingList.getPesoTeorico());
				packinglistCargasuelta.setPlcsnPesoNeto(packingList.getPesoNeto());
				packinglistCargasuelta.setPlcsnPesoBruto(packingList.getPesoBruto());
				packinglistCargasuelta.setPlcsnPaquetes(packingList.getPaquetes());
				packinglistCargasuelta.setPlcsnPiezas(packingList.getPiezas());
				packinglistCargasuelta.setPlcsvColor(packingList.getColor());
				packinglistCargasuelta.setPlcsvUsuarioCreacion(usuario);
				packinglistCargasuelta.setPlcsdFechaCreacion(new Date());
				packinglistCargasuelta.setPlcsnEstado(Constantes.ACTIVO);
				comexstPackinglistCargasueltaMapper.insert(packinglistCargasuelta);
			} else {
				packinglistCargasuelta.setPlcsnId(packingList.getId());
				
				if(packingList.getEstado() == Constantes.INACTIVO) {//Eliminar
					packinglistCargasuelta.setPlcsnEstado(Constantes.INACTIVO);
				} else {//Actualizar
					packinglistCargasuelta.setPlcsnIdExportacion(idExportacion);
					packinglistCargasuelta.setPlcsvCodigo(packingList.getCodigo());
					packinglistCargasuelta.setPlcsvMaterial(packingList.getMaterial());
					packinglistCargasuelta.setPlcsnPesoTeorico(packingList.getPesoTeorico());
					packinglistCargasuelta.setPlcsnPesoNeto(packingList.getPesoNeto());
					packinglistCargasuelta.setPlcsnPesoBruto(packingList.getPesoBruto());
					packinglistCargasuelta.setPlcsnPaquetes(packingList.getPaquetes());
					packinglistCargasuelta.setPlcsnPiezas(packingList.getPiezas());
					packinglistCargasuelta.setPlcsvColor(packingList.getColor());
					packinglistCargasuelta.setPlcsvUsuarioModificacion(usuario);
					packinglistCargasuelta.setPlcsdFechaModificacion(new Date());
				}

				comexstPackinglistCargasueltaMapper.updateByPrimaryKeySelective(packinglistCargasuelta);
			}
		}
	}

	@Override
	public List<PackingListCargaSueltaTO> obtenerPackingListCargaSuelta(int idExportacion) {
		return packingListExtendedMapper.obtenerPackingListCargaSuelta(idExportacion);
	}

	@Override
	public void registrarPackingListAereo(int idExportacion, List<PackingListAereoTO> packing, String usuario) {
		for (PackingListAereoTO packingList : packing) {
			ComexstPackinglistAereo packinglistAereo = new ComexstPackinglistAereo();
			if(packingList.getId() == 0) {
				int id = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_PACKINGLIST_AEREO.getValue());
				packinglistAereo.setPlaenId(id);
				packinglistAereo.setPlaenIdExportacion(idExportacion);
				packinglistAereo.setPlaenNumero(packingList.getNumero());
				packinglistAereo.setPlaevCodigo(packingList.getCodigo());
				packinglistAereo.setPlaevMaterial(packingList.getMaterial());
				packinglistAereo.setPlaenCantidad(packingList.getCantidad());
				packinglistAereo.setPlaenPesoBruto(packingList.getPesoBruto());
				packinglistAereo.setPlaevDimension(packingList.getDimension());
				packinglistAereo.setPlaevUsuarioCreacion(usuario);
				packinglistAereo.setPlaedFechaCreacion(new Date());
				packinglistAereo.setPlaenEstado(Constantes.ACTIVO);
				comexstPackinglistAereoMapper.insert(packinglistAereo);
			} else {
				packinglistAereo.setPlaenId(packingList.getId());
				
				if(packingList.getEstado() == Constantes.INACTIVO) {//Eliminar
					packinglistAereo.setPlaenEstado(Constantes.INACTIVO);
				} else {//Actualizar
					packinglistAereo.setPlaenIdExportacion(idExportacion);
					packinglistAereo.setPlaenNumero(packingList.getNumero());
					packinglistAereo.setPlaevCodigo(packingList.getCodigo());
					packinglistAereo.setPlaevMaterial(packingList.getMaterial());
					packinglistAereo.setPlaenCantidad(packingList.getCantidad());
					packinglistAereo.setPlaenPesoBruto(packingList.getPesoBruto());
					packinglistAereo.setPlaevDimension(packingList.getDimension());
					packinglistAereo.setPlaevUsuarioModificacion(usuario);
					packinglistAereo.setPlaedFechaModificacion(new Date());
				}

				comexstPackinglistAereoMapper.updateByPrimaryKeySelective(packinglistAereo);
			}
		}
	}

	@Override
	public List<PackingListAereoTO> obtenerPackingListAereo(int idExportacion) {
		return packingListExtendedMapper.obtenerPackingListAereo(idExportacion);
	}

}
