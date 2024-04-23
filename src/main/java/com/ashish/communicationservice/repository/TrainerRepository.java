package com.ashish.communicationservice.repository;

import com.ashish.communicationservice.model.Trainer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface TrainerRepository extends MongoRepository<Trainer, UUID> {
}
