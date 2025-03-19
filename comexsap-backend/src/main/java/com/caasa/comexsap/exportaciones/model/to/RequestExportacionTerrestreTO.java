package com.caasa.comexsap.exportaciones.model.to;

import java.util.List;

public class RequestExportacionTerrestreTO {
	private ExportacionTerrestreTO exportacion;
	private List<ExportacionTerrestrePosicionTO> posiciones;
	public ExportacionTerrestreTO getExportacion() {
		return exportacion;
	}
	public void setExportacion(ExportacionTerrestreTO exportacion) {
		this.exportacion = exportacion;
	}
	public List<ExportacionTerrestrePosicionTO> getPosiciones() {
		return posiciones;
	}
	public void setPosiciones(List<ExportacionTerrestrePosicionTO> posiciones) {
		this.posiciones = posiciones;
	}
	
}
