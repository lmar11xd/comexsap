package com.caasa.comexsap.exportaciones.model.service;

import java.util.List;

import com.caasa.comexsap.exportaciones.model.to.MailFileTO;

public interface MailService {
	public void sendMailAsync(List<String> to, String subject, String content, List<String> copyEmails, List<MailFileTO> files);
}
