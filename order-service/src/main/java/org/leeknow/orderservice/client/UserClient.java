package org.leeknow.orderservice.client;

import org.leeknow.commonservice.user.dto.UserInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/users/{id}")
    UserInfoDTO getUserInfo(@PathVariable("id") int id, @RequestHeader("Authorization") String authorization);
}
