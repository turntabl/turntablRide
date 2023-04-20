package com.ttrides.turntablRides.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user")
@AllArgsConstructor
public class UserModel {
    @Id
    private int id;

    private String firstName, lastName, profileUrl;

    @Indexed(unique = true)
    private String email;
}
