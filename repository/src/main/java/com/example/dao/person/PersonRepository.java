package com.example.dao.person;

import com.example.model.person.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface PersonRepository extends MongoRepository<Person,Integer> {
}