package org.otus.education.hw17.front.controller;

import org.otus.education.hw17.front.dto.UserDto;
import org.otus.education.hw17.front.services.FrontendService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class UserWebSocketController {

    private final FrontendService frontendService;
    private final SimpMessagingTemplate template;

    public UserWebSocketController(FrontendService frontendService, SimpMessagingTemplate template) {
        this.frontendService = frontendService;
        this.template = template;
    }

    @MessageMapping("/addUser")
    public void addUser(UserDto user) {
        frontendService.saveUser(
                user,
                us -> template.convertAndSend("/topic/newUser", us)
        );
    }
}
