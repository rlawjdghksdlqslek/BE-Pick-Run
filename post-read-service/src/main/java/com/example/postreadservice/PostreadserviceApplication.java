package com.example.postreadservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableDiscoveryClient
@EnableMongoAuditing
@EnableScheduling
@SpringBootApplication
public class PostreadserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostreadserviceApplication.class, args);
	}

}
