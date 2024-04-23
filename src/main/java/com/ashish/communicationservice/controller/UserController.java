package com.ashish.communicationservice.controller;

import com.ashish.communicationservice.dto.UserDto;
import com.ashish.communicationservice.model.Trainer;
import com.ashish.communicationservice.model.User;
import com.ashish.communicationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/chats")
public class UserController {
    @Autowired
    private UserService userService;



    @PostMapping("/register")
    public ResponseEntity<User> registerUser(
            @RequestBody UserDto userInfo
    ){
        return ResponseEntity.ok(userService.registerUser(userInfo));
    }

    @PostMapping("/disconnect")
    public ResponseEntity<Void> disconnectUser(@RequestBody UserDto userDto) {
        userService.disconnectUser(userDto.getUserId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getUsers(
            @RequestParam("trainerId") UUID trainerId
    ){
        return ResponseEntity.ok(userService.getAllUsers(trainerId));
    }

    @GetMapping("/getTrainer")
    public ResponseEntity<Trainer> getTrainer(
            @RequestParam("userId") UUID userId
    ){
        return ResponseEntity.ok(userService.getTrainer(userId));
    }
}
