* Create A Project with actuator & Eureka Server Dependency

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
		</dependency>
* Annotate Main class with @EnableEurekaServer 


		@EnableEurekaServer
		@SpringBootApplication
		public class Application {
		public static void main(String[] args) {
			SpringApplication.run(Application.class, args);
		}
		}

* Add Following code in application.properties

		server.port=8761
		# Eureka requires to be running on 8761
		eureka.client.register-with-eureka=false
		eureka.client.fetch-registry=false


* Run the project and access the URL **http://localhost:8761/**

* Add following properties in application.properties

		eureka.datacenter=chicago
		eureka.environment=prod 

* Run the project and access the URL **http://localhost:8761/** and observe that values are displayed on the page
