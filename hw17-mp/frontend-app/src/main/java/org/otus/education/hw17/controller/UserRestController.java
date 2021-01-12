package org.otus.education.hw17.controller;

import lombok.extern.slf4j.Slf4j;
import org.otus.education.hw17.dto.UserDto;
import org.otus.education.hw17.services.FrontendService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class UserRestController {
    private final FrontendService frontendService;

    public UserRestController(FrontendService frontendService) {
        this.frontendService = frontendService;
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(frontendService.findAllUsers());
    }

}
