package org.leeknow.userservice.mapper;

import org.leeknow.userservice.dto.UserDTO;
import org.leeknow.userservice.entity.RoleEntity;
import org.leeknow.userservice.entity.User;

import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setRoles(user.getRoles().stream().map(RoleEntity::getName).collect(Collectors.toList()));
        return userDTO;
    }
}
