package com.ndgndg91.todo.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormController {

    @GetMapping("/login/form")
    public String loginForm(){
        return "login";
    }

    @GetMapping("/join/form")
    public String joinForm() { return "join";}
}
