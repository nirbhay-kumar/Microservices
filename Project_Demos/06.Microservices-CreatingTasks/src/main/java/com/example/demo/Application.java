package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.cloud.task.listener.TaskExecutionListener;
import org.springframework.cloud.task.repository.TaskExecution;
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
	public class TollProcessorTask implements CommandLineRunner,TaskExecutionListener{

		@Override
		public void run(String... strings) throws Exception {
			
			for(String s: strings)
			System.out.println(s);
		}

		@Override
		public void onTaskEnd(TaskExecution taskExecution) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTaskFailed(TaskExecution taskExecution, Throwable throwable) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTaskStartup(TaskExecution taskExecution) {
			// TODO Auto-generated method stub
			
		}
		
	} 
}


/*

TaskExecutionListener
listener methods to call before task,after task, in case of task fail ..respective method*/


/*
The goal of Spring Cloud Task is to provide the functionality of 
creating short-lived microservices for Spring Boot application.

*/