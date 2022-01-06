package com.example.service.person;

import com.example.dao.person.PersonRepository;
//import com.example.mailservice.EmailService;
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

    @PostConstruct
    public void initPerson(){
        repository.saveAll(Stream.of
                        (new Person("John","Cardiac","Skg","email@gmail.com","sas"),
                                new Person("peter","eye","Skg","email@gmail.com","mam"))
                .collect(Collectors.toList()));
    }

    public List<Person> getPersons(){
        return repository.findAll();
    }

    public String savePerson(Person person, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addperson";
        }

        repository.save(person);
        model.addAttribute("people", person);
        return "redirect:/people";
    }

    public Optional<Person> getPerson(String id) {
        return repository.findById(id);
    }

    public String deletePerson(String id) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id));
        repository.delete(person);
        return "redirect:/people";
    }

    public String deleteComment(String id) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + id));
        repository.delete(person);
        return "redirect:/peopleFiltered";
    }

    public String updatePerson(String id, Model model) {
        Person person = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));

        model.addAttribute("people", person);
        return "update-person";
    }

    public String updatePerson(String id, Person person, BindingResult result, Model model) {
        if (result.hasErrors()) {
            person.setId(id);
            return "update-person";
        }

        repository.save(person);
        return "redirect:/people";
    }
}
