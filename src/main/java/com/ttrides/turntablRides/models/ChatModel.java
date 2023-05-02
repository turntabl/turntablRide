package com.ttrides.turntablRides.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
public class ChatModel {

    @Id
    private Long id;

    private UserModel sender, recipient;

    private String content, type;

}
