package com.scm.scm20.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
    // user dashboard page
    @RequestMapping(value = "/dashboard",method = RequestMethod.GET)
    public String userDashboard(){
        return "user/dashboard";
    }
    // user add contacts page
    // user view contacts
    // user edit contact
    // user delete contact
}
