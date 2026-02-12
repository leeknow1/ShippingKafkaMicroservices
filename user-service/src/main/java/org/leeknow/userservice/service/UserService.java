package org.leeknow.userservice.service;

import lombok.RequiredArgsConstructor;
import org.leeknow.userservice.dto.UserLoginDTO;
import org.leeknow.userservice.entity.RoleEntity;
import org.leeknow.userservice.entity.User;
import org.leeknow.userservice.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

import static org.leeknow.commonservice.user.Role.ROLE_USER;
import static org.leeknow.userservice.constants.RoleConstant.USER_ID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void save(UserLoginDTO userLoginDTO) {
        User user = new User();
        user.setEmail(userLoginDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userLoginDTO.getPassword()));
        user.setEnabled(true);
        user.setRoles(Set.of(new RoleEntity(USER_ID, ROLE_USER)));
        userRepository.save(user);
    }
}
