package com.bmt.myUsers.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bmt.myUsers.model.AppUser;
import com.bmt.myUsers.model.RegisterDto;
import com.bmt.myUsers.repositories.AppUserRepository;

@Service
public class AccountService {

    @Autowired
    private AppUserRepository userRepo;

    public void register(RegisterDto registerDto) {
        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        if (userRepo.findByEmail(registerDto.getEmail()).isPresent()) {
            throw new IllegalStateException("Email Address is already in use");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        AppUser newUser = new AppUser();
        newUser.setFirstName(registerDto.getFirstName());
        newUser.setLastName(registerDto.getLastName());
        newUser.setEmail(registerDto.getEmail());
        newUser.setPhone(registerDto.getPhone());
        newUser.setAddress(registerDto.getAddress());
        newUser.setRole("client");
        newUser.setCreatedAt(new Date());
        newUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        userRepo.save(newUser);
    }

}
