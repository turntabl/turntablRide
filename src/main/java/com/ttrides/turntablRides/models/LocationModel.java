package com.ttrides.turntablRides.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "location")
public class LocationModel {

    @Id
    private Long id;

    private int accuracy;

    private String longitude, latitude;

}
