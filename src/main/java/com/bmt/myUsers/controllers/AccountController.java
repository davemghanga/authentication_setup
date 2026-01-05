package com.bmt.myUsers.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bmt.myUsers.model.AppUser;
import com.bmt.myUsers.model.RegisterDto;
import com.bmt.myUsers.repositories.AppUserRepository;

import jakarta.validation.Valid;

@Controller
public class AccountController {

    @Autowired
    private AppUserRepository userRepo;

    @GetMapping("/register")
    public String register(Model model) {
        RegisterDto registerDto = new RegisterDto();
        model.addAttribute("registerDto", registerDto);
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute RegisterDto registerDto, BindingResult result) {

        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            result.addError(
                    new FieldError("registerDto", "confirmPassword", "Password and Confirm Password do not match"));
        }

        Optional<AppUser> appUser = userRepo.findByEmail(registerDto.getEmail());
        if(appUser != null){
            result.addError(new FieldError("registerDto","email","Email Address is used"));
        }

        if(result.hasErrors()){
            return "register";
        }

        return "register";

    }

}