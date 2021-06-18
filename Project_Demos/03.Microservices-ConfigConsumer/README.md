* Create new springboot project with following dependencies

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
			<artifactId>spring-cloud-starter-config</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-bootstrap</artifactId>
		</dependency>

* Add following code in application.properties

	spring.application.name=s1rates

* Add following code in bootstrap.properties

	spring.profiles.active=default
	spring.cloud.config.uri=http://localhost:8889
	#URL of 02.Microservices-Git-ConfigServer service
	
* Create a controller and add following code

		@Controller
		public class ConfigConsumer {
			@Value("${rate}")
			String rate;

		@Value("${lanecount}")
		String laneCount;

		@Value("${tollstart}")
		String tollStart;

		@RequestMapping("/rate")
		public String getRate(Model m){
			m.addAttribute("rate", rate);
			m.addAttribute("laneCount", laneCount);
			m.addAttribute("tollStart", tollStart);

			return "rateview";
		}
		}

* Create rateview.html in template folder with following code

		<!DOCTYPE HTML>
		<html xmlns:th="http://www.thymeleaf.org">
		<head>
		    <title>Config Client</title>
		    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		    <!-- Latest compiled and minified CSS -->
			<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"></link>	
		</head>
		<body>
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<h1>Spring Cloud Config Client</h1>
			    <p th:text="'Your rate is: ' + ${rate} + ', number of lanes is ' + ${laneCount} + ', toll start time is ' + ${tollStart}" />
			</div>
		    <div class="col-md-2"></div>
		</div>    
		</body>
		</html>

* Start server service **02.Microservices-Git-ConfigServer**

* Start client service 

* Access the service 
	
	http://localhost:8888/rate
	
Observe that values are pulled from **config-server** 
