package com.ttrides.turntablRides.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class LocationModel {

    @Id
    private Long id;

    private int accuracy;

    private String longitude, latitude;

}
