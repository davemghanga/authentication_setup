package com.bmt.myUsers.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bmt.myUsers.model.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByEmail(String email);
}
