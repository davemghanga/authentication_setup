package com.bmt.myUsers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bmt.myUsers.model.AppUser;
import com.bmt.myUsers.repositories.AppUserRepository;

@Service
public class AppUserService implements UserDetailsService {

    @Autowired
    private AppUserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email"));

        var springUser = User.withUsername(appUser.getEmail())
                .password(appUser.getPassword())
                .roles(appUser.getRole()).build();
        return springUser;

    }

}
