package com.mfgestion.controller;

import com.mfgestion.model.User;
import com.mfgestion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/index")
    public String getAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user/index";
    }

    @GetMapping("/form")
    public String showUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable("id") Long id, Model model) {
        Optional<User> userOptional = userService.getUserById(id);
        if (userOptional.isPresent()) {
            model.addAttribute("user", userOptional.get());
            return "user/form";
        } else {
            return "redirect:/users/index";
        }
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users/index";
    }
}
