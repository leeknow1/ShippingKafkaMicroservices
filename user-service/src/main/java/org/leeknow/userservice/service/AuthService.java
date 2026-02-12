package org.leeknow.userservice.service;

import lombok.RequiredArgsConstructor;
import org.leeknow.userservice.dto.UserDTO;
import org.leeknow.userservice.dto.UserLoginDTO;
import org.leeknow.userservice.entity.User;
import org.leeknow.userservice.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.leeknow.userservice.mapper.UserMapper.toUserDTO;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserDTO signIn(UserLoginDTO userLoginDTO) {
        Optional<User> user = userRepository.findByEmail(userLoginDTO.getEmail());
        if (user.isPresent()) {
            if(!passwordEncoder.matches(userLoginDTO.getPassword(), user.get().getPassword())) throw new BadCredentialsException("user.bad_credentials");

            return toUserDTO(user.get());
        }
        return null;
    }
}
