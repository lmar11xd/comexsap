package com.caasa.comexsap.exportaciones.model.to;

import java.util.List;

public class RequestExportacionMaritimoTO {
	
	private ExportacionMaritimoTO exportacion;
	private List<ExportacionMaritimoPosicionTO> posiciones;
	private List<ExportacionFacturaTO> facturas;
	private List<ExportacionEtiquetaTO> etiquetas;
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
	public List<ExportacionFacturaTO> getFacturas() {
		return facturas;
	}
	public void setFacturas(List<ExportacionFacturaTO> facturas) {
		this.facturas = facturas;
	}
	public List<ExportacionEtiquetaTO> getEtiquetas() {
		return etiquetas;
	}
	public void setEtiquetas(List<ExportacionEtiquetaTO> etiquetas) {
		this.etiquetas = etiquetas;
	}
}
