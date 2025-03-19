package com.caasa.comexsap.exportaciones.model.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.caasa.comexsap.exportaciones.enums.AplicacionEnum;
import com.caasa.comexsap.exportaciones.enums.TablasAplicativoEnum;
import com.caasa.comexsap.exportaciones.model.dao.PedidoIntercompanyDao;
import com.caasa.comexsap.exportaciones.model.domain.ComexstExportacion;
import com.caasa.comexsap.exportaciones.model.domain.ComexstExportacionfactura;
import com.caasa.comexsap.exportaciones.model.domain.ComexstExportacionfacturaExample;
import com.caasa.comexsap.exportaciones.model.domain.ComexstExportacionpedido;
import com.caasa.comexsap.exportaciones.model.domain.ComexstFactura;
import com.caasa.comexsap.exportaciones.model.domain.ComexstFacturaExample;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstExportacionMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstExportacionfacturaMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstExportacionpedidoMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstFacturaMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.ExportacionMaritimoExtendedMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.PedidoIntercompanyExtendedMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.TablaSecuenciaExtendedMapper;
import com.caasa.comexsap.exportaciones.model.to.EntregaSapTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionIntercompanyPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionMaritimoTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionPedidoPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.FacturaSapTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroExportacionPedidoPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroPedidoIntercompany;
import com.caasa.comexsap.exportaciones.model.to.PedidoIntercompanyTO;
import com.caasa.comexsap.exportaciones.util.Constantes;
import com.caasa.comexsap.exportaciones.util.Util;

@Repository("PedidoIntercompanyDao")
public class PedidoIntercompanyDaoImpl implements PedidoIntercompanyDao {

	@Autowired
	private PedidoIntercompanyExtendedMapper pedidoIntercompanyExtendedMapper;
	
	@Autowired
	private ExportacionMaritimoExtendedMapper exportacionMaritimoExtendedMapper;

	@Autowired
	private TablaSecuenciaExtendedMapper tablaSecuenciaExtendedMapper;

	@Autowired
	private ComexstExportacionMapper comexstExportacionMapper;
	
	@Autowired
	private ComexstExportacionpedidoMapper comexstExportacionpedidoMapper;
	
	@Autowired
	private ComexstExportacionfacturaMapper comexstExportacionfacturaMapper;
	
	@Autowired
	private ComexstFacturaMapper comexstFacturaMapper;
	
	@Override
	public List<PedidoIntercompanyTO> listarPedidoIntercompanySapxFiltro(FiltroPedidoIntercompany filtro) {
		return pedidoIntercompanyExtendedMapper.listarPedidoIntercompanySapxFiltro(filtro);
	}

	@Override
	public List<ExportacionIntercompanyPosicionTO> listarPosicionxIdExportacion(int idExportacion) {
		return pedidoIntercompanyExtendedMapper.listarPosicionxIdExportacion(idExportacion);
	}
	

	@Override
	public void guardarFacturaSap(ExportacionMaritimoTO documento, List<ExportacionIntercompanyPosicionTO> posiciones,
			String pedidoSap) {
		String serie = null, serieCodigo = null, etiquetaSubTotal = "", etiquetaImporteTotal = "";

		ExportacionIntercompanyPosicionTO posicion = null;

		// Actualizar Entrega a las Posiciones
		EntregaSapTO entregaSap = this.exportacionMaritimoExtendedMapper.obtenerEntregaSap(pedidoSap);
		if (entregaSap != null && entregaSap.getEntrega() != null) {
			for (ExportacionIntercompanyPosicionTO posicionTo : posiciones) {
				if (!entregaSap.getEntrega().equalsIgnoreCase(posicionTo.getEntrega())) {
					ComexstExportacionpedido expPosicion = comexstExportacionpedidoMapper
							.selectByPrimaryKey(posicionTo.getId());
					expPosicion.setEpedvEntrega(entregaSap.getEntrega());
					comexstExportacionpedidoMapper.updateByPrimaryKey(expPosicion);
					posicionTo.setEntrega(entregaSap.getEntrega());
				}
			}
		}

		FacturaSapTO facturaSap = this.exportacionMaritimoExtendedMapper.obtenerFacturaSap(pedidoSap);
		if (facturaSap != null) {
			ComexstFacturaExample comexstFacturaExample = new ComexstFacturaExample();
			comexstFacturaExample.createCriteria()
				.andFactvCodigosapEqualTo(facturaSap.getCodigoSap());
			long existeFactura = comexstFacturaMapper.countByExample(comexstFacturaExample);
			String folio = facturaSap.getFolio();
			
			// Crear Exportacion Factura
			float importeTotal = facturaSap.getImportePosiciones().floatValue();
			float flete = facturaSap.getFletePosiciones() == null ? 0 : facturaSap.getFletePosiciones().floatValue();
			float seguro = facturaSap.getSeguroPosiciones() == null ? 0: facturaSap.getSeguroPosiciones().floatValue();
			float subTotal = 0;

			if (documento.getCodigoIncoterm().equalsIgnoreCase(Constantes.CFR)
					&& documento.getCodigoIncotermComercial().equalsIgnoreCase(Constantes.CFR)) {
				subTotal = importeTotal - flete - seguro;
				etiquetaSubTotal = Constantes.FAC_ETIQ_SUBTOTAL_CFR;
				etiquetaImporteTotal = Constantes.FAC_ETIQ_IMPORTE_TOTAL_FOB;
			} else if ((documento.getCodigoIncoterm().equalsIgnoreCase(Constantes.FOB))
					&& documento.getCodigoIncotermComercial().equalsIgnoreCase(Constantes.CIF)
					|| (documento.getCodigoIncoterm().equalsIgnoreCase(Constantes.CIF)
					&& documento.getCodigoIncotermComercial().equalsIgnoreCase(Constantes.CIF))) {
				subTotal = importeTotal - flete - seguro;
				etiquetaSubTotal = Constantes.FAC_ETIQ_SUBTOTAL_CIF;
				etiquetaImporteTotal = Constantes.FAC_ETIQ_IMPORTE_TOTAL_FOB;
			} else {
				subTotal = importeTotal + flete + seguro;
				etiquetaSubTotal = Constantes.FAC_ETIQ_SUBTOTAL_FOB;
				etiquetaImporteTotal = Constantes.FAC_ETIQ_IMPORTE_TOTAL;
			}
			
			if (existeFactura == 0 && folio != null && !Util.isNumeric(folio)) {
				serieCodigo = folio.replace("01-0", "");
				serie = serieCodigo.split("-")[0];

				ComexstFactura factura = new ComexstFactura();
				int idFactura = this.tablaSecuenciaExtendedMapper.obtenerSecuencia(AplicacionEnum.APLICACION.getValue(), TablasAplicativoEnum.COMEXST_FACTURA.getValue());
				factura.setFactnId(idFactura);
				factura.setFactvCodigosap(facturaSap.getCodigoSap());
				factura.setFactvSector(Constantes.FAC_SECTOR);
				factura.setFactvFolio(facturaSap.getFolio());
				factura.setFactdFecha(facturaSap.getFecha());
				// factura.setFactvHorasunat(null);
				factura.setFactnImporte(new BigDecimal("" + importeTotal));
				// factura.setFactvFolionotacredito(null);
				// factura.setFactvNotacreditosap(null);
				factura.setFactvClaseexpedicion(Constantes.FAC_CLASE_EXP);
				factura.setFactvEstadofactura(Constantes.FAC_ESTADO);
				factura.setFactvOrganizacionventa(Constantes.FAC_ORG_VENTA);
				factura.setFactvCanal(Constantes.FAC_CANAL);
				// factura.setFactdFechasalida(null);
				// factura.setFactvFactura(null);
				factura.setFactvSerie(serie);
				factura.setFactvSeriecodigo(serieCodigo);
				factura.setFactvClasefactura(facturaSap.getClase());
				factura.setFactvUsuariocreacion(Constantes.FAC_USUARIO);
				factura.setFactdFechacreacion(new Date());
				factura.setFactnEstado(Constantes.ACTIVO);
				comexstFacturaMapper.insert(factura);

				//Eliminar de Exportacion Factura Anterior
				exportacionMaritimoExtendedMapper.eliminarExportacionFactura(documento.getId(), Constantes.FAC_USUARIO);
				
				if (posiciones != null && posiciones.size() > 0) {
					posicion = posiciones.get(0);
				}

				ComexstExportacionfactura exportacionfactura = new ComexstExportacionfactura();
				int idExportacionFactura = this.tablaSecuenciaExtendedMapper.obtenerSecuencia(AplicacionEnum.APLICACION.getValue(), TablasAplicativoEnum.COMEXST_EXPORTACION_FACTURA.getValue());
				exportacionfactura.setEfacnId(idExportacionFactura);
				exportacionfactura.setEfacnIdexportacion(documento.getId());
				exportacionfactura.setEfacnIdpedido(posicion != null ? posicion.getIdPedido() : null);
				exportacionfactura.setEfacvFactura(facturaSap.getCodigoSap());
				exportacionfactura.setEfacvEtiquetatotal(etiquetaSubTotal);
				exportacionfactura.setEfacvEtiquetaflete(flete > 0 ? Constantes.FAC_ETIQ_FLETE : null);
				exportacionfactura.setEfacvEtiquetaimportetotal(etiquetaImporteTotal);
				exportacionfactura.setEfacvEtiquetaunidadmedida(posicion != null ? posicion.getCodigoSAPUnidadMedidaVenta() : null);
				exportacionfactura.setEfacnMontototal(new BigDecimal("" + subTotal));
				exportacionfactura.setEfacnMontoflete(new BigDecimal("" + flete));
				exportacionfactura.setEfacnMontoimportetotal(new BigDecimal("" + importeTotal));
				exportacionfactura.setEfacvUsuariocreacion(Constantes.FAC_USUARIO);
				exportacionfactura.setEfacdFechacreacion(new Date());
				exportacionfactura.setEfacnEstado(Constantes.ACTIVO);
				comexstExportacionfacturaMapper.insert(exportacionfactura);

				// Actualizar Documento Maritimo
				ComexstExportacion docMaritimo = comexstExportacionMapper.selectByPrimaryKey(documento.getId());
				docMaritimo.setExponIdfactura(idFactura);
				comexstExportacionMapper.updateByPrimaryKeySelective(docMaritimo);
			} else if (existeFactura > 0) {
				ComexstExportacionfacturaExample query = new ComexstExportacionfacturaExample();
				query.createCriteria().andEfacnIdexportacionEqualTo(documento.getId()).andEfacnEstadoEqualTo(Constantes.ACTIVO);
				List<ComexstExportacionfactura> facturas = comexstExportacionfacturaMapper.selectByExample(query);
				if(facturas != null && facturas.size() > 0) {
					ComexstExportacionfactura expFactura = facturas.get(0);
					expFactura.setEfacvEtiquetaimportetotal(etiquetaImporteTotal);
					expFactura.setEfacvEtiquetatotal(etiquetaSubTotal);
					expFactura.setEfacvEtiquetaflete(flete > 0 ? Constantes.FAC_ETIQ_FLETE : null);
					expFactura.setEfacnMontoimportetotal(new BigDecimal("" + importeTotal));
					expFactura.setEfacnMontototal(new BigDecimal("" + subTotal));
					expFactura.setEfacnMontoflete(new BigDecimal("" + flete));
					comexstExportacionfacturaMapper.updateByPrimaryKeySelective(expFactura);
				}
			}
		}
	}

	@Override
	public List<ExportacionPedidoPosicionTO> listarPedidosIntercompanySapDisponibles(
			FiltroExportacionPedidoPosicionTO filtro) {
		return pedidoIntercompanyExtendedMapper.listarPedidosIntercompanySapDisponibles(filtro);
	}


}
