
package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@EnableOAuth2Sso
// class ReportController extends extends WebSecurityConfigurerAdapter
// Uncomment the code for ignoring Authentication on certain pages.
public class ReportController {
	
	@RequestMapping("/")
	public String loadHome(){
		return "home";
	}
	
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/","/login**").permitAll().anyRequest().authenticated();
	}*/
	
	/* Temporary code to get the token from github */
	
	@Autowired
	private OAuth2ClientContext clientContext;
	
	@RequestMapping("/home")
	public String getToken(){
		System.out.println("Token :: "+clientContext.getAccessToken().getValue());
		return "home";
	}
}
