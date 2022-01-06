package com.digitalinnovation.person.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.digitalinnovation.person.entities.Person;
import com.digitalinnovation.person.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	public Optional<Person> registerPerson(Person person) {
		
		if(personRepository.findByCpf(person.getCpf()).isPresent()){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já cadastrado");
		}
		
		return Optional.of(personRepository.save(person));
	}
	
}