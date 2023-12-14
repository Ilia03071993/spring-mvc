package com.selivanov.springweb.controller;

import com.selivanov.springweb.model.People;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    private People people;
    @GetMapping("/hello")
    public String getHelloMessage(@RequestParam(required = false) String username,
                                  @RequestParam(required = false) Integer password) {
        return "hello";
    }

    @GetMapping("/model")
    public String getModel(@RequestParam(required = false) String username, Model model) {
        model.addAttribute("user", username);
        return "welcome";
    }

    @GetMapping("/form")
    public String getForm(Model model) {
        model.addAttribute("people", new People());
        return "form";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("people", people);
        return "register";
    }
    @PostMapping("/register")
    public String register(@ModelAttribute People people,
                           Model model) {
       // model.addAttribute("people", people);
        this.people = people;
        return "redirect:/register";
    }

}