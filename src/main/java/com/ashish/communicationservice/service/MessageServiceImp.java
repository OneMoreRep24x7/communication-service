package com.ashish.communicationservice.service;

import com.ashish.communicationservice.model.ChatRoom;
import com.ashish.communicationservice.model.Message;
import com.ashish.communicationservice.model.User;
import com.ashish.communicationservice.repository.MessageRepository;
import com.ashish.communicationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MessageServiceImp implements MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private ChatRoomService chatRoomService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Message  sendMessage(Message message) {
        // Find or create a chat room based on sender and recipient
        UUID senderId = UUID.fromString(message.getSenderId());
        UUID recipientId = UUID.fromString(message.getRecipientId());
        User user = userRepository.findByUserId(senderId).orElse(null);


        String fullName;
        if (user != null) {
            fullName = user.getFullName(); // User exists, get their full name
        } else {
            fullName = "Unknown User"; // Default value if user not found
        }

        ChatRoom chatRoom = chatRoomService
                .getChatRoom(senderId, recipientId)
                .orElse(chatRoomService.createChatRoom(senderId,recipientId,message.getChatRoomId(),fullName));

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

