package com.example.service.person;

import com.example.dao.person.PersonRepository;
import com.example.mailservice.EmailService;
import com.example.model.person.Person;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

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
//        List<String> commentList = List.of("sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa","sas", "sasasa");
        repository.saveAll(Stream.of
                        (new Person("John","Cardiac","sas"),
                                new Person("peter","eye","mam"))
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

    public Optional<Person> getPerson(String id) {
        return repository.findById(id);
    }

    public String deletePerson(String id) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id));
        repository.delete(person);
        return "redirect:/peopleFiltered";
//        repository.deleteById(id);
//        return "person deleted with id :" + id;
    }

    public String updatePerson(String id, Model model) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id));

        model.addAttribute("car", person);
        return "update-person";
    }

    public String updatePerson(String id, Person person, BindingResult result, Model model) {
        if (result.hasErrors()) {
            person.setId(id);
            return "update-person";
        }

        repository.save(person);
        return "redirect:/peopleFiltered";
    }

//    public org.springframework.http.ResponseEntity<Person> updatePerson(String id, Person person) {
//        Optional<Person> personData = repository.findById(id);
//
//        if (personData.isPresent()) {
//            Person _person = personData.get();
//            _person.setFirstName(person.getFirstName());
//            _person.setLastName(person.getLastName());
//            return new ResponseEntity<>(repository.save(_person), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
}
