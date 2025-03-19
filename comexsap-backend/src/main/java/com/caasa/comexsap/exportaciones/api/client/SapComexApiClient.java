package com.caasa.comexsap.exportaciones.api.client;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.caasa.comexsap.exportaciones.api.to.FiltroPrecioMaterialTO;
import com.caasa.comexsap.exportaciones.api.to.ResultadoPrecioMaterialEnvelopTO;
import com.caasa.comexsap.exportaciones.spring.SapApiProperties;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class SapComexApiClient {

	@Autowired
	private SapApiProperties sapApiProperties;

	@Autowired
	private RestTemplate restTemplate;

	public ResultadoPrecioMaterialEnvelopTO getObtenerPrecioMaterial(FiltroPrecioMaterialTO filtro)
			throws JsonProcessingException {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("Authorization", "Bearer ".concat(sapApiProperties.getJwtToken()));
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<FiltroPrecioMaterialTO> request = new HttpEntity<FiltroPrecioMaterialTO>(filtro, headers);
		ResponseEntity<ResultadoPrecioMaterialEnvelopTO> responseEntity = restTemplate.exchange(
				sapApiProperties.getSap_get_precio_material(), HttpMethod.POST, request,
				ResultadoPrecioMaterialEnvelopTO.class);
		return responseEntity.getBody();

	}

}
