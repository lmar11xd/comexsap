package com.caasa.comexsap.exportaciones.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caasa.comexsap.exportaciones.model.response.ExportacionIntercompanyResponse;
import com.caasa.comexsap.exportaciones.model.service.ExportacionMaritimoService;
import com.caasa.comexsap.exportaciones.model.service.PedidoIntercompanyService;
import com.caasa.comexsap.exportaciones.model.to.ExportacionFacturaTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionIntercompanyPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionMaritimoTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionPedidoPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroExportacionPedidoPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroPedidoIntercompany;
import com.caasa.comexsap.exportaciones.model.to.PedidoIntercompanyTO;
import com.caasa.comexsap.exportaciones.model.to.RequestTO;
import com.caasa.comexsap.exportaciones.util.Constantes;

@RestController
@RequestMapping("/api/pedidoIntercompanyController")
public class PedidoIntercompanyController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PedidoIntercompanyService pedidoIntercompanyService;
	
	@Autowired
	private ExportacionMaritimoService exportacionMaritimoService;
	
	@PostMapping("/listarPedidoIntercompanySapxFiltro")
	public ResponseEntity<List<PedidoIntercompanyTO>> listarPedidoIntercompanySapxFiltro(
			@RequestBody RequestTO<FiltroPedidoIntercompany> request) {
		List<PedidoIntercompanyTO> lista = null;
		try {
			lista = pedidoIntercompanyService.listarPedidoIntercompanySapxFiltro(request.getFormulario());
		} catch (Exception e) {
			logger.error("Error en el servicio listarPedidoIntercompanySapxFiltro -->", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/obtenerExportacionIntercompany/{idExportacion}")
	public ResponseEntity<ExportacionIntercompanyResponse> obtenerExportacionMaritimo(@PathVariable("idExportacion") int id) {
		ExportacionMaritimoTO exportacionMaritimoTO = null;
		List<ExportacionIntercompanyPosicionTO> listaPosicionExportacion = null;
		List<ExportacionFacturaTO> listaExportacionFactura = null;
		ExportacionIntercompanyResponse response = new ExportacionIntercompanyResponse();
		try {
			exportacionMaritimoTO = exportacionMaritimoService.obtenerExportacionMaritimoxId(id);
			listaPosicionExportacion = pedidoIntercompanyService.listarPosicionxIdExportacion(id);
			if(listaPosicionExportacion != null && listaPosicionExportacion.size() > 0) {
				String pedidoSap = listaPosicionExportacion.get(0).getPedidoSap();
				if(pedidoSap != null && pedidoSap != "") {
					pedidoIntercompanyService.guardarFacturaSap(exportacionMaritimoTO, listaPosicionExportacion, pedidoSap);
				}
			}
			listaExportacionFactura = exportacionMaritimoService.listarExportacionFacturaxIdExportacion(id);
			response.setExportacion(exportacionMaritimoTO);
			response.setPosiciones(listaPosicionExportacion);
			response.setFacturaImpresion(listaExportacionFactura);
			response.setCod_rpta(Constantes.UNO);
			response.setRpta("Posiciones: " + listaPosicionExportacion.size());
		} catch (Exception e) {
			response.setCod_rpta(Constantes.CERO);
			response.setRpta(e.getMessage());
			logger.error("Error en el servicio obtenerExportacionIntercompany -->", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/listarPedidosIntercompanySapDisponibles")
	public ResponseEntity<List<ExportacionPedidoPosicionTO>> listarPedidosIntercompanySap(
			@RequestBody RequestTO<FiltroExportacionPedidoPosicionTO> request) {
		List<ExportacionPedidoPosicionTO> listaExportacionPedidoPosicion = null;
		try {
			listaExportacionPedidoPosicion = pedidoIntercompanyService.listarPedidosIntercompanySapDisponibles(request.getFormulario());
		} catch (Exception e) {
			logger.error("Error en el servicio listarPedidosIntercompanySapDisponibles -->", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(listaExportacionPedidoPosicion);
	}
}
