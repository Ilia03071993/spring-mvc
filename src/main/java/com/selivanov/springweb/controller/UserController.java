package com.selivanov.springweb.controller;

import com.selivanov.springweb.component.DataCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    private final DataCreationService dataCreationService;
    @Autowired
    public UserController(DataCreationService dataCreationService) {
        this.dataCreationService = dataCreationService;
    }

    @ResponseBody
    @GetMapping("/user")
    public String getUserController() {
        return dataCreationService.createUser().toString();
    }

    @ResponseBody
    @GetMapping("/message")
    public String getMessageController() {
        return dataCreationService.createMessage().toString();
    }
}