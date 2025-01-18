package com.scm.scm20.controller;

import com.scm.scm20.forms.SignupForm;
import com.scm.scm20.helper.Message;
import com.scm.scm20.helper.MessageType;
import com.scm.scm20.model.User;
import com.scm.scm20.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {
    // controller which is used to serve thymeleaf pages

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    @RequestMapping("/home")
    public String home(){
        return "home";
    }

    @RequestMapping("/about")
    public String about(){
        return "about";
    }

    @RequestMapping("/services")
    public String services(){
        return "services";
    }
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/contact")
    public String contact(){
        return "contact";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/signup")
    public String signup(Model model){
        SignupForm signupForm = new SignupForm();
      //  Setting default values from backend
//        signupForm.setName("default name");
//        signupForm.setAbout("enter about your self");
        model.addAttribute("signupForm",signupForm);
        return "signup";
    }

    @RequestMapping(value = "/do-register",method = RequestMethod.POST)
    public String processingRegistration(@ModelAttribute SignupForm signupForm, HttpSession session){
        System.out.println("Processing Registration");
        // fetch the form data
        System.out.println("---->"+signupForm);

        // validate form data, otherwise send error message
        User user = this.modelMapper.map(signupForm, User.class);

        // save user
        this.userService.saveUser(user);

        // return the message
        Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();
        session.setAttribute("message",message);

        // redirect again
        return "redirect:/signup";
    }
}
