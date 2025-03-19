package com.caasa.comexsap.exportaciones.soap.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.caasa.comexsap.exportaciones.model.response.ConsultarStockResponse;
import com.caasa.comexsap.exportaciones.model.response.PedidoExportacionResponse;
import com.caasa.comexsap.exportaciones.model.to.ClienteDatosVentaTO;
import com.caasa.comexsap.exportaciones.model.to.DetalleStockTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroDTConsultarStockReqTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroDTCreaPedidosReqTO;
import com.caasa.comexsap.exportaciones.soap.to.Cabecera;
import com.caasa.comexsap.exportaciones.soap.to.Condiciones;
import com.caasa.comexsap.exportaciones.soap.to.Datos;
import com.caasa.comexsap.exportaciones.soap.to.Detalle;
import com.caasa.comexsap.exportaciones.soap.to.Interlocutor;
import com.caasa.comexsap.exportaciones.soap.to.Repartos;
import com.caasa.comexsap.exportaciones.soap.to.RespuestaStock;
import com.caasa.comexsap.exportaciones.soap.to.Salida;
import com.caasa.comexsap.exportaciones.soap.to.Textos;
import com.caasa.comexsap.exportaciones.spring.SapSoapProperties;
import com.caasa.comexsap.exportaciones.util.Constantes;
import com.caasa.soap.exportaciones.DTCreaPedidosReq;
import com.caasa.soap.exportaciones.DTCreaPedidosRes;
import com.caasa.soap.exportaciones.DTModiPedidosReq;
import com.caasa.soap.exportaciones.DTModiPedidosRes;
import com.caasa.soap.exportaciones.OSCreaPedidos;
import com.caasa.soap.exportaciones.OSModiPedidos;
import com.caasa.soap.exportaciones.stock.DTConsultaStockReq;
import com.caasa.soap.exportaciones.stock.DTConsultaStockRes;
import com.caasa.soap.exportaciones.stock.OSConsultaStock;

@Component
public class PedidoClient {
	private static Logger logger = LoggerFactory.getLogger(PedidoClient.class);

	public static List<PedidoExportacionResponse> crearPedidoSap(SapSoapProperties sapSoapProperties,
			FiltroDTCreaPedidosReqTO objA_filtro, ClienteDatosVentaTO clienteVenta) throws Exception {
		List<PedidoExportacionResponse> objL_respuesta = new ArrayList<PedidoExportacionResponse>();
		try {
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
			factory.setServiceClass(OSCreaPedidos.class);
			factory.setAddress(sapSoapProperties.getEndpoint_crear_pedido());
			factory.setUsername(sapSoapProperties.getUsername());
			factory.setPassword(sapSoapProperties.getPassword());
			OSCreaPedidos client = (OSCreaPedidos) factory.create();
			DTCreaPedidosReq request = new DTCreaPedidosReq();
			request.setTestrun(objA_filtro.getTestrun());
			PedidoExportacionResponse pedidoResponse = new PedidoExportacionResponse();
			getCabecera(request, objA_filtro, clienteVenta);
			getDetalle(request, objA_filtro);
			getCondiciones(request, objA_filtro);
			getInterlocutor(request, objA_filtro);
			getTextos(request, objA_filtro);
			getRepartos(request, objA_filtro);
			DTCreaPedidosRes dtConsultaPedidosRes = client.osCreaPedidos(request);

			List<DTCreaPedidosRes.Salida> lstPedido = dtConsultaPedidosRes.getSalida();

			List<Salida> listaSalida = new ArrayList<Salida>();

			for (DTCreaPedidosRes.Salida salida : lstPedido) {
				Salida beanDatos = new Salida();
				beanDatos.setNumPedido(salida.getNumPedido());
				beanDatos.setTipoMsj(salida.getTipoMsj());
				beanDatos.setId(salida.getId());
				beanDatos.setDescMsj(salida.getDescMsj());
				beanDatos.setNum(salida.getNum());
				listaSalida.add(beanDatos);
			}
			pedidoResponse.setSalida(listaSalida);
			objL_respuesta.add(pedidoResponse);
			return objL_respuesta;
		} catch (Exception ex) {
			logger.error("Error crearPedidoSap", ex);
			return null;
		}
	}

	public static List<PedidoExportacionResponse> modificarPedidoSap(
		SapSoapProperties sapSoapProperties,
		FiltroDTCreaPedidosReqTO objA_filtro,
		ClienteDatosVentaTO clienteVenta
	) throws Exception {
		List<PedidoExportacionResponse> objL_respuesta = new ArrayList<PedidoExportacionResponse>();
		try {
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
			factory.setServiceClass(OSModiPedidos.class);
			factory.setAddress(sapSoapProperties.getEndpoint_modificar_pedido());
			factory.setUsername(sapSoapProperties.getUsername());
			factory.setPassword(sapSoapProperties.getPassword());
			OSModiPedidos client = (OSModiPedidos) factory.create();
			DTModiPedidosReq request = new DTModiPedidosReq();
			request.setTestrun(objA_filtro.getTestrun());
			PedidoExportacionResponse pedidoResponse = new PedidoExportacionResponse();
			getCabecera(request, objA_filtro, clienteVenta);
			getCabeceraX(request, objA_filtro);
			getInterlocutor(request, objA_filtro);
			getCondiciones(request, objA_filtro);
			DTModiPedidosRes dtConsultaPedidosRes = client.osModiPedidos(request);

			List<DTModiPedidosRes.Salida> lstPedido = dtConsultaPedidosRes.getSalida();

			List<Salida> listaSalida = new ArrayList<Salida>();

			for (DTModiPedidosRes.Salida salida : lstPedido) {
				Salida beanDatos = new Salida();
				beanDatos.setNumPedido(salida.getNumPedido());
				beanDatos.setTipoMsj(salida.getTipoMsj());
				beanDatos.setId(salida.getId());
				beanDatos.setDescMsj(salida.getDescMsj());
				beanDatos.setNum(salida.getNum());
				listaSalida.add(beanDatos);
			}
			pedidoResponse.setSalida(listaSalida);
			objL_respuesta.add(pedidoResponse);
			return objL_respuesta;
		} catch (Exception ex) {
			logger.error("Error en modificarPedidoSap", ex);
			return null;
		}
	}
	
	public static ConsultarStockResponse consultarStock(SapSoapProperties sapSoapProperties,
			FiltroDTConsultarStockReqTO objA_filtro) throws Exception {
		ConsultarStockResponse consultarStockResponse = new ConsultarStockResponse();

		try {
			JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
			factory.setServiceClass(OSConsultaStock.class);
			factory.setAddress(sapSoapProperties.getEndpoint_consultar_stock());
			factory.setUsername(sapSoapProperties.getUsername());
			factory.setPassword(sapSoapProperties.getPassword());
			OSConsultaStock client = (OSConsultaStock) factory.create();
			DTConsultaStockReq request = new DTConsultaStockReq();
			// request.setTestrun("X");
			request.setEsSecundario(Constantes.VACIO);
			request.setGpoVendedor(objA_filtro.getGrupoVendedor());
			request.setOficina(objA_filtro.getOficina());
			request.setCentro(objA_filtro.getCentro());
			request.setAlmacen(objA_filtro.getAlmacen());
			request.setOrgVentas(objA_filtro.getOrgVentas());
			request.setCanalDistribucion(objA_filtro.getCanalDistribucion());
			// List<DTConsultaStockReq.Detalle> listaDetalleReq = new
			// ArrayList<DTConsultaStockReq.Detalle>();
			for (DetalleStockTO salida : objA_filtro.getDetalles()) {
				DTConsultaStockReq.Detalle beanDatos = new DTConsultaStockReq.Detalle();
				beanDatos.setUnidad(salida.getUnidad());
				beanDatos.setMaterial(salida.getMaterial());
				beanDatos.setPosicion(salida.getPosicion());
				beanDatos.setCantidadSolicitada(salida.getCantidadSolicitada());
				beanDatos.setCentro(salida.getCentro());
				beanDatos.setAlmacen(salida.getAlmacen());
				beanDatos.setMultisociedad(Constantes.VACIO);
				request.getDetalle().add(beanDatos);
			}
			// request.getDetalle().addAll(listaDetalleReq);
			DTConsultaStockRes dtConsultaStockRes = client.osConsultaStock(request);

			List<DTConsultaStockRes.Datos> listaDatos = client.osConsultaStock(request).getDatos();
			List<DTConsultaStockRes.Return> listaMensajes = dtConsultaStockRes.getReturn();

			List<Datos> listaDatoResp = new ArrayList<Datos>();
			List<RespuestaStock> listaMensajeResp = new ArrayList<RespuestaStock>();
//
			for (DTConsultaStockRes.Datos salida : listaDatos) {
				Datos beanDatos = new Datos();
				beanDatos.setCentro(salida.getCentro());
				beanDatos.setAlmacen(salida.getAlmacen());
				beanDatos.setStock(salida.getStock() != null ? salida.getStock().trim() : salida.getStock());
				beanDatos.setMaterial(salida.getMaterial());
				beanDatos.setUnidad(salida.getUnidad());
				beanDatos.setFechaDisponible(salida.getFechaDisponible());
				beanDatos.setPosicion(salida.getPosicion());
				beanDatos.setCantidadPedido(salida.getCantidadPedido() != null ? salida.getCantidadPedido().trim()
						: salida.getCantidadPedido());
				beanDatos.setTipoPosicion(salida.getTipoPosicion());
				beanDatos.setMultisociedad(salida.getMultisociedad());
				beanDatos.setCantidadOriginal(salida.getCantidadOriginal() != null ? salida.getCantidadOriginal().trim()
						: salida.getCantidadOriginal());
				listaDatoResp.add(beanDatos);
			}
			for (DTConsultaStockRes.Return salida : listaMensajes) {
				RespuestaStock beanDatos = new RespuestaStock();
				beanDatos.setTipo(salida.getType());
				beanDatos.setCodigo(salida.getCode());
				beanDatos.setLogNo(salida.getLogNo());
				beanDatos.setLogMsgNo(salida.getLogMsgNo());
				beanDatos.setMensaje(salida.getMessage());
				beanDatos.setMensaje1(salida.getMessageV1());
				beanDatos.setMensaje2(salida.getMessageV2());
				beanDatos.setMensaje3(salida.getMessageV3());
				beanDatos.setMensaje4(salida.getMessageV4());

				listaMensajeResp.add(beanDatos);
			}
			consultarStockResponse.setDatos(listaDatoResp);
			consultarStockResponse.setMensajes(listaMensajeResp);

			return consultarStockResponse;
		} catch (Exception ex) {
			logger.error("Error consultarStock", ex);
			return null;
		}

	}

	public static DTCreaPedidosReq getCabecera(DTCreaPedidosReq request, FiltroDTCreaPedidosReqTO objA_filtro, ClienteDatosVentaTO clienteVenta) {
		for (Cabecera cabecera : objA_filtro.getCabecera()) {
			DTCreaPedidosReq.Cabecera cabeceraBean = new DTCreaPedidosReq.Cabecera();
			cabeceraBean.setOrgVentas(cabecera.getOrgVentas());
			cabeceraBean.setCanalDist(cabecera.getCanalDist());
			cabeceraBean.setSector(cabecera.getSector());
			cabeceraBean.setTipoDoc(cabecera.getTipoDoc());
			cabeceraBean.setMoneda(cabecera.getMoneda());
			cabeceraBean.setFechaPrecio(cabecera.getFechaPrecio());
			cabeceraBean.setGrupoVend(cabecera.getGrupoVend());
			cabeceraBean.setOficina(clienteVenta != null ? clienteVenta.getOficinaVentas() : cabecera.getOficina());
			cabeceraBean.setListaPrecio(cabecera.getListaPrecio());
			cabeceraBean.setCondPago(cabecera.getCondPago());
			cabeceraBean.setCondExp(cabecera.getCondExp());
			cabeceraBean.setGrupo(Constantes.VACIO);
			cabeceraBean.setFechaPref(cabecera.getFechaPref());
			cabeceraBean.setFechaFact(Constantes.VACIO);
			cabeceraBean.setFechaDoc(cabecera.getFechaDoc());
			cabeceraBean.setOrdCompra(Constantes.VACIO);
			cabeceraBean.setMotPedido(cabecera.getMotPedido());
			cabeceraBean.setCotizacion(cabecera.getCotizacion());
			cabeceraBean.setGrupo1(Constantes.VACIO);
			cabeceraBean.setTipoPago(cabecera.getTipoPago());
			cabeceraBean.setZonaVentas(cabecera.getZonaVentas());
			cabeceraBean.setCentroSum(cabecera.getCentroSum());
			cabeceraBean.setIncoterm1(cabecera.getIncoterm1());
			cabeceraBean.setIncoterm2(cabecera.getIncoterm2());
			cabeceraBean.setFechaOccli(cabecera.getFechaOccli());
			cabeceraBean.setNumDescObr(Constantes.VACIO);
			cabeceraBean.setNumDespa(cabecera.getNumDespa());
			cabeceraBean.setNumCont(Constantes.VACIO);
			cabeceraBean.setBloqueEntr(Constantes.VACIO);
			cabeceraBean.setGrupoCli(Constantes.VACIO);
			cabeceraBean.setFacturaFinanciera(Constantes.VACIO);
			request.getCabecera().add(cabeceraBean);
		}
		return request;
	}

	public static DTCreaPedidosReq getDetalle(DTCreaPedidosReq request, FiltroDTCreaPedidosReqTO objA_filtro) {
		for (Detalle detalle : objA_filtro.getDetalle()) {
			DTCreaPedidosReq.Detalle detalleBean = new DTCreaPedidosReq.Detalle();
			detalleBean.setPosicion(detalle.getPosicion());
			detalleBean.setPosSup(Constantes.VACIO);
			detalleBean.setMaterial(detalle.getMaterial());
			detalleBean.setCantidad(detalle.getCantidad());
			detalleBean.setCentro(detalle.getCentro());
			detalleBean.setAlmacen(detalle.getAlmacen());
			detalleBean.setUnidad(detalle.getUnidad());
			detalleBean.setRuta(detalle.getRuta() == null ? Constantes.VACIO : detalle.getRuta());
			detalleBean.setTdesExs(detalle.getTdesExs());
			detalleBean.setTdesFsu(detalle.getTdesFsu());
			detalleBean.setTdesLim(Constantes.VACIO);
			detalleBean.setTipoNc(Constantes.VACIO);
			detalleBean.setNumLote(Constantes.VACIO);
			detalleBean.setNumDmo(Constantes.VACIO);
			detalleBean.setNumPmo(Constantes.VACIO);
			detalleBean.setMotRech(Constantes.VACIO);
			detalleBean.setTipoPosicion(detalle.getTipoPosicion());
			request.getDetalle().add(detalleBean);
		}
		return request;
	}

	public static DTCreaPedidosReq getCondiciones(DTCreaPedidosReq request, FiltroDTCreaPedidosReqTO objA_filtro) {
		for (Condiciones condicion : objA_filtro.getCondiciones()) {
			DTCreaPedidosReq.Condiciones condicionesBean = new DTCreaPedidosReq.Condiciones();
			condicionesBean.setCondPos(Constantes.VACIO);
			condicionesBean.setCondPr(condicion.getCondPr());
			condicionesBean.setCondBp(condicion.getCondBp());
			condicionesBean.setCondVal(condicion.getCondVal());
			condicionesBean.setKoein(condicion.getKoein());
			condicionesBean.setKpein(Constantes.VACIO);
			condicionesBean.setKmein(Constantes.VACIO);
			request.getCondiciones().add(condicionesBean);
		}
		return request;
	}

	public static DTCreaPedidosReq getInterlocutor(DTCreaPedidosReq request, FiltroDTCreaPedidosReqTO objA_filtro) {
		for (Interlocutor interlocutor : objA_filtro.getInterlocutor()) {
			DTCreaPedidosReq.Interlocutor interlocutorBean = new DTCreaPedidosReq.Interlocutor();
			interlocutorBean.setCodInte(interlocutor.getCodInte());
			interlocutorBean.setTipoInt(interlocutor.getTipoInt());
			request.getInterlocutor().add(interlocutorBean);
		}
		return request;
	}

	public static DTCreaPedidosReq getTextos(DTCreaPedidosReq request, FiltroDTCreaPedidosReqTO objA_filtro) {
		for (Textos texto : objA_filtro.getTextos()) {
			DTCreaPedidosReq.Textos textoBean = new DTCreaPedidosReq.Textos();
			textoBean.setIdTexto(texto.getIdTexto());
			textoBean.setTexto(texto.getTexto());
			request.getTextos().add(textoBean);
		}
		return request;
	}

	public static DTCreaPedidosReq getRepartos(DTCreaPedidosReq request, FiltroDTCreaPedidosReqTO objA_filtro) {
		for (Repartos reparto : objA_filtro.getRepartos()) {
			DTCreaPedidosReq.Repartos repartoBean = new DTCreaPedidosReq.Repartos();
			repartoBean.setPosnr(reparto.getPosnr());
			repartoBean.setFechaPrefe(reparto.getFechaPrefe());
			repartoBean.setCantidad(reparto.getCantidad());
			request.getRepartos().add(repartoBean);
		}
		return request;
	}

	public static DTModiPedidosReq getCabecera(DTModiPedidosReq request, FiltroDTCreaPedidosReqTO objA_filtro, ClienteDatosVentaTO clienteVenta) {
		for (Cabecera cabecera : objA_filtro.getCabecera()) {
			DTModiPedidosReq.Cabecera cabeceraBean = new DTModiPedidosReq.Cabecera();
			cabeceraBean.setNumPedido(cabecera.getNumeroPedido());
			cabeceraBean.setOrgVentas(cabecera.getOrgVentas());
			cabeceraBean.setCanalDist(cabecera.getCanalDist());
			cabeceraBean.setSector(cabecera.getSector());
			cabeceraBean.setTipoDoc(cabecera.getTipoDoc());
			cabeceraBean.setMoneda(cabecera.getMoneda());
			cabeceraBean.setFechaPrecio(cabecera.getFechaPrecio());
			cabeceraBean.setGrupoVend(cabecera.getGrupoVend());
			cabeceraBean.setOficina(clienteVenta != null ? clienteVenta.getOficinaVentas() : cabecera.getOficina());
			cabeceraBean.setCondPago(cabecera.getCondPago());
			cabeceraBean.setCondExp(cabecera.getCondExp());
			cabeceraBean.setFechaPref(cabecera.getFechaPref());
			cabeceraBean.setFechaDoc(cabecera.getFechaDoc());
			cabeceraBean.setZonaVentas(cabecera.getZonaVentas());			
			cabeceraBean.setIncoterm1(cabecera.getIncoterm1());
			cabeceraBean.setIncoterm2(cabecera.getIncoterm2());			
			request.getCabecera().add(cabeceraBean);
		}
		return request;
	}
	
	public static DTModiPedidosReq getCabeceraX(DTModiPedidosReq request, FiltroDTCreaPedidosReqTO objA_filtro) {
		DTModiPedidosReq.CabeceraX cabeceraXBean = new DTModiPedidosReq.CabeceraX();
		cabeceraXBean.setUpdateflag("U");
		cabeceraXBean.setCurrency("X");
		cabeceraXBean.setSalesGrp("X");
		cabeceraXBean.setSalesOff("X");
		cabeceraXBean.setShipCond("X");
		cabeceraXBean.setReqDateH("X");
		cabeceraXBean.setBillDate("X");
		cabeceraXBean.setPriceDate("X");
		cabeceraXBean.setOrdReason("X");
		cabeceraXBean.setAssNumber("X");
		cabeceraXBean.setCustGrp1("X");
		cabeceraXBean.setDlvBlock("X");
		cabeceraXBean.setPriceList("X");
		cabeceraXBean.setPurchNoC("X");
		cabeceraXBean.setDocDate("X");
		cabeceraXBean.setCustGroup("X");
		cabeceraXBean.setSalesDist("X");
		cabeceraXBean.setPymtMeth("X");
		cabeceraXBean.setIncoterms1("X");
		cabeceraXBean.setIncoterms2("X");
		cabeceraXBean.setPmnttrms("X");
		cabeceraXBean.setPurchDate("X");
		cabeceraXBean.setPurchNoS("X");
		cabeceraXBean.setRef1("X");
		cabeceraXBean.setCustGrp5("X");
		request.getCabeceraX().add(cabeceraXBean);
		return request;
	}

	public static DTModiPedidosReq getCondiciones(DTModiPedidosReq request, FiltroDTCreaPedidosReqTO objA_filtro) {
		for (Condiciones condicion : objA_filtro.getCondiciones()) {
			DTModiPedidosReq.Condiciones condicionesBean = new DTModiPedidosReq.Condiciones();
			condicionesBean.setUpdateflag("I");
			condicionesBean.setCondPr(condicion.getCondPr());
			condicionesBean.setCondBp(condicion.getCondBp());
			condicionesBean.setCondVal(condicion.getCondVal());
			condicionesBean.setKoein(condicion.getKoein());

			request.getCondiciones().add(condicionesBean);
		}
		return request;
	}

	public static DTModiPedidosReq getInterlocutor(DTModiPedidosReq request, FiltroDTCreaPedidosReqTO objA_filtro) {
		for (Interlocutor interlocutor : objA_filtro.getInterlocutor()) {
			DTModiPedidosReq.Interlocutor interlocutorBean = new DTModiPedidosReq.Interlocutor();
			interlocutorBean.setUpdateflag("U");
			interlocutorBean.setCodInte(interlocutor.getCodInte());
			interlocutorBean.setTipoInt(interlocutor.getTipoInt());
			request.getInterlocutor().add(interlocutorBean);
		}
		return request;
	}
}
