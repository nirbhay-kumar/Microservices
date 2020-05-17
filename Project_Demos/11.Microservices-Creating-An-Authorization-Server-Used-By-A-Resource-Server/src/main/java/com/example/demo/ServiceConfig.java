package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

@Configuration
public class ServiceConfig extends GlobalAuthenticationConfigurerAdapter {
	
	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("mosalah").password("pass1").roles("USER").and()
		.withUser("klopp").password("pass2").roles("USER", "OPERATOR");
	}

}
