package com.caasa.comexsap.exportaciones.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReporteGenerico {

	private Logger objPv_logger = LoggerFactory.getLogger(getClass());;
	private static final String REPORT_FOLDER = "reportes/pdf";
	private static final String JASPER = ".jasper";

	public byte[] generarPDF(String nombre , Map<String, Object> parameters) {

		byte[] reporte = null;

		JasperPrint jasperPrint;
		JasperReport jasperReport;

		InputStream targetStream = new ByteArrayInputStream(getReportFile(nombre,"pdf"));

		try {
			jasperReport = (JasperReport) JRLoader.loadObject(targetStream);
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			reporte = JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (JRException ex) {
			objPv_logger.error(ex.getMessage(), ex);
		}

		return reporte;
	}
	
	public ByteArrayOutputStream exportPdf(String nombreArchivo, Map<String, Object> parametros) throws JRException, IOException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		ClassPathResource resource = new ClassPathResource(REPORT_FOLDER + File.separator + nombreArchivo + JASPER);
		
		InputStream inputStream = resource.getInputStream();
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, new JREmptyDataSource());
		JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
		
		return stream;
	}

	public void generarHTML(String nombre,Map<String, Object> parameters) {

		JasperPrint jasperPrint;
		JasperReport jasperReport;

		InputStream targetStream = new ByteArrayInputStream(getReportFile(nombre,"html"));
		
		String rutaPlantilla = parameters.get("fileServer").toString();
		
		try {
			jasperReport = (JasperReport) JRLoader.loadObject(targetStream);
			jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
			JasperExportManager.exportReportToHtmlFile(jasperPrint, rutaPlantilla);
		} catch (JRException ex) {
			objPv_logger.error(ex.getMessage(), ex);
		}
		
	}

	protected byte[] getReportFile(String nombre,String type) {
		try {
			InputStream stream = this.getClass().getClassLoader().getResourceAsStream("reportes/" + type + "/" + nombre + ".jasper");

			
			
			byte[] buffer = new byte[1024];
			ByteArrayOutputStream os = new ByteArrayOutputStream();

			int line = 0;
			// read bytes from stream, and store them in buffer
			while ((line = stream.read(buffer)) != -1) {
				// Writes bytes from byte array (buffer) into output stream.
				os.write(buffer, 0, line);
			}
			stream.close();
			os.flush();
			os.close();

			return os.toByteArray();
		} catch (IOException ex) {
			return null;
		}
	}
}
