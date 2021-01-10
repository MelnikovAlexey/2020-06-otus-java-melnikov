package org.otus.education.hw17.controller;

import lombok.extern.slf4j.Slf4j;
import org.otus.education.hw17.dto.UserDto;
import org.otus.education.hw17.services.FrontendService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/api/user")
public class UserRestController {
    private final FrontendService frontendService;
    private final SimpMessagingTemplate template;

    public UserRestController(FrontendService frontendService, SimpMessagingTemplate template) {
        this.frontendService = frontendService;
        this.template = template;
    }

    @MessageMapping
    public List<UserDto> getUsers() {
        return frontendService.findAllUsers();
    }

    @MessageMapping
    public void addUser(@RequestBody UserDto user) {
        frontendService.saveUser(user, us -> template.convertAndSend("/topic/newUser", us));
    }


}
