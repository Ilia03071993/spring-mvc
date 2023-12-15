package com.selivanov.springweb.controller;

import com.selivanov.springweb.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PersonController {
    private List<Person> personList = new ArrayList<>();

    //1.
//    @GetMapping("/param")
//    public String getPersonRequest(@RequestParam String name,
//                                   @RequestParam String gender,
//                                   @RequestParam Integer age,
//                                   Model model) {
//        Person person1 = new Person(name, gender, age);
//        model.addAttribute("name", person1.getName());
//        model.addAttribute("gender", person1.getGender());
//        model.addAttribute("age", person1.getAge());
//
//        return "param-page";
//    }

    //2.
        @GetMapping("/get-form")
    public String getPersonRequest(Model model) {
        model.addAttribute("person", new Person());
        return "get-form";
    }

    @GetMapping("/get-person")
    public String getPerson(@ModelAttribute Person person
            ,Model model) {
        model.addAttribute("person", person);
        return "getPerson";
    }
    //3.
    @GetMapping("/post-form")
    public String getPersonForm(Model model) {
        model.addAttribute("person", new Person());
        return "post-form";
    }

    @GetMapping("/person")
    public String showPerson(@RequestParam(defaultValue = "5") Integer limit,
                             @RequestParam(defaultValue = "age") String sortBy,
                             @RequestParam(defaultValue = "asc") String sortOrder,
                             @RequestParam(required = false) String filterField,
                             @RequestParam(required = false) String filterValue,
                             Model model) {
        List<Person> sortedList = personList.stream().sorted((p1, p2) -> {
                    if (sortOrder.equalsIgnoreCase("asc")) {
                        if (sortBy.equals("age")) {
                            return Integer.compare(p1.getAge(), p2.getAge());
                        } else {
                            return p1.getName().compareTo(p2.getName());
                        }
                    } else {
                        if (sortBy.equals("age")) {
                            return Integer.compare(p2.getAge(), p1.getAge());
                        } else {
                            return p2.getName().compareTo(p1.getName());
                        }
                    }
                })
                .limit(limit)
                .collect(Collectors.toList());

//        if (filterField.equalsIgnoreCase("gender") && filterValue.equalsIgnoreCase("male")){
//            sortedList.removeIf(person -> person.getGender().equalsIgnoreCase("male"));
//        } else if (filterField.equalsIgnoreCase("gender") && filterValue.equalsIgnoreCase("female")){
//            sortedList.removeIf(person -> person.getGender().equalsIgnoreCase("female"));}

        model.addAttribute("persons", sortedList);
        return "person";
    }

    @PostMapping("/person")
    public String register(@ModelAttribute Person person) {
        personList.add(person);
        return "redirect:/person";
    }

    @GetMapping("/delete-person")
    public String deletePerson(Model model) {
        model.addAttribute("filterField", "");
        model.addAttribute("filterValue", "");
        return "deletePerson";
    }

    @GetMapping("/")
    public String homePage(){
        return "home";
    }
}
