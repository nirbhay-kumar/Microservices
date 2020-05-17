package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}


/*
localhost:8888/s1rates/default
localhost:8888/s1rates/master
localhost:8888/s1100/master -------still looks default

add search-paths - station*
now it can search from subfolders also

localhost:8888/s1rates/dev
localhost:8888/s2rates/qa


localhost:8888/s1rates-default.properties
localhost:8888/s1rates-default.yml
localhost:8888/s1rates-default.json
*/