package com.caasa.comexsap.exportaciones.model.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caasa.comexsap.exportaciones.model.dao.ClienteDao;
import com.caasa.comexsap.exportaciones.model.dao.ExportacionMaritimoDao;
import com.caasa.comexsap.exportaciones.model.response.ConsultarStockResponse;
import com.caasa.comexsap.exportaciones.model.response.PedidoExportacionResponse;
import com.caasa.comexsap.exportaciones.model.service.ExportacionMaritimoService;
import com.caasa.comexsap.exportaciones.model.to.ClienteDatosVentaTO;
import com.caasa.comexsap.exportaciones.model.to.ComponenteTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionAgrupadoTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionEtiquetaTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionFacturaTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionMaritimoPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionMaritimoTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionOperacionTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionPaisTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionPedidoPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroDTConsultarStockReqTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroDTCreaPedidosReqTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroExportacionMaritimoRequestTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroExportacionPedidoPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.RequestExportacionMaritimoTO;
import com.caasa.comexsap.exportaciones.soap.client.PedidoClient;
import com.caasa.comexsap.exportaciones.soap.to.Salida;
import com.caasa.comexsap.exportaciones.spring.SapSoapProperties;

@Service
public class ExportacionMaritimoServiceImpl implements ExportacionMaritimoService {

	@Autowired
	private ExportacionMaritimoDao exportacionMaritimoDao;

	@Autowired
	private ClienteDao clienteDao;

	@Autowired
	private SapSoapProperties sapSoapProperties;

	@Override
	public List<ExportacionMaritimoTO> listarExportacionMaritimoxFiltro(FiltroExportacionMaritimoRequestTO request) {
		return exportacionMaritimoDao.listarExportacionMaritimoxFiltro(request);
	}

	@Override
	public List<ExportacionMaritimoPosicionTO> listarPosicionxIdExportacion(int idExportacion) {
		return exportacionMaritimoDao.listarPosicionxIdExportacion(idExportacion);
	}

	@Override
	public ExportacionMaritimoTO obtenerExportacionMaritimoxId(int idDocumento) {
		return exportacionMaritimoDao.obtenerExportacionMaritimoxId(idDocumento);
	}

	@Override
	public int buscarExportacionMaritimoxId(int id, int estadoDocumento) {
		return exportacionMaritimoDao.buscarExportacionMaritimoxId(id, estadoDocumento);
	}

	@Override
	public int registrarExportacionMaritimo(RequestExportacionMaritimoTO request, String usuario) {
		return exportacionMaritimoDao.registrarExportacionMaritimo(request, usuario);
	}

	@Override
	public List<ExportacionPedidoPosicionTO> listarPedidoPosicion(FiltroExportacionPedidoPosicionTO request) {
		return exportacionMaritimoDao.listarPedidoPosicion(request);
	}

	@Override
	public void actualizarExportacionMaritimo(RequestExportacionMaritimoTO request, String usuario) {
		this.exportacionMaritimoDao.actualizarExportacionMaritimo(request, usuario);
	}

	@Override
	public void confirmarExportacionMaritimo(int id, String usuario, int estadoDocumento) {
		this.exportacionMaritimoDao.confirmarExportacionMaritimo(id, usuario, estadoDocumento);
	}

	@Override
	public List<ExportacionMaritimoPosicionTO> obtenerExportacionPedido(int id) {
		return this.exportacionMaritimoDao.obtenerExportacionPedido(id);
	}

	@Override
	public void eliminarExportacionMaritimo(int id, String usuario) {
		this.exportacionMaritimoDao.eliminarExportacionMaritimo(id, usuario);
	}

	@Override
	public void eliminarExportacionMaritimoPosicion(int idExportacion, int idPosicion, String usuario) {
		this.exportacionMaritimoDao.eliminarExportacionMaritimoPosicion(idExportacion, idPosicion, usuario);
	}

	@Override
	public PedidoExportacionResponse crearPedidoSap(FiltroDTCreaPedidosReqTO filtro) throws Exception {
		List<PedidoExportacionResponse> pedidoResponse = new ArrayList<PedidoExportacionResponse>();
		ClienteDatosVentaTO clienteVenta = null;
		// Verificar si el DOCEXP ya ha sido creado en SAP
		String numeroPedidoSap = this.exportacionMaritimoDao.obtenerNumeroPedidoSAP(filtro.getCabecera().get(0));
		if (numeroPedidoSap == null || numeroPedidoSap.equals("")) {
			if (filtro.getCodigoCliente() != null) {
				clienteVenta = this.clienteDao.obtenerClienteDatosVentaxCodigo(filtro.getCodigoCliente());
			}
			pedidoResponse = PedidoClient.crearPedidoSap(sapSoapProperties, filtro, clienteVenta);
		} else {
			Salida salida = new Salida();
			salida.setTipoMsj("PE");
			salida.setDescMsj("Este pedido ya ha sido creado en SAP. <<" + numeroPedidoSap + ">>");
			PedidoExportacionResponse resp = new PedidoExportacionResponse();
			resp.setSalida(List.of(salida));
			pedidoResponse.add(resp);
		}

		if (pedidoResponse != null && pedidoResponse.size() > 0) {
			return pedidoResponse.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<ExportacionFacturaTO> listarExportacionFacturaxIdExportacion(int idExportacion) {
		return this.exportacionMaritimoDao.listarExportacionFacturaxIdExportacion(idExportacion);
	}

	@Override
	public ConsultarStockResponse consultarStockPedido(FiltroDTConsultarStockReqTO filtro) throws Exception {
		ConsultarStockResponse stockResponse = PedidoClient.consultarStock(sapSoapProperties, filtro);
		return stockResponse;
	}

	@Override
	public List<ComponenteTO> listarComponentexIdPosicion(int idPosicion) {
		return this.exportacionMaritimoDao.listarComponentexIdPosicion(idPosicion);
	}

	@Override
	public List<ExportacionOperacionTO> obtenerTotalPedidosExportacion() {
		return this.exportacionMaritimoDao.obtenerTotalPedidosExportacion();
	}

	@Override
	public List<ExportacionAgrupadoTO> obtenerTotalExportacion() {
		return this.exportacionMaritimoDao.obtenerTotalExportacion();
	}

	@Override
	public List<ExportacionPaisTO> obtenerTotalExportacionPais() {
		return this.exportacionMaritimoDao.obtenerTotalExportacionPais();
	}

	@Override
	public void guardarFacturaSap(ExportacionMaritimoTO documento, List<ExportacionMaritimoPosicionTO> posiciones,
			String pedidoSap) {
		this.exportacionMaritimoDao.guardarFacturaSap(documento, posiciones, pedidoSap);
	}

	@Override
	public void actualizarEntrega(int idExportacion, String pedidoSap) {
		this.exportacionMaritimoDao.actualizarEntrega(idExportacion, pedidoSap);
	}

	@Override
	public PedidoExportacionResponse modificarPedidoSap(FiltroDTCreaPedidosReqTO filtro) throws Exception {
		List<PedidoExportacionResponse> pedidoResponse = new ArrayList<PedidoExportacionResponse>();
		ClienteDatosVentaTO clienteVenta = null;
		if (filtro.getCodigoCliente() != null) {
			clienteVenta = this.clienteDao.obtenerClienteDatosVentaxCodigo(filtro.getCodigoCliente());
		}
		pedidoResponse = PedidoClient.modificarPedidoSap(sapSoapProperties, filtro, clienteVenta);
		if (pedidoResponse != null && pedidoResponse.size() > 0) {
			return pedidoResponse.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<ExportacionEtiquetaTO> listarExportacionEtiquetas(int idExportacion) {
		return this.exportacionMaritimoDao.listarExportacionEtiquetas(idExportacion);
	}

}