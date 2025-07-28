package com.rachael.api.album;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RachaelApiAlbumApplication {

	public static void main(String[] args) {
		SpringApplication.run(RachaelApiAlbumApplication.class, args);
	}

}
