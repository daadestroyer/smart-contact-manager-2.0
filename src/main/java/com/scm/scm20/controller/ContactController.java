package com.scm.scm20.controller;

import com.scm.scm20.dto.AddContactFormDto;
import com.scm.scm20.dto.ContactSearchForm;
import com.scm.scm20.helper.Helper;
import com.scm.scm20.helper.Message;
import com.scm.scm20.helper.MessageType;
import com.scm.scm20.model.Contacts;
import com.scm.scm20.model.User;
import com.scm.scm20.service.ContactService;
import com.scm.scm20.service.ImageService;
import com.scm.scm20.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user/contact")
public class ContactController {

    @Autowired
    private ImageService imageService;
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
        if (bindingResult.hasErrors()) {
            model.addAttribute("addContactFormDto", addContactFormDto);
            Message message = Message.builder()
                    .content("Please correct the following errors")
                    .type(MessageType.red)
                    .build();
            session.setAttribute("message", message);
            model.addAttribute("addContactFormDto", addContactFormDto);
            return "/user/addcontacts";
        }

        logger.info("Get original filename --- " + addContactFormDto.getContactPicture().getOriginalFilename());
        String emailOfLoggedInUser = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(emailOfLoggedInUser).get();
        logger.info("addContactFormDto Data---" + addContactFormDto);

        Contacts contacts = this.modelMapper.map(addContactFormDto, Contacts.class);

        if (addContactFormDto.getContactPicture().getOriginalFilename() == "") {
            logger.info("setting default image");
            contacts.setCloudinaryImagePublicId("https://img.icons8.com/?size=100&id=21441&format=png&color=000000");
        } else {
            logger.info("setting provided image");
            String fileURL = imageService.uploadImage(addContactFormDto.getContactPicture());
            contacts.setCloudinaryImagePublicId(fileURL);
        }
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
    @RequestMapping(value = "/viewcontacts", method = RequestMethod.GET)
    public String viewContacts(
            @RequestParam(defaultValue = "0") int currentPage,
            @RequestParam(defaultValue = "5") int size,
            Model model,
            Authentication authentication) {
        String email = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        Pageable pageable = PageRequest.of(currentPage, size);

        Page<Contacts> contactsPage = contactService.getByUserId(user.getUserId(), pageable);

        model.addAttribute("contacts", contactsPage.getContent());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", contactsPage.getTotalPages());
        model.addAttribute("size", size); // Current page size
        model.addAttribute("contactSearchForm", new ContactSearchForm());
        model.addAttribute("contactsPage", contactsPage);

        return "/user/viewcontacts";

    }

    @RequestMapping(value = "/search")
    public String search(
            @ModelAttribute ContactSearchForm contactSearchForm,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "contactName") String sortBy,
            @RequestParam(defaultValue = "asc") String order, Model model, Authentication authentication) {
        logger.info("field and keyword is : " + contactSearchForm.getField() + " : " + contactSearchForm.getValue());
        User user = userService.getUserByEmail(Helper.getEmailOfLoggedInUser(authentication)).get();
        Page<Contacts> pageContact = null;
        if (contactSearchForm.getField().equalsIgnoreCase("contactName")) {
            pageContact = contactService.searchByName(contactSearchForm.getValue(), size, page, sortBy, order, user);
        } else if (contactSearchForm.getField().equalsIgnoreCase("contactEmail")) {
            pageContact = contactService.searchByEmail(contactSearchForm.getValue(), size, page, sortBy, order, user);
        } else if (contactSearchForm.getField().equalsIgnoreCase("contactPhoneNumber")) {
            pageContact = contactService.searchByPhone(contactSearchForm.getValue(), size, page, sortBy, order, user);
        }
        logger.info("pageContact {}", pageContact);

        model.addAttribute("contactSearchForm", contactSearchForm);

        model.addAttribute("pageContact", pageContact);

        return "user/search";
    }

    @RequestMapping("/updatecontact/{contactId}")
    public String updateContact(Model model, @PathVariable String contactId) {
        Contacts contact = contactService.getById(contactId);
        AddContactFormDto addContactFormDto = new AddContactFormDto();
        addContactFormDto.setContactName(contact.getContactName());
        addContactFormDto.setContactEmail(contact.getContactEmail());
        addContactFormDto.setContactPhoneNumber(contact.getContactPhoneNumber());
        addContactFormDto.setContactAddress(contact.getContactAddress());
        addContactFormDto.setContactDescription(contact.getContactDescription());
        addContactFormDto.setImageUrl(contact.getCloudinaryImagePublicId());
        addContactFormDto.setFacebookLink(contact.getFacebookLink());
        addContactFormDto.setWebsiteLink(contact.getWebsiteLink());
        addContactFormDto.setContactFavourite(contact.isContactFavourite());
        model.addAttribute("addContactFormDto",addContactFormDto);
        return "user/updatecontact";
    }

    @RequestMapping("/deleteContact/{contactId}")
    public String deleteContact(@PathVariable String contactId) {
        this.contactService.delete(contactId);
        return "redirect:/user/contact/viewcontacts";
    }

}
