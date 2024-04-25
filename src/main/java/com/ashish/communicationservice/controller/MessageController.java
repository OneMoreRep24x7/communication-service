package com.ashish.communicationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashish.communicationservice.dto.MessageRequest;
import com.ashish.communicationservice.model.Message;
import com.ashish.communicationservice.service.MessageService;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;


    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/sendMessage")
    public void sendMessage(@Payload Message message) {
        // Add chatRoomId if not provided
        if (message.getChatRoomId() == null) {
            message.setChatRoomId(message.getSenderId() + "_" + message.getRecipientId());
        }

        // Persist the message and associate it with the correct chat room
        // Let's assume `messageService.sendMessage(message)` saves the message to the database
        Message savedMessage = messageService.sendMessage(message);

        // Send the message to the appropriate topic
        simpMessagingTemplate.convertAndSend("/topic/chat_room/" + savedMessage.getChatRoomId(), savedMessage);
    }


}
