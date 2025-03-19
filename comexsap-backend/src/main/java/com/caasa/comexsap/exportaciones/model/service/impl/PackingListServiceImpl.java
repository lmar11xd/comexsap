package com.caasa.comexsap.exportaciones.model.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caasa.comexsap.exportaciones.model.dao.PackingListDao;
import com.caasa.comexsap.exportaciones.model.service.PackingListService;
import com.caasa.comexsap.exportaciones.model.to.ContenedorTO;
import com.caasa.comexsap.exportaciones.model.to.ExportacionMaritimoTO;
import com.caasa.comexsap.exportaciones.model.to.FiltroPackingListRequestTO;
import com.caasa.comexsap.exportaciones.model.to.PackingListAereoTO;
import com.caasa.comexsap.exportaciones.model.to.PackingListCargaSueltaTO;
import com.caasa.comexsap.exportaciones.model.to.PackingListTO;
import com.caasa.comexsap.exportaciones.model.to.ReporteContenedorMaterialTO;
import com.caasa.comexsap.exportaciones.model.to.ReporteContenedorTO;
import com.caasa.comexsap.exportaciones.model.to.ReportePackingListTO;

@Service
public class PackingListServiceImpl implements PackingListService{

	@Autowired
	private PackingListDao packingListDao;	
	
	@Override
	public List<ExportacionMaritimoTO> listarPackingListxFiltro(FiltroPackingListRequestTO request) {
		return this.packingListDao.listarPackingListxFiltro(request);
	}

	@Override
	public ExportacionMaritimoTO obtenerExportacionxId(int id) {
		return this.packingListDao.obtenerExportacionxId(id);
	}

	@Override
	public List<ContenedorTO> listarContenedorxId(int id) {
		return this.packingListDao.listarContenedorxId(id);
	}

	@Override
	public List<PackingListTO> listarPackingListxId(int id) {
		return this.packingListDao.listarPackingListxId(id);
	}

	@Override
	public int registrarPackingList(List<ContenedorTO> contenedores, List<PackingListTO> packings, String usuario,
			int idExportacion) {
		return this.packingListDao.registrarPackingList(contenedores, packings, usuario, idExportacion);
	}

	@Override
	public List<ReporteContenedorTO> reporteContenedor(int idExportacion) {
		return this.packingListDao.reporteContenedor(idExportacion);
	}

	@Override
	public List<ReporteContenedorMaterialTO> reporteContenedorMaterial(int idContenedor) {
		return this.packingListDao.reporteContenedorMaterial(idContenedor);
	}

	@Override
	public List<ReportePackingListTO> reportePackingList(int idContenedor, String codigoMaterial) {
		return this.packingListDao.reportePackingList(idContenedor, codigoMaterial);
	}

	@Override
	public void registrarPackingListCargaSuelta(int idExportacion, List<PackingListCargaSueltaTO> packing,
			String usuario) {
		this.packingListDao.registrarPackingListCargaSuelta(idExportacion, packing, usuario);
	}

	@Override
	public List<PackingListCargaSueltaTO> obtenerPackingListCargaSuelta(int idExportacion) {
		return this.packingListDao.obtenerPackingListCargaSuelta(idExportacion);
	}

	@Override
	public void registrarPackingListAereo(int idExportacion, List<PackingListAereoTO> packing, String usuario) {
		this.packingListDao.registrarPackingListAereo(idExportacion, packing, usuario);
	}

	@Override
	public List<PackingListAereoTO> obtenerPackingListAereo(int idExportacion) {
		return this.packingListDao.obtenerPackingListAereo(idExportacion);
	}

}
