package com.caasa.comexsap.exportaciones.model.to;

import java.io.ByteArrayInputStream;

public class ReportePdfTO {
	private String filename;
	private ByteArrayInputStream stream;
	private int length;
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public ByteArrayInputStream getStream() {
		return stream;
	}
	public void setStream(ByteArrayInputStream stream) {
		this.stream = stream;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
}
