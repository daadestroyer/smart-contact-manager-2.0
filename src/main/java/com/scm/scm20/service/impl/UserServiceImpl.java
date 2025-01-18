package com.scm.scm20.service.impl;

import com.scm.scm20.customexception.UserNotFoundException;
import com.scm.scm20.model.User;
import com.scm.scm20.repositories.UserRepo;
import com.scm.scm20.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    private String PHOTO_URL = "https://fr.wikipedia.org/wiki/Fichier:User_icon-cp.png";
    @Override
    public User saveUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        user.setProfilePicture(PHOTO_URL);
        System.out.println("DB--->"+user);
        return this.userRepo.save(user);
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
    public List<User> getAllUser() {
        return this.userRepo.findAll();
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return this
                .userRepo
                .findByEmailAndPassword(email, password)
                .orElseThrow(
                        () -> new UserNotFoundException("User with email " + email + " and password " + password + " not found"));
    }

}
