package com.example.controller.person;

import com.example.model.person.Person;
import com.example.service.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class PersonController {

    @Autowired
    private PersonService service;

    //Den ksero an douleui
    @PostMapping("/addPerson")
    public String savePerson(@RequestBody Person person) {
        return service.savePerson(person);
    }


    @GetMapping("/findAllPersons")
    public List<Person> getPersons(){
        return service.getPersons();
    }

    @GetMapping("/findAllPersons/{id}")
    public Optional<Person> getPerson(@PathVariable String id){
        return service.getPerson(id);
    }

    //Den ksero an douleui
    @DeleteMapping("/delete/{id}")
    public String deletePerson(@PathVariable String id) {
        return service.deletePerson(id);
    }

    //Den ksero an douleui
    @PutMapping("/updatePerson/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable String id, @RequestBody Person person) {
        return service.updatePerson(id,person);
    }

    @GetMapping("/people")
    public String getPeople(Model model){
        List<Person> personList = service.getPersons();
        model.addAttribute("people",personList);
        System.out.println(personList);
        return "people";
    }
}
