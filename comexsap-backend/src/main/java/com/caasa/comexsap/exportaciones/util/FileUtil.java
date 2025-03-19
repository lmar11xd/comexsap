package com.caasa.comexsap.exportaciones.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

public class FileUtil {
	
	public static String readFileToString(File file) throws Exception {
		  String strL_content = "";
	      FileInputStream fileIn = null;
	      try
	      {
	    	  fileIn = new FileInputStream(file);
	    	  strL_content = IOUtils.toString(fileIn);
	      }catch(Exception e){
	    	  throw new Exception("Error al leer archivo",e);
	      }
	      finally{
	    	  if (fileIn != null){ 
	    		fileIn.close();
	    	  }
	      }
	      return strL_content;
	}
	
	public static byte[] readContentIntoByteArray(File file) throws Exception {
	      FileInputStream fileIn = null;
	      byte[] bFile = new byte[(int) file.length()];
	      try
	      {
	         //convert file into array of bytes
	    	  fileIn = new FileInputStream(file);
	    	  fileIn.read(bFile);       
	      }finally{
	    	  if (fileIn != null){ 
	    		fileIn.close();
	    	  }
	      }
	      return bFile;
	 }	
	
	public static void generarArchivo(String strA_rutaDestino, String strA_filename, List<Map<String,Object>> objA_listaMapa) throws Exception{
		FileWriter writer = new FileWriter(strA_rutaDestino+File.separator+strA_filename, false);
		int j = 0;
		for(Map<String,Object> objL_mapa : objA_listaMapa){
			int n = objL_mapa.size();
			int i = 0;
			if(j == 0){
				for (Map.Entry<String, Object> entry : objL_mapa.entrySet())
			    {
			       writer.write(entry.getKey());
			       if(i + 1 < n){
				       writer.write("\t");		    	   
			       }
			       i++;
			       
			    }
				writer.write("\r\n");
				i = 0;
			}					
			for (Map.Entry<String, Object> entry : objL_mapa.entrySet())
		    {
			   if(entry.getValue() != null){
			       writer.write(entry.getValue().toString());
			   }else{
				   writer.write("");
			   }
		       if(i + 1 < n){
			       writer.write("\t");		    	   
		       }
		       i++;
		       
		    }
			writer.write("\r\n");
			j++;
		}	    
	    writer.close();	      
	}
	
	public static void writeByteToFile(byte[] bytes, String rutaCompletaArchivo) throws FileNotFoundException, IOException {
	    File file = new File(rutaCompletaArchivo);
	    try(OutputStream os = new FileOutputStream(file);) {	      
	      os.write(bytes);
	    } 
	}
	    
}
