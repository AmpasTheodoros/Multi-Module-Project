package com.example.controller.person;

import com.example.model.person.Person;
import com.example.service.person.PersonSearch;
import com.example.service.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class PersonController {

    @Autowired
    private PersonService service;

    @Autowired
    private PersonSearch search;


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
    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable("id") String id, Model model) {
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

    @GetMapping("/peopleFiltered")
    public String getPeopleFiltered(Model model ,@RequestParam (defaultValue = "") String searchByComment){
        List<Person> personList = service.getPersons();
//        ArrayList<String> comments = new ArrayList<>();
//        for (int i=0;i<personList.size();i++){
//            comments.add(personList.get(i).getComment());
//        }
        model.addAttribute("people",personList);
//        model.addAttribute("comments",comments);
        System.out.println(personList);
        return "peopleFiltered";
    }

//    @PostMapping("/peopleFiltered")
//    public Object searchCommentSubmit(
//            @ModelAttribute PersonSearch searchModel) {
//        return "redirect:/peopleFiltered?searchByComment=" + searchModel.getComment();
//    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//    private final PersonRepository repository;
//
//    PersonController(PersonRepository repository) {
//        this.repository = repository;
//    }
//
//    @PostMapping("/peopleFiltered")
//    public String searchCarsSubmit(
//            @ModelAttribute PersonSearch searchModel) {
//        return "redirect:/peopleFiltered?searchByMaker=" + searchModel.getMaker();
//    }
//
//    @GetMapping("/peopleFiltered")
//    public String showCars(
//            Model model,
//            @RequestParam(defaultValue = "1") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(defaultValue = "") String searchByMaker
//    ) {
//        if (page < 1) {
//            return "redirect:/peopleFiltered?page=1&size="+ size;
//        };
//
//        System.out.println("repository searchByMaker = " + repository.findByMakerStartingWith(searchByMaker)) ;
//        Page<Person> persons = findPaginated(
//                !searchByMaker.equals("") ?
//                        repository.findByMakerStartingWith(searchByMaker) :
//                        repository.findAll(),
//                PageRequest.of(page - 1, size)
//        );
//        System.out.println(persons);
//
//        int totalPages = persons.getTotalPages();
//
//        System.out.println("totalPages = " + totalPages);
//
//        if (totalPages > 0 && page > totalPages) {
//            return "redirect:/peopleFiltered?size="+ size + "&page=" + totalPages;
//        };
//
//        if (totalPages > 0) {
//            List<Integer> pageNumbers = IntStream.rangeClosed(Math.max(1, page-2), Math.min(page + 2, totalPages))
//                    .boxed()
//                    .collect(Collectors.toList());
//            model.addAttribute("pageNumbers", pageNumbers);
//        }
//
//        model.addAttribute("page", page);
//        model.addAttribute("people", persons);
//        model.addAttribute("searchModel", new PersonSearch(searchByMaker));
//        System.out.println("model = " + model);
//        return "peopleFiltered";
//    }
//
//    private Page<Person> findPaginated(List<Person> personList, Pageable pageable) {
//        int pageSize = pageable.getPageSize();
//        int currentPage = pageable.getPageNumber();
//        int startItem = currentPage * pageSize;
//
//        List<Person> result;
//
//        if (personList.size() < startItem) {
//            result = Collections.emptyList();
//        } else {
//            int toIndex = Math.min(startItem + pageSize, personList.size());
//            result = personList.subList(startItem, toIndex);
//        }
//
//        Page<Person> peoplePage = new PageImpl<Person>(result, PageRequest.of(currentPage, pageSize), personList.size());
//
//        return peoplePage;
//    }

}
