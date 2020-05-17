package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

import com.example.demo.service.CustomUserInfoTokenServices;

@SpringBootApplication
@EnableResourceServer
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private ResourceServerProperties sso;
	
	@Bean
	public ResourceServerTokenServices myUserInfoTokenServices(){
		return new CustomUserInfoTokenServices(sso.getUserInfoUri(), sso.getClientId());
	}
}
