package org.leeknow.userservice.mapper;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.leeknow.commonservice.user.dto.UserInfoDTO;
import org.leeknow.userservice.dto.UserDTO;
import org.leeknow.userservice.entity.RoleEntity;
import org.leeknow.userservice.entity.User;

import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserMapper {

    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setRoles(user.getRoles().stream().map(RoleEntity::getName).collect(Collectors.toList()));
        return userDTO;
    }

    public static UserInfoDTO toUserInfoDTO(User user) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setEmail(user.getEmail());
        userInfoDTO.setEnabled(user.isEnabled());
        return userInfoDTO;
    }
}
