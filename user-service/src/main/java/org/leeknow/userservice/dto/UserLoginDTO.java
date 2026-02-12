package org.leeknow.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserLoginDTO {

    @NotNull
    @NotEmpty
    @Email
    @NotBlank
    private String email;

    @NotNull
    @NotEmpty
    @NotBlank
    private String password;
}
