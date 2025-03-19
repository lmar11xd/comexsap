package com.caasa.comexsap.exportaciones.model.to;

import java.util.List;

public class ExportacionAgrupadoTO {

	private String name;
	private List<SerieTO> series;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<SerieTO> getSeries() {
		return series;
	}
	public void setSeries(List<SerieTO> series) {
		this.series = series;
	}
	
	
	
}
