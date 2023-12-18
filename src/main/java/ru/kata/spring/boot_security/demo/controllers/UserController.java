package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.NoUserException;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;


@RestController
@CrossOrigin
@RequestMapping({"/api/user"})
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping()
    public User showUserAcc(Principal principal, Model model) {
        User user = userService.findByEmail(principal.getName());
        if (user == null) {
            throw new NoUserException("User not found");
        }
        return user;
    }


}

