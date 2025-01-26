package com.scm.scm20.customexception;

public class ContactNotFoundException extends RuntimeException{
    public ContactNotFoundException(String msg) {
        super(msg);
    }
}
