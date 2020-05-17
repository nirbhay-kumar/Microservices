package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

/*
Start a Eureka Service Registry
You first need a Eureka Service registry. You can use Spring Cloud’s @EnableEurekaServer to stand up a registry 
with which other applications can communicate. 
This is a regular Spring Boot application with one annotation (@EnableEurekaServer) 
added to enable the service registry
*/

/*
When the registry starts, it will complain (with a stacktrace) that there are no replica nodes to which 
the registry can connect. 
In a production environment, you will want more than one instance of the registry.
 For our simple purposes, however, it suffices to disable the relevant logging.

By default, the registry also tries to register itself, so you need to disable that behavior as well.

It is a good convention to put this registry on a separate port when using it locally.
*/