package com.scm.scm20.controller;

import com.scm.scm20.model.Contacts;
import com.scm.scm20.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contact/contact-details")
public class ContactRestController {
    @Autowired
    private ContactService contactService;
    @GetMapping("/{contactId}")
    public Contacts getContactByContactId(@PathVariable String contactId){
        return contactService.getById(contactId);
    }
}
