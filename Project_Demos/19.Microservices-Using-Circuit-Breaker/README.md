* Create a copy of 17.Microservices-Discovering-Service-With-Eureka
* Add following dependency

		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
		</dependency>

* Add @EnableCircuitBreaker annotation to the Main class


		@EnableCircuitBreaker
		@EnableEurekaClient
		@SpringBootApplication
		public class Application {
		public static void main(String[] args) {
			SpringApplication.run(Application.class, args);
		}
		}

* Add following code to the controller


		@Controller
		public class PersonController {
		@Autowired
		private RestTemplate restTemplate;
		@LoadBalanced
		@Bean
		public RestTemplate restTemplate(RestTemplateBuilder builder) {
			return builder.build();
		}
		@HystrixCommand(fallbackMethod="getDefaultPerson")
		@RequestMapping(path="/persondetails", params={"personid"})
		public String getPersonDetails(@RequestParam String personid, Model m){
			Person p = restTemplate.getForObject("http://person-service/person/" + personid, Person.class);
			m.addAttribute("person", p);
			return "console";
		}
		public String getDefaultPerson(@RequestParam String personid, Model m){
			System.out.println("Fallback operation called!");
			m.addAttribute("person", new Person(0, "", ""));
			return "console";
		} 
		}

* Add Following Code to application.properties file

		server.port=8082
		#eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/
		eureka.client.register-with-eureka=true
		eureka.client.fetch-registry=true
		management.endpoints.web.exposure.include=hystrix.stream


* Run all 3 services -   

		15.Microservices-Creating-Eureka-Server
		16.Microservices-Registering-Service-With-Eureka
		19.Microservices-Using-Circuit-Breaker

* Open browser and access the Client   

		http://localhost:8082/persondetails?personid=1	
	
* Shut 16.Microservices-Registering-Service-With-Eureka service

* Open browser and access the Client   

		http://localhost:8082/persondetails?personid=1	
* You should get default response

