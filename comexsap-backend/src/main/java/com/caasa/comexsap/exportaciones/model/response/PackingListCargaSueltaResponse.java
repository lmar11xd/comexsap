package com.caasa.comexsap.exportaciones.model.response;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.ExportacionIntercompanyPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionMaritimoPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionMaritimoTO;
import com.caasa.comexsap.exportaciones.model.to.PackingListCargaSueltaTO;

public class PackingListCargaSueltaResponse {

	private ExportacionMaritimoTO exportacion;
	private List<ExportacionMaritimoPosicionTO> posiciones;
	private List<ExportacionIntercompanyPosicionTO> posicionesIntercompany;
	private List<PackingListCargaSueltaTO> packingList;
	
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
	public List<PackingListCargaSueltaTO> getPackingList() {
		return packingList;
	}
	public void setPackingList(List<PackingListCargaSueltaTO> packingList) {
		this.packingList = packingList;
	}
}
