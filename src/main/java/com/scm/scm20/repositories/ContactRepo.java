package com.scm.scm20.repositories;

import com.scm.scm20.model.Contacts;
import com.scm.scm20.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface ContactRepo extends JpaRepository<Contacts, String> {
    // find the contact by user
    // custom finder method
    Contacts findByUser(User user);

    // custom query method
    @Query("SELECT c FROM Contacts c WHERE c.user.id = :userId")
    Page<Contacts> findByUserId(@Param("userId") String userId, Pageable pageable);

    Page<Contacts> findByUserAndContactNameContaining(User loggedInUser, String nameKeyword, Pageable pageable);

    Page<Contacts> findByUserAndContactEmailContaining(User loggedInUser, String emailKeyword, Pageable pageable);

    Page<Contacts> findByUserAndContactPhoneNumberContaining(User loggedInUser, String contactKeyword, Pageable pageable);
}
