package com.ashish.communicationservice.repository;

import com.ashish.communicationservice.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends MongoRepository<Message,String> {

    List<Message> findBySenderIdAndRecipientId(UUID userId, UUID trainerId);
}
