package com.scm.scm20.controller;

import com.scm.scm20.helper.Helper;
import com.scm.scm20.model.User;
import com.scm.scm20.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@ControllerAdvice // this controller advice meaning this controller will run before any other controller/request
public class RootController {
    // inside this class all the methods will execute before any request
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;

    @ModelAttribute("loggedInUser")
    public User addLoggedInUserToModel(Model model, Authentication authentication) {
        // This will be added to the model for every handler method
        if (authentication == null) {
            return null;
        }
        String emailOfLoggedInUser = Helper.getEmailOfLoggedInUser(authentication);
        Optional<User> optionalUser = userService.getUserByEmail(emailOfLoggedInUser);
        if (optionalUser.isPresent()) {
            logger.info("Details of loggedInUser = " + optionalUser.get());
            return optionalUser.get();
        } else {
            logger.info("Details of loggedInUser = " + optionalUser.get());
            model.addAttribute("loggedInUser", null);
            return optionalUser.get();
        }

    }
}
