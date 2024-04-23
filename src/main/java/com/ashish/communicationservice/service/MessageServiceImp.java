package com.ashish.communicationservice.service;

import com.ashish.communicationservice.model.Message;
import com.ashish.communicationservice.model.Trainer;
import com.ashish.communicationservice.model.User;
import com.ashish.communicationservice.repository.MessageRepository;
import com.ashish.communicationservice.repository.TrainerRepository;
import com.ashish.communicationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class MessageServiceImp implements MessageService{
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TrainerRepository trainerRepository;

    @Override
    public Message sendMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getMessagesBetweenUsers(UUID userId, UUID trainerId) {
        User user = userRepository.findById(userId).orElse(null);
        Trainer trainer = trainerRepository.findById(trainerId).orElse(null);
        if (user == null || trainer == null || !trainer.getUsers().contains(user)) {
            return Collections.emptyList();
        }
        return messageRepository.findBySenderIdAndRecipientId(userId, trainerId);
    }
}
