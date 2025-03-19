package com.caasa.comexsap.exportaciones.model.to;

public class ArchivoModelTO {
	private String name;
	private String url;
	
	public ArchivoModelTO(String name, String url) {
		super();
		this.name = name;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
}
