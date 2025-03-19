package com.caasa.comexsap.exportaciones.model.persistence.extended;

import org.apache.ibatis.annotations.Param;

import com.caasa.comexsap.exportaciones.model.to.DocumentoAgenteAduanaTO;

public interface DocumentoAgenteAduanaExtendedMapper {

	public DocumentoAgenteAduanaTO obtenerDocumentoAgenteAduanaxFactura(@Param("factura") String factura);
}
