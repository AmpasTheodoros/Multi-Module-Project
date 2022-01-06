package com.example.controller.person;


import com.example.dao.person.PersonRepository;
import com.example.model.person.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.SessionCookieConfig;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class CommentsController {

    private final PersonRepository repository;

    CommentsController(PersonRepository repository) {
        this.repository = repository;
    }

//    @PostMapping("/comment")
//    public Object searchCommentSubmit(
//            @ModelAttribute findByComment searchModel) {
//        return "redirect:/comments?searchByComment=" + searchModel.getComment();
//    }
//
//
//
//    @GetMapping("/comment")
//    public String addComment(Model model) {
//        model.findByComment("comment", new Person());
//        return "comment";
//    }
//
//    @PostMapping("/comment")
//    public String addComment(Person comment, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "comment";
//        }
//
//        repository.save(comment);
//        model.addAttribute("comment", );
//        return "redirect:/comment";
//    }
//
//    @GetMapping("/comment/{comment}")
//    public String updateComment(@PathVariable("comment") String comment, Model model) {
//        Person comment = repository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid car Id:" + comment));
//
//        model.addAttribute("comment", comment);
//        return "comment";
//    }
//
//    @PostMapping("/comment{id}")
//    public String updateCar(@PathVariable("id") String id,
//                            BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            SessionCookieConfig comment;
//            comment.setComment(comment);
//            return "comment";
//        }
//
//        repository.save(car);
//        return "redirect:/cars/electric";
//    }
//
//    @GetMapping("/comments/{comment}")
//    public String deleteCar(@PathVariable("comment") String comment, Model model) {
//        Person comment = repository.findById(comment)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid :" + comment));
//        repository.delete(comment);
//        return "redirect:/comment";
//    }
//    }

    @GetMapping("/updateComment/{id}")
    public String updateComments(@PathVariable("id") String id, Model model) {
        Person newperson = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No person Id:" + id));

        model.addAttribute("newperson", newperson);
        return "comment";
    }


    @GetMapping("/comment")
    public String getPeople(Model model){
        List<Person> personList = repository.findAll();
        List<>
        model.addAttribute("comment",personList.);
        System.out.println(personList);
        return "people";
    }

    @PostMapping("/comments/update/{id}")
    public Object updateComment(@PathVariable("id") long id, @Validated @ModelAttribute("newcomment")Comment newcomment,
                                BindingResult result, Model model) {
        if (result.hasErrors()) {
            newcomment.setId(id);
            return "comment";
        }

        repository.save(newcomment);
        return "redirect:/comment";
    }

    @GetMapping("/comments/delete/{id}")
    public String deleteComment(@PathVariable("id") long id, Model model) {
        Comment oldcomment = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid person Id:" + id));
        repository.delete(oldcomment);
        return "redirect:/comments";
    }


}
