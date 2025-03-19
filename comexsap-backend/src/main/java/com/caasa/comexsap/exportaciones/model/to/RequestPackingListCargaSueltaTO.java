package com.caasa.comexsap.exportaciones.model.to;

import java.util.List;

public class RequestPackingListCargaSueltaTO {
	private int idExportacion;
	private List<PackingListCargaSueltaTO> packing;
	
	public int getIdExportacion() {
		return idExportacion;
	}
	public void setIdExportacion(int idExportacion) {
		this.idExportacion = idExportacion;
	}
	public List<PackingListCargaSueltaTO> getPacking() {
		return packing;
	}
	public void setPacking(List<PackingListCargaSueltaTO> packing) {
		this.packing = packing;
	}
}
