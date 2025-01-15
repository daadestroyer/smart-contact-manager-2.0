package com.scm.scm20.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class SocialLink {
    @Id
    private Long id;
    private String link;
    private String title;
    @ManyToOne
    private Contacts contacts;
}
