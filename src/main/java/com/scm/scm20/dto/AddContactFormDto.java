package com.scm.scm20.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AddContactFormDto {
        private String contactName;
        private String contactEmail;
        private String contactPhoneNumber;
        private String contactAddress;
        private String contactDescription;
        private String facebookLink;
        private String websiteLink;
        private MultipartFile contactPicture;
        private boolean contactFavourite;
}
