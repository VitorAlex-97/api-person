package com.digitalinnovation.person.controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalinnovation.person.entities.Person;
import com.digitalinnovation.person.repository.PersonRepository;
import com.digitalinnovation.person.service.PersonService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/people")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@GetMapping
	public ResponseEntity<List<Person>> getAllPerson(){
		return ResponseEntity.ok(personService.getAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Person>> getByCpf(@PathVariable Long id){
		return ResponseEntity.ok(personService.getById(id));
	}
	
	@PostMapping
	public ResponseEntity<Optional<Person>> createPerson(@Valid @RequestBody Person person) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(personService.registerPerson(person));
	}
}
