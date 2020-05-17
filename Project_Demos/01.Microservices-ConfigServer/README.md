* Create a project with following dependencies

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-config-server</artifactId>
		</dependency>

* Main class

		@SpringBootApplication
		@EnableConfigServer
		public class Application {
		public static void main(String[] args) {
			SpringApplication.run(Application.class, args);
		}
		}
* Create a folder named **config** folder in **src/main/resources**

* Create 3 property files in this folder 
	1. app1.properties
	2. app2.properties
	3. app3.properties
	 	
* Type a key value pair in each of these files

		#app1.properties
		greeting=hello

		#app2.properties
		greeting=hey there

		#app3.properties
		greeting=howdy

* Add following code in  application.properties file 

		server.port=8888
		spring.profiles.active=native

* Start the app and access following urls
	
		http://localhost:8888/app1/default
		http://localhost:8888/app2/default
		http://localhost:8888/app3/default
