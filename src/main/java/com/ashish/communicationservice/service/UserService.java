package com.ashish.communicationservice.service;

import com.ashish.communicationservice.dto.UserDto;
import com.ashish.communicationservice.model.Trainer;
import com.ashish.communicationservice.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User registerUser(UserDto userInfo);

    void disconnectUser(String userId);

    void disconnectTrainer(String trainerId);

    List<User> getAllUsers(String trainerId);

    Trainer getTrainer(String userId);
}
