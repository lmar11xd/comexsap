package com.caasa.comexsap.exportaciones.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.caasa.comexsap.exportaciones.model.service.MailService;
import com.caasa.comexsap.exportaciones.model.to.MailTo;

@RestController
@RequestMapping("/api/mailController")
public class MailController {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MailService mailService;
		
	@PostMapping("/sendEmail")
	public ResponseEntity<String> sendEmail(@RequestBody MailTo request){
		try {
			List<String> to = new ArrayList<>();
			to.add(request.getTo());
			
			mailService.sendMailAsync(to, request.getSubject(), request.getBody(), null, null);
		} catch(Exception e) {
			logger.error("Error al enviar correo", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}		
		return ResponseEntity.ok("Correo enviado");
	}	
}
