package com.digitalinnovation.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.digitalinnovation.person.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
