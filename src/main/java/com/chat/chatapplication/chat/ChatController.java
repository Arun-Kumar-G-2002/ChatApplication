package com.chat.chatapplication.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
// This class is responsible for handling WebSocket messages
public class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    // This method will be called when a message is sent to the /chat.sendMessage endpoint
    public ChatMessage sendMessage( @Payload ChatMessage chatMessage) {
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    // This method will be called when a user joins the chat
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
