package com.solvd.twitter.web.dto;

import com.solvd.twitter.web.dto.validation.OnCreate;
import com.solvd.twitter.web.dto.validation.OnUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {

    @NotNull(message = "Id is required", groups = {OnUpdate.class})
    private String id;

    @NotNull(message = "Email is required", groups = {OnCreate.class, OnUpdate.class})
    @Size(min = 7, max = 45, message = "Email must be more than 7 and less than 45 " +
            "characters", groups = {OnCreate.class, OnUpdate.class})
    @Email(message = "Email is not valid", groups = {OnCreate.class, OnUpdate.class})
    private String email;

    @NotNull(message = "Password is required", groups = {OnUpdate.class})
    @Size(min = 7, max = 45, message = "Password must be more than 7 and less than 45 " +
            "characters", groups = {OnCreate.class, OnUpdate.class})
    private String password;

    @NotNull(message = "Name is required", groups = {OnCreate.class, OnUpdate.class})
    @Size(min = 2, max = 20, message = "Name must be more than 3 and less than 20 " +
            "characters", groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @NotNull(message = "Surname is required", groups = {OnCreate.class, OnUpdate.class})
    @Size(min = 2, max = 20, message = "Surname must be more than 2 and less than 20 " +
            "characters", groups = {OnCreate.class, OnUpdate.class})
    private String surname;

    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

}
