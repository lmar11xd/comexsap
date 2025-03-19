package com.caasa.comexsap.exportaciones.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Util {
	private static final Logger logger = LoggerFactory.getLogger(Util.class);
	public static final String FORMATOFECHACABECERA = "yyyy-MM-dd'T'HH:mm:ss'Z'";

	public static String convertDate(Date fecha) {
        String fechaConvertida;
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/YYYY");
        fechaConvertida = date.format(fecha);
        return fechaConvertida;
    }
	
	public static Date fechaUltimosSieteDias(int ano, int mes, int dia) {
        SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar fecha = Calendar.getInstance();
        fecha.set(ano, mes-1, dia - 7);
        fecha.setTimeZone(TimeZone.getTimeZone("America/Bogota"));

        int getano = fecha.get(Calendar.YEAR);
        int getmes = fecha.get(Calendar.MONTH) + 1;
        int getdia = fecha.get(Calendar.DAY_OF_MONTH);

        Date date = null;
        try {
            date = isoFormat.parse(
                    getano + "-" + getmes + "-" + getdia);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
	
	public static List<Date> filtro(){
		List<Date > fecha = new ArrayList<Date>();
		Date hoy = new Date();
		fecha.add(hoy);
		LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
		Date hoyMenosSiete = fechaUltimosSieteDias(year, month, day);
		fecha.add(hoyMenosSiete);
		return fecha;
	}
	
	public static String printPrettyJSONString(Object o) throws JsonProcessingException {
	    return new ObjectMapper().setDateFormat(getLocalFormat())
	        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).writerWithDefaultPrettyPrinter()
	        .writeValueAsString(o);
	}
	
	 public static DateFormat getLocalFormat() {
		    DateFormat dateFormat = new SimpleDateFormat(FORMATOFECHACABECERA);
		    dateFormat.setTimeZone(TimeZone.getDefault());
		    return dateFormat;
	}
	 
	 public static String ManualGenerateCode(String id,String codeTablePacking,int lengthCodeTablePacking){
		    String Codigo = codeTablePacking + lpad (id, '0', lengthCodeTablePacking);
			return Codigo;
	}
	 
	 public static String lpad(String str,char padString,int length){
		    while (str.length() < length) {
		        str = padString + str;
		    }
		    return str;
	}
	 
	public static String decimalFormat(float value, String format) {
		Locale currentLocale = Locale.getDefault();
		DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(currentLocale);
		otherSymbols.setDecimalSeparator('.');
		otherSymbols.setGroupingSeparator(','); 
		String strValue = "";
		try {
			DecimalFormat decimalFormat = new DecimalFormat(format, otherSymbols);
			strValue = decimalFormat.format(value);
			return strValue;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return "";
		}
	}
	
	public static boolean isNumeric(String cadena) {
        boolean resultado;
        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }
        return resultado;
    }
}
