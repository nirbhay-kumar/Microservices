package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.Person;

@RestController
public class DataController {

	@RequestMapping("/data")
	public List<Person> getData(){
		
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person("Mo", "Salaha"));
		persons.add(new Person("Donald","Trump"));
		return persons;
	}
	
}
