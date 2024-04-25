package com.ashish.communicationservice.service;

import com.ashish.communicationservice.model.ChatRoom;
import com.ashish.communicationservice.repository.ChatRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChatRoomServiceImp implements ChatRoomService{
    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Override
    public ChatRoom createChatRoom(UUID userId, UUID trainerId) {
        // Ensure that the participants are in a consistent order
        List<UUID> participants = Arrays.asList(userId, trainerId);
        Collections.sort(participants); // Sort to ensure consistent order

        // Check if the chat room already exists
        Optional<ChatRoom> existingChatRoom = chatRoomRepository.findByParticipants(participants);

        if (existingChatRoom.isPresent()) {
            // If it exists, return it
            return existingChatRoom.get();
        } else {
            // Otherwise, create a new one
            String uniqueId = UUID.randomUUID().toString();
            ChatRoom newChatRoom = new ChatRoom();
            newChatRoom.setId(uniqueId);
            newChatRoom.setParticipants(participants);
            return chatRoomRepository.save(newChatRoom);
        }
    }

    @Override
    public Optional<ChatRoom> getChatRoom(UUID userId, UUID trainerId) {
        // Ensure consistent order
        List<UUID> participants = Arrays.asList(userId, trainerId);
        Collections.sort(participants); // Sort to ensure consistent order
        return chatRoomRepository.findByParticipants(participants);
    }

    @Override
    public List<ChatRoom> getTrainerChatRooms(UUID trainerId) {
        return chatRoomRepository.findByParticipantsContaining(trainerId);
    }
}
