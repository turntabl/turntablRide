package com.ttrides.turntablRides.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
public class StopModel {

    @Id
    private Long id;

    private LocationModel pickup, dropOff;
}
