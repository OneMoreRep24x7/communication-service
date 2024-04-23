package com.ashish.communicationservice.controller;

import com.ashish.communicationservice.dto.MessageRequest;
import com.ashish.communicationservice.model.Message;
import com.ashish.communicationservice.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public class MessageController {
    @Autowired
    private MessageService messageService;

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public String sendMessage(String message) {
        return message;
//        public Message sendMessage(@Payload Message message) {
//        return messageService.sendMessage(message);
    }

    @PostMapping("/getMessages")
    public List<Message> getMessagesBetweenUsers(@RequestBody MessageRequest request) {
        return messageService.getMessagesBetweenUsers(request.getUserId(), request.getTrainerId());
    }
}