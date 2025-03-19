package com.caasa.comexsap.exportaciones.model.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.caasa.comexsap.exportaciones.enums.AplicacionEnum;
import com.caasa.comexsap.exportaciones.enums.CorreoEnum;
import com.caasa.comexsap.exportaciones.model.service.ConfiguracionService;
import com.caasa.comexsap.exportaciones.model.service.MailService;
import com.caasa.comexsap.exportaciones.model.to.MailFileTO;
import com.caasa.comexsap.exportaciones.util.UtilEmail;

@Service
public class MailServiceImpl implements MailService {
	private Logger logger = LogManager.getLogger(getClass());
	
	@Value("${mailServer.isTest}")
	private boolean isTest;

	@Value("${mailServer.testEmail}")
	private String testEmail;
	
	@Autowired
	private ConfiguracionService configuracionService; 
	
	@Async
	@Override
	public void sendMailAsync(List<String> to, String subject, String content, List<String> copyEmails, List<MailFileTO> files) {
		try {
			//Obtener datos de configuración de correo desde bd
			Map<String, String> datosCorreo = configuracionService.obtenerMapaParametrosCorreo(AplicacionEnum.APLICACION.getValue());
			final String user = datosCorreo.get(CorreoEnum.CORREO_NOTIFICADOR.getValue());
			final String password = datosCorreo.get(CorreoEnum.CORREO_CONTRASENIA.getValue());
			final String host = datosCorreo.get(CorreoEnum.CORREO_SERVIDOR.getValue());
			final String port = datosCorreo.get(CorreoEnum.CORREO_PUERTO.getValue());
			final String ssl = datosCorreo.get(CorreoEnum.CORREO_SSL.getValue());
						
			Runnable objL_task = new Runnable() {
				@Override
				public void run() {
					try {
						UtilEmail utilEmail = new UtilEmail(user, password);
						utilEmail.conexionSmtp(host, port, Boolean.parseBoolean(ssl));
						utilEmail.ponerTexto(content);
						
						if(isTest) {
							utilEmail.ponerPara(testEmail);
							utilEmail.ponerAsunto("PRUEBA - " + subject);
						} else {
							utilEmail.ponerPara(to);
							utilEmail.ponerAsunto(subject);
							
							if(copyEmails != null) {
								for (String copy : copyEmails) {
									utilEmail.ponerConCopia(copy);
								}
							}
						}
						
						if(files != null) {
							for (MailFileTO mailFile : files) {
								utilEmail.ponerAdjunto(mailFile.getFile(), mailFile.getName());
							}
						}

						utilEmail.envioMensaje();
					} catch (Exception e) {
						logger.error("Error al enviar correo", e);
					}
				}
			};
			Thread objL_Thread = new Thread(objL_task, "ServiceThread ComexMail Envio Correo : ");
			objL_Thread.start();
			objL_Thread.join();
		} catch (Exception e) {
			logger.error("Error en sendMailAsync()", e);
		}
	}

}
