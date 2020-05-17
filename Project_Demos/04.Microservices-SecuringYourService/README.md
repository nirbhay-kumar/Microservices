* Create a Copy of 02.Microservices-Git-ConfigServer project

* Add spring security dependency

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>


3. update the application.yml file with following entries
  
		server:
		  port: 8888
		spring:
		  cloud:
		    config:
		      server:
			git:
			  uri: https://github.com/kulsagar/SpringCloudConfig
			  #username: uname
			  #password: pass
			  search-paths:
			  - 'station*'
		  security:
		    user:
		      name: khulja
		      password: simsim                
      
4. Use postman to send the request
5. in authorization tab 
		
	Select *Basic Auth*
	enter the user and password entered in application.yml
 
