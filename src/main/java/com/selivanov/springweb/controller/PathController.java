package com.selivanov.springweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PathController {
    private String[] arr = {"0", "1", "2"};

    @ResponseBody
    @GetMapping("/get-value/{index}")
    public String getValue(@PathVariable Integer index) {
        return arr[index];
    }
}