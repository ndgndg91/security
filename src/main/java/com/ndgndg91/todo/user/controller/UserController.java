package com.ndgndg91.todo.user.controller;

import com.ndgndg91.todo.user.model.dto.request.CreateUserRequest;
import com.ndgndg91.todo.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public String createUser(
            final CreateUserRequest request
    )
    {
        request.validate();
        userService.createUser(request.getUsername(), request.getPassword());
        return "index";
    }

}
