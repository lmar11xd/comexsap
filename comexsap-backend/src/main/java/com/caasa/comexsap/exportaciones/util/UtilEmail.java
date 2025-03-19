package com.caasa.comexsap.exportaciones.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Clase especializada en el envio de Email SMTP
 * 
 * @author acaceres
 *
 */
public class UtilEmail {

	private Logger objPv_logger = LogManager.getLogger(getClass());

//	 	String strPv_host="";
//	    String strPv_puerto="";
	String strPv_usuario = "";
	String strPv_contrasena = "";
	String strPv_de = "";
	String strPv_para = "";
	String strPv_cc = "";
	String strPv_asunto = "";
	String strPv_mensaje = "";
	List<String> lstPv_para = new ArrayList<>();
	List<String> lstPv_cc = new ArrayList<>();
	MimeMessage objPv_mimeMsg = null;
	Multipart objPv_multipart = null;

	/**
	 * Asiganacion de usuario y passqword para seguridad en el envio de email
	 * 
	 * @param u
	 * @param c
	 */
	public UtilEmail(String u, String c) {
		this.strPv_usuario = u;
		this.strPv_contrasena = c;
	}

	/**
	 * Método que permite iniciar conexion con SMTp de foma segura y de foma anonima
	 * 
	 * @param host
	 * @param puerto
	 * @param autenticacion
	 */
	public void conexionSmtp(String host, String puerto, boolean autenticacion) {
		try {
			if (host == null)
				objPv_logger.error("host de correo no encontrado");
			if (puerto == null)
				objPv_logger.error("usuario de correo no encontrado");

			Properties properties = new Properties();
			properties.put("mail.smtp.host", host);
			properties.put("mail.smtp.port", puerto);
			Session session;
			if (autenticacion) {
				properties.put("mail.smtp.auth", "true");
				properties.put("mail.smtp.starttls.enable", "true");
				session = Session.getDefaultInstance(properties, new Authenticator() {
					public PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(strPv_usuario, strPv_contrasena);
					}
				});
			} else {
				session = Session.getDefaultInstance(properties, null);
			}
			objPv_mimeMsg = new MimeMessage(session);
			objPv_multipart = new MimeMultipart();
			if (strPv_usuario != null) {
				try {
					objPv_mimeMsg.setFrom(new InternetAddress(strPv_usuario));// aqui se pone de/from
				} catch (AddressException ex) {
					objPv_logger.error("Error al poner usuario", ex);
				} catch (MessagingException ex) {
					objPv_logger.error("Error al poner usuario", ex);
				}
			}
		} catch (Exception ex) {
			objPv_logger.error("Error en conexionSmtp()", ex);
		}
	}

	public void ponerRemitente(String email, String nombre) {
		if (nombre != null && email != null) {
			try {
				objPv_mimeMsg.setFrom(new InternetAddress(email, nombre));
			} catch (UnsupportedEncodingException e) {
				objPv_logger.error("Error al poner remitente", e);
			} catch (MessagingException ex) {
				objPv_logger.error("Error al poner remitente", ex);
			}
		} else {
			objPv_logger.error("Datos de remitente erroneos");
		}
	}

	public void ponerAsunto(String asunto) {
		this.strPv_asunto = asunto;
		if (asunto != null) {
			try {
				objPv_mimeMsg.setSubject(asunto, "UTF-8");
			} catch (MessagingException ex) {
				objPv_logger.error("Error al poner asunto", ex);
			}
		}
	}

	public void ponerPara(String para) {
		this.strPv_para = para;
		if (para != null) {
			try {
				objPv_mimeMsg.setRecipient(Message.RecipientType.TO, new InternetAddress(para));
			} catch (AddressException ex) {
				objPv_logger.error("Error al poner para", ex);
			} catch (MessagingException ex) {
				objPv_logger.error("Error al poner para", ex);
			}
		}
	}

	public void ponerPara(List<String> correos) {
		this.lstPv_para = correos;
		if (correos != null) {
			try {
				for (String correo : correos) {
					objPv_mimeMsg.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
				}
			} catch (AddressException ex) {
				objPv_logger.error("Error al poner para", ex);
			} catch (MessagingException ex) {
				objPv_logger.error("Error al poner para", ex);
			}
		}
	}
	
	public void ponerTexto(String mensaje) {
		ponerTexto(mensaje, null, null);
	}

	public void ponerTexto(String mensaje, String urlLogoEmpresa, String urLogolEmail) {
		BodyPart messageBodyPart = new MimeBodyPart();
		try {
			// Fill the message
			// procesamos los logos
			messageBodyPart.setContent(mensaje, "text/html; charset=UTF-8");
			objPv_multipart.addBodyPart(messageBodyPart);

			// Create another bodypart to include the image attachment.
			if (urlLogoEmpresa != null) {
				messageBodyPart = new MimeBodyPart();
				DataSource ds = new FileDataSource(urlLogoEmpresa);
				messageBodyPart.setDataHandler(new DataHandler(ds));
				// Set the content-ID of the image attachment.
				// Enclose the image CID with the lesser and greater signs.
				messageBodyPart.setHeader("Content-ID", "<$logo_empresa>");
				messageBodyPart.setDisposition(MimeBodyPart.INLINE);
				// Add image attachment to multipart.
				objPv_multipart.addBodyPart(messageBodyPart);
			}

			if (urLogolEmail != null) {
				messageBodyPart = new MimeBodyPart();
				DataSource ds2 = new FileDataSource(urLogolEmail);
				messageBodyPart.setDataHandler(new DataHandler(ds2));
				// Set the content-ID of the image attachment.
				// Enclose the image CID with the lesser and greater signs.
				messageBodyPart.setHeader("Content-ID", "<$logo_email>");
				messageBodyPart.setDisposition(MimeBodyPart.INLINE);
				// Add image attachment to multipart.
				objPv_multipart.addBodyPart(messageBodyPart);
			}

			// messageBodyPart.setContent(mensaje, "text/html; charset=UTF-8");
			// objPv_multipart.addBodyPart(messageBodyPart);
		} catch (MessagingException ex) {
			objPv_logger.error("Error al poner texto", ex);
		}
	}

	public void ponerTextoMasivo(String mensaje) {
		BodyPart messageBodyPart = new MimeBodyPart();
		try {
			messageBodyPart.setContent(mensaje, "text/html; charset=UTF-8");
			objPv_multipart.addBodyPart(messageBodyPart);
		} catch (MessagingException ex) {
			objPv_logger.error("Error al poner texto masivo", ex);
		}
	}

	public void ponerConCopia(String cc) {
		this.strPv_cc = cc;
		if (cc != null) {
			try {
				objPv_mimeMsg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
			} catch (AddressException ex) {
				objPv_logger.error("Error al poner copia", ex);
			} catch (MessagingException ex) {
				objPv_logger.error("Error al poner copia", ex);
			}
		}
	}
	
	public void ponerConCopia(List<String> copias) {
		this.lstPv_cc = copias;
		if (copias != null) {
			try {
				for (String cc : copias) {
					objPv_mimeMsg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
				}
			} catch (AddressException ex) {
				objPv_logger.error("Error al poner copias", ex);
			} catch (MessagingException ex) {
				objPv_logger.error("Error al poner copias", ex);
			}
		}
	}

	
	public void ponerConCopiaOculta(String cco) {
		this.strPv_cc = cco;
		if (cco != null) {
			try {
				objPv_mimeMsg.setRecipients(Message.RecipientType.BCC, cco);
			} catch (AddressException ex) {
				objPv_logger.error("Error al poner copia oculta", ex);
			} catch (MessagingException ex) {
				objPv_logger.error("Error al poner copia oculta", ex);
			}
		}
	}

	public void ponerAdjunto(String rutaCompletaAdjunto, String nombreArchivo) {
		BodyPart messageBodyPart = new MimeBodyPart();
		DataSource source = new FileDataSource(rutaCompletaAdjunto);
		try {
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(nombreArchivo);
			objPv_multipart.addBodyPart(messageBodyPart);
		} catch (MessagingException ex) {
			objPv_logger.error("Error al poner adjunto", ex);
		}
	}

	public void ponerAdjunto(File objA_adjunto, String filename) {
		BodyPart messageBodyPart = new MimeBodyPart();
		DataSource source = new FileDataSource(objA_adjunto);
		try {
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(filename);
			objPv_multipart.addBodyPart(messageBodyPart);
		} catch (MessagingException ex) {
			objPv_logger.error("Error al poner adjunto", ex);
		}
	}
	

	/**
	 * Metodo que envia Email al mime configurado
	 */
	public void envioMensaje() {
		try {
			objPv_mimeMsg.setContent(objPv_multipart);
			objPv_mimeMsg.setSentDate(new Date());
			Transport.send(objPv_mimeMsg);
		} catch (SendFailedException sfex) {
			StringBuilder strL_sbTo = new StringBuilder();
			StringBuilder strL_sbCc = new StringBuilder();
			// StringBuilder strL_sbCco = new StringBuilder();
			Address[] strL_correoValidoNoEnviado = sfex.getValidUnsentAddresses();
			Set<Address> setCuentaValida = new HashSet<Address>();
			for (Address cuentaValida : strL_correoValidoNoEnviado) {
				setCuentaValida.add(cuentaValida);
			}
			try {
				if (strL_correoValidoNoEnviado != null) {
					for (Address cuentaValida2 : setCuentaValida) {
						Address[] TO = objPv_mimeMsg.getRecipients(Message.RecipientType.TO);
						for (Address to : TO) {
							if (cuentaValida2.equals(to)) {
								strL_sbTo.append(to);
								strL_sbTo.append(",");
							}
						}
						Address[] CC = objPv_mimeMsg.getRecipients(Message.RecipientType.CC);
						if (CC != null) {
							for (Address cc : CC) {
								if (cuentaValida2.equals(cc)) {
									strL_sbCc.append(cc);
									strL_sbCc.append(",");
								}
							}
						}
//						Address [] BCC = objPv_mimeMsg.getRecipients(Message.RecipientType.BCC);
//						for (Address cco : BCC) {
//							if(cuentaValida2.equals(cco)){
//								strL_sbCco.append(cco);
//								strL_sbCco.append(",");
//							}
//						}
					}
					objPv_mimeMsg.setRecipients(Message.RecipientType.TO, strL_sbTo.toString());
					objPv_mimeMsg.setRecipients(Message.RecipientType.CC, strL_sbCc.toString());
					// objPv_mimeMsg.setRecipients(Message.RecipientType.BCC,
					// strL_sbCco.toString());
					Transport.send(objPv_mimeMsg);// jvaler 28.01.2015 En QAS y DEV no debe enviar correos
				}
			} catch (MessagingException ex) {
				throw new RuntimeException(ex);
			} catch (Exception ex) {
				objPv_logger.error("Error - interno de renvio", ex);
				throw new RuntimeException(ex);
			}
		} catch (MessagingException ex) {
			objPv_logger.error("Error - no envia correo", ex);
			throw new RuntimeException(ex);
		}
	}
}
