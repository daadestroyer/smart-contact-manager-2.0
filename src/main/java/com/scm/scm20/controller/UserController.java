package com.scm.scm20.controller;

import com.scm.scm20.helper.Helper;
import com.scm.scm20.service.UserService;
import com.scm.scm20.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {


    // user dashboard page
//    @RequestMapping(value = "/dashboard")
//    public String userDashboard() {
//        System.out.println("User dashboard");
//        return "user/dashboard";
//    }
    @RequestMapping(value = "/home")
    public String userDashboardHome(Authentication authentication){
        // System.out.println("PRINCIPAL DATA === "+principal.getName());
        String emailOfLoggedInUser = Helper.getEmailOfLoggedInUser(authentication);
        System.out.println("emailOfLoggedInUser = "+emailOfLoggedInUser);

        return "user/home";
    }
    @RequestMapping(value = "/profile")
    public String profile() {
        return "user/profile";
    }

    @RequestMapping(value = "/add-contacts")
    public String addContacts() {
        return "user/addcontacts";
    }

    @RequestMapping(value = "/viewcontacts")
    public String viewContacts() {
        return "user/viewcontacts";
    }




    // user add contacts page
    // user view contacts
    // user edit contact
    // user delete contact
}
