* Create a Springboot project with Hystrix Dashboard dependency

* Annotate Main class with @EnableHystrixDashboard

* Open application.properties file and following code

		server.port=8085
		eureka.client.register-with-eureka=false

* Run the app **http://localhost:8085/hystrix**

* Type following URL in Hystrix dahsboard    
	*http://localhost:8082/actuator/hystrix.stream*
