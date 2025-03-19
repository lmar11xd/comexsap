package com.caasa.comexsap.exportaciones.model.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import com.caasa.comexsap.exportaciones.model.to.CotizacionTO;
import com.caasa.comexsap.exportaciones.model.to.PosicionPedidoTO;
import com.caasa.comexsap.exportaciones.model.to.RequestReporteFacturaComercialTO;
import com.caasa.comexsap.exportaciones.model.to.RequestReporteFacturaTO;

public interface PdfService {
	public ResponseEntity<Resource> generarPackingListPdf(int idExportacion, boolean versionIngles, boolean original) throws Exception;
	public ResponseEntity<Resource> generarCotizacionPdf(CotizacionTO cotizacion,List<PosicionPedidoTO> posiciones,String usuario) throws Exception;
	public ResponseEntity<Resource> generarFacturaComercialPdf(RequestReporteFacturaComercialTO request, boolean versionIngles, boolean original, int tipoFactura) throws Exception;
	public ResponseEntity<Resource> generarExportacionMaritimaPdf(RequestReporteFacturaTO request) throws Exception;
}
