package com.example.controller.person;

import com.example.model.person.Person;
import com.example.service.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping("/doctors")
    public List<Person> getPersons(){
        return service.getPersons();
    }


}
