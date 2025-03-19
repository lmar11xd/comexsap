package com.caasa.comexsap.exportaciones.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorPrinterUtil {
	
	public static StringWriter printStackTraceCustomize(Exception e) {
		StringWriter sWriter = new StringWriter();

		// create PrintWriter for StringWriter
		PrintWriter pWriter = new PrintWriter(sWriter);

		// now print the stacktrace to PrintWriter we just created
		e.printStackTrace(pWriter);
		return sWriter;
	}
}