package com.scm.scm20.forms;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SignupForm {
    private String name;
    private String email;
    private String password;
    private String contact;
    private String about;

}
