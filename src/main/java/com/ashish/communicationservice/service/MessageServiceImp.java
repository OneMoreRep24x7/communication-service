package com.ashish.communicationservice.service;

import com.ashish.communicationservice.model.ChatRoom;
import com.ashish.communicationservice.model.Message;
import com.ashish.communicationservice.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class MessageServiceImp implements MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ChatRoomService chatRoomService;

    @Override
    public Message sendMessage(Message message) {
        // Find or create a chat room based on sender and recipient
        UUID senderId = UUID.fromString(message.getSenderId());
        UUID recipientId = UUID.fromString(message.getRecipientId());

        ChatRoom chatRoom = chatRoomService
                .getChatRoom(senderId, recipientId)
                .orElse(chatRoomService.createChatRoom(senderId, recipientId));

        // Set the chat room ID in the message
        message.setChatRoomId(chatRoom.getId());

        return messageRepository.save(message);
    }

    @Override
    public List<Message> getMessagesBetweenUsers(UUID userId, UUID trainerId) {
        String userIdString = userId.toString();
        String trainerIdString = trainerId.toString();

        return messageRepository.findMessagesBetweenUsers(userIdString, trainerIdString);
    }
}

