package com.caasa.comexsap.exportaciones.model.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.caasa.comexsap.exportaciones.enums.AplicacionEnum;
import com.caasa.comexsap.exportaciones.enums.TablasAplicativoEnum;
import com.caasa.comexsap.exportaciones.model.dao.PedidoFirmeDao;
import com.caasa.comexsap.exportaciones.model.domain.ComexstCliente;
import com.caasa.comexsap.exportaciones.model.domain.ComexstDestinatario;
import com.caasa.comexsap.exportaciones.model.domain.ComexstPedido;
import com.caasa.comexsap.exportaciones.model.domain.ComexstPedidoposicion;
import com.caasa.comexsap.exportaciones.model.domain.ComexstProducto;
import com.caasa.comexsap.exportaciones.model.domain.ComexstUnidadmedida;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstClienteMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstDestinatarioMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstPedidoMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstPedidoposicionMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstProductoMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstUnidadmedidaMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.ClienteExtendedMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.DestinatarioExtendedMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.PedidoFirmeExtendedMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.ProductoExtendedMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.TablaSecuenciaExtendedMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.UnidadMedidaExtendedMapper;
import com.caasa.comexsap.exportaciones.model.to.ClienteTO;
import com.caasa.comexsap.exportaciones.model.to.DestinatarioTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroPedidoFirmeRequestTO;
import com.caasa.comexsap.exportaciones.model.to.PedidoFirmePosicionTO;
import com.caasa.comexsap.exportaciones.model.to.PedidoFirmeSubPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.PedidoFirmeTO;
import com.caasa.comexsap.exportaciones.model.to.ProductoTO;
import com.caasa.comexsap.exportaciones.model.to.RequestPedidoFechasTO;
import com.caasa.comexsap.exportaciones.model.to.UnidadMedidaTO;
import com.caasa.comexsap.exportaciones.util.Constantes;

@Repository("PedidoFirmeDao")
public class PedidoFirmeDaoImpl implements PedidoFirmeDao {

	@Autowired
	private PedidoFirmeExtendedMapper pedidoFirmeExtendedMapper;

	@Autowired
	private TablaSecuenciaExtendedMapper tablaSecuenciaExtendedMapper;

	@Autowired
	private ClienteExtendedMapper clienteExtendedMapper;

	@Autowired
	private DestinatarioExtendedMapper destinatarioExtendedMapper;

	@Autowired
	private ComexstPedidoMapper comexstPedidoMapper;

	@Autowired
	private ComexstPedidoposicionMapper comexstPedidoposicionMapper;

	@Autowired
	private ComexstProductoMapper comexstProductoMapper;

	@Autowired
	private ProductoExtendedMapper productoExtendedMapper;

	@Autowired
	private UnidadMedidaExtendedMapper unidadMedidaExtendedMapper;

	@Autowired
	private ComexstUnidadmedidaMapper comexstUnidadmedidaMapper;

	@Autowired
	private ComexstClienteMapper comexstClienteMapper;

	@Autowired
	private ComexstDestinatarioMapper comexstDestinatarioMapper;

	@Override
	public List<PedidoFirmeTO> listarPedidoFirmexFiltro(FiltroPedidoFirmeRequestTO request) {
		return pedidoFirmeExtendedMapper.listarPedidoFirmexFiltro(request);
	}

	@Override
	public List<PedidoFirmePosicionTO> listarPosicionxPedidoFirme(int idPedido) {
		List<PedidoFirmePosicionTO> posiciones = pedidoFirmeExtendedMapper.listarPosicionxPedidoFirme(idPedido);
		if(posiciones != null) {
			for (PedidoFirmePosicionTO posicion : posiciones) {
				List<PedidoFirmeSubPosicionTO> subposiciones = pedidoFirmeExtendedMapper.listarSubPosicionPedidoFirme(posicion.getId());
				posicion.setSubPosicion(subposiciones);
			}
		}
		return posiciones;
	}

	@Override
	public int registrarPedidoFirme(PedidoFirmeTO pedido, String usuario, String codigoNuevoPedido,
			int estadoDocumento) {
		ComexstPedido pedidoBean = new ComexstPedido();
		int intL_secuencia = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_PEDIDO.getValue());
		pedidoBean.setPedinId(intL_secuencia);
		if (codigoNuevoPedido.trim() == Constantes.VACIO) {
			pedidoBean.setPedivCodigo(pedido.getCodigoPedido());
			pedidoBean.setPedidFechasolicitud(pedido.getFechaSolicitud());
			pedidoBean.setPedinGuardado(1);
		} else {
			pedidoBean.setPedivCodigo(codigoNuevoPedido);
			pedidoBean.setPedidFechasolicitud(new Date());
			pedidoBean.setPedinIdpadre(intL_secuencia);
			pedidoBean.setPedinGuardado(0);
		}
		pedidoBean.setPedinIdpuertoorigen(pedido.getIdPuertoOrigen() == 0 ? null : pedido.getIdPuertoOrigen());
		pedidoBean.setPedinIdpuertodestino(pedido.getIdPuertoDestino() == 0 ? null : pedido.getIdPuertoDestino());
       
		ClienteTO cliente = this.clienteExtendedMapper.obtenerClientexCodigoSap(pedido.getCodigoCliente());
		if (cliente != null) {
			pedidoBean.setPedinIdcliente(cliente.getIdCliente());
		} else {
			ComexstCliente clienteNuevo = new ComexstCliente();
			int intL_secuencia1 = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_CLIENTE.getValue());
			clienteNuevo.setClienId(intL_secuencia1);
			clienteNuevo.setClievCodigosap(pedido.getCodigoCliente());
			clienteNuevo.setClievNombre(pedido.getNombreCliente());
			clienteNuevo.setClievUsuariocreacion(usuario);
			clienteNuevo.setCliedFechacreacion(new Date());
			clienteNuevo.setClienEstado(Constantes.ACTIVO);
			this.comexstClienteMapper.insert(clienteNuevo);
			pedidoBean.setPedinIdcliente(intL_secuencia1);
		}

		if (!pedido.getCodigoDestinatario().equals(Constantes.VACIO)) {
			DestinatarioTO destinatario = this.destinatarioExtendedMapper
					.obtenerDestinatarioxCodigoSap(pedido.getCodigoDestinatario());
			if (destinatario != null) {
				pedidoBean.setPedinIddestinatario(destinatario.getIdDestinatario());
			} else {
				int intL_secuencia1 = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_DESTINATARIO.getValue());
				ComexstDestinatario destinatarioNuevo = new ComexstDestinatario();
				destinatarioNuevo.setDestnId(intL_secuencia1);
				destinatarioNuevo.setDestnIdcliente(pedidoBean.getPedinIdcliente());
				destinatarioNuevo.setDestvCodigosap(pedido.getCodigoDestinatario());
				destinatarioNuevo.setDestvNombre(pedido.getNombreDestinatario());
				destinatarioNuevo.setDestvUsuariocreacion(usuario);
				destinatarioNuevo.setDestdFechacreacion(new Date());
				destinatarioNuevo.setDestnEstado(Constantes.ACTIVO);
				this.comexstDestinatarioMapper.insert(destinatarioNuevo);
				pedidoBean.setPedinIddestinatario(intL_secuencia1);
			}
		}

		pedidoBean.setPedivNombrecliente(pedido.getNombreNuevoCliente());
		pedidoBean.setPedinIdtiposolicitud(Constantes.TIPO_SOLICITUD_PEDIDO);
		pedidoBean.setPedinIdincoterm(pedido.getIdIncoterm() == 0 ? null : pedido.getIdIncoterm());
		pedidoBean.setPedinIdtipotransporte(pedido.getIdTipoTransporte() == 0 ? null : pedido.getIdTipoTransporte());
		pedidoBean.setPedinIdcondicionpago(pedido.getIdCondicionPago() == 0 ? null : pedido.getIdCondicionPago());
		pedidoBean.setPedinIdlistaprecio(pedido.getIdListaPrecio() == 0 ? null : pedido.getIdListaPrecio());
		pedidoBean.setPedinIdmoneda(pedido.getIdMoneda() == 0 ? null : pedido.getIdMoneda());
		pedidoBean.setPedinIddespacho(pedido.getIdDespacho() == 0 ? null : pedido.getIdDespacho());
		pedidoBean.setPedinIdformapago(1);
		pedidoBean.setPedinIdestadodocumento(estadoDocumento);
		pedidoBean.setPedivObservacion(pedido.getObservacion());
		pedidoBean.setPedinImporteflete(pedido.getImporteFlete());
		pedidoBean.setPedinSeguro(pedido.getSeguroInternacional());
		pedidoBean.setPedinNumerocontenedor(pedido.getNumeroContenedor());
		pedidoBean.setPedivNombrecontacto(pedido.getNombreContacto());
		pedidoBean.setPedivCargocontacto(pedido.getCargoContacto());
		pedidoBean.setPedivCorreocontacto(pedido.getCorreoContacto());
		pedidoBean.setPedinVigenciaoferta(pedido.getVigenciaOferta());
		pedidoBean.setPedivTiempoentrega(pedido.getTiempoEntrega());
		pedidoBean.setPedivNombredestinatario(pedido.getNombreDestinatario());
		pedidoBean.setPedinIdlugardespacho(pedido.getIdLugarDespacho() == 0 ? null : pedido.getIdLugarDespacho());
		pedidoBean.setPedinIdfacturacion(pedido.getIdFacturacion() == 0 ? null : pedido.getIdFacturacion());
		pedidoBean.setPedinAnticipo(pedido.getAnticipo());
		pedidoBean.setPedinIdpesoobjetivo(pedido.getIdPesoObjetivo() == 0 ? null : pedido.getIdPesoObjetivo());
		pedidoBean.setPedinIdtoleranciaproduccion(
				pedido.getIdToleranciaProduccion() == 0 ? null : pedido.getIdToleranciaProduccion());
		pedidoBean.setPedinIdpesoetiqueta(pedido.getIdPesoEtiqueta() == 0 ? null : pedido.getIdPesoEtiqueta());
		pedidoBean.setPedivCondiciondescarga(pedido.getCondicionDescarga());
		pedidoBean.setPedinIdalmacenaje(pedido.getIdAlmacenaje() == 0 ? null : pedido.getIdAlmacenaje());
		pedidoBean.setPedivGastosoperativos(pedido.getGastosOperativos());
		pedidoBean.setPedidFechadesprequerido(pedido.getFechaDespachoRequerido());
		pedidoBean.setPedivDeal(pedido.getDeal());
		pedidoBean.setPedivLote(pedido.getLote());
		pedidoBean.setPedivDestinationport(pedido.getDestinationPort());
		pedidoBean.setPedidFechalistaprecio(pedido.getFechaListaPrecio());
		pedidoBean.setPedinIdincotermcomercial(
				pedido.getIdIncotermComercial() == 0 ? null : pedido.getIdIncotermComercial());

		pedidoBean.setPedivUsuariocreacion(usuario);
		pedidoBean.setPedidFechacreacion(new Date());
		pedidoBean.setPedidFechamodificacion(new Date());
		pedidoBean.setPedivUsuariomodificacion(usuario);
		pedidoBean.setPedinEstado(Constantes.ACTIVO);
		pedidoBean.setPedinIdruta(pedido.getIdRuta() == 0 ? null : pedido.getIdRuta());
		pedidoBean.setPedinIdcarpeta(pedido.getIdCarpeta() == 0 ? null : pedido.getIdCarpeta());
		this.comexstPedidoMapper.insert(pedidoBean);
		return intL_secuencia;
	}

	@Override
	public int obtenerSecuencia(String nombreTabla) {
		return tablaSecuenciaExtendedMapper.obtenerSecuencia(AplicacionEnum.APLICACION.getValue(), nombreTabla);
	}

	@Override
	public void actualizarPedidoFirme(PedidoFirmeTO pedido, List<PedidoFirmePosicionTO> posiciones, String usuario) {
		ComexstPedido pedidoBean = new ComexstPedido();
		pedidoBean = this.comexstPedidoMapper.selectByPrimaryKey(pedido.getId());

		// Cliente
		ClienteTO cliente = this.clienteExtendedMapper.obtenerClientexCodigoSap(pedido.getCodigoCliente());
		if (cliente != null) {
			pedidoBean.setPedinIdcliente(cliente.getIdCliente());
		} else {
			ComexstCliente clienteNuevo = new ComexstCliente();
			int intL_secuencia1 = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_CLIENTE.getValue());
			clienteNuevo.setClienId(intL_secuencia1);
			clienteNuevo.setClievCodigosap(pedido.getCodigoCliente());
			clienteNuevo.setClievNombre(pedido.getNombreCliente());
			clienteNuevo.setClievUsuariocreacion(usuario);
			clienteNuevo.setCliedFechacreacion(new Date());
			clienteNuevo.setClienEstado(Constantes.ACTIVO);
			this.comexstClienteMapper.insert(clienteNuevo);
			pedidoBean.setPedinIdcliente(intL_secuencia1);
		}

		// Destinatario
		if (!pedido.getCodigoDestinatario().equals(Constantes.VACIO)) {
			DestinatarioTO destinatario = this.destinatarioExtendedMapper
					.obtenerDestinatarioxCodigoSap(pedido.getCodigoDestinatario());
			if (destinatario != null) {
				pedidoBean.setPedinIddestinatario(destinatario.getIdDestinatario());
			} else {
				int intL_secuencia1 = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_DESTINATARIO.getValue());
				ComexstDestinatario destinatarioNuevo = new ComexstDestinatario();
				destinatarioNuevo.setDestnId(intL_secuencia1);
				destinatarioNuevo.setDestnIdcliente(pedidoBean.getPedinIdcliente());
				destinatarioNuevo.setDestvCodigosap(pedido.getCodigoDestinatario());
				destinatarioNuevo.setDestvNombre(pedido.getNombreDestinatario());
				destinatarioNuevo.setDestvUsuariocreacion(usuario);
				destinatarioNuevo.setDestdFechacreacion(new Date());
				destinatarioNuevo.setDestnEstado(Constantes.ACTIVO);
				this.comexstDestinatarioMapper.insert(destinatarioNuevo);
				pedidoBean.setPedinIddestinatario(intL_secuencia1);
			}
		}
		
		pedidoBean.setPedivNombrecliente(pedido.getNombreNuevoCliente());
		pedidoBean.setPedivCodigo(pedido.getCodigoPedido());
		pedidoBean.setPedinIdpuertoorigen(pedido.getIdPuertoOrigen() == 0 ? null : pedido.getIdPuertoOrigen());
		pedidoBean.setPedinIdpuertodestino(pedido.getIdPuertoDestino() == 0 ? null : pedido.getIdPuertoDestino());
		if (pedido.getTipoSolicitud() > 0) {
			pedidoBean.setPedinIdtiposolicitud(pedido.getTipoSolicitud());
		}
		if (pedido.getIdIncoterm() > 0) {
			pedidoBean.setPedinIdincoterm(pedido.getIdIncoterm());
		}
		if (pedido.getIdTipoTransporte() > 0) {
			pedidoBean.setPedinIdtipotransporte(pedido.getIdTipoTransporte());
		}
		if (pedido.getIdCondicionPago() > 0) {
			pedidoBean.setPedinIdcondicionpago(pedido.getIdCondicionPago());
		}
		if (pedido.getIdListaPrecio() > 0) {
			pedidoBean.setPedinIdlistaprecio(pedido.getIdListaPrecio());
		}
		if (pedido.getIdMoneda() > 0) {
			pedidoBean.setPedinIdmoneda(pedido.getIdMoneda());
		}
		if (pedido.getIdDespacho() > 0) {
			pedidoBean.setPedinIddespacho(pedido.getIdDespacho());
		}
		if (pedido.getIdFormaPago() > 0) {
			pedidoBean.setPedinIdformapago(pedido.getIdFormaPago());
		}
		if (pedido.getIdEstadoDocumento() > 0) {
			pedidoBean.setPedinIdestadodocumento(pedido.getIdEstadoDocumento());
		}
		pedidoBean.setPedinIdruta(pedido.getIdRuta() == 0 ? null : pedido.getIdRuta());
		pedidoBean.setPedivNombredestinatario(pedido.getNombreDestinatario());
		pedidoBean.setPedidFechasolicitud(pedido.getFechaSolicitud());
		pedidoBean.setPedivObservacion(pedido.getObservacion());
		pedidoBean.setPedidFechalistaprecio(pedido.getFechaListaPrecio());
		pedidoBean.setPedinImporteflete(pedido.getImporteFlete());
		pedidoBean.setPedinSeguro(pedido.getSeguroInternacional());
		pedidoBean.setPedinNumerocontenedor(pedido.getNumeroContenedor());
		pedidoBean.setPedinIdlugardespacho(pedido.getIdLugarDespacho() == 0 ? null : pedido.getIdLugarDespacho());
		pedidoBean.setPedinIdfacturacion(pedido.getIdFacturacion() == 0 ? null : pedido.getIdFacturacion());
		pedidoBean.setPedinAnticipo(pedido.getAnticipo());
		pedidoBean.setPedinIdpesoobjetivo(pedido.getIdPesoObjetivo() == 0 ? null : pedido.getIdPesoObjetivo());
		pedidoBean.setPedinIdtoleranciaproduccion(
				pedido.getIdToleranciaProduccion() == 0 ? null : pedido.getIdToleranciaProduccion());
		pedidoBean.setPedinIdpesoetiqueta(pedido.getIdPesoEtiqueta() == 0 ? null : pedido.getIdPesoEtiqueta());
		pedidoBean.setPedivCondiciondescarga(pedido.getCondicionDescarga());
		pedidoBean.setPedinIdalmacenaje(pedido.getIdAlmacenaje() == 0 ? null : pedido.getIdAlmacenaje());
		pedidoBean.setPedivGastosoperativos(pedido.getGastosOperativos());
		pedidoBean.setPedidFechadespachorequerida(pedido.getFechaDespachoRequerido());
		pedidoBean.setPedivDeal(pedido.getDeal());
		pedidoBean.setPedivLote(pedido.getLote());
		pedidoBean.setPedivDestinationport(pedido.getDestinationPort());
		pedidoBean.setPedidFechalistaprecio(pedido.getFechaListaPrecio());
		pedidoBean.setPedinIdincotermcomercial(
				pedido.getIdIncotermComercial() == 0 ? null : pedido.getIdIncotermComercial());
		pedidoBean.setPedinIdcarpeta(pedido.getIdCarpeta() == 0 ? null : pedido.getIdCarpeta());
		pedidoBean.setPedivUsuariomodificacion(usuario);
		pedidoBean.setPedidFechamodificacion(new Date());
		pedidoBean.setPedinEstado(Constantes.ACTIVO);
		this.comexstPedidoMapper.updateByPrimaryKey(pedidoBean);
		List<PedidoFirmePosicionTO> listaPosiciones = posiciones;
		for (PedidoFirmePosicionTO posicionPedido : listaPosiciones) {
			int idProducto = 0;
			int idUnidadMedida = 0;
			int idUnidadMedidaVenta = 0;

			ComexstPedidoposicion posicion = new ComexstPedidoposicion();
			if (posicionPedido.getId() > 0 && posicionPedido.getEstado() == 0) {// Eliminar
				posicion = this.comexstPedidoposicionMapper.selectByPrimaryKey(posicionPedido.getId());
				posicion.setPposnEstado(Constantes.INACTIVO);
				posicion.setPposvUsuariomodificacion(usuario);
				posicion.setPposdFechamodificacion(new Date());
				comexstPedidoposicionMapper.updateByPrimaryKey(posicion);
			} else {// Agregar - Actualizar
				// Producto
				if(posicionPedido.getCodigoSAP() != null) {
					int productoExiste = this.productoExtendedMapper
							.buscarProductoxCodigoSap(posicionPedido.getCodigoSAP());
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
	
					if (posicionPedido.getId() == 0) {
						int intL_secuencia = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_PEDIDOPOSICION.getValue());
						posicion.setPposnId(intL_secuencia);
						posicion.setPposnIdpedido(pedidoBean.getPedinId());
						posicion.setPposnItem(posicionPedido.getItem());
						posicion.setPposnIdmoneda(pedido.getIdMoneda());
						posicion.setPposnIdproducto(idProducto);
						posicion.setPposnCantidad(posicionPedido.getCantidad());
						posicion.setPposnCantidadventa(posicionPedido.getCantidadVenta());
						posicion.setPposnIdunidadmedida(idUnidadMedida);
						posicion.setPposnIdunidadmedidaventa(idUnidadMedidaVenta);
						posicion.setPposnPesotonelada(posicionPedido.getPesoTonelada());
						posicion.setPposnPesonominal(posicionPedido.getPesoNominal());
						posicion.setPposnPreciounitariosap(posicionPedido.getPrecioUnitarioSAP());
						posicion.setPposnPreciounitario(posicionPedido.getPrecioUnitario());
						posicion.setPposnImporte(posicionPedido.getImporte());
						posicion.setPposdFechadisponibilidad(posicionPedido.getFechaDisponibilidad());
						posicion.setPposnEstado(Constantes.ACTIVO);
						posicion.setPposvUsuariocreacion(usuario);
						posicion.setPposdFechacreacion(new Date());
						comexstPedidoposicionMapper.insert(posicion);
						for(PedidoFirmeSubPosicionTO subposicion : posicionPedido.getSubPosicion()) {
							
							//Producto
							int productoExisteSub = this.productoExtendedMapper.buscarProductoxCodigoSap(subposicion.getCodigoSAP());
						    if(productoExisteSub == 0) {
						    	ComexstProducto producto = new ComexstProducto();
								int intL_secuencia1 = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_PRODUCTO.getValue());
						    	producto.setProdnId(intL_secuencia1);
						    	producto.setProdvCodigosap(subposicion.getCodigoSAP());
						    	producto.setProdvDescripcion(subposicion.getDescripcionProducto());
						    	producto.setProdvUnidadmedidabase(subposicion.getCodigoSAPUnidadMedida());
						    	producto.setProdvUnidadmedidaventa(subposicion.getCodigoSAPUnidadMedidaVenta());
						    	producto.setProddFechacreacion(new Date());
						    	producto.setProdvUsuariocreacion(usuario);
						    	producto.setProdnEstado(Constantes.ACTIVO);
						        this.comexstProductoMapper.insert(producto);
						        idProducto = intL_secuencia1;
						    } else {
						    	ProductoTO producto = this.productoExtendedMapper
										.obtenerProductoxCodigoSap(subposicion.getCodigoSAP());
						    	idProducto = producto.getIdProducto();
						    }
						    
						    //Unidad Medida
						    int unidadMedidaExisteSub = this.unidadMedidaExtendedMapper.buscarUnidadMedidaxCodigoSap(subposicion.getCodigoSAPUnidadMedida().trim());
							if(unidadMedidaExisteSub == 0) {
								ComexstUnidadmedida unidadMedida = new ComexstUnidadmedida();
								int intL_secuencia2 = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_UNIDADMEDIDA.getValue());
								unidadMedida.setUmednId(intL_secuencia2);
								unidadMedida.setUmedvCodigosap(subposicion.getCodigoSAPUnidadMedida());
								unidadMedida.setUmedvUnidadmedida(subposicion.getCodigoSAPUnidadMedida());
								unidadMedida.setUmeddFechacreacion(new Date());
								unidadMedida.setUmedvUsuariocreacion(usuario);
								unidadMedida.setUmednEstado(Constantes.ACTIVO);
								this.comexstUnidadmedidaMapper.insert(unidadMedida);
								idUnidadMedida = intL_secuencia2;
							} else {
								UnidadMedidaTO unidadMedida = this.unidadMedidaExtendedMapper.obtenerUnidadMedidaxCodigoSap(subposicion.getCodigoSAPUnidadMedida());
								idUnidadMedida = unidadMedida.getIdUnidadMedida();
							}
							
							//Unidad Medida Venta
							int unidadMedidaVentaExisteSub = this.unidadMedidaExtendedMapper.buscarUnidadMedidaxCodigoSap(subposicion.getCodigoSAPUnidadMedidaVenta().trim());
							if(unidadMedidaVentaExisteSub == 0) {
								ComexstUnidadmedida unidadMedidaVenta = new ComexstUnidadmedida();
								int intL_secuencia3 = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_UNIDADMEDIDA.getValue());
								unidadMedidaVenta.setUmednId(intL_secuencia3);
								unidadMedidaVenta.setUmedvCodigosap(subposicion.getCodigoSAPUnidadMedidaVenta());
								unidadMedidaVenta.setUmedvUnidadmedida(subposicion.getCodigoSAPUnidadMedidaVenta());
								unidadMedidaVenta.setUmeddFechacreacion(new Date());
								unidadMedidaVenta.setUmedvUsuariocreacion(usuario);
								unidadMedidaVenta.setUmednEstado(Constantes.ACTIVO);
								this.comexstUnidadmedidaMapper.insert(unidadMedidaVenta);
								idUnidadMedidaVenta = intL_secuencia3;
							} else {
								UnidadMedidaTO unidadMedida = this.unidadMedidaExtendedMapper.obtenerUnidadMedidaxCodigoSap(subposicion.getCodigoSAPUnidadMedidaVenta());
								idUnidadMedidaVenta = unidadMedida.getIdUnidadMedida();
							}
							
							ComexstPedidoposicion subPosicion = new ComexstPedidoposicion();
							int intL_secuencia2 = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_PEDIDOPOSICION.getValue());
							subPosicion.setPposnId(intL_secuencia2);
							subPosicion.setPposnIdpedido(pedidoBean.getPedinId());
							//posicion.setPposnIdalmacen(posicionPedido.getIdAlmacen());
							subPosicion.setPposnIdmoneda(pedido.getIdMoneda());
							subPosicion.setPposnItem(subposicion.getItem());
							subPosicion.setPposnIdproducto(idProducto);
							subPosicion.setPposnCantidad(subposicion.getCantidad());
							subPosicion.setPposnCantidadventa(subposicion.getCantidadVenta());
							subPosicion.setPposnCantidadsaldo(subposicion.getCantidadVenta());
							subPosicion.setPposnCantidadtonelada(subposicion.getCantidadTonelada());
							subPosicion.setPposnFactor(subposicion.getFactor());
							subPosicion.setPposnIdunidadmedida(idUnidadMedida);
							subPosicion.setPposnIdunidadmedidaventa(idUnidadMedidaVenta);
							subPosicion.setPposnPesotonelada(subposicion.getPesoTonelada());
							subPosicion.setPposnPesonominal(subposicion.getPesoNominal());
							subPosicion.setPposnPreciounitariosap(subposicion.getPrecioUnitarioSAP());
							subPosicion.setPposnPreciounitario(subposicion.getPrecioUnitario());
							subPosicion.setPposnImporte(subposicion.getImporte());
							subPosicion.setPposdFechadisponibilidad(subposicion.getFechaDisponibilidad());
							subPosicion.setPposnEstado(Constantes.ACTIVO);
							subPosicion.setPposvUsuariocreacion(usuario);
							subPosicion.setPposdFechacreacion(new Date());
							subPosicion.setPposnIdpadre(intL_secuencia);
								comexstPedidoposicionMapper.insert(subPosicion);
						}
					} else {
						posicion = this.comexstPedidoposicionMapper.selectByPrimaryKey(posicionPedido.getId());
						posicion.setPposnItem(posicionPedido.getItem());
						posicion.setPposnIdproducto(idProducto);
						posicion.setPposnIdmoneda(pedido.getIdMoneda());
						posicion.setPposnCantidadventa(posicionPedido.getCantidadVenta());
						posicion.setPposnIdunidadmedida(idUnidadMedida);
						posicion.setPposnIdunidadmedidaventa(idUnidadMedidaVenta);
						posicion.setPposnPesotonelada(posicionPedido.getPesoTonelada());
						posicion.setPposnPesonominal(posicionPedido.getPesoNominal());
						posicion.setPposnPreciounitariosap(posicionPedido.getPrecioUnitarioSAP());
						posicion.setPposnPreciounitario(posicionPedido.getPrecioUnitario());
						posicion.setPposnImporte(posicionPedido.getImporte());
						posicion.setPposdFechadisponibilidad(posicionPedido.getFechaDisponibilidad());
						posicion.setPposvUsuariomodificacion(usuario);
						posicion.setPposdFechamodificacion(new Date());
						comexstPedidoposicionMapper.updateByPrimaryKey(posicion);
						for(PedidoFirmeSubPosicionTO subposicion : posicionPedido.getSubPosicion()) {
							
							//Producto
							int productoExisteSub = this.productoExtendedMapper.buscarProductoxCodigoSap(subposicion.getCodigoSAP());
						    if(productoExisteSub == 0) {
						    	ComexstProducto producto = new ComexstProducto();
								int intL_secuencia1 = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_PRODUCTO.getValue());
						    	producto.setProdnId(intL_secuencia1);
						    	producto.setProdvCodigosap(subposicion.getCodigoSAP());
						    	producto.setProdvDescripcion(subposicion.getDescripcionProducto());
						    	producto.setProdvUnidadmedidabase(subposicion.getCodigoSAPUnidadMedida());
						    	producto.setProdvUnidadmedidaventa(subposicion.getCodigoSAPUnidadMedidaVenta());
						    	producto.setProddFechacreacion(new Date());
						    	producto.setProdvUsuariocreacion(usuario);
						    	producto.setProdnEstado(Constantes.ACTIVO);
						        this.comexstProductoMapper.insert(producto);
						        idProducto = intL_secuencia1;
						    } else {
						    	ProductoTO producto = this.productoExtendedMapper
										.obtenerProductoxCodigoSap(subposicion.getCodigoSAP());
						    	idProducto = producto.getIdProducto();
						    }
						    
						    //Unidad Medida
						    int unidadMedidaExisteSub = this.unidadMedidaExtendedMapper.buscarUnidadMedidaxCodigoSap(subposicion.getCodigoSAPUnidadMedida().trim());
							if(unidadMedidaExisteSub == 0) {
								ComexstUnidadmedida unidadMedida = new ComexstUnidadmedida();
								int intL_secuencia2 = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_UNIDADMEDIDA.getValue());
								unidadMedida.setUmednId(intL_secuencia2);
								unidadMedida.setUmedvCodigosap(subposicion.getCodigoSAPUnidadMedida());
								unidadMedida.setUmedvUnidadmedida(subposicion.getCodigoSAPUnidadMedida());
								unidadMedida.setUmeddFechacreacion(new Date());
								unidadMedida.setUmedvUsuariocreacion(usuario);
								unidadMedida.setUmednEstado(Constantes.ACTIVO);
								this.comexstUnidadmedidaMapper.insert(unidadMedida);
								idUnidadMedida = intL_secuencia2;
							} else {
								UnidadMedidaTO unidadMedida = this.unidadMedidaExtendedMapper.obtenerUnidadMedidaxCodigoSap(subposicion.getCodigoSAPUnidadMedida());
								idUnidadMedida = unidadMedida.getIdUnidadMedida();
							}
							
							//Unidad Medida Venta
							int unidadMedidaVentaExisteSub = this.unidadMedidaExtendedMapper.buscarUnidadMedidaxCodigoSap(subposicion.getCodigoSAPUnidadMedidaVenta().trim());
							if(unidadMedidaVentaExisteSub == 0) {
								ComexstUnidadmedida unidadMedidaVenta = new ComexstUnidadmedida();
								int intL_secuencia3 = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_UNIDADMEDIDA.getValue());
								unidadMedidaVenta.setUmednId(intL_secuencia3);
								unidadMedidaVenta.setUmedvCodigosap(subposicion.getCodigoSAPUnidadMedidaVenta());
								unidadMedidaVenta.setUmedvUnidadmedida(subposicion.getCodigoSAPUnidadMedidaVenta());
								unidadMedidaVenta.setUmeddFechacreacion(new Date());
								unidadMedidaVenta.setUmedvUsuariocreacion(usuario);
								unidadMedidaVenta.setUmednEstado(Constantes.ACTIVO);
								this.comexstUnidadmedidaMapper.insert(unidadMedidaVenta);
								idUnidadMedidaVenta = intL_secuencia3;
							} else {
								UnidadMedidaTO unidadMedida = this.unidadMedidaExtendedMapper.obtenerUnidadMedidaxCodigoSap(subposicion.getCodigoSAPUnidadMedidaVenta());
								idUnidadMedidaVenta = unidadMedida.getIdUnidadMedida();
							}
							
							ComexstPedidoposicion subPosicion = new ComexstPedidoposicion();
							
							subPosicion.setPposnIdpedido(pedidoBean.getPedinId());
							//posicion.setPposnIdalmacen(posicionPedido.getIdAlmacen());
							subPosicion.setPposnIdmoneda(pedidoBean.getPedinIdmoneda());
							subPosicion.setPposnItem(subposicion.getItem());
							subPosicion.setPposnIdproducto(idProducto);
							subPosicion.setPposnCantidad(subposicion.getCantidad());
							subPosicion.setPposnCantidadventa(subposicion.getCantidadVenta());
							subPosicion.setPposnCantidadsaldo(subposicion.getCantidadVenta());
							subPosicion.setPposnCantidadtonelada(subposicion.getCantidadTonelada());
							subPosicion.setPposnFactor(subposicion.getFactor());
							subPosicion.setPposnIdunidadmedida(idUnidadMedida);
							subPosicion.setPposnIdunidadmedidaventa(idUnidadMedidaVenta);
							subPosicion.setPposnPesotonelada(subposicion.getPesoTonelada());
							subPosicion.setPposnPesonominal(subposicion.getPesoNominal());
							subPosicion.setPposnPreciounitariosap(subposicion.getPrecioUnitarioSAP());
							subPosicion.setPposnPreciounitario(subposicion.getPrecioUnitario());
							subPosicion.setPposnImporte(subposicion.getImporte());
							subPosicion.setPposdFechadisponibilidad(subposicion.getFechaDisponibilidad());
							subPosicion.setPposnEstado(Constantes.ACTIVO);
							subPosicion.setPposvUsuariocreacion(usuario);
							subPosicion.setPposdFechacreacion(new Date());
							subPosicion.setPposnIdpadre(posicionPedido.getId());
							if(subposicion.getId() == 0) {
								int intL_secuencia2 = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_PEDIDOPOSICION.getValue());
								subPosicion.setPposnId(intL_secuencia2);
								comexstPedidoposicionMapper.insert(subPosicion);
							}else {
								subPosicion.setPposnId(subposicion.getId());
								comexstPedidoposicionMapper.updateByPrimaryKey(subPosicion);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public PedidoFirmeTO obtenerPedidoFirmexIdPedido(int idPedido) {
		return this.pedidoFirmeExtendedMapper.obtenerPedidoFirmexIdPedido(idPedido);
	}

	@Override
	public void registrarPosicionPedido(List<PedidoFirmePosicionTO> posiciones, String usuario, int idPedido, int moneda, int duplicar) {
		for (PedidoFirmePosicionTO posicionPedido : posiciones) {
			int idProducto = 0;
			int idUnidadMedida = 0;
			int idUnidadMedidaVenta = 0;
			
			//Producto
			int productoExiste = this.productoExtendedMapper.buscarProductoxCodigoSap(posicionPedido.getCodigoSAP());
		    if(productoExiste == 0) {
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
		    
		    //Unidad Medida
		    int unidadMedidaExiste = this.unidadMedidaExtendedMapper.buscarUnidadMedidaxCodigoSap(posicionPedido.getCodigoSAPUnidadMedida().trim());
			if(unidadMedidaExiste == 0) {
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
				UnidadMedidaTO unidadMedida = this.unidadMedidaExtendedMapper.obtenerUnidadMedidaxCodigoSap(posicionPedido.getCodigoSAPUnidadMedida());
				idUnidadMedida = unidadMedida.getIdUnidadMedida();
			}
			
			//Unidad Medida Venta
			int unidadMedidaVentaExiste = this.unidadMedidaExtendedMapper.buscarUnidadMedidaxCodigoSap(posicionPedido.getCodigoSAPUnidadMedidaVenta().trim());
			if(unidadMedidaVentaExiste == 0) {
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
				UnidadMedidaTO unidadMedida = this.unidadMedidaExtendedMapper.obtenerUnidadMedidaxCodigoSap(posicionPedido.getCodigoSAPUnidadMedidaVenta());
				idUnidadMedidaVenta = unidadMedida.getIdUnidadMedida();
			}
			
			ComexstPedidoposicion posicion = new ComexstPedidoposicion();
			int intL_secuencia = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_PEDIDOPOSICION.getValue());
			posicion.setPposnId(intL_secuencia);
			posicion.setPposnIdpedido(idPedido);
			//posicion.setPposnIdalmacen(posicionPedido.getIdAlmacen());
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
			if(duplicar == 0) {
				posicion.setPposdFechadisponibilidad(posicionPedido.getFechaDisponibilidad());
			} else {
				posicion.setPposnPreciounitariosap(new BigDecimal(0));
				posicion.setPposdFechadisponibilidad(null);
			}
			posicion.setPposnEstado(Constantes.ACTIVO);
			posicion.setPposvUsuariocreacion(usuario);
			posicion.setPposdFechacreacion(new Date());
			comexstPedidoposicionMapper.insert(posicion);
			if(posicionPedido.getSubPosicion() != null) {
				for(PedidoFirmeSubPosicionTO subposicion : posicionPedido.getSubPosicion()) {
		
					//Producto
					int productoExisteSub = this.productoExtendedMapper.buscarProductoxCodigoSap(subposicion.getCodigoSAP());
				    if(productoExisteSub == 0) {
				    	ComexstProducto producto = new ComexstProducto();
						int intL_secuencia1 = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_PRODUCTO.getValue());
				    	producto.setProdnId(intL_secuencia1);
				    	producto.setProdvCodigosap(subposicion.getCodigoSAP());
				    	producto.setProdvDescripcion(subposicion.getDescripcionProducto());
				    	producto.setProdvUnidadmedidabase(subposicion.getCodigoSAPUnidadMedida());
				    	producto.setProdvUnidadmedidaventa(subposicion.getCodigoSAPUnidadMedidaVenta());
				    	producto.setProddFechacreacion(new Date());
				    	producto.setProdvUsuariocreacion(usuario);
				    	producto.setProdnEstado(Constantes.ACTIVO);
				        this.comexstProductoMapper.insert(producto);
				        idProducto = intL_secuencia1;
				    } else {
				    	ProductoTO producto = this.productoExtendedMapper
								.obtenerProductoxCodigoSap(subposicion.getCodigoSAP());
				    	idProducto = producto.getIdProducto();
				    }
				    
				    //Unidad Medida
				    int unidadMedidaExisteSub = this.unidadMedidaExtendedMapper.buscarUnidadMedidaxCodigoSap(subposicion.getCodigoSAPUnidadMedida().trim());
					if(unidadMedidaExisteSub == 0) {
						ComexstUnidadmedida unidadMedida = new ComexstUnidadmedida();
						int intL_secuencia2 = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_UNIDADMEDIDA.getValue());
						unidadMedida.setUmednId(intL_secuencia2);
						unidadMedida.setUmedvCodigosap(subposicion.getCodigoSAPUnidadMedida());
						unidadMedida.setUmedvUnidadmedida(subposicion.getCodigoSAPUnidadMedida());
						unidadMedida.setUmeddFechacreacion(new Date());
						unidadMedida.setUmedvUsuariocreacion(usuario);
						unidadMedida.setUmednEstado(Constantes.ACTIVO);
						this.comexstUnidadmedidaMapper.insert(unidadMedida);
						idUnidadMedida = intL_secuencia2;
					} else {
						UnidadMedidaTO unidadMedida = this.unidadMedidaExtendedMapper.obtenerUnidadMedidaxCodigoSap(subposicion.getCodigoSAPUnidadMedida());
						idUnidadMedida = unidadMedida.getIdUnidadMedida();
					}
					
					//Unidad Medida Venta
					int unidadMedidaVentaExisteSub = this.unidadMedidaExtendedMapper.buscarUnidadMedidaxCodigoSap(subposicion.getCodigoSAPUnidadMedidaVenta().trim());
					if(unidadMedidaVentaExisteSub == 0) {
						ComexstUnidadmedida unidadMedidaVenta = new ComexstUnidadmedida();
						int intL_secuencia3 = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_UNIDADMEDIDA.getValue());
						unidadMedidaVenta.setUmednId(intL_secuencia3);
						unidadMedidaVenta.setUmedvCodigosap(subposicion.getCodigoSAPUnidadMedidaVenta());
						unidadMedidaVenta.setUmedvUnidadmedida(subposicion.getCodigoSAPUnidadMedidaVenta());
						unidadMedidaVenta.setUmeddFechacreacion(new Date());
						unidadMedidaVenta.setUmedvUsuariocreacion(usuario);
						unidadMedidaVenta.setUmednEstado(Constantes.ACTIVO);
						this.comexstUnidadmedidaMapper.insert(unidadMedidaVenta);
						idUnidadMedidaVenta = intL_secuencia3;
					} else {
						UnidadMedidaTO unidadMedida = this.unidadMedidaExtendedMapper.obtenerUnidadMedidaxCodigoSap(subposicion.getCodigoSAPUnidadMedidaVenta());
						idUnidadMedidaVenta = unidadMedida.getIdUnidadMedida();
					}
					
					ComexstPedidoposicion subPosicion = new ComexstPedidoposicion();
					int intL_secuencia2 = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_PEDIDOPOSICION.getValue());
					subPosicion.setPposnId(intL_secuencia2);
					subPosicion.setPposnIdpedido(idPedido);
					//posicion.setPposnIdalmacen(posicionPedido.getIdAlmacen());
					subPosicion.setPposnIdmoneda(moneda);
					subPosicion.setPposnItem(subposicion.getItem());
					subPosicion.setPposnIdproducto(idProducto);
					subPosicion.setPposnCantidad(subposicion.getCantidad());
					subPosicion.setPposnCantidadventa(subposicion.getCantidadVenta());
					subPosicion.setPposnCantidadsaldo(subposicion.getCantidadVenta());
					subPosicion.setPposnCantidadtonelada(subposicion.getCantidadTonelada());
					subPosicion.setPposnFactor(subposicion.getFactor());
					subPosicion.setPposnIdunidadmedida(idUnidadMedida);
					subPosicion.setPposnIdunidadmedidaventa(idUnidadMedidaVenta);
					subPosicion.setPposnPesotonelada(subposicion.getPesoTonelada());
					subPosicion.setPposnPesonominal(subposicion.getPesoNominal());
					subPosicion.setPposnPreciounitariosap(subposicion.getPrecioUnitarioSAP());
					subPosicion.setPposnPreciounitario(subposicion.getPrecioUnitario());
					subPosicion.setPposnImporte(subposicion.getImporte());
					if(duplicar == 0) {
						subPosicion.setPposdFechadisponibilidad(subposicion.getFechaDisponibilidad());
					} else {
						subPosicion.setPposnPreciounitariosap(new BigDecimal(0));
						subPosicion.setPposdFechadisponibilidad(null);
					}
					subPosicion.setPposnEstado(Constantes.ACTIVO);
					subPosicion.setPposvUsuariocreacion(usuario);
					subPosicion.setPposdFechacreacion(new Date());
					subPosicion.setPposnIdpadre(intL_secuencia);
					comexstPedidoposicionMapper.insert(subPosicion);
				}
			}
		}
	}

	@Override
	public List<PedidoFirmeSubPosicionTO> listarSubPosicionPedidoFirme(int idPadre) {
		return this.pedidoFirmeExtendedMapper.listarSubPosicionPedidoFirme(idPadre);
	}

	@Override
	public void confirmarFechasDisponibilidad(RequestPedidoFechasTO formulario, String usuario) {
		List<PedidoFirmePosicionTO> posiciones = formulario.getPosiciones();
		if(posiciones != null) {
			for (PedidoFirmePosicionTO posicionTo : posiciones) {
				ComexstPedidoposicion posicion = this.comexstPedidoposicionMapper.selectByPrimaryKey(posicionTo.getId());
				if(posicion != null) {
					List<PedidoFirmeSubPosicionTO> componentes = posicionTo.getSubPosicion();
					if(componentes != null) {
						for (PedidoFirmeSubPosicionTO componenteTo : componentes) {
							ComexstPedidoposicion componente = new ComexstPedidoposicion();
							componente = this.comexstPedidoposicionMapper.selectByPrimaryKey(componenteTo.getId());
							if(componente != null) {
								componente.setPposdFechadisponibilidad(componenteTo.getFechaDisponibilidad());
								componente.setPposvUsuariomodificacion(usuario);
								componente.setPposdFechamodificacion(new Date());
								this.comexstPedidoposicionMapper.updateByPrimaryKey(componente);
							}
						}
					}
					
					posicion.setPposdFechadisponibilidad(posicionTo.getFechaDisponibilidad());
					posicion.setPposvUsuariomodificacion(usuario);
					posicion.setPposdFechamodificacion(new Date());
					this.comexstPedidoposicionMapper.updateByPrimaryKey(posicion);
				}
			}
			
			if(formulario.getConfirmar() == 1) {
				ComexstPedido pedido = this.comexstPedidoMapper.selectByPrimaryKey(formulario.getIdPedido());
				if(pedido != null) {
					pedido.setPedinIdestadodocumento(Constantes.CONFIRMADO);
					pedido.setPedivUsuariomodificacion(usuario);
					pedido.setPedidFechamodificacion(new Date());
					this.comexstPedidoMapper.updateByPrimaryKey(pedido);
				}
			}
		}
	}

	@Override
	public int pedidoFirmeConExportacion(int idPedido) {
		return this.pedidoFirmeExtendedMapper.pedidoFirmeConExportacion(idPedido);
	}
	
}
