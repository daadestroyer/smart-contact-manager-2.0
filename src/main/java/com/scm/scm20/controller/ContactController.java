package com.scm.scm20.controller;

import com.scm.scm20.dto.AddContactFormDto;
import com.scm.scm20.helper.Helper;
import com.scm.scm20.helper.Message;
import com.scm.scm20.helper.MessageType;
import com.scm.scm20.model.Contacts;
import com.scm.scm20.model.User;
import com.scm.scm20.service.ContactService;
import com.scm.scm20.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user/contact")
public class ContactController {

    @Autowired
    private UserService userService;
    @Autowired
    private ContactService contactService;
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
    public String processAddContact
            (@Valid @ModelAttribute("addContactFormDto") AddContactFormDto addContactFormDto,
             BindingResult bindingResult,
             Authentication authentication,
             Model model,
             HttpSession session) {
        // validate the form data [pending]
        if(bindingResult.hasErrors()){
            model.addAttribute("addContactFormDto",addContactFormDto);
            Message message = Message.builder()
                    .content("Please correct the following errors")
                    .type(MessageType.red)
                    .build();
            session.setAttribute("message", message);
            model.addAttribute("addContactFormDto", addContactFormDto);
            return "/user/addcontacts";
        }
        String emailOfLoggedInUser = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(emailOfLoggedInUser).get();
        logger.info("addContactFormDto Data---" + addContactFormDto);

        Contacts contacts = this.modelMapper.map(addContactFormDto, Contacts.class);
        logger.info("contacts Data---" + contacts);
        contacts.setUser(user);

        // set the contact profile picture [pending]

       // saving data into db [pending]
        contactService.saveContact(contacts);

        // display message in frontend
        Message message = Message.builder()
                .content("Contact added Successfully!")
                .type(MessageType.green)
                .build();
        session.setAttribute("message", message);
        return "redirect:/user/contact/add-contacts";
    }

    // user view contacts
    @RequestMapping(value = "/viewcontacts")
    public String viewContacts() {
        return "user/viewcontacts";
    }

}
