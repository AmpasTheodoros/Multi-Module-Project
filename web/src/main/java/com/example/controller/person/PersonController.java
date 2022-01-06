package com.example.controller.person;

import com.example.model.person.Person;
import com.example.service.person.PersonSearch;
import com.example.service.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping("/")
    public String home(){
        return "redirect:/people";
    }

    @GetMapping("/addperson")
    public String savePerson(Model model) {
        model.addAttribute("people", new Person());
        return "addperson";
    }

    @PostMapping("/addperson")
    public String savePerson(Person person, BindingResult result, Model model) {
        return service.savePerson(person,result,model);
    }

    @GetMapping("/findAllPersons")
    public List<Person> getPersons(){
        return service.getPersons();
    }

    @GetMapping("/findAllPersons/{id}")
    public Optional<Person> getPerson(@PathVariable String id){
        return service.getPerson(id);
    }

    @GetMapping("/deletePerson/{id}")
    public String deletePerson(@PathVariable("id") String id, Model model) {
        return service.deletePerson(id);
    }

    @GetMapping("/deleteComment/{id}")
    public String deleteComment(@PathVariable("id") String id, Model model) {
        return service.deleteComment(id);
    }

    @GetMapping("/people/update/{id}")
    public String updatePerson(@PathVariable("id") String id, Model model) {
        return service.updatePerson(id,model);
    }

    @PostMapping("/people/update/{id}")
    public String updatePerson(@PathVariable("id") String id, Person person,
                            BindingResult result, Model model) {
        return service.updatePerson(id,person,result,model);
    }

    @GetMapping("/people")
    public String getPeople(Model model){
        List<Person> personList = service.getPersons();
        model.addAttribute("people",personList);
        System.out.println(personList);
        return "people";
    }

    @GetMapping("/peopleFiltered")
    public String getPeopleFiltered(Model model ,@RequestParam (defaultValue = "") String searchByComment){
        List<Person> personList = service.getPersons();
        model.addAttribute("people",personList);
        System.out.println(personList);
        return "peopleFiltered";
    }

}
