package com.scm.scm20.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
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
    // Storing the image as a byte array
    private String cloudinaryImagePublicId;
    @Column(name = "contact_description", nullable = false,length = 10000)
    private String contactDescription;

    @Column(name = "contact_favourite")
    private String contactFavourite;

    private String websiteLink;
    private String facebookLink;
    @ManyToOne
    private User user;

    // mapping between contact and social link
    @OneToMany(mappedBy = "contacts", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<SocialLink> links = new ArrayList<>();

    // New column to store the creation timestamp
    @CreationTimestamp
    @Column(name = "created_date", updatable = true, nullable = false)
    private LocalDateTime createdDate;
}
