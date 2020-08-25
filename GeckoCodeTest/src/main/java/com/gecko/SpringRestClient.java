package com.gecko;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.gecko.bean.TestProduct;

public class SpringRestClient {

	// private static final String GET_EMPLOYEES_ENDPOINT_URL = "http://localhost:8080/api/v1/employees";
	private static final String GET_PRODUCT_ENDPOINT_URL = "http://localhost:8080/api/{id}";
	private static final String CREATE_PRODUCT_ENDPOINT_URL = "http://localhost:8080/api";

	public static void main(String[] args) {
		SpringRestClient springRestClient = new SpringRestClient();
		
		// Step1: first create a new employee
		// springRestClient.createProduct();
	}

	private void getProductById() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "99");

		RestTemplate restTemplate = new RestTemplate();
		TestProduct result = restTemplate.getForObject(GET_PRODUCT_ENDPOINT_URL, TestProduct.class, params);

		System.out.println(result);
	}
/*
	private void createProduct() {
		Product newProduct = new Product("Table", "Metal Table", "20200815121002");
		//newProduct.setId(Long.parseLong("1"));
		//System.out.println(">>>>>>>>>>>>>>>>>>>>>>"+newProduct.getId());

		RestTemplate restTemplate = new RestTemplate();
		Product result = restTemplate.postForObject(CREATE_PRODUCT_ENDPOINT_URL, newProduct, Product.class);

		System.out.println(result);
	}
*/
}
