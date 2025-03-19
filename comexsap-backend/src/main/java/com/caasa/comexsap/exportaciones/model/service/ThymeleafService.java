package com.caasa.comexsap.exportaciones.model.service;

import java.util.Map;

public interface ThymeleafService {
	public String createContent(String template, Map<String, Object> variables);
}
