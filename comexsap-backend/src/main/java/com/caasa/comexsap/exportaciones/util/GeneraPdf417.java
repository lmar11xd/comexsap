package com.caasa.comexsap.exportaciones.util;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.swing.ImageIcon;

import com.lowagie.text.BadElementException;
import com.lowagie.text.pdf.BarcodePDF417;

public class GeneraPdf417
{
  public static com.lowagie.text.Image getInstance(String text)
    throws BadElementException
  {
    com.lowagie.text.Image img = null;
    BarcodePDF417 pdf417 = new BarcodePDF417();
    pdf417.setText(text);
    img = pdf417.getImage();
    
    return img;
  }
  
  public static java.awt.Image getInstanceAWT(String text)
    throws BadElementException
  {
    java.awt.Image img = null;
    BarcodePDF417 pdf417 = new BarcodePDF417();
    pdf417.setText(text);
    img = pdf417.createAwtImage(Color.BLACK, Color.WHITE);
    
    return img;
  }
  
  public static String getCantidad(String text)
    throws BadElementException
  {
    while (text.length() < 4) {
      text = "0" + text;
    }
    return text;
  }
  
  public static byte[] getBytes(Blob imagen)
    throws BadElementException, SQLException
  {
    return imagen.getBytes(1L, (int)imagen.length());
  }
  
  public static InputStream getInputStream(Blob imagen)
    throws BadElementException, SQLException
  {
    return imagen.getBinaryStream();
  }
  
  public static java.awt.Image getImagenAWT(Blob imagen)
    throws BadElementException, SQLException, IOException
  {
    return new ImageIcon(imagen.getBytes(1L, (int)imagen.length())).getImage();
  }
  
}
