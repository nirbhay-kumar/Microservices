package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;

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
