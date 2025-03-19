package com.caasa.comexsap.exportaciones.model.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.caasa.comexsap.exportaciones.enums.AplicacionEnum;
import com.caasa.comexsap.exportaciones.enums.TablasAplicativoEnum;
import com.caasa.comexsap.exportaciones.model.dao.CotizacionDao;
import com.caasa.comexsap.exportaciones.model.domain.ComexstCliente;
import com.caasa.comexsap.exportaciones.model.domain.ComexstClienteNuevo;
import com.caasa.comexsap.exportaciones.model.domain.ComexstDestinatario;
import com.caasa.comexsap.exportaciones.model.domain.ComexstDestinatarioNuevo;
import com.caasa.comexsap.exportaciones.model.domain.ComexstPedido;
import com.caasa.comexsap.exportaciones.model.domain.ComexstPedidoposicion;
import com.caasa.comexsap.exportaciones.model.domain.ComexstProducto;
import com.caasa.comexsap.exportaciones.model.domain.ComexstUnidadmedida;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstClienteMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstClienteNuevoMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstDestinatarioMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstDestinatarioNuevoMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstPedidoMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstPedidoposicionMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstProductoMapper;
import com.caasa.comexsap.exportaciones.model.persistence.ComexstUnidadmedidaMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.ClienteExtendedMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.CotizacionExtendedMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.DestinatarioExtendedMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.ProductoExtendedMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.TablaSecuenciaExtendedMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.UnidadMedidaExtendedMapper;
import com.caasa.comexsap.exportaciones.model.to.ClienteTO;
import com.caasa.comexsap.exportaciones.model.to.CotizacionTO;
import com.caasa.comexsap.exportaciones.model.to.DestinatarioTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroCotizacionRequestTO;
import com.caasa.comexsap.exportaciones.model.to.PedidoTO;
import com.caasa.comexsap.exportaciones.model.to.PosicionPedidoTO;
import com.caasa.comexsap.exportaciones.model.to.ProductoTO;
import com.caasa.comexsap.exportaciones.model.to.RequestCotizacionTO;
import com.caasa.comexsap.exportaciones.model.to.RequestDetalleCotizacionTO;
import com.caasa.comexsap.exportaciones.model.to.UnidadMedidaTO;
import com.caasa.comexsap.exportaciones.util.Constantes;

@Repository("CotizacionDao")
public class CotizacionDaoImpl implements CotizacionDao {

	@Autowired
	private CotizacionExtendedMapper cotizacionExtendedMapper;

	@Autowired
	private ComexstPedidoMapper comexstPedidoMapper;

	@Autowired
	private ComexstPedidoposicionMapper comexstPedidoposicionMapper;

	@Autowired
	private TablaSecuenciaExtendedMapper tablaSecuenciaExtendedMapper;

	@Autowired
	private ComexstClienteMapper comexstClienteMapper;

	@Autowired
	private ClienteExtendedMapper clienteExtendedMapper;

	@Autowired
	private ComexstClienteNuevoMapper comexstClienteNuevoMapper;

	@Autowired
	private DestinatarioExtendedMapper destinatarioExtendedMapper;

	@Autowired
	private ComexstDestinatarioNuevoMapper comexstDestinatarioNuevoMapper;

	@Autowired
	private ComexstDestinatarioMapper comexstDestinatarioMapper;

	@Autowired
	private ProductoExtendedMapper productoExtendedMapper;

	@Autowired
	private ComexstProductoMapper comexstProductoMapper;

	@Autowired
	private UnidadMedidaExtendedMapper unidadMedidaExtendedMapper;

	@Autowired
	private ComexstUnidadmedidaMapper comexstUnidadmedidaMapper;

	@Override
	public List<CotizacionTO> listarCotizacionxFiltro(FiltroCotizacionRequestTO request) {
		return cotizacionExtendedMapper.listarCotizacionxFiltro(request);
	}

	@Override
	public int obtenerPedido(int codigoPedido) {
		return cotizacionExtendedMapper.obtenerPedido(codigoPedido);
	}

	@Override
	public void eliminarPedido(int codigoPedido, String usuario) {
		cotizacionExtendedMapper.eliminarPedido(codigoPedido, usuario);
	}

	@Override
	public List<CotizacionTO> listarCotizacionxPedido(RequestCotizacionTO request) {
		return cotizacionExtendedMapper.listarCotizacionxPedido(request);
	}

	@Override
	public CotizacionTO obtenerCotizacionxPedido(int idPedido) {
		return cotizacionExtendedMapper.listarCotizacionxPedido(idPedido);
	}

	@Override
	public List<PosicionPedidoTO> listarPosicionxPedido(RequestCotizacionTO request) {
		return cotizacionExtendedMapper.listarPosicionxPedido(request);
	}

	@Override
	public List<PosicionPedidoTO> listarPosicionxPedido(int idPedido) {
		return cotizacionExtendedMapper.listarPosicionxPedido(idPedido);
	}

	@Override
	public int obtenerPedidoxCodigo(String codigoNuevoPedido, int tipoSolicitud) {
		return cotizacionExtendedMapper.obtenerPedidoxCodigo(codigoNuevoPedido, tipoSolicitud);
	}

	@Override
	public int registrarCotizacion(CotizacionTO cotizacion, String usuario, String codigoNuevoPedido,
			int estadoDocumento) {
		int idCliente = 0;
		int idDestinatario = 0;
		ComexstPedido pedido = new ComexstPedido();
		int intL_secuencia = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_PEDIDO.getValue());
		pedido.setPedinId(intL_secuencia);
		pedido.setPedivCodigo(codigoNuevoPedido);
		if (codigoNuevoPedido.trim() == Constantes.VACIO) {
			pedido.setPedivCodigo(cotizacion.getCodigoPedido());
		} else {
			pedido.setPedivCodigo(codigoNuevoPedido);
		}
		pedido.setPedinIdpuertoorigen(cotizacion.getIdPuertoOrigen() == 0 ? null : cotizacion.getIdPuertoOrigen());
		pedido.setPedinIdpuertodestino(cotizacion.getIdPuertoDestino() == 0 ? null : cotizacion.getIdPuertoDestino());

		// Cliente
		if (cotizacion.isClienteSapExiste()) {
			int clienteExiste = this.clienteExtendedMapper.buscarClientexCodigoSap(cotizacion.getCodigoCliente());
			if (clienteExiste == 0) {
				ComexstCliente clienteNuevo = new ComexstCliente();
				int intL_secuencia1 = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_CLIENTE.getValue());
				clienteNuevo.setClienId(intL_secuencia1);
				clienteNuevo.setClievCodigosap(cotizacion.getCodigoCliente());
				clienteNuevo.setClievNombre(cotizacion.getNombreCliente());
				clienteNuevo.setClievUsuariocreacion(usuario);
				clienteNuevo.setCliedFechacreacion(new Date());
				clienteNuevo.setClienEstado(Constantes.ACTIVO);
				this.comexstClienteMapper.insert(clienteNuevo);
				idCliente = intL_secuencia1;
			} else {
				ClienteTO cliente = this.clienteExtendedMapper.obtenerClientexCodigoSap(cotizacion.getCodigoCliente());
				idCliente = cliente.getIdCliente();
			}
		} else {
			int clienteExiste = this.clienteExtendedMapper
					.buscarClienteNuevoxDescripcion(cotizacion.getNombreCliente());

			if (clienteExiste == 0) {
				ComexstClienteNuevo clienteNuevoExiste = this.comexstClienteNuevoMapper.selectByPrimaryKey(1);
				int intL_secuencia2 = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_CLIENTE_NUEVO.getValue());
				long valor_codigoSap = Long.parseLong(clienteNuevoExiste.getClievCodigosap()) + intL_secuencia2;
				ComexstClienteNuevo cliente = new ComexstClienteNuevo();
				cliente.setClienId(intL_secuencia2);
				cliente.setClievCodigosap(String.valueOf(valor_codigoSap));
				cliente.setClievNombre(cotizacion.getNombreCliente());
				cliente.setClievUsuariocreacion(usuario);
				cliente.setCliedFechacreacion(new Date());
				cliente.setClienEstado(Constantes.ACTIVO);
				this.comexstClienteNuevoMapper.insert(cliente);
				int intL_secuencia3 = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_CLIENTE.getValue());
				ComexstCliente clienteNuevo = new ComexstCliente();
				clienteNuevo.setClienId(intL_secuencia3);
				clienteNuevo.setClievCodigosap(String.valueOf(valor_codigoSap));
				clienteNuevo.setClievNombre(cotizacion.getNombreCliente());
				clienteNuevo.setClievUsuariocreacion(usuario);
				clienteNuevo.setCliedFechacreacion(new Date());
				clienteNuevo.setClienEstado(Constantes.ACTIVO);
				this.comexstClienteMapper.insert(clienteNuevo);
				idCliente = intL_secuencia3;
			} else {
				ClienteTO cliente = this.clienteExtendedMapper
						.obtenerClienteNuevoxDescripcion(cotizacion.getNombreCliente());
				idCliente = cliente.getIdCliente();
			}
		}
		pedido.setPedinIdcliente(idCliente);
		// Destinatario
		if (cotizacion.isClienteSapExiste()) {
			if (cotizacion.getCodigoDestinatario() != null
					&& !cotizacion.getCodigoDestinatario().trim().equals(Constantes.VACIO)) {
				int destinatarioExiste = this.destinatarioExtendedMapper
						.buscarDestinatarioxCodigoSap(cotizacion.getCodigoDestinatario());
				if (destinatarioExiste == 0) {
					int intL_secuencia1 = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_DESTINATARIO.getValue());
					ComexstDestinatario destinatarioNuevo = new ComexstDestinatario();
					destinatarioNuevo.setDestnId(intL_secuencia1);
					destinatarioNuevo.setDestnIdcliente(pedido.getPedinIdcliente());
					destinatarioNuevo.setDestvCodigosap(cotizacion.getCodigoDestinatario());
					destinatarioNuevo.setDestvNombre(cotizacion.getNombreDestinatario());
					destinatarioNuevo.setDestvUsuariocreacion(usuario);
					destinatarioNuevo.setDestdFechacreacion(new Date());
					destinatarioNuevo.setDestnEstado(Constantes.ACTIVO);
					this.comexstDestinatarioMapper.insert(destinatarioNuevo);
					idDestinatario = intL_secuencia1;
				} else {
					DestinatarioTO destinatario = this.destinatarioExtendedMapper
							.obtenerDestinatarioxCodigoSap(cotizacion.getCodigoDestinatario());
					idDestinatario = destinatario.getIdDestinatario();
				}
				pedido.setPedinIddestinatario(idDestinatario);
			} else {
				pedido.setPedinIddestinatario(null);
			}
		} else {
			if (cotizacion.getNombreDestinatario() != null && !cotizacion.getNombreDestinatario().trim().equals(Constantes.VACIO)) {
				int destinatarioExiste = this.destinatarioExtendedMapper
						.buscarDestinatarioNuevoxDescripcion(cotizacion.getNombreDestinatario().trim());
				if (destinatarioExiste == 0) {
					ComexstDestinatarioNuevo destinatarioNuevoExiste = this.comexstDestinatarioNuevoMapper
							.selectByPrimaryKey(1);
					int intL_secuencia2 = this
							.obtenerSecuencia(TablasAplicativoEnum.COMEXST_DESTINATARIO_NUEVO.getValue());
					long valor_codigoSap = Long.parseLong(destinatarioNuevoExiste.getDestvCodigosap())
							+ intL_secuencia2;
					ComexstDestinatarioNuevo destinatario = new ComexstDestinatarioNuevo();
					destinatario.setDestnId(intL_secuencia2);
					destinatario.setDestnIdcliente(pedido.getPedinIdcliente());
					destinatario.setDestvCodigosap(String.valueOf(valor_codigoSap));
					destinatario.setDestvNombre(cotizacion.getNombreDestinatario());
					destinatario.setDestvUsuariocreacion(usuario);
					destinatario.setDestdFechacreacion(new Date());
					destinatario.setDestnEstado(Constantes.ACTIVO);
					this.comexstDestinatarioNuevoMapper.insert(destinatario);

					int intL_secuencia3 = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_DESTINATARIO.getValue());
					ComexstDestinatario destinatarioNuevo = new ComexstDestinatario();
					destinatarioNuevo.setDestnId(intL_secuencia3);
					destinatarioNuevo.setDestnIdcliente(pedido.getPedinIdcliente());
					destinatarioNuevo.setDestvCodigosap(String.valueOf(valor_codigoSap));
					destinatarioNuevo.setDestvNombre(cotizacion.getNombreDestinatario());
					destinatarioNuevo.setDestvUsuariocreacion(usuario);
					destinatarioNuevo.setDestdFechacreacion(new Date());
					destinatarioNuevo.setDestnEstado(Constantes.ACTIVO);
					this.comexstDestinatarioMapper.insert(destinatarioNuevo);
					idDestinatario = intL_secuencia3;
				} else {
					DestinatarioTO destinatario = this.destinatarioExtendedMapper
							.obtenerDestinatarioxDescripcion(cotizacion.getNombreDestinatario().trim());
					idDestinatario = destinatario.getIdDestinatario();
				}
				pedido.setPedinIddestinatario(idDestinatario);
			} else {
				pedido.setPedinIddestinatario(null);
			}
		}

		pedido.setPedivNombrecliente(cotizacion.getNombreNuevoCliente());
		pedido.setPedinIdtiposolicitud(cotizacion.getTipoSolicitud() == 0 ? null : cotizacion.getTipoSolicitud());
		pedido.setPedinIdincoterm(cotizacion.getIdIncoterm() == 0 ? null : cotizacion.getIdIncoterm());
		pedido.setPedinIdincotermcomercial(cotizacion.getIdIncotermComercial() == 0 ? null : cotizacion.getIdIncotermComercial());
		pedido.setPedinIdtipotransporte(
				cotizacion.getIdTipoTransporte() == 0 ? null : cotizacion.getIdTipoTransporte());
		pedido.setPedinIdcondicionpago(cotizacion.getIdCondicionPago() == 0 ? null : cotizacion.getIdCondicionPago());
		pedido.setPedinIdlistaprecio(cotizacion.getIdListaPrecio() == 0 ? null : cotizacion.getIdListaPrecio());
		pedido.setPedinIdmoneda(cotizacion.getIdMoneda() == 0 ? null : cotizacion.getIdMoneda());
		pedido.setPedinIddespacho(cotizacion.getIdDespacho() == 0 ? null : cotizacion.getIdDespacho());
		pedido.setPedinIdformapago(cotizacion.getIdFormaPago() == 0 ? null : cotizacion.getIdFormaPago());
		pedido.setPedinIdestadodocumento(estadoDocumento);
		pedido.setPedidFechasolicitud(cotizacion.getFechaSolicitud());
		pedido.setPedivObservacion(cotizacion.getObservacion());
		pedido.setPedidFechalistaprecio(cotizacion.getFechaListaPrecio());
		pedido.setPedinImporteflete(cotizacion.getImporteFlete());
		pedido.setPedinSeguro(cotizacion.getSeguroInternacional());
		pedido.setPedinNumerocontenedor(cotizacion.getNumeroContenedor());
		pedido.setPedivNombrecontacto(cotizacion.getNombreContacto());
		pedido.setPedivCargocontacto(cotizacion.getCargoContacto());
		pedido.setPedivCorreocontacto(cotizacion.getCorreoContacto());
		pedido.setPedivCargocontactoadicional(cotizacion.getCargoContactoAdicional());
		pedido.setPedivNombrecontactoadicional(cotizacion.getNombreContactoAdicional());
		pedido.setPedivCorreocontactoadicional(cotizacion.getCorreoContactoAdicional());
		pedido.setPedinVigenciaoferta(cotizacion.getVigenciaOferta());
		pedido.setPedivTiempoentrega(cotizacion.getTiempoEntrega());
		pedido.setPedivNombredestinatario(cotizacion.getNombreDestinatario());
		pedido.setPedivUsuariocreacion(usuario);
		pedido.setPedidFechacreacion(new Date());
		pedido.setPedinEstado(Constantes.ACTIVO);
		pedido.setPedinIdruta(cotizacion.getIdRuta() == 0 ? null : cotizacion.getIdRuta());
		this.comexstPedidoMapper.insert(pedido);
		return intL_secuencia;
	}

	@Override
	public void registrarPosicioPedido(List<PosicionPedidoTO> posiciones, String usuario, int idPedido, int moneda) {
		for (PosicionPedidoTO posicionPedido : posiciones) {
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
			posicion.setPposnIdpedido(idPedido);
			// posicion.setPposnIdalmacen(posicionPedido.getIdAlmacen());
			posicion.setPposnIdmoneda(moneda);
			posicion.setPposnItem(posicionPedido.getItem());
			posicion.setPposnIdproducto(idProducto);
			posicion.setPposnCantidad(posicionPedido.getCantidad());
			posicion.setPposnCantidadventa(posicionPedido.getCantidadVenta());
			posicion.setPposnCantidadsaldo(posicionPedido.getCantidadVenta());
			posicion.setPposnCantidadtonelada(posicionPedido.getCantidadTonelada());
			posicion.setPposnFactor(posicionPedido.getFactor());
			posicion.setPposnPesonominal(posicionPedido.getPesoNominal());
			posicion.setPposnIdunidadmedida(idUnidadMedida);
			posicion.setPposnIdunidadmedidaventa(idUnidadMedidaVenta);
			posicion.setPposnPesotonelada(posicionPedido.getPesoTonelada());
			posicion.setPposnPreciounitariosap(posicionPedido.getPrecioUnitarioSAP());
			posicion.setPposnPreciounitario(posicionPedido.getPrecioUnitario());
			posicion.setPposnPaquete(posicionPedido.getNroPaquete());
			posicion.setPposnImporte(posicionPedido.getImporte());
			posicion.setPposdFechadisponibilidad(posicionPedido.getFechaDisponibilidad());
			posicion.setPposnEstado(Constantes.ACTIVO);
			posicion.setPposvUsuariocreacion(usuario);
			posicion.setPposdFechacreacion(new Date());
			comexstPedidoposicionMapper.insert(posicion);
		}
	}

	@Override
	public int obtenerSecuencia(String nombreTabla) {
		return tablaSecuenciaExtendedMapper.obtenerSecuencia(AplicacionEnum.APLICACION.getValue(), nombreTabla);
	}

	@Override
	public void confirmarPedido(int codigoPedido, String usuario, int estadoDocumento) {
		cotizacionExtendedMapper.confirmarPedido(codigoPedido, usuario, estadoDocumento);
	}

	@Override
	public int obtenerEstadoDocumentoxPedido(int codigoPedido, int estadoDocumento) {
		return cotizacionExtendedMapper.obtenerEstadoDocumentoxPedido(codigoPedido, estadoDocumento);
	}

	@Override
	public void actualizarCotizacion(RequestDetalleCotizacionTO request, String usuario) {
		ComexstPedido pedido = new ComexstPedido();
		int idCliente = 0;
		int idDestinatario = 0;
		CotizacionTO cotizacion = request.getCabecera();
		pedido = this.comexstPedidoMapper.selectByPrimaryKey(cotizacion.getIdCotizacion());

		// Cliente
		if (cotizacion.isClienteSapExiste()) {
			int clienteExiste = this.clienteExtendedMapper
					.buscarClientexCodigoSap(cotizacion.getCodigoCliente());
			if (clienteExiste == 0) {
				ComexstCliente clienteNuevo = new ComexstCliente();
				int intL_secuencia2 = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_CLIENTE.getValue());
				clienteNuevo.setClienId(intL_secuencia2);
				clienteNuevo.setClievCodigosap(cotizacion.getCodigoCliente());
				clienteNuevo.setClievNombre(cotizacion.getNombreCliente());
				clienteNuevo.setClievUsuariocreacion(usuario);
				clienteNuevo.setCliedFechacreacion(new Date());
				clienteNuevo.setClienEstado(Constantes.ACTIVO);
				this.comexstClienteMapper.insert(clienteNuevo);
				idCliente = intL_secuencia2;
			} else {
				ClienteTO cliente = this.clienteExtendedMapper
						.obtenerClientexCodigoSap(cotizacion.getCodigoCliente());
				idCliente = cliente.getIdCliente();
			}
		} else {
			int clienteExiste = this.clienteExtendedMapper
					.buscarClienteNuevoxDescripcion(cotizacion.getNombreCliente());

			if (clienteExiste == 0) {
				ComexstClienteNuevo clienteNuevoExiste = this.comexstClienteNuevoMapper.selectByPrimaryKey(1);
				int intL_secuencia3 = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_CLIENTE_NUEVO.getValue());
				long valor_codigoSap = Long.parseLong(clienteNuevoExiste.getClievCodigosap()) + intL_secuencia3;
				ComexstClienteNuevo cliente = new ComexstClienteNuevo();
				cliente.setClienId(intL_secuencia3);
				cliente.setClievCodigosap(String.valueOf(valor_codigoSap));
				cliente.setClievNombre(cotizacion.getNombreCliente());
				cliente.setClievUsuariocreacion(usuario);
				cliente.setCliedFechacreacion(new Date());
				cliente.setClienEstado(Constantes.ACTIVO);
				this.comexstClienteNuevoMapper.insert(cliente);

				int intL_secuencia4 = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_CLIENTE.getValue());
				ComexstCliente clienteNuevo = new ComexstCliente();
				clienteNuevo.setClienId(intL_secuencia4);
				clienteNuevo.setClievCodigosap(String.valueOf(valor_codigoSap));
				clienteNuevo.setClievNombre(cotizacion.getNombreCliente());
				clienteNuevo.setClievUsuariocreacion(usuario);
				clienteNuevo.setCliedFechacreacion(new Date());
				clienteNuevo.setClienEstado(Constantes.ACTIVO);
				this.comexstClienteMapper.insert(clienteNuevo);
				idCliente = intL_secuencia4;
			} else {
				ClienteTO cliente = this.clienteExtendedMapper
						.obtenerClienteNuevoxDescripcion(cotizacion.getNombreCliente());
				idCliente = cliente.getIdCliente();
			}
		}
		pedido.setPedinIdcliente(idCliente);

		// Destinatario
		if (cotizacion.isClienteSapExiste()) {
			if (cotizacion.getCodigoDestinatario() != null
					&& !cotizacion.getCodigoDestinatario().trim().equals(Constantes.VACIO)) {
				int destinatarioExiste = this.destinatarioExtendedMapper
						.buscarDestinatarioxCodigoSap(cotizacion.getCodigoDestinatario());
				if (destinatarioExiste == 0) {
					int intL_secuencia1 = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_DESTINATARIO.getValue());
					ComexstDestinatario destinatarioNuevo = new ComexstDestinatario();
					destinatarioNuevo.setDestnId(intL_secuencia1);
					destinatarioNuevo.setDestnIdcliente(pedido.getPedinIdcliente());
					destinatarioNuevo.setDestvCodigosap(cotizacion.getCodigoDestinatario());
					destinatarioNuevo.setDestvNombre(cotizacion.getNombreDestinatario());
					destinatarioNuevo.setDestvUsuariocreacion(usuario);
					destinatarioNuevo.setDestdFechacreacion(new Date());
					destinatarioNuevo.setDestnEstado(Constantes.ACTIVO);
					this.comexstDestinatarioMapper.insert(destinatarioNuevo);
					idDestinatario = intL_secuencia1;
				} else {
					DestinatarioTO destinatario = this.destinatarioExtendedMapper
							.obtenerDestinatarioxCodigoSap(cotizacion.getCodigoDestinatario());
					idDestinatario = destinatario.getIdDestinatario();
				}
				pedido.setPedinIddestinatario(idDestinatario);
			} else {
				pedido.setPedinIddestinatario(null);
			}
		} else {
			if (cotizacion.getNombreDestinatario() != null && !cotizacion.getNombreDestinatario().trim().equals(Constantes.VACIO)) {
				int destinatarioExiste = this.destinatarioExtendedMapper
						.buscarDestinatarioNuevoxDescripcion(cotizacion.getNombreDestinatario().trim());
				if (destinatarioExiste == 0) {
					ComexstDestinatarioNuevo destinatarioNuevoExiste = this.comexstDestinatarioNuevoMapper
							.selectByPrimaryKey(1);
					int intL_secuencia2 = this
							.obtenerSecuencia(TablasAplicativoEnum.COMEXST_DESTINATARIO_NUEVO.getValue());
					long valor_codigoSap = Long.parseLong(destinatarioNuevoExiste.getDestvCodigosap())
							+ intL_secuencia2;
					ComexstDestinatarioNuevo destinatario = new ComexstDestinatarioNuevo();
					destinatario.setDestnId(intL_secuencia2);
					destinatario.setDestnIdcliente(pedido.getPedinIdcliente());
					destinatario.setDestvCodigosap(String.valueOf(valor_codigoSap));
					destinatario.setDestvNombre(cotizacion.getNombreDestinatario());
					destinatario.setDestvUsuariocreacion(usuario);
					destinatario.setDestdFechacreacion(new Date());
					destinatario.setDestnEstado(Constantes.ACTIVO);
					this.comexstDestinatarioNuevoMapper.insert(destinatario);

					int intL_secuencia3 = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_DESTINATARIO.getValue());
					ComexstDestinatario destinatarioNuevo = new ComexstDestinatario();
					destinatarioNuevo.setDestnId(intL_secuencia3);
					destinatarioNuevo.setDestnIdcliente(pedido.getPedinIdcliente());
					destinatarioNuevo.setDestvCodigosap(String.valueOf(valor_codigoSap));
					destinatarioNuevo.setDestvNombre(cotizacion.getNombreDestinatario());
					destinatarioNuevo.setDestvUsuariocreacion(usuario);
					destinatarioNuevo.setDestdFechacreacion(new Date());
					destinatarioNuevo.setDestnEstado(Constantes.ACTIVO);
					this.comexstDestinatarioMapper.insert(destinatarioNuevo);
					idDestinatario = intL_secuencia3;
				} else {
					DestinatarioTO destinatario = this.destinatarioExtendedMapper
							.obtenerDestinatarioxDescripcion(cotizacion.getNombreDestinatario().trim());
					idDestinatario = destinatario.getIdDestinatario();
				}
				pedido.setPedinIddestinatario(idDestinatario);
			} else {
				pedido.setPedinIddestinatario(null);
			}
		}

		pedido.setPedivCodigo(cotizacion.getCodigoPedido());
		pedido.setPedivNombrecliente(cotizacion.getNombreNuevoCliente());
		pedido.setPedinIdpuertoorigen(cotizacion.getIdPuertoOrigen() == 0 ? null : cotizacion.getIdPuertoOrigen());
		pedido.setPedinIdpuertodestino(cotizacion.getIdPuertoDestino() == 0 ? null : cotizacion.getIdPuertoDestino());
		
		if (cotizacion.getTipoSolicitud() > 0) {
			pedido.setPedinIdtiposolicitud(cotizacion.getTipoSolicitud());
		}
		if (cotizacion.getIdIncoterm() > 0) {
			pedido.setPedinIdincoterm(cotizacion.getIdIncoterm());
		}
		if (cotizacion.getIdIncotermComercial() > 0) {
			pedido.setPedinIdincotermcomercial(cotizacion.getIdIncotermComercial());
		}
		if (cotizacion.getIdTipoTransporte() > 0) {
			pedido.setPedinIdtipotransporte(cotizacion.getIdTipoTransporte());
		}
		if (cotizacion.getIdCondicionPago() > 0) {
			pedido.setPedinIdcondicionpago(cotizacion.getIdCondicionPago());
		}
		if (cotizacion.getIdListaPrecio() > 0) {
			pedido.setPedinIdlistaprecio(cotizacion.getIdListaPrecio());
		}
		if (cotizacion.getIdMoneda() > 0) {
			pedido.setPedinIdmoneda(cotizacion.getIdMoneda());
		}
		if (cotizacion.getIdDespacho() > 0) {
			pedido.setPedinIddespacho(cotizacion.getIdDespacho());
		}
		if (cotizacion.getIdFormaPago() > 0) {
			pedido.setPedinIdformapago(cotizacion.getIdFormaPago());
		}
		if (cotizacion.getIdEstadoDocumento() > 0) {
			pedido.setPedinIdestadodocumento(cotizacion.getIdEstadoDocumento());
		}
		pedido.setPedinIdruta(cotizacion.getIdRuta() == 0 ? null : cotizacion.getIdRuta());
		pedido.setPedivNombredestinatario(cotizacion.getNombreDestinatario());
		pedido.setPedidFechasolicitud(cotizacion.getFechaSolicitud());
		pedido.setPedivObservacion(cotizacion.getObservacion());
		pedido.setPedidFechalistaprecio(cotizacion.getFechaListaPrecio());
		pedido.setPedinImporteflete(cotizacion.getImporteFlete());
		pedido.setPedinSeguro(cotizacion.getSeguroInternacional());
		pedido.setPedinNumerocontenedor(cotizacion.getNumeroContenedor());
		pedido.setPedivNombrecontacto(cotizacion.getNombreContacto());
		pedido.setPedivCargocontacto(cotizacion.getCargoContacto());
		pedido.setPedivCorreocontacto(cotizacion.getCorreoContacto());
		pedido.setPedivNombrecontactoadicional(cotizacion.getNombreContactoAdicional());
		pedido.setPedivCargocontactoadicional(cotizacion.getCargoContactoAdicional());
		pedido.setPedivCorreocontactoadicional(cotizacion.getCorreoContactoAdicional());
		pedido.setPedinVigenciaoferta(cotizacion.getVigenciaOferta());
		pedido.setPedivTiempoentrega(cotizacion.getTiempoEntrega());
		pedido.setPedivUsuariomodificacion(usuario);
		pedido.setPedidFechamodificacion(new Date());
		pedido.setPedinEstado(Constantes.ACTIVO);
		this.comexstPedidoMapper.updateByPrimaryKey(pedido);
		List<PosicionPedidoTO> posiciones = request.getPosiciones();
		for (PosicionPedidoTO posicionPedido : posiciones) {
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
					posicion.setPposnIdpedido(pedido.getPedinId());
					posicion.setPposnItem(posicionPedido.getItem());
					posicion.setPposnIdproducto(idProducto);
					posicion.setPposnCantidad(posicionPedido.getCantidadVenta());
					posicion.setPposnCantidadventa(posicionPedido.getCantidadVenta());
					posicion.setPposnIdunidadmedida(idUnidadMedida);
					posicion.setPposnIdunidadmedidaventa(idUnidadMedidaVenta);
					posicion.setPposnFactor(posicionPedido.getFactor());
					posicion.setPposnPesonominal(posicionPedido.getPesoNominal());
					posicion.setPposnCantidadtonelada(posicionPedido.getPesoTonelada());
					posicion.setPposnPesotonelada(posicionPedido.getPesoTonelada());
					posicion.setPposnPreciounitariosap(posicionPedido.getPrecioUnitarioSAP());
					posicion.setPposnPreciounitario(posicionPedido.getPrecioUnitario());
					posicion.setPposnImporte(posicionPedido.getImporte());
					posicion.setPposnEstado(Constantes.ACTIVO);
					posicion.setPposvUsuariocreacion(usuario);
					posicion.setPposdFechacreacion(new Date());
					comexstPedidoposicionMapper.insert(posicion);
				} else {
					posicion = this.comexstPedidoposicionMapper.selectByPrimaryKey(posicionPedido.getId());
					posicion.setPposnItem(posicionPedido.getItem());
					posicion.setPposnIdproducto(idProducto);
					posicion.setPposnCantidad(posicionPedido.getCantidad());
					posicion.setPposnCantidadventa(posicionPedido.getCantidadVenta());
					posicion.setPposnIdunidadmedida(idUnidadMedida);
					posicion.setPposnIdunidadmedidaventa(idUnidadMedidaVenta);
					posicion.setPposnFactor(posicionPedido.getFactor());
					posicion.setPposnPesonominal(posicionPedido.getPesoNominal());
					posicion.setPposnCantidadtonelada(posicionPedido.getPesoTonelada());
					posicion.setPposnPesotonelada(posicionPedido.getPesoTonelada());
					posicion.setPposnPreciounitariosap(posicionPedido.getPrecioUnitarioSAP());
					posicion.setPposnPreciounitario(posicionPedido.getPrecioUnitario());
					posicion.setPposnImporte(posicionPedido.getImporte());
					posicion.setPposvUsuariomodificacion(usuario);
					posicion.setPposdFechamodificacion(new Date());
					comexstPedidoposicionMapper.updateByPrimaryKey(posicion);
				}
			}
		}
	}

	@Override
	public int obtenerTipoSolicitudxPedido(int codigoPedido, String tipoSolicitud) {
		return cotizacionExtendedMapper.obtenerTipoSolicitudxPedido(codigoPedido, tipoSolicitud);
	}

	@Override
	public int registrarPedidoConfirmado(CotizacionTO cotizacion, String usuario, String codigoNuevoPedido,	int estadoDocumento) {
		ComexstPedido pedido = new ComexstPedido();
		pedido = comexstPedidoMapper.selectByPrimaryKey(cotizacion.getIdCotizacion());
		int id = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_PEDIDO.getValue());
		int idIncoterm = cotizacion.getIdIncotermComercial() == 0 ? cotizacion.getIdIncoterm() : cotizacion.getIdIncotermComercial();
		pedido.setPedinId(id);
		pedido.setPedivCodigo(codigoNuevoPedido);
		pedido.setPedinIdtiposolicitud(Constantes.TIPO_SOLICITUD_PEDIDO);
		pedido.setPedinIdcondicionpago(null);
		pedido.setPedinIdcarpeta(null);
		pedido.setPedinIdpadre(null);
		pedido.setPedinGuardado(0);
		pedido.setPedinIdincotermcomercial(idIncoterm);
		pedido.setPedinIdestadodocumento(estadoDocumento);
		pedido.setPedidFechasolicitud(new Date());
		pedido.setPedivUsuariocreacion(usuario);
		pedido.setPedidFechacreacion(new Date());
		pedido.setPedinEstado(Constantes.ACTIVO);
		this.comexstPedidoMapper.insert(pedido);
		return id;
	}

	@Override
	public int registrarPosPedidoConfirmado(List<PosicionPedidoTO> posiciones, String usuario, int idPedido, int idMoneda) {
		int insertado = 0;
		for (PosicionPedidoTO pedido : posiciones) {
			ComexstPedidoposicion posicion = new ComexstPedidoposicion();
			int intL_secuencia = this.obtenerSecuencia(TablasAplicativoEnum.COMEXST_PEDIDOPOSICION.getValue());
			posicion.setPposnId(intL_secuencia);
			posicion.setPposnIdpedido(idPedido);
			posicion.setPposnItem(pedido.getItem());
			posicion.setPposnIdproducto(pedido.getIdProducto() == 0 ? null : pedido.getIdProducto());
			posicion.setPposnCantidad(pedido.getCantidad());
			posicion.setPposnCantidadventa(pedido.getCantidadVenta());
			posicion.setPposnIdmoneda(idMoneda);
			posicion.setPposnFactor(pedido.getFactor());
			posicion.setPposnIdunidadmedida(pedido.getIdUnidadMedida() == 0 ? null : pedido.getIdUnidadMedida());
			posicion.setPposnIdunidadmedidaventa(
					pedido.getIdUnidadMedidaVenta() == 0 ? null : pedido.getIdUnidadMedidaVenta());
			posicion.setPposnIdalmacen(pedido.getIdAlmacen() == 0 ? null : pedido.getIdAlmacen());
			posicion.setPposnPesotonelada(pedido.getPesoTonelada());
			posicion.setPposnPesonominal(pedido.getPesoNominal());
			posicion.setPposnCantidadtonelada(pedido.getCantidadTonelada());
			posicion.setPposdFechadisponibilidad(pedido.getFechaDisponibilidad());
			posicion.setPposnPreciounitariosap(pedido.getPrecioUnitarioSAP());
			posicion.setPposnPreciounitario(pedido.getPrecioUnitario());
			posicion.setPposnImporte(pedido.getImporte());
			posicion.setPposnEstado(Constantes.ACTIVO);
			posicion.setPposvUsuariocreacion(usuario);
			posicion.setPposdFechacreacion(new Date());
			posicion.setPposvUsuariomodificacion(usuario);
			posicion.setPposdFechamodificacion(new Date());
			insertado = comexstPedidoposicionMapper.insert(posicion);
		}
		return insertado;
	}

	@Override
	public PedidoTO obtenerPedidoxIdPedido(int codigoPedido, int estadoDocumento) {
		return this.cotizacionExtendedMapper.obtenerPedidoxIdPedido(codigoPedido, estadoDocumento);
	}

	@Override
	public void cambiarEstadoDocumento(int idPedido, int estadoDocumento, String usuario) {
		this.cotizacionExtendedMapper.cambiarEstadoDocumento(idPedido, estadoDocumento, usuario);
	}
}
