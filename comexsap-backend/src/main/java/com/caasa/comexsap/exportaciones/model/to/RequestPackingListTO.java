package com.caasa.comexsap.exportaciones.model.to;

import java.util.List;

public class RequestPackingListTO {
	
	private int idExportacion;
	private List<ContenedorTO> contenedores;
	private List<PackingListTO> packings;
	public int getIdExportacion() {
		return idExportacion;
	}
	public void setIdExportacion(int idExportacion) {
		this.idExportacion = idExportacion;
	}
	public List<ContenedorTO> getContenedores() {
		return contenedores;
	}
	public void setContenedores(List<ContenedorTO> contenedores) {
		this.contenedores = contenedores;
	}
	public List<PackingListTO> getPackings() {
		return packings;
	}
	public void setPackings(List<PackingListTO> packings) {
		this.packings = packings;
	}
	
	
}
