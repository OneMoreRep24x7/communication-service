package com.ashish.communicationservice.service;

import com.ashish.communicationservice.model.ChatRoom;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ChatRoomService {
    ChatRoom createChatRoom(String userId, String trainerId,String chatRoomId,String fullName);

    Optional<ChatRoom> getChatRoom(String userId, String trainerId);

    List<ChatRoom> getTrainerChatRooms(String id);
}
