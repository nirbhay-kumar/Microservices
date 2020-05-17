* Create a project with following dependencies
	web, thymeleaf, Eureka client

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>

* application.properties

		server.port=8082
		#eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka/
		eureka.client.register-with-eureka=true
		eureka.client.fetch-registry=true

* Main class


		@EnableEurekaClient
		@SpringBootApplication
		public class Application {
		public static void main(String[] args) {
			SpringApplication.run(Application.class, args);
		}
		}

* Controller Class


		@Controller
		public class PersonController {
		@Autowired
		private RestTemplate restTemplate;
		@LoadBalanced
		@Bean
		public RestTemplate restTemplate(RestTemplateBuilder builder) {
			return builder.build();
		}
		@RequestMapping(path="/persondetails", params={"personid"})
		public String getPersonDetails(@RequestParam String personid, Model m){
			Person p = restTemplate.getForObject("http://person-service/person/" + personid, Person.class);
			m.addAttribute("person", p);
			return "console";
		}
		@RequestMapping("/")
		public @ResponseBody String hello(){
			return "hello";
		}
		}

* Person class

		public class Person {
		private int id;
		private String firstName;
		private String lastName;
		public Person() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Person(int id, String firstName, String lastName) {
			super();
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		@Override
		public String toString() {
			return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
		}
		}

* Access the client application http://localhost:8082/persondetail?personid=1
