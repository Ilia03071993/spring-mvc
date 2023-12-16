package com.selivanov.springweb.controller;

import com.selivanov.springweb.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Field;
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
//        return "param_page";
//    }

    //2.
    @GetMapping("/get-form")
    public String getPersonRequest(Model model) {
        model.addAttribute("person", new Person());
        return "get_form";
    }

    @GetMapping("/get-person")
    public String getPerson(@ModelAttribute Person person
            , Model model) {
        model.addAttribute("person", person);
        return "get_person";
    }

    //3.
    @GetMapping("/post-form")
    public String getPersonForm(Model model) {
        model.addAttribute("person", new Person());
        return "post_form";
    }

    @GetMapping("/person")
    public String showPerson(@RequestParam(required = false) Integer limit,
                             @RequestParam(required = false) String sortby,
                             @RequestParam(required = false) String sortorder,
                             Model model) {
        if (limit != null && sortby != null && sortorder != null) {
            List<Person> sortedList = personList.stream().sorted((p1, p2) -> {
                        if (sortorder.equalsIgnoreCase("asc")) {
                            if (sortby.equals("age")) {
                                return Integer.compare(p1.getAge(), p2.getAge());
                            } else {
                                return p1.getName().compareTo(p2.getName());
                            }
                        } else {
                            if (sortby.equals("age")) {
                                return Integer.compare(p2.getAge(), p1.getAge());
                            } else {
                                return p2.getName().compareTo(p1.getName());
                            }
                        }
                    })
                    .limit(limit)
                    .collect(Collectors.toList());

            model.addAttribute("persons", sortedList);
        } else {
            model.addAttribute("persons", personList);
        }
        return "person";
    }

    @PostMapping("/person")
    public String register(@ModelAttribute Person person) {
        personList.add(person);
        return "redirect:/person";
    }

    @GetMapping("/delete-person")
    public String deletePerson() {
        return "delete_person";
    }

    @PostMapping("/delete-person")
    public String deletePerson(@RequestParam String filterField,
                               @RequestParam String filterValue) {
        try {
            Field field = Person.class.getDeclaredField(filterField);

            for (int i = 0; i < personList.size(); i++) {
                Person person = personList.get(i);
                field.setAccessible(true);
                String fieldValue = (String) field.get(person);//name & gender only
                if (filterValue.equals(fieldValue)) {
                    personList.remove(person);
                    i--;
                }
            }
        } catch (Exception exception) {
            return "redirect:/person";
        }
        return "redirect:/person";
    }

    @GetMapping("/")
    public String homePage() {
        return "home";
    }
}
