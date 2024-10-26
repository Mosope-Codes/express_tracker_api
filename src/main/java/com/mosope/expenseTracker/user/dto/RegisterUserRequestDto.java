package com.mosope.expenseTracker.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterUserRequestDto {
    @NotBlank(message = "firstname cannot be blank")
    private String firstName;

    @NotBlank(message = "lastname cannot be blank")
    private String lastName;

    @NotBlank(message = "username cannot be blank")
    private String username;

    @NotBlank(message = "email cannot be blank")
    private String email;

    @NotBlank(message = "password cannot be blank")
    private String password;
}
