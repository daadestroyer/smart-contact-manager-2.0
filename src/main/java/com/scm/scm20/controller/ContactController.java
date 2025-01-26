package com.scm.scm20.controller;

import com.scm.scm20.dto.AddContactFormDto;
import com.scm.scm20.model.Contacts;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user/contact")
public class ContactController {

    Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ModelMapper modelMapper;

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
        model.addAttribute("addContactFormDto", addContactFormDto);
        return "user/addcontacts";
    }

    @RequestMapping(value = "/process-add-contacts", method = RequestMethod.POST)
    public String processAddContact(@ModelAttribute("addContactFormDto") AddContactFormDto addContactFormDto) {
        logger.info("addContactFormDto Data---" + addContactFormDto);

        Contacts contacts = this.modelMapper.map(addContactFormDto, Contacts.class);
        logger.info("contacts Data---" + contacts);
        return "user/addcontacts";
    }

    // user view contacts
    @RequestMapping(value = "/viewcontacts")
    public String viewContacts() {
        return "user/viewcontacts";
    }

}
