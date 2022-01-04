package com.example.controller.person;

import com.example.model.person.Person;
import com.example.service.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    private PersonService service;

    @PostMapping("/addPerson")
    public String savePerson(@RequestBody Person person) {
        return service.savePerson(person);
    }


    @GetMapping("/findAllPersons")
    public List<Person> getPersons(){
        return service.getPersons();
    }

    @GetMapping("/findAllPersons/{id}")
    public Optional<Person> getPerson(@PathVariable int id){
        return service.getPerson(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deletePerson(@PathVariable int id) {
        return service.deletePerson(id);
    }
}
