package com.ttrides.turntablRides.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "routeGroup")
public class RouteGroupModel {

    @Id
    private Long id;

    private RideModel rides[];

}
