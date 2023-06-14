package com.solvd.twitter.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {

    private String id;

    @NotNull(message = "Email is required")
    @Size(min = 7, max = 45, message = "Email must be more than 7 and less than 45 " +
            "characters")
    @Email(message = "Email is not valid")
    private String email;

    @NotNull(message = "Password is required")
    @Size(min = 7, max = 45, message = "Password must be more than 7 and less than 45 " +
            "characters")
    private String password;

    @NotNull(message = "Name is required")
    @Size(min = 2, max = 20, message = "Name must be more than 3 and less than 20 " +
            "characters")
    private String name;

    @NotNull(message = "Surname is required")
    @Size(min = 2, max = 20, message = "Surname must be more than 2 and less than 20 " +
            "characters")
    private String surname;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

}
