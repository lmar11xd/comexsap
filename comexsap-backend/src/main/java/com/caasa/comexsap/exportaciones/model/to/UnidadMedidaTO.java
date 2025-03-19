package com.caasa.comexsap.exportaciones.model.to;

public class UnidadMedidaTO  {
	
	private int idUnidadMedida;
    private String codigoMaterial;
    private String unidadMedida;
    private String unidadMedidaBase;
    private float umRen;
    private float umRez;
    private float pesoNominal;
    
	public String getCodigoMaterial() {
		return codigoMaterial;
	}
	public void setCodigoMaterial(String codigoMaterial) {
		this.codigoMaterial = codigoMaterial;
	}
	public String getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	public int getIdUnidadMedida() {
		return idUnidadMedida;
	}
	public void setIdUnidadMedida(int idUnidadMedida) {
		this.idUnidadMedida = idUnidadMedida;
	}
	public float getUmRen() {
		return umRen;
	}
	public void setUmRen(float umRen) {
		this.umRen = umRen;
	}
	public float getUmRez() {
		return umRez;
	}
	public void setUmRez(float umRez) {
		this.umRez = umRez;
	}
	public float getPesoNominal() {
		return pesoNominal;
	}
	public void setPesoNominal(float pesoNominal) {
		this.pesoNominal = pesoNominal;
	}
	public String getUnidadMedidaBase() {
		return unidadMedidaBase;
	}
	public void setUnidadMedidaBase(String unidadMedidaBase) {
		this.unidadMedidaBase = unidadMedidaBase;
	}
	
}
