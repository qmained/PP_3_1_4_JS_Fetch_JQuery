package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;


@Controller
@RequestMapping
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("admin")
    public String showIndexPage(Model model) {
        model.addAttribute("users", userService.getListUsers());
        return "users/admin";
    }

    @GetMapping("admin/{id}")
    public String showUser(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "users/show";
    }

    @GetMapping("admin/new")
    public String showUserCreationPage(Model model) {
        model.addAttribute("user", new User());

        return "users/new";
    }

    @PostMapping("/admin")
    public String createNewUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("admin/{id}/edit")
    public String showUserEditionPage(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUser(id));
        return "users/edit";
    }

    @PatchMapping("admin/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin";
    }

    @DeleteMapping("admin/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.removeById(id);
        return "redirect:/admin";
    }

}
