package com.caasa.comexsap.exportaciones.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil {
	
	public static Date string2DateYYYYMMDD(String dateText) throws Exception{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.parse(dateText);
	}
	
	public static String getFormatedNow(){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		return reportDate;
	}
	
	public static String getFormatedNowSlash(){
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		return reportDate;
	}
	
	public static String getFormatedLarge(){
		DateFormat df = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
		Date today = Calendar.getInstance().getTime();
		String reportDate = df.format(today);
		return reportDate;
	}
	
	public static String getFormatedLarge(Date date){
		if(date == null) return "";
		
		DateFormat df = new SimpleDateFormat("dd 'de' MMMM, yyyy", new Locale("ES"));
		String reportDate = df.format(date);
		return reportDate;
	}
	
	public static Date string2DateDDMMYYYY(String dateText) throws Exception{
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		return formatter.parse(dateText);
	}
	
	public static String string2DateTexto(String dateText){
		String strL_fecha = "";
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaD = formatter.parse(dateText);
			//SimpleDateFormat formatter = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es_ES"));
			formatter = new SimpleDateFormat("dd 'de' MMMM 'del' yyyy", new Locale("ES"));
			strL_fecha = (formatter.format(fechaD)).toUpperCase();
		}catch(Exception e){
			strL_fecha = dateText;
		}
		return strL_fecha;
	}
	public static String string2StringYYYYMMDD(String dateText){
		String strL_fecha = "";
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaD = formatter.parse(dateText);
			//SimpleDateFormat formatter = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es_ES"));
			formatter = new SimpleDateFormat("yyyy-MM-dd");
			strL_fecha = (formatter.format(fechaD)).toUpperCase();
		}catch(Exception e){
			strL_fecha = dateText;
		}
		return strL_fecha;
	}
	
	public static Date nowUtilDate() {
		Date fecha = new Date();
	    return fecha;
	}
	
	public static java.sql.Date nowSQLDate() {
		Date fecha = new Date();
		java.sql.Date fechaSQL = null;
		if(fecha != null){
			fechaSQL = new java.sql.Date(fecha.getTime());
		}
	    return fechaSQL;
	}
	
	public static java.sql.Date toSQLDate(Date fecha) {
		java.sql.Date fechaSQL = null;
		if(fecha != null){
			fechaSQL = new java.sql.Date(fecha.getTime());
		}
	    return fechaSQL;
	}

	public static Date toJavaDate(java.sql.Date fecha) {
		Date fechaJava = null;
		if(fecha != null){
			fechaJava = new Date(fecha.getTime());
		}
	    return fechaJava;
	}
	
	public static String formatGC(GregorianCalendar calendar){
	    SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	    fmt.setCalendar(calendar);
	    String dateFormatted = fmt.format(calendar.getTime());
	    return dateFormatted;
	}
	
	public static String obtenerPeriodoPrevio(int intA_numMeses){
		//El periodo debe tener el formato MM-yyyy
		Date currentDate = null;
		String dateString = null;
		try {
		    Calendar c = new GregorianCalendar();
		    c.set(Calendar.HOUR_OF_DAY, 0); // anything 0 - 23
		    c.set(Calendar.MINUTE, 0);
		    c.set(Calendar.SECOND, 0);
		    c.add(Calendar.MONTH, intA_numMeses);//previous month
		    //c.add(Calendar.MONTH, -2);//two months back
		    currentDate = c.getTime(); // the midnight, that's the first second
		    // of the day.
		    SimpleDateFormat sdfr = new SimpleDateFormat("MM-yyyy");
		    dateString = sdfr.format(currentDate);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return dateString;
	}
	
	public static String obtenerAnioActual(Date fecha1){
	    try {
	    	Date fechaActual = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY");
		    return dateFormat.format(fechaActual);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return null;
	}
	
}
