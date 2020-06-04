package com.ndgndg91.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginFormController {

    @GetMapping("/login/form")
    public String loginForm(){
        return "login";
    }
}
