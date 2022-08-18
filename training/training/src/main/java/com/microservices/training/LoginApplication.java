package com.microservices.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
@EnableDiscoveryClient
public class LoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}

	
	@Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
