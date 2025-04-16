package com.scm.scm20.service.impl;

import com.scm.scm20.customexception.UserNotFoundException;
import com.scm.scm20.helper.Helper;
import com.scm.scm20.model.User;
import com.scm.scm20.repositories.UserRepo;
import com.scm.scm20.service.EmailService;
import com.scm.scm20.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return this
                .userRepo
                .findByEmailAndPassword(email, password)
                .orElseThrow(
                        () -> new UserNotFoundException("User with email " + email + " and password " + password + " not found"));
    }

    private String PHOTO_URL = "https://img.icons8.com/?size=100&id=21441&format=png&color=000000";

    @Override
    public User saveUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        user.setProfilePicture(PHOTO_URL);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(List.of("ROLE_USER"));
        System.out.println("DB--->" + user);

        // generating email token
        String emailToken = UUID.randomUUID().toString();

        // saving user and email token into db
        user.setEmailToken(emailToken);
        User savedUser = userRepo.save(user);

        // sending mail to user for verification
        String emailLink = Helper.getLinkForEmailVerification(emailToken);
        emailService.sendEmail(savedUser.getEmail(), "Verify Email: Smart Contact Manager", emailLink);
        return savedUser;
    }

    @Override
    public Optional<User> getUserById(String id) {
        return this.userRepo.findById(id);
    }

    @Override
    public User updateUser(User user) {
        User dbUser = this.userRepo.findById(user.getUserId()).orElseThrow(
                () -> new UserNotFoundException("User with id " + user.getUserId() + " not found"));

        user.setUserId(dbUser.getUserId());
        return this.userRepo.save(user);
    }

    @Override
    public void deleteUser(String id) {
        User dbUser = this.userRepo.findById(id).orElseThrow(
                () -> new UserNotFoundException("User with id " + id + " not found"));
        this.userRepo.delete(dbUser);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return this.userRepo.findByEmail(email);
    }

    @Override
    public List<User> getAllUser() {
        return this.userRepo.findAll();
    }



    @Override
    public User findByEmailToken(String emailToken) {
        return this.userRepo.findByEmailToken(emailToken);
    }


}
