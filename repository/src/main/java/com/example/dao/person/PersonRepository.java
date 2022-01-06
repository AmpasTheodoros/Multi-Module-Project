package com.example.dao.person;

import com.example.model.person.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.xml.stream.events.Comment;
import java.util.List;

//@Repository
public interface PersonRepository extends MongoRepository<Person,String> {

    List<Person> findByComment(String comment);
    List<Person> findByFirstName(String firstName);
    List<Person> findByLastName(String lastName);
    List<Person> findByCommentStartingWith(String comment);
    List<Person> findByMakerStartingWith(String maker);

}