package com.scm.scm20.service.impl;

import com.scm.scm20.customexception.UserNotFoundException;
import com.scm.scm20.model.User;
import com.scm.scm20.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.userRepo.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User with email " + email + " not found"));
    }
}
