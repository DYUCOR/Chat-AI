package com.chat.chat.controller;


import com.chat.chat.service.AiService;
import com.chat.chat.service.ChatMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final AiService aiService;

    @MessageMapping("/send")
    @SendTo("/userMessage/messages")
    public String sendMessage(String message) {
        return aiService.enviarMensagemIA(message);
    }



}


