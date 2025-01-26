package com.scm.scm20.controller;

import com.scm.scm20.dto.AddContactFormDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user/contact")
public class ContactController {
    @RequestMapping(value = "/add-contacts")
    public String addContacts(Model model) {
        AddContactFormDto addContactFormDto = new AddContactFormDto();
//        addContactFormDto.setName("shubham nigam");
//        addContactFormDto.setEmail("shubham@gmail.com");
//        addContactFormDto.setPhone("3423434");
//        addContactFormDto.setAbout("dfdfdfdf");
//        addContactFormDto.setFacebookLink("dfdf");
//        addContactFormDto.setWebsiteLink("dfdf");
//        addContactFormDto.setFavourite(true);
        model.addAttribute("addContactFormDto",addContactFormDto);
        return "user/addcontacts";
    }

    @RequestMapping(value = "/process-add-contacts", method = RequestMethod.POST)
    public String processAddContact(@ModelAttribute("addContactFormDto") AddContactFormDto addContactFormDto){
        return "user/addcontacts";
    }
    // user view contacts
    @RequestMapping(value = "/viewcontacts")
    public String viewContacts() {
        return "user/viewcontacts";
    }

}
