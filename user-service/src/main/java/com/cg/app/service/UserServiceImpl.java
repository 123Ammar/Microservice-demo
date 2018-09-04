package com.cg.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserServiceImpl implements UserService {
	
	@Value("${productServiceURI}")
	private String productServiceURI;
	
	@Autowired
	private RestTemplate restTemplate;

	
	@SuppressWarnings("unchecked")
	@HystrixCommand(fallbackMethod = "getBackup")
	@Override
	public String getProductList() {
		
		String products = null;
		
		products = restTemplate.getForObject(productServiceURI,String.class);
		
		return products;
	}

	public String getBackup() {
		return "Product Service is currently not available please try again later";
	}

}
