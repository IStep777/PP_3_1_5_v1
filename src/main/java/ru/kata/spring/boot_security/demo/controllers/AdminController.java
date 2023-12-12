package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.List;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.model.User;

@Controller
@RequestMapping({"/admin"})
public class AdminController {
    private final RoleService roleService;
    private final UserService userService;
    private static final String REDIRECT = "redirect:/admin";

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String showAll(@ModelAttribute("user") User user, Principal principal, Model model) {
        User authenticatedUser = this.userService.findByEmail(principal.getName());
        model.addAttribute("authenticatedUser", authenticatedUser);
        model.addAttribute("rolesAuthenticatedUser", authenticatedUser.getRoles());
        model.addAttribute("users", this.userService.getAllUsers());
        List<Role> roles = this.roleService.getAllRoles();
        model.addAttribute("allRoles", roles);
        return "/admin-page";
    }

    @PostMapping({""})
    public String saveUser(@ModelAttribute("user") User user) {
        this.userService.saveUser(user);
        return "redirect:/admin";
    }

    @PatchMapping({"/{id}"})
    public String update(@ModelAttribute("user") User user) {
        this.userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping({"/{id}"})
    public String delete(@PathVariable("id") Long id) {
        this.userService.deleteUser(id);
        return "redirect:/admin";
    }
}
