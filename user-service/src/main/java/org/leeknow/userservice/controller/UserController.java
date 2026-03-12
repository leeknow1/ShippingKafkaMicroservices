package org.leeknow.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.leeknow.commonservice.user.dto.UserInfoDTO;
import org.leeknow.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserInfoDTO> findUserById(@PathVariable("userId") int userId) {
        return ResponseEntity.ok(userService.findUserById(userId));
    }
}
