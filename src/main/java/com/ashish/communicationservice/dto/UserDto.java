package com.ashish.communicationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDto {
    private String userId;
    private String userFistName;
    private String userLastName;
    private String  trainerId;
    private String trainerFirstName;
    private String trainerLastName;

}
