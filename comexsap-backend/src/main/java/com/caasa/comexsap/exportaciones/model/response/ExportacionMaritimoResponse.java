package com.caasa.comexsap.exportaciones.model.response;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.ExportacionFacturaTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionMaritimoPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionMaritimoTO;

public class ExportacionMaritimoResponse extends Response {
	
	ExportacionMaritimoTO exportacion;
	List<ExportacionMaritimoPosicionTO> posiciones;
	List<ExportacionFacturaTO> facturaImpresion;
	
	public ExportacionMaritimoTO getExportacion() {
		return exportacion;
	}
	public void setExportacion(ExportacionMaritimoTO exportacion) {
		this.exportacion = exportacion;
	}
	public List<ExportacionMaritimoPosicionTO> getPosiciones() {
		return posiciones;
	}
	public void setPosiciones(List<ExportacionMaritimoPosicionTO> posiciones) {
		this.posiciones = posiciones;
	}
	public List<ExportacionFacturaTO> getFacturaImpresion() {
		return facturaImpresion;
	}
	public void setFacturaImpresion(List<ExportacionFacturaTO> facturaImpresion) {
		this.facturaImpresion = facturaImpresion;
	}
}
