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
public class Contacts {
    @Id
    @Column(name = "contact_id", nullable = false)
    private String contactId;
    @Column(name = "contact_name", nullable = false)
    private String contactName;
    @Column(name = "contact_email", nullable = false)
    private String contactEmail;
    @Column(name = "contact_phone_number", nullable = false)
    private String contactPhoneNumber;

    @Column(name = "contact_address", nullable = false)
    private String contactAddress;
    @Column(name = "contact_picture", nullable = false)
    private String contactPicture;
    @Column(name = "contact_description", nullable = false,length = 10000)
    private String contactDescription;

    @Column(name = "contact_favourite")
    private String contactFavourite;

    @ManyToOne
    private User user;

    @OneToMany
    private List<SocialLink> contact = new ArrayList<>();

}
