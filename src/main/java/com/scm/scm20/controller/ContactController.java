package com.scm.scm20.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/contact")
public class ContactController {
    @RequestMapping(value = "/add-contacts")
    public String addContacts() {
        return "user/addcontacts";
    }

    // user view contacts
    @RequestMapping(value = "/viewcontacts")
    public String viewContacts() {
        return "user/viewcontacts";
    }

}
