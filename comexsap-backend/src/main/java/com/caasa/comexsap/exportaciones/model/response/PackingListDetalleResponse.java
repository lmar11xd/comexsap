package com.caasa.comexsap.exportaciones.model.response;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.ContenedorTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionIntercompanyPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionMaritimoPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionMaritimoTO;
import com.caasa.comexsap.exportaciones.model.to.PackingListTO;

public class PackingListDetalleResponse {

	private ExportacionMaritimoTO exportacion;
	private List<ExportacionMaritimoPosicionTO> posiciones;
	private List<ExportacionIntercompanyPosicionTO> posicionesIntercompany;
	private List<ContenedorTO> contenedores;
	private List<PackingListTO> packingList;
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
	public List<ExportacionIntercompanyPosicionTO> getPosicionesIntercompany() {
		return posicionesIntercompany;
	}
	public void setPosicionesIntercompany(List<ExportacionIntercompanyPosicionTO> posicionesIntercompany) {
		this.posicionesIntercompany = posicionesIntercompany;
	}
	public List<ContenedorTO> getContenedores() {
		return contenedores;
	}
	public void setContenedores(List<ContenedorTO> contenedores) {
		this.contenedores = contenedores;
	}
	public List<PackingListTO> getPackingList() {
		return packingList;
	}
	public void setPackingList(List<PackingListTO> packingList) {
		this.packingList = packingList;
	}
}
