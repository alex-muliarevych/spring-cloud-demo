package com.eureka.server.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {

	@RequestMapping("/")
	public String home() {
		return "Eureka server test access!";
	}

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}
}
