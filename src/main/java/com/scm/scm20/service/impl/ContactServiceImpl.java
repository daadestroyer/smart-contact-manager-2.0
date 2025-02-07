package com.scm.scm20.service.impl;

import com.scm.scm20.controller.ContactController;
import com.scm.scm20.customexception.ContactNotFoundException;
import com.scm.scm20.model.Contacts;
import com.scm.scm20.model.User;
import com.scm.scm20.repositories.ContactRepo;
import com.scm.scm20.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class ContactServiceImpl implements ContactService {
    Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);

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
        return contactRepo.findById(id).orElseThrow(() -> new ContactNotFoundException("Contact with id " + id + " not found"));
    }

    @Override
    public void delete(String id) {
        Contacts contacts = contactRepo.findById(id).orElseThrow(() -> new ContactNotFoundException("Contact with id " + id + " not found"));
        contactRepo.delete(contacts);
    }

    @Override
    public Page<Contacts> getByUserId(String userId, Pageable pageable) {
        return contactRepo.findByUserId(userId, pageable);
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

    @Override
    public Page<Contacts> searchByName(String keyword, int size, int page, String sortBy, String order) {
        logger.info("Search by name called");
        Sort sort = order.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        PageRequest pageAble = PageRequest.of(page, size, sort);
        logger.info("searchByName : "+contactRepo.findByContactNameContaining(keyword,pageAble));
        return contactRepo.findByContactNameContaining(keyword, pageAble);
    }

    @Override
    public Page<Contacts> searchByEmail(String keyword, int size, int page, String sortBy, String order) {
        Sort sort = order.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        PageRequest pageAble = PageRequest.of(page, size, sort);
        return contactRepo.findByContactNameContaining(keyword, pageAble);
    }

    @Override
    public Page<Contacts> searchByPhone(String keyword, int size, int page, String sortBy, String order) {
        Sort sort = order.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        PageRequest pageAble = PageRequest.of(page, size, sort);
        return contactRepo.findByContactPhoneNumberContaining(keyword, pageAble);
    }


}
