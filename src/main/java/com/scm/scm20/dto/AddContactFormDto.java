package com.scm.scm20.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AddContactFormDto {
        private String name;
        private String email;
        private String phone;
        private String address;
        private String about;
        private String facebookLink;
        private String websiteLink;
        private MultipartFile picture;
        private boolean favourite;
}
