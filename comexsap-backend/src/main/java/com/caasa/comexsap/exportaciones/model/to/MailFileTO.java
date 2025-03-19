package com.caasa.comexsap.exportaciones.model.to;

import java.io.File;

public class MailFileTO {
	private File file;
	private String name;
	
	public MailFileTO(File file, String name) {
		super();
		this.file = file;
		this.name = name;
	}
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
