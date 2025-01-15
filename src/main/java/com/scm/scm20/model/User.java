package com.scm.scm20.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User {
    // User entity can associated with multiple Contacts in his contact list
    // but Single Contacts can not be associated with many User
    // User(1)----Contacts(M) -- possible
    // Contact(1)----User(M) -- not possible
    @Id
    private String userId;

    @Column(name = "user_name",nullable = false)
    private String name;
    @Column(name="user_email",nullable = false)
    private String email;
    @Column(name="user_password",nullable = false)
    private String password;
    @Column(name="user_about",nullable = false,length = 10000)
    private String about;
    @Column(name="user_profile_pic"  )
    private String profilePicture;
    @Column(name="user_phone_number",nullable = false)
    private String phoneNumber;

    // information
    private boolean enabled = false;
    private boolean emailVerified = false;
    private boolean phoneVerified = false;

    // Provider details
    private Providers provider = Providers.SELF;
    private String providerUserId;

    // social link
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    private List<Contacts> contactsList = new ArrayList<>();
}
