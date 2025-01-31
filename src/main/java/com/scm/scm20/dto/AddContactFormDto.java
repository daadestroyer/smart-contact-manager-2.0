package com.scm.scm20.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class AddContactFormDto {
    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Minimum three characters required for name")
    private String contactName;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    private String contactEmail;
    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number, phone number must be exactly 10 digits")
    private String contactPhoneNumber;
//        @Size(min = 10, max = 10, message = "Invalid phone number, phone number can only be of 10 digit")

    @NotBlank(message = "Address is required")
    @Size(min = 3, message = "Minimum three characters required for address")
    private String contactAddress;
    @NotBlank(message = "About is required")
    @Size(min = 10, message = "Minimum 10 characters required for about")
    private String contactDescription;
    private String facebookLink;
    private String websiteLink;
    // Instead of MultipartFile, use byte[]
    private MultipartFile contactPicture;  // Receiving the file from frontend
    private boolean contactFavourite;
    @CreationTimestamp
    @Column(name = "created_date", updatable = true, nullable = false)
    private LocalDateTime createdDate;
}
