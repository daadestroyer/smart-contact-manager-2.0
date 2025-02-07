package com.scm.scm20.service;

import com.scm.scm20.model.Contacts;
import com.scm.scm20.model.User;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    // save contacts
    Contacts saveContact(Contacts contact);

    // update contact
    Contacts update(Contacts contact);

    // get contacts
    List<Contacts> getAll();

    // get contact by id
    Contacts getById(String id);

    // delete contact
    void delete(String id);

    // get contacts by userId
    Page<Contacts> getByUserId(String userId, Pageable pageable);

    byte[] convertMultipartFileToBytes(MultipartFile file);

    Page<Contacts> searchByName(String keyword, int size, int page, String sortBy, String order);

    Page<Contacts> searchByEmail(String keyword, int size, int page, String sortBy, String order);

    Page<Contacts> searchByPhone(String keyword, int size, int page, String sortBy, String order);

}
