package com.ashish.communicationservice.service;

import com.ashish.communicationservice.dto.UserDto;
import com.ashish.communicationservice.model.Status;
import com.ashish.communicationservice.model.Trainer;
import com.ashish.communicationservice.model.User;
import com.ashish.communicationservice.repository.TrainerRepository;
import com.ashish.communicationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TrainerRepository trainerRepository;
    @Override
    public User registerUser(UserDto userInfo) {
        User existingUser = userRepository.findById(userInfo.getUserId()).orElse(null);
        if(existingUser == null) {
            User user = User.builder()
                    .userId(userInfo.getUserId())
                    .fullName(userInfo.getUserFistName() + " " + userInfo.getUserLastName())
                    .trainerId(userInfo.getTrainerId())
                    .status(Status.ONLINE)
                    .build();
            User savedUser = userRepository.save(user);
            Trainer trainer = trainerRepository.findById(userInfo.getTrainerId()).orElse(null);
            if (trainer == null) {
                trainer = Trainer.builder()
                        .trainerId(userInfo.getTrainerId())
                        .fullName(userInfo.getTrainerFirstName() + " " + userInfo.getTrainerLastName())
                        .status(Status.ONLINE)
                        .users(new ArrayList<>())
                        .build();
                trainer = trainerRepository.save(trainer);
            }

            trainer.getUsers().add(savedUser);
            trainerRepository.save(trainer);
            return savedUser;
        }else {
            User user =  existingUser;
            if(user.getTrainerId() != userInfo.getTrainerId()){
                Trainer existingTrainer = trainerRepository.findById(user.getTrainerId()).orElse(null);
                if(existingTrainer != null){
                    existingTrainer.getUsers().removeIf(u->u.getUserId().equals(user.getUserId()));
                    trainerRepository.save(existingTrainer);
                }
                Trainer newTrainer = trainerRepository.findById(userInfo.getTrainerId()).orElse(null);
                if(newTrainer == null){
                    newTrainer = Trainer.builder()
                            .trainerId(userInfo.getTrainerId())
                            .fullName(userInfo.getTrainerFirstName() + " " + userInfo.getTrainerLastName())
                            .status(Status.ONLINE)
                            .users(new ArrayList<>())
                            .build();
                    newTrainer = trainerRepository.save(newTrainer);
                }
                newTrainer.getUsers().add(user);
                trainerRepository.save(newTrainer);
                user.setTrainerId(userInfo.getTrainerId());
            }else {
                return  user;
            }
            User savedUser = userRepository.save(user);
            return savedUser;
        }

    }

    @Override
    public void disconnectUser(String userId) {
        User storedUser = userRepository.findById(userId).orElse(null);
        if(storedUser!=null){
            storedUser.setStatus(Status.OFFLINE);
            userRepository.save(storedUser);
        }
    }
    @Override
    public void disconnectTrainer(String trainerId){
        Trainer storedTrainer = trainerRepository.findById(trainerId).orElse(null);
        if(storedTrainer!=null){
            storedTrainer.setStatus(Status.OFFLINE);
            trainerRepository.save(storedTrainer);
        }
    }

    @Override
    public List<User> getAllUsers(String trainerId) {
        Trainer trainer = trainerRepository.findById(trainerId).orElse(null);
        List<User> users = trainer.getUsers();
        return users;
    }

    @Override
    public Trainer getTrainer(String userId) {
        User user = userRepository.findById(userId).orElse(null);
        String trainerId = user.getTrainerId();
        Trainer trainer = trainerRepository.findById(trainerId).get();
        return trainer;
    }
}

