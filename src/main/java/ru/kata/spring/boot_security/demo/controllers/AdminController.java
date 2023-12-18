package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.NoUserException;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping({"/api/admin"})
public class AdminController {
    private final RoleService roleService;
    private final UserService userService;
    private static final String REDIRECT = "redirect:/admin";

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public List<User> showAll() {//@ModelAttribute("user") User user, Principal principal, Model model) {
//        User authenticatedUser = this.userService.findByEmail(principal.getName());
//        model.addAttribute("authenticatedUser", authenticatedUser);
//        model.addAttribute("rolesAuthenticatedUser", authenticatedUser.getRoles());
//        model.addAttribute("users", this.userService.getAllUsers());
//        List<Role> roles = this.roleService.getAllRoles();
//        model.addAttribute("allRoles", roles);
        List<User> users = this.userService.getAllUsers();
        return users;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        if (user == null) {
            throw new NoUserException("There is no user with ID = " + id + " in Database");
        }
        return user;
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        this.userService.saveUser(user);
        return user;
    }

    @PatchMapping({"/{id}"})
    public User update(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }

    @DeleteMapping({"/{id}"})
    public void delete(@PathVariable("id") Long id) {
        this.userService.deleteUser(id);
        //return "redirect:/admin";
    }
}
