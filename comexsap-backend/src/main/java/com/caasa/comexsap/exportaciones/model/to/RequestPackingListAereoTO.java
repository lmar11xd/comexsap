package com.caasa.comexsap.exportaciones.model.to;

import java.util.List;

public class RequestPackingListAereoTO {
	private int idExportacion;
	private List<PackingListAereoTO> packing;
	
	public int getIdExportacion() {
		return idExportacion;
	}
	public void setIdExportacion(int idExportacion) {
		this.idExportacion = idExportacion;
	}
	public List<PackingListAereoTO> getPacking() {
		return packing;
	}
	public void setPacking(List<PackingListAereoTO> packing) {
		this.packing = packing;
	}
}
