package com.caasa.comexsap.exportaciones.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {
	
	public static String ExceptionToString(Exception objA_excepcion){
		StringWriter objL_stringWriter = new StringWriter();
		objA_excepcion.printStackTrace(new PrintWriter(objL_stringWriter));
		String strL_trace = objL_stringWriter.toString();
		if(strL_trace != null && strL_trace.length() > 1000){
			strL_trace = strL_trace.substring(0,1000);
		}
		return strL_trace;
	}

}
