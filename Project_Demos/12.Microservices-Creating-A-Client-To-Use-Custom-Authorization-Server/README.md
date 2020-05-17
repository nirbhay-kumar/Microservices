* Create a new project with following dependencies

		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-oauth2</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-security</artifactId>
		</dependency>

* Open Application.java file and add following code


		@SpringBootApplication
		public class Application implements CommandLineRunner{
		public static void main(String[] args) {
			SpringApplication.run(Application.class, args);
		}
		@Override
		public void run(String... args) throws Exception {
			System.out.println("Job started");

		ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
		resourceDetails.setClientAuthenticationScheme(AuthenticationScheme.header);
		resourceDetails.setAccessTokenUri("http://localhost:9000/oauth/token");
		
		//must be a valid scope or get an error; if empty, get all scopes (default); better to ask for one
		resourceDetails.setScope(Arrays.asList("person_read"));
		
		//must be valid client id or get an error
		resourceDetails.setClientId("rosemont");
		resourceDetails.setClientSecret("rosemont");
		
		//diff user results in diff authorities/roles coming out; preauth on roles fails for adam, works for mosalah
		resourceDetails.setUsername("mosalah");
		resourceDetails.setPassword("pass1");
		
		OAuth2RestTemplate template = new OAuth2RestTemplate(resourceDetails);
		//could also get scopes: template.getAccessToken().getScope()
		String token =  template.getAccessToken().toString();//.getValue();
		
		System.out.println("Token: " + token);
		
		String s = template.getForObject("http://localhost:9090/data", String.class);
		
		System.out.println("Result: " + s);
	}
	}


