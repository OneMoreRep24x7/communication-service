package com.ashish.communicationservice.repository;

import com.ashish.communicationservice.model.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ChatRoomRepository extends MongoRepository<ChatRoom,String> {
    Optional<ChatRoom> findByParticipants(List<String> participants);

    List<ChatRoom> findByParticipantsContaining(String trainerId);
}
