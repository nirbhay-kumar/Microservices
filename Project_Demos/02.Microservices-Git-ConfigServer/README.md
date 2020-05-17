* login to your github account

* Fork following repository

	https://github.com/kulsagar/SpringCloudConfig

* Create new project with following dependencies

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-config-server</artifactId>
		</dependency>

* Add following code to application.properties file
	
		server.port=8889
		spring.cloud.config.server.git.uri=https://github.com/kulsagar/SpringCloudConfig
		#spring.cloud.config.server.git.username=username
		#spring.cloud.config.server.git.password=password

	* Add following code in Main class
	
	
		@SpringBootApplication
		@EnableConfigServer
		public class Application {
		public static void main(String[] args) {
			SpringApplication.run(Application.class, args);
		}
		}
	
* Run the service and access following url in browser /postman

	http://localhost:8889/s1rates/default


------------------------------------------------------------

* Add following property to apllication.properties file

		spring.cloud.config.server.git.search-paths=station*

* Restart the service

	http://localhost:8889/s1rates/default
	This Will return values inside s1rates.properties file as well as values inside application.properties file in the root

* Type following URLs and observer the output

	http://localhost:8889/s1rates/master
	http://localhost:8889/s1rates/dev
	http://localhost:8889/s1rates/qa
	http://localhost:8889/s2rates/master
	http://localhost:8889/s2rates/dev
	http://localhost:8889/s2rates/qa
	

------------------------------------------------------------

* Type following URLs and observer the output
	
	http://localhost:8889/s1rates-default.properties
	http://localhost:8889/s1rates-default.yml
	http://localhost:8889/s1rates-default.json
	http://localhost:8889/s1rates-dev.json

------------------------------------------------------------

