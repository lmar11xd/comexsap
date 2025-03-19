package com.caasa.comexsap.exportaciones.model.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.caasa.comexsap.exportaciones.enums.AplicacionEnum;
import com.caasa.comexsap.exportaciones.enums.CalendarioEnum;
import com.caasa.comexsap.exportaciones.enums.TablasAplicativoEnum;
import com.caasa.comexsap.exportaciones.model.dao.ExportacionMaritimoDao;
import com.caasa.comexsap.exportaciones.model.domain.ComexstEtiqueta;
import com.caasa.comexsap.exportaciones.model.domain.ComexstExportacion;
import com.caasa.comexsap.exportaciones.model.domain.ComexstExportacionEtiqueta;
import com.caasa.comexsap.exportaciones.model.domain.ComexstExportacionEtiquetaExample;
import com.caasa.comexsap.exportaciones.model.domain.ComexstExportacionfactura;
import com.caasa.comexsap.exportaciones.model.domain.ComexstExportacionfacturaExample;
import com.caasa.comexsap.exportaciones.model.domain.ComexstExportacionpedido;
import com.caasa.comexsap.exportaciones.model.domain.ComexstExportacionpedidoExample;
import com.caasa.comexsap.exportaciones.model.domain.ComexstFactura;
import com.caasa.comexsap.exportaciones.model.domain.ComexstFacturaExample;
import com.caasa.comexsap.exportaciones.model.domain.ComexstPedidoexportacionposicion;
import com.caasa.comexsap.exportaciones.model.domain.ComexstPedidoposicion;
import com.caasa.comexsap.exportaciones.model.domain.ComexstProducto;
import com.caasa.comexsap.exportaciones.model.domain.ComexstUnidadmedida;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstEtiquetaMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstExportacionEtiquetaMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstExportacionMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstExportacionfacturaMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstExportacionpedidoMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstFacturaMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstPedidoexportacionposicionMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstPedidoposicionMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstProductoMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstUnidadmedidaMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.ExportacionMaritimoExtendedMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.ProductoExtendedMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.TablaSecuenciaExtendedMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.UnidadMedidaExtendedMapper;
import com.caasa.comexsap.exportaciones.model.to.ComponenteTO;
import com.caasa.comexsap.exportaciones.model.to.EntregaSapTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionAgrupadoTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionEtiquetaTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionFacturaTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionMaritimoPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionMaritimoTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionOperacionTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionPaisTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionPedidoPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.FacturaSapTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroExportacionMaritimoRequestTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroExportacionPedidoPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.PedidoFirmePosicionTO;
import com.caasa.comexsap.exportaciones.model.to.ProductoTO;
import com.caasa.comexsap.exportaciones.model.to.RequestExportacionMaritimoTO;
import com.caasa.comexsap.exportaciones.model.to.SerieTO;
import com.caasa.comexsap.exportaciones.model.to.UnidadMedidaTO;
import com.caasa.comexsap.exportaciones.soap.to.Cabecera;
import com.caasa.comexsap.exportaciones.util.Constantes;
import com.caasa.comexsap.exportaciones.util.DateUtil;
import com.caasa.comexsap.exportaciones.util.Util;

@Repository("ExportacionMaritimoDao")
public class ExportacionMaritimoDaoImpl implements ExportacionMaritimoDao {

	@Autowired
	private ExportacionMaritimoExtendedMapper exportacionMaritimoExtendedMapper;

	@Autowired
	private TablaSecuenciaExtendedMapper tablaSecuenciaExtendedMapper;

	@Autowired
	private ComexstExportacionMapper comexstExportacionMapper;

	@Autowired
	private ComexstExportacionpedidoMapper comexstExportacionpedidoMapper;

	@Autowired
	private ComexstPedidoexportacionposicionMapper comexstPedidoexportacionposicionMapper;

	@Autowired
	private ComexstExportacionfacturaMapper comexstExportacionfacturaMapper;

	@Autowired
	private ProductoExtendedMapper productoExtendedMapper;

	@Autowired
	private ComexstProductoMapper comexstProductoMapper;

	@Autowired
	private UnidadMedidaExtendedMapper unidadMedidaExtendedMapper;

	@Autowired
	private ComexstUnidadmedidaMapper comexstUnidadmedidaMapper;

	@Autowired
	private ComexstPedidoposicionMapper comexstPedidoposicionMapper;

	@Autowired
	private ComexstFacturaMapper comexstFacturaMapper;
	
	@Autowired
	private ComexstEtiquetaMapper comexstEtiquetaMapper;
	
	@Autowired
	private ComexstExportacionEtiquetaMapper comexstExportacionEtiquetaMapper;

	@Override
	public List<ExportacionMaritimoTO> listarExportacionMaritimoxFiltro(FiltroExportacionMaritimoRequestTO request) {
		return exportacionMaritimoExtendedMapper.listarExportacionMaritimoxFiltro(request);
	}

	@Override
	public List<ExportacionMaritimoPosicionTO> listarPosicionxIdExportacion(int idExportacion) {
		return exportacionMaritimoExtendedMapper.listarPosicionxIdExportacion(idExportacion);
	}

	@Override
	public ExportacionMaritimoTO obtenerExportacionMaritimoxId(int idDocumento) {
		return exportacionMaritimoExtendedMapper.obtenerExportacionMaritimoxId(idDocumento);
	}

	@Override
	public int buscarExportacionMaritimoxId(int id, int estadoDocumento) {
		return exportacionMaritimoExtendedMapper.buscarExportacionMaritimoxId(id, estadoDocumento);
	}

	@Override
	public int registrarExportacionMaritimo(RequestExportacionMaritimoTO request, String usuario) {
		ExportacionMaritimoTO exportacion = request.getExportacion();
		
		ComexstExportacion exportacionMaritimoBean = new ComexstExportacion();
		int id = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_PEDIDO.getValue());
		exportacionMaritimoBean.setExponId(id);
		exportacionMaritimoBean.setExpovCodigo(Constantes.SUBINDICE_CODIGO_MARITIMO + id);
		exportacionMaritimoBean.setExponIdcliente(exportacion.getIdCliente());
		exportacionMaritimoBean
				.setExponIddestinatario(exportacion.getIdDestinatario() == 0 ? null : exportacion.getIdDestinatario());
		exportacionMaritimoBean
				.setExponIdpuertoorigen(exportacion.getIdPuertoOrigen() == 0 ? null : exportacion.getIdPuertoOrigen());
		exportacionMaritimoBean.setExponIdpuertodestino(
				exportacion.getIdPuertoDestino() == 0 ? null : exportacion.getIdPuertoDestino());
		exportacionMaritimoBean
				.setExponIdincoterm(exportacion.getIdIncoterm() == 0 ? null : exportacion.getIdIncoterm());
		exportacionMaritimoBean.setExponIdincotermcomercial(
				exportacion.getIdIncotermComercial() == 0 ? null : exportacion.getIdIncotermComercial());
		exportacionMaritimoBean.setExpodFechalistaprecio(exportacion.getFechaListaPrecio());
		exportacionMaritimoBean
				.setExponIdlistaprecio(exportacion.getIdListaPrecio() == 0 ? null : exportacion.getIdListaPrecio());
		exportacionMaritimoBean
				.setExponIddespacho(exportacion.getIdDespacho() == 0 ? null : exportacion.getIdDespacho());
		exportacionMaritimoBean.setExponIdmoneda(exportacion.getIdMoneda() == 0 ? null : exportacion.getIdMoneda());
		exportacionMaritimoBean.setExponIdcondicionpago(
				exportacion.getIdCondicionPago() == 0 ? null : exportacion.getIdCondicionPago());
		exportacionMaritimoBean.setExponIdruta(exportacion.getIdRuta() == 0 ? null : exportacion.getIdRuta());
		exportacionMaritimoBean.setExponIdformapago(1);
		exportacionMaritimoBean.setExponIdtipotransporte(Constantes.TIPO_TRANSPORTE_MARITIMO);
		exportacionMaritimoBean.setExponIdestadodocumento(Constantes.INICIAL);
		exportacionMaritimoBean.setExpovAgenteaduana(exportacion.getAgenteAduana());
		exportacionMaritimoBean
				.setExponIdagenteaduana(exportacion.getIdAgenteAduana() == 0 ? null : exportacion.getIdAgenteAduana());
		exportacionMaritimoBean.setExpovShipper(exportacion.getShipper());
		exportacionMaritimoBean.setExpovDireccionshipper(exportacion.getDireccionShipper());
		exportacionMaritimoBean.setExpovNotificante(exportacion.getNotificante());
		exportacionMaritimoBean.setExpovDireccionnotificante(exportacion.getDireccionNotificante());
		exportacionMaritimoBean.setExpovConsignatario(exportacion.getConsignatario());
		exportacionMaritimoBean.setExpovDireccionconsignatario(exportacion.getDireccionConsignatario());
		exportacionMaritimoBean.setExpovGlosa(exportacion.getGlosa());
		exportacionMaritimoBean.setExpovDescripcionTotal(exportacion.getDescripcionTotal());
		exportacionMaritimoBean.setExpovOrdenInterna(exportacion.getOrdenInterna());
		exportacionMaritimoBean.setExpovAgentecarga(exportacion.getAgenteCarga());
		exportacionMaritimoBean.setExpovAgentenaviera(exportacion.getAgenteNaviera());
		exportacionMaritimoBean.setExpovArmador(exportacion.getArmador());
		exportacionMaritimoBean.setExpovNave(exportacion.getNave());
		exportacionMaritimoBean.setExpodEtaorigen(exportacion.getEtaOrigen());
		exportacionMaritimoBean.setExpodEtadestino(exportacion.getEtaDestino());
		exportacionMaritimoBean.setExpodFechablprogramado(exportacion.getFechaBlProgramado());
		exportacionMaritimoBean.setExpodFechablreal(exportacion.getFechaBlReal());
		exportacionMaritimoBean.setExpodFechacarguio(exportacion.getFechaCarguio());
		exportacionMaritimoBean.setExpodFechamaximaingreso(exportacion.getFechaMaximaIngreso());
		exportacionMaritimoBean.setExpodFechaenviodocumento(exportacion.getFechaEnvioDocum());
		exportacionMaritimoBean
				.setExponIdmonedaflete(exportacion.getIdMonedaFlete() == 0 ? null : exportacion.getIdMonedaFlete());
		exportacionMaritimoBean.setExponFlete(exportacion.getFlete());
		exportacionMaritimoBean
				.setExponIdmonedaseguro(exportacion.getIdMonedaSeguro() == 0 ? null : exportacion.getIdMonedaSeguro());
		exportacionMaritimoBean.setExponSeguro(exportacion.getSeguro());
		exportacionMaritimoBean.setExponNumerocontenedor(exportacion.getNumeroContenedor());
		exportacionMaritimoBean.setExponFletetm(exportacion.getFleteTm());
		exportacionMaritimoBean.setExpovBooking(exportacion.getBooking());
		exportacionMaritimoBean.setExpovBl(exportacion.getBl());
		exportacionMaritimoBean.setExpovTipoenvio(exportacion.getTipoEnvio());
		exportacionMaritimoBean.setExpovGuiadhl(exportacion.getGuiaDhl());
		exportacionMaritimoBean.setExpovDam(exportacion.getDam());
		exportacionMaritimoBean.setExponIdregimen(exportacion.getIdRegimen() == 0 ? null : exportacion.getIdRegimen());
		exportacionMaritimoBean.setExpodFechaentrega(exportacion.getFechaEntrega());
		exportacionMaritimoBean.setExpodFechadam(exportacion.getFechaDam());
		exportacionMaritimoBean.setExpodFechadamregularizacion(exportacion.getFechaDamRegularizacion());
		exportacionMaritimoBean.setExpodFechadam41(exportacion.getFechaDam41());
		exportacionMaritimoBean.setExpodFecharecepciondam41(exportacion.getFechaRecepcionDam41());
		exportacionMaritimoBean.setExpodFechamanifiestoaduana(exportacion.getFechaManifAduana());
		exportacionMaritimoBean.setExponIdestadocontrolgasto(
				exportacion.getIdEstadoContGast() == 0 ? null : exportacion.getIdEstadoContGast());
		exportacionMaritimoBean.setExponIdestadopacking(
				exportacion.getIdEstadoPacking() == 0 ? null : exportacion.getIdEstadoPacking());
		exportacionMaritimoBean.setExpovEmitidoen(exportacion.getEmitidoEn());
		exportacionMaritimoBean.setExpovEmpaque(exportacion.getEmpaque());
		exportacionMaritimoBean.setExpovMarca(exportacion.getMarca());
		exportacionMaritimoBean.setExpovUnidadproductiva(exportacion.getUnidadProductiva());
		exportacionMaritimoBean.setExpovPreciounitario(exportacion.getPrecioUnitario());
		exportacionMaritimoBean.setExpovFacturacion(exportacion.getFacturacion());
		exportacionMaritimoBean.setExpovFormapago(exportacion.getFormaPago());
		exportacionMaritimoBean.setExpodFechafacturapacking(exportacion.getFechaFacturaPacking());
		exportacionMaritimoBean.setExpovEtiquetaempaque(exportacion.getEtiquetaEmpaque());
		exportacionMaritimoBean.setExpovEtiquetamarca(exportacion.getEtiquetaMarca());
		exportacionMaritimoBean.setExpovEtiquetaunidadproductiva(exportacion.getEtiquetaUnidProd());
		exportacionMaritimoBean.setExpovEtiquetapreciounitario(exportacion.getEtiquetaPrecioUnitario());
		exportacionMaritimoBean.setExpovEtiquetafacturacion(exportacion.getEtiquetaFacturacion());
		exportacionMaritimoBean.setExpovEtiquetaformapago(exportacion.getEtiquetaFormaPago());
		exportacionMaritimoBean.setExponEstado(Constantes.ACTIVO);
		exportacionMaritimoBean.setExpovUsuariocreacion(usuario);
		exportacionMaritimoBean.setExpodFechacreacion(new Date());
		exportacionMaritimoBean.setExponIntercompany(exportacion.getIntercompany());
		this.comexstExportacionMapper.insert(exportacionMaritimoBean);
		String codigoPacking = Util.ManualGenerateCode(String.valueOf(id), Constantes.CODE_TABLE_PACKING,
				Constantes.LENGTH_TABLE_PACKING);
		this.exportacionMaritimoExtendedMapper.actualizarCodigoPacking(id, codigoPacking);
		this.registrarExportacionPedido(request.getPosiciones(), id, usuario);
		if (request.getFacturas() != null) {
			this.registrarExportacionFactura(request.getFacturas(), id, usuario);
		}
		if(request.getEtiquetas() != null) {
			this.registrarExportacionEtiqueta(request.getEtiquetas(), id, usuario);
		}
		return id;
	}

	@Override
	public int obtenerSecuencia(String nombreTabla) {
		return tablaSecuenciaExtendedMapper.obtenerSecuencia(AplicacionEnum.APLICACION.getValue(), nombreTabla);
	}

	@Override
	public void registrarExportacionPedido(List<ExportacionMaritimoPosicionTO> posicion, int idExportacion,
			String usuario) {
		for (ExportacionMaritimoPosicionTO posicionPedido : posicion) {
			ComexstExportacionpedido posicionPedidoBean = new ComexstExportacionpedido();
			if (posicionPedido.getId() > 0 && posicionPedido.getEstado() == 0) {// Eliminar
				posicionPedidoBean = this.comexstExportacionpedidoMapper.selectByPrimaryKey(posicionPedido.getId());
				posicionPedidoBean.setEpednEstado(Constantes.INACTIVO);
				posicionPedidoBean.setEpedvUsuariomodificacion(usuario);
				posicionPedidoBean.setEpeddFechamodificacion(new Date());
				this.comexstExportacionpedidoMapper.updateByPrimaryKey(posicionPedidoBean);
			} else {// Agregar - Actualizar

				int idUnidadMedidaVenta = 0;

				// Unidad Medida
				int unidadMedidaExiste = this.unidadMedidaExtendedMapper
						.buscarUnidadMedidaxCodigoSap(posicionPedido.getUnidadMedidaVenta().trim());
				if (unidadMedidaExiste == 0) {
					ComexstUnidadmedida unidadMedida = new ComexstUnidadmedida();
					int intL_secuencia2 = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_UNIDADMEDIDA.getValue());
					unidadMedida.setUmednId(intL_secuencia2);
					unidadMedida.setUmedvCodigosap(posicionPedido.getUnidadMedidaVenta());
					unidadMedida.setUmedvUnidadmedida(posicionPedido.getUnidadMedidaVenta());
					unidadMedida.setUmeddFechacreacion(new Date());
					unidadMedida.setUmedvUsuariocreacion(usuario);
					unidadMedida.setUmednEstado(Constantes.ACTIVO);
					this.comexstUnidadmedidaMapper.insert(unidadMedida);
					idUnidadMedidaVenta = intL_secuencia2;
				} else {
					UnidadMedidaTO unidadMedida = this.unidadMedidaExtendedMapper
							.obtenerUnidadMedidaxCodigoSap(posicionPedido.getUnidadMedidaVenta());
					idUnidadMedidaVenta = unidadMedida.getIdUnidadMedida();
				}

				if (posicionPedido.getId() == 0) {
					int intL_secuencia = this
							.obtenerSecuencia(TablasAplicativoEnum.COMEXST_EXPORTACION_PEDIDO.getValue());
					posicionPedidoBean.setEpednId(intL_secuencia);
					posicionPedidoBean.setEpednIdexportacion(idExportacion);
					posicionPedidoBean.setEpednIdposicion(posicionPedido.getIdPosicion());
					
					posicionPedidoBean.setEpedvPedidosap(posicionPedido.getPedidoSap());
					posicionPedidoBean.setEpedvCodigosap(posicionPedido.getCodigoSAP());
					
					posicionPedidoBean.setEpedvDescripcioncomercial(posicionPedido.getDescripcionComercialProducto());
					posicionPedidoBean
							.setEpednIdmoneda(posicionPedido.getIdMoneda() == 0 ? null : posicionPedido.getIdMoneda());

					posicionPedidoBean.setEpednIdunidadmedida(
							posicionPedido.getIdUnidadMedida() == 0 ? null : posicionPedido.getIdUnidadMedida());
					posicionPedidoBean.setEpednCantidad(posicionPedido.getCantidad());

					posicionPedidoBean.setEpednIdunidadmedidaventa(idUnidadMedidaVenta);
					posicionPedidoBean.setEpednCantidadventa(posicionPedido.getCantidadVenta());
					posicionPedidoBean.setEpednPreciounitario(posicionPedido.getPrecioUnitario());

					posicionPedidoBean
							.setEpednIdunidadmedidaconversion(posicionPedido.getIdUnidadMedidaConv() == 0 ? null
									: posicionPedido.getIdUnidadMedidaConv());
					posicionPedidoBean.setEpednCantidadconversion(posicionPedido.getCantidadConversion());
					posicionPedidoBean.setEpednPreciounitarioconversion(posicionPedido.getPrecioUnitarioConv());

					posicionPedidoBean.setEpednIdalmacen(
							posicionPedido.getIdAlmacen() == 0 ? null : posicionPedido.getIdAlmacen());
					posicionPedidoBean.setEpednPesotonelada(posicionPedido.getPesoTonelada());
					posicionPedidoBean.setEpeddFechadisponibilidad(posicionPedido.getFechaDisponibilidad());
					posicionPedidoBean.setEpednImporte(posicionPedido.getImporte());
					posicionPedidoBean.setEpednIdpartidaarancelaria(posicionPedido.getIdPartidaArancelaria() == 0 ? null
							: posicionPedido.getIdPartidaArancelaria());
					posicionPedidoBean.setEpedvFolio(posicionPedido.getFolio());
					posicionPedidoBean.setEpedvReferencia(posicionPedido.getReferencia());
					posicionPedidoBean.setEpedvComponentetexto(posicionPedido.getComponenteTexto());
					posicionPedidoBean.setEpedvUsuariocreacion(usuario);
					posicionPedidoBean.setEpeddFechacreacion(new Date());
					posicionPedidoBean.setEpednEstado(Constantes.ACTIVO);
					this.comexstExportacionpedidoMapper.insert(posicionPedidoBean);
					this.registrarExportacionPosicionPedido(posicionPedido, intL_secuencia, usuario);
				} else {
					posicionPedidoBean = this.comexstExportacionpedidoMapper.selectByPrimaryKey(posicionPedido.getId());
					//posicionPedidoBean.setEpednId(posicionPedido.getId());
					//posicionPedidoBean.setEpednIdexportacion(idExportacion);
					//posicionPedidoBean.setEpednIdposicion(posicionPedido.getIdPosicion());
					posicionPedidoBean.setEpedvDescripcioncomercial(posicionPedido.getDescripcionComercialProducto());
					posicionPedidoBean
							.setEpednIdmoneda(posicionPedido.getIdMoneda() == 0 ? null : posicionPedido.getIdMoneda());

					posicionPedidoBean.setEpednIdunidadmedida(
							posicionPedido.getIdUnidadMedida() == 0 ? null : posicionPedido.getIdUnidadMedida());
					posicionPedidoBean.setEpednCantidad(posicionPedido.getCantidad());

					posicionPedidoBean.setEpednIdunidadmedidaventa(idUnidadMedidaVenta);
					posicionPedidoBean.setEpednCantidadventa(posicionPedido.getCantidadVenta());
					posicionPedidoBean.setEpednPreciounitario(posicionPedido.getPrecioUnitario());

					posicionPedidoBean
							.setEpednIdunidadmedidaconversion(posicionPedido.getIdUnidadMedidaConv() == 0 ? null
									: posicionPedido.getIdUnidadMedidaConv());
					posicionPedidoBean.setEpednCantidadconversion(posicionPedido.getCantidadConversion());
					posicionPedidoBean.setEpednPreciounitarioconversion(posicionPedido.getPrecioUnitarioConv());

					posicionPedidoBean.setEpednIdalmacen(
							posicionPedido.getIdAlmacen() == 0 ? null : posicionPedido.getIdAlmacen());
					posicionPedidoBean.setEpednPesotonelada(posicionPedido.getPesoTonelada());
					posicionPedidoBean.setEpeddFechadisponibilidad(posicionPedido.getFechaDisponibilidad());
					posicionPedidoBean.setEpednImporte(posicionPedido.getImporte());
					posicionPedidoBean.setEpednIdpartidaarancelaria(posicionPedido.getIdPartidaArancelaria() == 0 ? null
							: posicionPedido.getIdPartidaArancelaria());
					posicionPedidoBean.setEpedvPedidosap(posicionPedido.getPedidoSap());
					posicionPedidoBean.setEpedvBloqueo(posicionPedido.getBloqueo());
					posicionPedidoBean.setEpedvFolio(posicionPedido.getFolio());
					posicionPedidoBean.setEpedvReferencia(posicionPedido.getReferencia());
					posicionPedidoBean.setEpedvComponentetexto(posicionPedido.getComponenteTexto());
					posicionPedidoBean.setEpedvUsuariomodificacion(usuario);
					posicionPedidoBean.setEpeddFechamodificacion(new Date());
					posicionPedidoBean.setEpednEstado(Constantes.ACTIVO);
					this.comexstExportacionpedidoMapper.updateByPrimaryKey(posicionPedidoBean);
					this.registrarExportacionPosicionPedido(posicionPedido, posicionPedido.getId(), usuario);
				}
			}
		}
	}

	@Override
	public void registrarExportacionPosicionPedido(ExportacionMaritimoPosicionTO posicionPedido,
			int idExportacionPedido, String usuario) {
		ComexstPedidoexportacionposicion posicionPedidoBean = new ComexstPedidoexportacionposicion();
		BigDecimal cantidadVenta = new BigDecimal(0);
		if (posicionPedido.getCantidadConversion() != null
				&& posicionPedido.getCantidadConversion().floatValue() != 0) {
			cantidadVenta = posicionPedido.getCantidadConversion();
		} else {
			cantidadVenta = posicionPedido.getCantidadVenta();
		}
		int idPosicionExiste = this.buscarPedidoPosicion(posicionPedido.getIdPosicion(), idExportacionPedido, 1);
		if (idPosicionExiste == 0) {
			int intL_secuencia = this
					.obtenerSecuencia(TablasAplicativoEnum.COMEXST_EXPORTACION_PEDIDO_POSICION.getValue());
			posicionPedidoBean.setPeponId(intL_secuencia);
			posicionPedidoBean.setPeponIdpedidoposicion(posicionPedido.getIdPosicion());
			posicionPedidoBean.setPeponIdexportacionpedido(idExportacionPedido);
			posicionPedidoBean.setPeponCantidadventa(cantidadVenta);
			posicionPedidoBean.setPepovUsuariocreacion(usuario);
			posicionPedidoBean.setPepodFechacreacion(new Date());
			posicionPedidoBean.setPeponEstado(Constantes.ACTIVO);
			this.comexstPedidoexportacionposicionMapper.insert(posicionPedidoBean);
		} else {
			ExportacionMaritimoPosicionTO pedidoPosicion = this.obtenerPedidoPosicion(posicionPedido.getIdPosicion(),
					idExportacionPedido, 1);
			posicionPedidoBean.setPeponId(pedidoPosicion.getId());
			posicionPedidoBean.setPeponIdpedidoposicion(posicionPedido.getIdPosicion());
			posicionPedidoBean.setPeponIdexportacionpedido(idExportacionPedido);
			posicionPedidoBean.setPeponCantidadventa(cantidadVenta);
			posicionPedidoBean.setPepovUsuariomodificacion(usuario);
			posicionPedidoBean.setPepodFechamodificacion(new Date());
			posicionPedidoBean.setPeponEstado(Constantes.ACTIVO);
			this.comexstPedidoexportacionposicionMapper.updateByPrimaryKey(posicionPedidoBean);
		}
	}

	@Override
	public void registrarExportacionFactura(List<ExportacionFacturaTO> facturas, int idExportacion, String usuario) {
		for (ExportacionFacturaTO factura : facturas) {
			ComexstExportacionfactura comexstFactura = new ComexstExportacionfactura();
			if (factura.getId() == 0) {
				int intL_secuencia = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_EXPORTACION_FACTURA.getValue());
				comexstFactura.setEfacnId(intL_secuencia);
				comexstFactura.setEfacnIdexportacion(idExportacion);
				comexstFactura.setEfacnIdpedido(factura.getIdPedido());
				comexstFactura.setEfacvFactura(factura.getFactura());
				comexstFactura.setEfacvEtiquetatotal(factura.getEtiquetaTotal());
				comexstFactura.setEfacvEtiquetaflete(factura.getEtiquetaFlete());
				comexstFactura.setEfacvEtiquetaimportetotal(factura.getEtiquetaImporteTotal());
				comexstFactura.setEfacvEtiquetaunidadmedida(factura.getEtiquetaUnidadMedida());
				comexstFactura.setEfacnMontototal(factura.getMontoTotal());
				comexstFactura.setEfacnMontoflete(factura.getMontoFlete());
				comexstFactura.setEfacnMontoimportetotal(factura.getMontoImporteTotal());
				comexstFactura.setEfacvUsuariocreacion(usuario);
				comexstFactura.setEfacdFechacreacion(new Date());
				comexstFactura.setEfacnEstado(Constantes.ACTIVO);
				this.comexstExportacionfacturaMapper.insert(comexstFactura);
			} else {
				comexstFactura.setEfacnId(factura.getId());
				comexstFactura.setEfacnIdexportacion(idExportacion);
				comexstFactura.setEfacnIdpedido(factura.getIdPedido());
				comexstFactura.setEfacvFactura(factura.getFactura());
				comexstFactura.setEfacvEtiquetatotal(factura.getEtiquetaTotal());
				comexstFactura.setEfacvEtiquetaflete(factura.getEtiquetaFlete());
				comexstFactura.setEfacvEtiquetaimportetotal(factura.getEtiquetaImporteTotal());
				comexstFactura.setEfacvEtiquetaunidadmedida(factura.getEtiquetaUnidadMedida());
				comexstFactura.setEfacnMontototal(factura.getMontoTotal());
				comexstFactura.setEfacnMontoflete(factura.getMontoFlete());
				comexstFactura.setEfacnMontoimportetotal(factura.getMontoImporteTotal());
				comexstFactura.setEfacvUsuariomodificacion(usuario);
				comexstFactura.setEfacdFechamodificacion(new Date());
				this.comexstExportacionfacturaMapper.updateByPrimaryKeySelective(comexstFactura);
			}
		}
	}

	@Override
	public void registrarExportacionEtiqueta(List<ExportacionEtiquetaTO> etiquetas, int idExportacion, String usuario) {
		for (ExportacionEtiquetaTO etiqueta : etiquetas) {
			ComexstExportacionEtiqueta comexstEtiqueta = new ComexstExportacionEtiqueta();
			if(etiqueta.getId() == 0) {
				int id = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_EXPORTACION_ETIQUETA.getValue());
				comexstEtiqueta.setEetinId(id);
				comexstEtiqueta.setEetinIdExportacion(idExportacion);
				comexstEtiqueta.setEetinIdEtiqueta(etiqueta.getIdEtiqueta());
				comexstEtiqueta.setEetivContenido(etiqueta.getContenido());
				comexstEtiqueta.setEetinPosicion(etiqueta.getPosicion());
				comexstEtiqueta.setEetivUsuarioCreacion(usuario);
				comexstEtiqueta.setEetidFechaCreacion(new Date());
				comexstEtiqueta.setEetinEstado(Constantes.ACTIVO);
				comexstExportacionEtiquetaMapper.insert(comexstEtiqueta);
			} else {
				comexstEtiqueta.setEetinId(etiqueta.getId());
				comexstEtiqueta.setEetivUsuarioModificacion(usuario);
				comexstEtiqueta.setEetidFechaModificacion(new Date());
				
				if(etiqueta.getEstado() == Constantes.INACTIVO) {
					comexstEtiqueta.setEetinEstado(Constantes.INACTIVO);
				} else {				
					comexstEtiqueta.setEetinIdEtiqueta(etiqueta.getIdEtiqueta());
					comexstEtiqueta.setEetivContenido(etiqueta.getContenido());
					comexstEtiqueta.setEetinPosicion(etiqueta.getPosicion());
					comexstEtiqueta.setEetinEstado(Constantes.ACTIVO);
				}
				comexstExportacionEtiquetaMapper.updateByPrimaryKeySelective(comexstEtiqueta);
			}
		}
	}

	@Override
	public void registrarPosicionPedido(List<PedidoFirmePosicionTO> posiciones, String usuario, int codigoNuevoPedido,
			int moneda) {
		for (PedidoFirmePosicionTO posicionPedido : posiciones) {
			int idProducto = 0;
			int idUnidadMedida = 0;
			int idUnidadMedidaVenta = 0;

			// Producto
			int productoExiste = this.productoExtendedMapper.buscarProductoxCodigoSap(posicionPedido.getCodigoSAP());
			if (productoExiste == 0) {
				ComexstProducto producto = new ComexstProducto();
				int intL_secuencia1 = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_PRODUCTO.getValue());
				producto.setProdnId(intL_secuencia1);
				producto.setProdvCodigosap(posicionPedido.getCodigoSAP());
				producto.setProdvDescripcion(posicionPedido.getDescripcionProducto());
				producto.setProdvUnidadmedidabase(posicionPedido.getCodigoSAPUnidadMedida());
				producto.setProdvUnidadmedidaventa(posicionPedido.getCodigoSAPUnidadMedidaVenta());
				producto.setProddFechacreacion(new Date());
				producto.setProdvUsuariocreacion(usuario);
				producto.setProdnEstado(Constantes.ACTIVO);
				this.comexstProductoMapper.insert(producto);
				idProducto = intL_secuencia1;
			} else {
				ProductoTO producto = this.productoExtendedMapper
						.obtenerProductoxCodigoSap(posicionPedido.getCodigoSAP());
				idProducto = producto.getIdProducto();
			}

			// Unidad Medida
			int unidadMedidaExiste = this.unidadMedidaExtendedMapper
					.buscarUnidadMedidaxCodigoSap(posicionPedido.getCodigoSAPUnidadMedida().trim());
			if (unidadMedidaExiste == 0) {
				ComexstUnidadmedida unidadMedida = new ComexstUnidadmedida();
				int intL_secuencia2 = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_UNIDADMEDIDA.getValue());
				unidadMedida.setUmednId(intL_secuencia2);
				unidadMedida.setUmedvCodigosap(posicionPedido.getCodigoSAPUnidadMedida());
				unidadMedida.setUmedvUnidadmedida(posicionPedido.getCodigoSAPUnidadMedida());
				unidadMedida.setUmeddFechacreacion(new Date());
				unidadMedida.setUmedvUsuariocreacion(usuario);
				unidadMedida.setUmednEstado(Constantes.ACTIVO);
				this.comexstUnidadmedidaMapper.insert(unidadMedida);
				idUnidadMedida = intL_secuencia2;
			} else {
				UnidadMedidaTO unidadMedida = this.unidadMedidaExtendedMapper
						.obtenerUnidadMedidaxCodigoSap(posicionPedido.getCodigoSAPUnidadMedida());
				idUnidadMedida = unidadMedida.getIdUnidadMedida();
			}

			// Unidad Medida Venta
			int unidadMedidaVentaExiste = this.unidadMedidaExtendedMapper
					.buscarUnidadMedidaxCodigoSap(posicionPedido.getCodigoSAPUnidadMedidaVenta().trim());
			if (unidadMedidaVentaExiste == 0) {
				ComexstUnidadmedida unidadMedidaVenta = new ComexstUnidadmedida();
				int intL_secuencia3 = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_UNIDADMEDIDA.getValue());
				unidadMedidaVenta.setUmednId(intL_secuencia3);
				unidadMedidaVenta.setUmedvCodigosap(posicionPedido.getCodigoSAPUnidadMedidaVenta());
				unidadMedidaVenta.setUmedvUnidadmedida(posicionPedido.getCodigoSAPUnidadMedidaVenta());
				unidadMedidaVenta.setUmeddFechacreacion(new Date());
				unidadMedidaVenta.setUmedvUsuariocreacion(usuario);
				unidadMedidaVenta.setUmednEstado(Constantes.ACTIVO);
				this.comexstUnidadmedidaMapper.insert(unidadMedidaVenta);
				idUnidadMedidaVenta = intL_secuencia3;
			} else {
				UnidadMedidaTO unidadMedida = this.unidadMedidaExtendedMapper
						.obtenerUnidadMedidaxCodigoSap(posicionPedido.getCodigoSAPUnidadMedidaVenta());
				idUnidadMedidaVenta = unidadMedida.getIdUnidadMedida();
			}

			ComexstPedidoposicion posicion = new ComexstPedidoposicion();
			int intL_secuencia = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_PEDIDOPOSICION.getValue());
			posicion.setPposnId(intL_secuencia);
			posicion.setPposnIdpedido(posicionPedido.getIdPedido());
			posicion.setPposnIdalmacen(posicionPedido.getIdAlmacen() == 0 ? null : posicionPedido.getIdAlmacen());
			posicion.setPposnIdmoneda(moneda);
			posicion.setPposnItem(posicionPedido.getItem());
			posicion.setPposnIdproducto(idProducto);
			posicion.setPposnCantidad(posicionPedido.getCantidad());
			posicion.setPposnCantidadventa(posicionPedido.getCantidadVenta());
			posicion.setPposnCantidadsaldo(posicionPedido.getCantidadVenta());
			posicion.setPposnCantidadtonelada(posicionPedido.getCantidadTonelada());
			posicion.setPposnFactor(posicionPedido.getFactor());
			posicion.setPposnIdunidadmedida(idUnidadMedida);
			posicion.setPposnIdunidadmedidaventa(idUnidadMedidaVenta);
			posicion.setPposnPesotonelada(posicionPedido.getPesoTonelada());
			posicion.setPposnPesonominal(posicionPedido.getPesoNominal());
			posicion.setPposnPreciounitariosap(posicionPedido.getPrecioUnitarioSAP());
			posicion.setPposnPreciounitario(posicionPedido.getPrecioUnitario());
			posicion.setPposnImporte(posicionPedido.getImporte());
			posicion.setPposnPaquete(posicionPedido.getNroPaquete());
			posicion.setPposvUsuariocreacion(usuario);
			posicion.setPposdFechacreacion(new Date());
			posicion.setPposdFechadisponibilidad(posicionPedido.getFechaDisponibilidad());
			posicion.setPposnEstado(Constantes.ACTIVO);
			posicion.setPposvUsuariocreacion(usuario);
			posicion.setPposdFechacreacion(new Date());
			comexstPedidoposicionMapper.insert(posicion);
		}
	}

	@Override
	public List<ExportacionPedidoPosicionTO> listarPedidoPosicion(FiltroExportacionPedidoPosicionTO request) {
		return this.exportacionMaritimoExtendedMapper.listarPedidoPosicion(request);
	}

	@Override
	public void actualizarExportacionMaritimo(RequestExportacionMaritimoTO request, String usuario) {
		ExportacionMaritimoTO exportacion = request.getExportacion();
		
		ComexstExportacion exportacionMaritimoBean = new ComexstExportacion();
		exportacionMaritimoBean = this.comexstExportacionMapper.selectByPrimaryKey(exportacion.getId());
		exportacionMaritimoBean.setExponIddespacho(exportacion.getIdDespacho() == 0 ? null : exportacion.getIdDespacho());
		exportacionMaritimoBean.setExponIdruta(exportacion.getIdRuta() == 0 ? null : exportacion.getIdRuta());
		exportacionMaritimoBean.setExpovAgenteaduana(exportacion.getAgenteAduana());
		exportacionMaritimoBean.setExponIdagenteaduana(exportacion.getIdAgenteAduana() == 0 ? null : exportacion.getIdAgenteAduana());
		exportacionMaritimoBean.setExpovShipper(exportacion.getShipper());
		exportacionMaritimoBean.setExpovDireccionshipper(exportacion.getDireccionShipper());
		exportacionMaritimoBean.setExpovNotificante(exportacion.getNotificante());
		exportacionMaritimoBean.setExpovDireccionnotificante(exportacion.getDireccionNotificante());
		exportacionMaritimoBean.setExpovConsignatario(exportacion.getConsignatario());
		exportacionMaritimoBean.setExpovDireccionconsignatario(exportacion.getDireccionConsignatario());
		exportacionMaritimoBean.setExpovGlosa(exportacion.getGlosa());
		exportacionMaritimoBean.setExpovDescripcionTotal(exportacion.getDescripcionTotal());
		exportacionMaritimoBean.setExpovOrdenInterna(exportacion.getOrdenInterna());
		exportacionMaritimoBean.setExpovAgentecarga(exportacion.getAgenteCarga());
		exportacionMaritimoBean.setExpovAgentenaviera(exportacion.getAgenteNaviera());
		exportacionMaritimoBean.setExpovArmador(exportacion.getArmador());
		exportacionMaritimoBean.setExpovNave(exportacion.getNave());
		exportacionMaritimoBean.setExpodEtaorigen(exportacion.getEtaOrigen());
		exportacionMaritimoBean.setExpodEtadestino(exportacion.getEtaDestino());
		exportacionMaritimoBean.setExpodFechablprogramado(exportacion.getFechaBlProgramado());
		exportacionMaritimoBean.setExpodFechablreal(exportacion.getFechaBlReal());
		exportacionMaritimoBean.setExpodFechacarguio(exportacion.getFechaCarguio());
		exportacionMaritimoBean.setExpodFechamaximaingreso(exportacion.getFechaMaximaIngreso());
		exportacionMaritimoBean.setExpodFechaenviodocumento(exportacion.getFechaEnvioDocum());
		exportacionMaritimoBean
				.setExponIdmonedaflete(exportacion.getIdMonedaFlete() == 0 ? null : exportacion.getIdMonedaFlete());
		exportacionMaritimoBean.setExponFlete(exportacion.getFlete());
		exportacionMaritimoBean
				.setExponIdmonedaseguro(exportacion.getIdMonedaSeguro() == 0 ? null : exportacion.getIdMonedaSeguro());
		exportacionMaritimoBean.setExponSeguro(exportacion.getSeguro());
		exportacionMaritimoBean.setExponNumerocontenedor(exportacion.getNumeroContenedor());
		exportacionMaritimoBean.setExponFletetm(exportacion.getFleteTm());
		exportacionMaritimoBean.setExpovBooking(exportacion.getBooking());
		exportacionMaritimoBean.setExpovBl(exportacion.getBl());
		exportacionMaritimoBean.setExpovTipoenvio(exportacion.getTipoEnvio());
		exportacionMaritimoBean.setExpovGuiadhl(exportacion.getGuiaDhl());
		exportacionMaritimoBean.setExpovDam(exportacion.getDam());
		exportacionMaritimoBean.setExponIdregimen(exportacion.getIdRegimen() == 0 ? null : exportacion.getIdRegimen());
		exportacionMaritimoBean.setExpodFechaentrega(exportacion.getFechaEntrega());
		exportacionMaritimoBean.setExpodFechadam(exportacion.getFechaDam());
		exportacionMaritimoBean.setExpodFechadamregularizacion(exportacion.getFechaDamRegularizacion());
		exportacionMaritimoBean.setExpodFechadam41(exportacion.getFechaDam41());
		exportacionMaritimoBean.setExpodFecharecepciondam41(exportacion.getFechaRecepcionDam41());
		exportacionMaritimoBean.setExpodFechamanifiestoaduana(exportacion.getFechaManifAduana());
		exportacionMaritimoBean.setExponIdestadocontrolgasto(
				exportacion.getIdEstadoContGast() == 0 ? null : exportacion.getIdEstadoContGast());
		exportacionMaritimoBean.setExponIdestadopacking(
				exportacion.getIdEstadoPacking() == 0 ? null : exportacion.getIdEstadoPacking());
		exportacionMaritimoBean.setExpovEmitidoen(exportacion.getEmitidoEn());
		exportacionMaritimoBean.setExpodFechalistaprecio(exportacion.getFechaListaPrecio());
		exportacionMaritimoBean.setExpovEmpaque(exportacion.getEmpaque());
		exportacionMaritimoBean.setExpovMarca(exportacion.getMarca());
		exportacionMaritimoBean.setExpovUnidadproductiva(exportacion.getUnidadProductiva());
		exportacionMaritimoBean.setExpovPreciounitario(exportacion.getPrecioUnitario());
		exportacionMaritimoBean.setExpovFacturacion(exportacion.getFacturacion());
		exportacionMaritimoBean.setExpovFormapago(exportacion.getFormaPago());
		exportacionMaritimoBean.setExpodFechafacturapacking(exportacion.getFechaFacturaPacking());
		exportacionMaritimoBean.setExpovEtiquetaempaque(exportacion.getEtiquetaEmpaque());
		exportacionMaritimoBean.setExpovEtiquetamarca(exportacion.getEtiquetaMarca());
		exportacionMaritimoBean.setExpovEtiquetaunidadproductiva(exportacion.getEtiquetaUnidProd());
		exportacionMaritimoBean.setExpovEtiquetapreciounitario(exportacion.getEtiquetaPrecioUnitario());
		exportacionMaritimoBean.setExpovEtiquetafacturacion(exportacion.getEtiquetaFacturacion());
		exportacionMaritimoBean.setExpovEtiquetaformapago(exportacion.getEtiquetaFormaPago());
		exportacionMaritimoBean.setExponEstado(Constantes.ACTIVO);
		exportacionMaritimoBean.setExpovUsuariomodificacion(usuario);
		exportacionMaritimoBean.setExpodFechamodificacion(new Date());
		this.comexstExportacionMapper.updateByPrimaryKeySelective(exportacionMaritimoBean);
		this.registrarExportacionPedido(request.getPosiciones(), exportacion.getId(), usuario);
		if (request.getFacturas() != null) {
			this.registrarExportacionFactura(request.getFacturas(), exportacion.getId(), usuario);
		}
		if(request.getEtiquetas() != null) {
			this.registrarExportacionEtiqueta(request.getEtiquetas(), exportacion.getId(), usuario);
		}
	}

	@Override
	public int buscarPedidoPosicion(int idPedido, int idExportacion, int estadoDocumento) {
		return this.exportacionMaritimoExtendedMapper.buscarPedidoPosicion(idPedido, idExportacion, estadoDocumento);
	}

	@Override
	public ExportacionMaritimoPosicionTO obtenerPedidoPosicion(int idPedido, int idExportacion, int estadoDocumento) {
		return this.exportacionMaritimoExtendedMapper.obtenerPedidoPosicion(idPedido, idExportacion, estadoDocumento);
	}

	@Override
	public void confirmarExportacionMaritimo(int id, String usuario, int estadoDocumento) {
		this.exportacionMaritimoExtendedMapper.confirmarExportacionMaritimo(id, usuario, estadoDocumento);
	}

	@Override
	public List<ExportacionMaritimoPosicionTO> obtenerExportacionPedido(int id) {
		return this.exportacionMaritimoExtendedMapper.obtenerExportacionPedido(id);
	}

	@Override
	public void eliminarExportacionMaritimo(int id, String usuario) {
		this.exportacionMaritimoExtendedMapper.eliminarExportacionMaritimo(id, usuario);
	}

	@Override
	public void eliminarExportacionMaritimoPosicion(int idExportacion, int idPosicion, String usuario) {
		this.exportacionMaritimoExtendedMapper.eliminarExportacionMaritimoPosicion(idExportacion, idPosicion, usuario);
	}

	@Override
	public List<ExportacionFacturaTO> listarExportacionFacturaxIdExportacion(int idExportacion) {
		return this.exportacionMaritimoExtendedMapper.listarExportacionFacturaxIdExportacion(idExportacion);
	}

	@Override
	public List<ComponenteTO> listarComponentexIdPosicion(int idPosicion) {
		return this.exportacionMaritimoExtendedMapper.listarComponentexIdPosicion(idPosicion);
	}

	@Override
	public List<ExportacionOperacionTO> obtenerTotalPedidosExportacion() {
		Date fechaActual = new Date();
		String anioActual = DateUtil.obtenerAnioActual(fechaActual);

		int totalMaritimo = this.exportacionMaritimoExtendedMapper.obtenerTotalPedidosEnviados(anioActual);

		int totalTerrestre = this.exportacionMaritimoExtendedMapper.obtenerTotalPedidosTerrestresEnviados(anioActual);

		int totalPedidos = this.exportacionMaritimoExtendedMapper.obtenerPedidoFirmeConfirmado(anioActual);

		List<ExportacionOperacionTO> listadoExportacionOperacion = new ArrayList<ExportacionOperacionTO>();

		ExportacionOperacionTO exportacionOperacionTO = new ExportacionOperacionTO();
		exportacionOperacionTO.setName(Constantes.PEDIDOS_CONFIRMADOS);
		exportacionOperacionTO.setValue(totalPedidos);
		listadoExportacionOperacion.add(exportacionOperacionTO);

		exportacionOperacionTO = new ExportacionOperacionTO();
		exportacionOperacionTO.setName(Constantes.TOTAL_MARITIMO_ENVIADOS);
		exportacionOperacionTO.setValue(totalMaritimo);
		listadoExportacionOperacion.add(exportacionOperacionTO);

		exportacionOperacionTO = new ExportacionOperacionTO();
		exportacionOperacionTO.setName(Constantes.TOTAL_TERRESTRE_ENVIADO);
		exportacionOperacionTO.setValue(totalTerrestre);
		listadoExportacionOperacion.add(exportacionOperacionTO);

		return listadoExportacionOperacion;
	}

	@Override
	public List<ExportacionAgrupadoTO> obtenerTotalExportacion() {
		List<ExportacionAgrupadoTO> listadoExportacionOperacion = new ArrayList<ExportacionAgrupadoTO>();
		Date fechaActual = new Date();
		String anioActual = DateUtil.obtenerAnioActual(fechaActual);
		listadoExportacionOperacion.addAll(
				obtenerMesesExportacion(CalendarioEnum.ENERO.getValue(), CalendarioEnum.UNO.getValue(), anioActual));
		listadoExportacionOperacion.addAll(
				obtenerMesesExportacion(CalendarioEnum.FEBRERO.getValue(), CalendarioEnum.DOS.getValue(), anioActual));
		listadoExportacionOperacion.addAll(
				obtenerMesesExportacion(CalendarioEnum.MARZO.getValue(), CalendarioEnum.TRES.getValue(), anioActual));
		listadoExportacionOperacion.addAll(
				obtenerMesesExportacion(CalendarioEnum.ABRIL.getValue(), CalendarioEnum.CUATRO.getValue(), anioActual));
		listadoExportacionOperacion.addAll(
				obtenerMesesExportacion(CalendarioEnum.MAYO.getValue(), CalendarioEnum.CINCO.getValue(), anioActual));
		listadoExportacionOperacion.addAll(
				obtenerMesesExportacion(CalendarioEnum.JUNIO.getValue(), CalendarioEnum.SEIS.getValue(), anioActual));
		listadoExportacionOperacion.addAll(
				obtenerMesesExportacion(CalendarioEnum.JULIO.getValue(), CalendarioEnum.SIETE.getValue(), anioActual));
		listadoExportacionOperacion.addAll(
				obtenerMesesExportacion(CalendarioEnum.AGOSTO.getValue(), CalendarioEnum.OCHO.getValue(), anioActual));
		listadoExportacionOperacion.addAll(obtenerMesesExportacion(CalendarioEnum.SETIEMBRE.getValue(),
				CalendarioEnum.NUEVE.getValue(), anioActual));
		listadoExportacionOperacion.addAll(
				obtenerMesesExportacion(CalendarioEnum.OCTUBRE.getValue(), CalendarioEnum.DIEZ.getValue(), anioActual));
		listadoExportacionOperacion.addAll(obtenerMesesExportacion(CalendarioEnum.NOVIEMBRE.getValue(),
				CalendarioEnum.ONCE.getValue(), anioActual));
		listadoExportacionOperacion.addAll(obtenerMesesExportacion(CalendarioEnum.DICIEMBRE.getValue(),
				CalendarioEnum.DOCE.getValue(), anioActual));
		return listadoExportacionOperacion;
	}

	@Override
	public List<ExportacionPaisTO> obtenerTotalExportacionPais() {
		List<ExportacionPaisTO> listadoExportacionPais = new ArrayList<ExportacionPaisTO>();
		Date fechaActual = new Date();
		String anioActual = DateUtil.obtenerAnioActual(fechaActual);
		List<ExportacionMaritimoTO> listadoExportacion = this.exportacionMaritimoExtendedMapper
				.obtenerPaisExportacion(Constantes.INICIAL, anioActual);
		for (ExportacionMaritimoTO exportacion : listadoExportacion) {
			ExportacionPaisTO exportacionPais = new ExportacionPaisTO();
			exportacionPais.setValue(exportacion.getTotalPuertoDestino());
			exportacionPais.setName(exportacion.getPuertoDestino());

			listadoExportacionPais.add(exportacionPais);
		}

		return listadoExportacionPais;
	}

	@Override
	public List<ExportacionAgrupadoTO> obtenerMesesExportacion(String nombreMes, String numeroMes, String anio) {
		int totalContenedor = 0;
		int totalCargaSuelta = 0;
		List<ExportacionAgrupadoTO> listadoExportacionOperacionFiltro = new ArrayList<ExportacionAgrupadoTO>();
		totalContenedor = this.exportacionMaritimoExtendedMapper.obtenerTotalExportacion(
				Constantes.TIPO_TRANSPORTE_MARITIMO, Constantes.DESPACHO_CONTENEDOR, numeroMes, anio);
		totalCargaSuelta = this.exportacionMaritimoExtendedMapper.obtenerTotalExportacion(
				Constantes.TIPO_TRANSPORTE_MARITIMO, Constantes.DESPACHO_CARGA_SUELTA, numeroMes, anio);
		ExportacionAgrupadoTO exportacionOperacionTO = new ExportacionAgrupadoTO();
		List<SerieTO> listadoSerie = new ArrayList<SerieTO>();
		exportacionOperacionTO.setName(nombreMes);
		SerieTO serie = new SerieTO();
		serie.setName(Constantes.MARITIMO_CONTENEDOR);
		serie.setValue(totalContenedor);
		listadoSerie.add(serie);
		serie = new SerieTO();
		serie.setName(Constantes.MARITIMO_CARGA_SUELTA);
		serie.setValue(totalCargaSuelta);
		listadoSerie.add(serie);
		exportacionOperacionTO.setSeries(listadoSerie);
		listadoExportacionOperacionFiltro.add(exportacionOperacionTO);
		exportacionOperacionTO = new ExportacionAgrupadoTO();
		return listadoExportacionOperacionFiltro;
	}

	@Override
	public EntregaSapTO obtenerEntregaSap(String pedidoSap) {
		return exportacionMaritimoExtendedMapper.obtenerEntregaSap(pedidoSap);
	}

	@Override
	public FacturaSapTO obtenerFacturaSap(String pedidoSap) {
		return exportacionMaritimoExtendedMapper.obtenerFacturaSap(pedidoSap);
	}

	@Override
	public void guardarFacturaSap(ExportacionMaritimoTO documento, List<ExportacionMaritimoPosicionTO> posiciones,
			String pedidoSap) {
		String serie = null, serieCodigo = null, etiquetaSubTotal = "", etiquetaImporteTotal = "";
		boolean esNuevaEntrega = false;

		ExportacionMaritimoPosicionTO posicion = null;

		// Actualizar Entrega a las Posiciones
		EntregaSapTO entregaSap = this.obtenerEntregaSap(pedidoSap);
		if (entregaSap != null && entregaSap.getEntrega() != null) {
			for (ExportacionMaritimoPosicionTO posicionTo : posiciones) {
				if (!entregaSap.getEntrega().equalsIgnoreCase(posicionTo.getEntrega())) {
					ComexstExportacionpedido expPosicion = comexstExportacionpedidoMapper
							.selectByPrimaryKey(posicionTo.getId());
					expPosicion.setEpedvEntrega(entregaSap.getEntrega());
					comexstExportacionpedidoMapper.updateByPrimaryKey(expPosicion);
					posicionTo.setEntrega(entregaSap.getEntrega());
					esNuevaEntrega = true;
				}
			}
		}

		FacturaSapTO facturaSap = this.obtenerFacturaSap(pedidoSap);
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
			} else if ((documento.getCodigoIncoterm().equalsIgnoreCase(Constantes.FOB)
					&& documento.getCodigoIncotermComercial().equalsIgnoreCase(Constantes.CIF))
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
				int idFactura = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_FACTURA.getValue());
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
				int idExportacionFactura = this
						.obtenerSecuencia(TablasAplicativoEnum.COMEXST_EXPORTACION_FACTURA.getValue());
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
			} else if (existeFactura > 0 && esNuevaEntrega) {
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
	public String obtenerNumeroPedidoSAP(Cabecera cabeceraDocumento) {
		return exportacionMaritimoExtendedMapper.obtenerNumeroPedidoSAP(cabeceraDocumento);
	}

	@Override
	public void actualizarEntrega(int idExportacion, String pedidoSap) {
		EntregaSapTO entregaSap = this.obtenerEntregaSap(pedidoSap);
		if(entregaSap != null && entregaSap.getEntrega() != null) {
			ComexstExportacionpedidoExample query = new ComexstExportacionpedidoExample();
			query.createCriteria().andEpednIdexportacionEqualTo(idExportacion);
			
			List<ComexstExportacionpedido> posiciones = comexstExportacionpedidoMapper.selectByExample(query);
			if(posiciones != null) {
				posiciones.forEach(p -> {
					p.setEpedvEntrega(entregaSap.getEntrega());
					comexstExportacionpedidoMapper.updateByPrimaryKeySelective(p);
				});
			}
		}
	}

	@Override
	public List<ExportacionEtiquetaTO> listarExportacionEtiquetas(int idExportacion) {
		ComexstExportacionEtiquetaExample query = new ComexstExportacionEtiquetaExample();
		query
			.createCriteria()
				.andEetinIdExportacionEqualTo(idExportacion)
				.andEetinEstadoEqualTo(Constantes.ACTIVO);
		
		List<ComexstExportacionEtiqueta> listaExpEtiqueta = comexstExportacionEtiquetaMapper.selectByExample(query);
		listaExpEtiqueta.sort((e1, e2) -> Integer.compare(e1.getEetinId(), e2.getEetinId()));
		
		List<ExportacionEtiquetaTO> lista = new ArrayList<>();
		for (ComexstExportacionEtiqueta item : listaExpEtiqueta) {
			ComexstEtiqueta comexstEtiqueta = comexstEtiquetaMapper.selectByPrimaryKey(item.getEetinIdEtiqueta());
			if(comexstEtiqueta != null) {
				ExportacionEtiquetaTO etiqueta = new ExportacionEtiquetaTO();
				etiqueta.setId(item.getEetinId());
				etiqueta.setIdEtiqueta(item.getEetinIdEtiqueta());
				etiqueta.setNombreEtiqueta(comexstEtiqueta.getEtiqvNombre());
				etiqueta.setNombreInglesEtiqueta(comexstEtiqueta.getEtiqvNombreIngles());
				etiqueta.setContenido(item.getEetivContenido());
				etiqueta.setPosicion(item.getEetinPosicion());
				etiqueta.setEstado(item.getEetinEstado());
				lista.add(etiqueta);				
			}
		}
		
		return lista;
	}
}
