package com.ashish.communicationservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class User {

    @Id
    private UUID userId;
    private String fullName;
    private Status status;
    private UUID trainerId;


}
