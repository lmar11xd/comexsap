package com.caasa.comexsap.exportaciones.model.dao.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import com.caasa.comexsap.exportaciones.model.dao.ReporteDao;
import com.caasa.comexsap.exportaciones.model.domain.ConfigtParametro;
import com.caasa.comexsap.exportaciones.model.persistence.ConfigtParametroMapper;
import com.caasa.comexsap.exportaciones.model.persistence.extended.ReporteExtendedMapper;
import com.caasa.comexsap.exportaciones.model.to.FiltroReporteMaritimoRequest;
import com.caasa.comexsap.exportaciones.model.to.FiltroReporteTerrestreRequest;
import com.caasa.comexsap.exportaciones.model.to.ParametroTO;
import com.caasa.comexsap.exportaciones.model.to.ReporteFacturaPosicionTO;
import com.caasa.comexsap.exportaciones.model.to.ReporteMaritimoTO;
import com.caasa.comexsap.exportaciones.model.to.ReportePdfTO;
import com.caasa.comexsap.exportaciones.model.to.ReporteTerrestreTO;
import com.caasa.comexsap.exportaciones.model.to.RequestCertificadoTerrestreTO;
import com.caasa.comexsap.exportaciones.model.to.RequestPosicionFacturaTO;
import com.caasa.comexsap.exportaciones.model.to.RequestReporteFacturaComercialTO;
import com.caasa.comexsap.exportaciones.model.to.RequestReporteFacturaTO;
import com.caasa.comexsap.exportaciones.util.Constantes;
import com.caasa.comexsap.exportaciones.util.ReporteGenerico;
import com.caasa.comexsap.exportaciones.util.Util;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Repository("ReporteDao")
public class ReporteDaoImpl extends ReporteGenerico implements ReporteDao {

	@Autowired
	private ReporteExtendedMapper reporteExtendedMapper;

	@Autowired
	private ConfigtParametroMapper parametroMapper;

	@Override
	public ParametroTO obtenerParametroxId(int id) {
		ParametroTO parametro = null;
		ConfigtParametro record = parametroMapper.selectByPrimaryKey(id);
		if (record != null) {
			parametro = new ParametroTO();
			BeanUtils.copyProperties(record, parametro);
		}
		return parametro;
	}

	@Override
	public ReportePdfTO imprimirExportacionMaritimo(RequestReporteFacturaTO request) throws Exception {
		List<ReporteFacturaPosicionTO> listaPosicion = new ArrayList<ReporteFacturaPosicionTO>();
		String format = "#,##0.00", formatInt = "#,##0";
		String valorFobTotal = "", flete = "", seguro = "", valorCfrTotal = "", direccionShipper = "", direccionConsignatario = "", direccionNotificante = "";
		try {
			final File imgLogo = ResourceUtils.getFile("classpath:imagenes/logo_aceros.png");
			final File imgLogoSeguridad = ResourceUtils.getFile("classpath:imagenes/elige-seguridad.PNG");

			valorFobTotal = Util.decimalFormat(request.getValorFOBTotal(), format);
			flete = Util.decimalFormat(request.getFleteTotal(), format);
			seguro = Util.decimalFormat(request.getSeguroTotal(), format);
			valorCfrTotal = Util.decimalFormat(request.getValorCFRTotal(), format);
			
			if(request.getDireccionShipper() != null) direccionShipper = request.getDireccionShipper().replace("\\,", ",").replace("\\n", "<br/>");
			if(request.getDireccionConsignatario() != null) direccionConsignatario = request.getDireccionConsignatario().replace("\\,", ",").replace("\\n", "");
			if(request.getDireccionNotificante() != null) direccionNotificante = request.getDireccionNotificante().replace("\\,", ",").replace("\\n", "");
			
			final HashMap<String, Object> parameters = new HashMap<>();
			parameters.put("logo-aceros", new FileInputStream(imgLogo));
			parameters.put("logo-seguridad", new FileInputStream(imgLogoSeguridad));
			parameters.put("textoFecha", "");
			parameters.put("booking", request.getBooking());
			parameters.put("agenteAduana", request.getAgenteAduana());
			parameters.put("shipper", request.getShipper());
			parameters.put("direccionShipper", direccionShipper);
			parameters.put("consignatario", request.getConsignatario());
			parameters.put("direccionConsignatario", direccionConsignatario);
			parameters.put("notificante", request.getNotificante());
			parameters.put("direccionNotificante", direccionNotificante);
			parameters.put("puertoOrigen", request.getPuertoOrigen());
			parameters.put("puertoDestino", request.getPuertoDestino());
			parameters.put("fechaCarguio", request.getFechaCarguio());
			parameters.put("tipoTransporte", request.getTipoTransporte());
			parameters.put("motonave", request.getMotonave());
			parameters.put("regimen", request.getRegimen());
			parameters.put("etiquetaValor", request.getEtiquetaValor());
			parameters.put("valorFobTotal", valorFobTotal);
			parameters.put("flete", flete);
			parameters.put("seguro", seguro);
			parameters.put("valorCfrTotal", valorCfrTotal);
			
			listaPosicion = request.getPosiciones();
			if(listaPosicion != null) {
				for (ReporteFacturaPosicionTO posicion : listaPosicion) {
					String cantidad = "", valorFob = "";
					cantidad = Util.decimalFormat(Float.parseFloat(posicion.getCantidad()), formatInt);
					valorFob = Util.decimalFormat(Float.parseFloat(posicion.getValorFOB()), format);
					posicion.setCantidad(cantidad);
					posicion.setValorFOB(valorFob);
				}
			}
			JRBeanArrayDataSource dsPosicion = new JRBeanArrayDataSource(listaPosicion.toArray());
			parameters.put("dsPosicion", dsPosicion);

			ByteArrayOutputStream stream = exportPdf(Constantes.MARITIMO_EXPORTACION, parameters);
			byte[] bs = stream.toByteArray();
			ReportePdfTO reporte = new ReportePdfTO();
			reporte.setFilename("reporte_exportacion.pdf");
			reporte.setStream(new ByteArrayInputStream(bs));
			reporte.setLength(bs.length);
			return reporte;
		} catch (FileNotFoundException e) {
			throw new Exception(e);
		} catch (JRException e) {
			throw new Exception(e);
		} catch (IOException e) {
			throw new Exception(e);
		}
	}

	@Override
	public ResponseEntity<Resource> imprimirFacturaComercial(RequestReporteFacturaComercialTO request) throws Exception {
		try {
			final File imgLogo = ResourceUtils.getFile("classpath:imagenes/logo_aceros.png");
			InputStream targetStream = null;
			targetStream = new ByteArrayInputStream(getReportFile(Constantes.MARITIMO_FACTURA_COMERCIAL, "pdf"));
			final JasperReport report = (JasperReport) JRLoader.loadObject(targetStream);
			final HashMap<String, Object> parameters = new HashMap<>();
			parameters.put("logo-aceros", new FileInputStream(imgLogo));
			parameters.put("direccionShipper", request.getDireccionShipper());
			parameters.put("direccionConsignatario", request.getDireccionConsignatario());
			parameters.put("cliente", request.getCliente());
			parameters.put("puertoEmbarque", request.getPuertoEmbarque());
			parameters.put("navio", request.getNavio());
			parameters.put("puertoDescarga", request.getPuertoDescarga());
			parameters.put("booking", request.getBooking());
			parameters.put("factura", request.getFactura());
			parameters.put("textoFecha", request.getTextoFecha());
			parameters.put("emitidoEn", request.getEmitidoEn());
			parameters.put("condicionFlete", request.getCondicionFlete());
			parameters.put("formaPago", request.getFormaPago());
			parameters.put("paisOrigen", request.getPaisOrigen());
			parameters.put("referencia", request.getReferencia());
			parameters.put("nombreMonto", request.getDescripcionTotal());
			parameters.put("nombreUsuario", request.getNombreUsuario());
			
			//Buscar pesos del PackingList
			for (RequestPosicionFacturaTO posicion : request.getPosiciones()) {
				float pesoComex = this.obtenerPesoPackingList(posicion.getId());
				posicion.setPesoNeto(pesoComex);
			}
			
			JRBeanArrayDataSource dsPosicion = new JRBeanArrayDataSource(request.getPosiciones().toArray());
			JRBeanArrayDataSource dsTotales = new JRBeanArrayDataSource(request.getTotales().toArray());
			parameters.put("dsReporte", dsPosicion);
			parameters.put("dsTotales", dsTotales);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
			byte[] reporte = JasperExportManager.exportReportToPdf(jasperPrint);
			String sdf = (new SimpleDateFormat("dd/MM/yyyy")).format(new Date());
			StringBuilder stringBuilder = new StringBuilder().append("InvoicePDF:");
			ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
					.filename(stringBuilder.append("").append("generateDate:").append(sdf).append(".pdf").toString())
					.build();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentDisposition(contentDisposition);
			return ResponseEntity.ok().contentLength((long) reporte.length).contentType(MediaType.APPLICATION_PDF)
					.headers(headers).body(new ByteArrayResource(reporte));
		} catch (Exception ex) {
			throw new Exception(ex);
		}
	}

	@Override
	public List<ReporteMaritimoTO> reporteMaritimoContenedores(FiltroReporteMaritimoRequest filtro) {
		return this.reporteExtendedMapper.reporteMaritimoContenedores(filtro);
	}

	@Override
	public List<ReporteMaritimoTO> reporteMaritimoCargaSuelta(FiltroReporteMaritimoRequest filtro) {
		return this.reporteExtendedMapper.reporteMaritimoCargaSuelta(filtro);
	}

	@Override
	public List<ReporteTerrestreTO> reporteTerrestre(FiltroReporteTerrestreRequest filtro) {
		return this.reporteExtendedMapper.reporteTerrestre(filtro);
	}

	@Override
	public ResponseEntity<Resource> imprimirCertificadoTerrestre(RequestCertificadoTerrestreTO request) throws Exception {
		String format = "#,##0.00";
		String montoTotal = "";
		try {
			final File imgLogo = ResourceUtils.getFile("classpath:imagenes/logo_aceros.png");
			final File imgLogoSeguridad = ResourceUtils.getFile("classpath:imagenes/elige-seguridad.PNG");
			montoTotal = Util.decimalFormat(request.getMontoTotal(), format);
			InputStream targetStream = null;
			targetStream = new ByteArrayInputStream(getReportFile(Constantes.TERRESTRE_CERTIFICADO, "pdf"));
			final JasperReport report = (JasperReport) JRLoader.loadObject(targetStream);
			final HashMap<String, Object> parameters = new HashMap<>();
			parameters.put("logo-aceros", new FileInputStream(imgLogo));
			parameters.put("logo-seguridad", new FileInputStream(imgLogoSeguridad));
			parameters.put("textoAduana", request.getTextoAduana());
			parameters.put("textoCliente", request.getTextoCliente());
			parameters.put("textoDestinatario", request.getTextoDestinatario());
			parameters.put("factura", request.getFactura());
			parameters.put("montoTotal", montoTotal);
			parameters.put("formaPago", request.getFormaPago());
			parameters.put("textoFecha", request.getTextoFecha());

			JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());
			byte[] reporte = JasperExportManager.exportReportToPdf(jasperPrint);
			String sdf = (new SimpleDateFormat("dd/MM/yyyy")).format(new Date());
			StringBuilder stringBuilder = new StringBuilder().append("CertificadoPDF:");
			ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
					.filename(stringBuilder.append("").append("generateDate:").append(sdf).append(".pdf").toString())
					.build();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentDisposition(contentDisposition);
			return ResponseEntity.ok().contentLength((long) reporte.length).contentType(MediaType.APPLICATION_PDF)
					.headers(headers).body(new ByteArrayResource(reporte));
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@Override
	public float obtenerPesoPackingList(int idExportacionPedido) {
		return this.reporteExtendedMapper.obtenerPesoPackingList(idExportacionPedido);
	}

}
