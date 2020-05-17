package com.example.demo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.beans.Person;

@RestController
public class PersonController {

	@RequestMapping("/person/{id}")
	public Person getPerson(@PathVariable int id){
		
		switch(id){
		case 1: return new Person(1, "Justin", "Bieber");
		case 2: return new Person(2, "Taylor", "Swift");
		case 3: return new Person(3, "Ed", "Sheeran");
		}
		return null;
	}
}
