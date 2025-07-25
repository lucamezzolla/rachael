package com.rachael.api.wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// @EnableDiscoveryClient è facoltativo con Spring Boot 3+, ma può aiutare
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RachaelApiWalletApplication {

	public static void main(String[] args) {
		SpringApplication.run(RachaelApiWalletApplication.class, args);
	}
}
