package com.bmt.myUsers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.bmt.myUsers.model.RegisterDto;
import com.bmt.myUsers.repositories.AppUserRepository;
import com.bmt.myUsers.services.AccountService;

import jakarta.validation.Valid;

@Controller
public class AccountController {

    @Autowired
    private AppUserRepository userRepo;

    @Autowired
    private AccountService accountService;

    @GetMapping("/register")
    public String register(Model model) {
        RegisterDto registerDto = new RegisterDto();
        model.addAttribute("registerDto", registerDto);
        model.addAttribute("success", false);
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute RegisterDto registerDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }

        try {
            accountService.register(registerDto);
            return "redirect:/login";
        } catch (IllegalArgumentException ex) {
            result.addError(new FieldError("RegisterDto", "confirmPassword", ex.getMessage()));
            return "register";
        }
        catch(IllegalStateException ex){
            result.addError(new FieldError("RegisterDto","email",ex.getMessage()));
            return "register";
        }

    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

}