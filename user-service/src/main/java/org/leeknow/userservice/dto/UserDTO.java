package org.leeknow.userservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    private Integer userId;
    private List<String> roles;
}
