package com.ashish.communicationservice.service;

import com.ashish.communicationservice.model.Message;

import java.util.List;
import java.util.UUID;

public interface MessageService {
    Message sendMessage(Message message);

    List<Message> getMessagesBetweenUsers(UUID userId, UUID trainerId);
}
