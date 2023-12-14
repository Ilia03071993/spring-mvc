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

@Controller
public class PersonController {
    private Person person;

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
//        @GetMapping("/get-form")
//    public String getPersonRequest(Model model) {
//        model.addAttribute("person", new Person());
//        return "get-form";
//    }
//
//    @GetMapping("/get-person")
//    public String getPerson(@ModelAttribute Person person
//            ,Model model) {
//        model.addAttribute("person", person);
//        return "getPerson";
//    }
    //3.
    @GetMapping("/form-person")
    public String getPersonForm(Model model) {
        model.addAttribute("person", new Person());
        return "formPerson";
    }
    @GetMapping("/person")
    public String createPerson(Model model) {
        model.addAttribute("persons", List.of(person));
        return "person";
    }

    @PostMapping("/person")
    public String register(@ModelAttribute Person person,
                           Model model) {
        this.person = person;
        return "redirect:/person";
    }
}
