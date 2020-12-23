package org.otus.education.hw16.controller;

import org.otus.education.hw16.data.core.model.User;
import org.otus.education.hw16.message.FrontEndService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class UserWebSocketController {

    private final FrontEndService frontendService;
    private final SimpMessagingTemplate template;

    public UserWebSocketController(FrontEndService frontendService, SimpMessagingTemplate template) {
        this.frontendService = frontendService;
        this.template = template;
    }

    @MessageMapping("/addUser")
    public void addUser(User user) {
        frontendService.saveUser(
                user,
                us -> template.convertAndSend("/topic/newUser", us)
        );
    }
}
