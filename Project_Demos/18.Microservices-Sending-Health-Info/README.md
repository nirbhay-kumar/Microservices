* Create a copy of 16.Microservices-Registering-Service-With-Eureka
* Create a class CustomHealthCheck


		@Component
		public class CustomHealthCheck implements HealthIndicator {
		private int errorCode=0;
		@Override
		public Health health() {
			System.out.println("Health check performed. Error code is "+errorCode);
			if(errorCode>2 && errorCode<5){
				++errorCode;
				return Health.down().withDetail("Custom Error Code", errorCode).build();
			}
			errorCode++;
			return Health.up().build();
		}
		}

* Add following code to application.properties

		eureka.client.healthcheck.enabled=true

* Observe the changes in Eureka server localhost:8761
