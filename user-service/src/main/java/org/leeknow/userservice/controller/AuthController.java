package org.leeknow.userservice.controller;

import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.leeknow.userservice.dto.UserDTO;
import org.leeknow.userservice.dto.UserLoginDTO;
import org.leeknow.userservice.service.AuthService;
import org.leeknow.userservice.service.jwt.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final TokenService tokenService;

    private final AuthService authService;

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody UserLoginDTO userLoginDTO) throws JOSEException {
        UserDTO userDTO = authService.signIn(userLoginDTO);

        if (userDTO == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        return ResponseEntity.ok(Map.of("token", tokenService.generateAccessToken(userDTO.getUserId(), userDTO.getRoles())));
    }
}
