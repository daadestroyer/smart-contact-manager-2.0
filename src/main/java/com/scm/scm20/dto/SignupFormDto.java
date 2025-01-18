package com.scm.scm20.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SignupFormDto {

    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Minimum three characters required for name")
    private String name;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 5, message = "Minimum 5 characters required for password")
    private String password;
    @NotBlank(message = "Phone is required")
    @Size(min = 10, max = 10, message = "Invalid phone number, phone number can only be of 10 digit")
    private String contact;
    @NotBlank(message = "About is required")
    @Size(min = 10, message = "Minimum 10 characters required for about")
    private String about;

}
