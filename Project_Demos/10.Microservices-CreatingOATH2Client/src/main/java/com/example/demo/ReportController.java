
package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	@Autowired
	private OAuth2RestTemplate oauth2RestTemplate;
	
	@RequestMapping("/persons")
	public String getPersons(Model model){
	ResponseEntity<List<Person>> persons = oauth2RestTemplate.exchange("http://localhost:9090/data", HttpMethod.GET, null, new ParameterizedTypeReference<List<Person>>() {
	});
	System.out.println(persons.getBody());
	model.addAttribute("persons", persons.getBody());
	return "reports";
	}
}
