package com.ashish.communicationservice.repository;

import com.ashish.communicationservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends MongoRepository<User, UUID> {
   Optional<User> findByUserId(UUID senderId);
}
