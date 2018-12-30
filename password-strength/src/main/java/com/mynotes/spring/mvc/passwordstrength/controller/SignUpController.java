package com.mynotes.spring.mvc.passwordstrength.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mynotes.spring.mvc.passwordstrength.dto.UserDto;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    @ModelAttribute("user")
    public UserDto userDto() {
        return new UserDto();
    }

    @GetMapping
    public String showRegistrationForm(UserDto user) {
        return "signup";
    }

    @PostMapping
    public String addUser(@Valid @ModelAttribute("user") UserDto user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        return "success";
    }

}
