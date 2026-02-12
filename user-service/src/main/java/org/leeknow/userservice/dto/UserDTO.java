package org.leeknow.userservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    private String email;
    private List<String> roles;
}
