package com.boxing_app.boxing_arena_management_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BoxingArenaManagementAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoxingArenaManagementAppApplication.class, args);
	}

}
