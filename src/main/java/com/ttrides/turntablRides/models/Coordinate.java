package com.ttrides.turntablRides.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Coordinate {

    @Id
    private String Id;

    private Location location;

    private class Location {
        private Long latitude, longitude;
    }
}
