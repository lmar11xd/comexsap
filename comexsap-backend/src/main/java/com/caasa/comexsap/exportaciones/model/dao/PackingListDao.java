package com.caasa.comexsap.exportaciones.model.dao;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.ContenedorTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionMaritimoTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroPackingListRequestTO;
import com.caasa.comexsap.exportaciones.model.to.PackingListAereoTO;
import com.caasa.comexsap.exportaciones.model.to.PackingListCargaSueltaTO;
import com.caasa.comexsap.exportaciones.model.to.PackingListTO;
import com.caasa.comexsap.exportaciones.model.to.ReporteContenedorMaterialTO;
import com.caasa.comexsap.exportaciones.model.to.ReporteContenedorTO;
import com.caasa.comexsap.exportaciones.model.to.ReportePackingListTO;

public interface PackingListDao {
	
	public List<ExportacionMaritimoTO> listarPackingListxFiltro(FiltroPackingListRequestTO request);
	public ExportacionMaritimoTO obtenerExportacionxId(int id);
    public List<ContenedorTO> listarContenedorxId(int id);
    public List<PackingListTO> listarPackingListxId(int id);
    public int registrarPackingList(List<ContenedorTO> contenedores, 
			List<PackingListTO> packings,String usuario, int idExportacion);
	public void registrarContenedores(List<ContenedorTO> contenedores,int idExportacion, String usuario);
	public int obtenerSecuencia(String nombreTabla);
	public void registrarPackings(List<PackingListTO> packings,int idExportacion, String usuario);
	public ContenedorTO obtenerContenedorxNumero(int idExportacion,String numero);
	public List<ReporteContenedorTO> reporteContenedor(int idExportacion);
	public List<ReporteContenedorMaterialTO> reporteContenedorMaterial(int idContenedor);
	public List<ReportePackingListTO> reportePackingList(int idContenedor, String codigoMaterial);
	public void registrarPackingListCargaSuelta(int idExportacion, List<PackingListCargaSueltaTO> packing, String usuario);
	public List<PackingListCargaSueltaTO> obtenerPackingListCargaSuelta(int idExportacion);
	public void registrarPackingListAereo(int idExportacion, List<PackingListAereoTO> packing, String usuario);
	public List<PackingListAereoTO> obtenerPackingListAereo(int idExportacion);
}
