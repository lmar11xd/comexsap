package com.caasa.comexsap.exportaciones.model.to;

import java.util.List;

public class PackingListAereoResponse {
	
	private ExportacionMaritimoTO exportacion;
	private List<ExportacionMaritimoPosicionTO> posiciones;
	private List<ExportacionIntercompanyPosicionTO> posicionesIntercompany;
	private List<PackingListAereoTO> packingList;
	
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
	public List<PackingListAereoTO> getPackingList() {
		return packingList;
	}
	public void setPackingList(List<PackingListAereoTO> packingList) {
		this.packingList = packingList;
	}

}
