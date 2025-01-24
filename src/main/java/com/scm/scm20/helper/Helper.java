package com.scm.scm20.helper;

import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;


public class Helper {
    public static String getEmailOfLoggedInUser(Authentication authentication) {


        // if user logged using google or github
        if (authentication instanceof OAuth2AuthenticationToken) {
            // convert principal to OAuth2AuthenticationToken
            OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) authentication;
            String clientId = authenticationToken.getAuthorizedClientRegistrationId();

            OAuth2User oauth2User = authenticationToken.getPrincipal();
            String username = "";
            if (clientId.equalsIgnoreCase("google")) {
                System.out.println("Getting email from google");
                username = oauth2User.getAttribute("email");

            } else if (clientId.equalsIgnoreCase("github")) {
                System.out.println("Getting email from github");
                username = oauth2User.getAttribute("email") != null ? oauth2User.getAttribute("email").toString()
                        : oauth2User.getAttribute("login").toString() + "@gmail.com";
            }
            return username;
        } else {
            // user logged in through email and password [normal way]
            System.out.println("Getting data from local db");
            return authentication.getName();
        }


    }
}
