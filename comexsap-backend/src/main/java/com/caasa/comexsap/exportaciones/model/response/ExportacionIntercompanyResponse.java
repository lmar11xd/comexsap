package com.caasa.comexsap.exportaciones.model.response;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.ExportacionFacturaTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionMaritimoTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionIntercompanyPosicionTO;

public class ExportacionIntercompanyResponse extends Response {
	ExportacionMaritimoTO exportacion;
	List<ExportacionIntercompanyPosicionTO> posiciones;
	List<ExportacionFacturaTO> facturaImpresion;
	
	public ExportacionMaritimoTO getExportacion() {
		return exportacion;
	}
	public void setExportacion(ExportacionMaritimoTO exportacion) {
		this.exportacion = exportacion;
	}
	public List<ExportacionIntercompanyPosicionTO> getPosiciones() {
		return posiciones;
	}
	public void setPosiciones(List<ExportacionIntercompanyPosicionTO> posiciones) {
		this.posiciones = posiciones;
	}
	public List<ExportacionFacturaTO> getFacturaImpresion() {
		return facturaImpresion;
	}
	public void setFacturaImpresion(List<ExportacionFacturaTO> facturaImpresion) {
		this.facturaImpresion = facturaImpresion;
	}
}
