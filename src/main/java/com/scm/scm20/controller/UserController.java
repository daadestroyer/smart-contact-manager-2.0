package com.scm.scm20.controller;

import com.scm.scm20.helper.Helper;
import com.scm.scm20.model.User;
import com.scm.scm20.service.UserService;
import com.scm.scm20.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @RequestMapping(value = "/home")
    public String userDashboardHome(Model model, Authentication authentication){
        return "user/home";
    }
    @RequestMapping(value = "/profile")
    public String profile(Model model, Authentication authentication) {
        return "user/profile";
    }
    // user add contacts page

    // user edit contact
    // user delete contact
}
