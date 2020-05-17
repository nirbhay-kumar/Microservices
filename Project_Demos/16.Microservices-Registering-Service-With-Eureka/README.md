* Create a Project with following dependencies
	
	
		<dependency>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

* Create Person class

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

* Create a RestController


		@RestController
		public class PersonController {
		@RequestMapping("/person/{id}")
		public Person getPerson(@PathVariable int id){

			switch(id){
			case 1: return new Person(1, "Justin", "Bieber");
			case 2: return new Person(2, "Taylor", "Swift");
			case 3: return new Person(3, "Ed", "Sheeran");
			}
			return null;
		}
		}

* Start the server and test the Service in browser
*http://localhost:8080/person/3*

* Add following dependency to pom.xml

		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
		</dependency>

 
 * Annotate Main class with @EnableEurekaClient

		@EnableEurekaClient
		@SpringBootApplication
		public class Application {
		public static void main(String[] args) {
			SpringApplication.run(Application.class, args);
		}
		}

 * Create bootstrap.properties file in resource folder and add following code to it
 
		spring.application.name=person-service
 
* Add following code to application.properties

		server.port=0
		# Runs the app on available random port
		#eureka.client.serviceUrl.defaultZone=http://localhost:8761/eureka
		eureka.client.register-with-eureka=true
		eureka.client.fetch-registry=true
		eureka.instance.instance-id=${spring.application.name}:${random.int}
		eureka.instance.hostname=localhost
