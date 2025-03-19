package com.caasa.comexsap.exportaciones.model.persistence.extended;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.caasa.comexsap.exportaciones.model.to.ContenedorTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionMaritimoTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroPackingListRequestTO;
import com.caasa.comexsap.exportaciones.model.to.PackingListAereoTO;
import com.caasa.comexsap.exportaciones.model.to.PackingListCargaSueltaTO;
import com.caasa.comexsap.exportaciones.model.to.PackingListTO;
import com.caasa.comexsap.exportaciones.model.to.ReporteContenedorMaterialTO;
import com.caasa.comexsap.exportaciones.model.to.ReporteContenedorTO;
import com.caasa.comexsap.exportaciones.model.to.ReportePackingListTO;

public interface PackingListExtendedMapper {

	public List<ExportacionMaritimoTO> listarPackingListxFiltro(FiltroPackingListRequestTO request);
	public ExportacionMaritimoTO obtenerExportacionxId(@Param("id") int id);
    public List<ContenedorTO> listarContenedorxId(@Param("idExportacion") int id);
    public List<PackingListTO> listarPackingListxId(@Param("idExportacion") int id);
	public ContenedorTO obtenerContenedorxNumero(@Param("idExportacion") int idExportacion,@Param("numero") String numero,@Param("estado") int estado); 
	public List<ReporteContenedorTO> reporteContenedor(@Param("idExportacion") int idExportacion);
	public List<ReporteContenedorMaterialTO> reporteContenedorMaterial(@Param("idContenedor") int idContenedor);
	public List<ReportePackingListTO> reportePackingList(@Param("idContenedor") int idContenedor, @Param("codigoMaterial") String codigoMaterial);
	public List<PackingListCargaSueltaTO> obtenerPackingListCargaSuelta(@Param("idExportacion") int idExportacion);
	public List<PackingListAereoTO> obtenerPackingListAereo(@Param("idExportacion") int idExportacion);
}
