package com.example.readservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ReadserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadserviceApplication.class, args);
	}

}
