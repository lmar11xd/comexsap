package com.caasa.comexsap.exportaciones.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "mybatis.config")
public class MybatisProperties {

	private String maxcommit;

	public String getMaxcommit() {
		return maxcommit;
	}

	public void setMaxcommit(String maxcommit) {
		this.maxcommit = maxcommit;
	}
	
}
