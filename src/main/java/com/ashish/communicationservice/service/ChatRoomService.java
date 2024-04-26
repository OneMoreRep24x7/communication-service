package com.ashish.communicationservice.service;

import com.ashish.communicationservice.model.ChatRoom;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatRoomService {
    ChatRoom createChatRoom(UUID userId, UUID trainerId,String chatRoomId,String fullName);

    Optional<ChatRoom> getChatRoom(UUID userId, UUID trainerId);

    List<ChatRoom> getTrainerChatRooms(UUID id);
}
