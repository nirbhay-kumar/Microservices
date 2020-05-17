* Create a Project with Task Dependency

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-task</artifactId>
		</dependency>


* Add following code in Main class

		@SpringBootApplication
		@EnableTask
		public class Application {

		public static void main(String[] args) {
			SpringApplication.run(Application.class, args);
		}

		@Bean
		public TollProcessorTask tollProcessingTask(){
			return new TollProcessorTask();
		} 
		public class TollProcessorTask implements CommandLineRunner{

			@Override
			public void run(String... strings) throws Exception {

				for(String s: strings)
				System.out.println(s);
			}

		} 
		}

* Pass Command Line Arguments, they will be accessed by the task.
