* Create a project with following dependencies   

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-oauth2</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-security</artifactId>
		</dependency>

* Create Person class

	
		package com.example.demo.beans;

		public class Person {
		private String firstName;
		private String lastName;
		..............

* Create a Rest Controller which returns List of persons


		@RequestMapping("/data")
		public List<Person> getData(){

			List<Person> persons = new ArrayList<Person>();
			persons.add(new Person("Mo", "Salaha"));
			persons.add(new Person("Donald","Trump"));
			persons.add(new Person("Sagar","Kulkarni"));

			return persons;
		}	

* Test the service with postman use Basic Auth
  

------------------------------------------------------------

* Annotate the main class with @EnableResourceServer


		@SpringBootApplication
		@EnableResourceServer
		public class Application {


* Add following property in application.peroperties file

		server.port=9090
		security.oauth2.resource.user-info-uri=https://api.github.com/user

* Add following Code to the 08.Microservices-AuthorizationCodeGrantType ReportController.java

		@Autowired
		private OAuth2ClientContext clientContext;

		@RequestMapping("/home")
		public String getToken(){
			System.out.println("Token :: "+clientContext.getAccessToken().getValue());
			return "home";
		}

* Run the 08.Microservices-AuthorizationCodeGrantType App
* Copy the token printed on console of this app.
* Go to Postman
* Access *http://localhost:9090/data* service 
* Select No Auth in authorization type
* Add following header
	
		Authorization = Bearer 917f3d05ffeacea201e0aa36b3d922b77f24ae0e

* 		_917f3d05ffeacea201e0aa36b3d922b77f24ae0e_  is the token that you copied from client console
