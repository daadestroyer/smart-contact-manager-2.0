package com.scm.scm20.controller;

import com.scm.scm20.dto.SignupFormDto;
import com.scm.scm20.helper.Message;
import com.scm.scm20.helper.MessageType;
import com.scm.scm20.model.User;
import com.scm.scm20.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class PageController {

    private static final Logger logger = LoggerFactory.getLogger(PageController.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }

    @RequestMapping("/services")
    public String services() {
        return "services";
    }

    @RequestMapping("/contact")
    public String contact() {
        return "contact";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/signup")
    public String signup(Model model) {
        // Add a blank SignupFormDto object to the model
        model.addAttribute("signupFormDto", new SignupFormDto());
        return "signup";
    }

    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processingRegistration(@Valid @ModelAttribute("signupFormDto") SignupFormDto signupFormDto,
                                         BindingResult bindingResult, HttpSession session, Model model) {
        logger.info("Processing registration: {}", signupFormDto);

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            logger.warn("Validation errors occurred: {}", bindingResult.getAllErrors());
            // Add the signupFormDto back to the model
            model.addAttribute("signupFormDto", signupFormDto);
            return "signup";
        }

        // Map the DTO to the User entity
        User user = modelMapper.map(signupFormDto, User.class);

        try {
            // Save the user
            userService.saveUser(user);

            // Set success message in session
            Message message = Message.builder()
                    .content("Registration Successful!")
                    .type(MessageType.green)
                    .build();
            session.setAttribute("message", message);

            // Redirect to a success or login page
            return "redirect:/signup";
        } catch (Exception e) {
            logger.error("Error occurred during registration: ", e);

            // Set error message in session
            Message message = Message.builder()
                    .content("An error occurred during registration. Please try again.")
                    .type(MessageType.red)
                    .build();
            session.setAttribute("message", message);

            // Redirect back to signup page
            return "redirect:/signup";
        }
    }
}