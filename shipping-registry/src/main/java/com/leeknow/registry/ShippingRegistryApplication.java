package com.leeknow.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ShippingRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShippingRegistryApplication.class, args);
	}

}
