package com.scm.scm20.repositories;

import com.scm.scm20.model.Contacts;
import com.scm.scm20.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepo extends JpaRepository<Contacts, String> {
    // find the contact by user
    // custom finder method
    Contacts findByUser(User user);

    // custom query method
    @Query("SELECT c FROM Contacts c WHERE c.user.id = :userId")
    List<Contacts> findByUserId(@Param("userId") String userId);
}
