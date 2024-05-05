package com.ashish.communicationservice.controller;

import com.ashish.communicationservice.dto.MessageRequest;
import com.ashish.communicationservice.dto.UserDto;
import com.ashish.communicationservice.model.ChatRoom;
import com.ashish.communicationservice.model.Message;
import com.ashish.communicationservice.model.Trainer;
import com.ashish.communicationservice.model.User;
import com.ashish.communicationservice.service.ChatRoomService;
import com.ashish.communicationservice.service.MessageService;
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

    @Autowired
    private MessageService messageService;
    @Autowired
    private ChatRoomService chatRoomService;



    @PostMapping("/register")
    public ResponseEntity<User> registerUser(
            @RequestBody UserDto userInfo
    ){
        return ResponseEntity.ok(userService.registerUser(userInfo));
    }

    @PostMapping("/disconnect")
    public ResponseEntity<Void> disconnectUser(@RequestBody UserDto userDto) {
        userService.disconnectUser(userDto.getUserId()  );
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getUsers(
            @RequestParam("trainerId") String trainerId
    ){
        return ResponseEntity.ok(userService.getAllUsers(trainerId));
    }

    @GetMapping("/getTrainer")
    public ResponseEntity<Trainer> getTrainer(
            @RequestParam("userId") String userId
    ){
        return ResponseEntity.ok(userService.getTrainer(userId));
    }
    @PostMapping("/getMessages")
    public List<Message> getMessagesBetweenUsers(@RequestBody MessageRequest request) {
        System.out.println(request+"Request comming for fetching messages");
        return messageService.getMessagesBetweenUsers(request.getUserId(), request.getTrainerId());
    }
    @GetMapping("/chat-rooms")
    public List<ChatRoom> getChatRoomsByTrainer(
            @RequestParam("trainerId")String trainerId) {
        return chatRoomService.getTrainerChatRooms(trainerId);
    }
}
