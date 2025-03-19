package com.caasa.comexsap.exportaciones.model.response;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.ExportacionTerrestrePosicionTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionTerrestreTO;

public class ExportacionTerrestreResponse {
	ExportacionTerrestreTO exportacion;
	List<ExportacionTerrestrePosicionTO> posiciones;
	String cod_rpta;
	String rpta;
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
	public String getCod_rpta() {
		return cod_rpta;
	}
	public void setCod_rpta(String cod_rpta) {
		this.cod_rpta = cod_rpta;
	}
	public String getRpta() {
		return rpta;
	}
	public void setRpta(String rpta) {
		this.rpta = rpta;
	}
}
