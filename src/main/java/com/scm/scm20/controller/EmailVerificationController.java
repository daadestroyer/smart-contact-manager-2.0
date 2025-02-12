package com.scm.scm20.controller;

import com.scm.scm20.helper.Message;
import com.scm.scm20.helper.MessageType;
import com.scm.scm20.model.User;
import com.scm.scm20.repositories.UserRepo;
import com.scm.scm20.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class EmailVerificationController {

    // verify email
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;
    @GetMapping("/verify-email")
    public String verifyUser(@RequestParam("token") String token, HttpSession session, Model model){
        System.out.println("Email verification");
        User user = userService.findByEmailToken(token);
        if(user != null){
            if (user.getEmailToken().equals(token)) {
                user.setEmailVerified(true);
                user.setEnabled(true);
                userRepo.save(user);
                session.setAttribute("message", Message.builder().content("Email Verified").type(MessageType.green).build());
                return "status_page";
            }
            session.setAttribute("message", Message.builder().content("Email not verified").type(MessageType.red).build());
            return "status_page";
        }
        session.setAttribute("message", Message.builder().content("Email not verified").type(MessageType.red).build());
        return "status_page";
    }
}
