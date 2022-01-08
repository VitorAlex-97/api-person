package com.digitalinnovation.person.service;

import java.util.List;
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
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, 
					"Person is alredy resgiter");
		}
		return Optional.of(personRepository.save(person));
	}
	
	public List<Person> getAll() {
		return personRepository.findAll();
	}
	
	public Optional<Person> getById(Long id){
		personRepository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(
				HttpStatus.NOT_FOUND,
				"Person with id value " + id + " do not exist"));
		
		return personRepository.findById(id);
	}

	public void deletePerson(Long id) {
		Optional<Person> personDeleted = personRepository.findById(id);
		
		personDeleted.orElseThrow(() -> new ResponseStatusException(
				HttpStatus.BAD_REQUEST,
				"Person could not be deleted, because does not exist"));
		
		personDeleted.ifPresent((resp) -> personRepository.deleteById(resp.getId()));
	}
	
	public Optional<Person> update(Person personUpdate){
		 Optional<Person> personToUpdate = personRepository.findById(personUpdate.getId());
		 
		 personToUpdate.orElseThrow(() -> new ResponseStatusException(
				HttpStatus.NOT_FOUND,
				"Person do not exist"));
		
		return Optional.of(personRepository.save(personUpdate));
		 
	}
	
}
