package com.ashish.communicationservice.repository;

import com.ashish.communicationservice.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends MongoRepository<Message,String> {

    @Query("{$or: [{senderId: ?0, recipientId: ?1}, {senderId: ?1, recipientId: ?0}]}")
    List<Message> findMessagesBetweenUsers(String senderId, String recipientId);
}
