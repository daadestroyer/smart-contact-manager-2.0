package com.scm.scm20.service.impl;

import com.scm.scm20.customexception.ContactNotFoundException;
import com.scm.scm20.model.Contacts;
import com.scm.scm20.model.User;
import com.scm.scm20.repositories.ContactRepo;
import com.scm.scm20.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepo contactRepo;


    @Override
    public Contacts saveContact(Contacts contact) {
        String contactId = UUID.randomUUID().toString();
        contact.setContactId(contactId);
        return contactRepo.save(contact);
    }

    @Override
    public Contacts update(Contacts contact) {
        return null;
    }

    @Override
    public List<Contacts> getAll() {
        return contactRepo.findAll();
    }

    @Override
    public Contacts getById(String id) {
        return contactRepo.findById(id).orElseThrow(()->new ContactNotFoundException("Contact with id "+id+" not found"));
    }

    @Override
    public void delete(String id) {
        Contacts contacts = contactRepo.findById(id).orElseThrow(() -> new ContactNotFoundException("Contact with id " + id + " not found"));
        contactRepo.delete(contacts);
    }

    @Override
    public List<Contacts> getByUserId(String userId) {
        return contactRepo.findByUserId(userId);

    }



    public byte[] convertMultipartFileToBytes(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        try {
            return file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert file", e);
        }
    }
}
