package com.example.service.person;

import com.example.dao.person.PersonRepository;
import com.example.mailservice.EmailService;
import com.example.model.person.Person;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;
    @Autowired
    private EmailService service;

    @PostConstruct
    public void initDoctor(){
        List<String> commentList = List.of("sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa");
        repository.saveAll(Stream.of
                        (new Person(101,"John","Cardiac",commentList),
                                new Person(102,"peter","eye",commentList))
                .collect(Collectors.toList()));
    }

    public List<Person> getPersons(){
        service.sendEMail();
        return repository.findAll();
    }

    public String savePerson(Person person) {
        repository.save(person);
        return "Added person with id : " + person.getId();
    }

    public Optional<Person> getPerson(int id) {
        return repository.findById(id);
    }

    public String deletePerson(int id) {
        repository.deleteById(id);
        return "person deleted with id :" + id;
    }

    public org.springframework.http.ResponseEntity<Person> updatePerson(int id, Person person) {
        Optional<Person> personData = repository.findById(id);

        if (personData.isPresent()) {
            Person _person = personData.get();
            _person.setName(person.getName());
            _person.setSpecialist(person.getSpecialist());
            return new ResponseEntity<>(repository.save(_person), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
