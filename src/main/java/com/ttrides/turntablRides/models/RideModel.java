package com.ttrides.turntablRides.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "ride")
public class RideModel {

    @Id
    private Long id;

    private RiderModel passengers[];

    private RideDetailsModel rideDetails;

}