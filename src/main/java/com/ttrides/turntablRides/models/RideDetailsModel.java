package com.ttrides.turntablRides.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
public class RideDetailsModel {

    @Id
    private Long id;

    private StopModel stops[];

    private LocationModel departure, destination;

}