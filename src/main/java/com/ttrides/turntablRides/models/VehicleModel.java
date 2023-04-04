package com.ttrides.turntablRides.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class VehicleModel {

    @Id
    private Long id;

    private String carNumber;

    private int seatCapacity;
}
