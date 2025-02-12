package com.scm.scm20.service;

import com.scm.scm20.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    Optional<User> getUserById(String id);

    User updateUser(User user);

    void deleteUser(String id);

    Optional<User> getUserByEmail(String email);
    List<User> getAllUser();
    User findByEmailAndPassword(String email,String password);

    User findByEmailToken(String emailToken);
}
