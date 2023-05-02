package com.ttrides.turntablRides.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "rider")
public class RiderModel {

    @Id
    private Long id;

    private UserModel user;

    private boolean isDriving;

    private VehicleModel vehicle;

    private boolean booked;
}